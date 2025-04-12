import React, { useState } from 'react';
import { addPublication } from '../api';

function AdminPage() {
    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');
    const [monthlyPrice, setMonthlyPrice] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        await addPublication({ title, description, monthlyPrice });
        alert('Publication added');
    };

    return (
        <div>
            <h1>Admin - Add Publication</h1>
            <form onSubmit={handleSubmit}>
                <input type="text" value={title} onChange={e => setTitle(e.target.value)} placeholder="Title" required />
                <input type="text" value={description} onChange={e => setDescription(e.target.value)} placeholder="Description" required />
                <input type="number" value={monthlyPrice} onChange={e => setMonthlyPrice(e.target.value)} placeholder="Monthly Price" required />
                <button type="submit">Add Publication</button>
            </form>
        </div>
    );
}

export default AdminPage;