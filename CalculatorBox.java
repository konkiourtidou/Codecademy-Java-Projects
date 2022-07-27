import javax.swing.*;
import java.awt.*; //Font class for buttons
import java.awt.event.*;

/*
ActionListeners for each of these buttons.
These action listeners make the buttons listen to events such as when the user clicks on a button
and then update the output and other necessary fields
*/
public class CalculatorBox extends JFrame {
    //Button creation
    JButton btnAdd, btnSubtract, btnDivide, btnMultiply, btnClear, btnDelete, btnEquals, btnDot;
    JButton[] numBtn;
    JTextField output;
    String previous, current, operator, allExpression;

    public CalculatorBox() {
        //set title
        super("Box Calculator");
        //main panel, will house all others components
        JPanel mainPanel = new JPanel();

        // Initializing the calculator operands
        current = "";
        previous = "";
        allExpression = "";

        /*
        Since the calculator has a BoxLayout, we can create rows and then group all the components of
        the calculator into these rows. The rows comprise five sub-panels situated inside the main panel.
         */
        // Create sub panels inside main panel
        JPanel row1 = new JPanel();
        JPanel row2 = new JPanel();
        JPanel row3 = new JPanel();
        JPanel row4 = new JPanel();
        JPanel row5 = new JPanel();

        // Initialize components
        output = new JTextField(16);
        btnSubtract = new JButton(" - ");
        btnAdd = new JButton(" + ");
        btnDivide = new JButton(" ÷ ");
        btnMultiply = new JButton(" * ");
        btnDot = new JButton(" . ");
        btnEquals = new JButton(" = ");
        btnClear = new JButton(" C ");
        btnDelete = new JButton(" <- ");

        /*
         create objects of all the button handler classes which can then be passed as action listeners
         to each of the buttons.
         */
        // Instantiate action listeners
        NumberBtnHandler numBtnHandler = new NumberBtnHandler();
        OtherBtnHandler otherBtnHandler = new OtherBtnHandler();
        OperatorBtnHandler opBtnHandler = new OperatorBtnHandler();

        // Initialize, style, and add action listeners to number buttons
        numBtn = new JButton[11];
        numBtn[10] = btnDot;
        for (int count = 0; count < numBtn.length - 1; count++) {
            numBtn[count] = new JButton(String.valueOf(count));
            numBtn[count].setFont(new Font("Monospaced", Font.BOLD, 26));
            numBtn[count].addActionListener(numBtnHandler);
            numBtn[count].setPreferredSize(new Dimension(30, 30));
        }


        // Style other buttons
        //https://docs.oracle.com/javase/tutorial/uiswing/components/border.html
        btnDot.setFont(new Font("Monospaced", Font.BOLD, 26));
        btnDot.setBorder(BorderFactory.createRaisedBevelBorder());
        btnEquals.setFont(new Font("Monospaced", Font.BOLD, 26));
        btnEquals.setBorder(BorderFactory.createRaisedBevelBorder());
        btnAdd.setFont(new Font("Monospaced", Font.BOLD, 26));
        btnAdd.setBorder(BorderFactory.createRaisedBevelBorder());
        btnSubtract.setFont(new Font("Monospaced", Font.BOLD, 26));
        btnSubtract.setBorder(BorderFactory.createRaisedBevelBorder());
        btnDivide.setFont(new Font("Monospaced", Font.BOLD, 26));
        btnDivide.setBorder(BorderFactory.createRaisedBevelBorder());
        btnMultiply.setFont(new Font("Monospaced", Font.BOLD, 26));
        btnMultiply.setBorder(BorderFactory.createRaisedBevelBorder());
        btnClear.setFont(new Font("Monospaced", Font.BOLD, 24));
        btnClear.setBorder(BorderFactory.createRaisedBevelBorder());
        btnClear.setForeground(Color.blue);
        btnDelete.setFont(new Font("Monospaced", Font.BOLD, 24));
        btnDelete.setBorder(BorderFactory.createRaisedBevelBorder());
        btnDelete.setForeground(Color.blue);

        // Style the output display
        output.setMaximumSize(new Dimension(235, 45));
        output.setFont(new Font("Monospaced", Font.BOLD, 28));
        output.setDisabledTextColor(new Color(0, 0, 0));
        output.setMargin(new Insets(2, 5, 1, 1));
        output.setText("0");
        output.setForeground(Color.gray);



        // Add action listeners to other buttons
        btnDot.addActionListener(numBtnHandler);
        btnDelete.addActionListener(otherBtnHandler);
        btnClear.addActionListener(otherBtnHandler);
        btnEquals.addActionListener(otherBtnHandler);

        // Add action listeners to operation buttons
        btnMultiply.addActionListener(opBtnHandler);
        btnAdd.addActionListener(opBtnHandler);
        btnSubtract.addActionListener(opBtnHandler);
        btnDivide.addActionListener(opBtnHandler);

        /*
        these components are not visible unless added to their respective panels and the main panel.
        The next step is to set the layout of each row of the calculator.
         */
        // Set the layout of each row in the pane
        row1.setLayout(new BoxLayout(row1, BoxLayout.LINE_AXIS));
        row2.setLayout(new BoxLayout(row2, BoxLayout.LINE_AXIS));
        row3.setLayout(new BoxLayout(row3, BoxLayout.LINE_AXIS));
        row4.setLayout(new BoxLayout(row4, BoxLayout.LINE_AXIS));
        row5.setLayout(new BoxLayout(row5, BoxLayout.LINE_AXIS));
        // Add components to each of the row
        row1.add(Box.createHorizontalGlue());
        /*
        we created a horizontal glue and added it as the first component in row1.
        This horizontal glue pushes the C and D buttons as far as possible to the horizontal edge of the pane.
         */
        row1.add(btnClear);
        row1.add(btnDelete);
        row2.add(numBtn[7]);
        row2.add(numBtn[8]);
        row2.add(numBtn[9]);
        row2.add(btnMultiply);
        row3.add(numBtn[4]);
        row3.add(numBtn[5]);
        row3.add(numBtn[6]);
        row3.add(btnAdd);
        row4.add(numBtn[1]);
        row4.add(numBtn[2]);
        row4.add(numBtn[3]);
        row4.add(btnSubtract);
        row5.add(btnDot);
        row5.add(numBtn[0]);
        row5.add(btnEquals);
        row5.add(btnDivide);

        // Add all rows to the main panel
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.add(output);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        mainPanel.add(row1);
        mainPanel.add(row2);
        mainPanel.add(row3);
        mainPanel.add(row4);
        mainPanel.add(row5);

        // final touch
        this.add(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(305, 380);
    }

    //utility  methods
    public void delete() {
        if (current.length() > 0) {
            current = current.substring(0, current.length() - 1);
            allExpression = allExpression.substring(0, allExpression.length() - 1);
        } else if (operator != null){
            operator = null;
            allExpression = allExpression.substring(0, allExpression.length() - 1);
        } else if(previous.length() > 0){

            if (!previous.substring(0, previous.length() - 1).equals("-")) {
                previous = previous.substring(0, previous.length() - 1);
                allExpression = allExpression.substring(0, allExpression.length() - 1);
            }
        }
    }

    public void clear() {
        current = "";
        previous = "";
        allExpression = "";
        operator = null;
    }

    public void updateOutput(){
        output.setText(allExpression);
    }

    public void appendToOutput(String num) {
        // Prevents adding more than one dot on the output
        if (num.equals(".") && current.contains(".")) {
            return;
        }
        current += num;
        allExpression += num;
    }

    public void selectOperator(String newOperator) {
        // if no number is entered yet
        if (current.isEmpty()) {
            operator = newOperator;
            allExpression += newOperator;
            return;
        }

        if (!previous.isEmpty()) {
            calculate();
        }

        operator = newOperator;
        previous = current;
        current = "";
        allExpression = previous + operator;
    }

    public void calculate() {
        if (previous.length() < 1 || current.length() < 1) {
            return;
        }
        double result = 0.0;
        double num1 = Double.parseDouble(previous);
        double num2 = Double.parseDouble(current);
        switch (operator) {
            case "*" -> result = num1 * num2;
            case "+" -> result = num1 + num2;
            case "-" -> result = num1 - num2;
            case "÷" -> result = num1 / num2;
            default -> {
            }
        }
        //result =  String.format("%.7f", String.valueOf(result));
        current = "";
        operator = null;
        previous = String.valueOf(result);
        previous = processOutputNumber(previous);
        allExpression = previous;

    }

    public String processOutputNumber(String operant) {
        if (operant.length() > 0) {
            String integerPart = operant.split("\\.")[0];
            String decimalPart = operant.split("\\.")[1];
            if (decimalPart.equals("0")) {
                operant = integerPart;
            } else if (decimalPart.length() > 6){
                operant = integerPart +"." + decimalPart.substring(0,7);

            }
        }
        return operant;
    }

    /*
    NumberBtnHandler will handle events from all the number buttons, including the dot button.
    This class will implement the ActionListener interface to override the actionPerformed() abstract method
    in the ActionListener interface. This method takes in an ActionEvent parameter, which is made available
    by the event package imported earlier.
     */
    private class NumberBtnHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            /*
            what will happen when any of the “number buttons” get clicked, which can be achieved inside the
            actionPerformed() method, which takes in a parameter we named e.
            The parameter e can then access the Objects getSource() method to get the object where an event
            initially occurred, which is useful when checking for the clicked button.
             */
            JButton selectedBtn = (JButton) e.getSource();
            for (JButton btn : numBtn) {
                if (selectedBtn == btn) {
                    appendToOutput(btn.getText());
                    updateOutput();
                }
            }
        }
    }
    /*
        This class is similar to the NumberBtnHandler created above,
        but the significant difference lies inside the actionPerformed() method.
        Inside this class’s actionPerformed() method, we will compare each operator button with the button
        clicked to know which operator was selected. We can achieve this using if statements since the operator
        buttons are not inside an array, unlike the “number buttons”.

        When an operator button is selected, we get the button’s text, pass it to the selectOperator() method,
        and update the output by calling the updateOutput() method.
         */
    private class OperatorBtnHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton selectedBtn = (JButton) e.getSource();
            if (selectedBtn == btnMultiply) {
                selectOperator(btnMultiply.getText());
            } else if (selectedBtn == btnAdd) {
                selectOperator(btnAdd.getText());
            } else if (selectedBtn == btnSubtract) {
                selectOperator(btnSubtract.getText());
            } else if (selectedBtn == btnDivide) {
                selectOperator(btnDivide.getText());
            }
            updateOutput();
        }
    }

    /*
    ‘class OtherBtnHandler’ The last action listener class to be created is the OtherBtnHandler class.
    This class will also implement the ActionListener interface and override the actionPerformed() method.
    Similar to the OperatorBtnHandler, OtherBtnHandler will compare the selected button to the other buttons
    in the calculator and execute their respective methods.
    After the execution, we will also call the outputDisplay() method to update the UI.
     */
    private class OtherBtnHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e ) {
            JButton selectedBtn = (JButton) e.getSource();
            if (selectedBtn == btnDelete) {
                delete();
            } else if (selectedBtn == btnClear) {
                clear();
            } else if (selectedBtn == btnEquals) {
                calculate();
            }
            updateOutput();
        }
    }

    public static void main(String[] args) {
        new CalculatorBox();
    }
}