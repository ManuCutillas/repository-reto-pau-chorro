package reto.android.chorro.pau.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.android.AndroidAuthSession;

import java.util.Vector;

import nl.siegmann.epublib.epub.Main;
import reto.android.chorro.pau.Adapter.AdapterBook;
import reto.android.chorro.pau.Application;
import reto.android.chorro.pau.DropboxActivity;
import reto.android.chorro.pau.GetNameFiles;
import reto.android.chorro.pau.MainActivity;
import reto.android.chorro.pau.Model.Book;
import reto.android.chorro.pau.R;

/**
 * Created by pauchorroyanguas on 20/12/15.
 */
public class ListBooksFragment extends Fragment {

    private static final String TAG = "ListBooksFragment.class";

    private DropboxActivity mActivity;
    private RecyclerView recyclerView;
    private AdapterBook adapter;

    private DropboxAPI<AndroidAuthSession> mApi;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Log.d(TAG, "onAttach");

        if(context instanceof DropboxActivity)
        {
            this.mActivity = (DropboxActivity) context;
            if(Application.getAdapter() != null) adapter = Application.getAdapter();

            else {
                Toast.makeText(mActivity, "Adapter has not been initialized",
                        Toast.LENGTH_SHORT).show();
                mActivity.finish();
            }

            if(mActivity != null) mApi = this.mActivity.getmApi();

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView");
        View view = inflador.inflate(R.layout.fragment_list_ebooks,
                container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);


        Bundle args = getArguments();
        int typeDisplay = 1;
        if (args != null) {
            typeDisplay = args.getInt(MainActivity.ARG_DISPLAY);
        }

        if(typeDisplay == MainActivity.GRID) recyclerView.setLayoutManager(new GridLayoutManager(mActivity,2));
        else recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));

        recyclerView.setAdapter(adapter);


        Toast.makeText(getContext(), "Login", Toast.LENGTH_SHORT);

        adapter.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = recyclerView.getChildAdapterPosition(v);
                Toast.makeText(mActivity.getApplicationContext(),
                        "Item clicked: " + recyclerView.getChildAdapterPosition(v), Toast.LENGTH_SHORT).show();
                ((MainActivity) mActivity).showEbookInfo(id);

            }
        });

        return view;
    }
}
