package dataAccess;

import controller.DataAccess;
import exception.DBException;
import exception.SingletonConnectionException;
import model.Document;
import model.DocumentType;
import model.WorkflowType;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class DBAccess implements DataAccess {

    public DBAccess() throws DBException, SingletonConnectionException {

    }

    @Override
    public ArrayList<Integer> getAllWorkflow() throws DBException, SingletonConnectionException {
        Integer id;
        ArrayList<Integer> workflowNumbers = new ArrayList<>();
        String sqlInstruction = "SELECT id FROM workflow;";
        Connection connection = SingletonConnection.getInstance();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery();

            while (data.next()) {
                id = data.getInt("id");
                workflowNumbers.add(id);
            }
        }
        catch (SQLException exception) {
            throw new DBException(exception.getMessage());
        }
        return workflowNumbers;
    }

    @Override
    public ArrayList<WorkflowType> getAllWorkflowTypes()  throws DBException, SingletonConnectionException{
        Integer id;
        String wording;
        WorkflowType workflowType;
        ArrayList<WorkflowType> workflowTypes = new ArrayList<>();
        String sqlInstruction = "SELECT * FROM workflow_type;";
        Connection connection = SingletonConnection.getInstance();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery();

            while (data.next()) {
                id = data.getInt("id");
                wording = data.getString("wording");
                workflowType = new WorkflowType(id, wording);
                workflowTypes.add(workflowType);
            }
        }
        catch (SQLException exception) {
            throw new DBException(exception.getMessage());
        }

        return workflowTypes;
    }

    @Override
    public ArrayList<Document> getDocuments(Integer workflowNumber) throws DBException, SingletonConnectionException {
        Integer number;
        GregorianCalendar date;
        java.sql.Date sqlDate;
        String paymentCondition;
        Integer documentType;
        Integer processNumber;
        Double creditLimit;
        Document document;

        ArrayList<Document> documents = new ArrayList<>();
        String sqlInstruction = "SELECT * " +
                                "FROM document WHERE process IN " +
                                "(SELECT id " +
                                "FROM workflow WHERE type = ?);";
        Connection connection = SingletonConnection.getInstance();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1, workflowNumber);
            ResultSet data = preparedStatement.executeQuery();
            date = new GregorianCalendar();

            while (data.next()) {
                number = data.getInt("number");
                sqlDate = data.getDate("creation_date");
                date.setTime(sqlDate);
                documentType = data.getInt("type");
                processNumber = data.getInt("process");

                document = new Document(number, date, documentType, processNumber);

                paymentCondition = data.getString("payment_condition");
                if (!data.wasNull()) {
                    document.setPaymentCondition(paymentCondition);
                }

                creditLimit = data.getDouble("credit_limit");
                if (!data.wasNull()) {
                    document.setCreditLimit(creditLimit);
                }

                documents.add(document);
            }
        }
        catch (SQLException exception) {
            throw new DBException(exception.getMessage());
        }
        return documents;
    }

    @Override
    public ArrayList<Document> getAllDocuments() throws DBException, SingletonConnectionException {
        Integer number;
        GregorianCalendar date;
        java.sql.Date sqlDate;
        String paymentCondition;
        Integer documentType;
        Integer processNumber;
        Double creditLimit;
        Document document;

        ArrayList<Document> documents = new ArrayList<>();
        String sqlInstruction = "SELECT * FROM document;";
        Connection connection = SingletonConnection.getInstance();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery();
            date = new GregorianCalendar();

            while (data.next()) {
                number = data.getInt("number");
                sqlDate = data.getDate("creation_date");
                date.setTime(sqlDate);
                documentType = data.getInt("type");
                processNumber = data.getInt("process");

                document = new Document(number, date, documentType, processNumber);

                paymentCondition = data.getString("payment_condition");
                if (!data.wasNull()) {
                    document.setPaymentCondition(paymentCondition);
                }

                creditLimit = data.getDouble("credit_limit");
                if (!data.wasNull()) {
                    document.setCreditLimit(creditLimit);
                }

                documents.add(document);
            }
        }
        catch (SQLException exception) {
            throw new DBException(exception.getMessage());
        }
        return documents;
    }

    @Override
    public ArrayList<DocumentType> getAllDocumentTypes() throws  DBException, SingletonConnectionException {
        Integer id;
        String wording;
        DocumentType documentType;
        ArrayList<DocumentType> documentTypes = new ArrayList<>();
        String sqlInstruction = "SELECT * FROM document_type;";
        Connection connection = SingletonConnection.getInstance();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery();

            while (data.next()) {
                id = data.getInt("id");
                wording = data.getString("wording");
                documentType = new DocumentType(id, wording);
                documentTypes.add(documentType);
            }
        }
        catch (SQLException exception) {
            throw new DBException(exception.getMessage());
        }

        return documentTypes;
    }

    @Override
    public void addDocument(Document document) throws  DBException, SingletonConnectionException {
        int lastDocumentId;
        ResultSet data;
        String sqlInstruction = "INSERT INTO document(creation_date, type, process)" +
                                "VALUES(?, ?, ?);";
        Connection connection = SingletonConnection.getInstance();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            preparedStatement.setDate(1, new java.sql.Date(document.getCreationDate().getTimeInMillis()));
            preparedStatement.setInt(2, document.getType());
            preparedStatement.setInt(3, document.getworkflowNumber());

            preparedStatement.executeUpdate();

            sqlInstruction = "SELECT LAST_INSERT_ID() as id FROM document";
            preparedStatement = connection.prepareStatement(sqlInstruction);
            data = preparedStatement.executeQuery();

            data.next();
            lastDocumentId = data.getInt("id");

            if (document.getPaymentCondition() != null) {
                sqlInstruction = "UPDATE document SET payment_condition = ?" +
                                 "WHERE number = ?";
                preparedStatement = connection.prepareStatement(sqlInstruction);
                preparedStatement.setString(1, document.getPaymentCondition());
                preparedStatement.setString(2, Integer.toString(lastDocumentId));
                preparedStatement.executeUpdate();
            }

            if (document.getCreditLimit() != null) {
                sqlInstruction = "UPDATE document SET credit_limit = ?" +
                                 "WHERE number = ?";
                preparedStatement = connection.prepareStatement(sqlInstruction);
                preparedStatement.setDouble(1, document.getCreditLimit());
                preparedStatement.setString(2, Integer.toString(lastDocumentId));
                preparedStatement.executeUpdate();
            }
        }
        catch (SQLException exception) {
            throw new DBException(exception.getMessage());
        }
    }
}
