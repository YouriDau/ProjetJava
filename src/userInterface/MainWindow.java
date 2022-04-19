package userInterface;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame {
    private Container container;
    private AccueilPanel accueilPanel;
    private JMenuBar menuBar;
    private JMenu itemsMenu, documentsMenu;
    private JMenuItem searchByPromo, searchByWorkflowType, showDocuments;

    public MainWindow() {
        super("Magasin");
        setBounds(100, 100, 500, 500);

        accueilPanel = new AccueilPanel();

        container = this.getContentPane();
        container.setLayout(new BorderLayout());
        container.add(accueilPanel);

        menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        itemsMenu = new JMenu("Items");
        documentsMenu = new JMenu("Documents");

        searchByPromo = new JMenuItem("Search by promo");
        searchByWorkflowType = new JMenuItem("Search by workflow type");
        showDocuments = new JMenuItem("Show documents list");

        searchByPromo.addActionListener(new SearchByPromoListener());
        searchByWorkflowType.addActionListener(new SearchByWorkflowTypeListener());
        showDocuments.addActionListener(new ShowDocumentsListener());

        itemsMenu.add(searchByPromo);
        documentsMenu.add(searchByWorkflowType);
        documentsMenu.add(showDocuments);

        menuBar.add(itemsMenu);
        menuBar.add(documentsMenu);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setVisible(true);
    }

    public class SearchByPromoListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            container.removeAll();
            container.setLayout(new BorderLayout());
            container.add(new ItemPanel());
            container.repaint();
            setVisible(true);
        }
    }

    public class SearchByWorkflowTypeListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            container.removeAll();
            container.setLayout(new BorderLayout());
            container.add(new DocumentPanel());
            container.repaint();
            setVisible(true);
        }
    }

    public class ShowDocumentsListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            container.removeAll();
            container.setLayout(new BorderLayout());
            container.add(new DocumentPanel());
            container.repaint();
            setVisible(true);
        }
    }
}
