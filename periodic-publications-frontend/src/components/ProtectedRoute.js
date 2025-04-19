import React from "react";
import { Navigate } from "react-router-dom";
import { useAuth0 } from "@auth0/auth0-react";

const ProtectedRoute = ({ children, requiredRole }) => {
    const { isAuthenticated, isLoading, user } = useAuth0();

    if (isLoading) return <div>Завантаження...</div>;

    if (!isAuthenticated) {
        return <Navigate to="/" />;
    }

    const roles = user && user["http://yourapp.example.com/roles"];
    const hasRequiredRole = roles && roles.includes(requiredRole);

    if (!hasRequiredRole) {
        return <div>У вас немає доступу до цієї сторінки.</div>;
    }

    return children;
};

export default ProtectedRoute;
