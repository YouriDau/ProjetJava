package dataAccess;

import controller.DataAccess;
import exception.DBException;
import exception.SingletonConnectionException;
import model.*;

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
    public ArrayList<Detail> getDetails(ArrayList<Item> items) throws DBException, SingletonConnectionException{
        ArrayList<Detail> details= new ArrayList<>();
        for (Item item : items){
            details.add(getDetail(item.getId()));

        }
        return details;
    }
    @Override
    public Detail getDetail(Integer itemIdReceive) throws DBException, SingletonConnectionException{
        // colonnes de la base de données
        Integer code;
        Double unit_price;
        Integer itemId;
                /*Integer quantity;
                Double vatRate;
                Integer backOrder;
                Integer documentId;
                Integer lotId;*/
        // Variable a initialiser
        Detail detail;
        // etablissement de la connexion
        Connection connection = SingletonConnection.getInstance();
        // Requête SQL
        String sqlInstruction =
                "SELECT d.code, d.unit_price, d.item "+
                "FROM detail d INNER JOIN item i ON d.item = i.id "+
                "and d.code in ("+
                        "SELECT max(d.code) "+
                        "FROM detail d "+
                        "GROUP BY (d.item) "+
                ") "+
                "where i.id = "+itemIdReceive+";";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery();
            //
            data.next();
            //
            code = data.getInt("code");
            unit_price = data.getDouble("unit_price");
            itemId = data.getInt("item");
                    /*quantity = data.getInt("quantity");
                    vatRate = data.getDouble("vat_rate");
                    backOrder = data.getInt("back_order");
                    documentId = data.getInt("document");
                    lotId = data.getInt("lot");*/

            //detail = new Detail(code, unit_price, quantity, vatRate, backOrder, documentId, itemId, lotId);
            detail = new Detail(code, unit_price, itemId);

        }
        catch (SQLException exception){
            throw new DBException(exception.getMessage());
        }

        return detail;
    }
    @Override
    public ArrayList<Item> getItems(ArrayList<Promotion> promotions) throws DBException, SingletonConnectionException{
        ArrayList<Item> items = new ArrayList<>();
        for (Promotion promotion : promotions){
            items.add(getItem(promotion.getId()));
        }
        return items;
    }
    @Override
    public Item getItem(Integer idPromotion) throws DBException, SingletonConnectionException{
        // valeurs a récupérer
        Integer id;
        String wording;
        Item item;
        // initialisation de la connection
        Connection connection = SingletonConnection.getInstance();
        // requete SQL
        String sqlInstruction = "SELECT i.id, i.wording " +
                "FROM item i inner join promotion p on i.id = p.item " +
                "WHERE p.id = + "+ idPromotion + ";";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery();
            //
            data.next();
            id = data.getInt("id");
            wording = data.getString("wording");
            item = new Item(id,wording);
        }catch (SQLException exception){
            throw new DBException(exception.getMessage());
        }
        return item;
    }
    @Override
    public ArrayList<Promotion> getPromotions(int littleValue, int bigValue) throws DBException, SingletonConnectionException{
        // colonnes
        Integer id;
        Integer percentage;
        GregorianCalendar startDate;
        GregorianCalendar endDate;
        Integer itemId;
        // variable a initialiser
        ArrayList<Promotion> promotions = new ArrayList<>();
        // Requête SQL
        String sqlInstruction =
                "SELECT * " +
                "FROM promotion " +
                "WHERE percentage BETWEEN "+littleValue+" AND "+ bigValue+";";
        Connection connection = SingletonConnection.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery();
            while (data.next()){
                id = data.getInt("id");
                percentage = data.getInt("percentage");
                startDate = new GregorianCalendar();
                startDate.setTime(data.getDate("start_date"));
                endDate = new GregorianCalendar();
                endDate.setTime(data.getDate("end_date"));
                itemId = data.getInt("item");
                promotions.add(new Promotion(id,percentage,startDate,endDate,itemId));
            }
        } catch (SQLException exception){
            throw new DBException(exception.getMessage());
        }

        return promotions;
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

            while (data.next()) {
                date = new GregorianCalendar();

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
    @Override
    public void deleteDocument(int id) throws DBException, SingletonConnectionException {
        String sqlInstruction = "DELETE FROM document " +
                                "WHERE number = ?";
        Connection connection = SingletonConnection.getInstance();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1, id);
            int nbRowEffected = preparedStatement.executeUpdate();
        }
        catch(SQLException exception) {
            throw new DBException(exception.getMessage());
        }
    }
    @Override
    public void modifyDocument(Document document) throws DBException, SingletonConnectionException {
        String sqlInstruction = "UPDATE document " +
                                "SET payment_condition = ?, credit_limit = ?, type = ?, process = ? " +
                                "WHERE number = ?";
        Connection connection = SingletonConnection.getInstance();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);

            if (document.getPaymentCondition() != null){
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
            preparedStatement.setInt(5, document.getNumber());

            preparedStatement.executeUpdate();
        }
        catch (SQLException exception) {
            throw new DBException(exception.getMessage());
        }
    }
}
