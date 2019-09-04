package pl.oncode.glass.web.dto.viewMaterial;

import pl.oncode.glass.model.Material;

public class ViewMaterialDto {
    private Integer id;
    private String name;
    private String description;

    public ViewMaterialDto() {
    }

    public ViewMaterialDto(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static ViewMaterialDto createViewMaterialDto(Material material) {

        return new ViewMaterialDto(
                material.getId(),
                material.getName(),
                material.getDescription());
    }
}
