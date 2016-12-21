var request = require('superagent');

const PORT = 9000;
const PREFIX = 'http://localhost:' + PORT;

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
