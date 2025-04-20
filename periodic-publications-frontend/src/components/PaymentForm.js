import React, { useEffect, useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';

const PaymentForm = () => {
    const location = useLocation();
    const navigate = useNavigate();
    const queryParams = new URLSearchParams(location.search);
    const subscriptionId = queryParams.get('subscriptionId');

    const [amount, setAmount] = useState('');
    const [subscription, setSubscription] = useState(null);
    const [error, setError] = useState(false);

    useEffect(() => {
        if (!subscriptionId) {
            setError(true);
            return;
        }

        fetch(`http://localhost:8080/subscription?id=${subscriptionId}`)
            .then(res => {
                if (!res.ok) throw new Error('Failed to fetch subscription');
                return res.json();
            })
            .then(data => {
                setSubscription(data);
                setAmount(data.totalPrice);
            })
            .catch(err => {
                console.error('Error fetching subscription details:', err);
                setError(true);
            });
    }, [subscriptionId]);

    const handlePayment = () => {
        fetch('http://localhost:8080/payment', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: new URLSearchParams({
                subscriptionId,
                amount,
            }),
        })
            .then(() => {
                alert('Payment successful');
                navigate('/');
            })
            .catch((error) => {
                console.error('Error processing payment:', error);
                alert('Failed to process payment');
            });
    };

    if (error) return <p>Error fetching subscription details</p>;
    if (!subscription) return <p>Loading subscription details...</p>;

    return (
        <div>
            <h2>Оплата підписки</h2>
            <p><strong>Видання ID:</strong> {subscription.publicationId}</p>
            <p><strong>Пошта:</strong> {subscription.email}</p>
            <p><strong>Кількість місяців:</strong> {subscription.months}</p>

            <form onSubmit={(e) => e.preventDefault()}>
                <div>
                    <label>Сума (UAH):</label>
                    <input
                        type="number"
                        value={amount}
                        readOnly
                    />
                </div>
                <button type="button" onClick={handlePayment}>
                    Сплатити
                </button>
            </form>
        </div>
    );
};

export default PaymentForm;
