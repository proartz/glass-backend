package pl.oncode.glass.web.dto.updateOrder;

import pl.oncode.glass.model.Item;
import pl.oncode.glass.model.Operation;
import pl.oncode.glass.model.Order;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class UpdateOrderDto {

    private Integer id;
    private List<UpdateItemDto> items;
    private List<UpdateAttachmentDto> attachments;

    @Size(max = 30, message = "ExternalOrderId can have maximum 30 characters")
    private String externalOrderId;

    @Size(max = 30, message = "Customer can have maximum 30 characters")
    @NotEmpty(message = "Customer cannot be null nor empty")
    private String customer;

    @Size(max = 30, message = "Customer can have maximum 30 characters")
    private String invoiceNumber;

    @PositiveOrZero
    @Digits(integer = 19, fraction = 4)
    private BigDecimal price;

    @NotNull(message = "DueDate cannot be null")
    @FutureOrPresent(message = "DueDate cannot be in the past")
    private LocalDate dueDate;

    @Size(max = 100, message = "Description can have maximum 100 characters")
    private String description;

    private Date createDate;

    private String status;

    public UpdateOrderDto() {
    }

    public UpdateOrderDto(Integer id,
                          List<UpdateItemDto> items,
                          List<UpdateAttachmentDto> attachments,
                          String externalOrderId,
                          String customer,
                          String invoiceNumber,
                          BigDecimal price,
                          LocalDate dueDate,
                          String description,
                          Date createDate,
                          String status) {

        this.id = id;
        this.items = items;
        this.attachments = attachments;
        this.externalOrderId = externalOrderId;
        this.customer = customer;
        this.invoiceNumber = invoiceNumber;
        this.price = price;
        this.dueDate = dueDate;
        this.description = description;
        this.createDate = createDate;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<UpdateItemDto> getItems() {
        return items;
    }

    public void setItems(List<UpdateItemDto> items) {
        this.items = items;
    }

    public List<UpdateAttachmentDto> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<UpdateAttachmentDto> attachments) {
        this.attachments = attachments;
    }

    public String getExternalOrderId() {
        return externalOrderId;
    }

    public void setExternalOrderId(String externalOrderId) {
        this.externalOrderId = externalOrderId;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AddOrderDto{" +
                "id=" + id +
                "items=" + items +
                ", attachments=" + attachments +
                ", externalOrderId='" + externalOrderId + '\'' +
                ", customer='" + customer + '\'' +
                ", invoiceNumber='" + invoiceNumber + '\'' +
                ", price=" + price +
                ", dueDate=" + dueDate +
                ", description=" + description +
                ", createDate=" + createDate +
                ", status='" + status + '\'' +
                '}';
    }

    public static Order createOrder(UpdateOrderDto updateOrderDto) {
        Order order = new Order.OrderBuilder()
                .setId(updateOrderDto.getId())
                .setExternalOrderId(updateOrderDto.getExternalOrderId())
                .setCustomer(updateOrderDto.getCustomer())
                .setInvoiceNumber(updateOrderDto.getInvoiceNumber())
                .setPrice(updateOrderDto.getPrice())
                .setDueDate(updateOrderDto.getDueDate())
                .setDescription(updateOrderDto.getDescription())
                .setStatus(updateOrderDto.getStatus())
                .createOrder();

        for(UpdateItemDto updateItemDto : updateOrderDto.getItems()){

            Item item = new Item.ItemBuilder()
                    .setId(updateItemDto.getId())
                    .setMaterial(updateItemDto.getMaterial())
                    .setWidth(updateItemDto.getWidth())
                    .setHeight(updateItemDto.getHeight())
                    .setDepth(updateItemDto.getDepth())
                    .setQuantity(updateItemDto.getQuantity())
                    .setStatus(updateItemDto.getStatus())
                    .setNote(updateItemDto.getNote())
                    .createItem();

            for(UpdateOperationDto updateOperationDto : updateItemDto.getOperations()) {

                item.getOperations().add(
                        new Operation(updateOperationDto.getId(), updateOperationDto.getName(),
                                updateOperationDto.getStatus()));
            }

            order.getItems().add(item);
        }

        order.registerRelations();

        return order;
    }
}
