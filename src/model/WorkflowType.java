package model;

public class WorkflowType {
    private Integer number;
    private String wording;

    public WorkflowType(Integer number, String wording) {
        setNumber(number);
        setWording(wording);
    }

    public Integer getNumber() {
        return number;
    }

    public String getWording() {
        return wording;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setWording(String wording) {
        this.wording = wording;
    }
}
