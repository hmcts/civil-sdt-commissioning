-------------------------------------------------------------
-- Drop each of the schemas used by SDT and all their contents
--------------------------------------------------------------
DROP SCHEMA IF EXISTS "SDT_USER" CASCADE;
DROP SCHEMA IF EXISTS "SDT_BATCH_USER" CASCADE;
DROP SCHEMA IF EXISTS "SDT_OWNER" CASCADE;

-------------------------------------------------------------
-- Drop each of the users used by SDT
--------------------------------------------------------------
---- non schema owners first
DROP USER IF EXISTS "SDT_USER";
DROP USER IF EXISTS "SDT_BATCH_USER";
---- now schema owners
DROP USER IF EXISTS "SDT_OWNER";

-------------------------------------------------------------
-- Create the schemas used by SDT and all their contents
--------------------------------------------------------------
CREATE SCHEMA "SDT_OWNER";
CREATE SCHEMA "SDT_USER";
CREATE SCHEMA "SDT_BATCH_USER";

------------------------------------------------
-- Create each of the schemas used by SDT
------------------------------------------------
CREATE USER "SDT_OWNER";
GRANT POSTGRES TO "SDT_OWNER";

CREATE USER "SDT_USER";
GRANT POSTGRES TO "SDT_USER";
CREATE USER "SDT_BATCH_USER";
GRANT POSTGRES TO "SDT_BATCH_USER";

------------------------------------------------
-- Create tables for SDT_OWNER
------------------------------------------------
CREATE TABLE "SDT_OWNER"."BULK_CUSTOMERS"
("BULK_CUSTOMER_ID" NUMERIC NOT NULL,
"SDT_CUSTOMER_ID" NUMERIC(8),
"VERSION_NUMBER" NUMERIC DEFAULT 0);

CREATE TABLE "SDT_OWNER"."BULK_CUSTOMER_APPLICATIONS"
("BULK_CUSTOMER_APPLICATIONS_ID" NUMERIC NOT NULL,
"BULK_CUSTOMER_ID" NUMERIC,
"TARGET_APPLICATION_ID" NUMERIC,
"CUSTOMER_APPLICATION_ID" VARCHAR(32),
"VERSION_NUMBER" NUMERIC DEFAULT 0);

CREATE TABLE "SDT_OWNER"."BULK_SUBMISSIONS"
("BULK_SUBMISSION_ID" NUMERIC NOT NULL,
"BULK_CUSTOMER_ID" NUMERIC,
"TARGET_APPLICATION_ID" NUMERIC,
"SERVICE_REQUEST_ID" NUMERIC,
"SDT_BULK_REFERENCE" VARCHAR(29),
"CUSTOMER_REFERENCE" VARCHAR(32),
"CREATED_DATE" TIMESTAMP(6),
"NUMBER_OF_REQUESTS" NUMERIC,
"BULK_SUBMISSION_STATUS" VARCHAR(20),
"COMPLETED_DATE" TIMESTAMP(6),
"UPDATED_DATE" TIMESTAMP(6),
"ERROR_CODE" VARCHAR(32),
"ERROR_TEXT" VARCHAR(1000),
"VERSION_NUMBER" NUMERIC DEFAULT 0,
"BULK_PAYLOAD" BYTEA);

CREATE TABLE "SDT_OWNER"."ERROR_LOGS"
("ERROR_LOG_ID" NUMERIC NOT NULL,
"INDIVIDUAL_REQUEST_ID" NUMERIC,
"ERROR_CODE" VARCHAR(32),
"CREATED_DATE" TIMESTAMP(6),
"UPDATED_DATE" TIMESTAMP(6),
"VERSION_NUMBER" NUMERIC DEFAULT 0,
"ERROR_TEXT" VARCHAR(1000));

CREATE TABLE "SDT_OWNER"."ERROR_MESSAGES"
("ERROR_MESSAGE_ID" NUMERIC, 
"ERROR_CODE" VARCHAR(32), 
"ERROR_TEXT" VARCHAR(1000), 
"ERROR_DESCRIPTION" VARCHAR(2000), 
"VERSION_NUMBER" NUMERIC DEFAULT 0);

CREATE TABLE "SDT_OWNER"."GLOBAL_PARAMETERS"
("GLOBAL_PARAMETER_ID" NUMERIC, 
"PARAMETER_NAME" VARCHAR(32), 
"PARAMETER_VALUE" VARCHAR(32),
"PARAMETER_DESCRIPTION" VARCHAR(2000), 
"VERSION_NUMBER" NUMERIC DEFAULT 0);

CREATE TABLE "SDT_OWNER"."INDIVIDUAL_REQUESTS"
("INDIVIDUAL_REQUEST_ID" NUMERIC NOT NULL, 
"BULK_SUBMISSION_ID" NUMERIC, 
"CUSTOMER_REQUEST_REF" VARCHAR(32), 
"REQUEST_STATUS" VARCHAR(32), 
"SDT_BULK_REFERENCE" VARCHAR(29), 
"LINE_NUMBER" NUMERIC, 
"SDT_REQUEST_REFERENCE" VARCHAR(37), 
"CREATED_DATE" TIMESTAMP(6), 
"UPDATED_DATE" TIMESTAMP(6), 
"COMPLETED_DATE" TIMESTAMP(6), 
"FORWARDING_ATTEMPTS" NUMERIC, 
"DEAD_LETTER" CHAR(1), 
"INTERNAL_SYSTEM_ERROR" VARCHAR(4000), 
"REQUEST_TYPE" VARCHAR(50), 
"VERSION_NUMBER" NUMERIC DEFAULT 0, 
"INDIVIDUAL_PAYLOAD" BYTEA, 
"TARGET_APPLICATION_RESPONSE" BYTEA);

CREATE TABLE "SDT_OWNER"."SERVICE_REQUESTS"
("SERVICE_REQUEST_ID" NUMERIC NOT NULL, 
"REQUEST_PAYLOAD" BYTEA, 
"REQUEST_TIMESTAMP" TIMESTAMP(6), 
"RESPONSE_PAYLOAD" BYTEA, 
"RESPONSE_TIMESTAMP" TIMESTAMP(6), 
"REQUEST_TYPE" VARCHAR(32), 
"SDT_CUSTOMER_ID" VARCHAR(32), 
"SDT_BULK_REFERENCE" VARCHAR(29), 
"SERVER_HOST_NAME" VARCHAR(255), 
"VERSION_NUMBER" NUMERIC DEFAULT 0);

CREATE TABLE "SDT_OWNER"."SERVICE_ROUTINGS"
("SERVICE_ROUTINGS_ID" NUMERIC NOT NULL, 
"SERVICE_TYPE_ID" NUMERIC, 
"TARGET_APPLICATION_ID" NUMERIC, 
"WEB_SERVICE_ENDPOINT" VARCHAR(255), 
"VERSION_NUMBER" NUMERIC DEFAULT 0);

