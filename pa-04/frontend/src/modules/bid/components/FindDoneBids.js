import {useEffect} from 'react';
import {useDispatch} from 'react-redux';
import {useHistory} from 'react-router-dom';

import * as actions from '../actions';

const FindDoneBids = () => {

    const dispatch = useDispatch();
    const history = useHistory();

    useEffect(() => {

        dispatch(actions.findDoneBids({page: 0}));
        history.push('/bid/find-done-bids-result');

    });

    return null;

}

export default FindDoneBids;
