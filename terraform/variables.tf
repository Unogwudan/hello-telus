variable "project_id" {
  description = "The GCP project ID."
  type        = string
  default = "unogwudan-test"
}

variable "region" {
  description = "The GCP region for the GKE cluster."
  type        = string
  default     = "us-central1" # You can specify a default value or set it elsewhere
}

variable "node_service_account" {
  description = "The service account to attach to nodes."
  type        = string
  default = "serviceAccount:gke-node-service-account@unogwudan-test.iam.gserviceaccount.com"
}

variable "cluster_name" {
  description = "The name of the GKE cluster."
  type        = string
  default     = "autopilot-gke-cluster"
}

variable "deployment_user_email" {
  description = "The email of the user who will deploy to GKE."
  type        = string
  default = "gke-node-service-account@unogwudan-test.iam.gserviceaccount.com"
}