CREATE TABLE "SDT_OWNER"."SERVICE_TYPES"
("SERVICE_TYPE_ID" NUMERIC NOT NULL, 
"SERVICE_TYPE_NAME" VARCHAR(50), 
"SERVICE_TYPE_STATUS" VARCHAR(1),
"SERVICE_TYPE_DESCRIPTION" VARCHAR(2000), 
"VERSION_NUMBER" NUMERIC DEFAULT 0);

CREATE TABLE "SDT_OWNER"."TARGET_APPLICATIONS"
("TARGET_APPLICATION_ID" NUMERIC NOT NULL, 
"TARGET_APPLICATION_CODE" VARCHAR(4), 
"TARGET_APPLICATION_NAME" VARCHAR(255), 
"VERSION_NUMBER" NUMERIC DEFAULT 0);

------------------------------------------------
-- Create indices for SDT_OWNER
------------------------------------------------
CREATE INDEX "BCA_BULK_CUSTOMER_I" ON "SDT_OWNER"."BULK_CUSTOMER_APPLICATIONS" ("BULK_CUSTOMER_ID");
CREATE INDEX "BCA_TARGET_APPLICATION_I" ON "SDT_OWNER"."BULK_CUSTOMER_APPLICATIONS" ("TARGET_APPLICATION_ID");
CREATE INDEX "BS_BULK_CUSTOMER_ID_I" ON "SDT_OWNER"."BULK_SUBMISSIONS" ("BULK_CUSTOMER_ID");
CREATE INDEX "BS_SDT_BULK_REFERENCE_I" ON "SDT_OWNER"."BULK_SUBMISSIONS" ("SDT_BULK_REFERENCE");
CREATE INDEX "BS_SERVICE_REQUEST_ID_I" ON "SDT_OWNER"."BULK_SUBMISSIONS" ("SERVICE_REQUEST_ID");
CREATE INDEX "BS_TARGET_APPLICATION_ID_I" ON "SDT_OWNER"."BULK_SUBMISSIONS" ("TARGET_APPLICATION_ID");
CREATE UNIQUE INDEX "BULK_CUSTOMERS_PK" ON "SDT_OWNER"."BULK_CUSTOMERS" ("BULK_CUSTOMER_ID");
CREATE UNIQUE INDEX "BULK_CUSTOMER_APPLICATIONS_PK" ON "SDT_OWNER"."BULK_CUSTOMER_APPLICATIONS" ("BULK_CUSTOMER_APPLICATIONS_ID");
CREATE UNIQUE INDEX "BULK_SUBMISSIONS_PK" ON "SDT_OWNER"."BULK_SUBMISSIONS" ("BULK_SUBMISSION_ID");
CREATE INDEX "EL_INDIVIDUAL_REQUEST_ID_I" ON "SDT_OWNER"."ERROR_LOGS" ("INDIVIDUAL_REQUEST_ID");
CREATE UNIQUE INDEX "ERROR_LOGS_PK" ON "SDT_OWNER"."ERROR_LOGS" ("ERROR_LOG_ID");
CREATE UNIQUE INDEX "ERROR_MESSAGES_PK" ON "SDT_OWNER"."ERROR_MESSAGES" ("ERROR_MESSAGE_ID");
CREATE UNIQUE INDEX "GLOBAL_PARAMETERS_PK" ON "SDT_OWNER"."GLOBAL_PARAMETERS" ("GLOBAL_PARAMETER_ID");
CREATE UNIQUE INDEX "INDIVIDUAL_REQUESTS_PK" ON "SDT_OWNER"."INDIVIDUAL_REQUESTS" ("INDIVIDUAL_REQUEST_ID");
CREATE INDEX "IR_BULK_REFERENCE_I" ON "SDT_OWNER"."INDIVIDUAL_REQUESTS" ("SDT_BULK_REFERENCE");
CREATE INDEX "IR_BULK_SUBMISSION_ID_I" ON "SDT_OWNER"."INDIVIDUAL_REQUESTS" ("BULK_SUBMISSION_ID");
CREATE INDEX "IR_LWR_CUSTOMER_REQ_REF_CD_I" ON "SDT_OWNER"."INDIVIDUAL_REQUESTS" (LOWER("CUSTOMER_REQUEST_REF"), "CREATED_DATE");
CREATE INDEX "IR_SDT_REQUEST_REFERENCE_I" ON "SDT_OWNER"."INDIVIDUAL_REQUESTS" ("SDT_REQUEST_REFERENCE");
CREATE UNIQUE INDEX "SERVICE_REQUESTS_PK" ON "SDT_OWNER"."SERVICE_REQUESTS" ("SERVICE_REQUEST_ID");
CREATE UNIQUE INDEX "SERVICE_ROUTINGS_PK" ON "SDT_OWNER"."SERVICE_ROUTINGS" ("SERVICE_ROUTINGS_ID");
CREATE UNIQUE INDEX "SERVICE_TYPES_PK" ON "SDT_OWNER"."SERVICE_TYPES" ("SERVICE_TYPE_ID");
CREATE INDEX "SR_SERVICE_TYPE_I" ON "SDT_OWNER"."SERVICE_ROUTINGS" ("SERVICE_TYPE_ID");
CREATE INDEX "SR_TARGET_APPLICATION_I" ON "SDT_OWNER"."SERVICE_ROUTINGS" ("TARGET_APPLICATION_ID");
CREATE INDEX "ST_SERVICE_TYPE_NAME" ON "SDT_OWNER"."SERVICE_TYPES" ("SERVICE_TYPE_NAME");
CREATE UNIQUE INDEX "TARGET_APPLICATIONS_PK" ON "SDT_OWNER"."TARGET_APPLICATIONS" ("TARGET_APPLICATION_ID");
CREATE INDEX "TA_TARGET_APPLICATION_NAME" ON "SDT_OWNER"."TARGET_APPLICATIONS" ("TARGET_APPLICATION_NAME");

