import React, { useEffect, useState } from 'react';
import { getSubscriptions } from './api';
import { Link } from 'react-router-dom';

const Subscriptions = () => {
    const [subscriptions, setSubscriptions] = useState([]);

    useEffect(() => {
        const fetchSubscriptions = async () => {
            // Отримуємо підписки користувача
            const data = await getSubscriptions();
            setSubscriptions(data);
        };

        fetchSubscriptions();
    }, []);

    return (
        <div>
            <h2>Your Subscriptions</h2>
            <ul>
                {subscriptions.map((subscription) => (
                    <li key={subscription.id}>
                        <h3>{subscription.publicationTitle}</h3>
                        <p>Duration: {subscription.months} months</p>
                        <p>Total Amount: ${subscription.amount}</p>
                        <Link to={`/pay/${subscription.id}`} className="btn">Pay Now</Link>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default Subscriptions;
