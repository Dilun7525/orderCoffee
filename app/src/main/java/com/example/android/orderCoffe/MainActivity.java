package com.example.android.jastjava;

import android.content.Intent;
import android.net.Uri;
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
    //E-MAIL
    protected String sEmail = "qwert@darc.com";
    protected String sEmailTema = getString(R.string.j_buying_coffee);

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
    protected String nameConsomer() {
        EditText editText = (EditText) findViewById(R.id.imput_name);
        return String.valueOf(editText.getText());
    }

    /*Формирование информации о добавленных топингах*/
    protected String toppingMessage() {
        String toppings = "";
        toppingPriceTotal = 0;
        int priseTotal;

        //Whipped cream
        CheckBox checkBoxCrem = (CheckBox) findViewById(R.id.crem);
        if (checkBoxCrem.isChecked()) {
            priseTotal = toppingCream * quantity;
            toppings = toppings + getString(R.string.j_whipped_cream) +
                    toppingCream + getString(R.string.j_currency) + " x " + quantity + " = " +
                    priseTotal + getString(R.string.j_currency);
            toppingPriceTotal += priseTotal;
        }
        //Marshmallow
        CheckBox checkBoxMarshmallow = (CheckBox) findViewById(R.id.marshmallow);
        if (checkBoxMarshmallow.isChecked()) {
            priseTotal = toppingMarshmallow * quantity;
            toppings = toppings + "\nMarshmallow: \t\t" +
                    toppingMarshmallow + " руб. x " + quantity + " = " +
                    priseTotal + " руб.";
            toppingPriceTotal += priseTotal;
        }
        //Syrop
        CheckBox checkBoxSyrop = (CheckBox) findViewById(R.id.syrop);
        if (checkBoxSyrop.isChecked()) {
            priseTotal = toppingSyrop * quantity;
            toppings = toppings + "\nSyrop: \t\t\t\t\t\t\t\t" +
                    toppingSyrop + " руб. x " + quantity + " = " +
                    priseTotal + " руб.";
            toppingPriceTotal += priseTotal;
        }
        return toppings;
    }

    /*Вывод чека */
    private String displayMessage() {
        String toppings = toppingMessage();
        int number = quantity * priceOfCoffee + toppingPriceTotal;
        String priceTotal;
        priceTotal = (NumberFormat.getCurrencyInstance().format(number));
        String priceMessage;
        priceMessage = "Name: " + nameConsomer() +
                "\nCoffee: \t\t\t\t\t" + priceOfCoffee +
                " руб. x " + quantity + " = " +
                (priceOfCoffee * quantity) + " руб." +
                toppings +
                "\nTotal: \t\t\t\t\t\t\t\t" + priceTotal +
                "\n------------------------------" +
                "\nThank YOU!";
        return priceMessage;
    }

    /*Вывод информации по чеку в view*/
    public void submitOrder(View view) {
        String[] adr = {sEmail};            // Адрес email
        String temaEmail = sEmailTema;      // Тема email
        String textMail = displayMessage(); // Сообщение в email
        composeEmail(adr, temaEmail, textMail);
    }

    //endregion

    /*Отправка заказа по email*/
    public void composeEmail(String[] addresses, String subject, String textEmail) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, textEmail);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}