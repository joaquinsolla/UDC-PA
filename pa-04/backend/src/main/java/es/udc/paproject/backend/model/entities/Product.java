package es.udc.paproject.backend.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Product {

    private Long id;
    private User user;
    private Category category;
    private String name;
    private String description;
    private LocalDateTime publicationDate;
    private LocalDateTime expirationDate;  //fecha límite - fecha publicacion = duracion subasta
    private BigDecimal initialPrice;
    private BigDecimal currentPrice;
    private String shippingInfo;
    private Bid winnerBid;

    public Product(){}

    public Product(User user, Category category, String name, String description, LocalDateTime expirationDate, BigDecimal initialPrice, String shippingInfo) {
        this.user = user;
        this.category = category;
        this.name = name;
        this.description = description;
        this.publicationDate = LocalDateTime.now();
        this.expirationDate = expirationDate;
        this.initialPrice = initialPrice;
        this.currentPrice = initialPrice;
        this.shippingInfo = shippingInfo;
        this.winnerBid = null;
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

    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name = "categoryId")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public BigDecimal getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(BigDecimal initialPrice) {
        this.initialPrice = initialPrice;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getShippingInfo() {
        return shippingInfo;
    }

    public void setShippingInfo(String shippingInfo) {
        this.shippingInfo = shippingInfo;
    }

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "winnerBidId")
    public Bid getWinnerBid() {
        return winnerBid;
    }

    public void setWinnerBid(Bid winnerBid) {
        this.winnerBid = winnerBid;
    }
}
