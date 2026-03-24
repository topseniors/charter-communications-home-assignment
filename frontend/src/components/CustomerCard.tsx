import React, { useState } from 'react';
import { CustomerRewards } from '../types';

const MONTH_NAMES: Record<string, string> = {
  '01': 'January', '02': 'February', '03': 'March',
  '04': 'April', '05': 'May', '06': 'June',
  '07': 'July', '08': 'August', '09': 'September',
  '10': 'October', '11': 'November', '12': 'December',
};

function formatMonth(key: string): string {
  const [year, month] = key.split('-');
  return `${MONTH_NAMES[month]} ${year}`;
}

interface CustomerCardProps {
  customer: CustomerRewards;
}

function CustomerCard({ customer }: CustomerCardProps) {
  const [expanded, setExpanded] = useState<boolean>(true);
  const { customerId, monthlyPoints, totalPoints } = customer;

  return (
    <div className="customer-card">
      <div className="customer-header" onClick={() => setExpanded(!expanded)}>
        <h2>{customerId}</h2>
        <span className="total-badge">{totalPoints} pts total</span>
      </div>
      {expanded && (
        <div className="customer-details">
          {Object.entries(monthlyPoints).map(([month, points]) => (
            <div key={month} className="month-row">
              <span className="month-name">{formatMonth(month)}</span>
              <span className="month-points">{points} pts</span>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

export default CustomerCard;
