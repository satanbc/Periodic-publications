import React, { useState } from 'react';
import { submitSubscription } from '../api';

function SubscriptionPage() {
    const [userId, setUserId] = useState('');
    const [publicationId, setPublicationId] = useState('');
    const [months, setMonths] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        await submitSubscription({ userId, publicationId, months });
        alert('Subscription submitted');
    };

    return (
        <div>
            <h1>Subscribe to a Publication</h1>
            <form onSubmit={handleSubmit}>
                <input type="text" value={userId} onChange={e => setUserId(e.target.value)} placeholder="User ID" required />
                <input type="text" value={publicationId} onChange={e => setPublicationId(e.target.value)} placeholder="Publication ID" required />
                <input type="number" value={months} onChange={e => setMonths(e.target.value)} placeholder="Months" required />
                <button type="submit">Submit Subscription</button>
            </form>
        </div>
    );
}

export default SubscriptionPage;