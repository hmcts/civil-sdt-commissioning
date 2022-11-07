SET ECHO ON

-------------------------------------------------------------
-- Drop each of the schemas used by SDT and all their contents
--------------------------------------------------------------
DROP SCHEMA IF EXISTS "SDT_USER" CASCADE;
DROP SCHEMA IF EXISTS "SDT_BATCH_USER" CASCADE;
DROP SCHEMA IF EXISTS "SDT_OWNER" CASCADE;

-------------------------------------------------------------
-- Drop each of the users used by SD
--------------------------------------------------------------
-- non schema owners first
DROP USER IF EXISTS SDT_USER;
DROP USER IF EXISTS SDT_BATCH_USER;
-- now schema owners
DROP USER IF EXISTS SDT_OWNER;

-------------------------------------------------------------
-- Create the schemas used by SDT and all their contents
--------------------------------------------------------------
CREATE SCHEMA "SDT_OWNER";
CREATE SCHEMA "SDT_USER";
CREATE SCHEMA "SDT_BATCH_USER";

------------------------------------------------
-- Create each of the schemas used by SDT
------------------------------------------------
CREATE USER SDT_OWNER;
GRANT POSTGRES TO SDT_OWNER;

CREATE USER SDT_USER;
GRANT POSTGRES TO SDT_USER;
CREATE USER SDT_BATCH_USER;
GRANT POSTGRES TO SDT_BATCH_USER;

------------------------------------------------
-- Create tables for SDT_OWNER
------------------------------------------------
CREATE TABLE "SDT_OWNER"."BULK_CUSTOMERS"
   ("BULK_CUSTOMER_ID" NUMERIC NOT NULL, "SDT_CUSTOMER_ID" NUMERIC(8),
	"VERSION_NUMBER" NUMERIC DEFAULT 0 ) ;

CREATE TABLE "SDT_OWNER"."BULK_CUSTOMER_APPLICATIONS"
   ("BULK_CUSTOMER_APPLICATIONS_ID" NUMERIC NOT NULL,
	"BULK_CUSTOMER_ID" NUMERIC,	"TARGET_APPLICATION_ID" NUMERIC,
	"CUSTOMER_APPLICATION_ID" VARCHAR(32),	"VERSION_NUMBER" NUMERIC DEFAULT 0 );

CREATE TABLE "SDT_OWNER"."BULK_SUBMISSIONS"
   ("BULK_SUBMISSION_ID" NUMERIC NOT NULL,	"BULK_CUSTOMER_ID" NUMERIC,
	"TARGET_APPLICATION_ID" NUMERIC, "SERVICE_REQUEST_ID" NUMERIC,
	"SDT_BULK_REFERENCE" VARCHAR(29), "CUSTOMER_REFERENCE" VARCHAR(32),
	"CREATED_DATE" TIMESTAMP(6),	"NUMBER_OF_REQUESTS" NUMERIC,
	"BULK_SUBMISSION_STATUS" VARCHAR(20), "COMPLETED_DATE" TIMESTAMP(6),
	"UPDATED_DATE" TIMESTAMP(6), "ERROR_CODE" VARCHAR(32),
	"ERROR_TEXT" VARCHAR(1000),	"VERSION_NUMBER" NUMERIC DEFAULT 0,
	"BULK_PAYLOAD" BYTEA );

CREATE TABLE "SDT_OWNER"."ERROR_LOGS"
   ("ERROR_LOG_ID" NUMERIC NOT NULL, "INDIVIDUAL_REQUEST_ID" NUMERIC,
	"ERROR_CODE" VARCHAR(32), "CREATED_DATE" TIMESTAMP(6),
	"UPDATED_DATE" TIMESTAMP(6), "VERSION_NUMBER" NUMERIC DEFAULT 0,
	"ERROR_TEXT" VARCHAR(1000) );

CREATE TABLE "SDT_OWNER"."ERROR_MESSAGES"
    ("ERROR_MESSAGE_ID" NUMERIC, "ERROR_CODE" VARCHAR(32),
 	"ERROR_TEXT" VARCHAR(1000),	"ERROR_DESCRIPTION" VARCHAR(2000),
 	"VERSION_NUMBER" NUMERIC DEFAULT 0 );

