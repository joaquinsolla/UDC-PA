import {FormattedMessage, FormattedDate, FormattedTime, FormattedNumber} from "react-intl";
import PropTypes from "prop-types";

import ProductLink from "../../common/components/ProductLink";

const InsertedProducts = ({insertedProducts}) => (

    <table className="table table-striped table-hover">

        <thead>
            <tr>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.InsertedProductId'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.currentPrice'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.minutesLeft'/>
                </th>
                <th scope="col">
                    <FormattedMessage id='project.global.fields.currentWinner'/>
                </th>
            </tr>
        </thead>

        <tbody>
            {insertedProducts.map(insertedProduct =>
                <tr key={insertedProduct.id}>
                    <td>
                        <ProductLink id={insertedProduct.id} name={insertedProduct.name}/>
                    </td>
                    <td>
                        <FormattedNumber value={insertedProduct.currentPrice}/>
                    </td>
                    <td>
                        {insertedProduct.minutesLeft}
                    </td>
                    <td>
                        {insertedProduct.winnerBidEmail}
                    </td>
                </tr>
            )}
        </tbody>

    </table>
);

InsertedProducts.propTypes = {
    insertedProducts: PropTypes.array.isRequired
};

export default InsertedProducts;