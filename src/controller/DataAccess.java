package controller;

import exception.DBException;
import exception.SingletonConnectionException;
import model.*;

import java.util.ArrayList;

public interface DataAccess {
    public ArrayList<WorkflowType> getAllWorkflowTypes() throws DBException, SingletonConnectionException;
    public ArrayList<Integer> getAllWorkflow() throws DBException, SingletonConnectionException;
    public ArrayList<Document> getDocuments(Integer workflowNumber) throws DBException, SingletonConnectionException;
    public ArrayList<Document> getAllDocuments() throws DBException, SingletonConnectionException;
    public ArrayList<DocumentType> getAllDocumentTypes() throws DBException, SingletonConnectionException;

    public void addDocument(Document document) throws DBException, SingletonConnectionException;
    public void deleteDocument(int id) throws DBException, SingletonConnectionException;
    public ArrayList<Detail> getDetails(ArrayList<Item> items) throws DBException, SingletonConnectionException;
    public Detail getDetail(Integer itemIdReceive) throws DBException, SingletonConnectionException;
    public ArrayList<Promotion> getPromotions(int littleValue, int bigValue) throws DBException, SingletonConnectionException;
    public Item getItem(Integer idPromotion) throws DBException, SingletonConnectionException;
    public ArrayList<Item> getItems(ArrayList<Promotion> promotions) throws DBException, SingletonConnectionException;
}
