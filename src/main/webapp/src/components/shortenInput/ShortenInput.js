import React, { Component } from 'react';
import './ShortenInput.css'

class ShortenInput extends Component
{
    render() {
        return (
        <div className="mdl-cell mdl-cell--12-col shorten-input">
            <div className="mdl-textfield mdl-js-textfield shorten-textfield">
                <input className="mdl-textfield__input" type="text" id="url" name="url" />
                <label className="mdl-textfield__label" htmlFor="url">Paste your URL...</label>
            </div>
            <button className="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent shorten-btn">
                Shorten!
            </button>
        </div>
        )
    }
}

export default ShortenInput