import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './PublicationList.css';

function PublicationList() {
    const [publications, setPublications] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        fetch('http://localhost:8080/publications')
            .then((res) => res.json())
            .then((data) => setPublications(data))
            .catch((err) => console.error(err));
    }, []);

    const handleSubscribe = (publicationId) => {
        navigate(`/subscribe?publicationId=${publicationId}`);
    };

    return (
        <div className="publications-container">
            <h2>Список Періодичних видань</h2>
            <div className="cards">
                {publications.map((pub) => (
                    <div className="card" key={pub.id}>
                        <div>
                            <h3>{pub.title}</h3>
                            <p>{pub.description}</p>
                            <p><strong>Ціна:</strong> {pub.monthlyPrice} грн/міс</p>
                        </div>
                        <button onClick={() => handleSubscribe(pub.id)}>Оформити передплату</button>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default PublicationList;
