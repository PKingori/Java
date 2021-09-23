package com.example.calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class Controller {
    @FXML
    private Text output;

    private String operator = "";
    private double num1 = 0;
    private boolean resetCalculator = true;

    // Update the output text when a number button is clicked
    @FXML
    private void handleNumberBtn(ActionEvent event){
        // Reset the calculator
        if (resetCalculator){
            resetCalculator = false;
            output.setText("");
        }

        // The output text cannot have more than one '.' symbol
        if (output.getText().contains(".") && ((Button)event.getSource()).getText().equals("."))
            return;

        // Update the output text in response to a clicked number button
        String outputText = output.getText() + ((Button)event.getSource()).getText();
        output.setText(outputText);
    }

    // Handle clicked operator buttons
    // A "=" operator must be used to perform each calculation
    @FXML
    private void handleOperatorBtn(ActionEvent event){
        String operatorBtn = ((Button)event.getSource()).getText();

        // Reset the calculator
        if (operatorBtn.equals("AC")){
            output.setText("");
            operator = "";
            num1 = 0;
        }
        // Calculate
        else if (operatorBtn.equals("=")){
            if (operator.isEmpty())
                return;
            output.setText(String.valueOf(calculate(num1, Double.parseDouble(output.getText()), operator)));
            operator = "";
            resetCalculator = true;

        }
        // Save the operator
        else{
            if (!operator.isEmpty())
                return;
            operator = operatorBtn;
            num1 = Double.parseDouble(output.getText());
            output.setText("");
        }
    }

    // Calculate function
    public double calculate(double num1, double num2, String operator){
        switch (operator){
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "x":
                return num1 * num2;
            case "%":
                return num1 % num2;
            case "÷":
                if (num2 == 0)
                    return 0;
                return num1 / num2;
        }
        return 0;
    }



}