package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.entities.Product;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class ProductConversor {

    private ProductConversor() {}

    public final static ProductDto toProductDto(Product product) {

        return new ProductDto(product.getId(), product.getCategory().getId(), product.getName(), product.getDescription(), product.getUser().getUserName(),
                toMillis(product.getPublicationDate()), toMinutesLeft(product.getExpirationDate()), product.getInitialPrice(), product.getCurrentPrice(), product.getShippingInfo(), product.getWinnerBid());

    }

    public final static List<ProductSummaryDto> toProductSummaryDtos(List<Product> products) {
        return products.stream().map(p -> toProductSummaryDto(p)).collect(Collectors.toList());
    }

    private final static ProductSummaryDto toProductSummaryDto(Product product) {
        return new ProductSummaryDto(product.getId(), product.getCategory().getId(), product.getName(), product.getCurrentPrice(), toMinutesLeft(product.getExpirationDate()));
    }

    public final static List<ProductSummaryDto> toInsertedProductsSummaryDtos(List<Product> products) {
        return products.stream().map(p -> toInsertedProductSummaryDto(p)).collect(Collectors.toList());
    }

    private final static ProductSummaryDto toInsertedProductSummaryDto(Product product) {
        if (product.getWinnerBid()==null) return new ProductSummaryDto(product.getId(), product.getName() ,product.getCurrentPrice(), toMinutesLeft(product.getExpirationDate()));
        else return new ProductSummaryDto(product.getId(), product.getName() ,product.getCurrentPrice(), toMinutesLeft(product.getExpirationDate()), product.getWinnerBid().getUser().getEmail());
    }

    private final static long toMinutesLeft(LocalDateTime date) {
        return ChronoUnit.MINUTES.between(LocalDateTime.now(), date);
    }

    private final static long toMillis(LocalDateTime date) {
        return date.truncatedTo(ChronoUnit.MINUTES).atZone(ZoneOffset.systemDefault()).toInstant().toEpochMilli();
    }

}
