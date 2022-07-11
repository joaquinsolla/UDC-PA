import {useState} from 'react';
import {useDispatch} from "react-redux";
import {useHistory} from "react-router-dom";
import {FormattedMessage} from "react-intl";

import CategorySelector from './CategorySelector';
import * as actions from '../actions';

const FindProducts = () => {

    const dispatch = useDispatch();
    const history = useHistory();
    const [categoryId, setCategoryId] = useState('');
    const [keywords, setKeywords] = useState('');

    const handleSubmit = event => {

        event.preventDefault();

        dispatch(actions.findProducts(
            {categoryId: toNumber(categoryId),
                    keywords: keywords.trim(), page: 0}));

        history.push('/catalog/find-products-result');
    }

    const toNumber = value => value.length > 0 ? Number(value) : null;

    return(

        <form className="form-inline mt-2 mt-md-0" onSubmit={e => handleSubmit(e)}>

            <CategorySelector id="categoryId" className="custom-select my-1 mr-sm-2"
                   value={categoryId} onChange={e => setCategoryId(e.target.value)}/>

            <input id="keywords" type="text" className="form-control mr-sm-2"
                   value={keywords} onChange={e => setKeywords(e.target.value)}/>

            <button id="searchProductButton" type="submit" className="btn btn-primary my-2 my-sm-0">
                <FormattedMessage id='project.global.buttons.search'/>
            </button>

        </form>
    );
}

export default FindProducts;