------------------------------------------------
-- Create Primary Keys for SDT_OWNER
------------------------------------------------
ALTER TABLE "SDT_OWNER"."BULK_CUSTOMERS" ADD CONSTRAINT "BULK_CUSTOMERS_PK" PRIMARY KEY USING INDEX "BULK_CUSTOMERS_PK";
ALTER TABLE "SDT_OWNER"."BULK_CUSTOMER_APPLICATIONS" ADD CONSTRAINT "BULK_CUSTOMER_APPLICATIONS_PK" PRIMARY KEY USING INDEX "BULK_CUSTOMER_APPLICATIONS_PK";
ALTER TABLE "SDT_OWNER"."BULK_SUBMISSIONS" ADD CONSTRAINT "BULK_SUBMISSIONS_PK" PRIMARY KEY USING INDEX "BULK_SUBMISSIONS_PK";
ALTER TABLE "SDT_OWNER"."ERROR_LOGS" ADD CONSTRAINT "ERROR_LOGS_PK" PRIMARY KEY USING INDEX "ERROR_LOGS_PK";
ALTER TABLE "SDT_OWNER"."ERROR_MESSAGES" ADD CONSTRAINT "ERROR_MESSAGES_PK" PRIMARY KEY USING INDEX "ERROR_MESSAGES_PK";
ALTER TABLE "SDT_OWNER"."GLOBAL_PARAMETERS" ADD CONSTRAINT "GLOBAL_PARAMETERS_PK" PRIMARY KEY USING INDEX "GLOBAL_PARAMETERS_PK";
ALTER TABLE "SDT_OWNER"."INDIVIDUAL_REQUESTS" ADD CONSTRAINT "INDIVIDUAL_REQUESTS_PK" PRIMARY KEY USING INDEX "INDIVIDUAL_REQUESTS_PK";
ALTER TABLE "SDT_OWNER"."SERVICE_REQUESTS" ADD CONSTRAINT "SERVICE_REQUESTS_PK" PRIMARY KEY USING INDEX "SERVICE_REQUESTS_PK";
ALTER TABLE "SDT_OWNER"."SERVICE_ROUTINGS" ADD CONSTRAINT "SERVICE_ROUTINGS_PK" PRIMARY KEY USING INDEX "SERVICE_ROUTINGS_PK";
ALTER TABLE "SDT_OWNER"."SERVICE_TYPES" ADD CONSTRAINT "SERVICE_TYPES_PK" PRIMARY KEY USING INDEX "SERVICE_TYPES_PK";
ALTER TABLE "SDT_OWNER"."TARGET_APPLICATIONS" ADD CONSTRAINT "TARGET_APPLICATIONS_PK" PRIMARY KEY USING INDEX "TARGET_APPLICATIONS_PK";

------------------------------------------------
-- Create Check Constraints for SDT_OWNER
------------------------------------------------
ALTER TABLE "SDT_OWNER"."BULK_CUSTOMER_APPLICATIONS" ADD CONSTRAINT "BCA_CAI_NN" CHECK ("CUSTOMER_APPLICATION_ID" IS NOT NULL);
ALTER TABLE "SDT_OWNER"."BULK_CUSTOMER_APPLICATIONS" ADD CONSTRAINT "BCA_VN_NN" CHECK ("VERSION_NUMBER" IS NOT NULL);
ALTER TABLE "SDT_OWNER"."BULK_CUSTOMERS" ADD CONSTRAINT "BC_SCI_NN" CHECK ("SDT_CUSTOMER_ID" IS NOT NULL);
ALTER TABLE "SDT_OWNER"."BULK_CUSTOMERS" ADD CONSTRAINT "BC_VN_NN" CHECK ("VERSION_NUMBER" IS NOT NULL);
ALTER TABLE "SDT_OWNER"."BULK_SUBMISSIONS" ADD CONSTRAINT "BS_BCI_NN" CHECK ("BULK_CUSTOMER_ID" IS NOT NULL);
ALTER TABLE "SDT_OWNER"."BULK_SUBMISSIONS" ADD CONSTRAINT "BS_BP_NN" CHECK ("BULK_PAYLOAD" IS NOT NULL);
ALTER TABLE "SDT_OWNER"."BULK_SUBMISSIONS" ADD CONSTRAINT "BS_BSS_NN" CHECK ("BULK_SUBMISSION_STATUS" IS NOT NULL);
ALTER TABLE "SDT_OWNER"."BULK_SUBMISSIONS" ADD CONSTRAINT "BS_CD_NN" CHECK ("CREATED_DATE" IS NOT NULL);
ALTER TABLE "SDT_OWNER"."BULK_SUBMISSIONS" ADD CONSTRAINT "BS_NOR_NN" CHECK ("NUMBER_OF_REQUESTS" IS NOT NULL);
ALTER TABLE "SDT_OWNER"."BULK_SUBMISSIONS" ADD CONSTRAINT "BS_SBR_NN" CHECK ("SDT_BULK_REFERENCE" IS NOT NULL);
ALTER TABLE "SDT_OWNER"."BULK_SUBMISSIONS" ADD CONSTRAINT "BS_TAI_NN" CHECK ("TARGET_APPLICATION_ID" IS NOT NULL);
ALTER TABLE "SDT_OWNER"."BULK_SUBMISSIONS" ADD CONSTRAINT "BS_VN_NN" CHECK ("VERSION_NUMBER" IS NOT NULL);
ALTER TABLE "SDT_OWNER"."ERROR_LOGS" ADD CONSTRAINT "EL_CD_NN" CHECK ("CREATED_DATE" IS NOT NULL);
ALTER TABLE "SDT_OWNER"."ERROR_LOGS" ADD CONSTRAINT "EL_EC_NN" CHECK ("ERROR_CODE" IS NOT NULL);
ALTER TABLE "SDT_OWNER"."ERROR_LOGS" ADD CONSTRAINT "EL_ET_NN" CHECK ("ERROR_TEXT" IS NOT NULL);
ALTER TABLE "SDT_OWNER"."ERROR_LOGS" ADD CONSTRAINT "EL_IRI_NN" CHECK ("INDIVIDUAL_REQUEST_ID" IS NOT NULL);
ALTER TABLE "SDT_OWNER"."ERROR_LOGS" ADD CONSTRAINT "EL_VN_NN" CHECK ("VERSION_NUMBER" IS NOT NULL);
ALTER TABLE "SDT_OWNER"."ERROR_MESSAGES" ADD CONSTRAINT "EM_EC_NN" CHECK ("ERROR_CODE" IS NOT NULL);
ALTER TABLE "SDT_OWNER"."ERROR_MESSAGES" ADD CONSTRAINT "EM_ED_NN" CHECK ("ERROR_DESCRIPTION" IS NOT NULL);
ALTER TABLE "SDT_OWNER"."ERROR_MESSAGES" ADD CONSTRAINT "EM_ET_NN" CHECK ("ERROR_TEXT" IS NOT NULL);
ALTER TABLE "SDT_OWNER"."ERROR_MESSAGES" ADD CONSTRAINT "EM_VN_NN" CHECK ("VERSION_NUMBER" IS NOT NULL);
ALTER TABLE "SDT_OWNER"."GLOBAL_PARAMETERS" ADD CONSTRAINT "GP_PN_NN" CHECK ("PARAMETER_NAME"  IS NOT NULL);
ALTER TABLE "SDT_OWNER"."GLOBAL_PARAMETERS" ADD CONSTRAINT "GP_PV_NN" CHECK ("PARAMETER_VALUE" IS NOT NULL);
ALTER TABLE "SDT_OWNER"."GLOBAL_PARAMETERS" ADD CONSTRAINT "GP_VN_NN" CHECK ("VERSION_NUMBER" IS NOT NULL);
ALTER TABLE "SDT_OWNER"."INDIVIDUAL_REQUESTS" ADD CONSTRAINT "IR_BSI_NN" CHECK ("BULK_SUBMISSION_ID" IS NOT NULL);
ALTER TABLE "SDT_OWNER"."INDIVIDUAL_REQUESTS" ADD CONSTRAINT "IR_CD_NN" CHECK ("CREATED_DATE" IS NOT NULL);
ALTER TABLE "SDT_OWNER"."INDIVIDUAL_REQUESTS" ADD CONSTRAINT "IR_DL_NN" CHECK ("DEAD_LETTER" IS NOT NULL);
ALTER TABLE "SDT_OWNER"."INDIVIDUAL_REQUESTS" ADD CONSTRAINT "IR_LN_NN" CHECK ("LINE_NUMBER" IS NOT NULL);
ALTER TABLE "SDT_OWNER"."INDIVIDUAL_REQUESTS" ADD CONSTRAINT "IR_RS_NN" CHECK ("REQUEST_STATUS" IS NOT NULL);
ALTER TABLE "SDT_OWNER"."INDIVIDUAL_REQUESTS" ADD CONSTRAINT "IR_SBR_NN" CHECK ("SDT_BULK_REFERENCE" IS NOT NULL);
ALTER TABLE "SDT_OWNER"."INDIVIDUAL_REQUESTS" ADD CONSTRAINT "IR_SRR_NN" CHECK ("SDT_REQUEST_REFERENCE" IS NOT NULL);
ALTER TABLE "SDT_OWNER"."INDIVIDUAL_REQUESTS" ADD CONSTRAINT "IR_VN_NN" CHECK ("VERSION_NUMBER" IS NOT NULL);
ALTER TABLE "SDT_OWNER"."SERVICE_REQUESTS" ADD CONSTRAINT "SRE_RT_NN" CHECK ("REQUEST_TIMESTAMP" IS NOT NULL);
ALTER TABLE "SDT_OWNER"."SERVICE_ROUTINGS" ADD CONSTRAINT "SR_VN_NN" CHECK ("VERSION_NUMBER" IS NOT NULL);
ALTER TABLE "SDT_OWNER"."SERVICE_ROUTINGS" ADD CONSTRAINT "SR_WSE_NN" CHECK ("WEB_SERVICE_ENDPOINT" IS NOT NULL);
ALTER TABLE "SDT_OWNER"."SERVICE_TYPES" ADD CONSTRAINT "ST_STN_NN" CHECK ("SERVICE_TYPE_NAME" IS NOT NULL);
ALTER TABLE "SDT_OWNER"."SERVICE_TYPES" ADD CONSTRAINT "ST_STS_NN" CHECK ("SERVICE_TYPE_STATUS" IS NOT NULL);
ALTER TABLE "SDT_OWNER"."SERVICE_TYPES" ADD CONSTRAINT "ST_VN_NN" CHECK ("VERSION_NUMBER" IS NOT NULL);
ALTER TABLE "SDT_OWNER"."TARGET_APPLICATIONS" ADD CONSTRAINT "TA_TAC_NN" CHECK ("TARGET_APPLICATION_CODE" IS NOT NULL);
ALTER TABLE "SDT_OWNER"."TARGET_APPLICATIONS" ADD CONSTRAINT "TA_VN_NN" CHECK ("VERSION_NUMBER" IS NOT NULL);

