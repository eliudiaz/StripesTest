-- Table: role

-- DROP TABLE role;

CREATE TABLE role
(
  id serial NOT NULL,
  nombre character varying(50),
  estado character varying(50),
  fecha_creacion timestamp with time zone NOT NULL,
  creado_por character varying(50),
  fecha_ultimo_cambio timestamp with time zone,
  ultimo_cambio_por character varying(50),
  CONSTRAINT role_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);

-- Table: acceso

-- DROP TABLE acceso;

CREATE TABLE acceso
(
   id serial NOT NULL,
  valor character varying(50),
  tipo character varying(50),
  estado character varying(50),
  fecha_creacion timestamp with time zone NOT NULL,
  creado_por character varying(50),
  fecha_ultimo_cambio timestamp with time zone,
  ultimo_cambio_por character varying(50),
  codigo_padre integer,
  CONSTRAINT acceso_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);


CREATE TABLE acceso_role
(
    id serial NOT NULL,
  fk_acceso integer NOT NULL,
  fk_role integer NOT NULL,
  fecha_creacion timestamp with time zone NOT NULL,
  creado_por character varying(50),
  fecha_ultimo_cambio timestamp with time zone,
  ultimo_cambio_por character varying(50),
  CONSTRAINT acceso_role_pkey PRIMARY KEY (id),
  CONSTRAINT acceso_fk_role_fkey FOREIGN KEY (fk_role)
      REFERENCES role (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_acceso_fk FOREIGN KEY (fk_acceso)
      REFERENCES acceso (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);


  -- Table: usuario

-- DROP TABLE usuario;

CREATE TABLE usuario
(
   id character varying(100) NOT NULL,
  correo character varying(100),
  fk_persona character varying(50),
  fk_role integer NOT NULL,
  super_usuario boolean,
  estado character varying(50),
  nombres character varying(50),
  apellidos character varying(50),
  clave character varying(50),
  fecha_creacion timestamp with time zone NOT NULL,
  creado_por character varying(50),
  fecha_ultimo_cambio timestamp with time zone,
  ultimo_cambio_por character varying(50),
  CONSTRAINT usuario_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);


-- Table: area_geografica

-- DROP TABLE area_geografica;

CREATE TABLE area_geografica
(
 id serial NOT NULL,
  valor character varying(500),
  tipo character varying(50),
  estado character varying(50),
  codigo_padre integer,
  fecha_creacion timestamp with time zone NOT NULL,
  creado_por character varying(50),
  fecha_ultimo_cambio timestamp with time zone,
  ultimo_cambio_por character varying(50),
  CONSTRAINT area_geografica_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);

  -- Table: catalogos

-- DROP TABLE catalogos;

CREATE TABLE catalogos
(
   id serial NOT NULL,
  valor character varying(500),
  tipo character varying(50),
  estado character varying(50),
  codigo_padre integer,
  fecha_creacion timestamp with time zone NOT NULL,
  creado_por character varying(50),
  fecha_ultimo_cambio timestamp with time zone,
  ultimo_cambio_por character varying(50),
  CONSTRAINT catalogos_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);


-- Table: persona

-- DROP TABLE persona;

CREATE TABLE persona
(
   cui character varying(50) NOT NULL,
  primer_nombre character varying(100) NOT NULL,
  segundo_nombre character varying(100),
  otros_nombres text,
  primer_apellido character varying(100) NOT NULL,
  segundo_apellido character varying(100),
  otros_apellidos text,
  apellido_casada character varying(100),
  estado_civil character varying(50),
  sexo character varying(50),
  fk_nacionalidad integer NOT NULL, -- Catalogo tipo NACIONALIDADES
  fk_profesion integer NOT NULL, -- Catalogo tipo PROFESIONES
  limitaciones_fisicas text,
  sabe_leer boolean,
  sabe_escribir boolean,
  fecha_nacimiento timestamp with time zone NOT NULL,
  fk_municipio_nacimiento integer, -- Catalogo tipo MUNICIPIOS
  nac_no_libro character varying(50),
  nac_no_folio character varying(50),
  nac_no_partida character varying(50),
  fk_pueblo character varying(50),
  fk_comunidad_linguistica character varying(50),
  mrz text,
  no_cedula character varying(50),
  estado character varying(50),
  fk_municipio_cedula integer, -- Catalogo tipo MUNICIPIOS
  fk_municipio_vecindad integer, -- Catalogo tipo MUNICIPIOS
  huella_mano_der boolean,
  huella_mano_izq boolean,
  huella_dedo_der character varying(50),
  huella_dedo_izq character varying(50),
  fecha_creacion timestamp with time zone NOT NULL,
  creado_por character varying(50) NOT NULL,
  fecha_ultimo_cambio timestamp with time zone,
  ultimo_cambio_por character varying(50),
  edad integer NOT NULL,
  CONSTRAINT persona_pkey PRIMARY KEY (cui)
)
WITH (
  OIDS=FALSE
);

COMMENT ON COLUMN persona.fk_nacionalidad IS 'Catalogo tipo NACIONALIDADES';
COMMENT ON COLUMN persona.fk_profesion IS 'Catalogo tipo PROFESIONES';
COMMENT ON COLUMN persona.fk_municipio_nacimiento IS 'Catalogo tipo MUNICIPIOS';
COMMENT ON COLUMN persona.fk_municipio_cedula IS 'Catalogo tipo MUNICIPIOS';
COMMENT ON COLUMN persona.fk_municipio_vecindad IS 'Catalogo tipo MUNICIPIOS';

-- Table: dpi

-- DROP TABLE dpi;

CREATE TABLE dpi
(
  no_serie character varying(50) NOT NULL,
  fecha_emision timestamp with time zone NOT NULL,
  fecha_vencimiento timestamp with time zone NOT NULL,
  estado character varying(50),
  fk_persona character varying(50),
  fecha_creacion timestamp with time zone NOT NULL,
  creado_por character varying(50),
  CONSTRAINT dpi_pkey PRIMARY KEY (no_serie),
  CONSTRAINT dpi_fk_persona_fkey FOREIGN KEY (fk_persona)
      REFERENCES persona (cui) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

-- Table: estudio_salud

-- DROP TABLE estudio_salud;

CREATE TABLE estudio_salud
(
  id serial NOT NULL,
  anio_estudio integer,
  fk_persona character varying(50) NOT NULL,
  fecha_creacion timestamp with time zone NOT NULL,
  creado_por character varying(50),
  fecha_ultimo_cambio timestamp with time zone,
  ultimo_cambio_por character varying(50),
  CONSTRAINT estudio_salud_pkey PRIMARY KEY (id),
  CONSTRAINT estudio_salud_fk_persona_fkey FOREIGN KEY (fk_persona)
      REFERENCES persona (cui) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

-- Table: historico_persona

-- DROP TABLE historico_persona;

CREATE TABLE historico_persona
(
 id serial NOT NULL,
  fk_persona character varying(50) NOT NULL,
  primer_nombre character varying(100) NOT NULL,
  segundo_nombre character varying(100),
  otros_nombres text,
  primer_apellido character varying(100) NOT NULL,
  segundo_apellido character varying(100),
  otros_apellidos text,
  apellido_casada character varying(100),
  estado_civil character varying(50),
  sexo character varying(50),
  fk_nacionalidad integer NOT NULL,
  fk_profesion integer NOT NULL,
  limitaciones_fisicas text,
  sabe_leer boolean,
  sabe_escribir boolean,
  fecha_nacimiento timestamp with time zone NOT NULL,
  fk_municipio_nacimiento integer,
  nac_no_libro character varying(50),
  nac_no_folio character varying(50),
  nac_no_partida character varying(50),
  fk_pueblo character varying(50),
  fk_comunidad_linguistica character varying(50),
  mrz text,
  no_cedula character varying(50),
  estado character varying(50),
  fk_municipio_cedula integer,
  fk_municipio_vecindad integer,
  huella_mano_der boolean,
  huella_mano_izq boolean,
  huella_dedo_der character varying(50),
  huella_dedo_izq character varying(50),
  fecha_creacion timestamp with time zone NOT NULL,
  creado_por character varying(50) NOT NULL,
  edad integer NOT NULL,
  CONSTRAINT historico_persona_pkey PRIMARY KEY (id),
  CONSTRAINT historico_persona_fk_persona_fkey FOREIGN KEY (fk_persona)
      REFERENCES persona (cui) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

-- Table: idioma

-- DROP TABLE idioma;

CREATE TABLE idioma
(
   id serial NOT NULL,
  fk_idioma integer NOT NULL, -- Catalogo tipo IDIOMAS
  fk_persona character varying(50) NOT NULL,
  fecha_creacion timestamp with time zone NOT NULL,
  creado_por character varying(50),
  fecha_ultimo_cambio timestamp with time zone,
  ultimo_cambio_por character varying(50),
  CONSTRAINT unidad_ejecutora_pkey PRIMARY KEY (id),
  CONSTRAINT dpi_fk_persona_fkey FOREIGN KEY (fk_persona)
      REFERENCES persona (cui) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

COMMENT ON COLUMN idioma.fk_idioma IS 'Catalogo tipo IDIOMAS';

-- Table: lugar_residencia

-- DROP TABLE lugar_residencia;

CREATE TABLE lugar_residencia
(
 id serial NOT NULL,
  fk_municipio integer, -- Catalogo tipo MUNICIPIOS
  fk_persona character varying(50) NOT NULL,
  estado character varying(50),
  direccion text,
  fecha_creacion timestamp with time zone NOT NULL,
  creado_por character varying(50),
  CONSTRAINT lugar_residencia_pkey PRIMARY KEY (id),
  CONSTRAINT lugar_residencia_fk_persona_fkey FOREIGN KEY (fk_persona)
      REFERENCES persona (cui) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

COMMENT ON COLUMN lugar_residencia.fk_municipio IS 'Catalogo tipo MUNICIPIOS';


-- Table: puestos

-- DROP TABLE puestos;

CREATE TABLE puestos
(
  id serial NOT NULL,
  valor character varying(500),
  tipo character varying(50),
  estado character varying(50),
  codigo_padre integer,
  fecha_creacion timestamp with time zone NOT NULL,
  creado_por character varying(50),
  fecha_ultimo_cambio timestamp with time zone,
  ultimo_cambio_por character varying(50),
  valor_num integer,
  CONSTRAINT puestos_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);

-- Table: registro_academico

-- DROP TABLE registro_academico;

CREATE TABLE registro_academico
(
  id serial NOT NULL,
  ultimo_grado integer NOT NULL,
  estudia_actualmente boolean,
  grado_actual integer NOT NULL,
  fk_persona character varying(50),
  estado character varying(50),
  fecha_creacion timestamp with time zone NOT NULL,
  creado_por character varying(50),
  CONSTRAINT registro_academico_pkey PRIMARY KEY (id),
  CONSTRAINT registro_academico_fk_persona_fkey FOREIGN KEY (fk_persona)
      REFERENCES persona (cui) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

-- Table: registro_laboral

-- DROP TABLE registro_laboral;

CREATE TABLE registro_laboral
(
  id serial NOT NULL,
  anio_ingreso integer,
  fk_expectativa integer, -- Catalogo tipo EXPECTATIVAS
  fk_persona character varying(50) NOT NULL,
  comisionado boolean,
  fk_comunidad_comisionado integer, -- Catalogo tipo AREA_GEOGRAFICA
  estado character varying(50),
  fecha_creacion timestamp with time zone NOT NULL,
  creado_por character varying(50),
  fecha_ultimo_cambio timestamp with time zone,
  ultimo_cambio_por character varying(50),
  CONSTRAINT registro_laboral_pkey PRIMARY KEY (id),
  CONSTRAINT registro_laboral_fk_persona_fkey FOREIGN KEY (fk_persona)
      REFERENCES persona (cui) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
COMMENT ON COLUMN registro_laboral.fk_expectativa IS 'Catalogo tipo EXPECTATIVAS';
COMMENT ON COLUMN registro_laboral.fk_comunidad_comisionado IS 'Catalogo tipo AREA_GEOGRAFICA';

-- Table: puesto

-- DROP TABLE puesto;

CREATE TABLE puesto
(
 id serial NOT NULL,
  tipo character varying(50),
  fk_puesto_nominal integer,
  fk_registro_laboral integer,
  fk_comunidad integer,
  fecha_creacion timestamp with time zone NOT NULL,
  creado_por character varying(50),
  fecha_ultimo_cambio timestamp with time zone,
  ultimo_cambio_por character varying(50),
  fk_puesto_funcional integer,
  fk_clasificacin_servicio integer,
  CONSTRAINT puesto_pkey PRIMARY KEY (id),
  CONSTRAINT puesto_fk_registro_laboral_fkey FOREIGN KEY (fk_registro_laboral)
      REFERENCES registro_laboral (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
  -- Table: shops

-- DROP TABLE shops;

CREATE TABLE shops
(
  id serial NOT NULL,
  name character varying(100),
  employees_number integer,
  CONSTRAINT shop_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
-- Table: unidad_ejecutora

-- DROP TABLE unidad_ejecutora;

CREATE TABLE unidad_ejecutora
(
   id serial NOT NULL,
  nombre character varying(500),
  estado character varying(50),
  fecha_creacion timestamp with time zone NOT NULL,
  creado_por character varying(50),
  fecha_ultimo_cambio timestamp with time zone,
  ultimo_cambio_por character varying(50),
  CONSTRAINT unidad_ejecutora_pkey2 PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);

  -- Table: unidad_notificadora

-- DROP TABLE unidad_notificadora;

CREATE TABLE unidad_notificadora
(
  id serial NOT NULL,
  valor character varying(500),
  tipo character varying(50),
  estado character varying(50),
  codigo_padre integer,
  fecha_creacion timestamp with time zone NOT NULL,
  creado_por character varying(50),
  fecha_ultimo_cambio timestamp with time zone,
  ultimo_cambio_por character varying(50),
  CONSTRAINT unidad_notificadora_pkey2 PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);