package es.udc.paproject.backend.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Bid {

    private Long id;
    private User user;          //Usuario que realiza la puja
    private Product product;    //Producto sobre el que se hace la puja
    private LocalDateTime date;
    private BigDecimal maxBid;  //Cantidad máxima dispuesto a pagar
    //Estado de la puja es calculable segun el correo del ganador en Product

    public Bid(){}

    public Bid(User user, Product product, BigDecimal maxBid) {
        this.user = user;
        this.product = product;
        this.date = LocalDateTime.now();
        this.maxBid = maxBid;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name = "userId")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name = "productId")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public BigDecimal getMaxBid() {
        return maxBid;
    }

    public void setMaxBid(BigDecimal maxBid) {
        this.maxBid = maxBid;
    }
}
