package business;

import dataAccess.DocumentDBAccess;
import dataAccess.DocumentDataAccess;
import model.Document;

import java.util.ArrayList;

public class DocumentManager {
    private DocumentDataAccess dao;

    public DocumentManager() {
        dao = new DocumentDBAccess();
    }

    public ArrayList<Document> getDocuments(String workflowType) {
        return dao.getDocuments(workflowType);
    }
}
