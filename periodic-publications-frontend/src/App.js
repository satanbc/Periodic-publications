import React, { useEffect } from "react";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import { useAuth0 } from "@auth0/auth0-react";
import PublicationList from './components/PublicationList';
import AdminPublications from './components/AdminDashboard';
import SubscriptionForm from './components/SubscriptionForm';
import PaymentForm from './components/PaymentForm';
import LoginButton from './components/LoginButton';
import ProtectedRoute from './components/ProtectedRoute';
import SubscriptionsPage from "./components/SubscriptionsPage";

function App() {
    const { isAuthenticated, user, logout } = useAuth0();

    return (
        <Router>
            <div className="App">
                <h1>Система Періодичні видання</h1>

                <nav>
                    <Link to="/publications">Публікації</Link> |{" "}
                    <Link to="/my-subscriptions">Мої підписки</Link> |{" "}
                    <Link to="/admin/publications">Адміністратор</Link> {" "}
                    {isAuthenticated ? (
                        <button onClick={() => logout({ returnTo: window.location.origin })}>
                            Log Out
                        </button>
                    ) : (
                        <LoginButton />
                    )}
                </nav>

                {isAuthenticated && <p>Вітаю, {user.name}</p>}

                <Routes>
                    <Route path="/" element={<PublicationList />} />
                    <Route path="/publications" element={<PublicationList />} />
                    <Route path="/subscribe" element={<SubscriptionForm />} />
                    <Route path="/payment" element={<PaymentForm />} />
                    <Route path="/my-subscriptions" element={<SubscriptionsPage />} />
                    <Route
                        path="/admin/publications"
                        element={
                            <ProtectedRoute requiredRole="admin">
                                <AdminPublications />
                            </ProtectedRoute>
                        }
                    />
                </Routes>
            </div>
        </Router>
    );
}

export default App;
