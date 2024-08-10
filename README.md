## Project Overview
This Spring Boot project integrates with Google Pub/Sub to provide messaging capabilities. It offers the following key functionalities:

**Message Publishing:** A Rest API endpoint that allows clients to publish messages to a specified Pub/Sub topic.

**Message Subscription:** A service is implemented to read messages from the Pub/Sub subscription and process them accordingly.

**NOTE:** A test script is automatically ran upon successful deployment to publish some messages to be topic for testing.

## Key Technologies
**Spring Boot:** Used for building and running the application.

**Google Cloud Pub/Sub:** For message queuing and delivery.

**Terraform:** For provisioning the Google Cloud infrastructure.

**Google Kubernetes Engine (GKE):** Managed Kubernetes service for deploying containerized applications.

**Google Container Registry (GCR):** Service to store and manage Docker container images.

**Google Cloud Storage:** Object storage service for storing and accessing data on Google Cloud.

**Google Cloud Build:** CI/CD service for building, and deploying applications.


# Terraform GCP Infrastructure Deployment Guide


The Terraform configuration deploys a comprehensive set of Google Cloud Platform (GCP) resources designed for robust and scalable operations. The setup includes:

1. **Virtual Private Cloud (VPC) Network** - A custom VPC to segment network traffic.
2. **Subnetwork** - A subnetwork within the VPC with a custom IP CIDR range.
3. **Service Account** - A dedicated service account for managing the infrastructure.
4. **Google Kubernetes Engine (GKE) Cluster** - A GKE cluster in Autopilot mode for simplified Kubernetes management.
5. **Google Cloud Storage Bucket** - A storage bucket for housing Google Container Registry (GCR) container images.
6. **IAM Role Bindings** - IAM roles to authorize user and node service accounts.


## Terraform Setup

## Prerequisites

**Terraform:** Ensure Terraform is installed.

**Google Cloud SDK:** Install the Google Cloud SDK and authenticate with Google Cloud account.

**Service Account JSON Key:** Obtain the JSON key for the service account with necessary permissions.


## Initializing and Running Terraform

Run the following command to initialize the provider plugins:

    terraform init


Preview the changes Terraform will make by running:

    terraform plan

Apply the changes to your infrastructure:

    terraform apply

Output Results:

After Terraform completes, you will see the outputs defined in outputs.tf, such as the GKE cluster name and service account email.

# Automated Build and Deployment

## The project is configured to automate the build and deployment process:

**CI/CD Pipeline:** The CI/CD pipeline is implemented using Google Cloud Build to automate builds and deployments.

**Docker:** The application is containerized using Docker, and the image is pushed to a Google Container Registry (GCR).

**Kubernetes Deployment:** The Terraform configuration includes deployment specifications that automatically deploy the Docker container to the GKE cluster.
