package reto.android.chorro.pau.fragment;


import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import reto.android.chorro.pau.Application;
import nl.siegmann.epublib.domain.Book;
import reto.android.chorro.pau.R;

/**
 * Created by pauchorroyanguas on 20/12/15.
 */
public class EbookFragment extends Fragment {

    public static String ARG_ID_LIBRO = "id_ebook";
    private static final String TAG = "EbookFragment.class";
    private ImageView mCoverImage;
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

        Book book = Application.getBooks().get(id);
        mTxtTitle = (TextView) view.findViewById(R.id.title);
        mCoverImage = (ImageView) view.findViewById((R.id.ivcover));

        mTxtTitle.setText(book.getTitle());
        try {
            mCoverImage.setImageBitmap(BitmapFactory.decodeStream(book.getCoverImage().getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void putInfoBook(int id)
    {
        putInfoBook(id, getView());
    }

}
