const API_BASE = 'http://localhost:8080';

export async function getPublications() {
    const res = await fetch(`${API_BASE}/admin/publications`);
    return res.json();
}

export async function addPublication(publication) {
    return fetch(`${API_BASE}/admin/publications`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(publication),
    });
}

export async function submitSubscription(subscription) {
    return fetch(`${API_BASE}/subscription`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(subscription),
    });
}

export async function submitPayment(payment) {
    return fetch(`${API_BASE}/payment`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payment),
    });
}