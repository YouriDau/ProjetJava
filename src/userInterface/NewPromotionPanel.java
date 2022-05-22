package userInterface;

import controller.ApplicationController;
import exception.AddPromotionException;
import exception.DBException;
import exception.SingletonConnectionException;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.regex.Pattern;

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

    private JButton back;
    private JButton submit;


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
            sliderValue = new JLabel(""+50);

            // Préparations des entrées
                // preparation du JSlider
            percentageSlider = new JSlider(JSlider.HORIZONTAL,0,100,50);
            percentageSlider.setMajorTickSpacing(20);
            percentageSlider.setMinorTickSpacing(5);
            percentageSlider.setPaintTicks(true);
            percentageSlider.setPaintLabels(true);
            percentageSlider.addChangeListener(new SliderListener());
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
                // preparation des boutons
            back = new BackButton(new PromotionsByItemPanel(container, wordingItem), container);
            submit = new JButton("Submit");
            submit.addActionListener(new SubmitListener());
            // ajouts composants
            layoutConstraints.insets = new Insets(0,0,15,0);
            layoutConstraints.gridwidth = 2;
            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 0;
            this.add(wordingItemLabel, layoutConstraints);

            layoutConstraints.anchor = GridBagConstraints.LINE_START;
            layoutConstraints.gridwidth = 1;
            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 2;
            this.add(percentageLabel, layoutConstraints);

            layoutConstraints.anchor = GridBagConstraints.LINE_END;
            layoutConstraints.gridx = 1;
            this.add(percentageSlider, layoutConstraints);

            layoutConstraints.anchor = GridBagConstraints.LINE_END;
            layoutConstraints.gridx = 2;
            this.add(sliderValue, layoutConstraints);

            layoutConstraints.anchor = GridBagConstraints.LINE_START;
            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 3;
            this.add(startDateLabel, layoutConstraints);

            layoutConstraints.anchor = GridBagConstraints.LINE_END;
            layoutConstraints.gridx = 1;
            this.add(startDateSpinner, layoutConstraints);

            layoutConstraints.anchor = GridBagConstraints.LINE_START;
            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 4;
            this.add(endDateLabel, layoutConstraints);

            layoutConstraints.anchor = GridBagConstraints.LINE_END;
            layoutConstraints.gridx = 1;
            this.add(endDateSpinner, layoutConstraints);

            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 5;
            layoutConstraints.anchor = GridBagConstraints.LINE_START;
            this.add(back, layoutConstraints);

            layoutConstraints.anchor = GridBagConstraints.LINE_END;
            layoutConstraints.gridx = 1;
            this.add(submit, layoutConstraints);


        } catch (DBException exception){
            JOptionPane.showMessageDialog(null, exception.getErrorMessage(), "SQLError", JOptionPane.ERROR_MESSAGE);
        } catch (SingletonConnectionException exception){
            JOptionPane.showMessageDialog(null, exception.getErrorMessage(), exception.getErrorTitle(), JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setContainer(Container container) {
        this.container = container;
    }
    public GregorianCalendar convertJspinnerEditortoDate(JSpinner.DateEditor startDateSpinner){
        GregorianCalendar date = new GregorianCalendar();
        date.setTime((Date) startDateSpinner.getTextField().getValue());
        date.set(date.get(Calendar.YEAR), date.get(Calendar.MONTH)+1,date.get(Calendar.DAY_OF_MONTH));
        System.out.println(date.get(Calendar.YEAR) + " " + date.get(Calendar.MONTH) +  " "+date.get(Calendar.DAY_OF_MONTH));
        return date;
    }

    public boolean firstDateInferiorToSecond(GregorianCalendar date1, GregorianCalendar date2){
        if (date1.get(Calendar.YEAR) <= date2.get(Calendar.YEAR)){
            return true;
        } else{
            if (date1.get(Calendar.MONTH) <= date2.get(Calendar.MONTH)){
                return true;
            } else {
                if (date1.get(Calendar.DAY_OF_MONTH) <= date2.get(Calendar.DAY_OF_MONTH)){
                    return true;
                }
                else {
                    return false;
                }
            }
        }
    }


    public class SliderListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
                sliderValue.setText(""+percentageSlider.getValue());
        }
    }

    public class SubmitListener implements ActionListener{
        private Pattern pattern;

        @Override
        public void actionPerformed(ActionEvent e) {
            // Vérifier que la première date est bien entrée
            if (!pattern.matches("^\\d{2}-\\d{2}-[1-2]{1}\\d{3}$", startDateSpinnerEditor.getTextField().getText())){
                JOptionPane.showMessageDialog(null, "The start date must respect the format dd-mm-yyyy\nAnd it must be a credible date",
                        "Error date format", JOptionPane.ERROR_MESSAGE);
            } else {
                // Vérification pour la seconde date
                if (!pattern.matches("^\\d{2}-\\d{2}-[1-2]{1}\\d{3}$", endDateSpinnerEditor.getTextField().getText())) {
                    JOptionPane.showMessageDialog(null, "The end date must respect the format dd-mm-yyyy\nAnd it must be a credible date",
                            "Error date format", JOptionPane.ERROR_MESSAGE);

                } else {
                    GregorianCalendar startDate;
                    startDate = convertJspinnerEditortoDate(startDateSpinnerEditor);
                    GregorianCalendar endDate;
                    endDate = convertJspinnerEditortoDate(endDateSpinnerEditor);
                    // vérifie que la end date est > que la  start date
                    if (firstDateInferiorToSecond(startDate, endDate)){
                        String lastStartDate = startDate.get(Calendar.YEAR) + "-"+startDate.get(Calendar.MONTH)+"-"+startDate.get(Calendar.DAY_OF_MONTH);
                        String lastEndDate = endDate.get(Calendar.YEAR) + "-"+endDate.get(Calendar.MONTH)+"-"+endDate.get(Calendar.DAY_OF_MONTH);

                        try {
                            controller.addPromotion(percentageSlider.getValue(), lastStartDate, lastEndDate, wordingItemLabel.getText());
                        } catch (AddPromotionException exception){
                            JOptionPane.showMessageDialog(null, exception.getMessage(), "SQLError", JOptionPane.ERROR_MESSAGE);
                        } catch (SingletonConnectionException exception){
                            JOptionPane.showMessageDialog(null, exception.getErrorMessage(), exception.getErrorTitle(), JOptionPane.ERROR_MESSAGE);
                        }
                        container.removeAll();
                        container.add(new PromotionsByItemPanel(container, wordingItemLabel.getText()));
                        container.revalidate();
                        container.repaint();
                    } else {
                        JOptionPane.showMessageDialog(null, "The start date must inferior to the end date",
                                "Error date", JOptionPane.ERROR_MESSAGE);
                    }


                }
            }
        }
    }
}
