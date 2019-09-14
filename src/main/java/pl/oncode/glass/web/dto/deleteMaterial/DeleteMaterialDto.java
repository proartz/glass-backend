package pl.oncode.glass.web.dto.deleteMaterial;

import pl.oncode.glass.model.Material;
import pl.oncode.glass.model.Order;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DeleteMaterialDto {
    @NotNull(message = "Id cannot be null")
    private Integer id;

    public DeleteMaterialDto() {
    }

    public DeleteMaterialDto(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Material createMaterial() {
        return new Material(this.id, null, null);
    }

    @Override
    public String toString() {
        return "DeleteMaterialDto{" +
                "id=" + id +
                '}';
    }
}
