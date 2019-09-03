package pl.oncode.glass.web.dto.updateOrder;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

public class UpdateOrderDto {

    private Integer id;
    private Set<UpdateItemDto> items;
    private Set<UpdateAttachmentDto> attachments;

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

    private Date createDate;

    private String status;

    public UpdateOrderDto() {
    }

    public UpdateOrderDto(Integer id,
                          Set<UpdateItemDto> items,
                          Set<UpdateAttachmentDto> attachments,
                          String externalOrderId,
                          String customer,
                          String invoiceNumber,
                          BigDecimal price,
                          LocalDate dueDate,
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
        this.createDate = createDate;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<UpdateItemDto> getItems() {
        return items;
    }

    public void setItems(Set<UpdateItemDto> items) {
        this.items = items;
    }

    public Set<UpdateAttachmentDto> getAttachments() {
        return attachments;
    }

    public void setAttachments(Set<UpdateAttachmentDto> attachments) {
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
                ", createDate=" + createDate +
                ", status='" + status + '\'' +
                '}';
    }
}
