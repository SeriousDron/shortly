import React, {Component} from "react";

class Message extends Component 
{
    snackBar = null;
    constructor(props) {
        super(props);
    }

    componentDidMount() {
        ShortlyStore.subscribe(this.messageUpdated.bind(this))
    }

    componentWillUnmount() {
        ShortlyStore.unsub(this.messageUpdated)
    }

    messageUpdated(store) {
        if (store.message == null) {
            return;
        }
        this.showMessage(store.message);
    } 
    showMessage(message) {
        snackBar.MaterialSnackbar.showSnackbar(
            {
                message: message
            }
        );    
    }
    
    render() {
        return <div aria-live="assertive" aria-atomic="true" aria-relevant="text"
                    className="mdl-snackbar mdl-js-snackbar"
                    ref={(snackBar) => {
                               this.snackBar = snackBar;
                           }}>
            <div class="mdl-snackbar__text"></div>
            <button type="button" class="mdl-snackbar__action"></button>
        </div>
    }
}

export default Message;