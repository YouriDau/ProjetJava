package userInterface;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class DocumentListPanel extends JPanel{
    private JButton addDocument;
    private JButton modifyDocument;
    private JButton deleteDocument;
    private GridBagConstraints layoutConstraints;

    public DocumentListPanel() {
        this.setLayout(new GridBagLayout());
        layoutConstraints = new GridBagConstraints();
        addDocument = new JButton("Add a document");
        modifyDocument = new JButton("Modify a document");
        deleteDocument = new JButton("Delete a document");

        layoutConstraints.fill = GridBagConstraints.BOTH;
        layoutConstraints.insets = new Insets(0, 0, 10, 10);
        layoutConstraints.gridwidth = 2;
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 0;
        this.add(addDocument, layoutConstraints);

        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 1;
        this.add(modifyDocument, layoutConstraints);

        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 2;
        this.add(deleteDocument, layoutConstraints);
    }
}
