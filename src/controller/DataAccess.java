package controller;

import exception.*;
import model.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface DataAccess {

    // Workflow/workflowtype
    public ArrayList<WorkflowType> getAllWorkflowTypes() throws SingletonConnectionException;
    public ArrayList<Integer> getAllWorkflow() throws SingletonConnectionException;

    // Document / DocumentType
    public ArrayList<DocumentType> getAllDocumentTypes() throws SingletonConnectionException;
    public ArrayList<DocumentByWorkflowType> getDocuments(Integer workflowNumber) throws SingletonConnectionException;

    // CRUD
    public ArrayList<Document> getAllDocuments() throws SingletonConnectionException;
    public void addDocument(Document document) throws AddDocumentException, SingletonConnectionException;
    public void deleteDocument(Integer id) throws DeleteDocumentException, SingletonConnectionException;
    public void modifyDocument(Document document) throws ModifyDocumentException, SingletonConnectionException;

    // Item
    public ArrayList<ResearchByPromoModel> getResearchByPromo(int littleValue, int bigValue) throws SingletonConnectionException;

    // Business Task
    public ArrayList<BusinessTaskModel> getBusinessTaskInformation(String wordingItemReceive) throws SingletonConnectionException;
    public ArrayList<String> getAllItemsWording() throws SingletonConnectionException;
    public void addPromotion(int percentage, String startDate, String endDate, String itemWording) throws  AddPromotionException, SingletonConnectionException;


    // Pointing
    public ArrayList<PointingBetweenDates> getPointingBetweenDates(GregorianCalendar firstDate, GregorianCalendar secondDate) throws SingletonConnectionException;
}
