package model;

import exception.DBException;
import exception.SingletonConnectionException;
import userInterface.AllDocumentsPanel;

import javax.swing.*;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Document {
    public static final String[] columnsTitles = {"Number", "Creation date", "Payment condition", "Credit limit", "Document type", "Workflow number"};

    private Integer number;
    private GregorianCalendar creationDate;
    private String paymentCondition; // Can be null
    private Double creditLimit; // Can be null
    private Integer type;
    private Integer workflowNumber;
    private Boolean updateTheStock;

    private Pattern pattern;
    private Matcher matcher;

    public Document(Integer number, GregorianCalendar creationDate, String paymentCondition,
                         Double creditLimit, Integer type, Integer workflowNumber, Boolean updateTheStock) {
        setNumber(number);
        setCreationDate(creationDate);
        setPaymentCondition(paymentCondition);
        setCreditLimit(creditLimit);
        setType(type);
        setworkflowNumber(workflowNumber);
        setUpdateTheStock(updateTheStock);
    }

    public Document(Integer number, GregorianCalendar creationDate, Integer type, Integer workflowNumber, Boolean updateTheStock) {
        this(number, creationDate, null, null, type, workflowNumber, updateTheStock);
    }

    public Integer getNumber() {
        return number;
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

    public String getPaymentCondition() {
        return paymentCondition;
    }

    public Double getCreditLimit() {
        return creditLimit;
    }

    public Integer getType() {
        return type;
    }

    public Integer getworkflowNumber() {
        return workflowNumber;
    }

    public Boolean getUpdateTheStock() {
        return updateTheStock;
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

    public void setworkflowNumber(Integer processNumber) {
        this.workflowNumber = processNumber;
    }

    public void setUpdateTheStock(Boolean updateTheStock) {
        this.updateTheStock = updateTheStock;
    }
}
