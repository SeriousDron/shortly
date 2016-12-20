import React, { Component} from 'react'
import './progressbar.css'

class ProgressBar extends Component
{
    componentDidMount() {
        window.componentHandler.upgradeElements(document.querySelector('.shorten-progress'))
    }

    render() {
        return (
            <div className="mdl-progress mdl-js-progress mdl-progress__indeterminate shorten-progress" />
        );
    }
}

export default ProgressBar