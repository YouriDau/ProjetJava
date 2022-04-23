package dataAccess;

import model.WorkflowType;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WorkflowTypeDBAccess implements WorkflowTypeDataAccess {

    @Override
    public ArrayList<WorkflowType> getAllWorkflowTypes() {
        Integer number;
        String wording;
        WorkflowType workflowType;
        ArrayList<WorkflowType> workflowTypes = new ArrayList<>();
        String sqlInstruction = "SELECT * FROM workflow_type";

        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery();

            while (data.next()) {
                number = data.getInt("number");
                wording = data.getString("wording");
                workflowType = new WorkflowType(number, wording);
                workflowTypes.add(workflowType);
            }
        }
        catch (SQLException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        }

        return workflowTypes;
    }
}
