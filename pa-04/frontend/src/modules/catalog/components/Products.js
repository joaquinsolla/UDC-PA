import {FormattedMessage, FormattedNumber} from 'react-intl';
import PropTypes from 'prop-types';

import * as selectors from '../selectors';
import {ProductLink} from '../../common';

const Products = ({products, categories}) => (

    <table className="table table-striped table-hover">
        <thead>
        <tr>
            <th scope="col">
                <FormattedMessage id='project.global.fields.department'/>
            </th>
            <th scope="col">
                <FormattedMessage id='project.global.fields.name'/>
            </th>
            <th scope="col">
                <FormattedMessage id='project.global.fields.currentPrice'/>
            </th>
            <th scope="col">
                <FormattedMessage id='project.global.fields.minutesLeft'/>
            </th>
        </tr>
        </thead>

        <tbody>
        {products.map(product =>
            <tr key={product.id}>
                <td>{selectors.getCategoryName(categories, product.categoryId)}</td>
                <td><ProductLink id={product.id} name={product.name}/></td>
                <td><FormattedNumber value={product.currentPrice} style="currency" currency="EUR"/></td>
                <td>{product.minutesLeft}</td>
            </tr>
        )}
        </tbody>
    </table>

);

Products.propTypes = {
    products: PropTypes.array.isRequired,
    categories: PropTypes.array.isRequired
};

export default Products;