-- Table: organizations

-- DROP TABLE organizations;

CREATE TABLE organizations
(
  org_id serial NOT NULL,
  org_name character varying(200) NOT NULL,
  begin_time timestamp without time zone NOT NULL,
  end_time timestamp without time zone,
  usable_status integer NOT NULL DEFAULT 0,
  org_type character varying(20) NOT NULL,
  parent_id integer,
  org_code character varying(60) NOT NULL,
  CONSTRAINT pk_org_id PRIMARY KEY (org_id),
  CONSTRAINT fk_org_parent_id FOREIGN KEY (parent_id)
      REFERENCES organizations (org_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE organizations OWNER TO postgres;
