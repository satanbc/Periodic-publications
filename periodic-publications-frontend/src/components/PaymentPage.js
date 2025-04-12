import React, { useState } from 'react';
import { submitPayment } from '../api';

function PaymentPage() {
    const [subscriptionId, setSubscriptionId] = useState('');
    const [amount, setAmount] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        await submitPayment({ subscriptionId, amount });
        alert('Payment submitted');
    };

    return (
        <div>
            <h1>Payment</h1>
            <form onSubmit={handleSubmit}>
                <input type="text" value={subscriptionId} onChange={e => setSubscriptionId(e.target.value)} placeholder="Subscription ID" required />
                <input type="number" value={amount} onChange={e => setAmount(e.target.value)} placeholder="Amount" required />
                <button type="submit">Submit Payment</button>
            </form>
        </div>
    );
}

export default PaymentPage;