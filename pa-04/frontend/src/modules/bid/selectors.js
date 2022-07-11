const getModuleState = state => state.bid;

export const getBidSearch = state =>
    getModuleState(state).bidSearch;

export const getInsertedProductsSearch = state =>
    getModuleState(state).insertedProductsSearch;
