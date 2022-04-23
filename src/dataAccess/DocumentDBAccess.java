package dataAccess;

import model.Document;
import model.WorkflowType;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class DocumentDBAccess implements DocumentDataAccess {
    @Override
    public ArrayList<Document> getDocuments(String workflowType) {
        Integer number;
        Document document;
        String paymentCondition;
        Double creditLimit;
        Integer documentType;
        Integer workflowNumber;

        ArrayList<Document> documents = new ArrayList<>();
        String sqlInstruction = "SELECT * FROM document " +
                                "WHERE process = " +
                                "(SELECT number FROM workflow" +
                                "WHERE workflow_type = ?)";

        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery();

            while (data.next()) {
                number = data.getInt("number");

                //documents.add(document);
            }
            connection.close();
        }
        catch (SQLException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        }
        return documents;
    }
}
