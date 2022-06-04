package com.pervasivelab.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.charset.Charset;

public class MainActivity extends AppCompatActivity {
    int result = 0;
    int operationButtonResultShowing = 0;
    Operation lastOperation = Operation.none;
    int mathError = 0;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.calcText);
    }

    public void clickedOne(View view) {
        clickedDigit(1);
    }
    public void clickedTwo(View view) {
        clickedDigit(2);
    }
    public void clickedThree(View view) {
        clickedDigit(3);
    }
    public void clickedFour(View view) {
        clickedDigit(4);
    }
    public void clickedFive(View view) {
        clickedDigit(5);
    }
    public void clickedSix(View view) {
        clickedDigit(6);
    }
    public void clickedSeven(View view) {
        clickedDigit(7);
    }
    public void clickedEight(View view) {
        clickedDigit(8);
    }
    public void clickedNine(View view) {
        clickedDigit(9);
    }
    public void clickedZero(View view) {
        clickedDigit(0);
    }
    public void clickedPlus(View view) {
        processOperation(Operation.addition);
    }
    public void clickedMinus(View view) {
        processOperation(Operation.subtraction);
    }
    public void clickedMultiply(View view) {
        processOperation(Operation.multiplication);
    }
    public void clickedDivide(View view) {
        processOperation(Operation.division);
    }
    public void clickedEqual(View view) {
        processOperation(Operation.none);
        if(mathError == 1){
            mathError = 0;
            return;
        }
        result = 0;
        operationButtonResultShowing = 0;
        lastOperation = Operation.none;
    }
    public void processOperation(Operation operationType){
        if(operationButtonResultShowing == 1){
            lastOperation = operationType;
            return;
        }
        int lastNum = getNum();
        switch(lastOperation) {
            case none:
                result = lastNum;
                break;
            case addition:
                result += lastNum;
                break;
            case subtraction:
                result -= lastNum;
                break;
            case multiplication:
                result *= lastNum;
                break;
            case division:
                if(result != 0 && lastNum == 0){
                    Toast.makeText(getApplicationContext(),
                                    "Math error: division by zero",
                                    Toast.LENGTH_SHORT)
                            .show();
                    mathError = 1;


                    return;
                }
                result /= lastNum;
                break;
        }
        operationButtonResultShowing = 1;
        textView.setText(String.valueOf(result));
        lastOperation = operationType;
    }
    public void clickedClear(View view){
        clearDisplay();
        result = 0;
        operationButtonResultShowing = 0;
        lastOperation = Operation.none;
    }
    public void clickedDigit(int key){
        if(textView.getText().toString().equals("0") || textView.getText().toString().equals("00000000")){
            textView.setText("");
        }
        if(operationButtonResultShowing == 1){
            operationButtonResultShowing = 0;
            textView.setText("");
        }

        String newText = textView.getText().toString().concat(String.valueOf(key));
        textView.setText(newText);
    }
    public int getNum(){
        return Integer.parseInt(textView.getText().toString());
    }
    public void clearDisplay(){
        textView.setText("0");
    }
}

enum Operation {
    none,
    addition,
    subtraction,
    division,
    multiplication
}