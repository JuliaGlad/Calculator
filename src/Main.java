import javax.swing.*;

public class Main   {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                CalcFrame.setDefaultLookAndFeelDecorated(true);
                CalcFrame frame = new CalcFrame();
                frame.setLocationRelativeTo(null);
            }
        });
    }
}