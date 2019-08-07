package pl.oncode.glass.web.dto.viewOrders;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class ViewOrderDto implements Serializable {

    private Integer id;
    private String externalOrderId;
    private String customer;
    private String invoiceNumber;
    private BigDecimal price;
    private LocalDate dueDate;
    private Date createDate;
    private String status;

    public ViewOrderDto() {
    }

    public ViewOrderDto(Integer id, String externalOrderId, String customer, String invoiceNumber, BigDecimal price, LocalDate dueDate, Date createDate, String status) {
        this.id = id;
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

    public static class ViewOrderDtoBuilder {
        private Integer id;
        private String externalOrderId;
        private String customer;
        private String invoiceNumber;
        private BigDecimal price;
        private LocalDate dueDate;
        private Date createDate;
        private String status;

        public ViewOrderDtoBuilder setId(Integer id) {
            this.id = id;
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

        public ViewOrderDtoBuilder setCreateDate(Date createDate) {
            this.createDate = createDate;
            return this;
        }

        public ViewOrderDtoBuilder setStatus(String status) {
            this.status = status;
            return this;
        }

        public ViewOrderDto createViewOrderDto() {
            return new ViewOrderDto(id, externalOrderId, customer, invoiceNumber, price, dueDate, createDate, status);
        }
    }
}
