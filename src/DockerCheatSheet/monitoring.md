prometheus.yml

global:
scrape_interval: 15s

scrape_configs:
- job_name: 'docker'
  static_configs:
    - targets: ['docker.for.mac.localhost:9323']

docker-compose.yml
version: '3.8'

services:
prometheus:
image: prom/prometheus:latest
volumes:
- prometheus_data:/prometheus
- ./prometheus.yml:/etc/prometheus/prometheus.yml
ports:
- "9090:9090"

grafana:
image: grafana/grafana:latest
volumes:
- grafana_data:/var/lib/grafana
ports:
- "3000:3000"
depends_on:
- prometheus

volumes:
prometheus_data:
grafana_data: