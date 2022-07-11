import {config, appFetch} from './appFetch';

export const bidOnAProduct = (productId, maxBid,
                              onSuccess, onErrors) =>
    appFetch(`/bid/bids/bidOnAProduct`,
        config('POST', {productId, maxBid}), onSuccess, onErrors);

export const updateMaxBid = (productId, maxBid, onSuccess, onErrors) =>
    appFetch(`/bid/bids/updateMaxBid`,
        config('POST', {productId, maxBid}), onSuccess, onErrors);

export const insertProduct = (categoryId, name, description,
                              bidMinutesDuration, initialPrice, shippingInfo,
                              onSuccess, onErrors) =>
    appFetch(`/bid/products/insertProduct`,
        config('POST', {categoryId, name, description,
            bidMinutesDuration, initialPrice, shippingInfo}), onSuccess, onErrors);

export const findDoneBids = ({page}, onSuccess) =>
    appFetch(`/bid/bids?page=${page}`, config('GET'), onSuccess);


export const findInsertedProducts = ({page}, onSuccess) =>
    appFetch(`/bid/products?page=${page}`,
        config('GET'), onSuccess);