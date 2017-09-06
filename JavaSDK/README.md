API drafts  version 1.0.5
==================

The SDK aims to ease the manipulation of :
- API drafts
  **WORK IN PROGRESS! **

This is a working document, an informal draft for API "note-taking", evaluation of API-related tools, etc. Contains several errors, contradictions and mistakes. Some Wikipedia texts added as placeholders/examples.

HTTP response codes

* Wikipedia: https://en.wikipedia.org/wiki/List_of_HTTP_status_codes
* W3C: https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html


| HTTP method | Success  | Fail  |
| -------- | -------- | -------- |
| GET     | 200     | 404, 4XX, 5XX     |
| POST     | 200, 201     | 400, 404, 4XX, 5XX     |
| PUT | 204 | 400, 404, 4XX, 5XX |




Getting started
---------------

### Use the SDK in your project

This project is a maven project. To package it, run `mvn clean install`. The corresponding jar file is generated
under the `target` directory and is now available from you maven local repository.
You can then import it in your project as a maven dependency.

### Project structure

* The main entry point to make API calls is the `Sdk` class
* The representation beans for input and output payloads are in package `net.apispark.webapi.representation`
* The classes for authentication are in package `net.apispark.webapi.security`. See section "Configure credentials" below.

### Maven integration

In your `pom.xml`, add the following lines:

```
<dependencies>
    <!-- ... -->
    <dependency>
        <groupId>net.apispark.webapi</groupId>
        <artifactId>APIdrafts</artifactId>
        <version>1.0.0</version>
    </dependency>
</dependencies>
```

### Usage

Considering an API called companyApi with the following endpoints:
* GET /companies/{companyId}
* POST /companies

With this methods, an API call would be done with the following code :

```java
Sdk companyApi = new Sdk();

// Let's set up the basic auth
SecurityConfig securityConfig = companyApi.getConfig().getSecurityConfig();
// Method name depends of the supported security scheme of the API. For more details about how to configure security, see section "Configure credentials" below

securityConfig.configureAuthBasic("login", "password");

try {
    Company company = companyApi.company("1").getCompany();
    System.out.println("Company's name: " + company.getName());
} catch(ResourceException e) {
    System.err.println("Status: " + e.getStatus()); // An exception occurs when getting the company
}

// Add a new company
Company newCompany = new Company();
newCompany.setName("My company");
Address newCompanyAddress = new Address();
newCompanyAddress.setStreet("");
newCompanyAddress.setZipcode("");
newCompanyAddress.setCity("");
newCompany.setAddress(newCompanyAddress);
newCompany.setTags(Arrays.asList("high technologies"));

try {
    Company addedCompany = companyApi.companyList().postCompanyList(newCompany);
    System.out.println("New company created with ID " + addedCompany.getId());
} catch(ResourceException e) {
    System.err.println("Status: " + e.getStatus());
}
```

### Configure credentials

The `SecurityConfig` class can help with the following kinds of authentications: HTTP Basic, custom query parameter, custom header, OAuth 2.0, and custom security schemes.

#### Settings for security schemes declared in the API definition

```java
companyApi.getConfig().getSecurityConfig()
        // Security schemes declared in the API definition have their own methods to configure them.
            .configureAuthBasic("userId", "password");
```

#### Custom security scheme

* Configure a custom security scheme. In the case when the API definition is not up-to-date or incorrect,
declared schemes can be overridden by any kind of authentication mechanism (simply implement Authenticator
or use one of the generic implementations found in `net.apispark.webapi.security.authenticators`).

```java
companyApi.getConfig().getSecurityConfig()
            .configureCustomAuth(MyApiKeyAuthenticator.class,
                    new HeaderApiKeyAuthenticator("X-Custom-Auth", "customToken"));
```

* Configure a custom security scheme. In case the API definition doesn't include any security information,
a custom global scheme can be configured. This scheme will be used as a fallback for all calls.

```java
companyApi.getConfig().getSecurityConfig()
            .configureCustomGlobalAuth(
                    new HeaderApiKeyAuthenticator("X-Custom-Auth", "customToken"));
```

#### Support of untrusted SSL certificates when using HTTPS

If the API's server defines a SSL certificate that is not trusted by the JVM, you need to explicitly specify that you trust this certificate.
This happens, for example, if the SSL certificate is self-signed.

The preferred methods are to update the JVM's truststore or a specific truststore with the API's server certificate. 
Here is a resource that explains how to do this:
https://restlet.com/technical-resources/restlet-framework/guide/2.3/core/security/https#toc_3

Another way is to programmatically trust one or more certificates by calling the `addTrustedCertificate` method of the `SecurityConfig` class.

```java
securityConfig.addTrustedCertificate(Files.newInputStream(myCertificatePath));
```

#### OAuth 2.0 Implicit Grant Flow

