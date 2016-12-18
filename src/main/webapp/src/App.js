import React, { Component } from 'react';
import ShortenInput from './components/shortenInput/ShortenInput'
import Header from './components/header/Header'
import './App.css';


class App extends Component {
  render() {
    return (
    <div className="mdl-layout mdl-js-layout page-background">
        <Header/>
        <div className="mdl-layout__drawer">
            <span className="mdl-layout__title">Material Design Lite</span>
            <nav className="mdl-navigation">
                <a className="mdl-navigation__link" href="#">Hello</a>
                <a className="mdl-navigation__link" href="#">World.</a>
                <a className="mdl-navigation__link" href="#">How</a>
                <a className="mdl-navigation__link" href="#">Are</a>
                <a className="mdl-navigation__link" href="#">You?</a>
            </nav>
        </div>

        <main className="mdl-layout__content grid-outer">
            <div className="mdl-grid">
                <div className="mdl-cell mdl-cell--12-col">
                    <div className="title mdl-typography--font-light heading">
                        <h3>Shorten your links!</h3>
                        <h5>Shortened links for fun and profit</h5>
                    </div>
                </div>
                <ShortenInput />
            </div>

        </main>

        <footer className="mdl-mini-footer">
            <div className="mdl-mini-footer__left-section">
                <div className="mdl-logo">Title</div>
                <ul className="mdl-mini-footer__link-list">
                    <li><a href="#">Help</a></li>
                    <li><a href="#">Privacy & Terms</a></li>
                </ul>
            </div>
        </footer>
    </div>
    );
  }
}

export default App;
