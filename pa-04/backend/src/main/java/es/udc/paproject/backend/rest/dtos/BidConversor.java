package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.entities.Bid;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class BidConversor {

    private BidConversor() {}

    public final static List<BidSummaryDto> toBidSummaryDtos(List<Bid> bids) {
        return bids.stream().map(b -> toBidSummaryDto(b)).collect(Collectors.toList());
    }

    public final static BidDto toBidDto(Bid bid) {
        return new BidDto(bid.getId(), bid.getProduct().getWinnerBid().getId(), toMillis(bid.getProduct().getExpirationDate()), bid.getProduct().getCurrentPrice());
    }

    private final static BidSummaryDto toBidSummaryDto(Bid bid) {

        return new BidSummaryDto(bid.getId(), toMillis(bid.getDate()), bid.getProduct().getId(), bid.getProduct().getName(), bid.getMaxBid(), bid.getProduct().getWinnerBid().getId());

    }

    private final static long toMillis(LocalDateTime date) {
        return date.truncatedTo(ChronoUnit.MINUTES).atZone(ZoneOffset.systemDefault()).toInstant().toEpochMilli();
    }

}
