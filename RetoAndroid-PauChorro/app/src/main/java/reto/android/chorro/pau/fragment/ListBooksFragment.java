package reto.android.chorro.pau.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Vector;

import reto.android.chorro.pau.Adapter.AdapterBook;
import reto.android.chorro.pau.Application;
import reto.android.chorro.pau.MainActivity;
import reto.android.chorro.pau.Model.Book;
import reto.android.chorro.pau.R;

/**
 * Created by pauchorroyanguas on 20/12/15.
 */
public class ListBooksFragment extends Fragment {

    private static final String TAG = "ListBooksFragment.class";

    private Activity activity;
    private RecyclerView recyclerView;
    private AdapterBook adapter;

    private Vector<Book> books;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Log.d(TAG, "onAttach");

        if(context instanceof Activity)
        {
            this.activity = (Activity) context;
            if(Application.getAdapter() != null)
                adapter = Application.getAdapter();

            else {
                Toast.makeText(activity, "Adapter has not been initialized",
                        Toast.LENGTH_SHORT).show();
                activity.finish();
            }
            books = Book.getMockBooks();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView");
        View view = inflador.inflate(R.layout.fragment_list_ebooks,
                container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new GridLayoutManager(activity,2));
        recyclerView.setAdapter(adapter);

        Toast.makeText(getContext(), "Login", Toast.LENGTH_SHORT);

        adapter.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = recyclerView.getChildAdapterPosition(v);
                Toast.makeText(activity.getApplicationContext(),
                        "Item clicked: " + recyclerView.getChildAdapterPosition(v), Toast.LENGTH_SHORT).show();
                ((MainActivity) activity).showEbookInfo(id);

            }
        });

        return view;
    }
}
