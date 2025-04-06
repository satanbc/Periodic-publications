// src/components/SubscriptionPage.js
import React, { useState } from 'react';
import { submitSubscription } from '../api';

const SubscriptionPage = () => {
    const [publicationId, setPublicationId] = useState('');
    const [userInfo, setUserInfo] = useState('');

    const handleSubscriptionSubmit = (e) => {
        e.preventDefault();
        const subscriptionData = { publicationId, userInfo };
        submitSubscription(subscriptionData)
            .then(response => alert('Subscription successful!'))
            .catch(error => console.error('Error submitting subscription:', error));
    };

    return (
        <div>
            <h2>Subscribe to a Publication</h2>
            <form onSubmit={handleSubscriptionSubmit}>
                <label>
                    Publication ID:
                    <input
                        type="text"
                        value={publicationId}
                        onChange={(e) => setPublicationId(e.target.value)}
                        required
                    />
                </label>
                <br />
                <label>
                    Your Information:
                    <input
                        type="text"
                        value={userInfo}
                        onChange={(e) => setUserInfo(e.target.value)}
                        required
                    />
                </label>
                <br />
                <button type="submit">Subscribe</button>
            </form>
        </div>
    );
};

export default SubscriptionPage;
