import React, {Component} from "react";
import ShortlyActions from '../../data/ShortlyActions'
import ShortlyStore from '../../data/ShortlyStore'

import ShortenForm from './shortenForm/ShortenForm'
import ProgressBar from '../progressbar/ProgressBar'

import "./ShortenInput.css";

class ShortenInput extends Component
{
    constructor(props) {
        super(props);
        this.state = {
            showResult: false,
            inProgress: false
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
            inProgress: storage.shorteningInProgress
        })
    }

    render() {
        return (
        <div className="mdl-cell mdl-cell--12-col">
            {
                this.state.showResult ?
                    <div>Result</div>
                    : <ShortenForm onSubmit={ShortlyActions.shortenUrl}/>
            }
            {this.state.inProgress && <ProgressBar /> }
        </div>
        )
    }

}

export default ShortenInput