package com.fox.bookmanager.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.fox.bookmanager.R;
import com.fox.bookmanager.adapter.InvoiceAdapter;
import com.fox.bookmanager.base.BaseActivity;
import com.fox.bookmanager.base.RecyclerViewClickListener;
import com.fox.bookmanager.base.RecyclerViewTouchListener;
import com.fox.bookmanager.dao.InvoiceDAO;
import com.fox.bookmanager.model.Invoice;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class InvoiceActivity extends BaseActivity implements View.OnClickListener{

    private InvoiceDAO invoiceDAO;
    private RecyclerView lvList;
    private InvoiceAdapter invoiceAdapter;
    private LinearLayoutManager linearLayoutManager;
    private List<Invoice> invoices;

    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);
        initViews();
        addRecycleView();
        fab.setOnClickListener(this);
    }

    private void initViews(){
        invoices = new ArrayList<>();
        invoices.clear();
        invoiceDAO = new InvoiceDAO(InvoiceActivity.this);
        lvList = findViewById(R.id.lvList);
        linearLayoutManager = new LinearLayoutManager(InvoiceActivity.this);
        fab = findViewById(R.id.fab);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void addRecycleView(){
        invoices = invoiceDAO.getAllInvoice();
        invoiceAdapter = new InvoiceAdapter(InvoiceActivity.this,invoices);

        lvList.setLayoutManager(linearLayoutManager);
        lvList.setHasFixedSize(true);
        lvList.setAdapter(invoiceAdapter);

        lvList.addOnItemTouchListener(new RecyclerViewTouchListener(getApplicationContext(), lvList, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int pos) {
                Intent intent = new Intent(InvoiceActivity.this,InvoiceDetailActivity.class);
                intent.putExtra("pos",pos);
                startActivity(intent);
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
                                onEditInvoice(pos);
                                break;
                            case R.id.menu_delete:
                                popup.dismiss();
                                onDeleteInvoice(pos);
                                break;
                        }
                        return true;
                    }
                });
                popup.show();
            }
        }));
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

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.fab){
            onAddInvoice();
        }
    }

    private void onAddInvoice(){
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_add_invoice, null);
        dialog.setView(dialogView);
        final Dialog dialog_add_invoice = dialog.show();

        final EditText edtName = dialogView.findViewById(R.id.edtName);
        final TextView tvDate = dialogView.findViewById(R.id.tvDate);
        final Button btnDate = dialogView.findViewById(R.id.btnDate);
        Button btnAdd = dialogView.findViewById(R.id.btnAdd);
        Button btnCancel = dialogView.findViewById(R.id.btnCancel);

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                final int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(InvoiceActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                        Calendar calendar = Calendar.getInstance();
//                        calendar.set(year,month,dayOfMonth);
//                        long startTime = calendar.getTimeInMillis();
                        if(month < 10){
                            tvDate.setText(year + "/" + "0" + (month + 1) + "/" +dayOfMonth);
                        }else{
                            tvDate.setText(year + "/" + (month + 1) + "/" +dayOfMonth);
                        }

                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String i_id = edtName.getText().toString().trim();
                if(i_id.matches("") || i_id.length() > 7){
                    edtName.setError(getString(R.string.notify_wrong_text));
                    return;
                }
                String i_date = tvDate.getText().toString().trim();
                if(i_date.equals("")){
                    showMessage(getString(R.string.notify_choose_input));
                    return;
                }

                Invoice invoice = new Invoice(i_id,i_date);
                long result = invoiceDAO.insertInvoice(invoice);

                if(result > 0){
                    showMessage(getString(R.string.notify_input_successful));
                }else{
                    showMessage(getString(R.string.notify_error));
                }
                dialog_add_invoice.dismiss();
                addRecycleView();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_add_invoice.dismiss();
                addRecycleView();
            }
        });

    }

    private void onEditInvoice(int pos){
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_add_invoice, null);
        dialog.setView(dialogView);
        final Dialog dialog_add_invoice = dialog.show();

        final EditText edtName = dialogView.findViewById(R.id.edtName);
        final TextView tvDate = dialogView.findViewById(R.id.tvDate);
        final Button btnDate = dialogView.findViewById(R.id.btnDate);
        Button btnAdd = dialogView.findViewById(R.id.btnAdd);
        Button btnCancel = dialogView.findViewById(R.id.btnCancel);

        btnAdd.setText(getString(R.string.btn_action_save));
        edtName.setText(invoices.get(pos).ID);
        edtName.setEnabled(false);
        tvDate.setText(invoices.get(pos).DATE);

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                final int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(InvoiceActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year,month,dayOfMonth);
                        long startTime = calendar.getTimeInMillis();
                        tvDate.setText(new Date(startTime).toString());
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String i_id = edtName.getText().toString().trim();
                if(i_id.matches("") || i_id.length() > 7){
                    edtName.setError(getString(R.string.notify_wrong_text));
                    return;
                }
                String i_date = tvDate.getText().toString().trim();
                if(i_date.equals("")){
                    showMessage(getString(R.string.notify_choose_input));
                    return;
                }

                Invoice invoice = new Invoice(i_id,i_date);
                long result = invoiceDAO.updateInvoice(invoice);

                if(result > 0){
                    showMessage(getString(R.string.notify_input_successful));
                }else{
                    showMessage(getString(R.string.notify_error));
                }
                dialog_add_invoice.dismiss();
                addRecycleView();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_add_invoice.dismiss();
                addRecycleView();
            }
        });
    }

    private void onDeleteInvoice(final int pos){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("Are you sure to delete this invoice ?");
        dialog.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                invoiceDAO.deleteInvoice(invoices.get(pos).ID);
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

}
