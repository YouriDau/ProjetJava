package controller;

import business.BusinessTaskManager;
import business.DocumentManager;
import business.ItemManager;
import business.WorkflowManager;
import exception.DBException;
import exception.SingletonConnectionException;
import model.*;

import java.util.ArrayList;

public class ApplicationController {
    private WorkflowManager workflowManager;
    private DocumentManager documentManager;
    private ItemManager itemManager;
    private BusinessTaskManager businessTaskManager;

    public ApplicationController() throws DBException, SingletonConnectionException {
        workflowManager = new WorkflowManager();
        documentManager = new DocumentManager();
        itemManager = new ItemManager();
        businessTaskManager = new BusinessTaskManager();
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

    public void addDocument(Document document) throws DBException, SingletonConnectionException {
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
}
