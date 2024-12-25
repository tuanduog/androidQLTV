package com.example.nhom14didong.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom14didong.Database.Database;
import com.example.nhom14didong.Model.User;
import com.example.nhom14didong.R;
import com.example.nhom14didong.Utils.PaginationScrollListener;
import com.example.nhom14didong.adapter.Recyclerview.UserListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class QuanLyUser extends AppCompatActivity {
    private Toolbar toolbar;
    private FloatingActionButton btn_add;
    private RecyclerView recyclerView;
    private UserListAdapter userListAdapter;
    private List<User> userList;
    private SQLiteDatabase db ;
    private  boolean isLoading;
    private boolean isLastPage;
    private int totalPage;
    private int currentPage=1;
    private final int toltal = 10;
    private static final int REQUEST_EDIT_USER = 1;

    private static final int REQUEST_ADD_USER = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quan_ly_user);
        anhxa();
        setSupportActionBar(toolbar);
        userList = new ArrayList<User>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        userListAdapter = new UserListAdapter();
        recyclerView.setAdapter(userListAdapter);
        totalPage = getTotalPage();
        System.out.println(totalPage);
        List<User> testlist = getListUsers(toltal,(currentPage-1)*toltal);
        testlist.forEach(user -> System.out.println(user.toString()));
        setFirstData();

        recyclerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            public void loadMoreItems() {
                isLoading=true;
                currentPage +=1;
               loadNextPage();

            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }
        });
        userListAdapter.setOnUserEditClickListener(new UserListAdapter.OnUserEditClickListener() {
            @Override
            public void onUserEditClick(User user) {

                editUser(user);
            }

            @Override
            public void onUserDeleteClick(User user) {
                    deleteUser(user);
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuanLyUser.this, AddUserActivity.class);
                startActivityForResult(intent, REQUEST_ADD_USER);
            }
        });

    }
    private void editUser(User user) {
        // Tạo Intent để mở màn hình sửa user
        Intent intent = new Intent(QuanLyUser.this, EditUser.class);
        intent.putExtra("USER_ID", user.getUserID());
        intent.putExtra("USER_NAME", user.getUserName());
        intent.putExtra("FULL_NAME", user.getFullName());
        intent.putExtra("EMAIL", user.getEmail());
        intent.putExtra("ROLE", user.getRole());
        startActivityForResult(intent,REQUEST_EDIT_USER);
    }
    public void deleteUser(User user) {
        int userid = user.getUserID();
        String sql = "DELETE FROM NGUOIDUNG WHERE USERID = ?";
        db = openOrCreateDatabase("mydatabase.db", MODE_PRIVATE, null);
        db.delete("NGUOIDUNG", "USERID = ?", new String[]{String.valueOf(userid)});
        refreshUserList();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ADD_USER && resultCode == RESULT_OK) {
            // Refresh the user list after adding a new user
            refreshUserList();
        } else if (requestCode == REQUEST_EDIT_USER && resultCode == RESULT_OK) {
            // Existing code for handling edit user result
            refreshUserList();
        }
    }

    private void refreshUserList() {
        // Reset pagination
        currentPage = 1;
        isLastPage = false;
        isLoading = false;

        // Clear existing list
        userList.clear();
        userListAdapter.notifyDataSetChanged();

        // Load first page of data
        setFirstData();
    }
    public void anhxa(){
        toolbar=findViewById(R.id.toolbar);
        btn_add=findViewById(R.id.btn_fa_addUser);
        recyclerView=findViewById(R.id.rcv);
        db = openOrCreateDatabase("mydatabase.db", MODE_PRIVATE, null);
    }
    //load len page đầu tiên
    private void setFirstData(){
        List<User> users = new ArrayList<User>();
        users = getListUsers(toltal,(currentPage-1)*toltal);
        userListAdapter.setUsers(users);

        if(currentPage < totalPage){
            userListAdapter.addFooterLoading();

        }else {
            isLastPage = true;
        }
    }
    private void loadNextPage(){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<User> users = getListUsers(toltal, (currentPage - 1) * toltal);
                userListAdapter.removeFooterLoading();
                userListAdapter.addUsers(users);  // Sử dụng phương thức addUsers thay vì setUsers
                isLoading = false;
                if (currentPage < totalPage) {
                    userListAdapter.addFooterLoading();
                } else {
                    isLastPage = true;
                }
            }
        },1000);


    }
    public List<User> getListUsers(int total, int at){
        Toast.makeText(this,"Load trang "+currentPage,Toast.LENGTH_SHORT).show();
        Cursor cursor = db.rawQuery("SELECT * FROM NGUOIDUNG LIMIT ? OFFSET ?", new String[]{String.valueOf(total), String.valueOf(at)});
        List<User> danhsachlay = new ArrayList<>();

        if (cursor.getCount() > 0 && cursor.moveToFirst()) {
            do {
                try {
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow("USERID"));
                    String name = cursor.getString(cursor.getColumnIndexOrThrow("USERNAME"));
                    String pass = cursor.getString(cursor.getColumnIndexOrThrow("USERPASS"));
                    String fullName = cursor.getString(cursor.getColumnIndexOrThrow("FULLNAME"));
                    String email = cursor.getString(cursor.getColumnIndexOrThrow("EMAIL"));
                    String role = cursor.getString(cursor.getColumnIndexOrThrow("ROLE"));
                    String dataCreate = cursor.getString(cursor.getColumnIndexOrThrow("DATECREATE"));
                    User user = new User();
                    user.setUserID(id);
                    user.setUserName(name);
                    user.setUserPass(pass);
                    user.setFullName(fullName);
                    user.setEmail(email);
                    user.setRole(role);
                    user.setDataCreate(dataCreate);
                    danhsachlay.add(user);
                } catch (IllegalArgumentException e) {
                    Log.e("DatabaseError", "Column not found: " + e.getMessage());
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return danhsachlay;
    }
    public   int getTotalPage()
    {
        Cursor cursor = db.rawQuery("SELECT count(USERID) FROM NGUOIDUNG; " ,null);
        cursor.moveToFirst();
        totalPage = cursor.getInt(0);
        cursor.close();
        return  (totalPage % toltal) >0 ? totalPage/toltal +1 : totalPage/toltal ;
    }


}