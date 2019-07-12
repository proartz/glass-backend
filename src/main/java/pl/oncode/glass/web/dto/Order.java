package pl.oncode.glass.web.dto;

import java.math.BigDecimal;
import java.util.Date;

public class Order {

    private Integer id;
    private String customer;
    private String invoiceNumber;
    private BigDecimal price;
    private Date realisationDate;
    private Date createDate;
    private String status;

}
