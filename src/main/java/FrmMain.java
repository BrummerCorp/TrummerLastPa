import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

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

    private FileFilter fileFilter = new FileFilter() {
        @Override
        public boolean accept(File f) {
            return f.isDirectory() || f.getName().endsWith("txt");
        }

        @Override
        public String getDescription() {
            return "textfiles (*.txt)";
        }
    };

    private DefaultListModel dlmFile = new DefaultListModel();
    private DefaultComboBoxModel dcbm = new DefaultComboBoxModel();

    public FrmMain() {
        setContentPane(panMain);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setMinimumSize(new Dimension(550, 500));
        setTitle("GUIDemo - Behr");

        lstFile.setModel(dlmFile);
        cmbFile.setModel(dcbm);

//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        }
//        catch (Exception e) {
//            System.out.println("Unable to set Look and Feel");
//        }

        JMenuBar mb = new JMenuBar();

        JMenu menfile = new JMenu("File");
        JMenuItem miNew = new JMenuItem("New");
        JMenuItem miOpen = new JMenuItem("Open");
        JMenuItem miExit = new JMenuItem("Exit");

        miNew.setMnemonic(KeyEvent.VK_N);
        miNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));

        miOpen.setMnemonic(KeyEvent.VK_O);
        miOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));

        miOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                miOpen();
            }
        });

        cmbFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });

        btnOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                miOpen();
            }
        });

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

    public void miOpen() {
        JFileChooser fc = new JFileChooser();
        fc.addChoosableFileFilter(fileFilter);
        fc.setFileFilter(fileFilter);


        if(fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(fc.getSelectedFile()));

                if(dcbm.getIndexOf(fc.getSelectedFile().getAbsolutePath()) < 0) {
                    dcbm.insertElementAt(fc.getSelectedFile().getAbsolutePath(), 0);
                    dcbm.setSelectedItem(fc.getSelectedFile().getAbsolutePath());
                }
                String line;
                while((line = br.readLine()) != null) {
                    dlmFile.addElement(line);
                }
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }


}
