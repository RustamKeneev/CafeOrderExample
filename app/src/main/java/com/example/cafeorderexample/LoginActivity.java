package com.example.cafeorderexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private ImageView cafe_imageView;
    private TextView textView;
    private EditText person_name_editText;
    private EditText person_password_editText;
    private Button order_cafe_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initAndBuildViews();
    }

    private void initAndBuildViews() {
        cafe_imageView = findViewById(R.id.cafe_imageView);
        textView = findViewById(R.id.textView);
        person_name_editText = findViewById(R.id.person_name_editText);
        person_password_editText = findViewById(R.id.person_password_editText);
        order_cafe_button = findViewById(R.id.order_cafe_button);
    }

    public void clickCreatOrder(View view) {
        Intent intent = new Intent(this,CreateOrderActivity.class);
        String name = person_name_editText.getText().toString().trim();
        String password = person_password_editText.getText().toString().trim();
        if (!name.isEmpty() && !password.isEmpty()) {
            intent.putExtra("name",name);
            intent.putExtra("password",password);
            startActivity(intent);
        }else {
            Toast.makeText(this,R.string.All_fields_must_be_filled, Toast.LENGTH_LONG).show();
        }
    }
}
