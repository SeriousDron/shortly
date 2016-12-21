import React, { Component } from 'react'
import './header.css'

class Header extends Component
{
    render() {
        return (
        <header className="mdl-layout__header mdl-layout__header--scroll mdl-layout__header--transparent">
            <img className="mdl-layout-icon" alt="Menu" />
            <div className="mdl-layout__header-row">
                <span className="mdl-layout__title">Material Design Lite</span>
                <div className="mdl-layout-spacer"></div>
                <nav className="mdl-navigation">
                    <a className="mdl-navigation__link" href="#">Hello</a>
                    <a className="mdl-navigation__link" href="#">World.</a>
                    <a className="mdl-navigation__link" href="#">How</a>
                    <a className="mdl-navigation__link" href="#">Are</a>
                    <a className="mdl-navigation__link" href="#">You?</a>
                </nav>
            </div>
        </header>
        )
    }
}
export default Header