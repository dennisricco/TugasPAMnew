package com.example.sqlroom;

import static android.content.ContentValues.TAG;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.Manifest;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etEmail;
    private EditText etPass;
    private Button btnMasuk;
    private Button btnDaftar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etEmail = (EditText)findViewById(R.id.et_email);
        etPass = (EditText)findViewById(R.id.et_pass);
        btnMasuk = (Button) findViewById(R.id.btn_masuk);
        btnDaftar = (Button)findViewById(R.id.btn_daftar);
        mAuth = FirebaseAuth.getInstance();
        btnMasuk.setOnClickListener(this);
        btnDaftar.setOnClickListener(this);
    }
    @Override
    protected void onStart() {
        super.onStart();
// Check if user is signed in (non-null) and updateUI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_masuk:
                login(etEmail.getText().toString(),
                        etPass.getText().toString());
                break;
            case R.id.btn_daftar:
                signUp(etEmail.getText().toString(),
                        etPass.getText().toString());
                break;
        }
    }
    public void signUp(String email,String password){
        if (!validateForm()){
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void
                            onComplete(@NonNull Task<AuthResult> task) {
                                if
                                (task.isSuccessful()) {
// Sign in success, update UI with the signed-inuser's information
                                    Log.d(TAG,"createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    updateUI(user);
                                    Toast.makeText(LoginActivity.this, user.toString(), Toast.LENGTH_SHORT).show();
                                }
                                else {
// If sign in fails, display a messageto the user.
                                    Log.w(TAG,"createUserWithEmail:failure", task.getException());
                                    Toast.makeText(LoginActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                                    updateUI(null);
                                }
                            }
                        });
    }
    public void login(String email,String password){
        if (!validateForm()){
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void
                            onComplete(@NonNull Task<AuthResult>task) {
                                if
                                (task.isSuccessful()) {
// Sign in success, update UI with the signed-inuser's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user =mAuth.getCurrentUser();
                                    Toast.makeText(LoginActivity.this, user.toString(), Toast.LENGTH_SHORT).show();
                                    updateUI(user);
                                }
                                else {
// If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                    updateUI(null);
                                }
                            }
                        });
    }
    private boolean validateForm() {
        boolean result = true;
        if
        (TextUtils.isEmpty(etEmail.getText().toString())) {
            etEmail.setError("Required");
            result = false;
        } else {
            etEmail.setError(null);
        }
        if
        (TextUtils.isEmpty(etPass.getText().toString())) {
            etPass.setError("Required");
            result = false;
        } else {
            etPass.setError(null);
        }
        return result;
    }
    public void updateUI(FirebaseUser user) {
        if (user != null) {
            Intent intent = new
                    Intent(LoginActivity.this,
                    InsertNoteActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(LoginActivity.this,"Log In First",
                    Toast.LENGTH_SHORT).show();
        }
    }
}