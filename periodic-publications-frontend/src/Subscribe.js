import React, { useState, useEffect } from 'react';
import { useParams, useHistory } from 'react-router-dom';
import { submitSubscription } from './api';

const Subscribe = () => {
    const { publicationId } = useParams();
    const history = useHistory();
    const [months, setMonths] = useState(1);
    const [userId, setUserId] = useState(1); // Припустимо, що ID користувача 1 для тесту

    const handleSubmit = async (event) => {
        event.preventDefault();
        await submitSubscription(userId, publicationId, months);
        history.push('/subscriptions');
    };

    return (
        <div>
            <h2>Subscribe to Publication</h2>
            <form onSubmit={handleSubmit}>
                <label>
                    Duration (months):
                    <input
                        type="number"
                        value={months}
                        onChange={(e) => setMonths(e.target.value)}
                        min="1"
                    />
                </label>
                <button type="submit" className="btn">Subscribe</button>
            </form>
        </div>
    );
};

export default Subscribe;
