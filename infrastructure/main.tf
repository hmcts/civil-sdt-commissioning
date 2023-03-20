provider "azurerm" {
  features {}
}
locals {
  key_vault_name          = join("-", [var.product, "aat"])
  s2s_key_vault_name        = join("-", ["s2s", var.env])
  s2s_vault_resource_group  = join("-", ["rpe-service-auth-provider", var.env])
}

module "db-sdt-commissioning-v11" {
  source             = "git@github.com:hmcts/cnp-module-postgres?ref=master"
  product            = var.product
  component          = var.component
  name               = join("-", [var.product, var.component, "postgres-db", "v11"])
  location           = var.location
  subscription       = var.subscription
  env                = var.env
  postgresql_user    = "sdtcomissioning"
  database_name      = "sdtcomissioning"
  common_tags        = var.common_tags
  postgresql_version = "11"
}

data "azurerm_key_vault" "rd_key_vault" {
  name                = local.key_vault_name
  resource_group_name = local.key_vault_name
}

data "azurerm_key_vault" "s2s_key_vault" {
  name                = local.s2s_key_vault_name
  resource_group_name = local.s2s_vault_resource_group
}

data "azurerm_key_vault_secret" "s2s_secret" {
  name          = "microservicekey-civil-sdt-commissioning"
  key_vault_id  = data.azurerm_key_vault.s2s_key_vault.id
}

resource "azurerm_key_vault_secret" "sdt_commissioning_s2s_secret" {
  name          = "civil-sdt-commissioning-s2s-secret"
  value         = data.azurerm_key_vault_secret.s2s_secret.value
  key_vault_id  = data.azurerm_key_vault.rd_key_vault.id
}

resource "azurerm_key_vault_secret" "POSTGRES-USER" {
  name          = join("-", [var.component, "POSTGRES-USER"])
  value         = module.db-sdt-commissioning-v11.user_name
  key_vault_id  = data.azurerm_key_vault.rd_key_vault.id
}

resource "azurerm_key_vault_secret" "POSTGRES-PASS" {
  name          = join("-", [var.component, "POSTGRES-PASS"])
  value         = module.db-sdt-commissioning-v11.postgresql_password
  key_vault_id  = data.azurerm_key_vault.rd_key_vault.id
}

resource "azurerm_key_vault_secret" "POSTGRES_HOST" {
  name          = join("-", [var.component, "POSTGRES-HOST"])
  value         = module.db-sdt-commissioning-v11.host_name
  key_vault_id  = data.azurerm_key_vault.rd_key_vault.id
}

resource "azurerm_key_vault_secret" "POSTGRES_DATABASE" {
  name          = join("-", [var.component, "POSTGRES-DATABASE"])
  value         = module.db-sdt-commissioning-v11.postgresql_database
  key_vault_id  = data.azurerm_key_vault.rd_key_vault.id
}
