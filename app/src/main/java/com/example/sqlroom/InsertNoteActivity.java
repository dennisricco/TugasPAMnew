package com.example.sqlroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.Manifest;
public class InsertNoteActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tvEmail;
    private TextView tvUid;
    private Button btnKeluar;
    private FirebaseAuth mAuth;
    private Button btnnext;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    Note note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_note);
        tvEmail = findViewById(R.id.tv_email);
        tvUid = findViewById(R.id.tv_uid);
        btnKeluar = findViewById(R.id.btn_keluar);
        mAuth = FirebaseAuth.getInstance();
        btnKeluar.setOnClickListener(this);
        btnnext = findViewById(R.id.btn_next);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        note = new Note();
        btnnext.setOnClickListener(this);
    }
    @Override
    protected void onStart() {
        super.onStart();
// Check if user is signed in (non-null) and updateUI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null){
            tvEmail.setText(currentUser.getEmail());
            tvUid.setText(currentUser.getUid());
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_keluar:
                logOut();
                break;
            case R.id.btn_next:
                nextPage();
                break;
        }
    }

    private void nextPage() {
        Intent intent = new Intent(this,ContactActivity.class);
        startActivity(intent);
    }

    public void logOut(){
        mAuth.signOut();
        Intent intent = new Intent(InsertNoteActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);//makesure usercant go back
        startActivity(intent);
    }
}

