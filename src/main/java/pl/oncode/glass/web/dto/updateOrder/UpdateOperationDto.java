package pl.oncode.glass.web.dto.updateOrder;

public class UpdateOperationDto {

    private Integer id;
    private String name;
    private String status;

    public UpdateOperationDto() {
    }

    public UpdateOperationDto(Integer id,
                              String name,
                              String status) {

        this.name = name;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "AddOperationDto{" +
                "id=" + id +
                "name='" + name + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
