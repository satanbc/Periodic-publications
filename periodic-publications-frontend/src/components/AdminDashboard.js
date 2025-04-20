import React, { useState, useEffect } from 'react';

const AdminDashboard = () => {
    const [publications, setPublications] = useState([]);
    const [subscriptions, setSubscriptions] = useState([]);
    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');
    const [monthlyPrice, setMonthlyPrice] = useState('');

    useEffect(() => {
        fetch('http://localhost:8080/publications')
            .then(res => res.json())
            .then(data => setPublications(data))
            .catch(err => console.error('Помилка при отриманні публікацій:', err));

        fetch('http://localhost:8080/subscription')
            .then(res => res.json())
            .then(data => setSubscriptions(data))
            .catch(err => console.error('Помилка при отриманні підписок:', err));
    }, []);

    const handleAddPublication = () => {
        if (!title || !description || !monthlyPrice) {
            alert('Будь ласка, заповніть усі поля');
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
                alert('Публікацію додано');
                return fetch('http://localhost:8080/publications');
            })
            .then(res => res.json())
            .then(data => setPublications(data))
            .catch(err => console.error('Помилка при додаванні публікації:', err));
    };

    return (
        <div style={{ padding: '20px' }}>
            <h1>📚 Панель адміністратора</h1>

            <section style={{ marginBottom: '40px' }}>
                <h2>Додати нову публікацію</h2>
                <div>
                    <input
                        type="text"
                        placeholder="Назва"
                        value={title}
                        onChange={(e) => setTitle(e.target.value)}
                    />
                </div>
                <div>
                    <textarea
                        placeholder="Опис"
                        value={description}
                        onChange={(e) => setDescription(e.target.value)}
                    />
                </div>
                <div>
                    <input
                        type="number"
                        placeholder="Місячна ціна (грн)"
                        value={monthlyPrice}
                        onChange={(e) => setMonthlyPrice(e.target.value)}
                    />
                </div>
                <button onClick={handleAddPublication}>Додати публікацію</button>
            </section>

            <section style={{ marginBottom: '40px' }}>
                <h2>Всі публікації</h2>
                {publications.length === 0 ? (
                    <p>Публікацій не знайдено.</p>
                ) : (
                    <table border="1" cellPadding="10">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Назва</th>
                            <th>Опис</th>
                            <th>Місячна ціна</th>
                        </tr>
                        </thead>
                        <tbody>
                        {publications.map((pub) => (
                            <tr key={pub.id}>
                                <td>{pub.id}</td>
                                <td>{pub.title}</td>
                                <td>{pub.description}</td>
                                <td>{pub.monthlyPrice} грн</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                )}
            </section>

            <section>
                <h2>Всі підписки</h2>
                {subscriptions.length === 0 ? (
                    <p>Підписок не знайдено.</p>
                ) : (
                    <table border="1" cellPadding="10">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Email користувача</th>
                            <th>ID публікації</th>
                            <th>Місяців</th>
                            <th>Початок</th>
                            <th>Кінець</th>
                            <th>Статус</th>
                            <th>Загальна сума</th>
                        </tr>
                        </thead>
                        <tbody>
                        {subscriptions.map((sub) => (
                            <tr key={sub.id}>
                                <td>{sub.id}</td>
                                <td>{sub.email}</td>
                                <td>{sub.publicationId}</td>
                                <td>{sub.months}</td>
                                <td>{new Date(sub.startDate).toLocaleDateString()}</td>
                                <td>{new Date(sub.endDate).toLocaleDateString()}</td>
                                <td>{sub.active ? 'Активна' : 'Неактивна'}</td>
                                <td>{sub.totalPrice + " грн"}</td>
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
