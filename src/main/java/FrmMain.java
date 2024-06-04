import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.*;

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
    private JComboBox cmbFile;
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
    private DefaultListModel dlmFile = new DefaultListModel();
    private DefaultComboBoxModel dcbmFile = new DefaultComboBoxModel();
    private DefaultListModel dlmResult = new DefaultListModel();

    public FrmMain() {
        setSize(800, 600);
        setMinimumSize(new Dimension(550, 500));
        setTitle("SearchReplacePA");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panMain);

        // set models
        lstFile.setModel(dlmFile);
        cmbFile.setModel(dcbmFile);
        lstResult.setModel(dlmResult);

        // button group
        bgWhere.add(rbtnAnywhere);
        bgWhere.add(rbtnBeginOfLine);
        bgWhere.add(rbtnEndOfLine);
        rbtnAnywhere.setSelected(true);

        // add menu bar
        JMenuBar mnuBar = new JMenuBar();
        JMenu mnuFile = new JMenu("File");
        JMenu mnuEdit = new JMenu("Edit");
        JMenu mnuInfo = new JMenu("Info");

        JMenuItem mnuFileNew = new JMenuItem("New", KeyEvent.VK_N);
        mnuFileNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        mnuFileNew.setMnemonic(KeyEvent.VK_N);

        JMenuItem mnuFileOpen = new JMenuItem("Open", KeyEvent.VK_O);
        mnuFileOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        mnuFileOpen.setMnemonic(KeyEvent.VK_O);
        mnuFileOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });

        JMenuItem mnuFileSave = new JMenuItem("Save", KeyEvent.VK_S);
        mnuFileSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        mnuFileSave.setMnemonic(KeyEvent.VK_S);

        JMenuItem mnuFileExit = new JMenuItem("Exit", KeyEvent.VK_X);
        mnuFileExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));
        mnuFileExit.setMnemonic(KeyEvent.VK_X);
        mnuFileExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

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

        // make everything better
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Unable to set LookAndFeel");
        }

        changeFont(this, segoeUi);
        setVisible(true);
        btnOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });
    }

    private void openFile() {
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return (f.isDirectory() || f.getName().endsWith(".txt"));
            }

            @Override
            public String getDescription() {
                return "Text files (*.txt)";
            }
        });

        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(fc.getSelectedFile()));
                String line;

                if (dcbmFile.getIndexOf(fc.getSelectedFile()) < 0) {
                    dcbmFile.insertElementAt(fc.getSelectedFile(), 0);
                }

                while((line = br.readLine()) != null)
                    dlmFile.addElement(line);

                br.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
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
        changeFont(frm, frm.segoeUi);
    }
}
