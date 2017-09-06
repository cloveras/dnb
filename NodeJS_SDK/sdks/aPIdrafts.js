'use strict';

var util = require('util');
var restletUtils = require('../restletUtils');
var securityUtils = require('../securityUtils');

/**
 * @class APIdrafts
 * @param {string} [endpoint] - The API endpoint
 */
function APIdrafts(endpoint) {
  if (restletUtils.isDefined(endpoint) && (!restletUtils.isString(endpoint) || restletUtils.isString(endpoint) && endpoint.length === 0)) {
    throw new Error('endpoint parameter must be a non-empty string.');
  }

  this.globalSecurity = {};
  this.securityConfigurations = {};
  this.endpoint = restletUtils.stripTrailingSlash(endpoint || 'https://a1r3mp0.restletmocks.net');
}

/**
 * Sets up the authentication to be performed through API token
 *
 * @method
 * @name APIdrafts#setApiToken
 * @param {string} tokenName - the name of the query parameter or header based on the location parameter.
 * @param {string} tokenValue - the value of the token
 * @param {string} location - the location of the token, either 'HEADER' or 'QUERY'.
 * If undefined it defaults to 'header'.
 */
APIdrafts.prototype.configureGlobalApiToken = function(tokenName, tokenValue, location) {
  if (restletUtils.isUndefined(location)) {
    util.log('No location defined, it defaults to \'HEADER\'');
    location = 'HEADER';
  }

  if (location.toUpperCase() !== 'HEADER' && location.toUpperCase() !== 'QUERY') {
    throw new Error('Unknown location: ' + location);
  }

  this.globalSecurity = {
    type: 'API_KEY',
    placement: location.toUpperCase(),
    name: tokenName,
    token: tokenValue
  };
};

/**
 * Sets up the authentication to be performed through oAuth2 protocol
 * meaning that the Authorization header will contain a Bearer token.
 *
 * @method
 * @param token - the oAuth token to use
 */
APIdrafts.prototype.configureGlobalOAuth2Token = function (token) {
  this.globalSecurity = {
    type: 'OAUTH2',
    token: 'Bearer ' + token
  };
};

/**
 * Sets up the authentication to be performed through basic auth.
 *
 * @method
 * @name APIdrafts#setBasicAuth
 * @param {string} username - the user's username
 * @param {string} key - the user's key or password
 */
APIdrafts.prototype.configureGlobalBasicAuthentication = function(username, key) {
  this.globalSecurity = {
    type: 'BASIC',
    token: 'Basic ' + new Buffer(username + ':' + key).toString('base64')
  };
};



