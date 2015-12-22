package reto.android.chorro.pau.Adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

//import reto.android.chorro.pau.Model.Book;
import reto.android.chorro.pau.R;
import nl.siegmann.epublib.domain.Book;
//import nl.siegmann.epublib.domain.*;

/**
 * Created by pauchorroyanguas on 20/12/15.
 */
public class AdapterBook extends RecyclerView.Adapter<AdapterBook.ViewHolder> {

    private LayoutInflater mInflater;
    private List<Book> mBooks;
    private View.OnClickListener mOnClickListener;
    private Context mContext;
    public static String mainPath = Environment.getExternalStorageDirectory() +
            "/";

    public AdapterBook(List<Book> books, Context context) {

        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.mBooks = books;
        this.mContext = context;
    }

    //Creamos nuestro ViewHolder, con los tipos de elementos a modificar
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView cover;
        public TextView title;
        public ViewHolder(View itemView) {
            super(itemView);
            cover = (ImageView) itemView.findViewById(R.id.cover);
            cover.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = mInflater.inflate(R.layout.element_list_ebook, null);
        v.setOnClickListener(mOnClickListener);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

            Book book = mBooks.get(position);
            holder.title.setText(book.getTitle());

            if (book.getCoverImage() != null) {
               // Log.d("AdapterBook.java", book.getCoverImage().getHref());
                Picasso.with(mContext).load(book.getCoverImage().getHref()).into(holder.cover);


                try {
                    holder.cover.setImageBitmap(BitmapFactory.decodeStream(book.getCoverImage().getInputStream()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


    }



    @Override
    public int getItemCount() {
        return mBooks.size();
    }

    public void setOnItemClickListener(View.OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener; }
}
