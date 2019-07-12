package pl.oncode.glass.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
public class Order implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    @OneToMany(mappedBy = "orderId")
    private List<Item> items;
    @OneToMany(mappedBy = "orderId")
    private List<Attachment> attachments;
    private String externalOrderId;
    private String customer;
    private String invoiceNumber;
    private BigDecimal price;
    private Date realisationDate;
    private Date createDate;
    private String status;

    public Order(String externalOrderId,
                 String customer,
                 String invoiceNumber,
                 BigDecimal price,
                 Date realisationDate,
                 Date createDate) {
        this(null, null, externalOrderId, customer, invoiceNumber,
                price, realisationDate, createDate, "NEW");

    }

    public Order(List<Item> items,
                 List<Attachment> attachments,
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
}
