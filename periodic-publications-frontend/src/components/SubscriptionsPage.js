import React, { useEffect, useState } from 'react';
import { useAuth0 } from '@auth0/auth0-react';
import { useNavigate } from 'react-router-dom';

const SubscriptionsPage = () => {
    const { user, isAuthenticated } = useAuth0();
    const [subscriptions, setSubscriptions] = useState([]);
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        if (!isAuthenticated) return;

        const email = user?.email;

        fetch(`http://localhost:8080/subscription?email=${email}`)
            .then((res) => {
                if (!res.ok) throw new Error('Failed to fetch subscriptions');
                return res.json();
            })
            .then((data) => {
                setSubscriptions(data);
            })
            .catch((err) => {
                console.error('Error fetching subscriptions:', err);
                setError('Failed to fetch subscriptions');
            });
    }, [isAuthenticated, user]);

    const handlePayment = (subscriptionId) => {
        navigate(`/payment?subscriptionId=${subscriptionId}`);
    };

    if (!isAuthenticated) {
        return <p>Please log in to view your subscriptions.</p>;
    }

    if (error) {
        return <p>{error}</p>;
    }

    return (
        <div>
            <h2>Мої підписки</h2>
            {subscriptions.length === 0 ? (
                <p>У вас немає активних підписок.</p>
            ) : (
                <table style={{ width: '100%', borderCollapse: 'collapse', marginTop: '20px' }}>
                    <thead>
                    <tr>
                        <th style={{ border: '1px solid #ddd', padding: '8px', textAlign: 'left' }}>ID Видання</th>
                        <th style={{ border: '1px solid #ddd', padding: '8px', textAlign: 'left' }}>Дата початку</th>
                        <th style={{ border: '1px solid #ddd', padding: '8px', textAlign: 'left' }}>Дата кінця</th>
                        <th style={{ border: '1px solid #ddd', padding: '8px', textAlign: 'left' }}>Статус</th>
                        <th style={{ border: '1px solid #ddd', padding: '8px', textAlign: 'left' }}>Сума (UAH)</th>
                        <th style={{ border: '1px solid #ddd', padding: '8px', textAlign: 'left' }}></th>
                    </tr>
                    </thead>
                    <tbody>
                    {subscriptions.map((subscription) => (
                        <tr key={subscription.id}>
                            <td style={{ border: '1px solid #ddd', padding: '8px' }}>{subscription.publicationId}</td>
                            <td style={{ border: '1px solid #ddd', padding: '8px' }}>{subscription.startDate}</td>
                            <td style={{ border: '1px solid #ddd', padding: '8px' }}>{subscription.endDate}</td>
                            <td style={{ border: '1px solid #ddd', padding: '8px' }}>
                                {subscription.active ? 'Активна' : 'Неактивна'}
                            </td>
                            <td style={{ border: '1px solid #ddd', padding: '8px' }}>
                                {subscription.totalPrice} UAH
                            </td>
                            <td style={{ border: '1px solid #ddd', padding: '8px' }}>
                                {!subscription.active && (
                                    <button onClick={() => handlePayment(subscription.id)}>Оплатити</button>
                                )}
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            )}
        </div>
    );
};

export default SubscriptionsPage;
