-- Table: user_dict_categories

-- DROP TABLE user_dict_categories;

CREATE TABLE user_dict_categories
(
  category_id character varying(200) NOT NULL,
  display_name character varying(200) NOT NULL,
  value_type character varying(10) NOT NULL,
  table_name character varying(200) NOT NULL,
  CONSTRAINT pk_u_dict_cats PRIMARY KEY (category_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE user_dict_categories OWNER TO postgres;
