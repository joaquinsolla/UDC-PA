import backend from '../../backend';
import * as actionTypes from './actionTypes';

const productUpdated = bidResult => ({
    type: actionTypes.PRODUCT_UPDATED,
    bidResult
});

export const bidOnAProduct = (productId, maxBid,
    onSuccess, onErrors) => dispatch =>
    backend.bidService.bidOnAProduct(productId, maxBid,
    bidResult => {
            dispatch(productUpdated(bidResult));
            onSuccess();
        },
        onErrors);

const insertProductCompleted = (productId) => ({
    type: actionTypes.INSERT_PRODUCT_COMPLETED,
    productId
});

export const insertProduct = (categoryId, name, description,
                              bidMinutesDuration, initialPrice, shippingInfo,
                              onSuccess, onErrors) => dispatch =>
    backend.bidService.insertProduct(categoryId, name, description,
        bidMinutesDuration, initialPrice, shippingInfo, ({id}) => {
            dispatch(insertProductCompleted(id));
            onSuccess(id);
        },
        onErrors);

const findDoneBidsCompleted = bidSearch => ({
    type: actionTypes.FIND_DONE_BIDS_COMPLETED,
    bidSearch
});

const clearBidSearch = () => ({
    type: actionTypes.CLEAR_BID_SEARCH
});

export const findDoneBids = criteria => dispatch => {

    dispatch(clearBidSearch());
    backend.bidService.findDoneBids(criteria,
        result => dispatch(findDoneBidsCompleted({criteria, result})));

}

export const previousFindDoneBidsResultPage = criteria =>
    findDoneBids({page: criteria.page-1});

export const nextFindDoneBidsResultPage = criteria =>
    findDoneBids({page: criteria.page+1});

const clearInsertedProductsSearch = () => ({
    type: actionTypes.CLEAR_INSERTED_PRODUCTS_SEARCH
});

const findInsertedProductsCompleted = insertedProductsSearch => ({
    type: actionTypes.FIND_INSERTED_PRODUCTS_COMPLETED,
    insertedProductsSearch
});

export const findInsertedProducts = criteria => dispatch => {

    dispatch(clearInsertedProductsSearch());
    backend.bidService.findInsertedProducts(criteria,
            result => dispatch(findInsertedProductsCompleted({criteria, result})));

}

export const previousFindInsertedProductsResultPage = criteria =>
    findInsertedProducts({page: criteria.page-1});

export const nextFindInsertedProductsResultPage = criteria =>
    findInsertedProducts({page: criteria.page+1});

