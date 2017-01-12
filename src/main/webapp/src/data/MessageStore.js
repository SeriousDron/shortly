import ShortlyActionTypes from './ShortyActionTypes';
import ShortlyDispatcher from './ShortlyDispatcher';


const MessageStore = new class {

    _listeners = [];

    _message;

    constructor() {
        this._message = null;
        this._token = ShortlyDispatcher.register(this.processEvents.bind(this));
    }

    processEvents(payload) {
        switch (payload.type) {
            case ShortlyActionTypes.SHORTENING_ERROR:
                this._message = 'Error shortening URL';
                break;
            case ShortlyActionTypes.COPY_URL:
                this._message = 'URL copied to buffer';
        }
        this.triggerUpdate();
        return true;
    }

    triggerUpdate() {
        this._listeners.forEach((listener) => listener(this))
    }

    get token() {
        return this._token;
    }

    get message() {
        return this._activeUrl;
    }

    subscribe(func) {
        this._listeners.push(func);
    }

    unsub(func) {
        this._listeners.splice(this._listeners.indexOf(func), 1);
    }
}();

export default MessageStore
