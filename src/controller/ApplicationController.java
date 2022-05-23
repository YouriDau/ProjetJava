package controller;

import business.*;
import exception.*;
import model.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ApplicationController {
    private WorkflowManager workflowManager;
    private DocumentManager documentManager;
    private ItemManager itemManager;
    private BusinessTaskManager businessTaskManager;
    private PointingManager pointingManager;
    private ConnectionManager manager;


    public ApplicationController() {
        setWorkflowManager(new WorkflowManager());
        setDocumentManager(new DocumentManager());
        setItemManager(new ItemManager());
        setBusinessTaskManager(new BusinessTaskManager());
        setPointingManager(new PointingManager());
        setManager(new ConnectionManager());
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
    public void setManager(ConnectionManager manager) {
        this.manager = manager;
    }
    public ArrayList<WorkflowType> getAllWorkflowTypes() throws SingletonConnectionException {
        return workflowManager.getAllWorkflowTypes();
    }

    public ArrayList<DocumentByWorkflowType> getDocuments(Integer workflowNumber) throws SingletonConnectionException {
        return  documentManager.getDocuments(workflowNumber);
    }

    public ArrayList<DocumentType> getAllDocumentTypes() throws SingletonConnectionException {
        return documentManager.getAllDocumentTypes();
    }

    public ArrayList<Document> getAllDocuments() throws SingletonConnectionException {
        return documentManager.getAllDocuments();
    }

    public ArrayList<Integer> getAllWorkflow() throws SingletonConnectionException {
        return workflowManager.getAllWorkflow();
    }

    public void addDocument(Document document) throws AddDocumentException, SingletonConnectionException {
        documentManager.addDocument(document);
    }

    public void deleteDocument(int id) throws DeleteDocumentException, SingletonConnectionException {
        documentManager.deleteDocument(id);
    }

    public void modifyDocument(Document document) throws ModifyDocumentException, SingletonConnectionException {
        documentManager.modifyDocument(document);
    }

    public ArrayList<ResearchByPromoModel> getResearchByPromo(int littleValue, int bigValue) throws SingletonConnectionException{
        return itemManager.getResearchByPromo(littleValue, bigValue);
    }

    public ArrayList<BusinessTaskModel> getBusinessTaskInformation(String wordingItemReceive)throws SingletonConnectionException{
        return businessTaskManager.getBusinessTaskInformation(wordingItemReceive);
    }

    public ArrayList<String> getAllItemsWording() throws SingletonConnectionException{
        return businessTaskManager.getAllItemsWording();
    }

    public void addPromotion(int percentage, String startDate, String endDate, String itemWording) throws  AddPromotionException, SingletonConnectionException{
        businessTaskManager.addPromotion(percentage, startDate, endDate, itemWording);
    }

    public ArrayList<PointingBetweenDates> getPointingBetweenDates(GregorianCalendar firstDate, GregorianCalendar secondDate) throws  SingletonConnectionException {
        return pointingManager.getPointingBetweenDates(firstDate, secondDate);
    }

    public void closeConnection() throws CloseConnectionException{
        manager.closeConnection();
    }
}
