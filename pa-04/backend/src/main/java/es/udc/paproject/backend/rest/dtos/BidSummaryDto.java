package es.udc.paproject.backend.rest.dtos;

import java.math.BigDecimal;
import java.time.Instant;

public class BidSummaryDto {

    private Long id;
    private long date;
    private Long productId;
    private String productName;
    private BigDecimal maxBid;
    private String state;

    public BidSummaryDto(Long id, long date, Long productId, String productName, BigDecimal maxBid, Long currentWinnerBidId){

        this.id = id;
        this.date = date;
        this.productId = productId;
        this.productName = productName;
        this.maxBid = maxBid;
        if (date > Instant.now().toEpochMilli()){
            if(id == currentWinnerBidId) this.state = "Ganadora";
            else this.state = "Perdedora";
        } else {
            if(id == currentWinnerBidId) this.state = "Ganando";
            else this.state = "Perdiendo";
        }

    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getMaxBid() {
        return maxBid;
    }

    public void setMaxBid(BigDecimal maxBid) {
        this.maxBid = maxBid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
