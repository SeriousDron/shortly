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
                id: response.data.id
            })
        }).catch((result) => {
            ShortlyDispatcher.dispatch({
                type: ShortlyActionTypes.SHORTENING_ERROR
            })
        })
    },
};

export default Actions;