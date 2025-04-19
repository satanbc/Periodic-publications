import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import PublicationList from './components/PublicationList';
import AdminPublications from './components/AdminDashboard';
import SubscriptionForm from './components/SubscriptionForm';
import PaymentForm from './components/PaymentForm';
import { Link } from 'react-router-dom';
import AuthProvider from './auth0-provider';
import ProtectedRoute from './ProtectedRoute';

function App() {
    return (
        <AuthProvider>
            <Router>
                <div className="App">
                    <h1>Система Періодичні видання</h1>
                    <nav>
                        <Link to="/publications">Публікації</Link> |{" "}
                        <Link to="/admin/publications">Адміністратор</Link> |{" "}
                    </nav>
                    <Routes>
                        <Route path="/" element={<PublicationList />} />
                        <Route path="/publications" element={<PublicationList />} />
                        <ProtectedRoute
                            path="/admin/publications"
                            element={<AdminPublications />}
                        />
                        <Route path="/subscribe" element={<SubscriptionForm />} />
                        <Route path="/payment" element={<PaymentForm />} />
                    </Routes>
                </div>
            </Router>
        </AuthProvider>
    );
}

export default App;
