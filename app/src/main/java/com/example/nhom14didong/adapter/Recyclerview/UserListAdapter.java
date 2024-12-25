package com.example.nhom14didong.adapter.Recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom14didong.Model.User;
import com.example.nhom14didong.R;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 1;
    private static final int TYPE_LOADING = 2;

    private List<User> listUsers;
    private boolean isLoadingAdd;
    public interface OnUserEditClickListener {
        void onUserEditClick(User user);
        void onUserDeleteClick(User user);
    }

    private OnUserEditClickListener editClickListener;

    public void setOnUserEditClickListener(OnUserEditClickListener listener) {
        this.editClickListener = listener;
    }

    public void setUsers(List<User> users) {
        this.listUsers= users;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (TYPE_ITEM == viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_istem_user, parent, false);
            return new UserViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.iteam_loading, parent, false);
            return new LoadingViewHolder(view);
        }

    }
    public void addUsers(List<User> newUsers) {
        int startPos = listUsers.size();
        listUsers.addAll(newUsers);
        notifyItemRangeInserted(startPos, newUsers.size());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder.getItemViewType() == TYPE_ITEM) {
            User user = listUsers.get(position);
            UserViewHolder userViewHolder= (UserViewHolder) holder;
            userViewHolder.txtID.setText(user.getUserID()+"");
            userViewHolder.txtFullName.setText(user.getFullName()+"");

        }
    }

    @Override
    public int getItemCount() {
        if(listUsers!=null){
            return listUsers.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if(listUsers !=null && position == listUsers.size()-1 && isLoadingAdd){
            return  TYPE_LOADING;
        }
        return TYPE_ITEM;
    }

  public   class UserViewHolder extends RecyclerView.ViewHolder {
      private TextView txtID,txtFullName;
      private ImageButton  btn_edit,btn_delete;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            txtID=itemView.findViewById(R.id.txtUserId);
            txtFullName=itemView.findViewById(R.id.txtUserName);
            btn_edit=itemView.findViewById(R.id.btn_editUser);
            btn_delete=itemView.findViewById(R.id.btn_deleteUser);
            btn_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (editClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            editClickListener.onUserEditClick(listUsers.get(position));
                        }
                    }
                }
            });
              btn_delete.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      int position = getAdapterPosition();
                      if (position != RecyclerView.NO_POSITION) {
                          editClickListener.onUserDeleteClick(listUsers.get(position));
                      }
                  }
              });
        }
    }
    public class LoadingViewHolder extends RecyclerView.ViewHolder {
        private ProgressBar progressBar;
        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar=itemView.findViewById(R.id.progressbar);
        }
    }
    public void addFooterLoading(){
        isLoadingAdd=true;
        listUsers.add(new User());
    }
    public void removeFooterLoading(){
        isLoadingAdd=false;
        int position=listUsers.size()-1;
        User user= listUsers.get(position);
        if(user!=null){
            listUsers.remove(position);
            notifyItemRemoved(position);
        }
    }
}
