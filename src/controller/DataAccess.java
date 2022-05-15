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
    public ArrayList<Document> getDocuments(Integer workflowNumber) throws DBException, SingletonConnectionException;
    public ArrayList<DocumentType> getAllDocumentTypes() throws DBException, SingletonConnectionException;

    // CRUD
    public ArrayList<Document> getAllDocuments() throws DBException, SingletonConnectionException;
    public void addDocument(Document document) throws DBException, SingletonConnectionException;
    public void deleteDocument(int id) throws DBException, SingletonConnectionException;
    public void modifyDocument(Document document) throws DBException, SingletonConnectionException;

    // Detail
    public ArrayList<Detail> getDetails(ArrayList<Item> items) throws DBException, SingletonConnectionException;
    public Detail getDetail(Integer itemIdReceive) throws DBException, SingletonConnectionException;

    // Item
    public Item getItem(Integer idPromotion) throws DBException, SingletonConnectionException;
    public ArrayList<Item> getItems(ArrayList<Promotion> promotions) throws DBException, SingletonConnectionException;

    // Promotion
    public ArrayList<Promotion> getPromotions(int littleValue, int bigValue) throws DBException, SingletonConnectionException;
}
