package com.redheadhammer.cphelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Select One item");
        setContentView(R.layout.activity_main);

        findViewById(R.id.createArray).setOnClickListener(
                this::CreateArray);
        findViewById(R.id.xorCalculator).setOnClickListener(
                this::XorCalculator);
        findViewById(R.id.BinaryForm).setOnClickListener(
                this::BinaryRepresentation);
        findViewById(R.id.gcdCalculator).setOnClickListener(
                this::GcdCalculator);
    }

    public void CreateArray(View view) {
        Intent array = new Intent(this, RandomArray.class);
        this.startActivity(array);
    }

    public void XorCalculator(View view) {
    }

    public void BinaryRepresentation(View view) {
    }

    public void GcdCalculator(View view) {
    }
}