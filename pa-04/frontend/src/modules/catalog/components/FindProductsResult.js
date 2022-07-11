import {useSelector, useDispatch} from 'react-redux';
import {FormattedMessage} from 'react-intl';

import * as selectors from '../selectors';
import * as actions from '../actions';
import {Pager} from '../../common';
import Products from './Products';

const FindProductsResult = () => {

    const productSearch = useSelector(selectors.getProductSearch);
    const categories = useSelector(selectors.getCategories);
    const dispatch = useDispatch();

    if (!productSearch) {
        return null;
    }

    if (productSearch.result.items.length === 0) {
        return (
            <div className="alert alert-danger" role="alert">
                <FormattedMessage id='project.catalog.FindProductsResult.noProductsFound'/>
            </div>
        );
    }

    return (

        <div>
            <Products products={productSearch.result.items} categories={categories}/>
            <Pager
                back={{
                    enabled: productSearch.criteria.page >= 1,
                    onClick: () => dispatch(actions.previousFindProductsResultPage(productSearch.criteria))}}
                next={{
                    enabled: productSearch.result.existMoreItems,
                    onClick: () => dispatch(actions.nextFindProductsResultPage(productSearch.criteria))}}/>
        </div>

    );
}

export default FindProductsResult;