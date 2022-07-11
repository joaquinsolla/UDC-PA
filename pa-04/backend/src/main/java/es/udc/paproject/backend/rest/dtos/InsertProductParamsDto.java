package es.udc.paproject.backend.rest.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class InsertProductParamsDto {

    private Long categoryId;
    private String name;
    private String description;
    private int bidMinutesDuration;
    private BigDecimal initialPrice;
    private String shippingInfo;

    @NotNull
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long productId) {
        this.categoryId = productId;
    }

    @NotNull
    public String getName(){
        return name;
    }

    public void setName(){
        this.name = name;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(){
        this.description = description;
    }

    @Min(value = 1)
    public int getBidMinutesDuration() {
        return bidMinutesDuration;
    }

    public void setBidMinutesDuration(int bidMinutesDuration) {
        this.bidMinutesDuration = bidMinutesDuration;
    }

    @Min(value = 1)
    public BigDecimal getInitialPrice(){
        return initialPrice;
    }

    public void setInitialPrice(BigDecimal initialPrice){
        this.initialPrice = initialPrice;
    }

    public String getShippingInfo(){
        return shippingInfo;
    }

    public void setShippingInfo(String shippingInfo){
        this.shippingInfo = shippingInfo;
    }

}
