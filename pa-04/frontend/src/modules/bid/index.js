import * as actions from './actions';
import reducer from './reducer';
import * as selectors from './selectors';
import * as actionTypes from './actionTypes';

export {default as BidOnAProduct} from './components/BidOnAProduct';
export {default as InsertProduct} from './components/InsertProduct';
export {default as FindDoneBids} from './components/FindDoneBids';
export {default as FindDoneBidsResult} from './components/FindDoneBidsResult';
export {default as ButtonToInsertProduct} from './components/ButtonToInsertProduct';
export {default as FindInsertedProducts} from './components/FindInsertedProducts';
export {default as FindInsertedProductsResult} from './components/FindInsertedProductsResult';

// eslint-disable-next-line
export default {actions, reducer, selectors, actionTypes};