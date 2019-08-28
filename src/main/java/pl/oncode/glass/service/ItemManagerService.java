package pl.oncode.glass.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.oncode.glass.model.Item;
import pl.oncode.glass.web.dto.fetchItemDto.FetchItemDto;

import java.util.ArrayList;
import java.util.List;

@Service(value = "itemManagerService")
public class ItemManagerService {

    Logger logger = LoggerFactory.getLogger(ItemManagerService.class);
    private DatabaseService databaseService;

    public ItemManagerService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    // fetch Items
    public List<FetchItemDto> fetchItems(Integer orderId) {
        List<Item> items = databaseService.getAllOrderItems(orderId);
        List<FetchItemDto> fetchItemDtos = new ArrayList<>();

        for(Item item : items) {
            fetchItemDtos.add(createFetchItemDto(item));
        }

        return fetchItemDtos;
    }

    private FetchItemDto createFetchItemDto(Item item) {
        return new FetchItemDto.FetchItemDtoBuilder()
                .setId(item.getId())
                .setOrder(item.getOrder())
                .setMaterialId(item.getMaterialId())
                .setWidth(item.getWidth())
                .setHeight(item.getHeight())
                .setDepth(item.getDepth())
                .setQuantity(item.getQuantity())
                .setStatus(item.getStatus())
                .setNote(item.getNote())
                .createFetchItemDto();
    }

}