CREATE TABLE "SDT_OWNER"."GLOBAL_PARAMETERS"
    ("GLOBAL_PARAMETER_ID" NUMERIC, "PARAMETER_NAME" VARCHAR(32),
 	"PARAMETER_VALUE" VARCHAR(32), "PARAMETER_DESCRIPTION" VARCHAR(2000),
 	"VERSION_NUMBER" NUMERIC DEFAULT 0 );

CREATE TABLE "SDT_OWNER"."INDIVIDUAL_REQUESTS"
   ("INDIVIDUAL_REQUEST_ID" NUMERIC NOT NULL, "BULK_SUBMISSION_ID" NUMERIC,
	"CUSTOMER_REQUEST_REF" VARCHAR(32),	"REQUEST_STATUS" VARCHAR(32),
	"SDT_BULK_REFERENCE" VARCHAR(29), "LINE_NUMBER" NUMERIC,
	"SDT_REQUEST_REFERENCE" VARCHAR(37), "CREATED_DATE" TIMESTAMP(6),
	"UPDATED_DATE" TIMESTAMP(6), "COMPLETED_DATE" TIMESTAMP(6),
	"FORWARDING_ATTEMPTS" NUMERIC, "DEAD_LETTER" CHAR(1),
	"INTERNAL_SYSTEM_ERROR" VARCHAR(4000), "REQUEST_TYPE" VARCHAR(50),
	"VERSION_NUMBER" NUMERIC DEFAULT 0,	"INDIVIDUAL_PAYLOAD" BYTEA,
	"TARGET_APPLICATION_RESPONSE" BYTEA );

CREATE TABLE "SDT_OWNER"."SERVICE_REQUESTS"
   ("SERVICE_REQUEST_ID" NUMERIC NOT NULL, "REQUEST_PAYLOAD" BYTEA,
	"REQUEST_TIMESTAMP" TIMESTAMP(6), "RESPONSE_PAYLOAD" BYTEA,
	"RESPONSE_TIMESTAMP" TIMESTAMP(6), "REQUEST_TYPE" VARCHAR(32),
	"SDT_CUSTOMER_ID" VARCHAR(32), "SDT_BULK_REFERENCE" VARCHAR(29),
	"SERVER_HOST_NAME" VARCHAR(255), "VERSION_NUMBER" NUMERIC DEFAULT 0 );

CREATE TABLE "SDT_OWNER"."SERVICE_ROUTINGS"
   ("SERVICE_ROUTINGS_ID" NUMERIC NOT NULL,	"SERVICE_TYPE_ID" NUMERIC,
	"TARGET_APPLICATION_ID" NUMERIC, "WEB_SERVICE_ENDPOINT" VARCHAR(255),
	"VERSION_NUMBER" NUMERIC DEFAULT 0 );

CREATE TABLE "SDT_OWNER"."SERVICE_TYPES"
   ("SERVICE_TYPE_ID" NUMERIC NOT NULL,	"SERVICE_TYPE_NAME" VARCHAR(50),
	"SERVICE_TYPE_STATUS" VARCHAR(1), "SERVICE_TYPE_DESCRIPTION" VARCHAR(2000),
	"VERSION_NUMBER" NUMERIC DEFAULT 0 );

CREATE TABLE "SDT_OWNER"."TARGET_APPLICATIONS"
   ("TARGET_APPLICATION_ID" NUMERIC NOT NULL, "TARGET_APPLICATION_CODE" VARCHAR(4),
	"TARGET_APPLICATION_NAME" VARCHAR(255),	"VERSION_NUMBER" NUMERIC DEFAULT 0 );
------------

