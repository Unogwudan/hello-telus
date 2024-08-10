
        # Create VPC Network
        resource "google_compute_network" "my_vpc" {
          name                    = "my-vpc"
          auto_create_subnetworks = false
        }

        # Create Subnetwork
        resource "google_compute_subnetwork" "my_subnet" {
          name          = "my-subnet"
          region        = var.region
          network       = google_compute_network.my_vpc.id
          ip_cidr_range = "10.0.0.0/24"
        }

        # Create Service Account
        resource "google_service_account" "unogwudan_test" {
          account_id    = "unogwudan-test"
          display_name  = "Unogwudan Test Service Account"
          project       = var.project_id
        }

        # Create GKE Cluster (Autopilot Mode)
        resource "google_container_cluster" "autopilot_gke" {
          name     = var.cluster_name
          location = var.region

          # Enable Autopilot Mode by using the `enable_autopilot` argument
          enable_autopilot = true

          network    = google_compute_network.my_vpc.id
          subnetwork = google_compute_subnetwork.my_subnet.id
        }

        # Create Storage Bucket for GCR
        resource "google_storage_bucket" "gcr_bucket" {
          name                         = "${var.project_id}-gcr"
          location                     = var.region
          uniform_bucket_level_access  = true

#           lifecycle {
#             prevent_destroy = true
#           }
        }

        # Bind IAM Roles to User
        resource "google_project_iam_binding" "gke_roles" {
            project = var.project_id
            role    = "roles/container.clusterAdmin"
            members = [
                "serviceAccount:${var.deployment_user_email}"
            ]
        }

        # Bind IAM Role to Node Service Account
        resource "google_project_iam_member" "node_sa_role_binding" {
            project = var.project_id
            role    = "roles/container.nodeServiceAgent"
            member = "serviceAccount:${var.deployment_user_email}"
        }