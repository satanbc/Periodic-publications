import React from 'react';
import { Route, Navigate } from 'react-router-dom';
import { useAuth0 } from "@auth0/auth0-react";

const ProtectedRoute = ({ element, ...rest }) => {
    const { isAuthenticated, user, isLoading } = useAuth0();

    if (isLoading) return <div>Loading...</div>;

    // Якщо не аутентифікований або не має ролі "admin", переходимо на головну сторінку
    if (!isAuthenticated || !user?.['http://schemas.yourapp.com/roles']?.includes('admin')) {
        return <Navigate to="/" replace />;
    }

    return <Route {...rest} element={element} />;
};

export default ProtectedRoute;
