package controller;

import exception.DBException;
import exception.SingletonConnectionException;
import model.*;

import java.util.ArrayList;

public interface DataAccess {
    // Workflow / WorkflowType
    public ArrayList<WorkflowType> getAllWorkflowTypes() throws DBException, SingletonConnectionException;
    public ArrayList<Integer> getAllWorkflow() throws DBException, SingletonConnectionException;

    // Document / DocumentType
    public ArrayList<DocumentByWorkflowType> getDocuments(Integer workflowNumber) throws DBException, SingletonConnectionException;

    // CRUD
    public ArrayList<Document> getAllDocuments() throws DBException, SingletonConnectionException;
    public void addDocument(Document document) throws DBException, SingletonConnectionException;
    public void deleteDocument(Integer id) throws DBException, SingletonConnectionException;
    public void modifyDocument(Document document) throws DBException, SingletonConnectionException;

    public ArrayList<ResearchByPromo> getResearchByPromo(int littleValue, int bigValue) throws DBException, SingletonConnectionException;
    public ArrayList<BusinessTaskModel> getBusinessTaskInformation(String wordingItemReceive) throws DBException, SingletonConnectionException;
}
