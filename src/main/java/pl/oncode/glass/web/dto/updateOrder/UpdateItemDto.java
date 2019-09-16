package pl.oncode.glass.web.dto.updateOrder;

import pl.oncode.glass.model.Material;

import java.util.List;

public class UpdateItemDto {

    private Integer id;
    private Material material;
    private List<UpdateOperationDto> operations;
    private Double width;
    private Double height;
    private Double depth;
    private Integer quantity;
    private String status;
    private String note;

    public UpdateItemDto() {
    }

    public UpdateItemDto(Integer id,
                         Material material,
                         List<UpdateOperationDto> operations,
                         Double width,
                         Double height,
                         Double depth,
                         Integer quantity,
                         String status,
                         String note) {

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

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public List<UpdateOperationDto> getOperations() {
        return operations;
    }

    public void setOperations(List<UpdateOperationDto> operations) {
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
        return "AddItemDto{" +
                "id=" + id +
                "material=" + material +
                ", operations=" + operations +
                ", width=" + width +
                ", height=" + height +
                ", depth=" + depth +
                ", quantity=" + quantity +
                ", status='" + status + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}