package userInterface;

import javax.swing.*;
import java.awt.*;

public class ItemPanel extends JPanel {
    private JLabel firstNumberLabel;
    private JLabel secondNumberLabel;
    private JSpinner firstNumber;
    private JSpinner secondNumber;
    private SpinnerNumberModel firstNumberModel;
    private SpinnerNumberModel secondNumberModel;
    private GridBagConstraints layoutConstraints;
    private JButton submit;
    private JButton back;
    private JButton reset;

    public ItemPanel () {
        this.setLayout(new GridBagLayout());
        layoutConstraints = new GridBagConstraints();
        firstNumberLabel = new JLabel("Choose the min number :");
        firstNumberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        secondNumberLabel = new JLabel("Choose the max number :");
        secondNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);

        firstNumberModel = new SpinnerNumberModel(0, 0, 100, 1);
        firstNumber = new JSpinner(firstNumberModel);

        secondNumberModel = new SpinnerNumberModel(0, 0, 100, 1);
        secondNumber = new JSpinner(secondNumberModel);

        submit = new JButton("Submit");
        back = new JButton("Back");
        reset = new JButton("Reset");

        layoutConstraints.fill = GridBagConstraints.BOTH;
        layoutConstraints.insets = new Insets(0, 0, 10, 10);
        layoutConstraints.gridwidth = 2;
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 0;
        this.add(firstNumberLabel, layoutConstraints);

        layoutConstraints.gridwidth = 1;
        layoutConstraints.gridx = 2;
        layoutConstraints.gridy = 0;
        this.add(firstNumber, layoutConstraints);

        layoutConstraints.gridwidth = 2;
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 1;
        this.add(secondNumberLabel, layoutConstraints);

        layoutConstraints.gridwidth = 1;
        layoutConstraints.gridx = 2;
        layoutConstraints.gridy = 1;
        this.add(secondNumber, layoutConstraints);

        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 2;
        this.add(submit, layoutConstraints);

        layoutConstraints.gridx = 1;
        layoutConstraints.gridy = 2;
        this.add(back, layoutConstraints);

        layoutConstraints.gridx = 2;
        layoutConstraints.gridy = 2;
        this.add(reset, layoutConstraints);
    }
}
