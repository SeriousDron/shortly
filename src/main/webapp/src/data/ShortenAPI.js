var request = require('superagent');

const PREFIX = process.env.REACT_APP_BACKEND;

class ShortenAPI
{
    static shorten(url) {
        return request.post(PREFIX+'/api/v1/url')
            .type('form')
            .send({ url })
            .accept('json')
    }
}


export default ShortenAPI
