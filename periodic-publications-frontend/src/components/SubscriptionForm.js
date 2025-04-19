import React, { useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import './Form.css';

function SubscriptionForm() {
    const location = useLocation();
    const navigate = useNavigate();
    const queryParams = new URLSearchParams(location.search);
    const publicationIdFromUrl = queryParams.get('publicationId');

    const [userId, setUserId] = useState('');
    const [publicationId, setPublicationId] = useState(publicationIdFromUrl || '');
    const [months, setMonths] = useState(1);
    const [message, setMessage] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();

        const formData = new URLSearchParams();
        formData.append('userId', userId);
        formData.append('publicationId', publicationId);
        formData.append('months', months);

        fetch('http://localhost:8080/subscription', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: formData.toString()
        })
            .then((res) => res.json())
            .then((data) => {
                if (data.subscriptionId) {
                    navigate(`/payment?subscriptionId=${data.subscriptionId}`);
                } else {
                    setMessage('Передплату створено, але не вдалося отримати ID.');
                }
            })
            .catch(() => setMessage('Помилка під час оформлення передплати.'));
    };

    return (
        <div className="form-container">
            <h2>Оформити передплату</h2>
            <form onSubmit={handleSubmit}>
                <label>ID Користувача:
                    <input type="number" value={userId} onChange={(e) => setUserId(e.target.value)} required />
                </label>
                <label>ID Видання:
                    <input type="number" value={publicationId} onChange={(e) => setPublicationId(e.target.value)} required />
                </label>
                <label>Кількість місяців:
                    <input type="number" value={months} onChange={(e) => setMonths(e.target.value)} min="1" required />
                </label>
                <button type="submit">Підписатися</button>
                {message && <p className="message">{message}</p>}
            </form>
        </div>
    );
}

export default SubscriptionForm;
