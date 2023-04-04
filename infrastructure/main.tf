locals {
  key_vault_name          = join("-", [var.product, "aat"])
}

data "azurerm_key_vault" "rd_key_vault" {
  name                = local.key_vault_name
  resource_group_name = local.key_vault_name
}
