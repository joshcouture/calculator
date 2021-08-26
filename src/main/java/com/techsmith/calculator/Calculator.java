package com.techsmith.calculator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculator extends Application {

    private Pane rootPane;

    private Label activeTextArea;
    private Label operandOneTextArea;
    private Label operandTwoTextArea;
    private Label operatorTextArea;
    private Label answer;
    private Label equals;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        rootPane = new Pane();

        addInputButtons();
        addTextAreas();

        Button calculate = createButton("=", 120, 90);
        calculate.setOnAction(actionEvent -> calculate());

        Button clear = createButton("Clear", 150, 90);
        clear.setOnAction(actionEvent -> clear());

        Scene scene = new Scene(rootPane, 500, 500);
        stage.setTitle("Calculator!");
        stage.setScene(scene);
        stage.show();
    }

    private void clear() {
        activeTextArea = operandOneTextArea;
        operandOneTextArea.setText("");
        operandTwoTextArea.setText("");
        operatorTextArea.setText("");
        answer.setText("");
        equals.setText("");
    }

    private void calculate() {
        BigDecimal arg1 = new BigDecimal(operandOneTextArea.getText());
        BigDecimal arg2 = new BigDecimal(operandTwoTextArea.getText());

        equals.setText("=");
        switch (operatorTextArea.getText()) {
            case "*":
                answer.setText(arg1.multiply(arg2).toString());
                break;
            case "/":
                answer.setText(arg1.divide(arg2, 10, RoundingMode.FLOOR).toString());
                break;
            case "-":
                answer.setText(arg1.subtract(arg2).toString());
                break;
            case "+":
                answer.setText(arg1.add(arg2).toString());
                break;
            default:
                break;
        }
    }

    private void addTextAreas() {
        operandOneTextArea = createLabel(5, 120, 10, 60);
        activeTextArea = operandOneTextArea;
        operatorTextArea = createLabel(5, 135, 10, 60);
        operandTwoTextArea = createLabel(5, 150, 10, 60);
        equals = createLabel(5, 165, 10, 60);
        answer = createLabel(5, 180, 10, 600);
    }

    private Label createLabel(double x, double y, double height, double width) {
        Label label = new Label();
        label.setLayoutX(x);
        label.setLayoutY(y);
        label.setMaxHeight(height);
        label.setMinWidth(width);
        label.setMaxWidth(width);
        rootPane.getChildren().add(label);
        return label;
    }

    private void addInputButtons() {
        addNumberButton("1", 5, 60);
        addNumberButton("2", 35, 60);
        addNumberButton("3", 65, 60);
        addNumberButton("4", 5, 30);
        addNumberButton("5", 35, 30);
        addNumberButton("6", 65, 30);
        addNumberButton("7", 5, 0);
        addNumberButton("8", 35, 0);
        addNumberButton("9", 65, 0);
        addNumberButton("0", 35, 90);
        addNumberButton(".", 65, 90);

        addOperatorButton("+", 95, 60);
        addOperatorButton("-", 95, 90);
        addOperatorButton("/", 95, 30);
        addOperatorButton("*", 95, 0);
    }

    private Button createButton(String label, double x, double y) {
        Button button = new Button(label);
        button.setLayoutX(x);
        button.setLayoutY(y);
        rootPane.getChildren().add(button);
        return button;
    }

    private void addNumberButton(String label, double x, double y) {
        Button button = createButton(label, x, y);
        button.setOnAction(actionEvent -> {
            activeTextArea.setText(activeTextArea.getText() + label);
        });
    }

    private void addOperatorButton(String label, double x, double y) {
        Button button = createButton(label, x, y);
        button.setOnAction(actionEvent -> {
            operatorTextArea.setText(label);
            activeTextArea = operandTwoTextArea;
        });
    }
}