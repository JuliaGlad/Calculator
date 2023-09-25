
public class Main   {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            CalcFrame.setDefaultLookAndFeelDecorated(true);
            CalcFrame frame = new CalcFrame();
            frame.setLocationRelativeTo(null);
        });
    }
}
