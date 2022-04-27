package dataAccess;

import controller.DataAccess;
import controller.DocumentDataAccess;
import model.Document;
import model.WorkflowType;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class DBAccess implements DataAccess {

    public DBAccess()  {

    }

    @Override
    public ArrayList<WorkflowType> getAllWorkflowTypes() {
        Integer number;
        String wording;
        WorkflowType workflowType;
        ArrayList<WorkflowType> workflowTypes = new ArrayList<>();
        String sqlInstruction = "SELECT * FROM workflow_type";
        Connection connection = SingletonConnection.getInstance();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery();

            while (data.next()) {
                number = data.getInt("number");
                wording = data.getString("wording");
                workflowType = new WorkflowType(number, wording);
                workflowTypes.add(workflowType);
            }
        }
        catch (SQLException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        }

        return workflowTypes;
    }

    @Override
    public ArrayList<Document> getDocuments(String workflowType) {
        Integer number;
        GregorianCalendar date;
        java.sql.Date sqlDate;
        String paymentCondition;
        Integer documentType;
        Integer workflowNumber;
        Double creditLimit;
        Document document;

        ArrayList<Document> documents = new ArrayList<>();
        String sqlInstruction = "SELECT * FROM document " +
                                "WHERE process = " +
                                "(SELECT number FROM workflow" +
                                "WHERE workflow_type = ?)";

        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery();
            date = new GregorianCalendar();

            while (data.next()) {
                number = data.getInt("number");
                sqlDate = data.getDate("creation_date");
                date.setTime(sqlDate);
                documentType = data.getInt("document_type");
                workflowNumber = data.getInt("process");

                document = new Document(number, date, documentType, workflowNumber);



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
