package controller;

import exception.DBException;
import exception.SingletonConnectionException;
import model.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface DataAccess {
    // Workflow / WorkflowType
    public ArrayList<WorkflowType> getAllWorkflowTypes() throws DBException, SingletonConnectionException;
    public ArrayList<Integer> getAllWorkflow() throws DBException, SingletonConnectionException;

    // Document / DocumentType
    public ArrayList<DocumentType> getAllDocumentTypes() throws DBException, SingletonConnectionException;
    public ArrayList<DocumentByWorkflowType> getDocuments(Integer workflowNumber) throws DBException, SingletonConnectionException;

    // CRUD
    public ArrayList<Document> getAllDocuments() throws DBException, SingletonConnectionException;
    public void addDocument(Document document) throws DBException, SingletonConnectionException;
    public void deleteDocument(Integer id) throws DBException, SingletonConnectionException;
    public void modifyDocument(Document document) throws DBException, SingletonConnectionException;

    // Item
    public ArrayList<ResearchByPromoModel> getResearchByPromo(int littleValue, int bigValue) throws DBException, SingletonConnectionException;

    // Business Task
    public ArrayList<BusinessTaskModel> getBusinessTaskInformation(String wordingItemReceive) throws DBException, SingletonConnectionException;
    public ArrayList<String> getAllItemsWording() throws DBException, SingletonConnectionException;
    public void addPromotion(int percentage, String startDate, String endDate, String itemWording) throws  DBException, SingletonConnectionException;


    // Pointing
    public ArrayList<PointingBetweenDates> getPointingBetweenDates(GregorianCalendar firstDate, GregorianCalendar secondDate) throws  DBException, SingletonConnectionException;
}
