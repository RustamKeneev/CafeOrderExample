package com.example.cafeorderexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class CreateOrderActivity extends AppCompatActivity {
    private TextView greeting_text_to_client_text_view;
    private TextView what_to_add_text_view;
    private CheckBox sugar_check_box;
    private CheckBox milk_check_box;
    private CheckBox lemon_check_box;
    private Spinner choose_the_type_drink_tea_spinner;
    private Spinner choose_the_type_drink_coffee_spinner;

    private String name;
    private String password;
    private String drink;
    private StringBuilder stringBuilderAddications;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);
        initAndBuildViews();
    }

    private void initAndBuildViews() {
        greeting_text_to_client_text_view = findViewById(R.id.greeting_text_to_client_text_view);
        what_to_add_text_view = findViewById(R.id.what_to_add_text_view);
        sugar_check_box = findViewById(R.id.sugar_check_box);
        milk_check_box = findViewById(R.id.milk_check_box);
        lemon_check_box = findViewById(R.id.lemon_check_box);
        choose_the_type_drink_tea_spinner = findViewById(R.id.choose_the_type_drink_tea_spinner);
        choose_the_type_drink_coffee_spinner = findViewById(R.id.choose_the_type_drink_coffee_spinner);

        stringBuilderAddications = new StringBuilder();

        String additions = String.format(getString(R.string.what_to_added_your_order_text ),drink);
        what_to_add_text_view.setText(additions);

        Intent intent = getIntent();
        if (intent.hasExtra("name")  && intent.hasExtra("password")) {
            name = intent.getStringExtra("name");
            password = intent.getStringExtra("password");
            drink = getString(R.string.tea);
        }else{
            name = getString(R.string.default_name);
            password = getString(R.string.default_password);
        }

        String hello = String.format(getString(R.string.greeting_text_to_client),name);
        greeting_text_to_client_text_view.setText(hello);
    }

    public void onClickChangeDrink(View view) {
        RadioButton radioButton = (RadioButton) view;
        int id = radioButton.getId();
        if (id == R.id.tea_radio_button){
             drink = getString(R.string.tea);
             choose_the_type_drink_tea_spinner.setVisibility(View.VISIBLE);
             choose_the_type_drink_coffee_spinner.setVisibility(View.INVISIBLE);
             lemon_check_box.setVisibility(View.VISIBLE);
        }else if (id == R.id.cafe_radio_button){
             drink = getString(R.string.coffee);
            choose_the_type_drink_tea_spinner.setVisibility(View.INVISIBLE);
            choose_the_type_drink_coffee_spinner.setVisibility(View.VISIBLE);
            lemon_check_box.setVisibility(View.INVISIBLE);
        }
    }

    public void onClickSendOrder(View view) {
        stringBuilderAddications.setLength(0);
        if (milk_check_box.isChecked()){
            stringBuilderAddications.append(getString(R.string.milk)).append(" ");
        }
        if (sugar_check_box.isChecked()){
            stringBuilderAddications.append(getString(R.string.sugar)).append(" ");
        }
        if (lemon_check_box.isChecked() && drink.equals(getString(R.string.tea))){
            stringBuilderAddications.append(getString(R.string.lemon)).append(" ");
        }

        String  optionOfDrink = "";
        if (drink.equals(getString(R.string.tea ))){
            optionOfDrink = choose_the_type_drink_tea_spinner.getSelectedItem().toString();
        }else {
            optionOfDrink = choose_the_type_drink_coffee_spinner.getSelectedItem().toString();
        }
        String order = String.format("Имя: %s\nПароль: %s\nнапиток: %s\nВид напитка: %s",name,password,drink,optionOfDrink);
        String additions;
        if (stringBuilderAddications.length() > 0 ){
            additions = " Необходимые добавки: " + stringBuilderAddications.toString();
        }else {
            additions = "";
        }
        String fullOrder = order + additions;
        Intent intent =  new Intent(this,OrderDetailActivity.class);
        intent.putExtra("fullOder",fullOrder);
        startActivity(intent);
    }
}
