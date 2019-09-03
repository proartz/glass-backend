package pl.oncode.glass.web.dto.updateOrder;

public class UpdateAttachmentDto {

    private String Path;

    public UpdateAttachmentDto() {
    }

    public UpdateAttachmentDto(String path) {
        Path = path;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }
}
