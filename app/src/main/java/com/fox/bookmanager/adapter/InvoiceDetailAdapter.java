package com.fox.bookmanager.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fox.bookmanager.R;
import com.fox.bookmanager.dao.BookDAO;
import com.fox.bookmanager.model.InvoiceDetail;

import java.util.List;

public class InvoiceDetailAdapter extends RecyclerView.Adapter<InvoiceDetailAdapter.InvoiceDetailHolder> {

    public Context context;
    public List<InvoiceDetail> invoiceDetails;

    public InvoiceDetailAdapter(Context context, List<InvoiceDetail> invoiceDetails) {
        this.context = context;
        this.invoiceDetails = invoiceDetails;
    }

    @NonNull
    @Override
    public InvoiceDetailAdapter.InvoiceDetailHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_invoice_detail,viewGroup,false);
        return new InvoiceDetailAdapter.InvoiceDetailHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InvoiceDetailAdapter.InvoiceDetailHolder invoiceDetailHolder, int i) {
        invoiceDetailHolder.invoiceDetail = invoiceDetails.get(i);
        invoiceDetailHolder.tvInvoiceDetailBookId.setText(invoiceDetailHolder.invoiceDetail.BOOK_ID);
        float price = new BookDAO(context).getBookByID(invoiceDetailHolder.invoiceDetail.BOOK_ID).PRICE;
        invoiceDetailHolder.tvInvoiceDetailQuantity.setText(String.valueOf(invoiceDetailHolder.invoiceDetail.QUANTITY));
        invoiceDetailHolder.tvInvoiceDetailBookPrice.setText(String.valueOf(price));
        float sum = price * invoiceDetailHolder.invoiceDetail.QUANTITY;
        invoiceDetailHolder.tvInvoiceDetailSum.setText(String.valueOf(sum));
    }


    @Override
    public int getItemCount() {
        return (invoiceDetails == null) ? 0 : invoiceDetails.size();
    }

    public class InvoiceDetailHolder extends RecyclerView.ViewHolder{

        private TextView tvInvoiceDetailBookId;
        private TextView tvInvoiceDetailQuantity;
        private TextView tvInvoiceDetailBookPrice;
        private TextView tvInvoiceDetailSum;
        InvoiceDetail invoiceDetail;

        public InvoiceDetailHolder(@NonNull View itemView) {
            super(itemView);
            tvInvoiceDetailBookId = itemView.findViewById(R.id.tvInvoiceDetailBookId);
            tvInvoiceDetailQuantity = itemView.findViewById(R.id.tvInvoiceDetailQuantity);
            tvInvoiceDetailBookPrice = itemView.findViewById(R.id.tvInvoiceDetailBookPrice);
            tvInvoiceDetailSum = itemView.findViewById(R.id.tvInvoiceDetailSum);
        }
    }

}
