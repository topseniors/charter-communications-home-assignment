# Customer Rewards Program

A Spring Boot REST API with a React (TypeScript) frontend that calculates reward points for customers based on their purchase transactions.

## Rewards Logic

- **2 points** per dollar spent over $100 per transaction
- **1 point** per dollar spent between $50 and $100 per transaction
- Example: $120 purchase = (2 x $20) + (1 x $50) = **90 points**

## Tech Stack

- **Backend:** Spring Boot 3.2, Spring Data JPA, H2 (in-memory database)
- **Frontend:** React 18, TypeScript, Vite

## Prerequisites

- Java 17+
- Node.js 18+
- Maven (or use a locally installed copy)

## Running the Application

### Backend

```bash
mvn spring-boot:run
```

The API starts at `http://localhost:8080`.

### Frontend

```bash
cd frontend
npm install
npm run dev
```

The dev server starts at `http://localhost:3000` and proxies API requests to the backend.

## API Endpoints

| Method | URL | Description |
|--------|-----|-------------|
| GET | `/api/rewards` | Rewards for all customers (monthly + total) |
| GET | `/api/rewards/{customerId}` | Rewards for a single customer |

### Sample Response

```json
{
  "customerId": "customer-A",
  "monthlyPoints": {
    "2026-01": 115,
    "2026-02": 250,
    "2026-03": 150
  },
  "totalPoints": 515
}
```

## Running Tests

```bash
mvn test
```

## Project Structure

```
├── pom.xml
├── src/main/java/com/charter/rewards/
│   ├── RewardsApplication.java
│   ├── config/WebConfig.java
│   ├── controller/RewardsController.java
│   ├── dto/CustomerRewards.java
│   ├── model/Transaction.java
│   ├── repository/TransactionRepository.java
│   └── service/RewardsService.java
├── src/main/resources/
│   ├── application.properties
│   └── data.sql
├── src/test/java/com/charter/rewards/
│   ├── RewardsControllerTest.java
│   └── RewardsServiceTest.java
└── frontend/
    ├── package.json
    ├── tsconfig.json
    ├── vite.config.js
    └── src/
        ├── main.tsx
        ├── App.tsx
        ├── index.css
        ├── types.ts
        └── components/
            └── CustomerCard.tsx
```
