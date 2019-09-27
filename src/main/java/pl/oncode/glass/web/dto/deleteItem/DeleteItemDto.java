package pl.oncode.glass.web.dto.deleteItem;

public class DeleteItemDto {
    private int id;

    public DeleteItemDto() {
    }

    public DeleteItemDto(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
