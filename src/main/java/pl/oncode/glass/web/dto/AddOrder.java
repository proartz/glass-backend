package pl.oncode.glass.web.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class AddOrder {

    private Integer id;
    private String externalOrderId;
    private String customer;
    private List<ViewItem> itemsList;
    private List<ViewAttachment> attachmentsList;
    private String invoice_number;
    private BigDecimal price;
    private Date realisationDate;
    private String description;
    private Date createDate;
    private String status;

}
