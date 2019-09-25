package pl.oncode.glass.web.dto.fetchOrder;

import pl.oncode.glass.model.Attachment;
import pl.oncode.glass.model.Item;
import pl.oncode.glass.model.Order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class FetchOrderDto {

    private Integer id;
    private List<Item> items;
    private List<Attachment> attachments;
    private String externalOrderId;
    private String customer;
    private String invoiceNumber;
    private BigDecimal price;
    private LocalDate dueDate;
    private String description;
    private Date createDate;
    private String status;

    public FetchOrderDto() {
    }

    public FetchOrderDto(Integer id,
                         List<Item> items,
                         List<Attachment> attachments,
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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
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

    public static class ViewOrderDtoBuilder {
        private Integer id;
        private List<Item> items;
        private List<Attachment> attachments;
        private String externalOrderId;
        private String customer;
        private String invoiceNumber;
        private BigDecimal price;
        private LocalDate dueDate;
        private String description;
        private Date createDate;
        private String status;

        public ViewOrderDtoBuilder setId(Integer id) {
            this.id = id;
            return this;
        }

        public ViewOrderDtoBuilder setItems(List<Item> items) {
            this.items = items;
            return this;
        }

        public ViewOrderDtoBuilder setAttachments(List<Attachment> attachments) {
            this.attachments = attachments;
            return this;
        }

        public ViewOrderDtoBuilder setExternalOrderId(String externalOrderId) {
            this.externalOrderId = externalOrderId;
            return this;
        }

        public ViewOrderDtoBuilder setCustomer(String customer) {
            this.customer = customer;
            return this;
        }

        public ViewOrderDtoBuilder setInvoiceNumber(String invoiceNumber) {
            this.invoiceNumber = invoiceNumber;
            return this;
        }

        public ViewOrderDtoBuilder setPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public ViewOrderDtoBuilder setDueDate(LocalDate dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public ViewOrderDtoBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public ViewOrderDtoBuilder setCreateDate(Date createDate) {
            this.createDate = createDate;
            return this;
        }

        public ViewOrderDtoBuilder setStatus(String status) {
            this.status = status;
            return this;
        }

        public FetchOrderDto createViewOrderDto() {
            return new FetchOrderDto(id, items, attachments, externalOrderId, customer, invoiceNumber, price, dueDate, description, createDate, status);
        }
    }

    public static FetchOrderDto createFetchOrderDto(Order order) {
        return new FetchOrderDto.ViewOrderDtoBuilder()
                .setId(order.getId())
                .setItems(order.getItems())
                .setAttachments(order.getAttachments())
                .setExternalOrderId(order.getExternalOrderId())
                .setCustomer(order.getCustomer())
                .setInvoiceNumber(order.getInvoiceNumber())
                .setPrice(order.getPrice())
                .setDueDate(order.getDueDate())
                .setDescription(order.getDescription())
                .setCreateDate(order.getCreateDate())
                .setStatus(order.getStatus())
                .createViewOrderDto();
    }
}
