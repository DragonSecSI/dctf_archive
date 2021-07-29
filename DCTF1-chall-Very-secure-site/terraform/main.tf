provider "azurerm" {
  features {}
}

resource "azurerm_storage_account" "example" {
  name                     = var.storage_account
  resource_group_name      = azurerm_resource_group.example.name
  location                 = azurerm_resource_group.example.location
  account_tier             = "Standard"
  account_replication_type = "LRS"

  tags = {
    environment = "staging"
  }
}

resource "azurerm_storage_container" "example" {
  name                  = var.storage_container
  storage_account_name  = azurerm_storage_account.example.name
  container_access_type = "private"
}

resource "azurerm_resource_group" "example" {
  name     = var.resource_group_name
  location = "West Europe"
}

resource "azurerm_container_group" "example" {
  name                = var.container_group_name
  location            = azurerm_resource_group.example.location
  resource_group_name = azurerm_resource_group.example.name
  ip_address_type     = "public"
  dns_name_label      = var.container_group_name
  os_type             = "Linux"

    container {
        name   = var.container_name
        image  = var.container_image
        cpu    = "4"
        memory = "4"

        ports {
            port = 80
            protocol = "TCP"
        }
      
    }
  image_registry_credential {
    username = "dsecid"
    password = var.registry_token
    server = "ghcr.io"
  }
  tags = {
    environment = "testing"
  }
}
