package com.example.android.justjava4;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.justjava4.R;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increment(View view){
        quantity++;
        display(quantity);
    }

    public void decrement(View view){
        quantity--;
        display(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText nameField = (EditText) findViewById(R.id.name_field);
        String value = nameField.getText().toString();

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckbox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckbox.isChecked();

        int price = calculationPrice(hasWhippedCream, hasChocolate);
        String priceMessage = createOrderSummary(value, price, hasWhippedCream, hasChocolate);
        displayMessage(priceMessage);
    }

    private int calculationPrice(boolean addWhippedCream, boolean addChocolate){
        int basePrice = 5;
        if(addWhippedCream){
            basePrice += 1;
        }
        if(addChocolate){
            basePrice += 2;
        }
        int price = quantity * basePrice;
        return price;
    }

    private String createOrderSummary(String nameField, int price, boolean addWhippedCream, boolean addChocolate){
        String priceMessage = "Name : " + nameField;
        priceMessage += "\nAdd whipped cream?" + addWhippedCream;
        priceMessage += "\nAdd chocolate?" + addChocolate;
        priceMessage += "\nQuantity : " + quantity;
        priceMessage += "\nTotal : $" + price;
        priceMessage += "\nThank You!";
        return priceMessage;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displPrice(int number){
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    private void displayMessage(String message){
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }
}