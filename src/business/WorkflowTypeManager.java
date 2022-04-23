package business;

import dataAccess.WorkflowTypeDBAccess;
import dataAccess.WorkflowTypeDataAccess;
import model.WorkflowType;

import java.util.ArrayList;

public class WorkflowTypeManager {
    private WorkflowTypeDataAccess dao;

    public WorkflowTypeManager() {
        setDao(new WorkflowTypeDBAccess());
    }

    public ArrayList<WorkflowType> getAllWorkflowTypes() {
        return dao.getAllWorkflowTypes();
    }

    public void setDao(WorkflowTypeDBAccess workflowTypeDBAccess) {
        this.dao = workflowTypeDBAccess;
    }
}
