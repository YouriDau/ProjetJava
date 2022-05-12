package model;

public class Workflow {
    private Integer id;
    private Integer type;

    public Workflow(Integer id, Integer type) {
        setId(id);
        setType(type);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public Integer getType() {
        return type;
    }
}
