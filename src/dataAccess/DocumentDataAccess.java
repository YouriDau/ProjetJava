package dataAccess;

import model.Document;

import java.util.ArrayList;

public interface DocumentDataAccess {
    public ArrayList<Document> getDocuments(String type);
}
