package controller;

import business.DocumentManager;
import business.WorkflowManager;
import exception.DBException;
import exception.SingletonConnectionException;
import model.Document;
import model.DocumentType;
import model.WorkflowType;

import java.util.ArrayList;

public class ApplicationController {
    private WorkflowManager workflowManager;
    private DocumentManager documentManager;

    public ApplicationController() throws DBException, SingletonConnectionException {
        workflowManager = new WorkflowManager();
        documentManager = new DocumentManager();
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
}
