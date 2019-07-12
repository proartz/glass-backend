package pl.oncode.glass.web.dto;

import java.util.List;

public class Item {

    private Integer id;
    private Integer orderId;
    private Integer materialId;
    private List<Operation> operations;
    private Double width;
    private Double height;
    private Double depth;
    private Integer quantity;
    private String note;

}
