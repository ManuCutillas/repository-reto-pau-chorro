package reto.android.chorro.pau;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.DropboxAPI.Entry;
import com.dropbox.client2.exception.DropboxException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Vector;


import nl.siegmann.epublib.epub.EpubReader;
//import reto.android.chorro.pau.Model.Book;
import nl.siegmann.epublib.domain.Book;
import reto.android.chorro.pau.Model.BookBQ;

/**
 * Created by pauchorroyanguas on 20/12/15.
 */
public class GetNameFiles  extends AsyncTask<Void, Long, Boolean> {

    private Context mContext;
    private final ProgressDialog mDialog;
    private DropboxAPI<?> mApi;
    private String mPath;

    private FileOutputStream mFos;
    DropboxAPI.DeltaPage<Entry> mDeltaPage;


    private boolean mCanceled;
    private Long mFileLen;
    private String mErrorMsg;
    List<Book> books;
    public static String mainPath = Environment.getExternalStorageDirectory() +
            "/";



    public GetNameFiles(Context context, DropboxAPI<?> api,
                        String dropboxPath)
    {
        mContext = context.getApplicationContext();

        mApi = api;
        mPath = dropboxPath;

        mDialog = new ProgressDialog(context);
        mDialog.setMessage("Downloading Ebooks...");
        mDialog.setMax(100);
        mDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mDialog.setProgress(0);
        mDialog.setCancelable(false);
        mDialog.setButton(ProgressDialog.BUTTON_POSITIVE, "Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                mCanceled = true;
                mErrorMsg = "Canceled";

                // This will cancel the getThumbnail operation by closing
                // its stream
                if (mFos != null) {
                    try {
                        mFos.close();
                    } catch (IOException e) {
                    }
                }
            }
        });
        mDialog.show();

    }

    @Override
    protected Boolean doInBackground(Void... params) {

        try {

            mDeltaPage = mApi.delta(null);

            int sizeEntries = mDeltaPage.entries.size();

            mFileLen = 0L;

            for(int i = 0; i < sizeEntries; i++)
            {
                if(mDeltaPage.entries.get(i)
                        .metadata.fileName().endsWith(".epub")) {
                    mFileLen++;
                }
            }

            books = new Vector<>();
            long i = 0;
            for(DropboxAPI.DeltaEntry<Entry> entry : mDeltaPage.entries)
            {
                if(entry.metadata.fileName().endsWith(".epub")) {

                    Log.d("Files....", entry.lcPath + ", " + entry.metadata.icon);

                    String inPath = mainPath+ entry.metadata.fileName();
                    File file=new File(inPath);

                    try {

                        mFos = new FileOutputStream(file);
                    } catch (FileNotFoundException e) {
                        mErrorMsg = "Couldn't create a local file to store the image";
                        Log.d("ERROR", e.getMessage());

                        return false;
                    }

                    publishProgress(i);
                    i++;

                    Log.d("FILE EXIST", "The file exist");
                    mApi.getFile(entry.lcPath, null, mFos, null);

                    mFos.close();

                    InputStream inputStream = new FileInputStream(file);

                    nl.siegmann.epublib.domain.Book bookito =
                            (new EpubReader()).readEpub(inputStream);

                    if (mCanceled) {
                        return false;
                    }

                    books.add(bookito);
                }
            }

        } catch (DropboxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    protected void onProgressUpdate(Long... progress) {
        int percent = (int)(100.0*(double)progress[0]/mFileLen + 0.5);
        Application.getBooks().clear();
        for(Book book: books)
        {
            Application.getBooks().add(book);
        }

        Application.getAdapter().notifyDataSetChanged();

        mDialog.setProgress(percent);
    }

    @Override
    protected void onPostExecute(Boolean result) {
        mDialog.dismiss();

        if (result) {
            Application.getBooks().clear();
            for(Book book: books)
            {
                Application.getBooks().add(book);
            }

            //SORT by TITLE
            Collections.sort(Application.getBooks(), new Comparator<Book>() {
                @Override
                public int compare(Book lhs, Book rhs) {
                    return lhs.getTitle().compareTo(rhs.getTitle());
                }
            });


            Application.getAdapter().notifyDataSetChanged();
        }
        else
        {
            Toast.makeText(mContext,
                    mErrorMsg, Toast.LENGTH_SHORT).show();

            Application.getBooks().clear();
            for(Book book: books)
            {
                Application.getBooks().add(book);
            }

            Application.getAdapter().notifyDataSetChanged();
        }

    }




}
