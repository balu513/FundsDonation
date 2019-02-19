package com.example.fundsdonation.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fundsdonation.R;

public class PaymentActivity extends AppCompatActivity {

    private EditText etCardHolderName;
    private EditText etCardNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pament);

        etCardHolderName = (EditText) findViewById(R.id.cardholder_name);
        etCardNumber = (EditText) findViewById(R.id.card_number);


        ((Button)findViewById(R.id.pay_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
                Toast.makeText(PaymentActivity.this,"Payment Successful",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void validateData() {

        if(etCardHolderName.getText().toString().isEmpty()) {
            Toast.makeText(this, "Card holder name empty", Toast.LENGTH_SHORT).show();
            etCardHolderName.setError("invalid name");
        }
        if(etCardNumber.getText().toString().isEmpty()) {
            Toast.makeText(this, "Card holder name empty", Toast.LENGTH_SHORT).show();
            etCardNumber.setError("invalid card number");
        }

    }
}
