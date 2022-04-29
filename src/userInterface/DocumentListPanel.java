package userInterface;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DocumentListPanel extends JPanel{
    private JTable table;
    // ____________________
    // Pour le test

    private GregorianCalendar today;
    private String[] columnTitles = {"Number",
                                     "Creation_date",
                                     "Payment_condition",
                                     "credit_limit",
                                     "document_type",
                                     "process"};
    private Object[][] data = {
            {1, today.get(Calendar.DAY_OF_MONTH) +"-"+ (today.get(Calendar.MONTH)+1) +"-"+ today.get(Calendar.YEAR), "condition 1", 350.5, "Achat process", "Facture"},
            {2, today.get(Calendar.DAY_OF_MONTH) +"-"+ (today.get(Calendar.MONTH)+1) +"-"+ today.get(Calendar.YEAR), "condition 2", 650.2, "Achat process", "Facture"},
            {3, today.get(Calendar.DAY_OF_MONTH) +"-"+ (today.get(Calendar.MONTH)+1) +"-"+ today.get(Calendar.YEAR), "condition 3", 100.0, "Achat process", "Facture"}
    };

    // ____________________

    public DocumentListPanel() {
        this.setLayout(new BorderLayout());
        today = new GregorianCalendar();
        table = new JTable(data, columnTitles);
        table.setEnabled(false);

        today = new GregorianCalendar();

        this.add(table.getTableHeader(), BorderLayout.PAGE_START);
        this.add(table, BorderLayout.CENTER);
    }
}
