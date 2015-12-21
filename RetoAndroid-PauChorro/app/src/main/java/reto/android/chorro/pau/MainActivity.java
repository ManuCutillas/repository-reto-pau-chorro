package reto.android.chorro.pau;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dropbox.client2.android.AndroidAuthSession;

import reto.android.chorro.pau.fragment.EbookFragment;

public class MainActivity extends DropboxActivity {

    private final String TAG = "MainActivity";

    final static private String APP_KEY = "osm5yq0ctxiios1";
    final static private String APP_SECRET = "fa3s3swog37t0f9";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Application.initializeAdapter(getApplicationContext());
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_user_login);

    }

    @Override
    protected void onResume() {
        super.onResume();

       Log.d(TAG, "OnResume");
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



}
