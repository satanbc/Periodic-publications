import { useEffect, useState } from 'react';
import { getPublications } from '../api';
import { Link } from 'react-router-dom';

export default function Home() {
    const [publications, setPublications] = useState([]);

    useEffect(() => {
        getPublications().then(setPublications);
    }, []);

    return (
        <div>
            <h1 className="text-2xl font-bold">Available Publications</h1>
            <ul className="mt-4 space-y-4">
                {publications.map((pub) => (
                    <li key={pub.id} className="border p-4 rounded-lg shadow">
                        <h3 className="text-lg font-semibold">{pub.title}</h3>
                        <p>{pub.description}</p>
                        <p className="text-sm">Price: ${pub.monthlyPrice}</p>
                        <Link
                            to={`/subscribe?publicationId=${pub.id}`}
                            className="text-blue-600 hover:underline"
                        >
                            Subscribe
                        </Link>
                    </li>
                ))}
            </ul>
        </div>
    );
}
