package business;

import controller.DataAccess;
import dataAccess.DBAccess;
import exception.DBException;
import exception.SingletonConnectionException;
import model.WorkflowType;

import java.util.ArrayList;

public class WorkflowManager {
    private DataAccess dao;

    public WorkflowManager() throws DBException, SingletonConnectionException {
        setDao(new DBAccess());
    }

    public void setDao(DataAccess workflowTypeDBAccess) {
        this.dao = workflowTypeDBAccess;
    }

    public ArrayList<WorkflowType> getAllWorkflowTypes() throws DBException, SingletonConnectionException{
        return dao.getAllWorkflowTypes();
    }

    public ArrayList<Integer> getAllWorkflow() throws DBException, SingletonConnectionException {
        return dao.getAllWorkflow();
    }
}
