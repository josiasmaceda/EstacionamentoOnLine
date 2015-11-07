CREATE TABLE "USUARIO"
(    
   "ID" INT not null primary key

        GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),   
   "NOME" VARCHAR(20),     
   "LOGIN" VARCHAR(20),
   "SENHA" VARCHAR(20)
);

CREATE TABLE "CONFIGURACAO"
(    
   "ID" INT not null primary key

        GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),   
   "VALOR_HORA" NUMERIC(10,2),     
   "VALOR_DIARIA" NUMERIC(10,2),
   "NUM_HORAS_DIARIA" INTEGER
);

CREATE TABLE "VEICULO"
(    
   "ID" INT not null primary key

        GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),   
   "PLACA" VARCHAR(8),
   "TIPO" INTEGER
);

CREATE TABLE "MENSALISTA"
(    
   "ID" INT not null primary key

        GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),   
   "NOME" VARCHAR(50)
);

ALTER TABLE CARRO ADD IDMENSALISTA INT;
ALTER TABLE MOVIMENTO ADD IDMENSALISTA INT;
ALTER TABLE MOVIMENTO ADD VALOR_HORA NUMERIC(10,2);
ALTER TABLE MOVIMENTO ADD VALOR_DIARIA NUMERIC(10,2);
ALTER TABLE MOVIMENTO ADD VALOR_PAGO NUMERIC(10,2);

