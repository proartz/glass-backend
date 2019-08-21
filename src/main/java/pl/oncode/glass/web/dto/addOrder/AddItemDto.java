package pl.oncode.glass.web.dto.addOrder;

import java.util.List;
import java.util.List;

public class AddItemDto {

    private Integer materialId;
    private List<AddOperationDto> operations;
    private Double width;
    private Double height;
    private Double depth;
    private Integer quantity;
    private String status;
    private String note;

    public AddItemDto() {
    }

    public AddItemDto(Integer materialId,
                      List<AddOperationDto> operations,
                      Double width,
                      Double height,
                      Double depth,
                      Integer quantity,
                      String status,
                      String note) {

        this.materialId = materialId;
        this.operations = operations;
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.quantity = quantity;
        this.status = status;
        this.note = note;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public void ListMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public List<AddOperationDto> getOperations() {
        return operations;
    }

    public void ListOperations(List<AddOperationDto> operations) {
        this.operations = operations;
    }

    public Double getWidth() {
        return width;
    }

    public void ListWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void ListHeight(Double height) {
        this.height = height;
    }

    public Double getDepth() {
        return depth;
    }

    public void ListDepth(Double depth) {
        this.depth = depth;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void ListQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void ListStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void ListNote(String note) {
        this.note = note;
    }
}