package controller;

import exception.DBException;
import exception.SingletonConnectionException;
import model.Document;
import model.DocumentType;
import model.WorkflowType;

import java.util.ArrayList;

public interface DataAccess {
    public ArrayList<WorkflowType> getAllWorkflowTypes() throws DBException, SingletonConnectionException;
    public ArrayList<Integer> getAllWorkflow() throws DBException, SingletonConnectionException;
    public ArrayList<Document> getDocuments(Integer workflowNumber) throws DBException, SingletonConnectionException;
    public ArrayList<Document> getAllDocuments() throws DBException, SingletonConnectionException;
    public ArrayList<DocumentType> getAllDocumentTypes() throws DBException, SingletonConnectionException;

    public void addDocument(Document document) throws DBException, SingletonConnectionException;
    public void deleteDocument(int id) throws DBException, SingletonConnectionException;
}
