import React, { useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { useAuth0 } from '@auth0/auth0-react';
import './Form.css';

function SubscriptionForm() {
    const location = useLocation();
    const navigate = useNavigate();
    const { user, isAuthenticated } = useAuth0();

    const queryParams = new URLSearchParams(location.search);
    const publicationIdFromUrl = queryParams.get('publicationId');

    const [publicationId, setPublicationId] = useState(publicationIdFromUrl || '');
    const [months, setMonths] = useState(1);
    const [message, setMessage] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();

        if (!isAuthenticated || !user?.email) {
            setMessage("Ви повинні бути авторизовані.");
            return;
        }

        const formData = new URLSearchParams();
        formData.append('email', user.email);
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
