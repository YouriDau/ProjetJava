package controller;

import model.Document;
import model.WorkflowType;

import java.util.ArrayList;

public interface DataAccess {
    public ArrayList<WorkflowType> getAllWorkflowTypes();
    public ArrayList<Document> getDocuments(Integer workflowNumber);
    public ArrayList<Document> getAllDocuments();

}
