package model;

import java.util.GregorianCalendar;

public class Document {
    private Integer number;
    private GregorianCalendar creationDate;
    private String paymentCondition; // Can be null
    private Double creditLimit; // Can be null
    private Integer type;
    private Integer workflowNumber;

    public Document(Integer number, GregorianCalendar creationDate, String paymentCondition,
                         Double creditLimit, Integer type, Integer workflowNumber) {
        setNumber(number);
        setCreationDate(creationDate);
        setPaymentCondition(paymentCondition);
        setCreditLimit(creditLimit);
        setProcessNumber(workflowNumber);
    }

    public Document(Integer number, GregorianCalendar creationDate, Integer type, Integer workflowNumber) {
        setNumber(number);
        setCreationDate(creationDate);
        setProcessNumber(workflowNumber);
        setPaymentCondition(null);
        setCreditLimit(null);
    }

    public Integer getNumber() {
        return number;
    }

    public GregorianCalendar getCreationDate() {
        return creationDate;
    }

    public String getPaymentCondition() {
        return paymentCondition;
    }

    public Double getCreditLimit() {
        return creditLimit;
    }

    public Integer getType() {
        return type;
    }

    public Integer getProcessNumber() {
        return workflowNumber;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setCreationDate(GregorianCalendar creationDate) {
        this.creationDate = creationDate;
    }

    public void setPaymentCondition(String paymentCondition) {
        this.paymentCondition = paymentCondition;
    }

    public void setCreditLimit(Double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setProcessNumber(Integer processNumber) {
        this.workflowNumber = processNumber;
    }
}
