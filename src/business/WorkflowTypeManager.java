package business;

import controller.DataAccess;
import dataAccess.DBAccess;
import model.WorkflowType;

import java.util.ArrayList;

public class WorkflowTypeManager {
    private DataAccess dao;

    public WorkflowTypeManager() {
        setDao(new DBAccess());
    }

    public ArrayList<WorkflowType> getAllWorkflowTypes() {
        return dao.getAllWorkflowTypes();
    }

    public void setDao(DataAccess workflowTypeDBAccess) {
        this.dao = workflowTypeDBAccess;
    }
}
