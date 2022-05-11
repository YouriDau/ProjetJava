package business;

import controller.DataAccess;
import dataAccess.DBAccess;
import exception.DBException;
import model.WorkflowType;

import java.util.ArrayList;

public class WorkflowManager {
    private DataAccess dao;

    public WorkflowManager() throws DBException {
        setDao(new DBAccess());
    }

    public void setDao(DataAccess workflowTypeDBAccess) {
        this.dao = workflowTypeDBAccess;
    }

    public ArrayList<WorkflowType> getAllWorkflowTypes() throws DBException{
        return dao.getAllWorkflowTypes();
    }

    public ArrayList<Integer> getAllWorkflow() throws DBException {
        return dao.getAllWorkflow();
    }
}
