import React, {Component} from "react";
import ShortlyActions from '../../data/ShortlyActions'
import ShortlyStore from '../../data/ShortlyStore'

import ShortenForm from './shortenForm/ShortenForm'
import ShortenResult from './shortenResult/ShortenResult'
import ProgressBar from '../progressbar/ProgressBar'

import "./ShortenInput.css";

class ShortenInput extends Component
{
    constructor(props) {
        super(props);
        this.state = {
            showResult: false,
            inProgress: false,
            activeUrl: null
        };
    }

    componentDidMount() {
        ShortlyStore.subscribe(this.shortlyUpdated.bind(this))
    }

    componentWillUnmount() {
        ShortlyStore.unsub(this.shortlyUpdated)
    }

    shortlyUpdated(storage) {
        this.setState({
            inProgress: storage.shorteningInProgress,
            showResult: storage.activeUrl != null,
            activeUrl: storage.activeUrl
        })
    }

    render() {
        return (
        <div className="mdl-cell mdl-cell--12-col">
            {
                this.state.showResult ?
                    <ShortenResult url={this.state.activeUrl} onCopy={ShortlyActions.copyUrl} onClear={ShortlyActions.clear} />
                    : <ShortenForm onSubmit={ShortlyActions.shortenUrl} disabled={this.state.inProgress}/>
            }
            {this.state.inProgress && <ProgressBar /> }
        </div>
        )
    }

}

export default ShortenInput