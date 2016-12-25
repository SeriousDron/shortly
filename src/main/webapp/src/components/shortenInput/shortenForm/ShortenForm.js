import React, {Component} from "react";

class ShortenForm extends  Component
{
    textInput = null;

    constructor(props) {
        super(props);
        this.state = {url: ''};

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    componentDidMount() {
        if (window.componentHandler) {
            window.componentHandler.upgradeElements(document.querySelector('.shorten-input'));
        }
        this.textInput.focus();
    }

    handleChange(event) {
        this.setState({url: event.target.value});
    }

    handleSubmit(event) {
        event.preventDefault();
        this.props.onSubmit(this.state.url)
    }

    render() {
        return (
            <form onSubmit={this.handleSubmit} className="shorten-input">
                <div
                    className={"mdl-textfield mdl-js-textfield shorten-textfield " + (this.props.disabled && 'is-disabled')}>
                    <input className="mdl-textfield__input" type="text" id="url" name="url"
                           onChange={this.handleChange} value={this.state.url}
                           disabled={this.props.disabled}
                           ref={(input) => {
                               this.textInput = input;
                           }}/>
                    <label className="mdl-textfield__label" htmlFor="url">Paste your URL...</label>
                </div>
                <button
                    className="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent shorten-btn"
                    disabled={this.props.disabled}>
                    Shorten!
                </button>
            </form>
        )
    }
}

export default ShortenForm;