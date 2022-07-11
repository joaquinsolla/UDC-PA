import {useEffect} from 'react';
import {useDispatch} from 'react-redux';
import {BrowserRouter as Router} from 'react-router-dom';

import Header from './Header';
import Body from './Body';
import Footer from './Footer';
import users from '../../users';
import catalog from '../../catalog';

const App = () => {

    const dispatch = useDispatch();

    useEffect(() => {

        dispatch(users.actions.tryLoginFromServiceToken(
            () => dispatch(users.actions.logout())));

        dispatch(catalog.actions.findAllCategories());

    });

    return (
        <div>
            <Router>
                <div>
                    <Header/>
                    <Body/>
                </div>
            </Router>
            <Footer/>
        </div>
    );

}
    
export default App;
