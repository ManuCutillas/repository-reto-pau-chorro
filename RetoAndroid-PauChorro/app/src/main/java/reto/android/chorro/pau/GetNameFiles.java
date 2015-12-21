package reto.android.chorro.pau;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.DropboxAPI.Entry;
import com.dropbox.client2.exception.DropboxException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import reto.android.chorro.pau.Model.Book;

/**
 * Created by pauchorroyanguas on 20/12/15.
 */
public class GetNameFiles  extends AsyncTask<Void, Long, Boolean> {

    private Context mContext;
    private final ProgressDialog mDialog;
    private DropboxAPI<?> mApi;
    private String mPath;

    private FileOutputStream mFos;

    private boolean mCanceled;
    private Long mFileLen;
    private String mErrorMsg;
    Vector<Book> books;
    private static String mainPath = Environment.getExternalStorageDirectory() +
            "/";


    public GetNameFiles(Context context, DropboxAPI<?> api,
                        String dropboxPath)
    {
        mContext = context.getApplicationContext();

        mApi = api;
        mPath = dropboxPath;

        mDialog = new ProgressDialog(context);
        mDialog.setMessage("Downloading Ebooks...");
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

            DropboxAPI.DeltaPage<Entry> deltaPage = mApi.delta(null);

            books = new Vector<>();

            int i =0;
            for(DropboxAPI.DeltaEntry<Entry> entry : deltaPage.entries)
            {
                if(entry.metadata.fileName().endsWith(".epub")) {

                    Log.d("Files....", entry.lcPath + ", " + entry.metadata.icon);
                    Book book = new Book();
                    book.setTitle(entry.metadata.fileName());
                    book.setAuthor(entry.metadata.path);
                    book.setPath(entry.lcPath);
                    book.setNameFile(entry.metadata.fileName());
                    book.setId(i);
                    books.add(book);
                    i++;

                    String inPath = mainPath+ book.getNameFile();
                    File file=new File(inPath);
                    try {

                        mFos = new FileOutputStream(file);
                    } catch (FileNotFoundException e) {
                        mErrorMsg = "Couldn't create a local file to store the image";
                        Log.d("ERROR", e.getMessage());
                        return false;
                    }

                    mApi.getFile(entry.lcPath, null, mFos, null);

                    if (mCanceled) {
                        return false;
                    }

                }

            }

        } catch (DropboxException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    protected void onProgressUpdate(Long... progress) {
        int percent = (int)(100.0*(double)progress[0]/mFileLen + 0.5);
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
            Application.getAdapter().notifyDataSetChanged();
        }

    }
}
