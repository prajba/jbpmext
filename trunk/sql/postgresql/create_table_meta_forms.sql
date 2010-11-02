-- Table: meta_forms

-- DROP TABLE meta_forms;

CREATE TABLE meta_forms
(
  form_id integer NOT NULL DEFAULT nextval('meta_form_form_id_seq'::regclass),
  form_name character varying(200) NOT NULL,
  table_name character varying(200) NOT NULL,
  remarks character varying,
  form_html text NOT NULL,
  begin_time timestamp without time zone NOT NULL,
  end_time timestamp without time zone,
  usable_status integer NOT NULL DEFAULT 0,
  CONSTRAINT pk_meta_form_id PRIMARY KEY (form_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE meta_forms OWNER TO postgres;
