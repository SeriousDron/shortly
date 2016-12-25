import ShortlyActionTypes from './ShortyActionTypes';
import ShortlyDispatcher from './ShortlyDispatcher';

import ShortenAPI from './ShortenAPI'

const Actions = {
    shortenUrl(url) {

        ShortlyDispatcher.dispatch({
            type: ShortlyActionTypes.SHORTEN_URL,
            url,
        });

        ShortenAPI.shorten(url).then((response) => {
            ShortlyDispatcher.dispatch({
                type: ShortlyActionTypes.URL_SHORTENED,
                id: response.body.key
            })
        }).catch((result) => {
            ShortlyDispatcher.dispatch({
                type: ShortlyActionTypes.SHORTENING_ERROR,
                message: result.message
            })
        })
    },

    copyUrl(url) {
        document.execCommand('copy');
        ShortlyDispatcher.dispatch({
            type: ShortlyActionTypes.COPY_URL
        })
    },

    clear() {
        ShortlyDispatcher.dispatch({
            type: ShortlyActionTypes.CLEAR_ACTIVE
        })
    }
};

export default Actions;
