import * as actionTypes from './actionTypes';
import * as selectors from './selectors';
import backend from '../../backend';

const findAllCategoriesCompleted = categories => ({
    type: actionTypes.FIND_ALL_CATEGORIES_COMPLETED,
    categories
});

export const findAllCategories = () => (dispatch, getState) => {

    const categories = selectors.getCategories(getState());

    if (!categories) {

        backend.catalogService.findAllCategories(
            categories => dispatch(findAllCategoriesCompleted(categories))
        );

    }

}

const findProductsCompleted = productSearch => ({
    type: actionTypes.FIND_PRODUCTS_COMPLETED,
    productSearch
});

export const findProducts = criteria => dispatch => {

    dispatch(clearProductSearch());
    backend.catalogService.findProducts(criteria,
        result => dispatch(findProductsCompleted({criteria, result})));

}

export const previousFindProductsResultPage = criteria =>
    findProducts({...criteria, page: criteria.page-1});

export const nextFindProductsResultPage = criteria =>
    findProducts({...criteria, page: criteria.page+1});

const clearProductSearch = () => ({
    type: actionTypes.CLEAR_PRODUCT_SEARCH
});

const findProductByIdCompleted = product => ({
    type: actionTypes.FIND_PRODUCT_BY_ID_COMPLETED,
    product
});

export const findProductById = id => dispatch => {
    backend.catalogService.findByProductId(id,
        product => dispatch(findProductByIdCompleted(product)));
}

export const clearProduct = () => ({
    type: actionTypes.CLEAR_PRODUCT
});
