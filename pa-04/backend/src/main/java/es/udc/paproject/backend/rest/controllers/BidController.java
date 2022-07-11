package es.udc.paproject.backend.rest.controllers;

import static es.udc.paproject.backend.rest.dtos.BidConversor.toBidDto;
import static es.udc.paproject.backend.rest.dtos.BidConversor.toBidSummaryDtos;
import static es.udc.paproject.backend.rest.dtos.ProductConversor.toProductDto;
import static es.udc.paproject.backend.rest.dtos.ProductConversor.toInsertedProductsSummaryDtos;

import es.udc.paproject.backend.model.entities.Bid;
import es.udc.paproject.backend.model.entities.Product;
import es.udc.paproject.backend.model.exceptions.BidMinimumPriceException;
import es.udc.paproject.backend.model.exceptions.BidTimeExpiredException;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.services.BidService;
import es.udc.paproject.backend.model.services.Block;
import es.udc.paproject.backend.rest.common.ErrorsDto;
import es.udc.paproject.backend.rest.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("/bid")
public class BidController {

    private final static String BID_MINIMUM_PRICE_EXCEPTION_CODE = "project.exceptions.BidMinimumPriceException";
    private final static String BID_TIME_EXPIRED_EXCEPTION_CODE = "project.exceptions.BidTimeExpiredException";

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private BidService bidService;

    @ExceptionHandler(BidMinimumPriceException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorsDto handleBidMinimumPriceException(BidMinimumPriceException exception, Locale locale) {

        String errorMessage = messageSource.getMessage(BID_MINIMUM_PRICE_EXCEPTION_CODE, null,
                BID_MINIMUM_PRICE_EXCEPTION_CODE, locale);

        return new ErrorsDto(errorMessage);

    }

    @ExceptionHandler(BidTimeExpiredException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorsDto handleBidTimeExpiredException(BidTimeExpiredException exception, Locale locale) {

        String errorMessage = messageSource.getMessage(BID_TIME_EXPIRED_EXCEPTION_CODE, null,
                BID_TIME_EXPIRED_EXCEPTION_CODE, locale);

        return new ErrorsDto(errorMessage);

    }

    @PostMapping("/products/insertProduct")
    public ProductDto insertProduct(@RequestAttribute Long userId, @Validated @RequestBody InsertProductParamsDto params)
            throws InstanceNotFoundException {

        String description = "";
        String shippingInfo = "";
        if (params.getDescription() != null) description = params.getDescription();
        if (params.getShippingInfo() != null) shippingInfo = params.getShippingInfo();

        return toProductDto(bidService.insertProduct(userId, params.getCategoryId(), params.getName(),
                description, params.getBidMinutesDuration(), params.getInitialPrice(),
                shippingInfo));
    }

    @PostMapping("/bids/bidOnAProduct")
    public BidDto bidOnAProduct(@RequestAttribute Long userId, @Validated @RequestBody BidOnAProductParamsDto params)
            throws InstanceNotFoundException, BidTimeExpiredException, BidMinimumPriceException {

        return toBidDto(bidService.bidOnAProduct(userId, params.getProductId(), params.getMaxBid()));

    }

    @GetMapping("/products")
    public BlockDto<ProductSummaryDto> findInsertedProducts(@RequestAttribute Long userId,
                                                            @RequestParam(defaultValue = "0") int page) {

        Block<Product> orderBlock = bidService.findInsertedProducts(userId, page, 2);

        return new BlockDto<>(toInsertedProductsSummaryDtos(orderBlock.getItems()), orderBlock.getExistMoreItems());

    }

    @GetMapping("/bids")
    public BlockDto<BidSummaryDto> findDoneBids(@RequestAttribute Long userId, @RequestParam(defaultValue = "0") int page) {

        Block<Bid> orderBlock = bidService.findDoneBids(userId, page, 2);

        return new BlockDto<>(toBidSummaryDtos(orderBlock.getItems()), orderBlock.getExistMoreItems());

    }

}
