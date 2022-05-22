package business;

import controller.DataAccess;
import dataAccess.DBAccess;
import exception.*;
import model.Document;
import model.DocumentByWorkflowType;
import model.DocumentType;

import java.util.ArrayList;

public class DocumentManager {
    private DataAccess dao;

    public DocumentManager() {
        setDao(new DBAccess());
    }
    public void setDao(DataAccess documentDBAccess) {
        this.dao = documentDBAccess;
    }

    public ArrayList<DocumentByWorkflowType> getDocuments(Integer workflowNumber) throws SingletonConnectionException {
        return dao.getDocuments(workflowNumber);
    }

    public ArrayList<DocumentType> getAllDocumentTypes() throws SingletonConnectionException {
        return dao.getAllDocumentTypes();
    }

    public ArrayList<Document> getAllDocuments() throws SingletonConnectionException {
        return dao.getAllDocuments();
    }

    public void addDocument(Document document) throws AddDocumentException, SingletonConnectionException {
        dao.addDocument(document);
    }

    public void deleteDocument(int id) throws DeleteDocumentException, SingletonConnectionException {
        dao.deleteDocument(id);
    }

    public void modifyDocument(Document document) throws ModifyDocumentException, SingletonConnectionException {
        dao.modifyDocument(document);
    }
}
