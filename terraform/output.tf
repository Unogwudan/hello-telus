output "gke_cluster_name" {
  description = "Autopilot GKE cluster."
  value       = google_container_cluster.autopilot_gke.name
}

# Output Bucket Name
output "gcr_bucket_name" {
  value = google_storage_bucket.gcr_bucket.name
}

# Output Cluster Name
output "cluster_name" {
  value = google_container_cluster.autopilot_gke.name
}