import React, {Component} from "react";
import "./ShortenInput.css";

class ShortenInput extends Component
{
    constructor(props) {
        super(props);
        this.state = {url: '', inProgress: false};

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        this.setState({url: event.target.value});
    }

    handleSubmit(event) {
        this.setState({ inProgress: true });
        event.preventDefault();
    }

    render() {
        return (
        <div className="mdl-cell mdl-cell--12-col">
            <form onSubmit={this.handleSubmit} className="shorten-input">
                <div className="mdl-textfield mdl-js-textfield shorten-textfield">
                    <input className="mdl-textfield__input" type="text" id="url" name="url" onChange={this.handleChange} value={this.state.url} />
                    <label className="mdl-textfield__label" htmlFor="url">Paste your URL...</label>
                </div>
                <button className="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent shorten-btn">
                    Shorten!
                </button>
            </form>
            {this.state.inProgress ? <div id="p2"
                                          className="mdl-progress mdl-js-progress mdl-progress__indeterminate shorten-progress"/> : null }
        </div>
        )
    }
}

export default ShortenInput