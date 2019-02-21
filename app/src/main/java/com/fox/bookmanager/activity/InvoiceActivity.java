package com.fox.bookmanager.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.fox.bookmanager.R;
import com.fox.bookmanager.base.BaseActivity;
import com.fox.bookmanager.dao.InvoiceDAO;
import com.fox.bookmanager.model.Invoice;

import java.util.Calendar;
import java.util.Date;

public class InvoiceActivity extends BaseActivity {

    private EditText edtName;
    private Button btnDate;
    private Button btnAdd;

    private InvoiceDAO invoiceDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);
        initViews();

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

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
                        btnDate.setText(year + " - " + month + " - " + day);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date = new Date();
                String i_id = edtName.getText().toString().trim();
                if(i_id.matches("") || i_id.length() > 7){
                    edtName.setError(getString(R.string.notify_wrong_text));
                    return;
                }
                String i_date = btnDate.getText().toString().trim();
                if(i_date.equals(getString(R.string.btn_invoice_date))){
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
            }
        });

    }

    private void initViews(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        edtName = findViewById(R.id.edtName);
        btnDate = findViewById(R.id.btnDate);
        btnAdd = findViewById(R.id.btnAdd);
        invoiceDAO = new InvoiceDAO(InvoiceActivity.this);
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
