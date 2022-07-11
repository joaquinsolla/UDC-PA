package es.udc.paproject.backend.rest.dtos;
import es.udc.paproject.backend.model.entities.Bid;

import java.math.BigDecimal;

public class ProductDto {

    private Long id;
    private Long categoryId;
    private String name;
    private String description;
    private String user;
    private long publicationDate;
    private String minutesLeft;
    private BigDecimal initialPrice;
    private BigDecimal currentPrice;
    private String shippingInfo;
    private boolean hasBids;

    public ProductDto() {
    }

    public ProductDto(Long id, Long categoryId, String name, String description, String user, long publicationDate, long minutesLeft, BigDecimal initialPrice,
                      BigDecimal currentPrice, String shippingInfo, Bid winnerBid) {

        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.user = user;
        this.publicationDate = publicationDate;
        if (minutesLeft > 0) this.minutesLeft = Long. toString(minutesLeft);
        else this.minutesLeft = "0 - Expired";
        this.initialPrice = initialPrice;
        this.currentPrice = currentPrice;
        this.shippingInfo = shippingInfo;
        this.hasBids = (winnerBid != null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public long getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(long publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getMinutesLeft() {
        return minutesLeft;
    }

    public void setMinutesLeft(String minutesLeft) {
        this.minutesLeft = minutesLeft;
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

    public boolean getHasBids() {
        return hasBids;
    }

    public void setHasBids(boolean hasBids) {
        this.hasBids = hasBids;
    }

    public boolean isHasBids() {
        return hasBids;
    }
}
