package controller;

import business.DocumentManager;
import business.WorkflowManager;
import exception.DBException;
import model.Document;
import model.DocumentType;
import model.WorkflowType;

import java.util.ArrayList;

public class ApplicationController {
    private WorkflowManager workflowManager;
    private DocumentManager documentManager;

    public ApplicationController() throws DBException {
        workflowManager = new WorkflowManager();
        documentManager = new DocumentManager();
    }

    public ArrayList<WorkflowType> getAllWorkflowTypes() throws DBException{
        return workflowManager.getAllWorkflowTypes();
    }

    public ArrayList<Document> getDocuments(Integer workflowNumber) {
        return  documentManager.getDocuments(workflowNumber);
    }

    public ArrayList<Document> getAllDocuments() throws DBException {
        return documentManager.getAllDocuments();
    }

    public ArrayList<DocumentType> getAllDocumentTypes() throws DBException {
        return documentManager.getAllDocumentTypes();
    }

    public ArrayList<Integer> getAllWorkflow() throws DBException {
        return workflowManager.getAllWorkflow();
    }

    public void addDocument(Document document) throws DBException {
        documentManager.addDocument(document);
    }
}
