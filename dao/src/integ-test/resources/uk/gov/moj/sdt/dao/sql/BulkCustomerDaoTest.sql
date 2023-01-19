insert into BULK_CUSTOMERS (BULK_CUSTOMER_ID, SDT_CUSTOMER_ID, VERSION_NUMBER) values (10711, 2, 0);

insert into TARGET_APPLICATIONS (TARGET_APPLICATION_ID, TARGET_APPLICATION_CODE, TARGET_APPLICATION_NAME, VERSION_NUMBER) values (1, '1104', '2', 0);

insert into SERVICE_TYPES (SERVICE_TYPE_ID, SERVICE_TYPE_NAME, SERVICE_TYPE_STATUS, SERVICE_TYPE_DESCRIPTION, VERSION_NUMBER)
values (1, '11043', '2', '0', 0);

insert into SERVICE_ROUTINGS (SERVICE_ROUTINGS_ID, SERVICE_TYPE_ID, TARGET_APPLICATION_ID, WEB_SERVICE_ENDPOINT, VERSION_NUMBER) values (1, 1, 1, '2', 0);

insert into BULK_CUSTOMER_APPLICATIONS (BULK_CUSTOMER_APPLICATIONS_ID, BULK_CUSTOMER_ID, TARGET_APPLICATION_ID, CUSTOMER_APPLICATION_ID, VERSION_NUMBER)
values (1, 10711, 1, 1, 0);

