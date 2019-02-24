package com.fox.bookmanager.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.fox.bookmanager.R;
import com.fox.bookmanager.adapter.BookSpinnerAdapter;
import com.fox.bookmanager.adapter.InvoiceDetailAdapter;
import com.fox.bookmanager.dao.BookDAO;
import com.fox.bookmanager.dao.InvoiceDAO;
import com.fox.bookmanager.dao.InvoiceDetailDAO;
import com.fox.bookmanager.model.Book;
import com.fox.bookmanager.model.Invoice;
import com.fox.bookmanager.model.InvoiceDetail;

import java.util.ArrayList;
import java.util.List;

public class InvoiceDetailActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tvTotal;
    private RecyclerView lvList;
    private InvoiceDAO invoiceDAO;
    private InvoiceDetailDAO invoiceDetailDAO;
    private BookDAO bookDAO;
    private InvoiceDetailAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private List<InvoiceDetail> invoiceDetails;
    private int pos;

    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_detail);
        initViews();
        Intent intent = getIntent();
        pos = intent.getIntExtra("pos",0);
        addRecycleView();
        fab.setOnClickListener(this);
    }

    private void initViews(){
        invoiceDetails = new ArrayList<>();
        tvTotal = findViewById(R.id.tvTotal);
        lvList = findViewById(R.id.lvList);
        fab = findViewById(R.id.fab);
        linearLayoutManager = new LinearLayoutManager(InvoiceDetailActivity.this);
        invoiceDAO = new InvoiceDAO(InvoiceDetailActivity.this);
        invoiceDetailDAO = new InvoiceDetailDAO(InvoiceDetailActivity.this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void addRecycleView(){
        invoiceDetails = invoiceDetailDAO.getAllInvoiceDetailByInvoiceID(invoiceDAO.getAllInvoice().get(pos).ID);
        Log.e("e",invoiceDetails + "");
        adapter = new InvoiceDetailAdapter(this,invoiceDetails);

        lvList.setLayoutManager(linearLayoutManager);
        lvList.setHasFixedSize(true);
        lvList.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.fab){
            onAddInvoiceDetail();
        }
    }

    private void onAddInvoiceDetail(){
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        final LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_add_invoice_detail, null);
        dialog.setView(dialogView);
        final Dialog dialog_add_invoice_detail = dialog.show();

        final Spinner spBook = dialogView.findViewById(R.id.spBook);
        final EditText edtQuantity = dialogView.findViewById(R.id.edtQuantity);
        Button btnAdd = dialogView.findViewById(R.id.btnAdd);
        Button btnCancel = dialogView.findViewById(R.id.btnCancel);

        bookDAO = new BookDAO(InvoiceDetailActivity.this);
        List<Book> books = bookDAO.getAllBook();
        spBook.setAdapter(new BookSpinnerAdapter(InvoiceDetailActivity.this,books));

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InvoiceDetail invoiceDetail = new InvoiceDetail();
                invoiceDetail.BOOK_ID = ((Book)spBook.getSelectedItem()).ID;
                String i_id = invoiceDAO.getAllInvoice().get(pos).ID;
                invoiceDetail.INVOICE_ID = i_id;
                String quantity = edtQuantity.getText().toString().trim();
                if(edtQuantity.getText().toString().trim().matches("")){
                    edtQuantity.setError("Quantity can not be empty");
                    return;
                }
                invoiceDetail.QUANTITY = Integer.parseInt(quantity);

                invoiceDetailDAO.insertInvoiceDetail(invoiceDetail);
                dialog_add_invoice_detail.dismiss();
                addRecycleView();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_add_invoice_detail.dismiss();
            }
        });

    }

}
