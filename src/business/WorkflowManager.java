package business;

import controller.DataAccess;
import dataAccess.DBAccess;
import exception.DBException;
import exception.SingletonConnectionException;
import model.WorkflowType;

import java.util.ArrayList;

public class WorkflowManager {
    private DataAccess dao;

    public WorkflowManager() {
        setDao(new DBAccess());
    }

    public void setDao(DataAccess workflowTypeDBAccess) {
        this.dao = workflowTypeDBAccess;
    }

    public ArrayList<WorkflowType> getAllWorkflowTypes() throws SingletonConnectionException{
        return dao.getAllWorkflowTypes();
    }

    public ArrayList<Integer> getAllWorkflow() throws SingletonConnectionException {
        return dao.getAllWorkflow();
    }
}