------------------------------------------------
-- Create indices for SDT_OWNER
------------------------------------------------
CREATE UNIQUE INDEX "BCA_BULK_CUSTOMER_I" ON "SDT_OWNER"."BULK_CUSTOMER_APPLICATIONS" ("BULK_CUSTOMER_ID");
CREATE UNIQUE INDEX "BCA_TARGET_APPLICATION_I" ON "SDT_OWNER"."BULK_CUSTOMER_APPLICATIONS" ("TARGET_APPLICATION_ID");
CREATE UNIQUE INDEX "BS_BULK_CUSTOMER_ID_I" ON "SDT_OWNER"."BULK_SUBMISSIONS" ("BULK_CUSTOMER_ID");
CREATE UNIQUE INDEX "BS_SDT_BULK_REFERENCE_I" ON "SDT_OWNER"."BULK_SUBMISSIONS" ("SDT_BULK_REFERENCE");
CREATE UNIQUE INDEX "BS_SERVICE_REQUEST_ID_I" ON "SDT_OWNER"."BULK_SUBMISSIONS" ("SERVICE_REQUEST_ID");
CREATE UNIQUE INDEX "BS_TARGET_APPLICATION_ID_I" ON "SDT_OWNER"."BULK_SUBMISSIONS" ("TARGET_APPLICATION_ID");
CREATE UNIQUE INDEX "BULK_CUSTOMERS_PK" ON "SDT_OWNER"."BULK_CUSTOMERS" ("BULK_CUSTOMER_ID");
CREATE UNIQUE INDEX "BULK_CUSTOMER_APPLICATIONS_PK" ON "SDT_OWNER"."BULK_CUSTOMER_APPLICATIONS" ("BULK_CUSTOMER_APPLICATIONS_ID");
CREATE UNIQUE INDEX "BULK_SUBMISSIONS_PK" ON "SDT_OWNER"."BULK_SUBMISSIONS" ("BULK_SUBMISSION_ID");
CREATE UNIQUE INDEX "EL_INDIVIDUAL_REQUEST_ID_I" ON "SDT_OWNER"."ERROR_LOGS" ("INDIVIDUAL_REQUEST_ID");
CREATE UNIQUE INDEX "ERROR_LOGS_PK" ON "SDT_OWNER"."ERROR_LOGS" ("ERROR_LOG_ID");
CREATE UNIQUE INDEX "ERROR_MESSAGES_PK" ON "SDT_OWNER"."ERROR_MESSAGES" ("ERROR_MESSAGE_ID");
CREATE UNIQUE INDEX "GLOBAL_PARAMETERS_PK" ON "SDT_OWNER"."GLOBAL_PARAMETERS" ("GLOBAL_PARAMETER_ID");
CREATE UNIQUE INDEX "INDIVIDUAL_REQUESTS_PK" ON "SDT_OWNER"."INDIVIDUAL_REQUESTS" ("INDIVIDUAL_REQUEST_ID");
CREATE UNIQUE INDEX "IR_BULK_REFERENCE_I" ON "SDT_OWNER"."INDIVIDUAL_REQUESTS" ("SDT_BULK_REFERENCE");
CREATE UNIQUE INDEX "IR_BULK_SUBMISSION_ID_I" ON "SDT_OWNER"."INDIVIDUAL_REQUESTS" ("BULK_SUBMISSION_ID");
CREATE UNIQUE INDEX "IR_LWR_CUSTOMER_REQ_REF_CD_I" ON "SDT_OWNER"."INDIVIDUAL_REQUESTS" (LOWER("CUSTOMER_REQUEST_REF"), "CREATED_DATE");
CREATE UNIQUE INDEX "IR_SDT_REQUEST_REFERENCE_I" ON "SDT_OWNER"."INDIVIDUAL_REQUESTS" ("SDT_REQUEST_REFERENCE");
CREATE UNIQUE INDEX "SERVICE_REQUESTS_PK" ON "SDT_OWNER"."SERVICE_REQUESTS" ("SERVICE_REQUEST_ID");
CREATE UNIQUE INDEX "SERVICE_ROUTINGS_PK" ON "SDT_OWNER"."SERVICE_ROUTINGS" ("SERVICE_ROUTINGS_ID");
CREATE UNIQUE INDEX "SERVICE_TYPES_PK" ON "SDT_OWNER"."SERVICE_TYPES" ("SERVICE_TYPE_ID");
CREATE UNIQUE INDEX "SR_SERVICE_TYPE_I" ON "SDT_OWNER"."SERVICE_ROUTINGS" ("SERVICE_TYPE_ID");
CREATE UNIQUE INDEX "SR_TARGET_APPLICATION_I" ON "SDT_OWNER"."SERVICE_ROUTINGS" ("TARGET_APPLICATION_ID");
CREATE UNIQUE INDEX "ST_SERVICE_TYPE_NAME" ON "SDT_OWNER"."SERVICE_TYPES" ("SERVICE_TYPE_NAME");
CREATE UNIQUE INDEX "TARGET_APPLICATIONS_PK" ON "SDT_OWNER"."TARGET_APPLICATIONS" ("TARGET_APPLICATION_ID");
CREATE UNIQUE INDEX "TA_TARGET_APPLICATION_NAME" ON "SDT_OWNER"."TARGET_APPLICATIONS" ("TARGET_APPLICATION_NAME");

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
-- capitalised names reqd?
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
CREATE SEQUENCE  "SDT_OWNER"."BULK_CUST_SEQ"  MINVALUE 1 MAXVALUE 999999999999999999 INCREMENT BY 1 START WITH 1 NO CYCLE;
CREATE SEQUENCE  "SDT_OWNER"."BULK_SUB_SEQ"  MINVALUE 1 MAXVALUE 999999999999999999 INCREMENT BY 1 START WITH 1 NO CYCLE;
CREATE SEQUENCE  "SDT_OWNER"."ERR_LOG_SEQ"  MINVALUE 1 MAXVALUE 999999999999999999 INCREMENT BY 1 START WITH 1 NO CYCLE;
CREATE SEQUENCE  "SDT_OWNER"."ERR_MESG_SEQ"  MINVALUE 1 MAXVALUE 999999999999999999 INCREMENT BY 1 START WITH 1 NO CYCLE;
CREATE SEQUENCE  "SDT_OWNER"."GLB_PAR_SEQ"  MINVALUE 1 MAXVALUE 999999999 INCREMENT BY 1 START WITH 1 CYCLE;
CREATE SEQUENCE  "SDT_OWNER"."IND_REQ_SEQ"  MINVALUE 1 MAXVALUE 999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 2000 NO CYCLE;
CREATE SEQUENCE  "SDT_OWNER"."SDT_REF_SEQ"  MINVALUE 1 MAXVALUE 999999999 INCREMENT BY 1 START WITH 1 CYCLE;
CREATE SEQUENCE  "SDT_OWNER"."SER_ROU_SEQ"  MINVALUE 1 MAXVALUE 999999999 INCREMENT BY 1 START WITH 1 CYCLE;
CREATE SEQUENCE  "SDT_OWNER"."SER_TYP_SEQ"  MINVALUE 1 MAXVALUE 999999999 INCREMENT BY 1 START WITH 1 CYCLE;
CREATE SEQUENCE  "SDT_OWNER"."SRV_REQ_SEQ"  MINVALUE 1 MAXVALUE 999999999999999999 INCREMENT BY 1 START WITH 1 NO CYCLE;
CREATE SEQUENCE  "SDT_OWNER"."TAR_APP_SEQ"  MINVALUE 1 MAXVALUE 999999999 INCREMENT BY 1 START WITH 1 CYCLE;

------------------------------------------------
-- TOD: Grants! Tablespaces Synonym for SDT_OWNER
------------------------------------------------
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
--GRANT SELECT ON "SDT_OWNER"."BULK_CUST_APP_SEQ" TO "SDT_USER";
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
--- System Grants
-----------------
--GRANT UNLIMITED TABLESPACE TO "SDT_OWNER";
--GRANT CREATE SYNONYM TO "SDT_USER";
--GRANT CREATE SYNONYM TO "SDT_BATCH_USER";
-----------------
--- Sys Grants
-----------------
--GRANT EXECUTE ON "SYS"."UTL_FILE" TO "SDT_OWNER";












SET ECHO OFF
