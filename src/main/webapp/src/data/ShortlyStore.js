import ShortlyActionTypes from './ShortyActionTypes';
import ShortlyDispatcher from './ShortlyDispatcher';


const ShortlyStore = new class {

    _listeners = [];

    _token;
    _shorteningInProgress = false;
    _activeUrl = null;

    constructor() {
        this._shorteningInProgress = false;
        this._activeUrl = null;
        this._token = ShortlyDispatcher.register(this.processEvents.bind(this));
    }

    processEvents(payload) {
        //noinspection FallThroughInSwitchStatementJS
        switch (payload.type) {
            case ShortlyActionTypes.SHORTEN_URL:
                this._shorteningInProgress = true;
                break;
            case ShortlyActionTypes.URL_SHORTENED:
                this._activeUrl = 'http://shortened.com/'+payload.id;
            // eslint-disable-next-line
            case ShortlyActionTypes.SHORTENING_ERROR:
                this._shorteningInProgress = false;
                break;
            default:
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

    get shorteningInProgress() {
        return this._shorteningInProgress;
    }

    get activeUrl() {
        return this._activeUrl;
    }

    subscribe(func) {
        this._listeners.push(func);
    }

    unsub(func) {
        this._listeners.splice(this._listeners.indexOf(func), 1);
    }
}();

export default ShortlyStore

