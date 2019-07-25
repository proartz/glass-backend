package pl.oncode.glass.web.dto.viewOrder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ViewOrderDto implements Serializable {

    private String externalOrderId;
    private String customer;
    private String invoiceNumber;
    private BigDecimal price;
    private Date dueDate;
    private Date createDate;
    private String status;

    public ViewOrderDto() {
    }

    public ViewOrderDto(String externalOrderId,
                        String customer,
                        String invoiceNumber,
                        BigDecimal price,
                        Date dueDate,
                        Date createDate,
                        String status) {

        this.externalOrderId = externalOrderId;
        this.customer = customer;
        this.invoiceNumber = invoiceNumber;
        this.price = price;
        this.dueDate = dueDate;
        this.createDate = createDate;
        this.status = status;
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

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
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

    public static class ViewOrderBuilder {
        private String externalOrderId;
        private String customer;
        private String invoiceNumber;
        private BigDecimal price;
        private Date dueDate;
        private Date createDate;
        private String status;

        public ViewOrderBuilder setExternalOrderId(String externalOrderId) {
            this.externalOrderId = externalOrderId;
            return this;
        }

        public ViewOrderBuilder setCustomer(String customer) {
            this.customer = customer;
            return this;
        }

        public ViewOrderBuilder setInvoiceNumber(String invoiceNumber) {
            this.invoiceNumber = invoiceNumber;
            return this;
        }

        public ViewOrderBuilder setPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public ViewOrderBuilder setDueDate(Date dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public ViewOrderBuilder setCreateDate(Date createDate) {
            this.createDate = createDate;
            return this;
        }

        public ViewOrderBuilder setStatus(String status) {
            this.status = status;
            return this;
        }

        public ViewOrderDto createViewOrder() {
            return new ViewOrderDto(externalOrderId, customer, invoiceNumber, price, dueDate, createDate, status);
        }
    }
}
