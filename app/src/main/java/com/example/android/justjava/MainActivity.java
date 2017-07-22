package com.example.android.justjava; /**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 * <p>
 * package com.example.android.justjava;
 */


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    protected int quantity = 1;
    protected int priceOfCoffee = 120;

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


    public void submitOrder(View view) {
        displayMessage();
    }

    /**
     * This method displays the given quantity value on the screen.
     */

    private int reedDisplay() {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        return Integer.parseInt((quantityTextView.getText()).toString());
    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(String.valueOf(number));
    }

    /**
     * This method displays the given price on the screen.
     */
    private String displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        return (NumberFormat.getCurrencyInstance().format(number));

    }

    private void displayMessage() {
        StringBuilder priceMessage = new StringBuilder();
        priceMessage.append("Name: Kunal Balbeskin");
        priceMessage.append("\nQuantity: " + quantity + " coffee");
        priceMessage.append("\nTotal: ");
        priceMessage.append(displayPrice(quantity * priceOfCoffee));
        priceMessage.append("\n------------------------------");
        priceMessage.append("\nThank YOU!");
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(priceMessage.toString());
    }
}