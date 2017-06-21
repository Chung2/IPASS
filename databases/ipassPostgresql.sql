--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.3
-- Dumped by pg_dump version 9.6.3

-- Started on 2017-06-21 18:38:17

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 187 (class 1259 OID 16568)
-- Name: resultaat; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE resultaat (
    id_ronde integer NOT NULL,
    winnaar integer,
    id_speler integer NOT NULL
);


--
-- TOC entry 191 (class 1259 OID 16693)
-- Name: ronde_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE ronde_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 185 (class 1259 OID 16552)
-- Name: ronde; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE ronde (
    id_ronde integer DEFAULT nextval('ronde_id_seq'::regclass) NOT NULL,
    id_spel integer,
    tijd time without time zone,
    notities text,
    naam character varying NOT NULL
);


--
-- TOC entry 190 (class 1259 OID 16690)
-- Name: spel_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE spel_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 186 (class 1259 OID 16560)
-- Name: spel; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE spel (
    id_spel integer DEFAULT nextval('spel_id_seq'::regclass) NOT NULL,
    naam character varying,
    instructies text
);


--
-- TOC entry 189 (class 1259 OID 16687)
-- Name: speler_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE speler_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 188 (class 1259 OID 16573)
-- Name: speler; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE speler (
    id_speler integer DEFAULT nextval('speler_id_seq'::regclass) NOT NULL,
    naam character varying,
    wachtwoord character varying,
    rol character varying
);


--
-- TOC entry 2156 (class 0 OID 16568)
-- Dependencies: 187
-- Data for Name: resultaat; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO resultaat VALUES (1, 1, 1);
INSERT INTO resultaat VALUES (4, 1, 1);
INSERT INTO resultaat VALUES (1, 1, 2);
INSERT INTO resultaat VALUES (1, 1, 6);
INSERT INTO resultaat VALUES (4, 1, 6);
INSERT INTO resultaat VALUES (1, 1, 7);
INSERT INTO resultaat VALUES (4, 1, 7);
INSERT INTO resultaat VALUES (5, 3, 1);
INSERT INTO resultaat VALUES (5, 3, 2);
INSERT INTO resultaat VALUES (5, 3, 3);
INSERT INTO resultaat VALUES (3, 6, 1);
INSERT INTO resultaat VALUES (3, 6, 2);
INSERT INTO resultaat VALUES (3, 6, 6);
INSERT INTO resultaat VALUES (2, 7, 1);
INSERT INTO resultaat VALUES (2, 7, 3);
INSERT INTO resultaat VALUES (6, 3, 2);
INSERT INTO resultaat VALUES (6, 3, 3);
INSERT INTO resultaat VALUES (6, 3, 4);
INSERT INTO resultaat VALUES (7, 3, 1);
INSERT INTO resultaat VALUES (7, 3, 2);
INSERT INTO resultaat VALUES (7, 3, 3);
INSERT INTO resultaat VALUES (8, 7, 1);
INSERT INTO resultaat VALUES (8, 7, 2);
INSERT INTO resultaat VALUES (8, 7, 7);
INSERT INTO resultaat VALUES (9, 1, 1);
INSERT INTO resultaat VALUES (9, 1, 2);
INSERT INTO resultaat VALUES (9, 1, 6);


--
-- TOC entry 2154 (class 0 OID 16552)
-- Dependencies: 185
-- Data for Name: ronde; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO ronde VALUES (1, 2, '13:43:05', 'String', 'ronde1');
INSERT INTO ronde VALUES (2, 3, '00:00:06', 'sS', 'ronde2');
INSERT INTO ronde VALUES (3, 2, '00:00:16', 'gjhgjklhgfd', 'ronde1dfghjk');
INSERT INTO ronde VALUES (4, 5, '00:00:26', '', 'rondeNaam2');
INSERT INTO ronde VALUES (5, 5, '00:00:02', 'nog geen notities', 'Chungqert');
INSERT INTO ronde VALUES (6, 2, '00:00:07', 'nog geen notities', 'Chung24tew');
INSERT INTO ronde VALUES (7, 5, '00:00:11', 'TEST WAAROM WERKT HET NIET MET POSTGRES', 'qweretytujngfb');
INSERT INTO ronde VALUES (8, 7, '00:01:33', 'ITS ALIVE!!!', 'ronde1234568876536');
INSERT INTO ronde VALUES (9, 5, '00:00:42', 'asdfdgfddafg df daf g', 'ronde1234567uhf');


