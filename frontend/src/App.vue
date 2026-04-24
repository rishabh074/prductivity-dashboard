<script setup>
import { ref, onMounted } from 'vue'

const workers = ref([])
const workstations = ref([])
const factory = ref({})

const fetchData = async () => {
  const w = await fetch('http://localhost:8080/api/metrics/workers')
  workers.value = await w.json()

  const s = await fetch('http://localhost:8080/api/metrics/workstations')
  workstations.value = await s.json()

  const f = await fetch('http://localhost:8080/api/metrics/factory')
  factory.value = await f.json()
}

onMounted(fetchData)
</script>

<template>
  <div class="container">
    <h1>🏭 Factory Dashboard</h1>

    <div class="cards">
      <div class="card">
        <h3>Active Time</h3>
        <p>{{ factory?.totalActiveTime || 0 }}</p>
      </div>
      <div class="card">
        <h3>Production</h3>
        <p>{{ factory?.totalProduction || 0 }}</p>
      </div>
      <div class="card">
        <h3>Utilization</h3>
        <p>{{ (factory?.avgUtilization || 0).toFixed(2) }}</p>
      </div>
    </div>

    <div class="section">
      <h2>👷 Workers</h2>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Active</th>
            <th>Idle</th>
            <th>Utilization</th>
            <th>Units</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="w in workers" :key="w.workerId">
            <td>{{ w.workerId }}</td>
            <td>{{ w.activeTime }}</td>
            <td>{{ w.idleTime }}</td>
            <td>
              <span class="badge">
                {{ w.utilization.toFixed(2) }}
              </span>
            </td>
            <td>{{ w.unitsProduced }}</td>
          </tr>
        </tbody>
      </table>
    </div>

    <div class="section">
      <h2>🏭 Workstations</h2>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Active</th>
            <th>Utilization</th>
            <th>Units</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="s in workstations" :key="s.workstationId">
            <td>{{ s.workstationId }}</td>
            <td>{{ s.activeTime }}</td>
            <td>
              <span class="badge">
                {{ s.utilization.toFixed(2) }}
              </span>
            </td>
            <td>{{ s.totalUnits }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<style>
body {
  margin: 0;
  background: #0f172a;
  color: #e2e8f0;
  font-family: Arial, sans-serif;
}

.container {
  padding: 30px;
}

h1 {
  text-align: center;
  margin-bottom: 30px;
}

.cards {
  display: flex;
  gap: 20px;
  margin-bottom: 30px;
}

.card {
  flex: 1;
  background: #1e293b;
  padding: 20px;
  border-radius: 10px;
  text-align: center;
  box-shadow: 0 4px 10px rgba(0,0,0,0.3);
}

.card h3 {
  margin-bottom: 10px;
  color: #94a3b8;
}

.card p {
  font-size: 22px;
  font-weight: bold;
}

.section {
  margin-top: 30px;
}

table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 10px;
  background: #1e293b;
  border-radius: 10px;
  overflow: hidden;
}

th, td {
  padding: 12px;
  text-align: center;
}

th {
  background: #334155;
}

tr:nth-child(even) {
  background: #0f172a;
}

.badge {
  background: #22c55e;
  padding: 5px 10px;
  border-radius: 6px;
  font-weight: bold;
}
</style>