package pl.oncode.glass.model;

import pl.oncode.glass.model.Operation;

import javax.persistence.*;
import java.util.List;

@Entity
public class Item {

    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne(targetEntity = Order.class)
    @JoinColumn(name = "order_id")
    private Integer orderId;
    private Integer materialId;
    @OneToMany(mappedBy = "itemId", cascade = CascadeType.ALL)
    private List<Operation> operations;
    private Double width;
    private Double height;
    private Double depth;
    private Integer quantity;
    private String note;

    public Item() {
    }

    public Item(Integer orderId, Integer materialId, Double width, Double height,
                Double depth, Integer quantity) {
        this(orderId, materialId, null, width, height, depth, quantity, "");
    }

    public Item(Integer orderId, Integer materialId, List<Operation> operations,
                Double width, Double height, Double depth, Integer quantity,
                String note) {
        this.orderId = orderId;
        this.materialId = materialId;
        this.operations = operations;
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.quantity = quantity;
        this.note = note;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getDepth() {
        return depth;
    }

    public void setDepth(Double depth) {
        this.depth = depth;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
