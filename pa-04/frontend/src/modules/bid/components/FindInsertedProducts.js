import {useEffect} from "react";
import {useDispatch} from "react-redux";
import {useHistory} from "react-router-dom";

import * as actions from '../actions';

const FindInsertedProducts = () => {

    const dispatch = useDispatch();
    const history = useHistory();

    useEffect( () => {

        dispatch(actions.findInsertedProducts({page: 0}));
        history.push('/bid/find-inserted-products-result');

    });

    return null;

}

export default FindInsertedProducts;