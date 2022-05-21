package userInterface;

import controller.ApplicationController;
import exception.DBException;
import exception.SingletonConnectionException;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.GregorianCalendar;
import java.util.Map;

public class NewPromotionPanel extends JPanel {
    private Container container;
    private GridBagConstraints layoutConstraints;
    private ApplicationController controller;

    private JLabel percentageLabel;
    private JLabel startDateLabel;
    private JLabel endDateLabel;
    private JLabel wordingItemLabel;
    private JLabel sliderValue;
    private JSlider percentageSlider;
    private JSpinner startDateSpinner;
    private JSpinner endDateSpinner;
    private JSpinner.DateEditor startDateSpinnerEditor;
    private JSpinner.DateEditor endDateSpinnerEditor;
    private GregorianCalendar date;



    public NewPromotionPanel(Container container, String wordingItem){
        setContainer(container);
        this.setLayout(new GridBagLayout());
        layoutConstraints = new GridBagConstraints();
        try{
            // Création du controller
            controller = new ApplicationController();
            // Préparation des JLabels
            percentageLabel = new JLabel("Promotion percentage : ");
            startDateLabel = new JLabel("Start date of the promotion : ");
            endDateLabel = new JLabel("End date of the promotion : ");
            wordingItemLabel = new JLabel(wordingItem);
            wordingItemLabel.setFont(new Font("Verdana", Font.PLAIN, 24));
            Font font = wordingItemLabel.getFont();
            Map attributes = font.getAttributes();
            attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
            wordingItemLabel.setFont(font.deriveFont(attributes));
            sliderValue = new JLabel();

            // Préparations des entrées
                // preparation du JSlider
            percentageSlider = new JSlider(JSlider.HORIZONTAL,0,100,50);
            percentageSlider.setMajorTickSpacing(20);
            percentageSlider.setMinorTickSpacing(5);
            percentageSlider.setPaintTicks(true);
            percentageSlider.setPaintLabels(true);
            percentageSlider.addChangeListener(new sliderListener());
                // preparation des JSpinner.DateEditor
            date = new GregorianCalendar();
            startDateSpinner = new JSpinner(new SpinnerDateModel());
            endDateSpinner = new JSpinner(new SpinnerDateModel());
            startDateSpinnerEditor = new JSpinner.DateEditor(startDateSpinner, "dd-MM-yyyy");
            endDateSpinnerEditor = new JSpinner.DateEditor(endDateSpinner, "dd-MM-yyyy");
            startDateSpinner.setEditor(startDateSpinnerEditor);
            endDateSpinner.setEditor(endDateSpinnerEditor);
            startDateSpinner.setValue(date.getTime());
            endDateSpinner.setValue(date.getTime());

            // ajouts composants
            layoutConstraints.insets = new Insets(0,0,15,0);
            layoutConstraints.gridwidth = 2;
            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 0;
            this.add(wordingItemLabel, layoutConstraints);

            layoutConstraints.gridwidth = 1;
            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 2;
            this.add(percentageLabel, layoutConstraints);

            layoutConstraints.gridx = 1;
            this.add(percentageSlider, layoutConstraints);

            layoutConstraints.gridx = 2;
            this.add(sliderValue, layoutConstraints);

            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 3;
            this.add(startDateLabel, layoutConstraints);

            layoutConstraints.gridx = 1;
            this.add(startDateSpinner, layoutConstraints);

            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 4;
            this.add(endDateLabel, layoutConstraints);

            layoutConstraints.gridx = 1;
            this.add(endDateSpinner, layoutConstraints);

        } catch (DBException exception){
            JOptionPane.showMessageDialog(null, exception.getErrorMessage(), "SQLError", JOptionPane.ERROR_MESSAGE);
        } catch (SingletonConnectionException exception){
            JOptionPane.showMessageDialog(null, exception.getErrorMessage(), exception.getErrorTitle(), JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public class sliderListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
                sliderValue.setText(""+percentageSlider.getValue());
        }
    }
}
