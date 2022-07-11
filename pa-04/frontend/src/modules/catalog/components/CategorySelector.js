import {useSelector} from 'react-redux';
import {FormattedMessage} from 'react-intl';
import PropTypes from 'prop-types';

import * as selectors from '../selectors';

const CategorySelector = (selectProps) => {

    const categories = useSelector(selectors.getCategories);

    return(

        <select {...selectProps}>

            <FormattedMessage id='project.catalog.CategorySelector.allDepartments'>
                {message => (<option value="">{message}</option> )}
            </FormattedMessage>

            {categories && categories.map(category =>
                <option key={category.id} value={category.id}>{category.name}</option>)
            }

        </select>

    );

}

CategorySelector.propTypes = {
    selectProps: PropTypes.object
};

export default CategorySelector;