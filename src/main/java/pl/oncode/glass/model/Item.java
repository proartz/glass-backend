package pl.oncode.glass.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.*;

@Entity
public class Item {

    public enum itemStatus { NOWA, NORMALNA, USUNIĘTA };

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @OneToOne
    @JoinColumn(name = "material_id")
    private Material material;
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Operation> operations;
    private Double width;
    private Double height;
    private Double depth;
    private Integer quantity;

    @NotEmpty(message = "Status cannot be empty")
    private String status;
    private String note;

    public Item() {
    }

    public Item(Integer id, Material material, List<Operation> operations, Double width, Double height, Double depth, Integer quantity, String status, String note) {
        this.id = id;
        this.material = material;
        this.operations = operations;
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.quantity = quantity;
        this.status = status;
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

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
                ", material=" + material +
                ", operations=" + operations +
                ", width=" + width +
                ", height=" + height +
                ", depth=" + depth +
                ", quantity=" + quantity +
                ", status=" + status +
                ", note='" + note + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id.equals(item.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public boolean isItemNew() {
        return this.getStatus().equals(itemStatus.NOWA.name());
    }

    public boolean isItemToDelete() {
        return this.getStatus().equals(itemStatus.USUNIĘTA.name());
    }

    public static class ItemBuilder {
        private Integer id;
        private Material material;
        private List<Operation> operations;
        private Double width;
        private Double height;
        private Double depth;
        private Integer quantity;
        private String status;
        private String note;

        public ItemBuilder() {
            this.operations = new ArrayList<>();
        }

        public ItemBuilder setId(Integer id) {
            this.id = id;
            return this;
        }

        public ItemBuilder setMaterial(Material material) {
            this.material = material;
            return this;
        }

        public ItemBuilder setOperations(List<Operation> operations) {
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

        public ItemBuilder setStatus(String status) {
            this.status = status;
            return this;
        }

        public ItemBuilder setNote(String note) {
            this.note = note;
            return this;
        }

        public Item createItem() {
            return new Item(id, material, operations, width, height, depth, quantity, status, note);
        }
    }

}
