package oncode.pl.glass.web.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ViewOrders {

    private Integer orderId;
    private Date createDate;
    private String customer;
    private Date realisationDate;
    private String invoice_number;
    private BigDecimal price;
    private String status;

}
