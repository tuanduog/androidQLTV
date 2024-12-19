package com.example.nhom14didong.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhom14didong.Activity.ThongKeChiTiet;
import com.example.nhom14didong.R;

public class ThongKeAdapter extends BaseAdapter {
    private Context context;
    private Cursor cursor;
    private LayoutInflater inflater;
    public ThongKeAdapter(Context context, Cursor cursor) {
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
            int columnIndex = cursor.getColumnIndex("USERID");
            if (columnIndex != -1) {
                return cursor.getLong(columnIndex);
            }
        }
        return -1;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_tkuser, parent, false);
        }
        if(cursor.moveToPosition(position)){
            int userIDIndex = cursor.getColumnIndex("USERID");
            int userNameIndex = cursor.getColumnIndex("USERNAME");
            String userID = (userIDIndex != -1) ? cursor.getString(userIDIndex) : "Unknown UserID";
            String userName = (userNameIndex != -1) ? cursor.getString(userNameIndex) : "Unknown UserName";

            TextView userIDView = convertView.findViewById(R.id.user_id);
            userIDView.setText(userID);
            TextView userNameView = convertView.findViewById(R.id.user_name);
            userNameView.setText(userName);

            TextView txtChiTiet = convertView.findViewById(R.id.txtChiTiet);
            txtChiTiet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ThongKeChiTiet.class);
                    intent.putExtra("USERID", userID);
                    intent.putExtra("USERNAME", userName);
                    context.startActivity(intent);
                }
            });
        }
        return convertView;
    }
    public void closeCursor() {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }
}
