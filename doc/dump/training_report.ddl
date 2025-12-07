--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.2
-- Dumped by pg_dump version 9.5.2

-- Started on 2016-06-02 04:57:47 PDT

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 13223)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 3063 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 2 (class 3079 OID 16805)
-- Name: pgcrypto; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS pgcrypto WITH SCHEMA public;


--
-- TOC entry 3064 (class 0 OID 0)
-- Dependencies: 2
-- Name: EXTENSION pgcrypto; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION pgcrypto IS 'cryptographic functions';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 188 (class 1259 OID 16728)
-- Name: comment; Type: TABLE; Schema: public; Owner: custuser
--

CREATE TABLE comment (
    user_id character varying(20) NOT NULL,
    date date NOT NULL,
    seq integer NOT NULL,
    seq_comment integer NOT NULL,
    reg_user_id character varying(20),
    comment text,
    idate timestamp without time zone,
    udate timestamp without time zone
);


ALTER TABLE comment OWNER TO custuser;

--
-- TOC entry 182 (class 1259 OID 16539)
-- Name: info; Type: TABLE; Schema: public; Owner: custuser
--

CREATE TABLE info (
    seq integer NOT NULL,
    date date,
    body text,
    limit_date date,
    idate timestamp without time zone,
    udate timestamp without time zone
);


ALTER TABLE info OWNER TO custuser;

--
-- TOC entry 183 (class 1259 OID 16552)
-- Name: m_dept; Type: TABLE; Schema: public; Owner: custuser
--

CREATE TABLE m_dept (
    dept_code character varying(3) NOT NULL,
    dept_name character varying(50),
    idate timestamp without time zone,
    udate timestamp without time zone
);


ALTER TABLE m_dept OWNER TO custuser;

--
-- TOC entry 189 (class 1259 OID 16750)
-- Name: m_holiday; Type: TABLE; Schema: public; Owner: custuser
--

CREATE TABLE m_holiday (
    date date NOT NULL,
    note character varying(60),
    kind character(1),
    idate timestamp without time zone,
    udate timestamp without time zone
);


ALTER TABLE m_holiday OWNER TO custuser;

--
-- TOC entry 186 (class 1259 OID 16715)
-- Name: m_type; Type: TABLE; Schema: public; Owner: custuser
--

CREATE TABLE m_type (
    type_code character varying(3) NOT NULL,
    type_name character varying(30),
    idate timestamp without time zone,
    udate timestamp without time zone
);


ALTER TABLE m_type OWNER TO custuser;

--
-- TOC entry 191 (class 1259 OID 16842)
-- Name: m_user; Type: TABLE; Schema: public; Owner: custuser
--

CREATE TABLE m_user (
    user_id character varying(20) NOT NULL,
    user_pass text,
    user_name character varying(50),
    dept_code character varying(3),
    user_com character varying(1),
    mail_addr character varying(60),
    pass_limit timestamp without time zone,
    del_flag character(1),
    idate timestamp without time zone,
    udate timestamp without time zone
);


ALTER TABLE m_user OWNER TO custuser;

--
-- TOC entry 187 (class 1259 OID 16720)
-- Name: report; Type: TABLE; Schema: public; Owner: custuser
--

CREATE TABLE report (
    user_id character varying(20) NOT NULL,
    date date NOT NULL,
    seq integer NOT NULL,
    start_time character(4),
    end_time character(4),
    pre_time numeric,
    trv_time numeric,
    aft_time numeric,
    type_code character varying(3),
    title character varying(50),
    cust_name character varying(50),
    cust_dept character varying(50),
    cust_persons character varying(100),
    fact text,
    guess text,
    next text,
    apl_flag character(1),
    apl_user_id character varying(20),
    apl_comment text,
    idate timestamp without time zone,
    udate timestamp without time zone
);


ALTER TABLE report OWNER TO custuser;

--
-- TOC entry 185 (class 1259 OID 16567)
-- Name: schedule; Type: TABLE; Schema: public; Owner: custuser
--

CREATE TABLE schedule (
    user_id character varying(20) NOT NULL,
    date date NOT NULL,
    seq integer NOT NULL,
    start_time character(4),
    end_time character(4),
    title character varying(50),
    body text,
    idate timestamp without time zone,
    udate timestamp without time zone
);


ALTER TABLE schedule OWNER TO custuser;

--
-- TOC entry 184 (class 1259 OID 16562)
-- Name: view_group; Type: TABLE; Schema: public; Owner: custuser
--

CREATE TABLE view_group (
    user_id character varying(20) NOT NULL,
    view_user_id character varying(20) NOT NULL,
    idate timestamp without time zone
);


ALTER TABLE view_group OWNER TO custuser;

--
-- TOC entry 2937 (class 2606 OID 16735)
-- Name: comment_pkey; Type: CONSTRAINT; Schema: public; Owner: custuser
--

ALTER TABLE ONLY comment
    ADD CONSTRAINT comment_pkey PRIMARY KEY (user_id, date, seq, seq_comment);


--
-- TOC entry 2925 (class 2606 OID 16546)
-- Name: info_pkey; Type: CONSTRAINT; Schema: public; Owner: custuser
--

ALTER TABLE ONLY info
    ADD CONSTRAINT info_pkey PRIMARY KEY (seq);


--
-- TOC entry 2927 (class 2606 OID 16556)
-- Name: m_dept_pkey; Type: CONSTRAINT; Schema: public; Owner: custuser
--

ALTER TABLE ONLY m_dept
    ADD CONSTRAINT m_dept_pkey PRIMARY KEY (dept_code);


--
-- TOC entry 2939 (class 2606 OID 16754)
-- Name: m_holiday_pkey; Type: CONSTRAINT; Schema: public; Owner: custuser
--

ALTER TABLE ONLY m_holiday
    ADD CONSTRAINT m_holiday_pkey PRIMARY KEY (date);


--
-- TOC entry 2933 (class 2606 OID 16719)
-- Name: m_type_pkey; Type: CONSTRAINT; Schema: public; Owner: custuser
--

ALTER TABLE ONLY m_type
    ADD CONSTRAINT m_type_pkey PRIMARY KEY (type_code);


--
-- TOC entry 2941 (class 2606 OID 16849)
-- Name: m_user_pkey; Type: CONSTRAINT; Schema: public; Owner: custuser
--

ALTER TABLE ONLY m_user
    ADD CONSTRAINT m_user_pkey PRIMARY KEY (user_id);


--
-- TOC entry 2931 (class 2606 OID 16574)
-- Name: schedule_pkey; Type: CONSTRAINT; Schema: public; Owner: custuser
--

ALTER TABLE ONLY schedule
    ADD CONSTRAINT schedule_pkey PRIMARY KEY (user_id, date, seq);


--
-- TOC entry 2935 (class 2606 OID 16727)
-- Name: type_pkey; Type: CONSTRAINT; Schema: public; Owner: custuser
--

ALTER TABLE ONLY report
    ADD CONSTRAINT type_pkey PRIMARY KEY (user_id, date, seq);


--
-- TOC entry 2929 (class 2606 OID 16566)
-- Name: view_group_pkey; Type: CONSTRAINT; Schema: public; Owner: custuser
--

ALTER TABLE ONLY view_group
    ADD CONSTRAINT view_group_pkey PRIMARY KEY (user_id, view_user_id);


--
-- TOC entry 3062 (class 0 OID 0)
-- Dependencies: 7
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2016-06-02 04:57:48 PDT

--
-- PostgreSQL database dump complete
--
