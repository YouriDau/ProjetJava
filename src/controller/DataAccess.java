package controller;

import exception.DBException;
import model.Document;
import model.WorkflowType;

import java.util.ArrayList;

public interface DataAccess {
    public ArrayList<WorkflowType> getAllWorkflowTypes() throws DBException;
    public ArrayList<Document> getDocuments(Integer workflowNumber);
    public ArrayList<Document> getAllDocuments() throws DBException;

}
