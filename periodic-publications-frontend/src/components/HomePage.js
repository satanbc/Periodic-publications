// src/components/HomePage.js
import React from 'react';
import { Link } from 'react-router-dom';

const HomePage = () => {
    return (
        <div>
            <h2>Welcome to Periodic Publications System</h2>
            <ul>
                <li><Link to="/publications">View Publications</Link></li>
                <li><Link to="/subscribe">Subscribe to Publications</Link></li>
                <li><Link to="/payment">Make a Payment</Link></li>
                <li><Link to="/admin">Admin Panel</Link></li>
            </ul>
        </div>
    );
};

export default HomePage;
