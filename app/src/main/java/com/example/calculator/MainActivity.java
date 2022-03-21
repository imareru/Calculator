package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    TextView resultField; // текстовое поле для вывода результата
    EditText numberField;   // поле для ввода числа
    Double operand = null;  // операнд операции
    String lastOperation = "="; // последняя операция
    StringBuilder operation = new StringBuilder(lastOperation);
//    File history_file = new File("src/main/java/com/example/calculator/history_file.txt");
//    FileWriter add_calculation = new FileWriter(history_file, true);

//    public MainActivity() throws IOException {
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // получаем все поля по id из activity_main.xml
        resultField = findViewById(R.id.resultField);
        numberField = findViewById(R.id.numberField);
    }

    //Сохранение состояния
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("OPERATION", String.valueOf(operation));
        if(operand!=null)
            outState.putDouble("OPERAND", operand);
        super.onSaveInstanceState(outState);
    }

    // получение ранее сохраненного состояния
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        lastOperation = savedInstanceState.getString("OPERATION");
        operand= savedInstanceState.getDouble("OPERAND");
        resultField.setText(operand.toString());
    }

    // обработка нажатия на числовую кнопку
    public void onNumberClick(View view) throws IOException {
        Button button = (Button)view;
        numberField.append(button.getText());
        resultField.append(button.getText());
        //add_calculation.append(button.getText());
        if(lastOperation.equals("=") && operand!=null){
            operand = null;
        }
    }

    // обработка нажатия на кнопку операции
    public void onOperationClick(View view) throws IOException {
        Button button = (Button)view;
        String op = button.getText().toString();
        String number = numberField.getText().toString();
        // если введенно что-нибудь
        if(number.length()>0){
            number = number.replace(',', '.');
            try{
                performOperation(Double.valueOf(number), op);
            }catch (NumberFormatException | IOException ex){
                numberField.setText("");
            }
        }
        lastOperation = op;
        if(!lastOperation.equals("=")&&!lastOperation.equals("HIS")&&!lastOperation.equals("C")) {
            resultField.append(lastOperation);
            //add_calculation.append(lastOperation);
        }
        if (lastOperation.equals("C")){
            resultField.setText("");
            numberField.setText("");
        }
    }

    public void onHistoryClick(View view) throws FileNotFoundException {

/*
        CustomDialogFragment dialog = new CustomDialogFragment();
        dialog.show(getSupportFragmentManager(), "custom");*/
    }

    private void performOperation(Double number, String operation) throws IOException {

        // если операнд ранее не был установлен (при вводе самой первой операции)
        if(operand ==null){
            operand = number;
        }
        else{
            if(lastOperation.equals("=")){
                lastOperation = operation;
            }
            switch(lastOperation){
                case "=":
                    operand =number;
                    break;
                case "/":
                    if(number==0){
                        operand =0.0;
                    }
                    else{
                        operand /=number;
                    }
                    break;
                case "*":
                    operand *=number;
                    break;
                case "+":
                    operand +=number;
                    break;
                case "-":
                    operand -=number;
                    break;
                case "%":
                    operand *= (operand/100)*number;
                case "√":
                    operand = Math.pow(operand, 1/number);
                case "x^2":
                    operand = Math.pow(operand,2);
            }
        }
        resultField.setText(operand.toString());
        numberField.setText("");

        //add_calculation.append("=" + operand.toString());
        //if(lastOperation.equals("=")){

        //}
    }

}