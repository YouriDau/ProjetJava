package business;

import controller.DataAccess;
import dataAccess.DBAccess;
import exception.AddDocumentException;
import exception.DBException;
import exception.SingletonConnectionException;
import model.Document;
import model.DocumentByWorkflowType;
import model.DocumentType;

import java.util.ArrayList;

public class DocumentManager {
    private DataAccess dao;

    public DocumentManager() throws AddDocumentException, SingletonConnectionException {
        setDao(new DBAccess());
    }
    public void setDao(DataAccess documentDBAccess) {
        this.dao = documentDBAccess;
    }
    public ArrayList<DocumentByWorkflowType> getDocuments(Integer workflowNumber) throws DBException, SingletonConnectionException {
        return dao.getDocuments(workflowNumber);
    }

    public ArrayList<DocumentType> getAllDocumentTypes() throws DBException, SingletonConnectionException {
        return dao.getAllDocumentTypes();
    }

    public ArrayList<Document> getAllDocuments() throws DBException, SingletonConnectionException {
        return dao.getAllDocuments();
    }

    public void addDocument(Document document) throws AddDocumentException, SingletonConnectionException {
        dao.addDocument(document);
    }

    public void deleteDocument(int id) throws DBException, SingletonConnectionException {
        dao.deleteDocument(id);
    }

    public void modifyDocument(Document document) throws DBException, SingletonConnectionException {
        dao.modifyDocument(document);
    }
}
