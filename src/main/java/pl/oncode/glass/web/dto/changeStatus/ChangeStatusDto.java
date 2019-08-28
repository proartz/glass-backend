package pl.oncode.glass.web.dto.changeStatus;

public class ChangeStatusDto {

    private Integer operationId;
    private String newStatus;

    public ChangeStatusDto() {
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
                "operationId=" + operationId +
                ", newStatus='" + newStatus + '\'' +
                '}';
    }
}
