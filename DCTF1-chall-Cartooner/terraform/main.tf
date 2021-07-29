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
        environment_variables = {
          "PWN_URI" = "mysql://siteadmin:pwnuserpass123fd01602018b08e149a355d23cae54541@dctf1-chall-cartooner.westeurope.azurecontainer.io:3306/cartoonerdb",
          "PWN_JWT_SECRET" = "rachel",
          "PORT" = "9999"
          }
        cpu    = "2"
        memory = "2"

        ports {
            port = 9999
            protocol = "TCP"
        }
      
    }
    container {
      name   = "mysql"
      image  = "ghcr.io/dragonsecsi/cartoonerdb"
      cpu    = "2"
      memory = "2"
      ports {
          port = 3306
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
