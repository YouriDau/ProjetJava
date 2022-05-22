package dataAccess;

import controller.DataAccess;
import exception.DBException;
import exception.SingletonConnectionException;
import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
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
        } catch (SQLException exception) {
            throw new DBException(exception.getMessage());
        }
        return workflowNumbers;
    }

    @Override
    public ArrayList<WorkflowType> getAllWorkflowTypes() throws DBException, SingletonConnectionException {
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
        } catch (SQLException exception) {
            throw new DBException(exception.getMessage());
        }

        return workflowTypes;
    }

    @Override
    public ArrayList<DocumentType> getAllDocumentTypes() throws DBException, SingletonConnectionException {
        DocumentType type;
        ArrayList<DocumentType> types = new ArrayList<>();

        String sqlInstruction = "SELECT * FROM document_type;";
        Connection connection = SingletonConnection.getInstance();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery();

            while (data.next()) {
                type = new DocumentType(data.getInt("id"), data.getString("wording"));
                types.add(type);
            }
        } catch (SQLException exception) {
            throw new DBException(exception.getMessage());
        }
        return types;
    }

    @Override
    public ArrayList<DocumentByWorkflowType> getDocuments(Integer workflowNumber) throws DBException, SingletonConnectionException {
        Integer documentNumber;
        Integer workflowId;
        Double creditLimit;
        String documentType;
        GregorianCalendar creationDate;
        java.sql.Date sqlDate;

        DocumentByWorkflowType document;
        ArrayList<DocumentByWorkflowType> documents = new ArrayList<>();

        String sqlInstruction = "SELECT d.number, d.creation_date, d.credit_limit, w.id, dt.wording " +
                "FROM document d " +
                "INNER JOIN workflow w " +
                "ON (d.process = w.id) " +
                "INNER JOIN document_type dt " +
                "ON (d.type = dt.id) " +
                "WHERE d.process = ?;";
        Connection connection = SingletonConnection.getInstance();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1, workflowNumber);
            ResultSet data = preparedStatement.executeQuery();

            while (data.next()) {
                creationDate = new GregorianCalendar();
                documentNumber = data.getInt("d.number");
                sqlDate = data.getDate("d.creation_date");
                workflowId = data.getInt("w.id");
                documentType = data.getString("dt.wording");

                creationDate.setTime(sqlDate);
                document = new DocumentByWorkflowType(documentNumber, workflowId, creationDate, documentType);

                creditLimit = data.getDouble("d.credit_limit");
                if (!data.wasNull()) {
                    document.setCreditLimit(creditLimit);
                }

                documents.add(document);
            }
        } catch (SQLException exception) {
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
        Boolean updateTheStock;

        Document document;
        ArrayList<Document> documents = new ArrayList<>();

        String sqlInstruction = "SELECT * FROM document;";
        Connection connection = SingletonConnection.getInstance();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery();

            while (data.next()) {
                date = new GregorianCalendar();

                number = data.getInt("number");
                sqlDate = data.getDate("creation_date");
                date.setTime(sqlDate);
                documentType = data.getInt("type");
                processNumber = data.getInt("process");
                updateTheStock = data.getBoolean("update_the_stock");

                document = new Document(number, date, documentType, processNumber, updateTheStock);

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
        } catch (SQLException exception) {
            throw new DBException(exception.getMessage());
        }
        return documents;
    }

    @Override
    public void addDocument(Document document) throws DBException, SingletonConnectionException {
        int lastDocumentId;
        ResultSet data;
        String sqlInstruction = "INSERT INTO document(creation_date, type, process, update_the_stock) " +
                "VALUES(?, ?, ?, ?);";
        Connection connection = SingletonConnection.getInstance();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            preparedStatement.setDate(1, new java.sql.Date(document.getCreationDate().getTimeInMillis()));
            preparedStatement.setInt(2, document.getType());
            preparedStatement.setInt(3, document.getworkflowNumber());
            preparedStatement.setBoolean(4, document.getUpdateTheStock());

            preparedStatement.executeUpdate();

            sqlInstruction = "SELECT LAST_INSERT_ID() as last_id FROM document";
            preparedStatement = connection.prepareStatement(sqlInstruction);
            data = preparedStatement.executeQuery();

            data.next();
            lastDocumentId = data.getInt("last_id");

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
        } catch (SQLException exception) {
            throw new DBException(exception.getMessage());
        }
    }

    @Override
    public void deleteDocument(Integer id) throws DBException, SingletonConnectionException {
        String sqlInstruction = "DELETE FROM document " +
                "WHERE number = ?";

        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new DBException(exception.getMessage());
        }
    }

    @Override
    public void modifyDocument(Document document) throws DBException, SingletonConnectionException {
        String sqlInstruction = "UPDATE document " +
                "SET payment_condition = ?, credit_limit = ?, type = ?, process = ?, update_the_stock = ? " +
                "WHERE number = ?";
        Connection connection = SingletonConnection.getInstance();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            if (document.getPaymentCondition() != null) {
                preparedStatement.setString(1, document.getPaymentCondition());
            } else {
                preparedStatement.setNull(1, Types.VARCHAR);
            }

            if (document.getCreditLimit() != null) {
                preparedStatement.setDouble(2, document.getCreditLimit());
            } else {
                preparedStatement.setNull(2, Types.DECIMAL);
            }

            preparedStatement.setInt(3, document.getType());
            preparedStatement.setInt(4, document.getworkflowNumber());
            preparedStatement.setBoolean(5, document.getUpdateTheStock());
            preparedStatement.setInt(6, document.getNumber());

            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new DBException(exception.getMessage());
        }
    }

    @Override
    public ArrayList<ResearchByPromoModel> getResearchByPromo(int littleValue, int bigValue) throws DBException, SingletonConnectionException {
        // valeurs a recuperer dans la base de données
        Integer percentage;
        String wordingItem;
        Double lastUnitPrice;
        // tableau a initialiser avec des ResearchByPromo
        ArrayList<ResearchByPromoModel> researchByPromoModels = new ArrayList<>();
        // ResearchByPromo qui ira dans l'arrayList
        ResearchByPromoModel researchByPromoModel;
        // requête SQL
        String SQLInstruction = "SELECT p.percentage, i.wording, d.unit_price " +
                                "FROM promotion p " +
                                "INNER JOIN item i " +
                                "ON p.item = i.id " +
                                "INNER JOIN detail d " +
                                "ON i.id = d.item " +
                                "WHERE percentage BETWEEN ? AND ?" +
                                " AND d.code IN (SELECT max(d.code) FROM detail d GROUP BY (d.item));";

        Connection connection = SingletonConnection.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLInstruction);
            preparedStatement.setInt(1,littleValue);
            preparedStatement.setInt(2,bigValue);
            ResultSet data = preparedStatement.executeQuery();
            while (data.next()) {
                percentage = data.getInt("percentage");
                wordingItem = data.getString("wording");
                lastUnitPrice = data.getDouble("unit_price");
                researchByPromoModel = new ResearchByPromoModel(percentage, wordingItem, lastUnitPrice);
                researchByPromoModels.add(researchByPromoModel);
            }

        } catch (SQLException exception) {
            throw new DBException(exception.getMessage());
        }
        return researchByPromoModels;
    }

    @Override
    public ArrayList<BusinessTaskModel> getBusinessTaskInformation(String wordingItemReceive) throws DBException, SingletonConnectionException {
        // valeurs a récuperer dans la BD
        String wordingItem;
        Integer percentagePromotion;
        Integer percentageId;
        GregorianCalendar startDate ;
        GregorianCalendar endDate ;
        Integer detailQuantity;
        // tableau a initialiser
        ArrayList<BusinessTaskModel> businessTaskModels = new ArrayList<>();
        // valeur qui s'ajoutera a l'array list
        BusinessTaskModel businessTaskModel;
        // Requête SQL
        String SQLInstruction = "SELECT i.wording,p.percentage, p.id, p.start_date, p.end_date,SUM(d.quantity) as quantity FROM promotion p " +
                "                INNER JOIN item i ON p.item = i.id " +
                "                INNER JOIN detail d ON i.id = d.item " +
                "                INNER JOIN document doc ON d.document = doc.number " +
                "                INNER JOIN document_type dt on doc.type = dt.id " +
                "                WHERE dt.wording = 'Bon de vente' " +
                "                AND i.wording = ?" +
                "                AND doc.creation_date BETWEEN p.start_date AND p.end_date " +
                "                GROUP BY  p.id;";
        // création de la connexion
        Connection connection = SingletonConnection.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLInstruction);
            preparedStatement.setString(1,wordingItemReceive);
            ResultSet data = preparedStatement.executeQuery();

            while (data.next()){
                startDate = new GregorianCalendar();
                endDate = new GregorianCalendar();
                wordingItem = data.getString("wording");
                percentagePromotion = data.getInt("percentage");
                percentageId = data.getInt("id");
                startDate.setTime(data.getDate("start_date"));
                endDate.setTime(data.getDate("end_date"));
                detailQuantity = data.getInt("quantity");
                businessTaskModel = new BusinessTaskModel(wordingItem, percentagePromotion, percentageId, startDate, endDate, detailQuantity);
                businessTaskModels.add(businessTaskModel);
            }

        } catch (SQLException sqlException) {
            throw new DBException(sqlException.getMessage());
        }


        return businessTaskModels;
    }

    public ArrayList<String> getAllItemsWording() throws DBException, SingletonConnectionException {
        // Valeur a récupérer dans la BD
        String wordingItem;
        // Tableau a initialiser
        ArrayList<String> wordingItems = new ArrayList<>();
        // Requête SQL
        String SQLInstruction = "Select wording FROM item;";
        // établissement de la connexion
        Connection connection = SingletonConnection.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLInstruction);
            ResultSet data = preparedStatement.executeQuery();
            while (data.next()) {
                wordingItem = data.getString("wording");
                wordingItems.add(wordingItem);
            }
        } catch (SQLException sqlException) {
            throw new DBException(sqlException.getMessage());
        }
            return wordingItems;
    }

    public void addPromotion(int percentage, String startDate, String endDate, String itemWording) throws  DBException, SingletonConnectionException{
        String SQLInstruction = "INSERT INTO promotion(percentage, start_date, end_date, item) " +
                " VALUES ( ? , ? , ? , (SELECT id FROM item WHERE wording = ?));";
        Connection connection = SingletonConnection.getInstance();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLInstruction);
            preparedStatement.setInt(1,percentage);
            preparedStatement.setString(2, startDate);
            preparedStatement.setString(3, endDate);
            preparedStatement.setString(4, itemWording);

            preparedStatement.executeUpdate();
        } catch (SQLException exception){
            throw new DBException(exception.getMessage());
        }

    }

    @Override
    public ArrayList<PointingBetweenDates> getPointingBetweenDates(GregorianCalendar firstDate, GregorianCalendar secondDate) throws  DBException, SingletonConnectionException {
        String lastName;
        String firstName; // can be null
        String personType;
        GregorianCalendar pointingDate;
        GregorianCalendar pointingHour;
        String pointingType;
        java.sql.Date firstSQLDate;
        java.sql.Date secondSQLDate;

        PointingBetweenDates pointing;
        ArrayList<PointingBetweenDates> pointings = new ArrayList<>();

        String sqlInstruction = "SELECT po.date, po.type, pe.last_name, pe.first_name, pet.wording " +
                "FROM pointing po " +
                "INNER JOIN person pe " +
                "ON (po.employee = pe.number) " +
                "INNER JOIN person_type pet " +
                "ON (pe.type = pet.id) " +
                "WHERE po.date " +
                "BETWEEN ? AND ?";
        Connection connection = SingletonConnection.getInstance();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            firstSQLDate = new java.sql.Date(firstDate.getTimeInMillis());
            secondSQLDate = new java.sql.Date(secondDate.getTimeInMillis());

            preparedStatement.setDate(1, firstSQLDate);
            preparedStatement.setDate(2, secondSQLDate);

            ResultSet data = preparedStatement.executeQuery();
            while (data.next()) {
                pointingDate = new GregorianCalendar();
                pointingHour = new GregorianCalendar();

                lastName = data.getString("last_name");
                personType = data.getString("wording");
                pointingDate.setTime(data.getDate("date"));
                pointingType = data.getString("type");

                pointingHour.setTime(data.getTime("po.date"));
                pointingDate.set(Calendar.HOUR, pointingHour.get(Calendar.HOUR));
                pointingDate.set(Calendar.MINUTE, pointingHour.get(Calendar.MINUTE));

                pointing = new PointingBetweenDates(lastName, personType, pointingDate, pointingType);

                firstName = data.getString("first_name");
                if (!data.wasNull()) {
                    pointing.setFirstName(firstName);
                }
                pointings.add(pointing);
            }
        } catch (SQLException exception) {
            throw new DBException(exception.getMessage());
        }
        return pointings;
    }
}



