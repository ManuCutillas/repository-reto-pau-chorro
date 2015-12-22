package reto.android.chorro.pau.Model;

import android.graphics.Bitmap;

import java.util.Date;
import java.util.Vector;

/**
 * Created by pauchorroyanguas on 20/12/15.
 */
public class BookBQ{

    private int id;
    private String path;
    private Date dateCreation;

    public BookBQ() {}

    public BookBQ(int id, String path, Date dateCreation) {
        this.id = id;
        this.path = path;
        this.dateCreation = dateCreation;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }



    public Date getDateCreation() {

        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

}
