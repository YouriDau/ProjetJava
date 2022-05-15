package controller;

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

    public ApplicationController() throws DBException, SingletonConnectionException {
        workflowManager = new WorkflowManager();
        documentManager = new DocumentManager();
        itemManager = new ItemManager();
    }

    public ArrayList<WorkflowType> getAllWorkflowTypes() throws DBException, SingletonConnectionException {
        return workflowManager.getAllWorkflowTypes();
    }

    public ArrayList<Document> getDocuments(Integer workflowNumber) throws DBException, SingletonConnectionException {
        return  documentManager.getDocuments(workflowNumber);
    }

    public ArrayList<Document> getAllDocuments() throws DBException, SingletonConnectionException {
        return documentManager.getAllDocuments();
    }

    public ArrayList<DocumentType> getAllDocumentTypes() throws DBException, SingletonConnectionException {
        return documentManager.getAllDocumentTypes();
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

    public ArrayList<Detail> getDetails(ArrayList<Item> items) throws DBException, SingletonConnectionException{
        return itemManager.getDetails(items);
    }

    public ArrayList<Promotion> getPromotions(int littleValue, int bigValue) throws DBException, SingletonConnectionException{
        return itemManager.getPromotions(littleValue, bigValue);
    }

    public ArrayList<Item> getItems(ArrayList<Promotion> promotions) throws DBException, SingletonConnectionException{
        return itemManager.getItems(promotions);
    }
}
