package controller;

import business.DocumentManager;
import business.WorkflowTypeManager;
import model.Document;
import model.WorkflowType;

import java.util.ArrayList;

public class ApplicationController {
    private WorkflowTypeManager workflowTypeManager;
    private DocumentManager documentManager;

    public ApplicationController() {
        workflowTypeManager = new WorkflowTypeManager();
        documentManager = new DocumentManager();
    }

    public ArrayList<WorkflowType> getAllWorkflowTypes() {
        return workflowTypeManager.getAllWorkflowTypes();
    }

    public ArrayList<Document> getDocuments(String type) {
        return  documentManager.getDocuments(type);
    }
}
