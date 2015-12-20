package reto.android.chorro.pau;

import android.content.Context;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.android.AndroidAuthSession;

import reto.android.chorro.pau.Adapter.AdapterBook;

/**
 * Created by pauchorroyanguas on 20/12/15.
 */
public class Application {


    private static DropboxAPI<AndroidAuthSession> mApi;
    private static AdapterBook adapter;

    public static DropboxAPI<AndroidAuthSession> getInstance() {
        if(mApi != null) return mApi;
        else return null;
    }

    public static void initializeAdapter(Context context)
    {
        if(adapter == null)  adapter = new AdapterBook(null, context);

    }

    public static AdapterBook getAdapter()
    {
        return adapter;
    }

    private Application() {

    }

    public static void setInstance(DropboxAPI<AndroidAuthSession> api)
    {
        if(mApi == null) mApi = api;

    }


}
