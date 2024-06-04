import javax.swing.*;

/**
 * @author : Reinhard Behr
 * @class : FrmMain
 * @created : 04.06.24
 **/

public class FrmMain extends JFrame {
    private JPanel panMain;

    public FrmMain() {
        setContentPane(panMain);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,600);
        setVisible(true);
    }

    public static void main(String[] args) {
        FrmMain frmMain = new FrmMain();
    }
}
