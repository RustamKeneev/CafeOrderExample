package com.example.cafeorderexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OrderDetailActivity extends AppCompatActivity {
    private TextView order_detail_text_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        order_detail_text_view = findViewById(R.id.order_detail_text_view);

        Intent intent = getIntent();
        if (intent.hasExtra("fullOder")){
            String orderText = intent.getStringExtra("fullOder");
            order_detail_text_view.setText(orderText );
        }else {
            Intent backToLoginActivity = new Intent(this,LoginActivity.class);
            startActivity(backToLoginActivity);
            finish();
        }

    }
}
