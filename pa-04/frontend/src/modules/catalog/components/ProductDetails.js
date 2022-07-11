import {useEffect} from 'react';
import {useSelector, useDispatch} from 'react-redux';
import {FormattedDate, FormattedMessage, FormattedNumber} from 'react-intl';
import {useParams} from 'react-router-dom';

import users from '../../users';
import * as selectors from '../selectors';
import * as actions from '../actions';
import {BidOnAProduct} from '../../bid';
import {BackLink} from '../../common';

const ProductDetails = () => {

    const loggedIn = useSelector(users.selectors.isLoggedIn);
    const product = useSelector(selectors.getProduct);
    const categories = useSelector(selectors.getCategories);
    const dispatch = useDispatch();
    const {id} = useParams();

    useEffect(() => {

        const productId = Number(id);

        if (!Number.isNaN(productId)) {
            dispatch(actions.findProductById(productId));
        }

        return () => dispatch(actions.clearProduct());

    }, [id, dispatch]);

    if (!product) {
        return null;
    }

    var productDate = new Date(product.publicationDate);

    return (

        <div>

            <BackLink/>

            <div className="card text-center">
                <div className="card-body">
                    <h4 id="productName" className="card-title">{product.name}</h4>
                    <h5 id="productCategory" className="card-title text-muted">
                        <FormattedMessage id='project.global.fields.department'/>:&nbsp;
                        {selectors.getCategoryName(categories, product.categoryId)}
                    </h5>
                    <h6 id="productVendor" className="card-text text-muted">
                        <FormattedMessage id='project.global.fields.vendorUsername'/>:&nbsp;
                        {product.user}
                    </h6>
                    <h6 id="productPublicationDate" className="card-text text-muted">
                        <FormattedMessage id='project.global.fields.publicationDate'/>:&nbsp;
                        <FormattedDate value={productDate}
                                       year= "numeric"
                                       month = "2-digit"
                                       day = "2-digit"
                                       hour= "2-digit"
                                       minute= "2-digit"/>
                    </h6>
                    <h6 id="productMinutesLeft" className="card-text text-muted">
                        <FormattedMessage id='project.global.fields.minutesLeft'/>:&nbsp;
                        {product.minutesLeft}
                    </h6>
                    <p id="productDescription" className="card-text">
                        <FormattedMessage id='project.global.fields.productDescription'/>:&nbsp;
                        {product.description}</p>
                    <p id="productShippingInfo" className="card-text">
                        <FormattedMessage id='project.global.fields.shippingInfo'/>:&nbsp;
                        {product.shippingInfo}</p>
                    <p id="productInitialPrice" className="card-title font-weight-bold">
                        <FormattedMessage id='project.global.fields.initialPrice'/>{': '}
                        <FormattedNumber value={product.initialPrice} style="currency" currency="EUR"/>
                    </p>
                    <p id="productCurrentPrice" className="card-subtitle font-weight-bold">
                        <FormattedMessage id='project.global.fields.currentPrice'/>{': '}
                        {/* eslint-disable-next-line */}
                        <FormattedNumber value={product.currentPrice} style="currency" currency="EUR"/>
                    </p>
                </div>
            </div>

            {loggedIn && (product.minutesLeft !== '0 - Expired') &&
            <div>
                <br/>
                <BidOnAProduct productId={product.id}/>
            </div>
            }

        </div>

    );
}

export default ProductDetails;
