package com.example.truyentieuthuyet;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText edtTaiKhoan, edtMatKhau;
    Button btnLogin;
    CheckBox chkCheck;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        init();
        sharedPreferences = getSharedPreferences("Remember",MODE_PRIVATE);
        edtTaiKhoan.setText(sharedPreferences.getString("username",""));
        edtMatKhau.setText(sharedPreferences.getString("password",""));
        chkCheck.setChecked(sharedPreferences.getBoolean("remember",false));
    }
    private void init() {
        edtTaiKhoan = findViewById(R.id.edtTaiKhoan);
        edtMatKhau = findViewById(R.id.edtMatKhau);
        btnLogin = findViewById(R.id.btnLogin);
        chkCheck=findViewById(R.id.chkCheck);
    }





    public void Login(View view) {


        if (edtTaiKhoan.getText().toString().length() == 0 || edtMatKhau.getText().toString().length() == 0) {
            Toast.makeText(getApplicationContext(), "User và Password không được để trống", Toast.LENGTH_LONG).show();
            return;
        }
        if (edtTaiKhoan.getText().toString().equals("admin") && edtMatKhau.getText().toString().equals("admin")) {
            if (chkCheck.isChecked()){
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("username",edtTaiKhoan.getText().toString());
                editor.putString("password",edtMatKhau.getText().toString());
                editor.putBoolean("remember",true);
                editor.commit();
            }else {
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.remove("username");
                editor.remove("password");
                editor.remove("remember");
                editor.commit();
            }
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getApplicationContext(), "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
        }
    }}

