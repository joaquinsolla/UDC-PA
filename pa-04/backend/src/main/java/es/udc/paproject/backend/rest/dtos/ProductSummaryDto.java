package es.udc.paproject.backend.rest.dtos;

import java.math.BigDecimal;

public class ProductSummaryDto {

    private Long id;
    private Long categoryId;
    private String name;
    private BigDecimal currentPrice;
    private String minutesLeft;
    private String winnerBidEmail;


    public ProductSummaryDto() {}

    public ProductSummaryDto(Long id, Long categoryId, String name, BigDecimal currentPrice, long minutesLeft) {

        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.currentPrice = currentPrice;
        if (minutesLeft > 0) this.minutesLeft = Long.toString(minutesLeft);
        else this.minutesLeft = "0 - Expired";

    }

    public ProductSummaryDto(Long id, String name,BigDecimal currentPrice, long minutesLeft) {

        this.id = id;
        this.name = name;
        this.currentPrice = currentPrice;
        if (minutesLeft > 0) this.minutesLeft = Long.toString(minutesLeft);
        else this.minutesLeft = "0 - Expired";
        this.winnerBidEmail = "N/A - Sin pujas";

    }

    public ProductSummaryDto(Long id, String name,BigDecimal currentPrice, long minutesLeft, String winnerBidEmail) {

        this.id = id;
        this.name = name;
        this.currentPrice = currentPrice;
        if (minutesLeft > 0) this.minutesLeft = Long.toString(minutesLeft);
        else this.minutesLeft = "0 - Expired";
        this.winnerBidEmail = winnerBidEmail;

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

    public void setCategoryId(String category) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getMinutesLeft() {
        return minutesLeft;
    }

    public void setMinutesLeft(String minutesLeft) {
        this.minutesLeft = minutesLeft;
    }

    public String getWinnerBidEmail() {
        return winnerBidEmail;
    }

    public void setWinnerBidEmail(String winnerBidEmail) {
        this.winnerBidEmail = winnerBidEmail;
    }

}
