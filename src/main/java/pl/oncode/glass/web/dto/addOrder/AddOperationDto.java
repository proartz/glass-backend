package pl.oncode.glass.web.dto.addOrder;

public class AddOperationDto {

    private String name;
    private String status;

    public AddOperationDto() {
    }

    public AddOperationDto(String name,
                           String status) {

        this.name = name;
        this.status = status;
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
                "name='" + name + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