The SDK provides helpers for implementing OAuth 2.0 Implicit Grant Flow. Please refer to the [OAuth documentation](https://tools.ietf.org/html/rfc6749) for an explanation of the flow.
The Implicit Grant Flow is handled by the the `OAuth2ImplicitFlowAuthenticator` class located in the `net.apispark.webapi.security.authenticators` package.


##### Basic usage example

```java
// Configure the OAuth Authenticator for the Implicit Grant Flow
OAuth2ImplicitFlowAuthenticator auth = new OAuth2ImplicitFlowAuthenticator()
                .setAuthorizationUri("http://example.com/api/authorization")
                .setClientId("my-client-id")
                .setScope("read")
                .setRedirectionUri(REDIRECT_URI);
companyApi.getConfig().getSecurityConfig().configureCustomGlobalAuth(auth);

// Additional parameters can be added
auth.setAdditionalParameter("my-specific-parameter", "42");

// Get the full authorization URI
String authorizationUri = auth.getAuthorizationUri();

// Here you'll need to open the authorizationUri in an embedded WebView (user-agent).
// Through this page, the OAuth provider will ask the user (resource owner) to authorize the application.
// Once authorized, the user-agent will be redirected to the previously given REDIRECT_URI.
// Depending on the technology you'll choose to handle the WebView, you'll have a way to intercept this redirect and return back to your application flow.

// Give the full redirect URL to the SDK to extract and store the token from fragment
auth.fetchAccessToken(url);

// Finally call the API (with the token as an Authorization header)
try {
    Company company = companyApi.company("1").getCompany();
    System.out.println("Company's name: " + company.getName());
} catch(ResourceException e) {
    System.err.println("Status: " + e.getStatus()); // An exception occurs when getting the company
}
```


By default, the access token is sent in the Authentication header (`Authentication: Bearer <token>`).
You can choose to send the token in a query parameter named `access_token` by calling:

```java
auth.sendAccessTokenAsQueryParameter(true);
```

Please refer to the Javadoc for more details on the API.

#### OAuth 2.0 Resource Owner Password Flow

The Client SDK provides helpers for implementing OAuth 2.0 Resource Owner Password Flow. Please refer to the [OAuth documentation](https://tools.ietf.org/html/rfc6749) for an explanation of the flow.
The Resource Owner Password Flow is handled by the `OAuth2ResourceOwnerPasswordFlowAuthenticator` class located in the `net.apispark.webapi.security.authenticators` package.


##### Basic usage example

```java
// Configure the OAuth Authenticator for the Resource Owner Password Flow
OAuth2ResourceOwnerPasswordFlowAuthenticator auth = new OAuth2ResourceOwnerPasswordFlowAuthenticator()
                .setTokenUri("http://example.com/api/token");
companyApi.getConfig().getSecurityConfig().configureCustomGlobalAuth(auth);

// Add some parameters
auth.setAdditionalParameter("client_id", "my-client-id")
   .setAdditionalParameter("client_secret", "123456789");

// Ask the user (resource owner) for their credentials
// and register them in the Authenticator
auth.setUserName(username)
    .setPassword(password);

// Get a token in exchange of the credentials
auth.fetchAccessToken();

// Finally call the API (with the token)
try {
    Company company = companyApi.company("1").getCompany();
    System.out.println("Company's name: " + company.getName());
} catch(ResourceException e) {
    System.err.println("Status: " + e.getStatus()); // An exception occurs when getting the company
}
```

By default, the access token is sent in the Authentication header (`Authentication: Bearer <token>`).
You can choose to send the token as a query parameter (named `access_token`) by calling:

```java
auth.sendAccessTokenAsQueryParameter(true);
```

###### Support of untrusted SSL certificates when using HTTPS

If the authorization server defines a SSL certificate that is not trusted by the JVM, you need to explicitly specify that you trust this certificate.
This happens, for example, if the SSL certificate is self-signed.

The preferred methods are to update the JVM's truststore or a specific truststore with the authorization server certificate. 
Here is a resource that explains how to do this:
https://restlet.com/technical-resources/restlet-framework/guide/2.3/core/security/https#toc_3

Another way is to programmatically trust one or more certificates by calling the `addTrustedCertificate` method of the `OAuth2ResourceOwnerPasswordFlowAuthenticator` class.

```java
auth.addTrustedCertificate(Files.newInputStream(myCertificatePath));
```

##### Refresh Token

Additionally to the Access Token, the OAuth provider can issue a Refresh Token.
For this case, the SDK provides some useful helpers.

Expiration information can be accessed, once the access token obtained:

```java
Integer tokenValidityInSeconds = auth.getExpiresIn();
Date estimatedExpirationDate = auth.getTokenExpirationDate();
```

By default, the SDK will try to automatically refresh the tokens if it has expired.
On an API call, and if the token has expired, the SDK will:
* trigger a refresh of the tokens,
* and then call the API with the newly obtained access token.

A token is considered as expired if the expiration time is over or a 401 error is received.
You can customize this 401 check for their needs by extending the `OAuth2ResourceOwnerPasswordFlowAuthenticator` class and override the `shouldRefreshToken()` method.

The automatic refreshing mechanism can be disabled by calling:

```java
auth.enableTokenAutoRefresh(false);
```

In this case, the refreshing can be done by manually calling:

```java
auth.refreshTokens();
```

Please refer to the Javadoc for more details on the API.

#### CSRF protection

The SDK embeds a protection against Cross-Site Request Forgery (CSRF).
In compliance with the OAuth specification, you can choose to send a "state" parameter when negotiating a token.
This state parameter should be returned unchanged by the Authentication server.

The SDK checks this to prevent CSRF.
If the state parameter doesn't match, an exception is thrown.

### Logging

By default, the SDK uses the java logging (JUL).
You can specify the configuration file with the following system property `-Djava.util.logging.config.file="/path/to/logging.properties"`

For more explanations, see: http://restlet.com/technical-resources/restlet-framework/guide/2.3/editions/jse/logging
