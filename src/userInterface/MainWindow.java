package userInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame {
    private Container container;
    private JMenuBar menuBar;
    private JMenu itemsMenu, documentsMenu, pointingMenu;
    private JMenuItem searchByPromo, searchByWorkflowType, showDocumentsList, pointingByDate;

    public MainWindow() {
        super("Magasin");
        setBounds(2000, 100, 500, 500);

        container = this.getContentPane();
        container.setLayout(new BorderLayout());
        container.add(new AccueilPanel());

        menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        itemsMenu = new JMenu("Items");
        documentsMenu = new JMenu("Documents");
        pointingMenu = new JMenu("Pointing");

        searchByPromo = new JMenuItem("By promo");
        searchByWorkflowType = new JMenuItem("By workflow type");
        showDocumentsList = new JMenuItem("Documents list");
        pointingByDate = new JMenuItem("By date");

        searchByPromo.addActionListener(new SearchByPromoListener());
        searchByWorkflowType.addActionListener(new SearchByWorkflowTypeListener());
        showDocumentsList.addActionListener(new ShowDocumentsListListener());
        pointingByDate.addActionListener(new PointingByDateListener());

        itemsMenu.add(searchByPromo);
        documentsMenu.add(searchByWorkflowType);
        documentsMenu.add(showDocumentsList);
        pointingMenu.add(pointingByDate);

        menuBar.add(itemsMenu);
        menuBar.add(documentsMenu);
        menuBar.add(pointingMenu);

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
            container.add(new DocumentWorkflowPanel());
            container.repaint();
            setVisible(true);
        }
    }

    public class ShowDocumentsListListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            container.removeAll();
            container.setLayout(new BorderLayout());
            container.add(new DocumentListPanel());
            container.repaint();
            setVisible(true);
        }
    }

    public class PointingByDateListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            container.removeAll();
            container.setLayout(new BorderLayout());
            container.add(new PointingPanel());
            container.repaint();
            setVisible(true);
        }
    }
}
