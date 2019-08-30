package pl.oncode.glass.web.dto.fetchOperation;

import pl.oncode.glass.model.Item;
import pl.oncode.glass.model.Operation;

public class FetchOperationDto {

    private Integer id;
    private Item item;
    private String name;
    private String status;

    public FetchOperationDto() {
    }

    public FetchOperationDto(Integer id, Item item, String name, String status) {
        this.id = id;
        this.item = item;
        this.name = name;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
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

    public static class FetchOperationDtoBuilder {
        private Integer id;
        private Item item;
        private String name;
        private String status;

        public FetchOperationDtoBuilder setId(Integer id) {
            this.id = id;
            return this;
        }

        public FetchOperationDtoBuilder setItem(Item item) {
            this.item = item;
            return this;
        }

        public FetchOperationDtoBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public FetchOperationDtoBuilder setStatus(String status) {
            this.status = status;
            return this;
        }

        public FetchOperationDto createFetchOperationDto() {
            return new FetchOperationDto(id, item, name, status);
        }


    }

    public static FetchOperationDto createFetchOperationDto(Operation operation) {
        return new FetchOperationDtoBuilder()
                .setId(operation.getId())
                .setItem(operation.getItem())
                .setName(operation.getName())
                .setStatus(operation.getStatus())
                .createFetchOperationDto();
    }

}
