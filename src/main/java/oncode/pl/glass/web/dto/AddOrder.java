package oncode.pl.glass.web.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class AddOrder extends ViewOrders {

    private Integer id;
    private String externalOrderId;
    private String customer;
    private List<Item> itemsList;
    private List<Attachment> attachmentsList;
    private String invoice_number;
    private BigDecimal price;
    private Date realisationDate;
    private String description;
    private Date createDate;
    private String status;

}
