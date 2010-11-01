-- Table: user_validators

-- DROP TABLE user_validators;

CREATE TABLE user_validators
(
  validator_id serial NOT NULL,
  validator_name character varying(50) NOT NULL,
  remarks character varying(200),
  script_snippet text NOT NULL,
  is_enabled boolean NOT NULL DEFAULT true,
  CONSTRAINT pk_u_validators PRIMARY KEY (validator_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE user_validators OWNER TO postgres;
