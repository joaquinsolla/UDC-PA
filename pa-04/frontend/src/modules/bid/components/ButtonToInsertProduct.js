import {useHistory} from 'react-router-dom';
import {FormattedMessage} from "react-intl";
import {useSelector} from "react-redux";
import users from "../../users";

const ButtonToInsertProduct = () => {

    const userName = useSelector(users.selectors.getUserName);
    const history = useHistory();

    function handleSubmit(e) {
        e.preventDefault();
        history.push('/insert-product')
    }

    return(
        <div style={{textAlign:'center'}}>
            <h5>
                <FormattedMessage id="project.global.fields.welcome"/> {userName}!
            </h5>
            <br/>
            <form  onSubmit={handleSubmit}>
                <button id="insertProductButton" type="submit" className="btn btn-primary">
                    <FormattedMessage id="project.Bid.insertButton"/>
                </button>
            </form>
        </div>
    );

}

export default ButtonToInsertProduct;