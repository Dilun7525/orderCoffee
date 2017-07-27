package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

/*This app displays an order form to order coffee. */
public class MainActivity extends AppCompatActivity {
    //region variables
    /*Переменные: цены, количество по умолчанию*/
    protected int quantity = 1;
    protected int priceOfCoffee = 120;
    protected int toppingCream = 30;
    protected int toppingMarshmallow = 45;
    protected int toppingSyrop = 50;
    protected int toppingPriceTotal = 0;

    //endregion
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //region View display quantity
    /*Показ количества заказа*/
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(String.valueOf(number));
    }

    /*Добавление и убавление количества кофе*/
    public void increment(View view) {
        if (quantity < 50)
        display(++quantity);
    }

    public void decrement(View view) {

        if (quantity > 0)
            display(--quantity);
    }

    //endregion

    //region Show Order
    /*Получение имени клиента*/
    protected String nameConsomer (){
        EditText editText = (EditText) findViewById(R.id.imput_name);
       return String.valueOf(editText.getText());
    }

    /*Формирование информации о добавленных топингах*/
    protected String toppingMessage() {
        String toppings = "";
        toppingPriceTotal = 0;
        int priseTotal;

        CheckBox checkBoxCrem = (CheckBox) findViewById(R.id.crem);
        if (checkBoxCrem.isChecked()) {
            priseTotal = toppingCream * quantity;
            toppings = toppings + "\nWhipped cream: \t" +
                    toppingCream + " руб. x " + quantity + " = " +
                    priseTotal + " руб.";
            toppingPriceTotal += priseTotal;
        }
        CheckBox checkBoxMarshmallow = (CheckBox) findViewById(R.id.marshmallow);
        if (checkBoxMarshmallow.isChecked()) {
            priseTotal = toppingMarshmallow * quantity;
            toppings = toppings + "\nMarshmallow: \t\t\t" +
                    toppingMarshmallow + " руб. x " + quantity + " = " +
                    priseTotal + " руб.";
            toppingPriceTotal += priseTotal;
        }
        CheckBox checkBoxSyrop = (CheckBox) findViewById(R.id.syrop);
        if (checkBoxSyrop.isChecked()) {
            priseTotal = toppingSyrop * quantity;
            toppings = toppings + "\nSyrop: \t\t\t\t\t\t\t\t\t" +
                    toppingSyrop + " руб. x " + quantity + " = " +
                    priseTotal + " руб.";
            toppingPriceTotal += priseTotal;
        }
        return toppings;
    }

    /*Вывод чека */
    private void displayMessage() {
        String toppings = toppingMessage();
        int number = quantity * priceOfCoffee + toppingPriceTotal;
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        String priceTotal;
        priceTotal = (NumberFormat.getCurrencyInstance().format(number));
        String priceMessage =
                "Name: " + nameConsomer() +
                        "\nCoffee: \t\t\t\t\t\t\t" + priceOfCoffee +
                        " руб. x " + quantity + " = " +
                        (priceOfCoffee * quantity) + " руб." +
                        toppings +
                        "\nTotal: \t\t\t\t\t\t\t\t\t\t" + priceTotal +
                        "\n------------------------------" +
                        "\nThank YOU!";
        priceTextView.setText(priceMessage);
    }

    /*Вывод информации по чеку в view*/
    public void submitOrder(View view) {
        displayMessage();
    }
    //endregion
}