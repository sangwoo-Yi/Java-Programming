package ch9;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {

    private JTextField display;
    private JLabel history;
    private JPanel panel;
    private JButton button;
    private double operand1, operand2, answer;
    private String operator;

    public Calculator() {
        this.setBounds(100, 100, 300, 500);
        this.setTitle("계산기");
        this.setLayout(new BorderLayout());

        display = new JTextField(30);
        display.setText("0");
        display.setFont(new Font("궁서체", Font.BOLD, 35));
        display.setHorizontalAlignment(SwingConstants.RIGHT);

        this.add(display, BorderLayout.NORTH);

        history = new JLabel("--");
        this.add(history, BorderLayout.SOUTH);

        panel = new JPanel();
        panel.setLayout(new GridLayout(6, 4));

        addButton("%", e -> {
            double d = Double.parseDouble(display.getText());
            display.setText(Double.toString(d / 100));
        });
        addButton("CE", e -> display.setText("0"));
        addButton("C", e -> display.setText("0"));
        addButton("<-", e -> {
            String currentText = display.getText();
            if (currentText.length() > 0) {
                display.setText(currentText.substring(0, currentText.length() - 1));
            }
        });
        addButton("1/x", e -> {
            double d = Double.parseDouble(display.getText());
            display.setText(Double.toString(1.0 / d));
        });
        addButton("x^2", e -> {
            double d = Double.parseDouble(display.getText());
            display.setText(Double.toString(d * d));
        });
        addButton("Sqrt", e -> {
            double d = Double.parseDouble(display.getText());
            display.setText(Double.toString(Math.sqrt(d)));
        });
        addButton("/", e -> {
            operand1 = Double.parseDouble(display.getText());
            display.setText("0");
            operator = "/";
        });
        addButton("7", e -> appendToDisplay("7"));
        addButton("8", e -> appendToDisplay("8"));
        addButton("9", e -> appendToDisplay("9"));
        addButton("*", e -> {
            operand1 = Double.parseDouble(display.getText());
            display.setText("0");
            operator = "*";
        });
        addButton("4", e -> appendToDisplay("4"));
        addButton("5", e -> appendToDisplay("5"));
        addButton("6", e -> appendToDisplay("6"));
        addButton("-", e -> {
            operand1 = Double.parseDouble(display.getText());
            display.setText("0");
            operator = "-";
        });
        addButton("1", e -> appendToDisplay("1"));
        addButton("2", e -> appendToDisplay("2"));
        addButton("3", e -> appendToDisplay("3"));
        addButton("+", e -> {
            operand1 = Double.parseDouble(display.getText());
            display.setText("0");
            operator = "+";
        });
        addButton("+/-", e -> {
            String currentText = display.getText();
            if (!currentText.equals("0")) {
                if (currentText.startsWith("-")) {
                    display.setText(currentText.substring(1));
                } else {
                    display.setText("-" + currentText);
                }
            }
        });
        addButton("0", e -> appendToDisplay("0"));
        addButton(".", e -> {
            String currentText = display.getText();
            if (!currentText.contains(".")) {
                display.setText(currentText + ".");
            }
        });
        addButton("=", e -> {
            operand2 = Double.parseDouble(display.getText());
            switch (operator) {
                case "+":
                    answer = operand1 + operand2;
                    break;
                case "-":
                    answer = operand1 - operand2;
                    break;
                case "*":
                    answer = operand1 * operand2;
                    break;
                case "/":
                    answer = operand1 / operand2;
                    break;
                default:
                    break;
            }
            display.setText(Double.toString(answer));
        });

        this.add(panel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    private void addButton(String label, ActionListener listener) {
        button = new JButton(label);
        button.addActionListener(listener);
        panel.add(button);
    }

    private void appendToDisplay(String text) {
        if (!display.getText().equals("0")) {
            display.setText(display.getText() + text);
        } else {
            display.setText(text);
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
