package com.fox.bookmanager.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fox.bookmanager.R;
import com.fox.bookmanager.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {

    public Context context;
    public List<User> users;

    public UserAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user,viewGroup,false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder userHolder, int i) {
        userHolder.user = users.get(i);
        userHolder.tvName.setText(userHolder.user.USER_FULL_NAME);
        userHolder.tvPhone.setText(userHolder.user.USER_PHONE);
    }

    @Override
    public int getItemCount() {
        return (users == null) ? 0 : users.size();
    }

    class UserHolder extends RecyclerView.ViewHolder{

        private TextView tvName;
        private TextView tvPhone;
        User user;

        public UserHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPhone = itemView.findViewById(R.id.tvPhone);
        }
    }

}
