package business;

import controller.DataAccess;
import dataAccess.DBAccess;
import exception.DBException;
import model.Document;
import model.DocumentType;

import java.util.ArrayList;

public class DocumentManager {
    private DataAccess dao;

    public DocumentManager() throws DBException {
        dao = new DBAccess();
    }

    public ArrayList<Document> getDocuments(Integer workflowNumber) {
        return dao.getDocuments(workflowNumber);
    }

    public ArrayList<Document> getAllDocuments() throws DBException {
        return dao.getAllDocuments();
    }

    public ArrayList<DocumentType> getAllDocumentTypes() throws DBException {
        return dao.getAllDocumentTypes();
    }

    public void addDocument(Document document) throws DBException {
        dao.addDocument(document);
    }
}
