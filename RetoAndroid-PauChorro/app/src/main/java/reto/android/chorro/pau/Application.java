package reto.android.chorro.pau;

import android.content.Context;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.android.AndroidAuthSession;

import java.util.Vector;

import reto.android.chorro.pau.Adapter.AdapterBook;
//import reto.android.chorro.pau.Model.Book;
import nl.siegmann.epublib.domain.Book;

/**
 * Created by pauchorroyanguas on 20/12/15.
 */
public class Application {


    private static DropboxAPI<AndroidAuthSession> mApi;
    private static AdapterBook adapter;
    private static Vector<Book> books;

    public static DropboxAPI<AndroidAuthSession> getInstance() {
        if(mApi != null) return mApi;
        else return null;
    }

    public static void initializeAdapter(Context context)
    {
        if(adapter == null) {
            books =  new Vector<>();//Book.getMockBooks();
            adapter = new AdapterBook(books, context);
        }

    }

    public static AdapterBook getAdapter()
    {
        return adapter;
    }

    public static Vector<Book> getBooks()
    {
        return books;
    }

    private Application() {

    }

    public static void setInstance(DropboxAPI<AndroidAuthSession> api)
    {
        if(mApi == null) mApi = api;

    }




}
