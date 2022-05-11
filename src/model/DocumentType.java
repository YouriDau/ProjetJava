package model;

public class DocumentType {
    private Integer id;
    private String wording;

    public DocumentType(Integer id, String wording) {
        setId(id);
        setWording(wording);
    }

    public Integer getNumber() {
        return id;
    }

    public String getWording() {
        return wording;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setWording(String wording) {
        this.wording = wording;
    }
}
