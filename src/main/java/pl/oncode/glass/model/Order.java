package pl.oncode.glass.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "`order`")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Item> items;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Attachment> attachments;
    private String externalOrderId;
    private String customer;
    private String invoiceNumber;
    private BigDecimal price;
    private Date realisationDate;
    private Date createDate;
    private String status;

    public Order() {
    }

    public Order(Set<Item> items,
                 Set<Attachment> attachments,
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public Set<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(Set<Attachment> attachments) {
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
        return "Order{" +
                "id=" + id +
                ", items=" + items +
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

    public static class OrderBuilder {
        private Set<Item> items;
        private Set<Attachment> attachments;
        private String externalOrderId;
        private String customer;
        private String invoiceNumber;
        private BigDecimal price;
        private Date realisationDate;
        private Date createDate;
        private String status;

        public OrderBuilder() {
            this.items = new HashSet<>();
            this.attachments = new HashSet<>();
        }

        public OrderBuilder setItems(Set<Item> items) {
            this.items = items;
            return this;
        }

        public OrderBuilder addItem(Item item) {
            this.items.add(item);
            return this;
        }

        public OrderBuilder setAttachments(Set<Attachment> attachments) {
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

        public OrderBuilder setRealisationDate(Date realisationDate) {
            this.realisationDate = realisationDate;
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
            return new Order(items, attachments, externalOrderId, customer, invoiceNumber, price, realisationDate, createDate, status);
        }
    }
}
