package com.fox.bookmanager.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fox.bookmanager.R;
import com.fox.bookmanager.model.Invoice;

import java.util.List;

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.InvoiceHolder>{

    public Context context;
    public List<Invoice> invoices;

    public InvoiceAdapter(Context context, List<Invoice> invoices) {
        this.context = context;
        this.invoices = invoices;
    }

    @NonNull
    @Override
    public InvoiceAdapter.InvoiceHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_invoice,viewGroup,false);
        return new InvoiceAdapter.InvoiceHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InvoiceAdapter.InvoiceHolder invoiceHolder, int i) {
        invoiceHolder.invoice = invoices.get(i);
        invoiceHolder.tvInvoiceId.setText(invoiceHolder.invoice.ID);
        invoiceHolder.tvInvoiceDate.setText(invoiceHolder.invoice.DATE);
    }


    @Override
    public int getItemCount() {
        return (invoices == null) ? 0 : invoices.size();
    }

    public class InvoiceHolder extends RecyclerView.ViewHolder{

        private TextView tvInvoiceId;
        private TextView tvInvoiceDate;
        Invoice invoice;

        public InvoiceHolder(@NonNull View itemView) {
            super(itemView);
            tvInvoiceId = itemView.findViewById(R.id.tvInvoiceId);
            tvInvoiceDate = itemView.findViewById(R.id.tvInvoiceDate);
        }
    }
}
