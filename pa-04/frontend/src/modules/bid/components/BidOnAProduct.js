import {useState} from 'react';
import {useSelector, useDispatch} from 'react-redux';
import {FormattedMessage, FormattedNumber} from 'react-intl';
import PropTypes from 'prop-types';

import {Errors, Success} from '../../common';
import * as actions from '../actions';
import * as selectors from '../../catalog/selectors';

const BidOnAProduct = ({productId}) => {

    const product = useSelector(selectors.getProduct);
    const dispatch = useDispatch();
    const [backendErrors, setBackendErrors] = useState(null);
    const [bidSuccess, setBidSuccess] = useState(null);
    let form;
    let [maxBid, setMaxBid] = useState(null);
    let minimumMsgId = "project.Bid.minimumEqualOrHigher"
    if (product.hasBids){
        minimumMsgId = "project.Bid.minimumHigher"
    }

    const handleSubmit = event => {

        event.preventDefault();

        if (form.checkValidity()) {

            dispatch(actions.bidOnAProduct(productId, maxBid,
                () => {
                    setBackendErrors(null);
                    setBidSuccess(<FormattedMessage id="project.Bid.bidDone"/>)
                },
                errors => {
                    setBidSuccess(null)
                    setBackendErrors(errors)
                }));

        } else {

            setBackendErrors(null);
            form.classList.add('was-validated');

        }

    }

    return (
        <div>
            <Success message={bidSuccess} onClose={() => setBidSuccess(null)}/>
            <Errors errors={backendErrors} onClose={() => setBackendErrors(null)}/>
            <form ref={node => form = node}
                  className="needs-validation" noValidate
                  onSubmit={e => handleSubmit(e)}>
                <div>
                    <label id="minimumBidMessage">
                        <FormattedMessage id={minimumMsgId}/> <FormattedNumber value={product.currentPrice} style="currency" currency="EUR"/>
                    </label>
                </div>
                <div className="form-group row">
                    <label id="myBidMessage" htmlFor="price" className="offset-md-5 col-md-1 col-form-label">
                        <FormattedMessage id="project.global.fields.myBid"/>
                    </label>
                    <div className="col-md-2">
                        <input type="number" id="price" className="form-control"
                               value={maxBid}
                               onChange={e => setMaxBid(Number(e.target.value))}
                               autoFocus
                               min={product.currentPrice}
                               step="any"/>
                        <div className="invalid-feedback">
                            <FormattedMessage id='project.global.validator.incorrectQuantity'/>
                        </div>
                    </div>
                </div>
                <div className="form-group row">
                    <div className="offset-md-6 col-md-2">
                        <button id="placeBidButton" type="submit" className="btn btn-primary">
                            <FormattedMessage id="project.Bid.bid"/>
                        </button>
                    </div>
                </div>
            </form>
        </div>
    );

}


BidOnAProduct.propTypes = {
    productId: PropTypes.number.isRequired
};

export default BidOnAProduct;
