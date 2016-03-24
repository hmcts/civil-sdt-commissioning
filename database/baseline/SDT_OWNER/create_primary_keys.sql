
  ALTER TABLE "SDT_OWNER"."BULK_CUSTOMERS" ADD CONSTRAINT "BULK_CUSTOMERS_PK" PRIMARY KEY ("BULK_CUSTOMER_ID") using index SDT_OWNER.BULK_CUSTOMERS_PK ENABLE;



  ALTER TABLE "SDT_OWNER"."BULK_CUSTOMER_APPLICATIONS" ADD CONSTRAINT "BULK_CUSTOMER_APPLICATIONS_PK" PRIMARY KEY ("BULK_CUSTOMER_APPLICATIONS_ID") using index SDT_OWNER.BULK_CUSTOMER_APPLICATIONS_PK ENABLE;



  ALTER TABLE "SDT_OWNER"."BULK_SUBMISSIONS" ADD CONSTRAINT "BULK_SUBMISSIONS_PK" PRIMARY KEY ("BULK_SUBMISSION_ID") using index SDT_OWNER.BULK_SUBMISSIONS_PK ENABLE;



  ALTER TABLE "SDT_OWNER"."ERROR_LOGS" ADD CONSTRAINT "ERROR_LOGS_PK" PRIMARY KEY ("ERROR_LOG_ID") using index SDT_OWNER.ERROR_LOGS_PK ENABLE;



  ALTER TABLE "SDT_OWNER"."ERROR_MESSAGES" ADD CONSTRAINT "ERROR_MESSAGES_PK" PRIMARY KEY ("ERROR_MESSAGE_ID") using index SDT_OWNER.ERROR_MESSAGES_PK ENABLE;



  ALTER TABLE "SDT_OWNER"."GLOBAL_PARAMETERS" ADD CONSTRAINT "GLOBAL_PARAMETERS_PK" PRIMARY KEY ("GLOBAL_PARAMETER_ID") using index SDT_OWNER.GLOBAL_PARAMETERS_PK ENABLE;



  ALTER TABLE "SDT_OWNER"."INDIVIDUAL_REQUESTS" ADD CONSTRAINT "INDIVIDUAL_REQUESTS_PK" PRIMARY KEY ("INDIVIDUAL_REQUEST_ID") using index SDT_OWNER.INDIVIDUAL_REQUESTS_PK ENABLE;



  ALTER TABLE "SDT_OWNER"."SERVICE_REQUESTS" ADD CONSTRAINT "SERVICE_REQUESTS_PK" PRIMARY KEY ("SERVICE_REQUEST_ID") using index SDT_OWNER.SERVICE_REQUESTS_PK ENABLE;



  ALTER TABLE "SDT_OWNER"."SERVICE_ROUTINGS" ADD CONSTRAINT "SERVICE_ROUTINGS_PK" PRIMARY KEY ("SERVICE_ROUTINGS_ID") using index SDT_OWNER.SERVICE_ROUTINGS_PK ENABLE;



  ALTER TABLE "SDT_OWNER"."SERVICE_TYPES" ADD CONSTRAINT "SERVICE_TYPES_PK" PRIMARY KEY ("SERVICE_TYPE_ID") using index SDT_OWNER.SERVICE_TYPES_PK ENABLE;



  ALTER TABLE "SDT_OWNER"."TARGET_APPLICATIONS" ADD CONSTRAINT "TARGET_APPLICATIONS_PK" PRIMARY KEY ("TARGET_APPLICATION_ID") using index SDT_OWNER.TARGET_APPLICATIONS_PK ENABLE;


