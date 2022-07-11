package es.udc.paproject.backend.rest.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

public class BidDto {

    private Long id;
    private String state;
    private String minutesLeft;
    private BigDecimal productCurrentPrice;

    public BidDto(){}

    public BidDto(Long id, Long winnerBidId, long productExpirationDate, BigDecimal productCurrentPrice){

        this.id = id;
        if (winnerBidId == id) this.state = "Ganando";
        else this.state = "Perdiendo";
        this.minutesLeft = Long.toString((productExpirationDate - LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES).atZone(ZoneOffset.systemDefault()).toInstant().toEpochMilli())/60000);
        this.productCurrentPrice = productCurrentPrice;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMinutesLeft() {
        return minutesLeft;
    }

    public void setMinutesLeft(String minutesLeft) {
        this.minutesLeft = minutesLeft;
    }

    public BigDecimal getProductCurrentPrice() {
        return productCurrentPrice;
    }

    public void setProductCurrentPrice(BigDecimal productCurrentPrice) {
        this.productCurrentPrice = productCurrentPrice;
    }
}
