-- Table: members

-- DROP TABLE members;

CREATE TABLE members
(
  member_id serial NOT NULL,
  login_name character varying(200),
  begin_time timestamp without time zone NOT NULL,
  end_time timestamp without time zone,
  usable_status integer NOT NULL DEFAULT 0,
  login_password character varying(32),
  full_name character varying(200) NOT NULL,
  CONSTRAINT pk_members_id PRIMARY KEY (member_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE members OWNER TO postgres;
