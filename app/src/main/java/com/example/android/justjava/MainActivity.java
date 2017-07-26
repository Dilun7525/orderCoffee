package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    /*Переменные: цены, количество по умолчанию*/
    protected int quantity = 1;
    protected int priceOfCoffee = 120;
    protected int toppingCream = 30;
    protected int toppingMarshmallow = 45;
    protected int toppingSyrop = 50;
    protected int toppingPriceTotal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void increment(View view) {
        //quantity = reedDisplay();
        display(++quantity);
    }

    public void decrement(View view) {
        if (quantity > 0) display(--quantity);
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(String.valueOf(number));
    }

    public void submitOrder(View view) {
        displayMessage();
    }

    protected String toppingMessage() {
        String toppings = "";
        toppingPriceTotal = 0;

        CheckBox checkBoxCrem = (CheckBox) findViewById(R.id.crem);
        if (checkBoxCrem.isChecked()) {
            toppings = toppings + "\nAdd Whipped cream: " + toppingCream + " руб.";
            toppingPriceTotal += toppingCream;
        }
        CheckBox checkBoxMarshmallow = (CheckBox) findViewById(R.id.marshmallow);
        if (checkBoxMarshmallow.isChecked()) {
            toppings = toppings + "\nAdd Marshmallow: " + toppingMarshmallow + " руб.";
            toppingPriceTotal += toppingMarshmallow;
        }
        CheckBox checkBoxSyrop = (CheckBox) findViewById(R.id.syrop);
        if (checkBoxSyrop.isChecked()) {
            toppings = toppings + "\nAdd Syrop: " + toppingSyrop + " руб.";
            toppingPriceTotal += toppingSyrop;
        }
        return toppings;
    }

    private void displayMessage() {
        String toppings = toppingMessage();
        int number = quantity * (priceOfCoffee + toppingPriceTotal);
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        String priceTotal = "";
        priceTotal = (NumberFormat.getCurrencyInstance().format(number));
        String priceMessage =
                "Name: Kunal Balbeskin" +
                        "\nQuantity: " + quantity + " coffee" +
                        toppings +
                        "\nTotal: " + priceTotal +
                        "\n------------------------------" +
                        "\nThank YOU!";
        priceTextView.setText(priceMessage);
    }
}