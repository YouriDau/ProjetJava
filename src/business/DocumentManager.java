package business;

import controller.DataAccess;
import dataAccess.DBAccess;
import exception.DBException;
import exception.SingletonConnectionException;
import model.Document;
import model.DocumentType;

import java.util.ArrayList;

public class DocumentManager {
    private DataAccess dao;

    public DocumentManager() throws DBException, SingletonConnectionException {
        dao = new DBAccess();
    }

    public ArrayList<Document> getDocuments(Integer workflowNumber) throws DBException, SingletonConnectionException {
        return dao.getDocuments(workflowNumber);
    }

    public ArrayList<Document> getAllDocuments() throws DBException, SingletonConnectionException {
        return dao.getAllDocuments();
    }

    public ArrayList<DocumentType> getAllDocumentTypes() throws DBException, SingletonConnectionException {
        return dao.getAllDocumentTypes();
    }

    public void addDocument(Document document) throws DBException, SingletonConnectionException {
        dao.addDocument(document);
    }

    public void deleteDocument(int id) throws DBException, SingletonConnectionException {
        dao.deleteDocument(id);
    }

    public void modifyDocument(Document document) throws DBException, SingletonConnectionException {
        dao.modifyDocument(document);
    }
}
