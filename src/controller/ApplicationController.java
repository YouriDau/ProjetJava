package controller;

import business.*;
import exception.AddDocumentException;
import exception.DBException;
import exception.SingletonConnectionException;
import model.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ApplicationController {
    private WorkflowManager workflowManager;
    private DocumentManager documentManager;
    private ItemManager itemManager;
    private BusinessTaskManager businessTaskManager;
    private PointingManager pointingManager;

    public ApplicationController() throws DBException, SingletonConnectionException {
        setWorkflowManager(new WorkflowManager());
        setDocumentManager(new DocumentManager());
        setItemManager(new ItemManager());
        setBusinessTaskManager(new BusinessTaskManager());
        setPointingManager(new PointingManager());
    }

    public void setWorkflowManager(WorkflowManager workflowManager) {
        this.workflowManager = workflowManager;
    }

    public void setDocumentManager(DocumentManager documentManager) {
        this.documentManager = documentManager;
    }

    public void setItemManager(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    public void setBusinessTaskManager(BusinessTaskManager businessTaskManager) {
        this.businessTaskManager = businessTaskManager;
    }

    public void setPointingManager(PointingManager pointingManager) {
        this.pointingManager = pointingManager;
    }

    public ArrayList<WorkflowType> getAllWorkflowTypes() throws DBException, SingletonConnectionException {
        return workflowManager.getAllWorkflowTypes();
    }

    public ArrayList<DocumentByWorkflowType> getDocuments(Integer workflowNumber) throws DBException, SingletonConnectionException {
        return  documentManager.getDocuments(workflowNumber);
    }

    public ArrayList<DocumentType> getAllDocumentTypes() throws DBException, SingletonConnectionException {
        return documentManager.getAllDocumentTypes();
    }

    public ArrayList<Document> getAllDocuments() throws DBException, SingletonConnectionException {
        return documentManager.getAllDocuments();
    }

    public ArrayList<Integer> getAllWorkflow() throws DBException, SingletonConnectionException {
        return workflowManager.getAllWorkflow();
    }

    public void addDocument(Document document) throws AddDocumentException, SingletonConnectionException {
        documentManager.addDocument(document);
    }

    public void deleteDocument(int id) throws DBException, SingletonConnectionException {
        documentManager.deleteDocument(id);
    }

    public void modifyDocument(Document document) throws DBException, SingletonConnectionException {
        documentManager.modifyDocument(document);
    }

    public ArrayList<ResearchByPromoModel> getResearchByPromo(int littleValue, int bigValue) throws DBException, SingletonConnectionException{
        return itemManager.getResearchByPromo(littleValue, bigValue);
    }

    public ArrayList<BusinessTaskModel> getBusinessTaskInformation(String wordingItemReceive)throws DBException, SingletonConnectionException{
        return businessTaskManager.getBusinessTaskInformation(wordingItemReceive);
    }
    public ArrayList<String> getAllItemsWording() throws DBException, SingletonConnectionException{
        return businessTaskManager.getAllItemsWording();
    }

    public void addPromotion(int percentage, String startDate, String endDate, String itemWording) throws  DBException, SingletonConnectionException{
        businessTaskManager.addPromotion(percentage, startDate, endDate, itemWording);
    }

    public ArrayList<PointingBetweenDates> getPointingBetweenDates(GregorianCalendar firstDate, GregorianCalendar secondDate) throws  DBException, SingletonConnectionException {
        return pointingManager.getPointingBetweenDates(firstDate, secondDate);
    }
}
