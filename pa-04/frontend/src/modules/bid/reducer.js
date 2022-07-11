import {combineReducers} from 'redux';

import * as actionTypes from './actionTypes';

const initialState = {
    bidSearch: null,
    insertedProductsSearch: null
};


const bidSearch = (state = initialState.bidSearch, action) => {

    switch (action.type) {

        case actionTypes.FIND_DONE_BIDS_COMPLETED:
            return action.bidSearch;

        case actionTypes.CLEAR_BID_SEARCH:
            return initialState.bidSearch;

        default:
            return state;

    }

}

const insertedProductsSearch = (state = initialState.insertedProductsSearch, action) => {

    switch (action.type) {
        case actionTypes.FIND_INSERTED_PRODUCTS_COMPLETED:
            return action.insertedProductsSearch;

        case actionTypes.CLEAR_INSERTED_PRODUCTS_SEARCH:
            return initialState.insertedProductsSearch;

        default:
            return state;
    }
}

const reducer = combineReducers({
    bidSearch,
    insertedProductsSearch
});

export default reducer;
