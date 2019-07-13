package pl.oncode.glass.model;

import javax.persistence.*;

@Entity
public class Operation {

    @Id @GeneratedValue
    private Integer id;
    @ManyToOne(targetEntity = Item.class)
    @JoinColumn(name = "item_id")
    private Integer itemId;
    private String name;
    private String status;

    public Operation() {
    }

    public Operation(Integer itemId, String name) {
        this.itemId = itemId;
        this.name = name;
        this.status = "NEW";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
