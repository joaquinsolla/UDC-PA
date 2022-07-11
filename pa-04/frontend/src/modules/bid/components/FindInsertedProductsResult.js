import {useDispatch, useSelector} from "react-redux";

import * as actions from '../actions';
import * as selectors from '../selectors';
import {FormattedMessage} from "react-intl";
import InsertedProducts from "./InsertedProducts";
import {Pager} from "../../common";

const FindInsertedProductsResult = () => {

    const insertedProductsSearch = useSelector(selectors.getInsertedProductsSearch);
    const dispatch = useDispatch();

    if(!insertedProductsSearch){
        return null;
    }

    if(insertedProductsSearch.result.items.length === 0){
        return (
            <div className="alert alert-info" role="alert">
                <FormattedMessage id='project.bid.FindInsertedProductsResult.noInsertedProducts'/>
            </div>
        );
    }

    return (

        <div>
            <InsertedProducts insertedProducts={insertedProductsSearch.result.items}/>
            <Pager
                back={{
                    enabled: insertedProductsSearch.criteria.page >= 1,
                    onClick: () => dispatch(actions.previousFindInsertedProductsResultPage(insertedProductsSearch.criteria))}}
                next={{
                    enabled: insertedProductsSearch.result.existMoreItems,
                    onClick: () => dispatch(actions.nextFindInsertedProductsResultPage(insertedProductsSearch.criteria))}}/>
        </div>

    );

}

export default FindInsertedProductsResult;