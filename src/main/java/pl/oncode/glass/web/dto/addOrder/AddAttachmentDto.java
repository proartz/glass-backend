package pl.oncode.glass.web.dto.addOrder;

public class AddAttachmentDto {

    private String Path;

    public AddAttachmentDto() {
    }

    public AddAttachmentDto(String path) {
        Path = path;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }
}