/**
 * Simplified example. Requires a method of identification, the minimum being Facebook or Google OAuth. 
 * @method
 * @name APIdrafts#getApiKey
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Status 200
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getApiKey = function(config, callback) {
  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/api/key',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * For now: Also returns some housekeeping details, as an example. 
 * @method
 * @name APIdrafts#getApiKeyApikey
 * @param {string} apikey - REQUIRED - Example: fa01s-21res-qq21p-mq1p6
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Status 200 - Payload :
{
  "balance" : "sample balance",
  "customer" : null,
  "invoice" : {
    "amount" : 1,
    "date" : null,
    "id" : "sample id",
    "paid" : false
  }
}
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getApiKeyApikey = function(apikey, config, callback) {
  restletUtils.checkPathVariables(apikey, 'apikey');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/api/key/' + apikey + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#deleteApiKeyApikey
 * @param {string} apikey - REQUIRED - Example: fa01s-21res-qq21p-mq1p6
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.deleteApiKeyApikey = function(apikey, config, callback) {
  restletUtils.checkPathVariables(apikey, 'apikey');

  restletUtils.executeRequest.call(this, 'DELETE',
    this.endpoint + '/api/key/' + apikey + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * How am I doing? Am I near my limit? 
 * @method
 * @name APIdrafts#getApiRatelimit
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getApiRatelimit = function(config, callback) {
  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/api/ratelimit',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * All details of current API user account:
* Contact details
* API key details
* Payment/plan/subscription
* Throttling info
* etc
 * @method
 * @name APIdrafts#getApiWhoami
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Details of current API key holder.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getApiWhoami = function(config, callback) {
  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/api/whoami',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getApiPing
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getApiPing = function(config, callback) {
  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/api/ping',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * Customer score with all details.
 * @method
 * @name APIdrafts#getCustomerOrgnrScore
 * @param {string} orgnr - REQUIRED
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Status 200 - Payload :
{
  "customerid" : "sample customerid",
  "id" : "sample id",
  "score" : 1,
  "score_details" : "sample score_details"
}
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getCustomerOrgnrScore = function(orgnr, config, callback) {
  restletUtils.checkPathVariables(orgnr, 'orgnr');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/customer/' + orgnr + '/score',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#postCustomerOrgnrScore
 * @param {string} orgnr - REQUIRED
 * @param {object} body - the payload; is of type: Customerscore; has the following structure:
{
  "customerid" : "sample customerid",
  "id" : "sample id",
  "score" : 1,
  "score_details" : "sample score_details"
}
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.postCustomerOrgnrScore = function(orgnr, body, config, callback) {
  restletUtils.checkPathVariables(orgnr, 'orgnr');

  restletUtils.executeRequest.call(this, 'POST',
    this.endpoint + '/customer/' + orgnr + '/score',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations),
    body
  );
};

/**
 * 
 * @method
 * @name APIdrafts#deleteCustomerOrgnrScore
 * @param {string} orgnr - REQUIRED
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.deleteCustomerOrgnrScore = function(orgnr, config, callback) {
  restletUtils.checkPathVariables(orgnr, 'orgnr');

  restletUtils.executeRequest.call(this, 'DELETE',
    this.endpoint + '/customer/' + orgnr + '/score',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getCustomersOrgnr
 * @param {string} orgnr - REQUIRED - Organization number, 9 digits. Example: 123456789
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 -  - Payload :
{
  "Address" : "sample Address",
  "City" : "sample City",
  "Country" : "sample Country",
  "Email" : "sample Email",
  "Id" : "sample Id",
  "Name" : "sample Name",
  "Notes (placeholder)" : "sample Notes (placeholder)",
  "Phone" : "sample Phone",
  "Postcode" : "sample Postcode",
  "Website" : "sample Website",
  "program" : "sample program",
  "status" : "sample status",
  "welcomepackage" : "sample welcomepackage"
}
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getCustomersOrgnr = function(orgnr, config, callback) {
  restletUtils.checkPathVariables(orgnr, 'orgnr');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/customers/' + orgnr + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#postCustomersOrgnr
 * @param {string} orgnr - REQUIRED - Organization number, 9 digits. Example: 123456789
 * @param {object} body - the payload; is of type: Customer; has the following structure:
{
  "Address" : "sample Address",
  "City" : "sample City",
  "Country" : "sample Country",
  "Email" : "sample Email",
  "Id" : "sample Id",
  "Name" : "sample Name",
  "Notes (placeholder)" : "sample Notes (placeholder)",
  "Phone" : "sample Phone",
  "Postcode" : "sample Postcode",
  "Website" : "sample Website",
  "program" : "sample program",
  "status" : "sample status",
  "welcomepackage" : "sample welcomepackage"
}
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.postCustomersOrgnr = function(orgnr, body, config, callback) {
  restletUtils.checkPathVariables(orgnr, 'orgnr');

  restletUtils.executeRequest.call(this, 'POST',
    this.endpoint + '/customers/' + orgnr + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations),
    body
  );
};

/**
 * 
 * @method
 * @name APIdrafts#deleteCustomersOrgnr
 * @param {string} orgnr - REQUIRED - Organization number, 9 digits. Example: 123456789
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.deleteCustomersOrgnr = function(orgnr, config, callback) {
  restletUtils.checkPathVariables(orgnr, 'orgnr');

  restletUtils.executeRequest.call(this, 'DELETE',
    this.endpoint + '/customers/' + orgnr + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * Nice if this is different from /customers/{orgnr}.
 * @method
 * @name APIdrafts#getCustomersOrgnrAbout
 * @param {string} orgnr - REQUIRED - Organization number, 9 digits. Example: 123456789
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 -  - Payload :
{
  "Address" : "sample Address",
  "City" : "sample City",
  "Country" : "sample Country",
  "Email" : "sample Email",
  "Id" : "sample Id",
  "Name" : "sample Name",
  "Notes (placeholder)" : "sample Notes (placeholder)",
  "Phone" : "sample Phone",
  "Postcode" : "sample Postcode",
  "Website" : "sample Website",
  "program" : "sample program",
  "status" : "sample status",
  "welcomepackage" : "sample welcomepackage"
}
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getCustomersOrgnrAbout = function(orgnr, config, callback) {
  restletUtils.checkPathVariables(orgnr, 'orgnr');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/customers/' + orgnr + '/about',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#postCustomersOrgnrAbout
 * @param {string} orgnr - REQUIRED - Organization number, 9 digits. Example: 123456789
 * @param {object} body - the payload; is of type: Customer; has the following structure:
{
  "Address" : "sample Address",
  "City" : "sample City",
  "Country" : "sample Country",
  "Email" : "sample Email",
  "Id" : "sample Id",
  "Name" : "sample Name",
  "Notes (placeholder)" : "sample Notes (placeholder)",
  "Phone" : "sample Phone",
  "Postcode" : "sample Postcode",
  "Website" : "sample Website",
  "program" : "sample program",
  "status" : "sample status",
  "welcomepackage" : "sample welcomepackage"
}
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.postCustomersOrgnrAbout = function(orgnr, body, config, callback) {
  restletUtils.checkPathVariables(orgnr, 'orgnr');

  restletUtils.executeRequest.call(this, 'POST',
    this.endpoint + '/customers/' + orgnr + '/about',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations),
    body
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getCustomersOrgnrAccounts
 * @param {string} orgnr - REQUIRED - Organization number, 9 digits. Example: 123456789
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Payload :
[{
  "IBAN" : "sample IBAN",
  "balance" : 1,
  "created" : null,
  "currency" : "sample currency",
  "locked" : false,
  "name" : "sample name",
  "notes" : "sample notes",
  "number" : "sample number",
  "swift" : "sample swift",
  "type" : "sample type",
  "updated" : null
}]
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getCustomersOrgnrAccounts = function(orgnr, config, callback) {
  restletUtils.checkPathVariables(orgnr, 'orgnr');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/customers/' + orgnr + '/accounts',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getCustomersOrgnrProfile
 * @param {string} orgnr - REQUIRED
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getCustomersOrgnrProfile = function(orgnr, config, callback) {
  restletUtils.checkPathVariables(orgnr, 'orgnr');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/customers/' + orgnr + '/profile',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#postCustomersOrgnrProfile
 * @param {string} orgnr - REQUIRED
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.postCustomersOrgnrProfile = function(orgnr, config, callback) {
  restletUtils.checkPathVariables(orgnr, 'orgnr');

  restletUtils.executeRequest.call(this, 'POST',
    this.endpoint + '/customers/' + orgnr + '/profile',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#deleteCustomersOrgnrProfile
 * @param {string} orgnr - REQUIRED
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.deleteCustomersOrgnrProfile = function(orgnr, config, callback) {
  restletUtils.checkPathVariables(orgnr, 'orgnr');

  restletUtils.executeRequest.call(this, 'DELETE',
    this.endpoint + '/customers/' + orgnr + '/profile',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * Maybe add query parameters for filtering? '?type=stocks', etc
 * @method
 * @name APIdrafts#getAccountAccount
 * @param {string} account - REQUIRED - Account number, 11 digits.
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Status 200 - Payload :
{
  "IBAN" : "sample IBAN",
  "balance" : 1,
  "created" : null,
  "currency" : "sample currency",
  "locked" : false,
  "name" : "sample name",
  "notes" : "sample notes",
  "number" : "sample number",
  "swift" : "sample swift",
  "type" : "sample type",
  "updated" : null
}
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getAccountAccount = function(account, config, callback) {
  restletUtils.checkPathVariables(account, 'account');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/account/' + account + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * Maybe send partial Account object (without account number) in request?
 * @method
 * @name APIdrafts#postAccountAccount
 * @param {string} account - REQUIRED - Account number, 11 digits.
 * @param {object} body - the payload; is of type: Account; has the following structure:
{
  "IBAN" : "sample IBAN",
  "balance" : 1,
  "created" : null,
  "currency" : "sample currency",
  "locked" : false,
  "name" : "sample name",
  "notes" : "sample notes",
  "number" : "sample number",
  "swift" : "sample swift",
  "type" : "sample type",
  "updated" : null
}
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.postAccountAccount = function(account, body, config, callback) {
  restletUtils.checkPathVariables(account, 'account');

  restletUtils.executeRequest.call(this, 'POST',
    this.endpoint + '/account/' + account + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations),
    body
  );
};

/**
 * Maybe return HTTP 2XX 'marked for deletion', as the account will not be deleted immediately?
 * @method
 * @name APIdrafts#deleteAccountAccount
 * @param {string} account - REQUIRED - Account number, 11 digits.
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.deleteAccountAccount = function(account, config, callback) {
  restletUtils.checkPathVariables(account, 'account');

  restletUtils.executeRequest.call(this, 'DELETE',
    this.endpoint + '/account/' + account + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getAccountsAccountBalance
 * @param {string} account - REQUIRED - Account number, 11 digits.
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Payload :
{
  "account" : "sample account",
  "amount" : 1,
  "timestamp" : null
}
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getAccountsAccountBalance = function(account, config, callback) {
  restletUtils.checkPathVariables(account, 'account');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/accounts/' + account + '/balance',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getAccountsAccountName
 * @param {string} account - REQUIRED - Account number, 11 digits.
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getAccountsAccountName = function(account, config, callback) {
  restletUtils.checkPathVariables(account, 'account');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/accounts/' + account + '/name',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#postAccountsAccountName
 * @param {string} account - REQUIRED - Account number, 11 digits.
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.postAccountsAccountName = function(account, config, callback) {
  restletUtils.checkPathVariables(account, 'account');

  restletUtils.executeRequest.call(this, 'POST',
    this.endpoint + '/accounts/' + account + '/name',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#deleteAccountsAccountName
 * @param {string} account - REQUIRED - Account number, 11 digits.
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.deleteAccountsAccountName = function(account, config, callback) {
  restletUtils.checkPathVariables(account, 'account');

  restletUtils.executeRequest.call(this, 'DELETE',
    this.endpoint + '/accounts/' + account + '/name',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * The current balance, minus known outgoing transactions, plus known incoming transactions. 
 * @method
 * @name APIdrafts#getAccountsAccountSafetospend
 * @param {string} account - REQUIRED - Account number, 11 digits.
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Here it is! Don't spend it all at once.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getAccountsAccountSafetospend = function(account, config, callback) {
  restletUtils.checkPathVariables(account, 'account');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/accounts/' + account + '/safetospend',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * Account statement for specified account, year and month. Some meta data, and a list of all transactions. 
 * @method
 * @name APIdrafts#getAccountsAccountStatementYearMonth
 * @param {string} account - REQUIRED
 * @param {string} year - REQUIRED
 * @param {string} month - REQUIRED
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Status 200 - Payload :
{
  "account" : "sample account",
  "month" : "sample month",
  "transactions" : [ ],
  "year" : "sample year"
}
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getAccountsAccountStatementYearMonth = function(account, year, month, config, callback) {
  restletUtils.checkPathVariables(account, 'account', year, 'year', month, 'month');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/accounts/' + account + '/statement/' + year + '/' + month + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * Supports pagination: pagesize and pagenumber,
 * @method
 * @name APIdrafts#getAccountsAccountTransactionlist
 * @param {string} account - REQUIRED
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {integer} config.queryParameters.pagesize - 
 * @param {integer} config.queryParameters.pagenumber
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Status 200 - Payload :
[{
  "Notes (placeholder)" : "sample Notes (placeholder)",
  "account" : "sample account",
  "amount" : "sample amount",
  "currency" : "sample currency",
  "id" : "sample id",
  "reference" : "sample reference",
  "type" : "sample type"
}]
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getAccountsAccountTransactionlist = function(account, config, callback) {
  restletUtils.checkPathVariables(account, 'account');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/accounts/' + account + '/transactionlist',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getAccountsAccountTransactionlistYearMonth
 * @param {string} account - REQUIRED - Account number, 11 digits. Example: 12345678910
 * @param {string} year - REQUIRED - Year, 4 digits. Example: 2017
 * @param {string} month - REQUIRED - Month, 2 digits (January is 01). Example: 01
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 -  - Payload :
[{
  "Notes (placeholder)" : "sample Notes (placeholder)",
  "account" : "sample account",
  "amount" : "sample amount",
  "currency" : "sample currency",
  "id" : "sample id",
  "reference" : "sample reference",
  "type" : "sample type"
}]
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getAccountsAccountTransactionlistYearMonth = function(account, year, month, config, callback) {
  restletUtils.checkPathVariables(account, 'account', year, 'year', month, 'month');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/accounts/' + account + '/transactionlist/' + year + '/' + month + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * For now: Using Account object with SSIDs of tennant and owner. 

Required:
* Electronic copy of signed rental agreement
* The address of the apartment (or license details for car, boat, etc)
* Name, SSN, email, phone of the lender (or is SSN sufficient, at least for existing DNB customers?)

Use a Customer object?

DNB deposit account: https://www.dnb.no/privat/nettbank-mobil-og-kort/konto/depositumskonto.html 
 * @method
 * @name APIdrafts#postAccountDepositOwneridLenderid
 * @param {string} ownerid - REQUIRED - Personal id number (SSN) of the "owner" for the artifact this deposit account is security for, 11 digits.
 * @param {string} lenderid - REQUIRED - Personal id number (SSN) of the "lender" for the artifact this deposit account is security for, 11 digits.
 * @param {object} body - the payload; is of type: Account; has the following structure:
{
  "IBAN" : "sample IBAN",
  "balance" : 1,
  "created" : null,
  "currency" : "sample currency",
  "locked" : false,
  "name" : "sample name",
  "notes" : "sample notes",
  "number" : "sample number",
  "swift" : "sample swift",
  "type" : "sample type",
  "updated" : null
}
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.postAccountDepositOwneridLenderid = function(ownerid, lenderid, body, config, callback) {
  restletUtils.checkPathVariables(ownerid, 'ownerid', lenderid, 'lenderid');

  restletUtils.executeRequest.call(this, 'POST',
    this.endpoint + '/account/deposit/' + ownerid + '/' + lenderid + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations),
    body
  );
};

/**
 * 
 * @method
 * @name APIdrafts#deleteAccountDepositOwneridLenderid
 * @param {string} ownerid - REQUIRED - Personal id number (SSN) of the "owner" for the artifact this deposit account is security for, 11 digits.
 * @param {string} lenderid - REQUIRED - Personal id number (SSN) of the "lender" for the artifact this deposit account is security for, 11 digits.
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.deleteAccountDepositOwneridLenderid = function(ownerid, lenderid, config, callback) {
  restletUtils.checkPathVariables(ownerid, 'ownerid', lenderid, 'lenderid');

  restletUtils.executeRequest.call(this, 'DELETE',
    this.endpoint + '/account/deposit/' + ownerid + '/' + lenderid + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * Need to provide more than an Account object, but then again this is a draft/note only.
 * @method
 * @name APIdrafts#getAccountDepositOwneridLenderid
 * @param {string} ownerid - REQUIRED - Personal id number (SSN) of the "owner" for the artifact this deposit account is security for, 11 digits.
 * @param {string} lenderid - REQUIRED - Personal id number (SSN) of the "lender" for the artifact this deposit account is security for, 11 digits.
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Payload :
{
  "IBAN" : "sample IBAN",
  "balance" : 1,
  "created" : null,
  "currency" : "sample currency",
  "locked" : false,
  "name" : "sample name",
  "notes" : "sample notes",
  "number" : "sample number",
  "swift" : "sample swift",
  "type" : "sample type",
  "updated" : null
}
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getAccountDepositOwneridLenderid = function(ownerid, lenderid, config, callback) {
  restletUtils.checkPathVariables(ownerid, 'ownerid', lenderid, 'lenderid');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/account/deposit/' + ownerid + '/' + lenderid + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getTransactionsCategoryMccAccount
 * @param {string} mcc - REQUIRED - MCC: Merchant Category Code: https://en.wikipedia.org/wiki/Merchant_category_code
 * @param {string} account - REQUIRED - Account number, 11 digits.
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Status 200 - Payload :
[{
  "Notes (placeholder)" : "sample Notes (placeholder)",
  "account" : "sample account",
  "amount" : "sample amount",
  "currency" : "sample currency",
  "id" : "sample id",
  "reference" : "sample reference",
  "type" : "sample type"
}]
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getTransactionsCategoryMccAccount = function(mcc, account, config, callback) {
  restletUtils.checkPathVariables(mcc, 'mcc', account, 'account');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/transactions/category/' + mcc + '/' + account + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#postTransactionsSepa
 * @param {object} body - the payload; is of type: SEPArequest; has the following structure:
{
  "account" : "sample account",
  "amount" : 1,
  "currency" : "sample currency",
  "id" : "sample id",
  "remote_account" : "sample remote_account",
  "remote_bic" : "sample remote_bic",
  "remote_iban" : "sample remote_iban"
}
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Status 200 - Payload :
{
  "account" : "sample account",
  "amount" : 1,
  "created" : "sample created",
  "currency" : "sample currency",
  "id" : "sample id",
  "remote:_bic" : "sample remote:_bic",
  "remote_iban" : "sample remote_iban",
  "remote_name" : "sample remote_name",
  "state" : "sample state",
  "updated" : "sample updated"
}
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.postTransactionsSepa = function(body, config, callback) {
  restletUtils.executeRequest.call(this, 'POST',
    this.endpoint + '/transactions/sepa',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations),
    body
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getTransactionsTransactionid
 * @param {string} transactionid - REQUIRED - id
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Status 200 - Payload :
{
  "Notes (placeholder)" : "sample Notes (placeholder)",
  "account" : "sample account",
  "amount" : "sample amount",
  "currency" : "sample currency",
  "id" : "sample id",
  "reference" : "sample reference",
  "type" : "sample type"
}
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getTransactionsTransactionid = function(transactionid, config, callback) {
  restletUtils.checkPathVariables(transactionid, 'transactionid');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/transactions/' + transactionid + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * TBD. Needs a lot more details. Just a note, really.
 * @method
 * @name APIdrafts#postTransactionsTransactionid
 * @param {string} transactionid - REQUIRED - id
 * @param {object} body - the payload; is of type: Transaction; has the following structure:
{
  "Notes (placeholder)" : "sample Notes (placeholder)",
  "account" : "sample account",
  "amount" : "sample amount",
  "currency" : "sample currency",
  "id" : "sample id",
  "reference" : "sample reference",
  "type" : "sample type"
}
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.postTransactionsTransactionid = function(transactionid, body, config, callback) {
  restletUtils.checkPathVariables(transactionid, 'transactionid');

  restletUtils.executeRequest.call(this, 'POST',
    this.endpoint + '/transactions/' + transactionid + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations),
    body
  );
};

/**
 * 
 * @method
 * @name APIdrafts#deleteTransactionsTransactionid
 * @param {string} transactionid - REQUIRED - id
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.deleteTransactionsTransactionid = function(transactionid, config, callback) {
  restletUtils.checkPathVariables(transactionid, 'transactionid');

  restletUtils.executeRequest.call(this, 'DELETE',
    this.endpoint + '/transactions/' + transactionid + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * Need to POST quite a bit of data, and also handle AML, KYC, etc
 * @method
 * @name APIdrafts#postLoansApply
 * @param {object} body - the payload; is of type: string
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.postLoansApply = function(body, config, callback) {
  restletUtils.executeRequest.call(this, 'POST',
    this.endpoint + '/loans/apply',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations),
    body
  );
};

/**
 * 
 * @method
 * @name APIdrafts#postLoansDownpayLoanid
 * @param {string} loanid - REQUIRED - The loan to downpay.
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.postLoansDownpayLoanid = function(loanid, config, callback) {
  restletUtils.checkPathVariables(loanid, 'loanid');

  restletUtils.executeRequest.call(this, 'POST',
    this.endpoint + '/loans/downpay/' + loanid + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getLoansLoanid
 * @param {string} loanid - REQUIRED
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Status 200 - Payload :
{
  "created" : null,
  "customerid" : "sample customerid",
  "details (placeholder)" : "sample details (placeholder)",
  "expires" : null,
  "id" : "sample id",
  "interest" : 1,
  "remainingamount" : "sample remainingamount",
  "totalamount" : "sample totalamount",
  "type" : "sample type"
}
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getLoansLoanid = function(loanid, config, callback) {
  restletUtils.checkPathVariables(loanid, 'loanid');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/loans/' + loanid + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * Downpayment first.
 * @method
 * @name APIdrafts#deleteLoansLoanid
 * @param {string} loanid - REQUIRED
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.deleteLoansLoanid = function(loanid, config, callback) {
  restletUtils.checkPathVariables(loanid, 'loanid');

  restletUtils.executeRequest.call(this, 'DELETE',
    this.endpoint + '/loans/' + loanid + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * For now: Returns a Loan object.
 * @method
 * @name APIdrafts#getPreapprovalLoanid
 * @param {string} loanid - REQUIRED
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Status 200 - Payload :
{
  "created" : null,
  "customerid" : "sample customerid",
  "details (placeholder)" : "sample details (placeholder)",
  "expires" : null,
  "id" : "sample id",
  "interest" : 1,
  "remainingamount" : "sample remainingamount",
  "totalamount" : "sample totalamount",
  "type" : "sample type"
}
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getPreapprovalLoanid = function(loanid, config, callback) {
  restletUtils.checkPathVariables(loanid, 'loanid');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/preapproval/' + loanid + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * Optional parameters: County (kommunenummer), gnr (gårdsnummer) and bnr (bruksnummer ) may be used to specify which property the preapproval is being checked for. This may allow DNB to find out that the preapproval holder is in a bidding process.
 * @method
 * @name APIdrafts#getPreapprovalPersonnrDesiredamount
 * @param {string} personnr - REQUIRED - 11-digit personal id number
 * @param {string} desiredamount - REQUIRED - How much?
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {string} config.queryParameters.county - Number of the county (kommune).
 * @param {string} config.queryParameters.gnr - Gårdsnummer.
 * @param {string} config.queryParameters.bnr - Bruksnummer.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getPreapprovalPersonnrDesiredamount = function(personnr, desiredamount, config, callback) {
  restletUtils.checkPathVariables(personnr, 'personnr', desiredamount, 'desiredamount');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/preapproval/' + personnr + '/' + desiredamount + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * For now apply for a specified amount only. If the amouhnt specified is 0 (zero), the application will be treated as for the maximum possible amount.
 * @method
 * @name APIdrafts#postPreapprovalPersonnrDesiredamount
 * @param {string} personnr - REQUIRED - 11-digit personal id number
 * @param {string} desiredamount - REQUIRED - How much?
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.postPreapprovalPersonnrDesiredamount = function(personnr, desiredamount, config, callback) {
  restletUtils.checkPathVariables(personnr, 'personnr', desiredamount, 'desiredamount');

  restletUtils.executeRequest.call(this, 'POST',
    this.endpoint + '/preapproval/' + personnr + '/' + desiredamount + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * When a loan is issued, either by DNB or another bank, the preapproval should be deleted.
Separate method to avoid specifying amount. "Personnr" is sufficient.
 * @method
 * @name APIdrafts#deletePreapprovalDeletePersonnr
 * @param {string} personnr - REQUIRED
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.deletePreapprovalDeletePersonnr = function(personnr, config, callback) {
  restletUtils.checkPathVariables(personnr, 'personnr');

  restletUtils.executeRequest.call(this, 'DELETE',
    this.endpoint + '/preapproval/delete/' + personnr + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getCurrencyConvertForeigncurrencyCurrencyAmount
 * @param {string} foreigncurrency - REQUIRED - ISO 4217: alpha 3-letter upcase e.g EUR
 * @param {string} currency - REQUIRED - ISO 4217: alpha 3-letter upcase e.g EUR
 * @param {integer} amount - REQUIRED - 
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {string} config.queryParameters.date - YYYY-MM-DD (or change to ISO timestamp?)
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Status 200
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getCurrencyConvertForeigncurrencyCurrencyAmount = function(foreigncurrency, currency, amount, config, callback) {
  restletUtils.checkPathVariables(foreigncurrency, 'foreigncurrency', currency, 'currency', amount, 'amount');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/currency/convert/' + foreigncurrency + '/' + currency + '/' + amount + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * Suitable for making a table or similar.
 * @method
 * @name APIdrafts#getCurrencyListCurrency
 * @param {string} currency - REQUIRED - "Home" currency
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Status 200
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getCurrencyListCurrency = function(currency, config, callback) {
  restletUtils.checkPathVariables(currency, 'currency');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/currency/list/' + currency + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * Possibly add parameters for markup/margin, specify a date, find min/max in an interval (separate method?), etc.
 * @method
 * @name APIdrafts#getCurrencyRateCurrency
 * @param {string} currency - REQUIRED - ISO 4217: alpha 3-letter upcase e.g EUR
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Status 200
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getCurrencyRateCurrency = function(currency, config, callback) {
  restletUtils.checkPathVariables(currency, 'currency');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/currency/rate/' + currency + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getLocationBranchFindbyaddress
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {string} config.queryParameters.address - For now: Assume geocoding functionality similar to Google Maps: https://developers.google.com/maps/documentation/geocoding/intro
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Status 200 - Payload :
{
  "description" : "sample description",
  "id" : "sample id",
  "latitude" : 1.1,
  "longditude" : 1.1
}
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getLocationBranchFindbyaddress = function(config, callback) {
  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/location/branch/findbyaddress',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getLocationBranchBranchidDetails
 * @param {string} branchid - REQUIRED - Id of branch
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Status 200
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getLocationBranchBranchidDetails = function(branchid, config, callback) {
  restletUtils.checkPathVariables(branchid, 'branchid');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/location/branch/' + branchid + '/details',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#postLocationBranchBranchidDetails
 * @param {string} branchid - REQUIRED - Id of branch
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.postLocationBranchBranchidDetails = function(branchid, config, callback) {
  restletUtils.checkPathVariables(branchid, 'branchid');

  restletUtils.executeRequest.call(this, 'POST',
    this.endpoint + '/location/branch/' + branchid + '/details',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getLocationBranchBranchidOpeninghours
 * @param {string} branchid - REQUIRED - Id of a branch
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Status 200 - Payload :
[{
  "close_hour" : 1,
  "close_minutes" : "sample close_minutes",
  "day" : 1,
  "open_hour" : 1,
  "open_minute" : 1
}]
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getLocationBranchBranchidOpeninghours = function(branchid, config, callback) {
  restletUtils.checkPathVariables(branchid, 'branchid');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/location/branch/' + branchid + '/openinghours',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#postLocationBranchBranchidOpeninghours
 * @param {string} branchid - REQUIRED - Id of a branch
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.postLocationBranchBranchidOpeninghours = function(branchid, config, callback) {
  restletUtils.checkPathVariables(branchid, 'branchid');

  restletUtils.executeRequest.call(this, 'POST',
    this.endpoint + '/location/branch/' + branchid + '/openinghours',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getLocationBranchLatitudeLongditude
 * @param {string} latitude - REQUIRED
 * @param {string} longditude - REQUIRED
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getLocationBranchLatitudeLongditude = function(latitude, longditude, config, callback) {
  restletUtils.checkPathVariables(latitude, 'latitude', longditude, 'longditude');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/location/branch/' + latitude + '/' + longditude + '/',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getLocationAtmLatitudeLongditude
 * @param {string} latitude - REQUIRED - Current latitude. Example: 37.4238253802915
 * @param {string} longditude - REQUIRED - Current longditude. Example: -122.0842499
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - List of minibank locations
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getLocationAtmLatitudeLongditude = function(latitude, longditude, config, callback) {
  restletUtils.checkPathVariables(latitude, 'latitude', longditude, 'longditude');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/location/atm/' + latitude + '/' + longditude + '/',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * Needs a lot of input. https://www.dnb.no/privat/forsikring/bilforsikring.html

For now: Assume the attributes of Car object are sufficient.
 * @method
 * @name APIdrafts#postInsuranceCarOfferLicensenumber
 * @param {string} licensenumber - REQUIRED - AB12345
 * @param {object} body - the payload; is of type: Car; has the following structure:
{
  "description" : "sample description",
  "id" : "sample id",
  "licensenumber" : "sample licensenumber",
  "milage" : 1.1,
  "value_new" : 1.1,
  "value_today" : 1.1
}
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Status 200 - Payload :
{
  "details (placeholder)" : "sample details (placeholder)"
}
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.postInsuranceCarOfferLicensenumber = function(licensenumber, body, config, callback) {
  restletUtils.checkPathVariables(licensenumber, 'licensenumber');

  restletUtils.executeRequest.call(this, 'POST',
    this.endpoint + '/insurance/car/offer/' + licensenumber + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations),
    body
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getInsuranceCarServiceLatitudeLongditude
 * @param {string} latitude - REQUIRED
 * @param {string} longditude - REQUIRED
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getInsuranceCarServiceLatitudeLongditude = function(latitude, longditude, config, callback) {
  restletUtils.checkPathVariables(latitude, 'latitude', longditude, 'longditude');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/insurance/car/service/' + latitude + '/' + longditude + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getInsuranceCarTowingserviceLatitudeLongditude
 * @param {string} latitude - REQUIRED
 * @param {string} longditude - REQUIRED
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Status 200
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getInsuranceCarTowingserviceLatitudeLongditude = function(latitude, longditude, config, callback) {
  restletUtils.checkPathVariables(latitude, 'latitude', longditude, 'longditude');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/insurance/car/towingservice/' + latitude + '/' + longditude + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * Check if a car is insured: /insurance/car/AB12345
 * @method
 * @name APIdrafts#getInsuranceCarLicensenumber
 * @param {string} licensenumber - REQUIRED - AB12345
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Status 200 - Payload :
{
  "amount" : 1,
  "id" : "sample id",
  "name" : "sample name",
  "type" : "sample type"
}
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getInsuranceCarLicensenumber = function(licensenumber, config, callback) {
  restletUtils.checkPathVariables(licensenumber, 'licensenumber');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/insurance/car/' + licensenumber + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * Needs a lot of input. https://www.dnb.no/privat/forsikring/bilforsikring.html
 * @method
 * @name APIdrafts#postInsuranceCarLicensenumber
 * @param {string} licensenumber - REQUIRED - AB12345
 * @param {object} body - the payload; is of type: string
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Offer provided. - Payload :
{
  "amount" : 1,
  "id" : "sample id",
  "name" : "sample name",
  "type" : "sample type"
}
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.postInsuranceCarLicensenumber = function(licensenumber, body, config, callback) {
  restletUtils.checkPathVariables(licensenumber, 'licensenumber');

  restletUtils.executeRequest.call(this, 'POST',
    this.endpoint + '/insurance/car/' + licensenumber + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations),
    body
  );
};

/**
 * 
 * @method
 * @name APIdrafts#postInsuranceClaim
 * @param {object} body - the payload; is of type: Insuranceclaim; has the following structure:
{
  "details (placeholder)" : "sample details (placeholder)"
}
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.postInsuranceClaim = function(body, config, callback) {
  restletUtils.executeRequest.call(this, 'POST',
    this.endpoint + '/insurance/claim',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations),
    body
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getInsuranceWeatheralertLatitudeLongditude
 * @param {string} latitude - REQUIRED
 * @param {string} longditude - REQUIRED
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {string} config.queryParameters.radius - Radius in km.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Yes there is, and here are the details.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getInsuranceWeatheralertLatitudeLongditude = function(latitude, longditude, config, callback) {
  restletUtils.checkPathVariables(latitude, 'latitude', longditude, 'longditude');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/insurance/weatheralert/' + latitude + '/' + longditude + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getInsurancePolicynumber
 * @param {string} policynumber - REQUIRED
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Status 200 - Payload :
{
  "amount" : 1,
  "id" : "sample id",
  "name" : "sample name",
  "type" : "sample type"
}
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getInsurancePolicynumber = function(policynumber, config, callback) {
  restletUtils.checkPathVariables(policynumber, 'policynumber');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/insurance/' + policynumber + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#postInsurancePolicynumber
 * @param {string} policynumber - REQUIRED
 * @param {object} body - the payload; is of type: Insurance; has the following structure:
{
  "amount" : 1,
  "id" : "sample id",
  "name" : "sample name",
  "type" : "sample type"
}
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.postInsurancePolicynumber = function(policynumber, body, config, callback) {
  restletUtils.checkPathVariables(policynumber, 'policynumber');

  restletUtils.executeRequest.call(this, 'POST',
    this.endpoint + '/insurance/' + policynumber + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations),
    body
  );
};

/**
 * 
 * @method
 * @name APIdrafts#deleteInsurancePolicynumber
 * @param {string} policynumber - REQUIRED
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.deleteInsurancePolicynumber = function(policynumber, config, callback) {
  restletUtils.checkPathVariables(policynumber, 'policynumber');

  restletUtils.executeRequest.call(this, 'DELETE',
    this.endpoint + '/insurance/' + policynumber + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getInsurancePolicynumberClaims
 * @param {string} policynumber - REQUIRED
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Status 200 - Payload :
[{
  "details (placeholder)" : "sample details (placeholder)"
}]
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getInsurancePolicynumberClaims = function(policynumber, config, callback) {
  restletUtils.checkPathVariables(policynumber, 'policynumber');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/insurance/' + policynumber + '/claims',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getInsurancePolicynumberClaimsClaimid
 * @param {string} policynumber - REQUIRED
 * @param {string} claimid - REQUIRED
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Status 200 - Payload :
{
  "details (placeholder)" : "sample details (placeholder)"
}
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getInsurancePolicynumberClaimsClaimid = function(policynumber, claimid, config, callback) {
  restletUtils.checkPathVariables(policynumber, 'policynumber', claimid, 'claimid');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/insurance/' + policynumber + '/claims/' + claimid + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getStocksPriceTickerTimestamp
 * @param {string} ticker - REQUIRED
 * @param {date} timestamp - REQUIRED
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getStocksPriceTickerTimestamp = function(ticker, timestamp, config, callback) {
  restletUtils.checkPathVariables(ticker, 'ticker', timestamp, 'timestamp');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/stocks/price/' + ticker + '/' + timestamp + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getStocksTicker
 * @param {string} ticker - REQUIRED
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getStocksTicker = function(ticker, config, callback) {
  restletUtils.checkPathVariables(ticker, 'ticker');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/stocks/' + ticker + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * From: https://www.vipps.no/portalfront/vipps/nedlast/eCommerce_API_v1.2.pdf

To launch the ViPPS app, use the following call (at a minimum):

vipps://?action=inAppPayment&appID=fa01s-21res-qq21p-mq1p6&amount=12000&merchantSerialNumber=ed9320pre&fallbackURL=app://?action=confirmed
 * @method
 * @name APIdrafts#getAppRoot
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {string} config.queryParameters.action - REQUIRED - for payment it should be “inAppPayment” and for reservation “inAppReserve”.
 * @param {string} config.queryParameters.appID - REQUIRED - App registration id will be created for Merchants’ each app registered in Vipps Systems and will be shared once the enrolment process is done
 * @param {string} config.queryParameters.amount - REQUIRED - Amount for which the payment should be initiated. The amount should in the lowest denomination i.e., Øre. Vipps only support NOK currency. Minimum value is 100 and maximum value is 9999999. Vipps will not round of the provided number. If the amount is not in the mentioned format, Vipps will return the FAIL state of transaction mentioning incorrect number format.
 * @param {string} config.queryParameters.orderID - REQUIRED - Order id from 3rdparty app for which user is initiating a payment. Maximum length for this field is 30 chars. This order ID will be stored in the Vipps systems against the transactions and shall be displayed the receipt in the Vipps App for the user. The order ID will also be presented for the respective transaction in the Sales and Settlement report.
 * @param {string} config.queryParameters.message - URL encoded string. Payment’s reference message. Maximum length for this field is 200 chars. The message field will be displayed as part of the receipt.
 * @param {string} config.queryParameters.merchantSerialNumber - REQUIRED - Merchant Sales Unit’s serial number will be a unique number in Vipps systems. The Serial number registered in Vipps Systems will be shared once the enrollment process is done.
 * @param {string} config.queryParameters.data - URL encoded string. If this exists, then the contents will go as a query string parameter with fallbackURL. Calling app can use this field to identify their session or booking for which the payment has been done. Maximum length for this field is 128 chars.
 * @param {string} config.queryParameters.fallbackURL - REQUIRED - URL encoded string. URL to be invoked once the payment process is complete. This is to respond back to calling app
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getAppRoot = function(config, callback) {
  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getPaymentsOrderIdSerialNumberMerchantSerialNumberDetails
 * @param {string} orderId - REQUIRED
 * @param {string} merchantSerialNumber - REQUIRED
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Status 200 - Payload :
{
  "orderId" : "sample orderId"
}
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getPaymentsOrderIdSerialNumberMerchantSerialNumberDetails = function(orderId, merchantSerialNumber, config, callback) {
  restletUtils.checkPathVariables(orderId, 'orderId', merchantSerialNumber, 'merchantSerialNumber');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/payments /' + orderId + '/serialNumber/' + merchantSerialNumber + '/details',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * Phone number, personal id number, name, card number, account number
 * @method
 * @name APIdrafts#postVippsCustomer
 * @param {object} body - the payload; is of type: string
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Status 200 - Payload :
{
  "Address" : "sample Address",
  "City" : "sample City",
  "Country" : "sample Country",
  "Email" : "sample Email",
  "Id" : "sample Id",
  "Name" : "sample Name",
  "Notes (placeholder)" : "sample Notes (placeholder)",
  "Phone" : "sample Phone",
  "Postcode" : "sample Postcode",
  "Website" : "sample Website",
  "program" : "sample program",
  "status" : "sample status",
  "welcomepackage" : "sample welcomepackage"
}
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.postVippsCustomer = function(body, config, callback) {
  restletUtils.executeRequest.call(this, 'POST',
    this.endpoint + '/vipps/customer',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations),
    body
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getVippsInvoiceOrgnr
 * @param {string} orgnr - REQUIRED - Organization number, 9 digits. Example: 123456789
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getVippsInvoiceOrgnr = function(orgnr, config, callback) {
  restletUtils.checkPathVariables(orgnr, 'orgnr');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/vipps/invoice/' + orgnr + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#postVippsInvoiceOrgnr
 * @param {string} orgnr - REQUIRED - Organization number, 9 digits. Example: 123456789
 * @param {object} body - the payload; is of type: Customer; has the following structure:
{
  "Address" : "sample Address",
  "City" : "sample City",
  "Country" : "sample Country",
  "Email" : "sample Email",
  "Id" : "sample Id",
  "Name" : "sample Name",
  "Notes (placeholder)" : "sample Notes (placeholder)",
  "Phone" : "sample Phone",
  "Postcode" : "sample Postcode",
  "Website" : "sample Website",
  "program" : "sample program",
  "status" : "sample status",
  "welcomepackage" : "sample welcomepackage"
}
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.postVippsInvoiceOrgnr = function(orgnr, body, config, callback) {
  restletUtils.checkPathVariables(orgnr, 'orgnr');

  restletUtils.executeRequest.call(this, 'POST',
    this.endpoint + '/vipps/invoice/' + orgnr + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations),
    body
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getVippsMerchantsLatitudeLongditude
 * @param {string} latitude - REQUIRED - Current latitude. Example: 37.4238253802915
 * @param {string} longditude - REQUIRED - Current longditude. Example: -122.0842499
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Status 200 - Payload :
[{
  "Address" : "sample Address",
  "City" : "sample City",
  "Country" : "sample Country",
  "Email" : "sample Email",
  "Id" : "sample Id",
  "Name" : "sample Name",
  "Notes (placeholder)" : "sample Notes (placeholder)",
  "Phone" : "sample Phone",
  "Postcode" : "sample Postcode",
  "Website" : "sample Website",
  "program" : "sample program",
  "status" : "sample status",
  "welcomepackage" : "sample welcomepackage"
}]
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getVippsMerchantsLatitudeLongditude = function(latitude, longditude, config, callback) {
  restletUtils.checkPathVariables(latitude, 'latitude', longditude, 'longditude');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/vipps/merchants/' + latitude + '/' + longditude + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * From 8.3.2: https://www.vipps.no/portalfront/vipps/nedlast/eCommerce_API_v1.2.pdf
 * @method
 * @name APIdrafts#postVippsPayments
 * @param {object} body - the payload; is of type: Vippspaymentrequest; has the following structure:
{
  "customerInfo" : {
    "mobileNumber" : "sample mobileNumber"
  },
  "merchantInfo" : {
    "callBack" : "sample callBack",
    "merchantSerialNumber" : "sample merchantSerialNumber"
  },
  "transaction" : {
    "amount" : 1,
    "orderId" : "sample orderId",
    "refOrderId" : "sample refOrderId",
    "timeStamp" : "sample timeStamp",
    "transactionText" : "sample transactionText"
  }
}
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 -  - Payload :
{
  "merchantSerialNumber" : "sample merchantSerialNumber",
  "orderId" : "sample orderId",
  "transactionInfo" : {
    "amount" : "sample amount",
    "message" : "sample message",
    "status" : "sample status",
    "timeStamp" : "sample timeStamp",
    "transactionId" : "sample transactionId"
  }
}
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.postVippsPayments = function(body, config, callback) {
  restletUtils.executeRequest.call(this, 'POST',
    this.endpoint + '/vipps/payments',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations),
    body
  );
};

/**
 * date, name, participants, currency(?)
 * @method
 * @name APIdrafts#postVippsSettlementCreate
 * @param {object} body - the payload; is of type: string
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.postVippsSettlementCreate = function(body, config, callback) {
  restletUtils.executeRequest.call(this, 'POST',
    this.endpoint + '/vipps/settlement/create',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations),
    body
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getVippsSettlementSettlementid
 * @param {string} settlementid - REQUIRED
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Created, status, name, owners/admins, list of expenses
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getVippsSettlementSettlementid = function(settlementid, config, callback) {
  restletUtils.checkPathVariables(settlementid, 'settlementid');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/vipps/settlement/' + settlementid + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * (Not possible in Vipps today)
 * @method
 * @name APIdrafts#deleteVippsSettlementSettlementid
 * @param {string} settlementid - REQUIRED
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.deleteVippsSettlementSettlementid = function(settlementid, config, callback) {
  restletUtils.checkPathVariables(settlementid, 'settlementid');

  restletUtils.executeRequest.call(this, 'DELETE',
    this.endpoint + '/vipps/settlement/' + settlementid + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getVippsSettlementSettlementidExpense
 * @param {string} settlementid - REQUIRED
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getVippsSettlementSettlementidExpense = function(settlementid, config, callback) {
  restletUtils.checkPathVariables(settlementid, 'settlementid');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/vipps/settlement/' + settlementid + '/expense',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * Date, who, amount, description, etc
 * @method
 * @name APIdrafts#postVippsSettlementSettlementidExpense
 * @param {string} settlementid - REQUIRED
 * @param {object} body - the payload; is of type: string
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.postVippsSettlementSettlementidExpense = function(settlementid, body, config, callback) {
  restletUtils.checkPathVariables(settlementid, 'settlementid');

  restletUtils.executeRequest.call(this, 'POST',
    this.endpoint + '/vipps/settlement/' + settlementid + '/expense',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations),
    body
  );
};

/**
 * 
 * @method
 * @name APIdrafts#deleteVippsSettlementSettlementidExpense
 * @param {string} settlementid - REQUIRED
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.deleteVippsSettlementSettlementidExpense = function(settlementid, config, callback) {
  restletUtils.checkPathVariables(settlementid, 'settlementid');

  restletUtils.executeRequest.call(this, 'DELETE',
    this.endpoint + '/vipps/settlement/' + settlementid + '/expense',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getVippsSettlementSettlementidSettle
 * @param {string} settlementid - REQUIRED
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getVippsSettlementSettlementidSettle = function(settlementid, config, callback) {
  restletUtils.checkPathVariables(settlementid, 'settlementid');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/vipps/settlement/' + settlementid + '/settle',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * Should POST a Person, not Customer
 * @method
 * @name APIdrafts#postVippsSettlementSettlementidUser
 * @param {string} settlementid - REQUIRED
 * @param {object} body - the payload; is of type: Customer; has the following structure:
{
  "Address" : "sample Address",
  "City" : "sample City",
  "Country" : "sample Country",
  "Email" : "sample Email",
  "Id" : "sample Id",
  "Name" : "sample Name",
  "Notes (placeholder)" : "sample Notes (placeholder)",
  "Phone" : "sample Phone",
  "Postcode" : "sample Postcode",
  "Website" : "sample Website",
  "program" : "sample program",
  "status" : "sample status",
  "welcomepackage" : "sample welcomepackage"
}
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.postVippsSettlementSettlementidUser = function(settlementid, body, config, callback) {
  restletUtils.checkPathVariables(settlementid, 'settlementid');

  restletUtils.executeRequest.call(this, 'POST',
    this.endpoint + '/vipps/settlement/' + settlementid + '/user',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations),
    body
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getVippsSettlementSettlementidUserUserid
 * @param {string} settlementid - REQUIRED
 * @param {string} userid - REQUIRED
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Maybe just a Person, as he/she may not be a customer with us. - Payload :
{
  "Address" : "sample Address",
  "City" : "sample City",
  "Country" : "sample Country",
  "Email" : "sample Email",
  "Id" : "sample Id",
  "Name" : "sample Name",
  "Notes (placeholder)" : "sample Notes (placeholder)",
  "Phone" : "sample Phone",
  "Postcode" : "sample Postcode",
  "Website" : "sample Website",
  "program" : "sample program",
  "status" : "sample status",
  "welcomepackage" : "sample welcomepackage"
}
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getVippsSettlementSettlementidUserUserid = function(settlementid, userid, config, callback) {
  restletUtils.checkPathVariables(settlementid, 'settlementid', userid, 'userid');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/vipps/settlement/' + settlementid + '/user/' + userid + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getSaftOrgnr
 * @param {string} orgnr - REQUIRED - Organization number, 9 digits. Example: 123456789
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Status 200 - Payload :
{
  "fileurl" : "sample fileurl",
  "id" : "sample id",
  "type" : "sample type"
}
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getSaftOrgnr = function(orgnr, config, callback) {
  restletUtils.checkPathVariables(orgnr, 'orgnr');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/saf-t/' + orgnr + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#postSaftOrgnr
 * @param {string} orgnr - REQUIRED - Organization number, 9 digits. Example: 123456789
 * @param {object} body - the payload; is of type: SAF-Tfile; has the following structure:
{
  "fileurl" : "sample fileurl",
  "id" : "sample id",
  "type" : "sample type"
}
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.postSaftOrgnr = function(orgnr, body, config, callback) {
  restletUtils.checkPathVariables(orgnr, 'orgnr');

  restletUtils.executeRequest.call(this, 'POST',
    this.endpoint + '/saf-t/' + orgnr + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations),
    body
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getSaftOrgnrType
 * @param {string} orgnr - REQUIRED - Organization number, 9 digits. Example: 123456789
 * @param {string} type - REQUIRED - Type of SAF-T file
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Status 200 - Payload :
{
  "fileurl" : "sample fileurl",
  "id" : "sample id",
  "type" : "sample type"
}
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getSaftOrgnrType = function(orgnr, type, config, callback) {
  restletUtils.checkPathVariables(orgnr, 'orgnr', type, 'type');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/saf-t/' + orgnr + '/' + type + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#postSaftOrgnrType
 * @param {string} orgnr - REQUIRED - Organization number, 9 digits. Example: 123456789
 * @param {string} type - REQUIRED - Type of SAF-T file
 * @param {object} body - the payload; is of type: SAF-Tfile; has the following structure:
{
  "fileurl" : "sample fileurl",
  "id" : "sample id",
  "type" : "sample type"
}
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.postSaftOrgnrType = function(orgnr, type, body, config, callback) {
  restletUtils.checkPathVariables(orgnr, 'orgnr', type, 'type');

  restletUtils.executeRequest.call(this, 'POST',
    this.endpoint + '/saf-t/' + orgnr + '/' + type + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations),
    body
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getReportsAccountsOrgnr
 * @param {string} orgnr - REQUIRED - Organization number, 9 digits. Example: 123456789
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Different use than /accounts? - Payload :
{
  "details" : "sample details",
  "id" : "sample id"
}
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getReportsAccountsOrgnr = function(orgnr, config, callback) {
  restletUtils.checkPathVariables(orgnr, 'orgnr');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/reports/accounts/' + orgnr + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getReportsAdvicesYearMonthOrgnr
 * @param {string} year - REQUIRED - Year, 4 digits. Example: 2017
 * @param {string} month - REQUIRED - Month, 2 digits (January is 01). Example: 01
 * @param {string} orgnr - REQUIRED - Organization number, 9 digits. Example: 123456789
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Status 200 - Payload :
[{
  "details" : "sample details",
  "id" : "sample id",
  "type" : "sample type"
}]
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getReportsAdvicesYearMonthOrgnr = function(year, month, orgnr, config, callback) {
  restletUtils.checkPathVariables(year, 'year', month, 'month', orgnr, 'orgnr');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/reports/advices/' + year + '/' + month + '/' + orgnr + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#deleteReportsAdvicesYearMonthOrgnr
 * @param {string} year - REQUIRED - Year, 4 digits. Example: 2017
 * @param {string} month - REQUIRED - Month, 2 digits (January is 01). Example: 01
 * @param {string} orgnr - REQUIRED - Organization number, 9 digits. Example: 123456789
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.deleteReportsAdvicesYearMonthOrgnr = function(year, month, orgnr, config, callback) {
  restletUtils.checkPathVariables(year, 'year', month, 'month', orgnr, 'orgnr');

  restletUtils.executeRequest.call(this, 'DELETE',
    this.endpoint + '/reports/advices/' + year + '/' + month + '/' + orgnr + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getReportsAnnualYearOrgnr
 * @param {string} year - REQUIRED - Year, 4 digits. Example: 2017
 * @param {string} orgnr - REQUIRED - Organization number, 9 digits. Example: 123456789
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Report found. - Payload :
{
  "details" : "sample details",
  "orgnr" : "sample orgnr",
  "year" : "sample year"
}
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getReportsAnnualYearOrgnr = function(year, orgnr, config, callback) {
  restletUtils.checkPathVariables(year, 'year', orgnr, 'orgnr');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/reports/annual/' + year + '/' + orgnr + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getReportsBehaviourOrgnr
 * @param {string} orgnr - REQUIRED - Organization number, 9 digits. Example: 123456789
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Status 200 - Payload :
{
  "details" : "sample details",
  "id" : "sample id"
}
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getReportsBehaviourOrgnr = function(orgnr, config, callback) {
  restletUtils.checkPathVariables(orgnr, 'orgnr');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/reports/behaviour/' + orgnr + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#deleteReportsBehaviourOrgnr
 * @param {string} orgnr - REQUIRED - Organization number, 9 digits. Example: 123456789
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.deleteReportsBehaviourOrgnr = function(orgnr, config, callback) {
  restletUtils.checkPathVariables(orgnr, 'orgnr');

  restletUtils.executeRequest.call(this, 'DELETE',
    this.endpoint + '/reports/behaviour/' + orgnr + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getReportsBenchmarksYearMonthOrgnrComparetoOrgnr_compare
 * @param {string} year - REQUIRED - Year, 4 digits. Example: 2017
 * @param {string} month - REQUIRED - Month, 2 digits (January is 01). Example: 01
 * @param {string} orgnr - REQUIRED - Organization number, 9 digits. Example: 123456789
 * @param {string} orgnr_compare - REQUIRED - Organization number of company to to compare with, 9 digits. Example: 123456789
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {string} config.queryParameters.?type= - What to compare: revenue (default=, profitability, liquidity
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Status 200 - Payload :
{
  "details" : "sample details",
  "id" : "sample id"
}
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getReportsBenchmarksYearMonthOrgnrComparetoOrgnr_compare = function(year, month, orgnr, orgnr_compare, config, callback) {
  restletUtils.checkPathVariables(year, 'year', month, 'month', orgnr, 'orgnr', orgnr_compare, 'orgnr_compare');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/reports/benchmarks/' + year + '/' + month + '/' + orgnr + '/compareto/' + orgnr_compare + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getReportsCalendarsYearMonthOrgnr
 * @param {string} year - REQUIRED - Year, 4 digits. Example: 2017
 * @param {string} month - REQUIRED - Month, 2 digits (January is 01). Example: 01
 * @param {string} orgnr - REQUIRED - Organization number, 9 digits. Example: 123456789
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Status 200 - Payload :
{
  "details" : "sample details",
  "id" : "sample id"
}
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getReportsCalendarsYearMonthOrgnr = function(year, month, orgnr, config, callback) {
  restletUtils.checkPathVariables(year, 'year', month, 'month', orgnr, 'orgnr');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/reports/calendars/' + year + '/' + month + '/' + orgnr + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getReportsChatsYearMonthOrgnr
 * @param {string} year - REQUIRED - Year, 4 digits. Example: 2017
 * @param {string} month - REQUIRED - Month, 2 digits (January is 01). Example: 01
 * @param {string} orgnr - REQUIRED - Organization number, 9 digits. Example: 123456789
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Status 200 - Payload :
{
  "details" : "sample details",
  "id" : "sample id"
}
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getReportsChatsYearMonthOrgnr = function(year, month, orgnr, config, callback) {
  restletUtils.checkPathVariables(year, 'year', month, 'month', orgnr, 'orgnr');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/reports/chats/' + year + '/' + month + '/' + orgnr + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getReportsExpensesYearMonthOrgnr
 * @param {string} year - REQUIRED - Year, 4 digits. Example: 2017
 * @param {string} month - REQUIRED - Month, 2 digits (January is 01). Example: 01
 * @param {string} orgnr - REQUIRED - Organization number, 9 digits. Example: 123456789
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Status 200 - Payload :
{
  "details" : "sample details",
  "id" : "sample id"
}
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getReportsExpensesYearMonthOrgnr = function(year, month, orgnr, config, callback) {
  restletUtils.checkPathVariables(year, 'year', month, 'month', orgnr, 'orgnr');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/reports/expenses/' + year + '/' + month + '/' + orgnr + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getReportsInvoicesOrgnr
 * @param {string} orgnr - REQUIRED - Organization number, 9 digits. Example: 123456789
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Status 200 - Payload :
{
  "details" : "sample details",
  "id" : "sample id"
}
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getReportsInvoicesOrgnr = function(orgnr, config, callback) {
  restletUtils.checkPathVariables(orgnr, 'orgnr');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/reports/invoices/' + orgnr + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getReportsLiquidityYearmonthOrgnr
 * @param {string} year - REQUIRED - Year, 4 digits. Example: 2017
 * @param {string} month - REQUIRED - Month, 2 digits (January is 01). Example: 01
 * @param {string} orgnr - REQUIRED - Organization number, 9 digits. Example: 123456789
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Status 200 - Payload :
{
  "details" : "sample details",
  "id" : "sample id"
}
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getReportsLiquidityYearmonthOrgnr = function(year, month, orgnr, config, callback) {
  restletUtils.checkPathVariables(year, 'year', month, 'month', orgnr, 'orgnr');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/reports/liquidity/' + year + '' + month + '/' + orgnr + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getReportsLiquidityprognosisYearMonthOrgnr
 * @param {string} year - REQUIRED - Year, 4 digits. Example: 2017
 * @param {string} month - REQUIRED - Month, 2 digits (January is 01). Example: 01
 * @param {string} orgnr - REQUIRED - Organization number, 9 digits. Example: 123456789
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Status 200 - Payload :
{
  "details" : "sample details",
  "id" : "sample id"
}
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getReportsLiquidityprognosisYearMonthOrgnr = function(year, month, orgnr, config, callback) {
  restletUtils.checkPathVariables(year, 'year', month, 'month', orgnr, 'orgnr');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/reports/liquidityprognosis/' + year + '/' + month + '/' + orgnr + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#deleteReportsLiquidityprognosisYearMonthOrgnr
 * @param {string} year - REQUIRED - Year, 4 digits. Example: 2017
 * @param {string} month - REQUIRED - Month, 2 digits (January is 01). Example: 01
 * @param {string} orgnr - REQUIRED - Organization number, 9 digits. Example: 123456789
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.deleteReportsLiquidityprognosisYearMonthOrgnr = function(year, month, orgnr, config, callback) {
  restletUtils.checkPathVariables(year, 'year', month, 'month', orgnr, 'orgnr');

  restletUtils.executeRequest.call(this, 'DELETE',
    this.endpoint + '/reports/liquidityprognosis/' + year + '/' + month + '/' + orgnr + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getReportsLiquiditystatusYearMonthOrgnr
 * @param {string} year - REQUIRED - Year, 4 digits. Example: 2017
 * @param {string} month - REQUIRED - Month, 2 digits (January is 01). Example: 01
 * @param {string} orgnr - REQUIRED - Organization number, 9 digits. Example: 123456789
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Status 200 - Payload :
{
  "details" : "sample details",
  "id" : "sample id"
}
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getReportsLiquiditystatusYearMonthOrgnr = function(year, month, orgnr, config, callback) {
  restletUtils.checkPathVariables(year, 'year', month, 'month', orgnr, 'orgnr');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/reports/liquiditystatus/' + year + '/' + month + '/' + orgnr + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getReportsLoanpaymentsYearMonthOrgnr
 * @param {string} year - REQUIRED - Year, 4 digits. Example: 2017
 * @param {string} month - REQUIRED - Month, 2 digits (January is 01). Example: 01
 * @param {string} orgnr - REQUIRED - Organization number, 9 digits. Example: 123456789
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Status 200 - Payload :
{
  "details" : "sample details",
  "id" : "sample id"
}
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getReportsLoanpaymentsYearMonthOrgnr = function(year, month, orgnr, config, callback) {
  restletUtils.checkPathVariables(year, 'year', month, 'month', orgnr, 'orgnr');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/reports/loanpayments/' + year + '/' + month + '/' + orgnr + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getReportsNotificationsYearMonthOrgnr
 * @param {string} year - REQUIRED - Year, 4 digits. Example: 2017
 * @param {string} month - REQUIRED - Month, 2 digits (January is 01). Example: 01
 * @param {string} orgnr - REQUIRED - Organization number, 9 digits. Example: 123456789
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Status 200 - Payload :
{
  "details" : "sample details",
  "id" : "sample id"
}
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getReportsNotificationsYearMonthOrgnr = function(year, month, orgnr, config, callback) {
  restletUtils.checkPathVariables(year, 'year', month, 'month', orgnr, 'orgnr');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/reports/notifications/' + year + '/' + month + '/' + orgnr + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getReportsOrganizationOrgnr
 * @param {string} orgnr - REQUIRED - Organization number, 9 digits. Example: 123456789
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - Status 200 - Payload :
{
  "details" : "sample details",
  "id" : "sample id"
}
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getReportsOrganizationOrgnr = function(orgnr, config, callback) {
  restletUtils.checkPathVariables(orgnr, 'orgnr');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/reports/organization/' + orgnr + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getReportsProductsOrgnr
 * @param {string} orgnr - REQUIRED - Organization number, 9 digits. Example: 123456789
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getReportsProductsOrgnr = function(orgnr, config, callback) {
  restletUtils.checkPathVariables(orgnr, 'orgnr');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/reports/products/' + orgnr + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getReportsProfitabilityYearMonthOrgnr
 * @param {string} year - REQUIRED - Year, 4 digits. Example: 2017
 * @param {string} month - REQUIRED - Month, 2 digits (January is 01). Example: 01
 * @param {string} orgnr - REQUIRED - Organization number, 9 digits. Example: 123456789
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getReportsProfitabilityYearMonthOrgnr = function(year, month, orgnr, config, callback) {
  restletUtils.checkPathVariables(year, 'year', month, 'month', orgnr, 'orgnr');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/reports/profitability/' + year + '/' + month + '/' + orgnr + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getReportsRevenuesYearMonthOrgnr
 * @param {string} year - REQUIRED - Year, 4 digits. Example: 2017
 * @param {string} month - REQUIRED - Month, 2 digits (January is 01). Example: 01
 * @param {string} orgnr - REQUIRED - Organization number, 9 digits. Example: 123456789
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getReportsRevenuesYearMonthOrgnr = function(year, month, orgnr, config, callback) {
  restletUtils.checkPathVariables(year, 'year', month, 'month', orgnr, 'orgnr');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/reports/revenues/' + year + '/' + month + '/' + orgnr + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getReportsRolesYearOrgnr
 * @param {string} year - REQUIRED - Year, 4 digits. Example: 2017
 * @param {string} orgnr - REQUIRED - Organization number, 9 digits. Example: 123456789
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getReportsRolesYearOrgnr = function(year, orgnr, config, callback) {
  restletUtils.checkPathVariables(year, 'year', orgnr, 'orgnr');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/reports/roles/' + year + '/' + orgnr + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getReportsSolidityOrgnr
 * @param {string} orgnr - REQUIRED - Organization number, 9 digits. Example: 123456789
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getReportsSolidityOrgnr = function(orgnr, config, callback) {
  restletUtils.checkPathVariables(orgnr, 'orgnr');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/reports/solidity/' + orgnr + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getReportsSystemOrgnr
 * @param {string} orgnr - REQUIRED - Organization number, 9 digits. Example: 123456789
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getReportsSystemOrgnr = function(orgnr, config, callback) {
  restletUtils.checkPathVariables(orgnr, 'orgnr');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/reports/system/' + orgnr + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getReportsTransactionsYearMonthOrgnr
 * @param {string} year - REQUIRED - Year, 4 digits. Example: 2017
 * @param {string} month - REQUIRED - Month, 2 digits (January is 01). Example: 01
 * @param {string} orgnr - REQUIRED - Organization number, 9 digits. Example: 123456789
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *    - Status code : 200 - {
    "transactions": [
        {
            "account_balance": 12325,
            "amount": -20,
            "created": "2016-08-05T08:00:00Z",
            "currency": "NOK",
            "description": "DNB Børsbaren",
            "id": "<id>",
            "merchant_id": "<id>",
            "notes": "Dobbel espresso",
            "settled": "2016-08-05T08:00:00Z ",
            "category": "restaurant"
        },
        {
            "account_balance": 12305,
            "amount": -20,
            "created": "2016-08-05T08:05:00Z",
            "currency": "NOK",
            "description": "DNB Børsbaren",
            "id": "<id>",
            "merchant_id": "<id>",
            "notes": "Dobbel espresso",
            "settled": "2016-08-05T08:05:00Z ",
            "category": "restaurant"
        },
    ]
}
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getReportsTransactionsYearMonthOrgnr = function(year, month, orgnr, config, callback) {
  restletUtils.checkPathVariables(year, 'year', month, 'month', orgnr, 'orgnr');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/reports/transactions/' + year + '/' + month + '/' + orgnr + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

/**
 * 
 * @method
 * @name APIdrafts#getReportsTurnoversYearMonthOrgnr
 * @param {string} year - REQUIRED - Year, 4 digits. Example: 2017
 * @param {string} month - REQUIRED - Month, 2 digits (January is 01). Example: 01
 * @param {string} orgnr - REQUIRED - Organization number, 9 digits. Example: 123456789
 * @param {object} config - the configuration object containing the query parameters and additional headers.
 * @param {object} config.headers - headers to use for the request in addition to the default ones.
 * @param {object} config.queryParameters - query parameters to use for the request in addition to the default ones.
 * @param {Function} callback - the callback called after request completion with the following parameters:
 *  - error if any technical error occured or if the response's status does not belong to the 2xx range. In that case the error would have the following structure:
{
  status : 400,
  message : 'The request cannot be fulfilled due to XXX'
}
 *  - body of the response auto-extracted from the response if the status is in the 2xx range.
 *  - response the technical (low-level) node response (c.f. https://nodejs.org/api/http.html#http_http_incomingmessage)
 */
APIdrafts.prototype.getReportsTurnoversYearMonthOrgnr = function(year, month, orgnr, config, callback) {
  restletUtils.checkPathVariables(year, 'year', month, 'month', orgnr, 'orgnr');

  restletUtils.executeRequest.call(this, 'GET',
    this.endpoint + '/reports/turnovers/' + year + '/' + month + '/' + orgnr + '',
    callback,
    securityUtils.addSecurityConfiguration(config, this.globalSecurity, this.securityConfigurations)
  );
};

module.exports = APIdrafts;
