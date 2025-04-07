# EAMS Installation Process

## Prerequisites
- Docker must be installed and running

## Process
- Pull the `compose.yaml` file to the host machine
- Navigate to the folder that contain the `compose.yaml` file
- Composing the docker containers by using `docker-compose up` command
- After running compose, check application by accessing the web at `http://{YOUR_LAN_IP_ADDRESS}:2563/admin/smart-outlet`, the application is running on port 2563