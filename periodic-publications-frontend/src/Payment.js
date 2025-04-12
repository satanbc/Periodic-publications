import React, { useState, useEffect } from 'react';
import { useParams, useHistory } from 'react-router-dom';
import { processPayment } from './api';

const Payment = () => {
    const { subscriptionId } = useParams();
    const history = useHistory();
    const [amount, setAmount] = useState(0);

    useEffect(() => {
        // Отримати дані підписки для визначення суми
        // Цей код припускає, що ви вже маєте API для отримання даних підписки
        // setAmount(subscriptionAmount);
    }, [subscriptionId]);

    const handlePayment = async (event) => {
        event.preventDefault();
        await processPayment(subscriptionId, amount);
        history.push('/subscriptions'); // Переходить на сторінку підписок після успішної оплати
    };

    return (
        <div>
            <h2>Payment</h2>
            <form onSubmit={handlePayment}>
                <label>
                    Amount to Pay: $
                    <input
                        type="number"
                        value={amount}
                        onChange={(e) => setAmount(e.target.value)}
                        min="0"
                        step="0.01"
                    />
                </label>
                <button type="submit" className="btn">Pay Now</button>
            </form>
        </div>
    );
};

export default Payment;
