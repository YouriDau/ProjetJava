package controller;

import business.DocumentManager;
import business.WorkflowTypeManager;
import exception.DBException;
import model.Document;
import model.WorkflowType;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ApplicationController {
    private WorkflowTypeManager workflowTypeManager;
    private DocumentManager documentManager;

    public ApplicationController() throws DBException {
        workflowTypeManager = new WorkflowTypeManager();
        documentManager = new DocumentManager();
    }

    public ArrayList<WorkflowType> getAllWorkflowTypes() throws DBException{
        return workflowTypeManager.getAllWorkflowTypes();
    }

    public ArrayList<Document> getDocuments(Integer workflowNumber) {
        return  documentManager.getDocuments(workflowNumber);
    }

    public ArrayList<Document> getAllDocuments() throws DBException {
        return documentManager.getAllDocuments();
    }
}
