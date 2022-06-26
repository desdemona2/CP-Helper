package com.redheadhammer.cphelper;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class RandomArray extends AppCompatActivity {

    private TextView arrayView;
    private CheckBox sorted, reversed;
    private Button submit;

    private int arraySize, start, end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_array);

        arrayView = findViewById(R.id.arrayView);

        sorted = findViewById(R.id.sorted);
        reversed = findViewById(R.id.reverse);
        submit = findViewById(R.id.submit);

        reversed.setOnCheckedChangeListener(this::reversedCheck);
        submit.setOnClickListener(this::submitButton);
    }

    public void reversedCheck(CompoundButton buttonView, boolean isChecked) {
        /* if reverse is checked than automatically check sorted. */
        if (isChecked) sorted.setChecked(true);
        Toast.makeText(this, getString(R.string.reversed_array_toast),
                Toast.LENGTH_SHORT).show();
    }

    public void submitButton(View view) {
        /* check if arguments passed are valid otherwise use default values for arguments */
        try {
            arraySize = Integer.parseInt(((TextView) findViewById(R.id.totalValues)).
                    getText().toString());

            // if arraySize is above 100 set array size to be 100 max
            if (arraySize > 100) {
                arraySize = 100;
                Toast.makeText(this, "Max value for ArraySize is 100",
                        Toast.LENGTH_SHORT).show();
            }
        } catch (IllegalArgumentException e) {
            arraySize = 10;
            Toast.makeText(this, R.string.illegal_values_use_default,
                    Toast.LENGTH_SHORT).show();
        }

        try {
            start = Integer.parseInt(((TextView) findViewById(R.id.rangeStart)).
                    getText().toString());
            end = Integer.parseInt(((TextView) findViewById(R.id.rangeEnd)).
                    getText().toString());

            if (start >= end) throw new IllegalArgumentException();
        } catch (IllegalArgumentException e) {
            start = 0;
            end = 10000;
            Toast.makeText(this, R.string.illegal_values_use_default,
                    Toast.LENGTH_SHORT).show();
        }
        arrayView.setText(RandomArrayGenerator());
        hideKeyboardFrom(getBaseContext(), arrayView);
    }

    private String RandomArrayGenerator() {
        //generate array of size arraySize within range start-end
        int[] randomIntsArray = IntStream.generate(() -> new Random().
                                      nextInt(end-start) + start).limit(arraySize).toArray();
        // sort the array if selected
        if (sorted.isChecked())
            Arrays.sort(randomIntsArray);

        // reverse the array if selected
        if (reversed.isChecked())
            ArrayReverse(randomIntsArray);

        return Arrays.toString(randomIntsArray);
    }

    private void ArrayReverse(int[] array) {
        int size = array.length;
        for (int i = 0; i < size/2; i++) {
            int tmp = array[i];
            array[i] = array[size-i-1];
            array[size-i-1] = tmp;
        }
    }

    // static method to hide the keyboard
    private static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.
                                    getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}