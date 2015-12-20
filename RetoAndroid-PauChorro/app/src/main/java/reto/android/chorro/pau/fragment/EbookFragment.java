package reto.android.chorro.pau.fragment;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;

import java.io.IOException;

import reto.android.chorro.pau.Model.Book;
import reto.android.chorro.pau.R;

/**
 * Created by pauchorroyanguas on 20/12/15.
 */
public class EbookFragment extends Fragment {

    public static String ARG_ID_LIBRO = "id_ebook";
    private static final String TAG = "EbookFragment.class";
    private TextView mTxtAuthor;
    private TextView mTxtTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup contenedor,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ebook,
                contenedor, false);
        Bundle args = getArguments();
        if (args != null) {
            int position = args.getInt(ARG_ID_LIBRO);
            putInfoBook(position, view);
        } else {
            putInfoBook(0, view);
        }

        return view;
    }


    public void putInfoBook(int id, View view) {

        Book book = Book.getMockBooks().elementAt(id);
        Log.d(TAG, book.getAuthor());
        mTxtAuthor = (TextView) view.findViewById(R.id.title);
        mTxtTitle = (TextView) view.findViewById(R.id.author);
        mTxtTitle.setText(book.getTitle());
        mTxtAuthor.setText(book.getAuthor());

    }

    public void putInfoBook(int id)
    {
        putInfoBook(id, getView());
    }



}