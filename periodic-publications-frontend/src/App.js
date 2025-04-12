import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import PublicationsPage from './components/PublicationsPage';
import SubscriptionPage from './components/SubscriptionPage';
import PaymentPage from './components/PaymentPage';
import AdminPage from './components/AdminPage';

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<PublicationsPage />} />
                <Route path="/subscriptions" element={<SubscriptionPage />} />
                <Route path="/payment" element={<PaymentPage />} />
                <Route path="/admin" element={<AdminPage />} />
            </Routes>
        </Router>
    );
}

export default App;