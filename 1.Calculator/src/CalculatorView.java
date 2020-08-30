import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorView {

    public static final String[][] BUTTON_TEXTS = {
            {"7", "8", "9", "+"},
            {"4", "5", "6", "-"},
            {"1", "2", "3", "*"},
            {"C", "0", "=", "/"}
    };

    public static final Font BUTTON_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 25);
    private static final JButton[][] buttons = new JButton[BUTTON_TEXTS.length][BUTTON_TEXTS[0].length];
    private static String operandText = null;
    private static String previousOperandText = null;
    private static String currentOperandText = null;
    private static double cummulated = 0;

    public static void createAndShowGui() {

        JTextField inputField = new JTextField(15);
        inputField.setFont(BUTTON_FONT);

        JPanel buttonPanel = new JPanel(new GridLayout(BUTTON_TEXTS.length, BUTTON_TEXTS[0].length));

        for (int i = 0; i < BUTTON_TEXTS.length; i++) {
            for (int j = 0; j < BUTTON_TEXTS[0].length; j++) {
                buttons[i][j] = new JButton(BUTTON_TEXTS[i][j]);
                buttons[i][j].setFont(BUTTON_FONT);
                buttonPanel.add(buttons[i][j]);
            }
        }

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(inputField, BorderLayout.PAGE_START);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        JFrame mainFrame = new JFrame("Calculator");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.getContentPane().add(mainPanel);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);

        ////adding functionality to the calculator

        for (int i = 0; i < BUTTON_TEXTS.length; i++) {
            buttons[i][3].setEnabled(false);
        }

        ResultModel result = new ResultModel();

        //CLEAR
        buttons[3][0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < BUTTON_TEXTS.length; i++) {
                    buttons[i][3].setEnabled(true);
                }
                result.setPreviousOperation(5);
                operandText = null;
                inputField.setText("");
                cummulated = 0;
                result.setResult(0);
                for (int i = 0; i < BUTTON_TEXTS.length; i++) {
                    buttons[i][3].setEnabled(false);
                }
            }
        });

        //ADDITION
        buttons[0][3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                result.setPreviousOperation(1);
                previousOperandText = operandText;
                inputField.setText("+");
                operandText = null;
            }
        });

        //SUBSTRACTION
        buttons[1][3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                result.setPreviousOperation(2);
                previousOperandText = operandText;
                inputField.setText("-");
                operandText = null;
            }
        });

        //MULTIPLICATION
        buttons[2][3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                result.setPreviousOperation(3);
                previousOperandText = operandText;
                inputField.setText("*");
                operandText = null;
            }
        });

        //DIVISION
        buttons[3][3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                result.setPreviousOperation(4);
                previousOperandText = operandText;
                inputField.setText("/");
                operandText = null;
            }
        });

        //DISPLAY RESULT
        buttons[3][2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttons[3][2].setEnabled(false);
                double previousToDouble;
                if (cummulated == 0) {
                    previousToDouble = Double.parseDouble(previousOperandText);
                } else {
                    previousToDouble = cummulated;
                }
                double currentToDouble = Double.parseDouble(currentOperandText);
                switch (result.getPreviousOperation()) {
                    case 1:
                        result.setResult(previousToDouble + currentToDouble);
                        inputField.setText(String.valueOf(result.getResult()));
                        previousOperandText = String.valueOf(result.getResult());
                        cummulated = result.getResult();
                        break;
                    case 2:
                        result.setResult(previousToDouble - currentToDouble);
                        inputField.setText(String.valueOf(result.getResult()));
                        cummulated = result.getResult();
                        break;
                    case 3:
                        result.setResult(previousToDouble * currentToDouble);
                        inputField.setText(String.valueOf(result.getResult()));
                        cummulated = result.getResult();
                        break;
                    case 4:
                        result.setResult(previousToDouble / currentToDouble);
                        inputField.setText(String.valueOf(result.getResult()));
                        cummulated = result.getResult();
                        break;
                    case 5:
                        result.setResult(previousToDouble);
                        inputField.setText(String.valueOf(result.getResult()));
                        cummulated = result.getResult();
                        break;
                }
            }
        });

        //DIGITS
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (result.getPreviousOperation() == 5) {
                    int finalI = i;
                    int finalJ = j;
                    buttons[i][j].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            for (int i = 0; i < BUTTON_TEXTS.length; i++) {
                                buttons[i][3].setEnabled(true);
                            }
                            buttons[3][2].setEnabled(true);
                            String buttonText = buttons[finalI][finalJ].getText();
                            if (operandText == null) {
                                operandText = buttonText;
                            } else {
                                operandText += buttonText;
                            }
                            inputField.setText(String.valueOf(operandText));
                            int operandToInt = Integer.parseInt(String.valueOf(operandText));
                            result.setResult(operandToInt);
                            currentOperandText = operandText;
                        }
                    });
                } else {
                    buttons[3][2].setEnabled(true);
                    buttons[i][3].setEnabled(true);
                    int finalI = i;
                    int finalJ = j;
                    buttons[i][j].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            for (int i = 0; i < BUTTON_TEXTS.length; i++) {
                                buttons[i][3].setEnabled(true);
                            }
                            String buttonText = buttons[finalI][finalJ].getText();
                            operandText += buttonText;

                            inputField.setText(String.valueOf(operandText));
                            currentOperandText = operandText;
                        }
                    });
                }
            }
        }
        buttons[3][1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttons[3][2].setEnabled(true);
                String buttonText = buttons[3][1].getText();
                if (operandText == null) {
                    operandText = buttonText;
                } else {
                    operandText += buttonText;
                }
                inputField.setText(String.valueOf(operandText));
                int operandToInt = Integer.parseInt(String.valueOf(operandText));
                result.setResult(operandToInt);
                currentOperandText = operandText;
            }
        });
    }
}