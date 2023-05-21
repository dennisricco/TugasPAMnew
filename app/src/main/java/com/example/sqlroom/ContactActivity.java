package com.example.sqlroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.example.sqlroom.ContactDatabase;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity{
        //Init var
        private ArrayList<Contact> contactList = new ArrayList<>();
        private ContactAdapter contactAdapter;
        private RecyclerView recyclerView;
        private TextView option;
        private LinearLayout layAddContact;
        private EditText etName, etNumber, etInstagram, etGroup;
        private Button btnClear, btnSubmit;
        private ContactDatabase contactDatabase;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_contact);
            recyclerView = findViewById(R.id.recycle_contact);
            recyclerView.setHasFixedSize(true);
            layAddContact = findViewById(R.id.layout_add);
            option = findViewById(R.id.tv_option);
            etName = findViewById(R.id.et_name);
            etNumber = findViewById(R.id.et_number);
            etInstagram = findViewById(R.id.et_instagram);
            etGroup = findViewById(R.id.et_group);
            btnClear = findViewById(R.id.btn_clear);
            btnSubmit = findViewById(R.id.btn_submit);
            contactDatabase =
                    ContactDatabase.getInstance(getApplicationContext());
            option.setOnClickListener(v -> {
                if (recyclerView.getVisibility() == View.VISIBLE){
                    recyclerView.setVisibility(View.GONE);
                    layAddContact.setVisibility(View.VISIBLE);
                    clearData();
                }else {
                    recyclerView.setVisibility(View.VISIBLE);
                    layAddContact.setVisibility(View.GONE);
                }
            });
            btnClear.setOnClickListener(v -> {
                clearData();
            });
            btnSubmit.setOnClickListener(v -> {
                if (etName.getText().toString().equals("") ||
                        etNumber.getText().toString().equals("") ||
                        etInstagram.getText().toString().equals("") ||
                        etGroup.getText().toString().equals("") ){
                    Toast.makeText(this, "Please fill in the entire form", Toast.LENGTH_SHORT).show();
                } else {
                    contactDatabase.ContactDAO().insert(new Contact(etName.getText().toString(),
                            etNumber.getText().toString(),
                            etGroup.getText().toString(),
                            etInstagram.getText().toString()));
                    setRecyclerView();
                    recyclerView.setVisibility(View.VISIBLE);
                    layAddContact.setVisibility(View.GONE);
                    clearData();
                }
            });
            setRecyclerView();
        }
        public void clearData(){
            etName.setText("");
            etNumber.setText("");
            etInstagram.setText("");
            etGroup.setText("");
        }
        public void setRecyclerView(){
            contactAdapter = new ContactAdapter(contactDatabase.ContactDAO().getAll());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(contactAdapter);
        }
}

