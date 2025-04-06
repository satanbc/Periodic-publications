// src/api.js
import axios from 'axios';

const API_URL = 'http://localhost:8080'; // Replace with your backend URL

// Fetch all publications
export const getPublications = () => {
    return axios.get(`${API_URL}/publications`);
};

// Submit subscription
export const submitSubscription = (subscriptionData) => {
    return axios.post(`${API_URL}/subscriptions`, subscriptionData);
};

// Submit payment
export const submitPayment = (paymentData) => {
    return axios.post(`${API_URL}/payments`, paymentData);
};