------------------------------------------------
-- Create Referential constraints for SDT_OWNER
------------------------------------------------
ALTER TABLE "SDT_OWNER"."BULK_CUSTOMER_APPLICATIONS" ADD CONSTRAINT "BCA_BULK_CUSTOMER_FK" FOREIGN KEY ("BULK_CUSTOMER_ID")
  REFERENCES "SDT_OWNER"."BULK_CUSTOMERS" ("BULK_CUSTOMER_ID");
ALTER TABLE "SDT_OWNER"."BULK_CUSTOMER_APPLICATIONS" ADD CONSTRAINT "BCA_TARGET_APPLICATION_FK" FOREIGN KEY ("TARGET_APPLICATION_ID")
  REFERENCES "SDT_OWNER"."TARGET_APPLICATIONS" ("TARGET_APPLICATION_ID");
ALTER TABLE "SDT_OWNER"."BULK_SUBMISSIONS" ADD CONSTRAINT "BS_CUSTOMER_ID_FK" FOREIGN KEY ("BULK_CUSTOMER_ID")
  REFERENCES "SDT_OWNER"."BULK_CUSTOMERS" ("BULK_CUSTOMER_ID");
ALTER TABLE "SDT_OWNER"."BULK_SUBMISSIONS" ADD CONSTRAINT "BS_SERVICE_REQUEST_ID_FK" FOREIGN KEY ("SERVICE_REQUEST_ID")
  REFERENCES "SDT_OWNER"."SERVICE_REQUESTS" ("SERVICE_REQUEST_ID");
ALTER TABLE "SDT_OWNER"."BULK_SUBMISSIONS" ADD CONSTRAINT "BS_TARGET_APPLICATION_FK" FOREIGN KEY ("TARGET_APPLICATION_ID")
  REFERENCES "SDT_OWNER"."TARGET_APPLICATIONS" ("TARGET_APPLICATION_ID");
ALTER TABLE "SDT_OWNER"."ERROR_LOGS" ADD CONSTRAINT "EL_INDIVIDUAL_REQUEST_FK" FOREIGN KEY ("INDIVIDUAL_REQUEST_ID")
  REFERENCES "SDT_OWNER"."INDIVIDUAL_REQUESTS" ("INDIVIDUAL_REQUEST_ID");
ALTER TABLE "SDT_OWNER"."INDIVIDUAL_REQUESTS" ADD CONSTRAINT "IR_BULK_SUBMISSION_FK" FOREIGN KEY ("BULK_SUBMISSION_ID")
  REFERENCES "SDT_OWNER"."BULK_SUBMISSIONS" ("BULK_SUBMISSION_ID");
ALTER TABLE "SDT_OWNER"."SERVICE_ROUTINGS" ADD CONSTRAINT "SR_SERVICE_TYPE_FK" FOREIGN KEY ("SERVICE_TYPE_ID")
  REFERENCES "SDT_OWNER"."SERVICE_TYPES" ("SERVICE_TYPE_ID");
ALTER TABLE "SDT_OWNER"."SERVICE_ROUTINGS" ADD CONSTRAINT "SR_TARGET_APPLICATION_FK" FOREIGN KEY ("TARGET_APPLICATION_ID")
  REFERENCES "SDT_OWNER"."TARGET_APPLICATIONS" ("TARGET_APPLICATION_ID");

------------------------------------------------
-- Create Unique constraints for SDT_OWNER
------------------------------------------------
ALTER TABLE "SDT_OWNER"."BULK_SUBMISSIONS" ADD CONSTRAINT "BS_SBR_UNI" UNIQUE ("SDT_BULK_REFERENCE");
ALTER TABLE "SDT_OWNER"."SERVICE_TYPES" ADD CONSTRAINT "RT_RTN_UNI" UNIQUE ("SERVICE_TYPE_NAME");
ALTER TABLE "SDT_OWNER"."TARGET_APPLICATIONS" ADD CONSTRAINT "TA_TAN_UNI" UNIQUE ("TARGET_APPLICATION_NAME");

