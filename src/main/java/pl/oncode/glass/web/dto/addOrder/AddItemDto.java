package pl.oncode.glass.web.dto.addOrder;

import java.util.Set;

public class AddItemDto {

    private Integer materialId;
    private Set<AddOperationDto> operations;
    private Double width;
    private Double height;
    private Double depth;
    private Integer quantity;
    private String note;

    public AddItemDto() {
    }

    public AddItemDto(Integer materialId,
                      Set<AddOperationDto> operations,
                      Double width,
                      Double height,
                      Double depth,
                      Integer quantity,
                      String note) {

        this.materialId = materialId;
        this.operations = operations;
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.quantity = quantity;
        this.note = note;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public Set<AddOperationDto> getOperations() {
        return operations;
    }

    public void setOperations(Set<AddOperationDto> operations) {
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