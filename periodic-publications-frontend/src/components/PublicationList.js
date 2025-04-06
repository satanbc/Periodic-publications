// src/components/PublicationList.js
import React, { useState, useEffect } from 'react';
import { getPublications } from '../api';

const PublicationList = () => {
    const [publications, setPublications] = useState([]);

    useEffect(() => {
        getPublications()
            .then(response => setPublications(response.data))
            .catch(error => console.error('Error fetching publications:', error));
    }, []);

    return (
        <div>
            <h2>List of Publications</h2>
            <ul>
                {publications.map(pub => (
                    <li key={pub.id}>
                        <h3>{pub.name}</h3>
                        <p>{pub.description}</p>
                        <p>Price: ${pub.price}</p>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default PublicationList;