------------------------------------------------
-- Create Sequences for SDT_OWNER
------------------------------------------------
-- BIGINT/NUMERIC = 18 digits, but seq was formerly 27 digits! Consider type change
CREATE SEQUENCE "SDT_OWNER"."BULK_CUST_APP_SEQ" MINVALUE 1 MAXVALUE  999999999999999999 INCREMENT BY 1 START WITH 1 NO CYCLE;
CREATE SEQUENCE "SDT_OWNER"."BULK_CUST_SEQ" MINVALUE 1 MAXVALUE 999999999999999999 INCREMENT BY 1 START WITH 1 NO CYCLE;
CREATE SEQUENCE "SDT_OWNER"."BULK_SUB_SEQ" MINVALUE 1 MAXVALUE 999999999999999999 INCREMENT BY 1 START WITH 1 NO CYCLE;
CREATE SEQUENCE "SDT_OWNER"."ERR_LOG_SEQ" MINVALUE 1 MAXVALUE 999999999999999999 INCREMENT BY 1 START WITH 1 NO CYCLE;
CREATE SEQUENCE "SDT_OWNER"."ERR_MESG_SEQ" MINVALUE 1 MAXVALUE 999999999999999999 INCREMENT BY 1 START WITH 1 NO CYCLE;
CREATE SEQUENCE "SDT_OWNER"."GLB_PAR_SEQ" MINVALUE 1 MAXVALUE 999999999 INCREMENT BY 1 START WITH 1 CYCLE;
CREATE SEQUENCE "SDT_OWNER"."IND_REQ_SEQ" MINVALUE 1 MAXVALUE 999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 2000 NO CYCLE;
CREATE SEQUENCE "SDT_OWNER"."SDT_REF_SEQ" MINVALUE 1 MAXVALUE 999999999 INCREMENT BY 1 START WITH 1 CYCLE;
CREATE SEQUENCE "SDT_OWNER"."SER_ROU_SEQ" MINVALUE 1 MAXVALUE 999999999 INCREMENT BY 1 START WITH 1 CYCLE;
CREATE SEQUENCE "SDT_OWNER"."SER_TYP_SEQ" MINVALUE 1 MAXVALUE 999999999 INCREMENT BY 1 START WITH 1 CYCLE;
CREATE SEQUENCE "SDT_OWNER"."SRV_REQ_SEQ" MINVALUE 1 MAXVALUE 999999999999999999 INCREMENT BY 1 START WITH 1 NO CYCLE;
CREATE SEQUENCE "SDT_OWNER"."TAR_APP_SEQ" MINVALUE 1 MAXVALUE 999999999 INCREMENT BY 1 START WITH 1 CYCLE;

GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA "SDT_OWNER" TO "SDT_USER";

GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA "SDT_OWNER" TO "SDT_BATCH_USER";

------------------------------------------------
-- Create Grants for SDT_OWNER
------------------------------------------------
-----------------
--- Object Grants
-----------------
GRANT DELETE ON "SDT_OWNER"."BULK_SUBMISSIONS" TO "SDT_BATCH_USER";
GRANT SELECT ON "SDT_OWNER"."BULK_SUBMISSIONS" TO "SDT_BATCH_USER";
GRANT DELETE ON "SDT_OWNER"."ERROR_LOGS" TO "SDT_BATCH_USER";
GRANT SELECT ON "SDT_OWNER"."ERROR_LOGS" TO "SDT_BATCH_USER";
GRANT SELECT ON "SDT_OWNER"."GLOBAL_PARAMETERS" TO "SDT_BATCH_USER";
GRANT DELETE ON "SDT_OWNER"."INDIVIDUAL_REQUESTS" TO "SDT_BATCH_USER";
GRANT SELECT ON "SDT_OWNER"."INDIVIDUAL_REQUESTS" TO "SDT_BATCH_USER";
GRANT DELETE ON "SDT_OWNER"."SERVICE_REQUESTS" TO "SDT_BATCH_USER";
GRANT SELECT ON "SDT_OWNER"."SERVICE_REQUESTS" TO "SDT_BATCH_USER";
--GRANT EXECUTE ON "SDT_OWNER"."PURGE" TO "SDT_BATCH_USER";
GRANT DELETE ON "SDT_OWNER"."BULK_CUSTOMERS" TO "SDT_USER";
GRANT INSERT ON "SDT_OWNER"."BULK_CUSTOMERS" TO "SDT_USER";
GRANT SELECT ON "SDT_OWNER"."BULK_CUSTOMERS" TO "SDT_USER";
GRANT UPDATE ON "SDT_OWNER"."BULK_CUSTOMERS" TO "SDT_USER";
GRANT DELETE ON "SDT_OWNER"."BULK_CUSTOMER_APPLICATIONS" TO "SDT_USER";
GRANT INSERT ON "SDT_OWNER"."BULK_CUSTOMER_APPLICATIONS" TO "SDT_USER";
GRANT SELECT ON "SDT_OWNER"."BULK_CUSTOMER_APPLICATIONS" TO "SDT_USER";
GRANT UPDATE ON "SDT_OWNER"."BULK_CUSTOMER_APPLICATIONS" TO "SDT_USER";
GRANT DELETE ON "SDT_OWNER"."BULK_SUBMISSIONS" TO "SDT_USER";
GRANT INSERT ON "SDT_OWNER"."BULK_SUBMISSIONS" TO "SDT_USER";
GRANT SELECT ON "SDT_OWNER"."BULK_SUBMISSIONS" TO "SDT_USER";
GRANT UPDATE ON "SDT_OWNER"."BULK_SUBMISSIONS" TO "SDT_USER";
GRANT DELETE ON "SDT_OWNER"."ERROR_LOGS" TO "SDT_USER";
GRANT INSERT ON "SDT_OWNER"."ERROR_LOGS" TO "SDT_USER";
GRANT SELECT ON "SDT_OWNER"."ERROR_LOGS" TO "SDT_USER";
GRANT UPDATE ON "SDT_OWNER"."ERROR_LOGS" TO "SDT_USER";
GRANT DELETE ON "SDT_OWNER"."ERROR_MESSAGES" TO "SDT_USER";
GRANT INSERT ON "SDT_OWNER"."ERROR_MESSAGES" TO "SDT_USER";
GRANT SELECT ON "SDT_OWNER"."ERROR_MESSAGES" TO "SDT_USER";
GRANT UPDATE ON "SDT_OWNER"."ERROR_MESSAGES" TO "SDT_USER";
GRANT DELETE ON "SDT_OWNER"."GLOBAL_PARAMETERS" TO "SDT_USER";
GRANT INSERT ON "SDT_OWNER"."GLOBAL_PARAMETERS" TO "SDT_USER";
GRANT SELECT ON "SDT_OWNER"."GLOBAL_PARAMETERS" TO "SDT_USER";
GRANT UPDATE ON "SDT_OWNER"."GLOBAL_PARAMETERS" TO "SDT_USER";
GRANT DELETE ON "SDT_OWNER"."INDIVIDUAL_REQUESTS" TO "SDT_USER";
GRANT INSERT ON "SDT_OWNER"."INDIVIDUAL_REQUESTS" TO "SDT_USER";
GRANT SELECT ON "SDT_OWNER"."INDIVIDUAL_REQUESTS" TO "SDT_USER";
GRANT UPDATE ON "SDT_OWNER"."INDIVIDUAL_REQUESTS" TO "SDT_USER";
GRANT DELETE ON "SDT_OWNER"."SERVICE_ROUTINGS" TO "SDT_USER";
GRANT INSERT ON "SDT_OWNER"."SERVICE_ROUTINGS" TO "SDT_USER";
GRANT SELECT ON "SDT_OWNER"."SERVICE_ROUTINGS" TO "SDT_USER";
GRANT UPDATE ON "SDT_OWNER"."SERVICE_ROUTINGS" TO "SDT_USER";
GRANT DELETE ON "SDT_OWNER"."SERVICE_TYPES" TO "SDT_USER";
GRANT INSERT ON "SDT_OWNER"."SERVICE_TYPES" TO "SDT_USER";
GRANT SELECT ON "SDT_OWNER"."SERVICE_TYPES" TO "SDT_USER";
GRANT UPDATE ON "SDT_OWNER"."SERVICE_TYPES" TO "SDT_USER";
GRANT DELETE ON "SDT_OWNER"."SERVICE_REQUESTS" TO "SDT_USER";
GRANT INSERT ON "SDT_OWNER"."SERVICE_REQUESTS" TO "SDT_USER";
GRANT SELECT ON "SDT_OWNER"."SERVICE_REQUESTS" TO "SDT_USER";
GRANT UPDATE ON "SDT_OWNER"."SERVICE_REQUESTS" TO "SDT_USER";
GRANT DELETE ON "SDT_OWNER"."TARGET_APPLICATIONS" TO "SDT_USER";
GRANT INSERT ON "SDT_OWNER"."TARGET_APPLICATIONS" TO "SDT_USER";
GRANT SELECT ON "SDT_OWNER"."TARGET_APPLICATIONS" TO "SDT_USER";
GRANT UPDATE ON "SDT_OWNER"."TARGET_APPLICATIONS" TO "SDT_USER";
GRANT SELECT ON "SDT_OWNER"."BULK_CUST_SEQ" TO "SDT_USER";
GRANT SELECT ON "SDT_OWNER"."BULK_CUST_APP_SEQ" TO "SDT_USER";
GRANT SELECT ON "SDT_OWNER"."BULK_SUB_SEQ" TO "SDT_USER";
GRANT SELECT ON "SDT_OWNER"."ERR_LOG_SEQ" TO "SDT_USER";
GRANT SELECT ON "SDT_OWNER"."ERR_MESG_SEQ" TO "SDT_USER";
GRANT SELECT ON "SDT_OWNER"."GLB_PAR_SEQ" TO "SDT_USER";
GRANT SELECT ON "SDT_OWNER"."IND_REQ_SEQ" TO "SDT_USER";
GRANT SELECT ON "SDT_OWNER"."SER_ROU_SEQ" TO "SDT_USER";
GRANT SELECT ON "SDT_OWNER"."SER_TYP_SEQ" TO "SDT_USER";
GRANT SELECT ON "SDT_OWNER"."TAR_APP_SEQ" TO "SDT_USER";
GRANT SELECT ON "SDT_OWNER"."SDT_REF_SEQ" TO "SDT_USER";
GRANT SELECT ON "SDT_OWNER"."SRV_REQ_SEQ" TO "SDT_USER";
-----------------
--- Sys Grants
-----------------
--GRANT EXECUTE ON "SYS"."UTL_FILE" TO "SDT_OWNER";

