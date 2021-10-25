package com.example.lab3_calc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button buttonMinus;
    private Button buttonPlus;
    private Button buttonMultiply;
    private Button buttonDivide;
    private Button buttonRoot;
    private Button buttonSeven;
    private Button buttonEight;
    private Button buttonNine;
    private Button buttonEqual;
    private Button buttonFour;
    private Button buttonFive;
    private Button buttonSix;
    private Button buttonZero;
    private Button buttonOne;
    private Button buttonTwo;
    private Button buttonThree;
    private Button buttonDelete;
    private Button buttonChangeToMinus;
    private Button buttonChangeToPlus;

    private TextView textViewScreen;

    public String PrevInput;
    public String CurrentOperation;
    private boolean clearNeeded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonMinus = (Button) findViewById(R.id.buttonMinus);
        buttonPlus = (Button) findViewById(R.id.buttonPlus);
        buttonMultiply = (Button) findViewById(R.id.buttonMultiply);
        buttonDivide = (Button) findViewById(R.id.buttonDivide);
        buttonRoot = (Button) findViewById(R.id.buttonRoot);
        buttonSeven = (Button) findViewById(R.id.buttonSeven);
        buttonEight = (Button) findViewById(R.id.buttonEight);
        buttonNine = (Button) findViewById(R.id.buttonNine);
        buttonEqual = (Button) findViewById(R.id.buttonEqual);
        buttonFour = (Button) findViewById(R.id.buttonFour);
        buttonFive = (Button) findViewById(R.id.buttonFive);
        buttonSix = (Button) findViewById(R.id.buttonSix);
        buttonZero = (Button) findViewById(R.id.buttonZero);
        buttonOne = (Button) findViewById(R.id.buttonOne);
        buttonTwo = (Button) findViewById(R.id.buttonTwo);
        buttonThree = (Button) findViewById(R.id.buttonThree);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);
        buttonChangeToMinus = (Button) findViewById(R.id.buttonChangeToMinus);
        buttonChangeToPlus = (Button) findViewById(R.id.buttonChangeToPlus);

        buttonMinus.setOnClickListener(this);
        buttonPlus.setOnClickListener(this);
        buttonMultiply.setOnClickListener(this);
        buttonDivide.setOnClickListener(this);
        buttonRoot.setOnClickListener(this);
        buttonSeven.setOnClickListener(this);
        buttonEight.setOnClickListener(this);
        buttonNine.setOnClickListener(this);
        buttonEqual.setOnClickListener(this);
        buttonFive.setOnClickListener(this);
        buttonFour.setOnClickListener(this);
        buttonSix.setOnClickListener(this);
        buttonZero.setOnClickListener(this);
        buttonOne.setOnClickListener(this);
        buttonTwo.setOnClickListener(this);
        buttonThree.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
        buttonChangeToMinus.setOnClickListener(this);
        buttonChangeToPlus.setOnClickListener(this);

        textViewScreen = (TextView) findViewById(R.id.textView);
        textViewScreen.setText("");
        PrevInput = "";
        CurrentOperation = "";
        clearNeeded = false;
    }
    @Override
    public void onClick(View v) {
        if(clearNeeded){
            clearNeeded = false;
            ClearTextView();
        }
        switch(v.getId()) {
            case R.id.buttonOne:
                AddAtTheEndOfTextView("1");
                break;
            case R.id.buttonTwo:
                AddAtTheEndOfTextView("2");
                break;
            case R.id.buttonThree:
                AddAtTheEndOfTextView("3");
                break;
            case R.id.buttonFour:
                AddAtTheEndOfTextView("4");
                break;
            case R.id.buttonFive:
                AddAtTheEndOfTextView("5");
                break;
            case R.id.buttonSix:
                AddAtTheEndOfTextView("6");
                break;
            case R.id.buttonSeven:
                AddAtTheEndOfTextView("7");
                break;
            case R.id.buttonEight:
                AddAtTheEndOfTextView("8");
                break;
            case R.id.buttonNine:
                AddAtTheEndOfTextView("9");
                break;
            case R.id.buttonZero:
                AddAtTheEndOfTextView("0");
                break;
            case R.id.buttonChangeToMinus:
                AddAtTheBegginingOfTextView("-");
                break;
            case R.id.buttonChangeToPlus:
                AddAtTheBegginingOfTextView("+");
                break;
            case R.id.buttonDelete:
                Clear();
                break;

            case R.id.buttonMinus:
                SpecialOperations("-");
                break;
            case R.id.buttonPlus:
                SpecialOperations("+");
                break;
            case R.id.buttonMultiply:
                SpecialOperations("*");
                break;
            case R.id.buttonDivide:
                SpecialOperations("/");
                break;
            case R.id.buttonRoot:
                String rootResult = RootOperation(textViewScreen.getText().toString());
                Clear();
                AddAtTheEndOfTextView(rootResult);
                break;
            case R.id.buttonEqual:
                if(!CurrentOperation.equals("")) {
                    String result = Calculate(PrevInput, textViewScreen.getText().toString(), CurrentOperation);
                    Clear();
                    AddAtTheEndOfTextView(result);
                }
                break;

        }

    }
    public void AddAtTheEndOfTextView(String input){
        String current = textViewScreen.getText().toString();
        current+=input;
        textViewScreen.setText(current);
    }
    public void AddAtTheBegginingOfTextView(String input){
        if(input.equals("-")){
            input += textViewScreen.getText().toString();
            textViewScreen.setText(input);
        }
        else{
            String screen = textViewScreen.getText().toString();
            screen = screen.replaceAll("[-]", "");
            textViewScreen.setText(screen);
        }
    }
    public void Clear(){
        ClearTextView();
        PrevInput = "";
        CurrentOperation = "";
    }
    public String RootOperation(String input){
        int inputA = Integer.parseInt(input);
        return Double.toString(Math.sqrt(inputA));
    }
    public void ClearTextView(){
        textViewScreen.setText("");
    }
    public void SpecialOperations(String input){
            boolean didCalculate = PreprocessOperation();
            PrevInput = textViewScreen.getText().toString();
            if(didCalculate) {
                clearNeeded = true;
            }
            else{
                ClearTextView();
            }
            CurrentOperation = input;
    }

    public boolean PreprocessOperation(){
        if(!CurrentOperation.equals("")){
            String result = Calculate(PrevInput, textViewScreen.getText().toString(), CurrentOperation);
            ClearTextView();
            AddAtTheEndOfTextView(result);
            return true;
        }
        return false;
    }
    public String Calculate(String input1, String input2, String operation){
        String result = "";
        double inputA;
        double inputB;
        if(!input1.equals("")) {
            inputA= Double.parseDouble(input1);
        }
        else{
            inputA = 0;
        }
        if(!input2.equals("")) {
            inputB= Double.parseDouble(input2);
        }
        else{
            inputB = 0;
        }
        switch (operation){
            case "-":
                result =  Double.toString(inputA-inputB);
                break;
            case "+":
                result = Double.toString(inputA+inputB);
                break;
            case "/":
                result = Double.toString(inputA/inputB);
                break;
            case "*":
                result = Double.toString(inputA*inputB);
                break;
        }
        return result;
    }


}