package userInterface;

import controller.ApplicationController;
import exception.CloseConnectionException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame {
    private Container container;
    private JMenuBar menuBar;
    private JMenu applicationMenu, itemsMenu, documentsMenu, pointingMenu, promotionsMenu;
    private JMenuItem quit, home, searchByPromo, searchByWorkflowType, allDocuments, pointingByDate, businessTask;


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
        promotionsMenu = new JMenu("Promotions");

        quit = new JMenuItem("Quit");
        home = new JMenuItem("Home");
        searchByPromo = new JMenuItem("By promo");
        searchByWorkflowType = new JMenuItem("By workflow type");
        allDocuments = new JMenuItem("Documents list");
        pointingByDate = new JMenuItem("By date");
        businessTask = new JMenuItem("By items");

        quit.addActionListener(new QuitListener());
        home.addActionListener(new HomeListener());
        searchByPromo.addActionListener(new SearchByPromoListener());
        searchByWorkflowType.addActionListener(new SearchByWorkflowTypeListener());
        allDocuments.addActionListener(new AllDocumentsListener());
        pointingByDate.addActionListener(new PointingByDateListener());
        businessTask.addActionListener( new PromotionsByItemListener());

        applicationMenu.add(quit);
        applicationMenu.add(home);
        itemsMenu.add(searchByPromo);
        documentsMenu.add(searchByWorkflowType);
        documentsMenu.add(allDocuments);
        pointingMenu.add(pointingByDate);
        promotionsMenu.add(businessTask);

        menuBar.add(applicationMenu);
        menuBar.add(itemsMenu);
        menuBar.add(documentsMenu);
        menuBar.add(pointingMenu);
        menuBar.add(promotionsMenu);

        addWindowListener(new WindowAdapter() {
            ApplicationController controller = new ApplicationController();
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    controller.closeConnection();
                    System.exit(0);
                } catch (CloseConnectionException exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage(), exception.getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    private class QuitListener implements ActionListener {
        ApplicationController controller = new ApplicationController();
        @Override
        public void actionPerformed(ActionEvent event) {

            try {
                controller.closeConnection();
                System.exit(0);
            } catch (CloseConnectionException exception){
                JOptionPane.showMessageDialog(null, exception.getMessage(), exception.getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    private class HomeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            container.removeAll();
            container.add(new HomePanel());
            container.repaint();
            setVisible(true);
        }
    }

    private class SearchByPromoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            container.removeAll();
            MainWindow.this.setSize(561, 500);
            container.add(new ItemPanel(container));
            container.repaint();
            setVisible(true);
        }
    }

    private class SearchByWorkflowTypeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            container.removeAll();
            container.add(new WorkflowPanel(container));
            container.repaint();
            setVisible(true);
        }
    }

    private class AllDocumentsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            container.removeAll();
            container.add(new AllDocumentsPanel(container), BorderLayout.PAGE_START);
            container.repaint();
            MainWindow.this.pack();
            setVisible(true);
        }
    }

    private class PointingByDateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            container.removeAll();
            container.add(new PointingPanel(container));
            container.repaint();
            setVisible(true);
        }
    }

    private class PromotionsByItemListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            container.removeAll();
            container.add(new ItemSelectionPanel(container));
            container.repaint();
            setVisible(true);
        }
    }
}