---------------------------------------------------------
-- Create Views (instead of Oracle Synonyms) for SDT_USER
---------------------------------------------------------
CREATE OR REPLACE VIEW "SDT_USER"."BULK_CUSTOMERS" AS SELECT * FROM "SDT_OWNER"."BULK_CUSTOMERS";
CREATE OR REPLACE VIEW "SDT_USER"."BULK_CUSTOMER_APPLICATIONS" AS SELECT * FROM "SDT_OWNER"."BULK_CUSTOMER_APPLICATIONS";
CREATE OR REPLACE VIEW "SDT_USER"."BULK_CUST_APP_SEQ" AS SELECT * FROM "SDT_OWNER"."BULK_CUST_APP_SEQ";
CREATE OR REPLACE VIEW "SDT_USER"."BULK_CUST_SEQ" AS SELECT * FROM "SDT_OWNER"."BULK_CUST_SEQ";
CREATE OR REPLACE VIEW "SDT_USER"."BULK_SUBMISSIONS" AS SELECT * FROM "SDT_OWNER"."BULK_SUBMISSIONS";
CREATE OR REPLACE VIEW "SDT_USER"."BULK_SUB_SEQ" AS SELECT * FROM "SDT_OWNER"."BULK_SUB_SEQ";
CREATE OR REPLACE VIEW "SDT_USER"."ERROR_LOGS" AS SELECT * FROM "SDT_OWNER"."ERROR_LOGS";
CREATE OR REPLACE VIEW "SDT_USER"."ERROR_MESSAGES" AS SELECT * FROM "SDT_OWNER"."ERROR_MESSAGES";
CREATE OR REPLACE VIEW "SDT_USER"."ERR_LOG_SEQ" AS SELECT * FROM "SDT_OWNER"."ERR_LOG_SEQ";
CREATE OR REPLACE VIEW "SDT_USER"."ERR_MESG_SEQ" AS SELECT * FROM "SDT_OWNER"."ERR_MESG_SEQ";
CREATE OR REPLACE VIEW "SDT_USER"."GLB_PAR_SEQ" AS SELECT * FROM "SDT_OWNER"."GLB_PAR_SEQ";
CREATE OR REPLACE VIEW "SDT_USER"."GLOBAL_PARAMETERS" AS SELECT * FROM "SDT_OWNER"."GLOBAL_PARAMETERS";
CREATE OR REPLACE VIEW "SDT_USER"."INDIVIDUAL_REQUESTS" AS SELECT * FROM "SDT_OWNER"."INDIVIDUAL_REQUESTS";
CREATE OR REPLACE VIEW "SDT_USER"."IND_REQ_SEQ" AS SELECT * FROM "SDT_OWNER"."IND_REQ_SEQ";
CREATE OR REPLACE VIEW "SDT_USER"."SDT_REF_SEQ" AS SELECT * FROM "SDT_OWNER"."SDT_REF_SEQ";
CREATE OR REPLACE VIEW "SDT_USER"."SERVICE_REQUESTS" AS SELECT * FROM "SDT_OWNER"."SERVICE_REQUESTS";
CREATE OR REPLACE VIEW "SDT_USER"."SERVICE_ROUTINGS" AS SELECT * FROM "SDT_OWNER"."SERVICE_ROUTINGS";
CREATE OR REPLACE VIEW "SDT_USER"."SERVICE_TYPES" AS SELECT * FROM "SDT_OWNER"."SERVICE_TYPES";
CREATE OR REPLACE VIEW "SDT_USER"."SER_ROU_SEQ" AS SELECT * FROM "SDT_OWNER"."SER_ROU_SEQ";
CREATE OR REPLACE VIEW "SDT_USER"."SER_TYP_SEQ" AS SELECT * FROM "SDT_OWNER"."SER_TYP_SEQ";
CREATE OR REPLACE VIEW "SDT_USER"."SRV_REQ_SEQ" AS SELECT * FROM "SDT_OWNER"."SRV_REQ_SEQ";
CREATE OR REPLACE VIEW "SDT_USER"."TARGET_APPLICATIONS" AS SELECT * FROM "SDT_OWNER"."TARGET_APPLICATIONS";
CREATE OR REPLACE VIEW "SDT_USER"."TAR_APP_SEQ" AS SELECT * FROM "SDT_OWNER"."TAR_APP_SEQ";

---------------------------------------------------------------
-- Create Views (instead of Oracle Synonyms) for SDT_BATCH_USER
---------------------------------------------------------------
CREATE OR REPLACE VIEW "SDT_BATCH_USER"."BULK_SUBMISSIONS" AS SELECT * FROM "SDT_OWNER"."BULK_SUBMISSIONS";
CREATE OR REPLACE VIEW "SDT_BATCH_USER"."ERROR_LOGS" AS SELECT * FROM "SDT_OWNER"."ERROR_LOGS";
CREATE OR REPLACE VIEW "SDT_BATCH_USER"."GLOBAL_PARAMETERS" AS SELECT * FROM "SDT_OWNER"."GLOBAL_PARAMETERS";
CREATE OR REPLACE VIEW "SDT_BATCH_USER"."INDIVIDUAL_REQUESTS" AS SELECT * FROM "SDT_OWNER"."INDIVIDUAL_REQUESTS";
--CREATE OR REPLACE VIEW "SDT_BATCH_USER"."PURGE" AS SELECT * FROM "SDT_OWNER"."PURGE";
CREATE OR REPLACE VIEW "SDT_BATCH_USER"."SERVICE_REQUESTS" AS SELECT * FROM "SDT_OWNER"."SERVICE_REQUESTS";

------------------------------------------------
-- Create Reference Data
------------------------------------------------
INSERT INTO "SDT_OWNER"."GLOBAL_PARAMETERS" ("GLOBAL_PARAMETER_ID","PARAMETER_NAME","PARAMETER_VALUE","PARAMETER_DESCRIPTION")
VALUES ( 1,'DATA_RETENTION_PERIOD',90,'Duration in days, to retain data in the tables subject to a prescribed purge');
INSERT INTO "SDT_OWNER"."GLOBAL_PARAMETERS" ("GLOBAL_PARAMETER_ID","PARAMETER_NAME","PARAMETER_VALUE","PARAMETER_DESCRIPTION")
VALUES ( 2,'TARGET_APP_TIMEOUT',15000,'Period in milliseconds, to wait for next re-try to reach the target application');
INSERT INTO "SDT_OWNER"."GLOBAL_PARAMETERS" ("GLOBAL_PARAMETER_ID","PARAMETER_NAME","PARAMETER_VALUE","PARAMETER_DESCRIPTION")
VALUES ( 3,'MAX_FORWARDING_ATTEMPTS',3,'Number of forwarding attempts made to transmit an individual request to target application');
INSERT INTO "SDT_OWNER"."GLOBAL_PARAMETERS" ("GLOBAL_PARAMETER_ID","PARAMETER_NAME","PARAMETER_VALUE","PARAMETER_DESCRIPTION")
VALUES ( 4,'MCOL_MAX_CONCURRENT_INDV_REQ',5,'Maximum number of concurrent Individual Requests that can be forwarded to MCOL');
INSERT INTO "SDT_OWNER"."GLOBAL_PARAMETERS" ("GLOBAL_PARAMETER_ID","PARAMETER_NAME","PARAMETER_VALUE","PARAMETER_DESCRIPTION")
VALUES ( 5,'MCOL_INDV_REQ_DELAY',10,'Time delay in milliseconds before processing the next Individual Request that can be forwarded to MCOL');
INSERT INTO "SDT_OWNER"."GLOBAL_PARAMETERS" ("GLOBAL_PARAMETER_ID","PARAMETER_NAME","PARAMETER_VALUE","PARAMETER_DESCRIPTION")
VALUES ( 6,'MCOL_MAX_CONCURRENT_QUERY_REQ',5,'Maximum number of concurrent Submit Query Requests that can be forwarded to MCOL');
INSERT INTO "SDT_OWNER"."GLOBAL_PARAMETERS" ("GLOBAL_PARAMETER_ID","PARAMETER_NAME","PARAMETER_VALUE","PARAMETER_DESCRIPTION")
VALUES ( 7,'CONTACT_DETAILS','tbc','Current contact details for outgoing SDT application messages to Bulk Customer System');
INSERT INTO "SDT_OWNER"."GLOBAL_PARAMETERS" ("GLOBAL_PARAMETER_ID","PARAMETER_NAME","PARAMETER_VALUE","PARAMETER_DESCRIPTION")
VALUES ( 8,'TARGET_APP_RESP_TIMEOUT',30000,'Period in milliseconds, read timeout to wait for response from target application');

INSERT INTO "SDT_OWNER"."SERVICE_TYPES" ("SERVICE_TYPE_ID","SERVICE_TYPE_NAME","SERVICE_TYPE_STATUS","SERVICE_TYPE_DESCRIPTION")
VALUES (1,'SUBMIT_INDIVIDUAL','A', 'Submit individual request web service');
INSERT INTO "SDT_OWNER"."SERVICE_TYPES" ("SERVICE_TYPE_ID","SERVICE_TYPE_NAME","SERVICE_TYPE_STATUS","SERVICE_TYPE_DESCRIPTION")
VALUES (2,'SUBMIT_QUERY','A', 'Submit query web service');

INSERT INTO "SDT_OWNER"."TARGET_APPLICATIONS" ("TARGET_APPLICATION_ID","TARGET_APPLICATION_CODE","TARGET_APPLICATION_NAME")
VALUES (1,'MCOL','mcol live service');
INSERT INTO "SDT_OWNER"."TARGET_APPLICATIONS" ("TARGET_APPLICATION_ID","TARGET_APPLICATION_CODE","TARGET_APPLICATION_NAME")
VALUES (2,'PCOL','pcol live service');
INSERT INTO "SDT_OWNER"."TARGET_APPLICATIONS" ("TARGET_APPLICATION_ID","TARGET_APPLICATION_CODE","TARGET_APPLICATION_NAME")
VALUES (3,'C_MC','mcol commissioning service');
INSERT INTO "SDT_OWNER"."TARGET_APPLICATIONS" ("TARGET_APPLICATION_ID","TARGET_APPLICATION_CODE","TARGET_APPLICATION_NAME")
VALUES (4,'C_PC','pcol commissioning service');

