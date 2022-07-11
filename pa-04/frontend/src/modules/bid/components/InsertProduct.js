import {useState} from 'react';
import {useDispatch} from 'react-redux';
import {FormattedMessage} from 'react-intl';
import {useHistory} from 'react-router-dom';

import {Errors} from '../../common';
import * as actions from '../actions';
import CategorySelector from "./CategorySelector";

const InsertProduct = () => {

    const dispatch = useDispatch();
    const history = useHistory();
    const [categoryId, setCategoryId] = useState('');
    const [name, setName] = useState('');
    const [description, setDescription] = useState('');
    const [bidMinutesDuration, setBidMinutesDuration] = useState('');
    const [initialPrice, setInitialPrice] = useState('');
    const [shippingInfo, setShippingInfo] = useState('');
    const [backendErrors, setBackendErrors] = useState(null);
    let form;

    const handleSubmit = event => {

        event.preventDefault();

        if (form.checkValidity()) {

            dispatch(actions.insertProduct(categoryId,
                name.trim(), description.trim(), bidMinutesDuration,
                initialPrice, shippingInfo.trim(),
                (id) => history.push('/catalog/product-details/'+id),
                errors => setBackendErrors(errors)));

        } else {
            setBackendErrors(null);
            form.classList.add('was-validated');
        }

    }

    return (
        <div>
            <div>
            <Errors errors={backendErrors}
                    onClose={() => setBackendErrors(null)}/>
            <div className="card bg-light border-dark">
                <h5 className="card-header">
                    <FormattedMessage id="project.shopping.InsertProduct.title"/>
                </h5>
                <div className="card-body">
                    <form ref={node => form = node}
                          className="needs-validation" noValidate
                          onSubmit={(e) => handleSubmit(e)}>

                        <div className="form-group row">
                            <label htmlFor="category" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.department"/>
                            </label>
                            <div className="col-md-4">
                            <CategorySelector id="productCategoryId" className="custom-select my-1 mr-sm-2"
                                              value={categoryId} onChange={e => setCategoryId(e.target.value)} required/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required'/>
                                </div>
                            </div>
                        </div>
                        <div className="form-group row">
                            <label htmlFor="name" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.name"/>
                            </label>
                            <div className="col-md-4">
                                <input type="text" id="name" className="form-control"
                                       value={name}
                                       onChange={e => setName(e.target.value)}
                                       maxLength={60}
                                       required/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required'/>
                                </div>
                            </div>
                        </div>
                        <div className="form-group row">
                            <label htmlFor="description" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.productDescription"/>
                            </label>
                            <div className="col-md-4">
                                <input type="text" id="description" className="form-control"
                                       value={description}
                                       onChange={e => setDescription(e.target.value)}
                                       maxLength={500}
                                       required/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required'/>
                                </div>
                            </div>
                        </div>
                        <div className="form-group row">
                            <label htmlFor="bidMinutesDuration" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.bidMinutesDuration"/>
                            </label>
                            <div className="col-md-4">
                                <input type="number" id="bidMinutesDuration" className="form-control"
                                       value={bidMinutesDuration}
                                       onChange={e => setBidMinutesDuration(e.target.value)}
                                       min={1}
                                       required/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required'/>
                                </div>
                            </div>
                        </div>
                        <div className="form-group row">
                            <label htmlFor="initialPrice" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.initialPrice"/>
                            </label>
                            <div className="col-md-4">
                                <input type="number" id="initialPrice" className="form-control"
                                       value={initialPrice}
                                       onChange={e => setInitialPrice(e.target.value)}
                                       min={1}
                                       step="any"
                                       required/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required'/>
                                </div>
                            </div>
                        </div>
                        <div className="form-group row">
                            <label htmlFor="shippingInfo" className="col-md-3 col-form-label">
                                <FormattedMessage id="project.global.fields.shippingInfo"/>
                            </label>
                            <div className="col-md-4">
                                <input type="text" id="shippingInfo" className="form-control"
                                       value={shippingInfo}
                                       onChange={e => setShippingInfo(e.target.value)}
                                       maxLength={500}
                                       required/>
                                <div className="invalid-feedback">
                                    <FormattedMessage id='project.global.validator.required'/>
                                </div>
                            </div>
                        </div>

                        <div className="form-group row">
                            <div className="offset-md-3 col-md-1">
                                <button id="confirmInsertProductButton" type="submit" className="btn btn-primary">
                                    <FormattedMessage id="project.global.buttons.insert"/>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        </div>
    );

}

export default InsertProduct;
