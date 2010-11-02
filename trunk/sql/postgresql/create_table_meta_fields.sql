-- Table: meta_fields

-- DROP TABLE meta_fields;

CREATE TABLE meta_fields
(
  field_id serial NOT NULL,
  field_name character varying(200) NOT NULL,
  column_name character varying(200) NOT NULL,
  form_id integer NOT NULL,
  input_hint character varying(200) NOT NULL,
  display_type character varying(50) NOT NULL,
  data_type character varying(50) NOT NULL,
  dict_category character varying(200),
  ref_form_id integer,
  usable_status integer NOT NULL DEFAULT 0,
  validators text,
  remarks character varying(200),
  CONSTRAINT pk_meta_field_id PRIMARY KEY (field_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE meta_fields OWNER TO postgres;
