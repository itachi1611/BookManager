package com.fox.bookmanager.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;

import com.fox.bookmanager.R;
import com.fox.bookmanager.adapter.CategoryAdapter;
import com.fox.bookmanager.base.BaseActivity;
import com.fox.bookmanager.base.RecyclerViewClickListener;
import com.fox.bookmanager.base.RecyclerViewTouchListener;
import com.fox.bookmanager.dao.CategoryDAO;
import com.fox.bookmanager.database.DBHelper;
import com.fox.bookmanager.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends BaseActivity implements View.OnClickListener{

    private CategoryDAO categoryDAO;
    private DBHelper dbHelper;
    private RecyclerView lvList;
    private CategoryAdapter categoryAdapter;
    private LinearLayoutManager linearLayoutManager;
    private List<Category> categories;

    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        initViews();
        addRecycleView();

        fab.setOnClickListener(this);
    }

    private void initViews(){
        dbHelper = new DBHelper(CategoryActivity.this);
        categories = new ArrayList<>();
        categories.clear();
        categoryDAO = new CategoryDAO(CategoryActivity.this);
        lvList = findViewById(R.id.lvList);
        linearLayoutManager = new LinearLayoutManager(CategoryActivity.this);
        fab = findViewById(R.id.fab);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void addRecycleView(){
        categories = categoryDAO.getAllCategory();
        categoryAdapter = new CategoryAdapter(this,categories);

        lvList.setLayoutManager(linearLayoutManager);
        lvList.setHasFixedSize(true);
        lvList.setAdapter(categoryAdapter);

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
                                onEditCategory(pos);
                                break;
                            case R.id.menu_delete:
                                popup.dismiss();
                                onDeleteCategory(pos);
                                break;
                        }
                        return true;
                    }
                });
                popup.show();
            }
        }));
    }

    private void onEditCategory(int pos){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_edit_category, null);
        dialog.setView(dialogView);
        final Dialog dialog_edit_category = dialog.show();

        final EditText edtCategoryId = dialogView.findViewById(R.id.edtCategoryId);
        final EditText edtCategoryName = dialogView.findViewById(R.id.edtCategoryName);
        final EditText edtCategoryPosition = dialogView.findViewById(R.id.edtCategoryPosition);
        final EditText edtCategoryDescription = dialogView.findViewById(R.id.edtCategoryDescription);
        Button btnSave = dialogView.findViewById(R.id.btnSave);
        Button btnCancel = dialogView.findViewById(R.id.btnCancel);

        edtCategoryId.setText(categories.get(pos).ID);
        edtCategoryName.setText(categories.get(pos).NAME);
        edtCategoryPosition.setText(categories.get(pos).POSITION);
        edtCategoryDescription.setText(categories.get(pos).DESCRIPTION);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edtCategoryId.getText().toString().trim();
                String name = edtCategoryName.getText().toString().trim();
                if(name.matches("")){
                    edtCategoryName.setError("Name of category can not be empty!");
                    return;
                }
                String position = edtCategoryPosition.getText().toString().trim();
                String description = edtCategoryDescription.getText().toString().trim();
                Category category = new Category(id,name,position,description);
                categoryDAO.updateCategory(category);
                dialog_edit_category.dismiss();
                addRecycleView();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_edit_category.dismiss();
            }
        });
    }

    private void onDeleteCategory(final int pos){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("Are you sure to delete this category ?");
        dialog.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                categoryDAO.deleteCategory(categories.get(pos).ID);
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
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.fab){
            startNewActivity(AddCategoryActivity.class);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
