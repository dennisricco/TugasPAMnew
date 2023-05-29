package com.example.sqlroom;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.Manifest;
import java.util.List;
import java.util.*;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private Context context;
    private List<Contact> contactList;
    private ContactDatabase contactDatabase;
    public ContactAdapter(List<Contact> contactList){
        this.contactList = contactList;
    }
    @NonNull
    @Override

    public ContactViewHolder
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new ContactViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        final Contact contact = contactList.get(position);
        holder.tvName.setText(contact.getName());
        holder.tvNumber.setText(contact.getNumber());
        holder.tvMessage.setText(contact.getGroup());
        holder.tvWhatsapp.setText(contact.getInstagram());
    }
    @Override
    public int getItemCount() {
        return contactList.size();
    }
    public class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        LinearLayout contactLayout;
        TextView tvName, tvNumber, tvCall, tvMessage, tvWhatsapp, tvDelete;
        public ContactViewHolder(@NonNull View itemView)
        {
            super(itemView);
            contactDatabase = ContactDatabase.getInstance(itemView.getContext());
            contactLayout = itemView.findViewById(R.id.contact_layout);
            tvDelete = itemView.findViewById(R.id.tv_delete);
            tvName = itemView.findViewById(R.id.tv_name);
            tvNumber = itemView.findViewById(R.id.tv_number);
            tvCall = itemView.findViewById(R.id.tv_call);
            tvMessage = itemView.findViewById(R.id.tv_message);
            tvWhatsapp = itemView.findViewById(R.id.tv_whatsapp);
            tvDelete.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            contactDatabase.ContactDAO().delete(contactList.get(getAdapterPosition()));
            contactList.remove(getAdapterPosition());
            notifyItemRemoved(getAdapterPosition());
            notifyItemRangeChanged(getAdapterPosition(),
                    contactList.size());
        }
    }
}
