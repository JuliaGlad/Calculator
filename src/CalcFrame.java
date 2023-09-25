import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CalcFrame extends JFrame implements ActionListener {
    public CalcFrame() {
        super();
        init();

    }

    double firstNum;
    double secondNum;
    String currentAction = null;

    public static final String SUM_OPERATION = "+";
    public static final String MINUS_OPERATION = "-";
    public static final String MULTIPLY_OPERATION = "*";
    public static final String DIVIDE_OPERATION = "/";
    public static final String EQUALS = "=";
    public static final String DELETE_ALL = "C";
    public static final String DELETE_PREVIOUS = "Delete";
    public static final String ADD_MINUS = " -";
    public static final String ADD_POINT = ".";
    private JTextField textField;

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

        JButton sum = new JButton("+");
        sum.setBounds(10, 100, 60, 40);
        sum.setFont(new Font("Arial", Font.BOLD, 18));
        sum.addActionListener(this);
        sum.setActionCommand(SUM_OPERATION);
        panel.add(sum);

        JButton minus = new JButton("-");
        minus.setBounds(10, 100, 60, 40);
        minus.setFont(new Font("Arial", Font.BOLD, 18));
        minus.addActionListener(this);
        minus.setActionCommand(MINUS_OPERATION);
        panel.add(minus);

        JButton mult = new JButton("*");
        mult.setBounds(10, 100, 60, 40);
        mult.setFont(new Font("Arial", Font.BOLD, 19));
        mult.addActionListener(this);
        mult.setActionCommand(MULTIPLY_OPERATION);
        panel.add(mult);

        JButton div = new JButton("/");
        div.setBounds(10, 100, 60, 40);
        div.setFont(new Font("Arial", Font.BOLD, 20));
        div.addActionListener(this);
        div.setActionCommand(DIVIDE_OPERATION);
        panel.add(div);

        JButton eq = new JButton("=");
        eq.setBounds(10, 100, 60, 40);
        eq.setFont(new Font("Arial", Font.BOLD, 17));
        eq.addActionListener(this);
        eq.setActionCommand(EQUALS);
        panel.add(eq);


        JButton act = new JButton("C");
        act.setBounds(10, 100, 60, 40);
        act.setFont(new Font("Arial", Font.BOLD, 17));
        act.addActionListener(this);
        act.setActionCommand(DELETE_ALL);
        panel.add(act);

        JButton dot = new JButton(".");
        dot.setBounds(10, 100, 60, 40);
        dot.setFont(new Font("Arial", Font.BOLD, 17));
        dot.addActionListener(this);
        dot.setActionCommand(ADD_POINT);
        panel.add(dot);

        JButton del = new JButton("Delete");
        del.setBounds(10, 100, 60, 40);
        del.setFont(new Font("Arial", Font.BOLD, 17));
        del.addActionListener(this);
        del.setActionCommand(DELETE_PREVIOUS);
        panel.add(del);

        JButton minusNum = new JButton("+/-");
        minusNum.setBounds(10, 100, 60, 40);
        minusNum.setFont(new Font("Arial", Font.BOLD, 17));
        minusNum.addActionListener(this);
        minusNum.setActionCommand(ADD_MINUS);
        panel.add(minusNum);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String action = e.getActionCommand();
        switch (action) {
            case "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "." :
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
        if (action.equals(ADD_MINUS)){
            fieldText += action;
            switch (action){
                case SUM_OPERATION, MINUS_OPERATION, MULTIPLY_OPERATION, DIVIDE_OPERATION -> fieldText += "-";
            }
        }
        if (fieldText.startsWith("0")) {
            fieldText = fieldText.substring(1);
        }
        if (fieldText.endsWith(".0")){
            fieldText = fieldText.substring(0, fieldText.length() - 2);
        }
        textField.setText(fieldText);
    }
}