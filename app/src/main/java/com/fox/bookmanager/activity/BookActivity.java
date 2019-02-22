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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.fox.bookmanager.R;
import com.fox.bookmanager.adapter.BookAdapter;
import com.fox.bookmanager.adapter.CategorySpinnerAdapter;
import com.fox.bookmanager.base.BaseActivity;
import com.fox.bookmanager.base.RecyclerViewClickListener;
import com.fox.bookmanager.base.RecyclerViewTouchListener;
import com.fox.bookmanager.dao.BookDAO;
import com.fox.bookmanager.dao.CategoryDAO;
import com.fox.bookmanager.database.DBHelper;
import com.fox.bookmanager.model.Book;
import com.fox.bookmanager.model.Category;

import java.util.ArrayList;
import java.util.List;

public class BookActivity extends BaseActivity {

    private BookDAO bookDAO;
    private DBHelper dbHelper;
    private RecyclerView lvList;
    private BookAdapter bookAdapter;
    private LinearLayoutManager linearLayoutManager;
    private List<Book> books;

    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        initViews();
        addRecycleView();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNewActivity(AddBookActivity.class);
            }
        });
    }

    private void initViews(){
        dbHelper = new DBHelper(BookActivity.this);
        books = new ArrayList<>();
        books.clear();
        bookDAO = new BookDAO(BookActivity.this);
        lvList = findViewById(R.id.lvList);
        linearLayoutManager = new LinearLayoutManager(BookActivity.this);
        fab = findViewById(R.id.fab);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void addRecycleView(){
        books = bookDAO.getAllBook();
        bookAdapter = new BookAdapter(this,books);

        lvList.setLayoutManager(linearLayoutManager);
        lvList.setHasFixedSize(true);
        lvList.setAdapter(bookAdapter);

        lvList.addOnItemTouchListener(new RecyclerViewTouchListener(getApplicationContext(), lvList, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int pos) {
            }

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
                                onEditBook(pos);
                                break;
                            case R.id.menu_delete:
                                popup.dismiss();
                                onDeleteBook(pos);
                                break;
                        }
                        return true;
                    }
                });
                popup.show();
            }
        }));
    }

    private void onEditBook(int pos){
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_edit_book, null);
        dialog.setView(dialogView);
        final Dialog dialog_edit_book = dialog.show();

        final EditText edtBookId = dialogView.findViewById(R.id.edtBookId);
        final Spinner spCatId = dialogView.findViewById(R.id.spCatId);
        ImageButton iBtnAdd = dialogView.findViewById(R.id.i_btnAdd);
        final EditText edtBookName = dialogView.findViewById(R.id.edtBookName);
        final EditText edtAuthor = dialogView.findViewById(R.id.edtAuthor);
        final EditText edtProducer = dialogView.findViewById(R.id.edtProducer);
        final EditText edtPrice = dialogView.findViewById(R.id.edtPrice);
        final EditText edtQuantity = dialogView.findViewById(R.id.edtQuantity);
        Button btnSave = dialogView.findViewById(R.id.btnSave);
        Button btnCancel = dialogView.findViewById(R.id.btnCancel);

        List<Category> categories = new CategoryDAO(BookActivity.this).getAllCategory();
        spCatId.setAdapter(new CategorySpinnerAdapter(BookActivity.this,categories));
        edtBookId.setText(books.get(pos).ID);
        edtBookName.setText(books.get(pos).NAME);
        edtAuthor.setText(books.get(pos).AUTHOR);
        edtProducer.setText(books.get(pos).PRODUCER);
        edtPrice.setText(String.valueOf(books.get(pos).PRICE));
        edtQuantity.setText(String.valueOf(books.get(pos).QUANTITY));

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edtBookId.getText().toString().trim();
                String name = edtBookName.getText().toString().trim();
                String author = edtAuthor.getText().toString().trim();
                String producer = edtProducer.getText().toString().trim();
                float price = Float.parseFloat(edtPrice.getText().toString().trim());
                int quantity = Integer.parseInt(edtQuantity.getText().toString().trim());
                String cat_id = String.valueOf(spCatId.getSelectedItemId());
                Book book = new Book(id,cat_id,name,author,producer,price,quantity);
                bookDAO.updateBook(book);
                dialog_edit_book.dismiss();
                addRecycleView();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_edit_book.dismiss();
            }
        });

    }

    private void onDeleteBook(final int pos){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("Are you sure to delete this book ?");
        dialog.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                bookDAO.deleteBook(books.get(pos).ID);
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
