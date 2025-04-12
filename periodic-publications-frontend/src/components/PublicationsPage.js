import React, { useEffect, useState } from 'react';
import { getPublications } from '../api';
import { Link } from 'react-router-dom';

function PublicationsPage() {
    const [publications, setPublications] = useState([]);

    useEffect(() => {
        getPublications().then(data => setPublications(data));
    }, []);

    return (
        <div>
            <h1>Publications</h1>
            <nav>
                <ul>
                    <li><Link to="/">Home</Link></li>
                    <li><Link to="/subscriptions">Manage Subscriptions</Link></li>
                    <li><Link to="/payment">Payment</Link></li>
                </ul>
            </nav>
            <h2>Available Publications</h2>
            <ul>
                {publications.map(pub => (
                    <li key={pub.id}>
                        <h3>{pub.title}</h3>
                        <p>{pub.description}</p>
                        <p>Price: ${pub.monthlyPrice}</p>
                        <Link to={`/subscriptions?publicationId=${pub.id}`} className="btn">Subscribe</Link>
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default PublicationsPage;