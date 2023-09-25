import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CalcFrame extends JFrame implements ActionListener {

    private JTextField textField;

    double firstNum;
    double secondNum;
    String currentAction = null;

    String fieldText = "0";

    public static final String SUM_OPERATION = "+";
    public static final String MINUS_OPERATION = "-";
    public static final String MULTIPLY_OPERATION = "*";
    public static final String DIVIDE_OPERATION = "/";
    public static final String EQUALS = "=";
    public static final String DELETE_ALL = "C";
    public static final String ADD_POINT = ".";
    public static final String DELETE_PREVIOUS = "Delete";

    public CalcFrame() {
        super();
        init();

    }

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

        initButton(panel, "+", SUM_OPERATION);

        initButton(panel, "-", MINUS_OPERATION);

        initButton(panel, "*", MULTIPLY_OPERATION);

        initButton(panel, "/", DIVIDE_OPERATION);

        initButton(panel, "=", EQUALS);

        initButton(panel, "C", DELETE_ALL);

        initButton(panel, ".", ADD_POINT);

        initButton(panel, "Delete", DELETE_PREVIOUS);

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
            case SUM_OPERATION, MINUS_OPERATION, MULTIPLY_OPERATION, DIVIDE_OPERATION:
                firstNum = Double.parseDouble(textField.getText());
                fieldText += action;
                currentAction = action;
                break;
            case EQUALS:
                int index = fieldText.indexOf(currentAction);
                secondNum = Double.parseDouble(fieldText.substring(index + 1));
                switch (currentAction) {
                    case SUM_OPERATION:
                        fieldText = String.valueOf(firstNum + secondNum);
                        break;
                    case MINUS_OPERATION:
                        fieldText = String.valueOf(firstNum - secondNum);
                        break;
                    case MULTIPLY_OPERATION:
                        fieldText = String.valueOf(firstNum * secondNum);
                        break;
                    case DIVIDE_OPERATION:
                        fieldText = String.valueOf(firstNum / secondNum);
                        break;
                }
        }
        if (action.equals(DELETE_ALL)) {
            fieldText = String.valueOf(0);
        }
        if (action.equals(DELETE_PREVIOUS)) {
            if (fieldText != null) {
                fieldText = fieldText.substring(0, fieldText.length() - 1);
            }
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
