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
    private EditText etMobile;
    private EditText etExpiry;
    private EditText etCvv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);

        etCardHolderName = (EditText) findViewById(R.id.cardholder_name);
        etCardNumber = (EditText) findViewById(R.id.card_number);
        etMobile =(EditText)findViewById(R.id.mobile);
        etExpiry =(EditText)findViewById(R.id.expiry);
        etCvv =(EditText)findViewById(R.id.cvv);


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
            etCardNumber.setError("Card number mandatory");
        }
        if(etMobile.getText().toString().isEmpty()) {
            Toast.makeText(this, "Mobile number empty", Toast.LENGTH_SHORT).show();
            etMobile.setError("invalid mobile number");
        }
        if(etCvv.getText().toString().isEmpty()) {
            Toast.makeText(this, "CVV empty", Toast.LENGTH_SHORT).show();
            etCvv.setError("CVV must");
        }
        if(etExpiry.getText().toString().isEmpty()) {
            Toast.makeText(this, "Expire must", Toast.LENGTH_SHORT).show();
            etExpiry.setError("expiry must");
        }

    }
}
