package com.redheadhammer.cphelper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.Random;

public class XorCalculator extends AppCompatActivity {
    private Button submit;
    private RecyclerView recyclerView;

    private RadioButton radioButton;
    private RecyclerAdapter recyclerAdapter;
    private final ArrayList<String> values = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Bitwise Operations");
        setContentView(R.layout.activity_xor_calculator);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerAdapter = new RecyclerAdapter(values);
        recyclerView.setLayoutManager(new LinearLayoutManager(XorCalculator.this));
        recyclerView.setAdapter(recyclerAdapter);

        submit = findViewById(R.id.submit);
        submit.setOnClickListener(this::onClick);
    }

    public void onClick(View view) {
        int value1, value2;

        try {
            value1 = Integer.parseInt(((EditText) findViewById(R.id.value)).
                    getText().toString());
            value2 = Integer.parseInt(((EditText) findViewById(R.id.xorValue)).
                    getText().toString());
        } catch (IllegalArgumentException e) {
            Random random = new Random();
            value1 = random.nextInt(500);
            value2 = random.nextInt(100);

            ((EditText) this.findViewById(R.id.value)).setText(String.valueOf(value1));
            ((EditText) this.findViewById(R.id.xorValue)).setText(String.valueOf(value2));
        }

        final String binValue1 = Integer.toBinaryString(value1);
        final String binValue2 = Integer.toBinaryString(value2);

        int checkedId = ((RadioGroup)findViewById(R.id.radioGroup)).getCheckedRadioButtonId();
        radioButton = findViewById(checkedId);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(binValue1);
        if (radioButton.getText().toString().equals("XOR")) {
            stringBuilder.append(" ^ ").append(binValue2).append(" : ");
            stringBuilder.append(Integer.toBinaryString(value1 ^ value2)).append(" :");
            stringBuilder.append(value1^value2);
        }
        else if (radioButton.getText().toString().equals("AND")) {
            stringBuilder.append(" & ").append(binValue2).append(" : ");
            stringBuilder.append(Integer.toBinaryString(value1 & value2)).append(" :");
            stringBuilder.append(value1&value2);
        }
        else {
            stringBuilder.append(" | ").append(binValue2).append(" : ");
            stringBuilder.append(Integer.toBinaryString(value1 | value2)).append(" :");
            stringBuilder.append(value1|value2);
        }

        values.add(stringBuilder.toString());
        recyclerAdapter.notifyItemInserted(values.size()-1);
    }
}