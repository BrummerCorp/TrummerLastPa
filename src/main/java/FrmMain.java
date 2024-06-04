import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * @author : Itmam Alam
 * @class : FrmMain
 * @created : 04.06.2024
 **/

public class FrmMain extends JFrame {
    private final Font segoeUi = new Font("Segoe UI", Font.PLAIN, 11);

    private JPanel panMain;
    private JPanel panSource;
    private JPanel panSearch;
    private JPanel panResult;
    private JList lstResult;
    private JLabel lblFile;
    private JComboBox cmbFIle;
    private JButton btnOpen;
    private JList lstFile;
    private JLabel lblSearch;
    private JComboBox cmbSearch;
    private JButton btnSearch;
    private JPanel panOptions;
    private JCheckBox cbxIgnoreCapitals;
    private JRadioButton rbtnEndOfLine;
    private JRadioButton rbtnBeginOfLine;
    private JRadioButton rbtnAnywhere;
    private JPanel panRadioOptions;
    private ButtonGroup bgWhere = new ButtonGroup();

    public FrmMain() {
        setSize(800, 600);
        setMinimumSize(new Dimension(550, 500));
        setTitle("SearchReplacePA");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panMain);

        bgWhere.add(rbtnAnywhere);
        bgWhere.add(rbtnBeginOfLine);
        bgWhere.add(rbtnEndOfLine);
        rbtnAnywhere.setSelected(true);

        JMenuBar mnuBar = new JMenuBar();
        JMenu mnuFile = new JMenu("File");
        JMenu mnuEdit = new JMenu("Edit");
        JMenu mnuInfo = new JMenu("Info");

        JMenuItem mnuFileNew = new JMenuItem("New");
        JMenuItem mnuFileOpen = new JMenuItem("Open");
        JMenuItem mnuFileSave = new JMenuItem("Save");
        JMenuItem mnuFileExit = new JMenuItem("Exit", KeyEvent.VK_X);
        JMenuItem mnuEditSearch = new JMenuItem("Search");
        JMenuItem mnuEditReplace = new JMenuItem("Replace");
        JMenuItem mnuInfoAbout = new JMenuItem("About ...");
        mnuInfoAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(FrmMain.this, "            SearchReplace\n                Version 1.0\n     © 2024 BrummerCorp.", "About SearchReplace", JOptionPane.WARNING_MESSAGE);
            }
        });

        mnuFile.add(mnuFileNew);
        mnuFile.addSeparator();
        mnuFile.add(mnuFileOpen);
        mnuFile.add(mnuFileSave);
        mnuFile.addSeparator();
        mnuFile.add(mnuFileExit);

        mnuEdit.add(mnuEditSearch);
        mnuEdit.add(mnuEditReplace);

        mnuInfo.add(mnuInfoAbout);

        mnuBar.add(mnuFile);
        mnuBar.add(mnuEdit);
        mnuBar.add(mnuInfo);

        setJMenuBar(mnuBar);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Unable to set LookAndFeel");
        }

        changeFont(this, segoeUi);
        setVisible(true);
    }

    public static void changeFont(Component component, Font font) {
        component.setFont(font);
        if ( component instanceof Container )
            for (Component child : ((Container)component).getComponents())
                if (child.getName() != null && !child.getName().equals("resultList"))
                    changeFont (child, font);
    }

    public static void main(String[] args) {
        FrmMain frm = new FrmMain();
    }
}
