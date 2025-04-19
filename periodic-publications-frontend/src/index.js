import React from 'react';
import { createRoot } from 'react-dom/client';
import { Auth0Provider } from '@auth0/auth0-react';
import App from './App';
import './index.css';

const root = createRoot(document.getElementById('root'));

root.render(
    <Auth0Provider
        domain="dev-kqpgdhqmlnnp5fjc.us.auth0.com"
        clientId="n2pw6V1QtcUZdm8zSWXBhCLXgGbBxwSq"
        authorizationParams={{
            redirect_uri: window.location.origin
        }}
    >
        <App />
    </Auth0Provider>,
);
