package pl.oncode.glass.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.nio.file.Path;

public class Attachment {

    @Id @GeneratedValue
    private Integer id;
    private Path path;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Integer orderId;

    public Attachment(Path path, Integer orderId) {
        this.path = path;
        this.orderId = orderId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
