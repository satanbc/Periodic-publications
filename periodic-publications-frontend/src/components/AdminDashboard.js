import React, { useState, useEffect } from 'react';

const AdminDashboard = () => {
    const [publications, setPublications] = useState([]);
    const [subscriptions, setSubscriptions] = useState([]);
    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');
    const [monthlyPrice, setMonthlyPrice] = useState('');

    // Fetch data
    useEffect(() => {
        fetch('http://localhost:8080/publications')
            .then(res => res.json())
            .then(data => setPublications(data))
            .catch(err => console.error('Error fetching publications:', err));

        fetch('http://localhost:8080/subscription')
            .then(res => res.json())
            .then(data => setSubscriptions(data))
            .catch(err => console.error('Error fetching subscriptions:', err));
    }, []);

    // Add publication
    const handleAddPublication = () => {
        if (!title || !description || !monthlyPrice) {
            alert('Please fill all fields');
            return;
        }

        const body = new URLSearchParams({
            title,
            description,
            monthlyPrice
        });

        fetch('http://localhost:8080/publications', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body
        })
            .then(() => {
                setTitle('');
                setDescription('');
                setMonthlyPrice('');
                alert('Publication added');
                return fetch('http://localhost:8080/publications');
            })
            .then(res => res.json())
            .then(data => setPublications(data))
            .catch(err => console.error('Error adding publication:', err));
    };

    return (
        <div style={{ padding: '20px' }}>
            <h1>📚 Admin Dashboard</h1>

            <section style={{ marginBottom: '40px' }}>
                <h2>Add New Publication</h2>
                <div>
                    <input
                        type="text"
                        placeholder="Title"
                        value={title}
                        onChange={(e) => setTitle(e.target.value)}
                    />
                </div>
                <div>
                    <textarea
                        placeholder="Description"
                        value={description}
                        onChange={(e) => setDescription(e.target.value)}
                    />
                </div>
                <div>
                    <input
                        type="number"
                        placeholder="Monthly Price (UAH)"
                        value={monthlyPrice}
                        onChange={(e) => setMonthlyPrice(e.target.value)}
                    />
                </div>
                <button onClick={handleAddPublication}>Add Publication</button>
            </section>

            <section style={{ marginBottom: '40px' }}>
                <h2>All Publications</h2>
                {publications.length === 0 ? (
                    <p>No publications found.</p>
                ) : (
                    <table border="1" cellPadding="10">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Title</th>
                            <th>Description</th>
                            <th>Monthly Price</th>
                        </tr>
                        </thead>
                        <tbody>
                        {publications.map((pub) => (
                            <tr key={pub.id}>
                                <td>{pub.id}</td>
                                <td>{pub.title}</td>
                                <td>{pub.description}</td>
                                <td>{pub.monthlyPrice} UAH</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                )}
            </section>

            <section>
                <h2>All Subscriptions</h2>
                {subscriptions.length === 0 ? (
                    <p>No subscriptions found.</p>
                ) : (
                    <table border="1" cellPadding="10">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>User ID</th>
                            <th>Publication ID</th>
                            <th>Months</th>
                            <th>Start</th>
                            <th>End</th>
                            <th>Status</th>
                        </tr>
                        </thead>
                        <tbody>
                        {subscriptions.map((sub) => (
                            <tr key={sub.id}>
                                <td>{sub.id}</td>
                                <td>{sub.userId}</td>
                                <td>{sub.publicationId}</td>
                                <td>{sub.months}</td>
                                <td>{new Date(sub.startDate).toLocaleDateString()}</td>
                                <td>{new Date(sub.endDate).toLocaleDateString()}</td>
                                <td>{sub.active ? 'Active' : 'Inactive'}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                )}
            </section>
        </div>
    );
};

export default AdminDashboard;
