import React from 'react';
import { Auth0Provider } from '@auth0/auth0-react';

const domain = "YOUR_DOMAIN";
const clientId = "YOUR_CLIENT_ID";
const audience = "http://localhost:8080";

const AuthProvider = ({ children }) => (
    <Auth0Provider
        domain={domain}
        clientId={clientId}
        authorizationParams={{
            redirect_uri: window.location.origin,
            audience: audience
        }}
        cacheLocation="localstorage"
    >
        {children}
    </Auth0Provider>
);

export default AuthProvider;
