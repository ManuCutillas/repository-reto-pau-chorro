package reto.android.chorro.pau;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dropbox.client2.android.AndroidAuthSession;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import nl.siegmann.epublib.domain.Book;
import reto.android.chorro.pau.fragment.EbookFragment;
import reto.android.chorro.pau.fragment.ListBooksFragment;

public class MainActivity extends DropboxActivity {

    private final String TAG = "MainActivity";

    final static private String APP_KEY = "osm5yq0ctxiios1";
    final static private String APP_SECRET = "fa3s3swog37t0f9";

    public static final String ARG_DISPLAY = "Args_display";
    public static final int GRID = 1;
    public static final int LIST = 2;
    boolean grid = true;
    boolean sortByTitle = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Application.initializeAdapter(getApplicationContext());
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_user_login);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);


        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

       Log.d(TAG, "OnResume");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sort_list:

                String msg = "";
                if(!sortByTitle)
                {
                    sortByTitle = true;
                    sortByTitle();
                    msg = "Sorted by Title";
                    item.setTitle("Sort by Date");
                    item.setIcon(R.drawable.ic_date_range_black_24dp);
                }
                else
                {
                    sortByTitle = false;
                    sortByDate();
                    msg = "Sorted by Date";
                    item.setTitle("Sort by Title");
                    item.setIcon(R.drawable.ic_sort_by_alpha_black_24dp);
                }

                Application.getAdapter().notifyDataSetChanged();
                Toast.makeText(this, msg, Toast.LENGTH_LONG).show();

                break;
            case R.id.type_view:
                if(grid) {
                    grid = false;
                    changeList(LIST);
                    item.setTitle("Change to GridView");
                    item.setIcon(R.drawable.ic_view_module_black_24dp);
                }
                else {
                    grid = true;
                    changeList(GRID);
                    item.setTitle("Change to ListView");
                    item.setIcon(R.drawable.ic_view_headline_black_24dp);
                }
                break;
            case R.id.menu_about:
                Toast.makeText(this, "App by Pau Chorro", Toast.LENGTH_LONG).show();
                break;
        }
        return false;
    }


    public void showEbookInfo(int id)
    {
        EbookFragment ebookFragment = (EbookFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_ebook);
        if (ebookFragment != null) {
            ebookFragment.putInfoBook(id);
        } else {
            EbookFragment newFragment = new EbookFragment();
            Bundle args = new Bundle();
            args.putInt(EbookFragment.ARG_ID_LIBRO, id);
            newFragment.setArguments(args);
            FragmentTransaction transaccion = getSupportFragmentManager()
                    .beginTransaction();
            transaccion.replace(R.id.main_container, newFragment);
            transaccion.addToBackStack(null);
            transaccion.commit();
        }
    }

    public void changeList(int id)
    {
        ListBooksFragment listBooksFragment = new ListBooksFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_DISPLAY, id);
        listBooksFragment.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        transaction.replace(R.id.main_container, listBooksFragment);

        transaction.commit();

    }

    public void sortByDate()
    {
        //SORT by TITLE
        Collections.sort(Application.getBooks(), new Comparator<Book>() {
            @Override
            public int compare(Book lhs, Book rhs) {

                Date lhsDate;
                Date rhsDate;

                SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
                String dateInString = "";

                try {

                    if (lhs.getMetadata().getDates().size() > 1) {
                        dateInString = lhs.getMetadata().getDates().get(1).getValue();
                        lhsDate = formatter.parse(dateInString);
                    } else {
                        lhsDate = new Date();
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                    lhsDate = new Date();
                }

                try {
                    if (rhs.getMetadata().getDates().size() > 1) {
                        dateInString = rhs.getMetadata().getDates().get(1).getValue();
                        rhsDate = formatter.parse(dateInString);
                    } else {
                        rhsDate = new Date();
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                    rhsDate = new Date();
                }


                return lhsDate.compareTo(rhsDate);
            }
        });
    }


    public void sortByTitle()
    {
        //SORT by TITLE
        Collections.sort(Application.getBooks(), new Comparator<Book>() {
            @Override
            public int compare(Book lhs, Book rhs) {
                return lhs.getTitle().compareTo(rhs.getTitle());
            }
        });
    }



}
