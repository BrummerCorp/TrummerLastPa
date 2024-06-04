import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * @author : Reinhard Behr
 * @class : FrmMain
 * @created : 04.06.24
 **/

public class FrmMain extends JFrame {
    private JPanel panMain;
    private JPanel panSource;
    private JPanel panSearch;
    private JPanel panResult;
    private JList lstResult;
    private JLabel lblFIle;
    private JComboBox cmbFile;
    private JButton btnOpen;
    private JList lstFile;
    private JLabel lblSearch;
    private JComboBox cmbSearch;
    private JButton btnSearch;
    private JPanel panOptions;
    private JCheckBox cbxIgnoreCapitals;
    private JRadioButton rbBeginnOfLine;
    private JRadioButton rbEndOfLine;
    private JRadioButton rbAnywhere;

    public FrmMain() {
        setContentPane(panMain);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,600);
        setMinimumSize(new Dimension(550, 500));
        setTitle("GUIDemo - Behr");

        JMenuBar mb = new JMenuBar();

        JMenu menfile = new JMenu("File");
        JMenuItem miNew = new JMenuItem("New");
        JMenuItem miOpen = new JMenuItem("Open");
        JMenuItem miExit = new JMenuItem("Exit");

        miNew.setMnemonic(KeyEvent.VK_N);
        miNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));

        miOpen.setMnemonic(KeyEvent.VK_O);
        miOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));

        miExit.setMnemonic(KeyEvent.VK_X);
        miExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));

        miExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menfile.add(miNew);
        menfile.addSeparator();
        menfile.add(miOpen);
        menfile.addSeparator();
        menfile.add(miExit);

        mb.add(menfile);

        setJMenuBar(mb);



        ButtonGroup bgWhere = new ButtonGroup();
        bgWhere.add(rbAnywhere);
        bgWhere.add(rbBeginnOfLine);
        bgWhere.add(rbEndOfLine);
        rbAnywhere.setSelected(true);

        setVisible(true);
    }

    public static void main(String[] args) {
        FrmMain frmMain = new FrmMain();
    }
}
