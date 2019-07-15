package pl.oncode.glass.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    private Integer materialId;
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Operation> operations;
    private Double width;
    private Double height;
    private Double depth;
    private Integer quantity;
    private String note;

    public Item() {
    }

    public Item(Integer materialId, Set<Operation> operations, Double width, Double height, Double depth, Integer quantity, String note) {
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public Set<Operation> getOperations() {
        return operations;
    }

    public void setOperations(Set<Operation> operations) {
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

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", order=" + order +
                ", materialId=" + materialId +
                ", operations=" + operations +
                ", width=" + width +
                ", height=" + height +
                ", depth=" + depth +
                ", quantity=" + quantity +
                ", note='" + note + '\'' +
                '}';
    }

    public static class ItemBuilder {
        private Integer materialId;
        private Set<Operation> operations;
        private Double width;
        private Double height;
        private Double depth;
        private Integer quantity;
        private String note;

        public ItemBuilder() {
            this.operations = new HashSet<>();
        }

        public ItemBuilder setMaterialId(Integer materialId) {
            this.materialId = materialId;
            return this;
        }

        public ItemBuilder setOperations(Set<Operation> operations) {
            this.operations = operations;
            return this;
        }

        public ItemBuilder addOperation(Operation operation) {
            this.operations.add(operation);
        return this;
        }

        public ItemBuilder setWidth(Double width) {
            this.width = width;
            return this;
        }

        public ItemBuilder setHeight(Double height) {
            this.height = height;
            return this;
        }

        public ItemBuilder setDepth(Double depth) {
            this.depth = depth;
            return this;
        }

        public ItemBuilder setQuantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public ItemBuilder setNote(String note) {
            this.note = note;
            return this;
        }

        public Item createItem() {
            return new Item(materialId, operations, width, height, depth, quantity, note);
        }
    }

}
