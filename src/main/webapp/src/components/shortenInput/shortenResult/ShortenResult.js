import React, {Component} from "react";

class ShortenResult extends  Component
{
    textInput = null;

    constructor(props) {
        super(props);
        this.state = {url: props.url};

        this.handleCopy = this.handleCopy.bind(this);
        this.handleClear = this.handleClear.bind(this);
        this.handleFocus = this.handleFocus.bind(this);
    }

    handleCopy(event) {
        event.preventDefault();
        this.textInput.focus();
        this.textInput.select();
        this.props.onCopy(this.state.url)
    }

    handleClear(event) {
        event.preventDefault();
        this.props.onClear()
    }

    componentDidMount() {
        if (window.componentHandler) {
            window.componentHandler.upgradeElements(document.querySelector('.shorten-input'));
        }
        this.textInput.focus();
        this.textInput.select();
    }

    handleFocus(event) {
        this.textInput.select();
    }

    render() {
        return (
            <form onSubmit={this.handleCopy} className="shorten-input">
                <div className="mdl-textfield mdl-js-textfield shorten-textfield">
                    <input className="mdl-textfield__input" type="text" id="url" name="url"
                           value={this.state.url}
                           ref={(input) => { this.textInput = input; }}
                           readOnly={true}
                            onFocus={this.handleFocus}/>
                    <button className="mdl-button mdl-js-button mdl-button--icon mdl-button--accent shorten-clear mdl-js-ripple-effect"
                    onClick={this.handleClear}>
                        <i className="material-icons">clear</i>
                    </button>
                </div>
                <button
                    className="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent shorten-btn">
                    Copy!
                </button>
            </form>
        )
    }
}

export default ShortenResult;