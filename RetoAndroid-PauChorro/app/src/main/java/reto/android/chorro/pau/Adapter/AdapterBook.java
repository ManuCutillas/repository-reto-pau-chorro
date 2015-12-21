package reto.android.chorro.pau.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Vector;

import reto.android.chorro.pau.Model.Book;
import reto.android.chorro.pau.R;
//import nl.siegmann.epublib.domain.*;

/**
 * Created by pauchorroyanguas on 20/12/15.
 */
public class AdapterBook extends RecyclerView.Adapter<AdapterBook.ViewHolder> {

    private LayoutInflater mInflater;
    private Vector<Book> mBooks;
    private View.OnClickListener mOnClickListener;
    private Context mContext;

    public AdapterBook(Vector<Book> books, Context context) {

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
        Book book = mBooks.elementAt(position);
        holder.title.setText(book.getId() + ": " + book.getTitle());
        holder.cover.setImageResource(R.drawable.ic_import_contacts_black_24dp);
    }



    @Override
    public int getItemCount() {
        return mBooks.size();
    }

    public void setOnItemClickListener(View.OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener; }
}
