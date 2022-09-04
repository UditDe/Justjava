/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.justjava;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    String toppings = "No Toppings are added";
    int quantity = 0;
    int basePrice = 5;
    String content = "";
    public void onClickSwitchBox(View view) {
        boolean checked = ((CheckBox)view).isChecked();

        // Figure out if the user wants whipped cream topping
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whippedCream);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        // Figure out if the user wants chocolate topping
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        if(hasWhippedCream && hasChocolate) {
            toppings = "Whipped cream and Chocolate added";
            basePrice = 8;
        }
        else if(hasWhippedCream) {
            toppings = "Whipped Cream Added";
            basePrice = 6;
        }
        else if(hasChocolate) {
            toppings = "Chocolate Added";
            basePrice = 7;
        }
        else {
            toppings = "No Toppings are added";
            basePrice = 5;
        }
    }

    public void Increase(View view) {
        if(quantity < 40)
            quantity++;
        if(quantity >= 40)
            Toast.makeText(MainActivity.this,"You have reached to the maximum limit",Toast.LENGTH_SHORT).show();
        display(quantity);
    }
    public void Decrease(View view) {
        if(quantity > 0)
            quantity--;
        if(quantity <= 0)
            Toast.makeText(MainActivity.this,"You have reached to the minimum limit",Toast.LENGTH_SHORT).show();
        display(quantity);
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText customer = (EditText) findViewById(R.id.cumtomerName);
        String myCustomer = customer.getText().toString();
        createOrderSummery(myCustomer, calculatePrice(quantity), quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * This method calculate price of coffee
     */
    private int calculatePrice(int x) {
        return quantity * basePrice;
    }

    /*
    This Method creates the order summery.
     */
    private void createOrderSummery(String message, int x, int y) {
        TextView priceTextView = (TextView) findViewById(R.id.orderSummery_text_view);
        content = "Name :" + message + "\n"+ toppings + "\nquantity :" + y + "\n" + "Total :" + x + "\nThank You!!!";
        priceTextView.setText(content);
    }
    /*
    This Method Sends the Order to Store
     */

    public void sendOrder(View view) {
        EditText customer = (EditText) findViewById(R.id.cumtomerName);
        String myCustomer = customer.getText().toString();
        composeEmail(new String[]{"imondey.com@gmail.com"}, myCustomer);
    }
    public void composeEmail(String[] addresses, String name) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, content);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}