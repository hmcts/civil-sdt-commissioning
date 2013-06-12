alter session set current_schema=sdt_owner;


--
-- PK for bulk_customers
--

CREATE SEQUENCE bulk_cust_seq
MINVALUE 1
MAXVALUE 999999999999999999999999999
INCREMENT BY 1
NOCYCLE
NOCACHE
NOORDER
;


--
-- PK for bulk_submissions
--

CREATE SEQUENCE bulk_sub_seq
MINVALUE 1
MAXVALUE 999999999999999999999999999
INCREMENT BY 1
NOCYCLE
NOCACHE
NOORDER
;

--
-- PK for individual_requests

CREATE SEQUENCE ind_req_seq
MINVALUE 1
MAXVALUE 999999999999999999999999999
INCREMENT BY 1
NOCYCLE
NOCACHE
NOORDER
;

--
-- PK for error_logs
--

CREATE SEQUENCE err_log_seq
MINVALUE 1
MAXVALUE 999999999999999999999999999
INCREMENT BY 1
NOCYCLE
NOCACHE
NOORDER
;

--
-- PK for message_logs
--

CREATE SEQUENCE msg_log_seq
MINVALUE 1
MAXVALUE 999999999999999999999999999
INCREMENT BY 1
NOCYCLE
NOCACHE
NOORDER
;

--
-- Sequence to support sdt_bulk_reference
--

CREATE SEQUENCE sdt_ref_seq
MINVALUE 1
MAXVALUE 999999999
INCREMENT BY 1
CYCLE
NOCACHE
NOORDER
;
