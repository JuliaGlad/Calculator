import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CalcFrame extends JFrame implements ActionListener {

    private JTextField textField;

    public CalcFrame() {
        super();
        init();

    }

    double firstNum;
    double secondNum;
    String currentAction = null;

    String fieldText = "0";

    public void init() {

        JFrame frameNew = new JFrame("Calculator");
        frameNew.setVisible(true);
        frameNew.setSize(280, 230);
        frameNew.setResizable(false);
        frameNew.setDefaultCloseOperation(EXIT_ON_CLOSE);

        textField = new JTextField(14);
        textField.setBounds(10, 40, 270, 40);
        textField.setFont(new Font("Arial", Font.BOLD, 20));
        textField.setEditable(false);
        textField.getText();
        textField.revalidate();
        textField.setText(fieldText);

        JPanel panel = new JPanel();
        frameNew.add(panel);
        panel.add(textField);

        ArrayList<JButton> buttons = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            JButton buttonNew = new JButton(String.valueOf(i));
            buttonNew.setBounds(10, 100, 60, 40);
            buttonNew.setFont(new Font("Arial", Font.BOLD, 17));
            buttonNew.addActionListener(this);
            buttonNew.setActionCommand(String.valueOf(i));
            buttons.add(buttonNew);
        }
        buttons.forEach(panel::add);

        initButton(panel, "+", Constants.SUM_OPERATION);

        initButton(panel, "-", Constants.MINUS_OPERATION);

        initButton(panel, "*", Constants.MULTIPLY_OPERATION);

        initButton(panel, "/", Constants.DIVIDE_OPERATION);

        initButton(panel, "=", Constants.EQUALS);

        initButton(panel, "C", Constants.DELETE_ALL);

        initButton(panel, ".", Constants.ADD_POINT);

        initButton(panel, "Delete", Constants.DELETE_PREVIOUS);

        initButton(panel, "+/-", Constants.ADD_MINUS);
    }

    void initButton(JPanel panel, String text, String command) {
        JButton minusNum = new JButton(text);
        minusNum.setBounds(10, 100, 60, 40);
        minusNum.setFont(new Font("Arial", Font.BOLD, 17));
        minusNum.addActionListener(this);
        minusNum.setActionCommand(command);
        panel.add(minusNum);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch (action) {
            case "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ".":
                fieldText += action;
                break;
            case Constants.SUM_OPERATION, Constants.MINUS_OPERATION, Constants.MULTIPLY_OPERATION, Constants.DIVIDE_OPERATION:
                firstNum = Double.parseDouble(textField.getText());
                fieldText += action;
                currentAction = action;
                break;
            case Constants.EQUALS:
                int index = fieldText.indexOf(currentAction);
                secondNum = Double.parseDouble(fieldText.substring(index + 1));
                switch (currentAction) {
                    case Constants.SUM_OPERATION:
                        fieldText = String.valueOf(firstNum + secondNum);
                        break;
                    case Constants.MINUS_OPERATION:
                        fieldText = String.valueOf(firstNum - secondNum);
                        break;
                    case Constants.MULTIPLY_OPERATION:
                        fieldText = String.valueOf(firstNum * secondNum);
                        break;
                    case Constants.DIVIDE_OPERATION:
                        fieldText = String.valueOf(firstNum / secondNum);
                        break;
                }
        }
        if (action.equals(Constants.DELETE_ALL)) {
            fieldText = String.valueOf(0);
        }
        if (action.equals(Constants.DELETE_PREVIOUS)) {
            if (fieldText != null) {
                fieldText = fieldText.substring(0, fieldText.length() - 1);
            }
        }
        if (action.equals(Constants.ADD_MINUS)) {
            fieldText += action;
            fieldText += "-";
        }
        if (fieldText.startsWith("0")) {
            fieldText = fieldText.substring(1);
        }
        if (fieldText.endsWith(".0")) {
            fieldText = fieldText.substring(0, fieldText.length() - 2);
        }
        textField.setText(fieldText);
    }
}
