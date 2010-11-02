-- Table: obj_permissions

-- DROP TABLE obj_permissions;

CREATE TABLE obj_permissions
(
  obj_id serial NOT NULL,
  owner_id integer NOT NULL,
  group_id integer NOT NULL,
  permission_value integer NOT NULL DEFAULT 503414784,
  CONSTRAINT pk_obj_permission_id PRIMARY KEY (obj_id),
  CONSTRAINT fk_obj_permission_group_id FOREIGN KEY (group_id)
      REFERENCES organizations (org_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_obj_permission_owner_id FOREIGN KEY (owner_id)
      REFERENCES members (member_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE obj_permissions OWNER TO postgres;
