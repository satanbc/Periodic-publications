import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import HomePage from './components/HomePage';
import PublicationList from './components/PublicationList';
import SubscriptionPage from './components/SubscriptionPage';
import PaymentPage from './components/PaymentPage';
import AdminPanel from './components/AdminPanel';

const App = () => {
    return (
        <Router>
            <h1>Система Періодичні видання</h1>
            <Routes>
                <Route path="/" element={<HomePage />} />
                <Route path="/publications" element={<PublicationList />} />
                <Route path="/subscribe" element={<SubscriptionPage />} />
                <Route path="/payment" element={<PaymentPage />} />
                <Route path="/admin" element={<AdminPanel />} />
            </Routes>
        </Router>
    );
};

export default App;
