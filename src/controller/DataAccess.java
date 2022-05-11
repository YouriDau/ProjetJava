package controller;

import exception.DBException;
import model.Document;
import model.DocumentType;
import model.WorkflowType;

import java.util.ArrayList;

public interface DataAccess {
    public ArrayList<WorkflowType> getAllWorkflowTypes() throws DBException;
    public ArrayList<Integer> getAllWorkflow() throws DBException;
    public ArrayList<Document> getDocuments(Integer workflowNumber);
    public ArrayList<Document> getAllDocuments() throws DBException;
    public ArrayList<DocumentType> getAllDocumentTypes() throws DBException;

    public void addDocument(Document document) throws DBException;
}
