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

import reto.android.chorro.pau.fragment.EbookFragment;
import reto.android.chorro.pau.fragment.ListBooksFragment;

public class MainActivity extends DropboxActivity {

    private final String TAG = "MainActivity";

    final static private String APP_KEY = "osm5yq0ctxiios1";
    final static private String APP_SECRET = "fa3s3swog37t0f9";

    public static final String ARG_DISPLAY = "Args_display";
    public static final int GRID = 1;
    public static final int LIST = 2;
    boolean grid;



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
            case R.id.menu_preferencias:
                Toast.makeText(this, "Preferencias", Toast.LENGTH_LONG).show();

                break;
            case R.id.menu_ultimo:
                if(grid) {
                    grid = false;
                    changeList(LIST);
                }
                else {
                    grid = true;
                    changeList(GRID);
                }
                break;
            case R.id.menu_acerca:
                Toast.makeText(this, "Acerca de", Toast.LENGTH_LONG).show();
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



}
