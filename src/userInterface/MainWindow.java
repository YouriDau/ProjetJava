package userInterface;

import dataAccess.SingletonConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame {
    private Container container;
    private JMenuBar menuBar;
    private JMenu applicationMenu, itemsMenu, documentsMenu, pointingMenu;
    private JMenuItem quit, searchByPromo, searchByWorkflowType, allDocuments, pointingByDate;

    public MainWindow() {
        super("Magasin");
        setBounds(100, 100, 500, 500);

        container = this.getContentPane();
        container.setLayout(new BorderLayout());
        container.add(new HomePanel());

        menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        applicationMenu = new JMenu("Application");
        itemsMenu = new JMenu("Items");
        documentsMenu = new JMenu("Documents");
        pointingMenu = new JMenu("Pointing");

        quit = new JMenuItem("Quit");
        searchByPromo = new JMenuItem("By promo");
        searchByWorkflowType = new JMenuItem("By workflow type");
        allDocuments = new JMenuItem("Documents list");
        pointingByDate = new JMenuItem("By date");

        quit.addActionListener(new QuitListener());
        searchByPromo.addActionListener(new SearchByPromoListener());
        searchByWorkflowType.addActionListener(new SearchByWorkflowTypeListener());
        allDocuments.addActionListener(new AllDocumentsListener());
        pointingByDate.addActionListener(new PointingByDateListener());

        applicationMenu.add(quit);
        itemsMenu.add(searchByPromo);
        documentsMenu.add(searchByWorkflowType);
        documentsMenu.add(allDocuments);
        pointingMenu.add(pointingByDate);

        menuBar.add(applicationMenu);
        menuBar.add(itemsMenu);
        menuBar.add(documentsMenu);
        menuBar.add(pointingMenu);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                if (!SingletonConnection.connectionIsNull())
                    SingletonConnection.closeConnection();
                System.exit(0);
            }
        });
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public class QuitListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (!SingletonConnection.connectionIsNull())
                SingletonConnection.closeConnection();
            System.exit(0);
        }
    }

    public class SearchByPromoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            container.removeAll();
            container.add(new ItemPanel(container));
            container.repaint();
            setVisible(true);
        }
    }

    public class SearchByWorkflowTypeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            container.removeAll();
            container.add(new WorkflowPanel(container));
            container.repaint();
            setVisible(true);
        }
    }

    public class AllDocumentsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            container.removeAll();
            container.add(new AllDocumentsPanel());
            container.repaint();
            setVisible(true);
        }
    }

    public class PointingByDateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            container.removeAll();
            container.add(new PointingPanel(container));
            container.repaint();
            setVisible(true);
        }
    }
}
