package com.example.nhom14didong.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.example.nhom14didong.Activity.ChiTietTaiLieu;
import com.example.nhom14didong.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class YeuThichAdapter extends BaseAdapter {
    private Context context;
    private Cursor cursor;
    private LayoutInflater inflater;
    public YeuThichAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public Object getItem(int position) {
        if (cursor.moveToPosition(position)) {
            return cursor;
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        if (cursor.moveToPosition(position)) {
            int columnIndex = cursor.getColumnIndex("TAILIEUID");
            if (columnIndex != -1) {
                return cursor.getLong(columnIndex);
            }
        }
        return -1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_tailieu2, parent, false);
        }

        if (cursor.moveToPosition(position)) {
            // Get data from cursor
            int bookNameIndex = cursor.getColumnIndex("TENTAILIEU");
            int categoryIndex = cursor.getColumnIndex("THELOAI");
            int countIndex = cursor.getColumnIndex("SOLUONG");
            int statusIndex = cursor.getColumnIndex("TINHTRANG");
            int imagePathIndex = cursor.getColumnIndex("IMAGE");
            int itemIdIndex = cursor.getColumnIndex("TAILIEUID");


            // Fetch the data from the cursor
            String bookName = (bookNameIndex != -1) ? cursor.getString(bookNameIndex) : "Unknown Book Name";
            String category = (categoryIndex != -1) ? cursor.getString(categoryIndex) : "Unknown Category";
            String count = (countIndex != -1) ? cursor.getString(countIndex) : "0";
            String status = (statusIndex != -1) ? cursor.getString(statusIndex) : "Unknown Status";
            String imagePath = (imagePathIndex != -1) ? cursor.getString(imagePathIndex) : "";
            long itemId = (itemIdIndex != -1) ? cursor.getLong(itemIdIndex) : -1;

            // Set up views
            TextView bookNameView = convertView.findViewById(R.id.book_name);
            bookNameView.setText(bookName);

            TextView bookCategoryView = convertView.findViewById(R.id.book_category);
            bookCategoryView.setText(category);

            TextView bookCountView = convertView.findViewById(R.id.book_count);
            bookCountView.setText(count);

            TextView bookStatusView = convertView.findViewById(R.id.book_status);
            bookStatusView.setText(status);
            CardView cardView = convertView.findViewById(R.id.cart_view);
            // ImageView setup: Load image from path (Glide)
            ImageView bookImageView = convertView.findViewById(R.id.book_image);
            if (imagePath != null && !imagePath.isEmpty()) {
                int resId = context.getResources().getIdentifier(imagePath, "drawable", context.getPackageName());
                if (resId != 0) {
                    Glide.with(context)
                            .load(resId) // Load resource by ID
                            .placeholder(R.drawable.book_img)
                            .error(R.drawable.book_img)
                            .into(bookImageView);
                } else {
                    bookImageView.setImageResource(R.drawable.book_img); // Fallback image
                }
            } else {
                bookImageView.setImageResource(R.drawable.book_img);
            }
            cardView.setOnClickListener(v -> {
                if (itemId != -1) {
                    Intent intent = new Intent(context, ChiTietTaiLieu.class);
                    intent.putExtra("TAILIEUID", itemId);
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context, "Không tìm thấy TAILIEUID", Toast.LENGTH_SHORT).show();
                }
            });
        }
        return convertView;
    }
}
