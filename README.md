# 🏭 AI-Powered Worker Productivity Dashboard

## 🚀 Overview

This project is a full-stack web application that ingests AI-generated CCTV events from a manufacturing factory and computes real-time productivity metrics.

The system tracks worker activity (working, idle, production) and provides insights at:

* Worker level
* Workstation level
* Factory level

---

## 🧱 Architecture

```
CCTV AI System → Spring Boot Backend → Database (H2) → Metrics Service → Vue Dashboard
```

* AI cameras send structured event data
* Backend APIs ingest and store events
* Metrics are computed dynamically in the service layer
* Frontend dashboard displays productivity insights

---

## ⚙️ Tech Stack

### Backend

* Java + Spring Boot
* Spring Data JPA
* H2 Database

### Frontend

* Vue 3 (Vite)

---

## 🗄️ Database Schema

### Worker

* `worker_id`
* `name`

### Workstation

* `station_id`
* `name`

### Event

* `timestamp`
* `worker_id`
* `workstation_id`
* `event_type` (working, idle, product_count)
* `count`

---

## 📊 Metrics Definition

### Worker-Level Metrics

* **Active Time** → Time spent in `working` state
* **Idle Time** → Time spent in `idle` state
* **Utilization** → `active / (active + idle)`
* **Units Produced** → Sum of `product_count` events

### Workstation-Level Metrics

* Active time and utilization based on assigned events
* Total units produced

### Factory-Level Metrics

* Total active time (sum of all workers)
* Total production
* Average utilization across workers

---

## ⏱️ Time Calculation Logic

Events do not store duration.

👉 Duration is calculated as:

> Difference between consecutive timestamps

Example:

* 10:00 → working
* 10:30 → idle

→ Working time = 30 minutes

---

## ⚠️ Assumptions

* Events are sorted by timestamp before processing
* Last event duration is ignored (no next event)
* Product events are independent of time events
* Confidence field is not used in metrics

---

## 🔧 Edge Case Handling

### Intermittent Connectivity

* System accepts delayed events
* Metrics are recomputed dynamically

### Duplicate Events

* Can be handled using a unique key:

  ```
  worker_id + timestamp + event_type
  ```

### Out-of-Order Events

* Events are sorted before processing

---

## 📈 Scalability

### From 5 → 100+ Cameras

* Introduce message queue (Kafka / RabbitMQ)
* Decouple ingestion from processing

### Multi-site Deployment

* Add `factory_id`
* Use multi-tenant database architecture

---

## 🤖 AI/ML Considerations

### Model Versioning

* Add `model_version` field in events

### Model Drift Detection

* Monitor:

  * Drop in confidence
  * Sudden changes in activity patterns

### Retraining

* Trigger when model accuracy degrades

---

## ▶️ How to Run

### 1. Start Backend

```
cd backend
mvn spring-boot:run
```

### 2. Seed Data

Use Postman or curl:

```
POST http://localhost:8080/api/seed
```

### 3. Start Frontend

```
cd frontend
npm install
npm run dev
```

Open:

```
http://localhost:5173
```

---

## 📡 API Endpoints

### Ingest Event

```
POST /api/events
```

### Seed Data

```
POST /api/seed
```

### Metrics

```
GET /api/metrics/workers
GET /api/metrics/workstations
GET /api/metrics/factory
```

---

## 🎯 Features

* Real-time productivity tracking
* Worker & workstation insights
* Factory-level analytics
* Clean and responsive dashboard UI

---

## 📸 Screenshots

### 🏭 Dashboard Overview

![Dashboard](./screenshots/dashboard.png)

---

### 👷 Worker Metrics

![Workers](./screenshots/workers.png)

---

### 🏭 Workstation Metrics

![Workstations](./screenshots/workstations.png)


## 🏁 Conclusion

This system demonstrates a scalable architecture for monitoring worker productivity using AI-generated events, with a strong focus on backend metric computation and clean data flow.
