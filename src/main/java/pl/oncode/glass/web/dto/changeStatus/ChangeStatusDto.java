package pl.oncode.glass.web.dto.changeStatus;

public class ChangeStatusDto {

    private Integer orderId;
    private Integer itemId;
    private Integer operationId;
    private String newStatus;

    public ChangeStatusDto() {
    }

    public ChangeStatusDto(Integer orderId, Integer itemId, Integer operationId, String newStatus) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.operationId = operationId;
        this.newStatus = newStatus;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getOperationId() {
        return operationId;
    }

    public void setOperationId(Integer operationId) {
        this.operationId = operationId;
    }

    public String getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus;
    }

    @Override
    public String toString() {
        return "ChangeStatusDto{" +
                "orderId=" + orderId +
                ", itemId=" + itemId +
                ", operationId=" + operationId +
                ", newStatus='" + newStatus + '\'' +
                '}';
    }
}
