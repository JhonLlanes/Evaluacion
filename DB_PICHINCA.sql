CREATE TABLE cliente (
    clie_id          SERIAL NOT NULL,
    contrasena       VARCHAR(30) NOT NULL,
    estado           VARCHAR(15) NOT NULL,
    persona_per_id   INTEGER NOT NULL
);

ALTER TABLE cliente ADD CONSTRAINT cliente_pk PRIMARY KEY ( clie_id );

CREATE TABLE cuenta (
    cuen_id           SERIAL NOT NULL,
    numero_cuenta     VARCHAR(20) NOT NULL,
    tipo_cuenta       VARCHAR(20) NOT NULL,
    saldo_inicial     NUMERIC(10,2) NOT NULL,
    estado            VARCHAR(15) NOT NULL,
    cliente_clie_id   INTEGER NOT NULL
);

ALTER TABLE cuenta ADD CONSTRAINT cuenta_pk PRIMARY KEY ( cuen_id );

CREATE TABLE movimientos (
    mov_id            SERIAL NOT NULL,
    fecha             DATE NOT NULL,
    tipo_movimiento   VARCHAR(30) NOT NULL,
    valor             NUMERIC(10,2) NOT NULL,
    saldo             NUMERIC(10,2) NOT NULL,
    cuenta_cuen_id    INTEGER NOT NULL
);

ALTER TABLE movimientos ADD CONSTRAINT movimientos_pk PRIMARY KEY ( mov_id );

CREATE TABLE persona (
    per_id           SERIAL NOT NULL,
    nombre           VARCHAR(200) NOT NULL,
    genero           VARCHAR(15) NOT NULL,
    edad             INTEGER NOT NULL,
    identificacion   VARCHAR(20) NOT NULL,
    telefono         VARCHAR(200) NOT NULL
);

ALTER TABLE persona ADD CONSTRAINT persona_pk PRIMARY KEY ( per_id );

ALTER TABLE cliente
    ADD CONSTRAINT cliente_persona_fk FOREIGN KEY ( persona_per_id )
        REFERENCES persona ( per_id );

ALTER TABLE cuenta
    ADD CONSTRAINT cuenta_cliente_fk FOREIGN KEY ( cliente_clie_id )
        REFERENCES cliente ( clie_id );

ALTER TABLE movimientos
    ADD CONSTRAINT movimientos_cuenta_fk FOREIGN KEY ( cuenta_cuen_id )
        REFERENCES cuenta ( cuen_id );

ALTER TABLE persona
   add constraint UQ_identifiacion
   unique (identificacion);
