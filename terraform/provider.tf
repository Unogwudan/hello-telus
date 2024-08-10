  terraform {
      required_providers {
        google = {
          source  = "hashicorp/google"
          version = ">= 3.60.0"
        }
      }
      required_version = ">= 0.14"
  }

  provider "google" {
    credentials = file("/home/unogwudan/Documents/IdeaProjects/hello-telus/terraform/unogwudan-test-cbd91fe78e78.json")
    project     = var.project_id
    region      = var.region
  }