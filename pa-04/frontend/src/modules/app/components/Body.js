import {useSelector} from 'react-redux';
import {Route, Switch} from 'react-router-dom';

import AppGlobalComponents from './AppGlobalComponents';
import Home from './Home';
import {Login, SignUp, UpdateProfile, ChangePassword, Logout} from '../../users';
import users from '../../users';

import {FindProductsResult, ProductDetails} from '../../catalog';
import {InsertProduct, FindDoneBids, FindDoneBidsResult, ButtonToInsertProduct, FindInsertedProducts, FindInsertedProductsResult} from '../../bid';

const Body = () => {

    const loggedIn = useSelector(users.selectors.isLoggedIn);
    
   return (

        <div className="container">
            <br/>
            <AppGlobalComponents/>
            <Switch>
                {loggedIn && <Route exact path="/"><ButtonToInsertProduct/></Route>}
                <Route exact path="/"><Home/></Route>
                <Route exact path="/catalog/find-products-result"><FindProductsResult/></Route>
                <Route exact path="/catalog/product-details/:id"><ProductDetails/></Route>
                {loggedIn && <Route exact path="/insert-product"><InsertProduct/></Route>}
                {loggedIn && <Route exact path="/bid/find-done-bids"><FindDoneBids/></Route>}
                {loggedIn && <Route exact path="/bid/find-done-bids-result"><FindDoneBidsResult/></Route>}
                {loggedIn && <Route exact path="/bid/find-inserted-products"><FindInsertedProducts/></Route>}
                {loggedIn && <Route exact path="/bid/find-inserted-products-result"><FindInsertedProductsResult/></Route>}
                {loggedIn && <Route exact path="/users/update-profile"><UpdateProfile/></Route>}
                {loggedIn && <Route exact path="/users/change-password"><ChangePassword/></Route>}
                {loggedIn && <Route exact path="/users/logout"><Logout/></Route>}
                {!loggedIn && <Route exact path="/users/login"><Login/></Route>}
                {!loggedIn && <Route exact path="/users/signup"><SignUp/></Route>}
                <Route><Home/></Route>
            </Switch>
        </div>

    );

};

export default Body;
