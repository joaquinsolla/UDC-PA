import {FormattedMessage, FormattedDate, FormattedNumber} from 'react-intl';
import PropTypes from 'prop-types';
import {ProductLink} from "../../common";

const Bids = ({bids}) => (

    <table className="table table-striped table-hover">

        <thead>
        <tr>
            <th scope="col">
                <FormattedMessage id='project.global.fields.date'/>
            </th>
            <th scope="col">
                <FormattedMessage id='project.global.fields.product'/>
            </th>
            <th scope="col">
                <FormattedMessage id='project.global.fields.maxbid'/>
            </th>
            <th scope="col">
                <FormattedMessage id='project.global.fields.state'/>
            </th>
        </tr>
        </thead>

        <tbody>
        {bids.map(bid =>
            <tr key={bid.id}>
                <td>
                    <FormattedDate value={new Date(bid.date)}/>
                </td>
                <td><ProductLink id={bid.productId} name={bid.productName}/></td>
                <td>
                    <FormattedNumber value={bid.maxBid} style="currency" currency="EUR"/>
                </td>
                <td>
                    {bid.state}
                </td>
            </tr>
        )}
        </tbody>

    </table>

);

Bids.propTypes = {
    bids: PropTypes.array.isRequired
};

export default Bids;