--
-- TOC entry 2166 (class 0 OID 0)
-- Dependencies: 191
-- Name: ronde_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('ronde_id_seq', 9, true);


--
-- TOC entry 2155 (class 0 OID 16560)
-- Dependencies: 186
-- Data for Name: spel; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO spel VALUES (1, 'Kies een spel!', 'Kies een spel!');
INSERT INTO spel VALUES (2, 'Sh*t Head', 'Hier in komt de Instructies voor het spel Sh*t Head');
INSERT INTO spel VALUES (3, 'Pesten', 'Hier in komt de Instructies voor het spel Pesten');
INSERT INTO spel VALUES (4, 'Paardenrace', 'Hier in komt de Instructies voor het spel Paardenrace');
INSERT INTO spel VALUES (5, 'Oorlogje', 'Alle kaarten worden geschud en verdeeld onder de spelers. De speler naast de deler mag het spel beginnen. Iedereen heeft zijn kaarten in een dichte stapel voor zich op tafel liggen. Om de beurt moet iedere speler de bovenste kaart van zijn stapel omdraaien. Speluitleg Oorlogje spelenDe speler die de hoogste kaart heeft mag alle omgedraaide kaarten pakken en in en dichte stapel naast zich leggen. Wanneer er een 2, 6, boer en Aas zijn opgelegt heeft de speler die de Aas oplegde deze ronde dus gewonnen. Wanneer je stapel op is dan moet je de kaarten die je gewonnen hebt schudden en weer voor je leggen in een dichte stapel, hier speel je dan me verder. Wanneer een speler door al zijn kaarten heen is, is hij dood en ligt hij uit het spel. Diegene die als laatste overblijft is de overlever en heeft het spel gewonnen. De joker is altijd de hoogste kaart in het spel. Maar je kunt bijvoorbeeld ook van te voren afspreken dat de 8 de Joker verslaat, dit maakt het dan nog net wat spannender. Wanneer er 2 spelers zijn die allebei de hoogste kaart opleggen dan moet ze beide nog een gesloten kaart en dan weer een open kaart opleggen. Diegene die dan de hoogste kaart heeft opgelegt wint het spel. Hetzelfde geldt als het spel met 2 spelers gespeeld wordt. Als de spelers allebei dezelfde kaart opleggen moeten ze eerst een gesloten kaart opleggen gevolgd door een open kaart. De hoogste kaart wint. Lees meer: http://www.speluitleg.com/spelregels-oorlogje/');
INSERT INTO spel VALUES (7, 'Ezelen', 'Ezelen is een leuk kaartspel voor 4 tot 8 spelers met als doel om 4 dezelfde kaarten in je hand te krijgen. Van te voren spreken de spelers een gebaar af dat aangeeft dat iemand 4 dezelfde kaarten heeft. Wanneer iemand dat gebaar maakt geeft dat dus aan dat hij/zij 4 dezelfde kaarten heeft en dan moet er gereageerd worden. De speler die als laatste reageerd krijgt een letter toegekent. De eerste letter is de E van ezel. Dit gaat zo door totdat iemand alle letters van het woord ezel heeft, wanneer dit zo is is de speler de EZEL. Vaak gaat dit dan gepaard met leuke opdrachten die gedaan worden. Er is ook een echt kaartspel uitgekomen dat Ezelen heet. In dit spel zitten 4 Ezels, deze moeten zo snel mogelijk gepakt worden wanneer er iemand 4 dezelde kaarten heeft. Als je te laat bent bent, ben je de Ezel! De kaarten hebben vrolijke afbeeldingen van dieren. Maar Ezelen kan ook gespeeld worden met een normaal set speelkaarten.Speluitleg Ezelen kaarten
Benodigdheden :

De kaarten

Pen en papier　
Spelverloop

Het spel begint met het sorteren van de kaarten. Wanneer er met 4 spelers gespeeld wordt moeten alle Azen, Vrouwen, en boeren uit het kaartspel gehaald worden, alleen met deze kaarten wordt het spel gespeeld.

Vervolgens worden de kaarten goed geschud en verdeeld onder de 4 spelers. De spelers beslissen dan ieder voor zich welke kaarten ze willen verzamelen, en uiteindelijk 4 dezelfde kaarten te krijgen. De spelers schuiven dan om de beurt telkens een kaart die ze niet nodig hebben naar de speler links van hem. Dit gaat net zolang door totdat een speler 4 dezelfde kaarten heeft. Wanneer dit het geval is moet het gebaar dat van te voren afgesproken is gemaakt worden. Dit kan bijvoorbeeld een hand op tafel plaatsen zijn of 1 keer in je hand klappen of je hand opsteken, het kan vanalles zijn. Als het maar goed wordt afgesproken van te voren zodat er geen misverstanden ontstaan. De speler die het gebaar als laatste gemaakt heeft krijg dan de letter E. De volgende keer als dezelfde speler verliest krijg hij dus de letter Z, dit gaat zo door totdat iemand de EZEL is. De ezel moet dan een opdracht uitvoeren. Deze kan van te voren zijn afgesproken of erna verzonnen worden.


Lees meer: http://www.speluitleg.com/spelregels-ezelen/');


--
-- TOC entry 2167 (class 0 OID 0)
-- Dependencies: 190
-- Name: spel_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('spel_id_seq', 7, true);


--
-- TOC entry 2157 (class 0 OID 16573)
-- Dependencies: 188
-- Data for Name: speler; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO speler VALUES (1, 'Chung', 'test', 'admin');
INSERT INTO speler VALUES (2, 'Ward', 'test', 'admin');
INSERT INTO speler VALUES (3, 'Henkie', 'test', 'user');
INSERT INTO speler VALUES (4, 'Klaas', 'test', 'user');
INSERT INTO speler VALUES (5, 'Geen speler', 'test', 'user');
INSERT INTO speler VALUES (6, 'Arnoud', 'test', 'user');
INSERT INTO speler VALUES (7, 'Michiel', 'test', 'user');


--
-- TOC entry 2168 (class 0 OID 0)
-- Dependencies: 189
-- Name: speler_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('speler_id_seq', 7, true);


--
-- TOC entry 2025 (class 2606 OID 16559)
-- Name: ronde ronde_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY ronde
    ADD CONSTRAINT ronde_pkey PRIMARY KEY (id_ronde);


--
-- TOC entry 2027 (class 2606 OID 16567)
-- Name: spel spel_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY spel
    ADD CONSTRAINT spel_pkey PRIMARY KEY (id_spel);


--
-- TOC entry 2032 (class 2606 OID 16580)
-- Name: speler speler_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY speler
    ADD CONSTRAINT speler_pkey PRIMARY KEY (id_speler);


--
-- TOC entry 2028 (class 1259 OID 16591)
-- Name: FKI_idspeler; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX "FKI_idspeler" ON resultaat USING btree (winnaar);


--
-- TOC entry 2023 (class 1259 OID 16674)
-- Name: fki_fk_id_spel; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_fk_id_spel ON ronde USING btree (id_spel);


--
-- TOC entry 2029 (class 1259 OID 16662)
-- Name: fki_fk_ronde; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_fk_ronde ON resultaat USING btree (id_ronde);


--
-- TOC entry 2030 (class 1259 OID 16668)
-- Name: fki_fk_speler; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_fk_speler ON resultaat USING btree (id_speler);


--
-- TOC entry 2034 (class 2606 OID 16586)
-- Name: resultaat FK_idspeler; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY resultaat
    ADD CONSTRAINT "FK_idspeler" FOREIGN KEY (winnaar) REFERENCES speler(id_speler);


--
-- TOC entry 2036 (class 2606 OID 16675)
-- Name: resultaat fk_ronde; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY resultaat
    ADD CONSTRAINT fk_ronde FOREIGN KEY (id_ronde) REFERENCES ronde(id_ronde) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2033 (class 2606 OID 16680)
-- Name: ronde fk_spel; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY ronde
    ADD CONSTRAINT fk_spel FOREIGN KEY (id_spel) REFERENCES spel(id_spel) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2035 (class 2606 OID 16663)
-- Name: resultaat fk_speler; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY resultaat
    ADD CONSTRAINT fk_speler FOREIGN KEY (id_speler) REFERENCES speler(id_speler);


-- Completed on 2017-06-21 18:38:17

--
-- PostgreSQL database dump complete
--

