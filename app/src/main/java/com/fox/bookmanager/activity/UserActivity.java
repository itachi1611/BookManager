package com.fox.bookmanager.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.fox.bookmanager.R;
import com.fox.bookmanager.adapter.UserAdapter;
import com.fox.bookmanager.base.BaseActivity;
import com.fox.bookmanager.base.RecyclerViewClickListener;
import com.fox.bookmanager.base.RecyclerViewTouchListener;
import com.fox.bookmanager.dao.UserDAO;
import com.fox.bookmanager.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends BaseActivity {

    private UserDAO userDAO;

    private RecyclerView lvList;
    private UserAdapter userAdapter;
    private LinearLayoutManager linearLayoutManager;
    private List<User> users;
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        initViews();
        //userDAO.insertUser(new User("admin","admin123","0934203620","admin"));
        addRecycleView();

    }

    private void initViews(){
        users = new ArrayList<>();
        users.clear();
        userDAO = new UserDAO(UserActivity.this);
        lvList = findViewById(R.id.lvList);
        linearLayoutManager = new LinearLayoutManager(this);
        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void addRecycleView(){
        users = userDAO.getAllUser();
        userAdapter = new UserAdapter(this,users);

        lvList.setLayoutManager(linearLayoutManager);
        lvList.setHasFixedSize(true);
        lvList.setAdapter(userAdapter);

        lvList.addOnItemTouchListener(new RecyclerViewTouchListener(getApplicationContext(), lvList, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int pos) {}

            @Override
            public void onLongClick(View view, final int pos) {
                final PopupMenu popup = new PopupMenu(getApplicationContext(),view);
                popup.getMenuInflater().inflate(R.menu.popup_menu,popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        switch (id){
                            case R.id.menu_edit:
                                popup.dismiss();
                                onEditUser(pos);
                                break;
                            case R.id.menu_delete:
                                popup.dismiss();
                                onDeleteUser(pos);
                                break;
                        }
                        return true;
                    }
                });
                popup.show();
            }
        }));
    }

    private void onEditUser(int pos){
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_edit_user, null);
        dialog.setView(dialogView);
        final Dialog dialog_edit_user = dialog.show();

        final EditText edtUsername = dialogView.findViewById(R.id.edtUsername);
        final EditText edtPhone = dialogView.findViewById(R.id.edtPhone);
        final EditText edtFullName = dialogView.findViewById(R.id.edtFullName);
        Button btnSave = dialogView.findViewById(R.id.btnSave);
        Button btnCancel = dialogView.findViewById(R.id.btnCancel);

        edtUsername.setText(users.get(pos).USER_USER_NAME);
        edtPhone.setText(users.get(pos).USER_PHONE);
        edtFullName.setText(users.get(pos).USER_FULL_NAME);
        final String current_pass = users.get(pos).USER_PASSWORD;

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString().trim();
                String phone = edtPhone.getText().toString().trim();
                String full_name = edtFullName.getText().toString().trim();
                userDAO.updateUser(new User(username,current_pass,phone,full_name));
                dialog_edit_user.dismiss();
                addRecycleView();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_edit_user.dismiss();
            }
        });

    }

    private void onDeleteUser(final int pos){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("Are you sure to delete this user ?");
        dialog.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                userDAO.deleteUser(users.get(pos).USER_USER_NAME);
                addRecycleView();
            }
        });
        dialog.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case R.id.action_add_user:
                startNewActivity(AddUserActivity.class);
                finish();
                break;
            case R.id.action_change_pass:
                SharedPreferences sharedPreferences = getSharedPreferences("USER_POSITION",MODE_PRIVATE);
                int pos = sharedPreferences.getInt("POSITION", 0);
                Log.e("e",pos + "");
                onChangePassword(pos);
                break;
            case R.id.action_search:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onChangePassword(int pos){
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_change_password, null);
        dialog.setView(dialogView);
        final Dialog dialog_change_password = dialog.show();

        final EditText edtCurrentPass = dialogView.findViewById(R.id.edtCurrentPass);
        final EditText edtNewPass = dialogView.findViewById(R.id.edtNewPass);
        final EditText edtReNewPass = dialogView.findViewById(R.id.edtReNewPass);
        Button btnSave = dialogView.findViewById(R.id.btnSave);
        Button btnCancel = dialogView.findViewById(R.id.btnCancel);

        edtCurrentPass.setText(users.get(pos).USER_PASSWORD);
        final String username = users.get(pos).USER_USER_NAME;
        final String phone = users.get(pos).USER_PHONE;
        final String full_name = users.get(pos).USER_FULL_NAME;

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String new_pass = edtNewPass.getText().toString().trim();
                if(new_pass.matches("") || new_pass.length() < 5){
                    if(new_pass.length() < 5){
                        edtNewPass.setError(getString(R.string.notify_length_pass));
                        return;
                    }

                    if(new_pass.matches("")){
                        edtNewPass.setError(getString(R.string.notify_empty_pass));
                        return;
                    }
                }
                String re_new_pass = edtReNewPass.getText().toString().trim();
                if(!re_new_pass.equals(new_pass)){
                    edtReNewPass.setError("Password not match!");
                    return;
                }
                User user = new User(username,new_pass,phone,full_name);
                userDAO.updateUser(user);
                dialog_change_password.dismiss();
                addRecycleView();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_change_password.dismiss();
            }
        });

    }


}
