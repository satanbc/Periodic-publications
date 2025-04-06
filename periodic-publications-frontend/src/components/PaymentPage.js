// src/components/PaymentPage.js
import React, { useState } from 'react';
import { submitPayment } from '../api';

const PaymentPage = () => {
    const [paymentDetails, setPaymentDetails] = useState('');

    const handlePaymentSubmit = (e) => {
        e.preventDefault();
        const paymentData = { paymentDetails };
        submitPayment(paymentData)
            .then(response => alert('Payment successful!'))
            .catch(error => console.error('Error submitting payment:', error));
    };

    return (
        <div>
            <h2>Make a Payment</h2>
            <form onSubmit={handlePaymentSubmit}>
                <label>
                    Payment Details:
                    <input
                        type="text"
                        value={paymentDetails}
                        onChange={(e) => setPaymentDetails(e.target.value)}
                        required
                    />
                </label>
                <br />
                <button type="submit">Pay</button>
            </form>
        </div>
    );
};

export default PaymentPage;
