package pl.oncode.glass.web.dto.addOrder;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

public class AddOrderDto {

    private Set<AddItemDto> items;
    private Set<AddAttachmentDto> attachments;
    private String externalOrderId;
    private String customer;
    private String invoiceNumber;
    private BigDecimal price;
    private Date realisationDate;
    private Date createDate;
    private String status;

    public AddOrderDto() {
    }

    public AddOrderDto(Set<AddItemDto> items,
                       Set<AddAttachmentDto> attachments,
                       String externalOrderId,
                       String customer,
                       String invoiceNumber,
                       BigDecimal price,
                       Date realisationDate,
                       Date createDate,
                       String status) {

        this.items = items;
        this.attachments = attachments;
        this.externalOrderId = externalOrderId;
        this.customer = customer;
        this.invoiceNumber = invoiceNumber;
        this.price = price;
        this.realisationDate = realisationDate;
        this.createDate = createDate;
        this.status = status;
    }

    public Set<AddItemDto> getItems() {
        return items;
    }

    public void setItems(Set<AddItemDto> items) {
        this.items = items;
    }

    public Set<AddAttachmentDto> getAttachments() {
        return attachments;
    }

    public void setAttachments(Set<AddAttachmentDto> attachments) {
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

    public Date getRealisationDate() {
        return realisationDate;
    }

    public void setRealisationDate(Date realisationDate) {
        this.realisationDate = realisationDate;
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
                "items=" + items +
                ", attachments=" + attachments +
                ", externalOrderId='" + externalOrderId + '\'' +
                ", customer='" + customer + '\'' +
                ", invoiceNumber='" + invoiceNumber + '\'' +
                ", price=" + price +
                ", realisationDate=" + realisationDate +
                ", createDate=" + createDate +
                ", status='" + status + '\'' +
                '}';
    }
}
