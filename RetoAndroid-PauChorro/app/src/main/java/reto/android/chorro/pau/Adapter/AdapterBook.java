package reto.android.chorro.pau.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.IOException;
import java.util.List;
import reto.android.chorro.pau.R;
import nl.siegmann.epublib.domain.Book;


/**
 * Created by pauchorroyanguas on 20/12/15.
 */
public class AdapterBook extends RecyclerView.Adapter<AdapterBook.ViewHolder> {

    private LayoutInflater mInflater;
    private List<Book> mBooks;
    private View.OnClickListener mOnClickListener;
    private Context mContext;

    public AdapterBook(List<Book> books, Context context) {

        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.mBooks = books;
        this.mContext = context;
    }

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
               // Picasso.with(mContext).load(book.getCoverImage().getHref()).into(holder.cover);

                try {
                    Bitmap bitmap = BitmapFactory.decodeStream(book.getCoverImage().getInputStream());
                    holder.cover.setImageBitmap(bitmap);
                    //Extraemos el color principal de un bitmap
                    Palette palette = Palette.from(bitmap).generate();
                    holder.itemView.setBackgroundColor(palette.getLightMutedColor(0));
                    holder.title.setBackgroundColor(palette.getLightVibrantColor(0));

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
