package pl.oncode.glass.web.dto.fetchItemDto;

import pl.oncode.glass.model.Item;
import pl.oncode.glass.model.Material;
import pl.oncode.glass.model.Order;

public class FetchItemDto {

    private Integer id;
    private Order order;
    private Material material;
    private Double width;
    private Double height;
    private Double depth;
    private Integer quantity;
    private String status;
    private String note;

    public FetchItemDto() {
    }

    public FetchItemDto(Integer id, Order order, Material material, Double width, Double height, Double depth, Integer quantity, String status, String note) {
        this.id = id;
        this.order = order;
        this.material = material;
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.quantity = quantity;
        this.status = status;
        this.note = note;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getDepth() {
        return depth;
    }

    public void setDepth(Double depth) {
        this.depth = depth;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public static class FetchItemDtoBuilder {
        private Integer id;
        private Order order;
        private Material material;
        private Double width;
        private Double height;
        private Double depth;
        private Integer quantity;
        private String status;
        private String note;

        public FetchItemDtoBuilder setId(Integer id) {
            this.id = id;
            return this;
        }

        public FetchItemDtoBuilder setOrder(Order order) {
            this.order = order;
            return this;
        }

        public FetchItemDtoBuilder setMaterialId(Material material) {
            this.material = material;
            return this;
        }

        public FetchItemDtoBuilder setWidth(Double width) {
            this.width = width;
            return this;
        }

        public FetchItemDtoBuilder setHeight(Double height) {
            this.height = height;
            return this;
        }

        public FetchItemDtoBuilder setDepth(Double depth) {
            this.depth = depth;
            return this;
        }

        public FetchItemDtoBuilder setQuantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public FetchItemDtoBuilder setStatus(String status) {
            this.status = status;
            return this;
        }

        public FetchItemDtoBuilder setNote(String note) {
            this.note = note;
            return this;
        }

        public FetchItemDto createFetchItemDto() {
            return new FetchItemDto(id, order, material, width, height, depth, quantity, status, note);
        }
    }

    public static FetchItemDto createFetchItemDto(Item item) {
        return new FetchItemDto.FetchItemDtoBuilder()
                .setId(item.getId())
                .setOrder(item.getOrder())
                .setMaterialId(item.getMaterial())
                .setWidth(item.getWidth())
                .setHeight(item.getHeight())
                .setDepth(item.getDepth())
                .setQuantity(item.getQuantity())
                .setStatus(item.getStatus())
                .setNote(item.getNote())
                .createFetchItemDto();
    }
}