INSERT INTO "SDT_OWNER"."SERVICE_ROUTINGS" ("SERVICE_ROUTINGS_ID","SERVICE_TYPE_ID","TARGET_APPLICATION_ID","WEB_SERVICE_ENDPOINT")
VALUES (1,1,1,'http://localhost:8888/mcol-web-services/service');
INSERT INTO "SDT_OWNER"."SERVICE_ROUTINGS" ("SERVICE_ROUTINGS_ID","SERVICE_TYPE_ID","TARGET_APPLICATION_ID","WEB_SERVICE_ENDPOINT")
VALUES (2,2,1,'http://localhost:8888/mcol-web-services/service');

INSERT INTO "SDT_OWNER"."ERROR_MESSAGES" ("ERROR_MESSAGE_ID","ERROR_CODE","ERROR_TEXT","ERROR_DESCRIPTION")
VALUES (1,'SDT_INT_ERR','A system error has occurred. Please contact {0} for assistance.'
,'A system error has occured');
INSERT INTO "SDT_OWNER"."ERROR_MESSAGES" ("ERROR_MESSAGE_ID","ERROR_CODE","ERROR_TEXT","ERROR_DESCRIPTION")
VALUES (2,'CUST_NOT_SETUP',
'The Bulk Customer organisation is not setup to send Service Request messages to the {0}. Please contact {1} for assistance.',
'The SDT Customer ID for the Bulk Customer organisation is not recognised by SDT.');
INSERT INTO "SDT_OWNER"."ERROR_MESSAGES" ("ERROR_MESSAGE_ID","ERROR_CODE","ERROR_TEXT","ERROR_DESCRIPTION")
VALUES (3,'CUST_ID_INVALID',
'The Bulk Customer organisation does not have an SDT Customer ID set up. Please contact {1} for assistance.',
'The Bulk Customer organisation is recognised by the SDT Service, but is not set up to send a Service Request message to the specified Target Application.');
INSERT INTO "SDT_OWNER"."ERROR_MESSAGES" ("ERROR_MESSAGE_ID","ERROR_CODE","ERROR_TEXT","ERROR_DESCRIPTION")
VALUES (4,'DUP_CUST_FILEID','Duplicate User File Reference {0} supplied. This was previously used to submit a Bulk Request on {1} and the SDT Bulk Reference {2} was allocated.',
'A duplicate User File Reference is identified by SDT for the Bulk Customer.');
INSERT INTO "SDT_OWNER"."ERROR_MESSAGES" ("ERROR_MESSAGE_ID","ERROR_CODE","ERROR_TEXT","ERROR_DESCRIPTION")
VALUES (5,'REQ_COUNT_MISMATCH','Unexpected Total Number of Requests identified. {0} requested identified, {1} requests expected in Bulk Request {2}.',
'The Total Number of Requests identified by SDT does not match the Total Number of Requests expected (provided by the Submit Bulk Request).');
INSERT INTO "SDT_OWNER"."ERROR_MESSAGES" ("ERROR_MESSAGE_ID","ERROR_CODE","ERROR_TEXT","ERROR_DESCRIPTION")
VALUES (6,'BULK_REF_INVALID','There is no Bulk Request submission associated with your account for the supplied SDT Bulk Reference {0}.',
'The supplied SDT Bulk Reference is not listed against the Bulk Customers Bulk Submissions detail');
INSERT INTO "SDT_OWNER"."ERROR_MESSAGES" ("ERROR_MESSAGE_ID","ERROR_CODE","ERROR_TEXT","ERROR_DESCRIPTION")
VALUES (7,'TAR_APP_BUSY','The Target Application is currently busy and cannot process your Submit Query request. Please try again later.',
'SDT has reached the maximum number of concurrent Submit Query requests that can be forwarded to the Target Application for processing.');
INSERT INTO "SDT_OWNER"."ERROR_MESSAGES" ("ERROR_MESSAGE_ID","ERROR_CODE","ERROR_TEXT","ERROR_DESCRIPTION")
VALUES (8,'TAR_APP_ERROR','The system encountered a problem when processing your Submit Query request. Please try again later or contact {0} for assistance.',
'The Target Appliation does not send a response to SDT within the expected timescale, or an error message is received.');
INSERT INTO "SDT_OWNER"."ERROR_MESSAGES" ("ERROR_MESSAGE_ID","ERROR_CODE","ERROR_TEXT","ERROR_DESCRIPTION")
VALUES (9,'DUP_CUST_REFID','Duplicate User File Reference {0} supplied','A duplicate User File Reference is identified by SDT for the Bulk Customer.');
INSERT INTO "SDT_OWNER"."ERROR_MESSAGES" ("ERROR_MESSAGE_ID","ERROR_CODE","ERROR_TEXT","ERROR_DESCRIPTION")
VALUES (10,'NO_VALID_REQS','The submitted Bulk Request {0} does not contain valid individual Requests.',
'The Submit Bulk Request message does not contain Individual Requests deemed to be valid. The Individual Requests have all failed SDT format validation and been rejected and processing has been completed as far as possible.');
INSERT INTO "SDT_OWNER"."ERROR_MESSAGES" ("ERROR_MESSAGE_ID","ERROR_CODE","ERROR_TEXT","ERROR_DESCRIPTION")
VALUES (11,'DUPLD_CUST_REQID','Unique Request Identifier has been specified more than once within the originating Bulk Request.',
'SDT identifies that a Unique Request Identifier has been associated with more than one Individual Request within the same Bulk Request.');
INSERT INTO "SDT_OWNER"."ERROR_MESSAGES" ("ERROR_MESSAGE_ID","ERROR_CODE","ERROR_TEXT","ERROR_DESCRIPTION")
VALUES (12,'DUP_CUST_REQID','Duplicate Unique Request Identifier submitted {0}.','SDT identifies that a Unique Request Identifier has been associated with more than one Individual Request within the same Bulk Request.');
INSERT INTO "SDT_OWNER"."ERROR_MESSAGES" ("ERROR_MESSAGE_ID","ERROR_CODE","ERROR_TEXT","ERROR_DESCRIPTION")
VALUES (13,'REQ_NOT_ACK','Individual Request not acknowledged by Target Application. Please contact {0} for assistance.',
'The Target Application does not send back an acknowledgement response within the expected period, or returns a response indicating that there was an error with the transmission.');
INSERT INTO "SDT_OWNER"."ERROR_MESSAGES" ("ERROR_MESSAGE_ID","ERROR_CODE","ERROR_TEXT","ERROR_DESCRIPTION")
VALUES (14,'CUST_XML_ERR','Individual Request format could not be processed by the Target Application. Please check the data and resubmit the request, or contact {0} for assistance.',
'Client data has caused SOAP Fault error and rejected the request.');
