package business;

import controller.DataAccess;
import dataAccess.DBAccess;
import model.Document;

import java.util.ArrayList;

public class DocumentManager {
    private DataAccess dao;

    public DocumentManager() {
        dao = new DBAccess();
    }

    public ArrayList<Document> getDocuments(Integer workflowNumber) {
        return dao.getDocuments(workflowNumber);
    }

    public ArrayList<Document> getAllDocuments() {
        return dao.getAllDocuments();
    }
}
