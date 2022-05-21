package model;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DocumentByWorkflowType {
    private Integer documentNumber;
    private Integer WorkflowNumber;
    private GregorianCalendar creationDate;
    private Double creditLimit;
    private String documentType;

    public DocumentByWorkflowType(Integer documentNumber, Integer workflowNumber, GregorianCalendar creationCreation,
                                  Double creditLimit, String documentType) {
        setDocumentNumber(documentNumber);
        setWorkflowNumber(workflowNumber);
        setCreationDate(creationCreation);
        setCreditLimit(creditLimit);
        setDocumentType(documentType);
    }

    public DocumentByWorkflowType(Integer documentNumber, Integer workflowNumber, GregorianCalendar creationCreation, String documentType) {
        this(documentNumber, workflowNumber, creationCreation, null, documentType);
    }

    public Integer getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(Integer documentNumber) {
        this.documentNumber = documentNumber;
    }

    public Integer getWorkflowNumber() {
        return WorkflowNumber;
    }

    public void setWorkflowNumber(Integer workflowNumber) {
        WorkflowNumber = workflowNumber;
    }

    public GregorianCalendar getCreationDate() {
        return creationDate;
    }

    public String getCreationDateStr() {
        int year = creationDate.get(Calendar.YEAR);
        int month = creationDate.get(Calendar.MONTH)+1;
        int day = creationDate.get(Calendar.DAY_OF_MONTH);

        return year + "-" + month + "-" + day;
    }

    public void setCreationDate(GregorianCalendar creationDate) {
        this.creationDate = creationDate;
    }

    public Double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }
}
