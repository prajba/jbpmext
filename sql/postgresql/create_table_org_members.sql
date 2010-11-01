-- Table: org_members

-- DROP TABLE org_members;

CREATE TABLE org_members
(
  org_id integer NOT NULL,
  member_id integer NOT NULL,
  begin_time timestamp without time zone NOT NULL,
  end_time timestamp without time zone NOT NULL,
  usable_status integer NOT NULL DEFAULT 0,
  org_member_id serial NOT NULL,
  CONSTRAINT pk_org_member_id PRIMARY KEY (org_member_id),
  CONSTRAINT fk_org_member_member_id FOREIGN KEY (member_id)
      REFERENCES members (member_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_org_member_org_id FOREIGN KEY (org_id)
      REFERENCES organizations (org_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE org_members OWNER TO postgres;
