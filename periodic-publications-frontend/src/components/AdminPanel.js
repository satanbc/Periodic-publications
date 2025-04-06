// src/components/AdminPanel.js
import React, { useState } from 'react';

const AdminPanel = () => {
    const [newPublication, setNewPublication] = useState({ name: '', description: '', price: '' });

    const handleAddPublication = (e) => {
        e.preventDefault();
        // Add logic to submit the new publication (e.g., API call)
        console.log('Adding new publication:', newPublication);
        setNewPublication({ name: '', description: '', price: '' });
    };

    return (
        <div>
            <h2>Admin Panel</h2>
            <form onSubmit={handleAddPublication}>
                <label>
                    Name:
                    <input
                        type="text"
                        value={newPublication.name}
                        onChange={(e) => setNewPublication({ ...newPublication, name: e.target.value })}
                        required
                    />
                </label>
                <br />
                <label>
                    Description:
                    <input
                        type="text"
                        value={newPublication.description}
                        onChange={(e) => setNewPublication({ ...newPublication, description: e.target.value })}
                        required
                    />
                </label>
                <br />
                <label>
                    Price:
                    <input
                        type="number"
                        value={newPublication.price}
                        onChange={(e) => setNewPublication({ ...newPublication, price: e.target.value })}
                        required
                    />
                </label>
                <br />
                <button type="submit">Add Publication</button>
            </form>
        </div>
    );
};

export default AdminPanel;
