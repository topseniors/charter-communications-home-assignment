import React, { useState, useEffect } from 'react';
import CustomerCard from './components/CustomerCard';
import { CustomerRewards } from './types';

function App() {
  const [rewards, setRewards] = useState<CustomerRewards[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    fetch('/api/rewards')
      .then((res) => {
        if (!res.ok) throw new Error(`HTTP ${res.status}`);
        return res.json();
      })
      .then((data: CustomerRewards[]) => {
        setRewards(data);
        setLoading(false);
      })
      .catch((err: Error) => {
        setError(err.message);
        setLoading(false);
      });
  }, []);

  return (
    <div className="app">
      <h1>Customer Rewards Program</h1>
      {loading && <p className="loading">Loading rewards data...</p>}
      {error && <p className="error">Failed to load rewards: {error}</p>}
      {rewards.map((customer) => (
        <CustomerCard key={customer.customerId} customer={customer} />
      ))}
    </div>
  );
}

export default App;
