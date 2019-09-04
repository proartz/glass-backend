package pl.oncode.glass.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "`order`")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Item> items;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Attachment> attachments;

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

    @NotEmpty(message = "Status cannot be empty")
    private String status;

    public Order() {
    }

    public Order(Integer id,
                 List<Item> items,
                 List<Attachment> attachments,
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
        return "Order{" +
                "id=" + id +
                ", items=" + items +
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

    public static class OrderBuilder {
        private Integer id;
        private List<Item> items;
        private List<Attachment> attachments;
        private String externalOrderId;
        private String customer;
        private String invoiceNumber;
        private BigDecimal price;
        private LocalDate dueDate;
        private Date createDate;
        private String status;

        public OrderBuilder() {
            this.items = new ArrayList<>();
            this.attachments = new ArrayList<>();
        }

        public OrderBuilder setId(Integer id) {
            this.id = id;
            return this;
        }

        public OrderBuilder setItems(List<Item> items) {
            this.items = items;
            return this;
        }

        public OrderBuilder addItem(Item item) {
            this.items.add(item);
            return this;
        }

        public OrderBuilder setAttachments(List<Attachment> attachments) {
            this.attachments = attachments;
            return this;
        }

        public OrderBuilder addAttachment(Attachment attachment) {
            this.attachments.add(attachment);
            return this;
        }

        public OrderBuilder setExternalOrderId(String externalOrderId) {
            this.externalOrderId = externalOrderId;
            return this;
        }

        public OrderBuilder setCustomer(String customer) {
            this.customer = customer;
            return this;
        }

        public OrderBuilder setInvoiceNumber(String invoiceNumber) {
            this.invoiceNumber = invoiceNumber;
            return this;
        }

        public OrderBuilder setPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public OrderBuilder setDueDate(LocalDate dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public OrderBuilder setCreateDate(Date createDate) {
            this.createDate = createDate;
            return this;
        }

        public OrderBuilder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Order createOrder() {
            return new Order(id, items, attachments, externalOrderId, customer, invoiceNumber, price, dueDate, createDate, status);
        }
    }

    public void registerRelations() {
        for(Item item : this.getItems()) {
            for(Operation operation : item.getOperations()) {
                operation.setItem(item);
            }
            item.setOrder(this);
        }

        for(Attachment attachment : this.getAttachments()) {
            attachment.setOrder(this);
        }
    }
}
