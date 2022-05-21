package userInterface;

import javax.swing.*;
import java.awt.*;

public class ItemSelectionPanel extends JPanel {
    private Container container;
    private GridBagConstraints layoutconstraints;
    private JLabel label;
    private JComboBox itemSelection;
    public ItemSelectionPanel(Container container){
        setContainer(container);
        this.setLayout( new GridBagLayout());
        layoutconstraints = new GridBagConstraints();
        label = new JLabel("Choose an item :");



    }

    public void setContainer(Container container) {
        this.container = container;
    }
}
