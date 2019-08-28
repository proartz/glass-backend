package pl.oncode.glass.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.oncode.glass.model.Material;
import pl.oncode.glass.web.dto.viewMaterial.ViewMaterialDto;

import java.util.ArrayList;
import java.util.List;

@Service(value = "materialManagerService")
public class MaterialManagerService {

    Logger logger = LoggerFactory.getLogger(MaterialManagerService.class);
    private DatabaseService databaseService;

    public MaterialManagerService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    // view Materials
    public List<ViewMaterialDto> viewMaterials() {
        List<Material> Materials = databaseService.getAllMaterials();
        List<ViewMaterialDto> viewMaterialDtos = new ArrayList<>();

        for(Material material : Materials) {
            viewMaterialDtos.add(createViewMaterialDto(material));
        }

        return viewMaterialDtos;
    }

    private ViewMaterialDto createViewMaterialDto(Material material) {

        return new ViewMaterialDto(
                material.getId(),
                material.getName(),
                material.getDescription());
    }
}
