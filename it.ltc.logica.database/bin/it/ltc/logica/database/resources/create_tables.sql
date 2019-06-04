CREATE TABLE IF NOT EXISTS listino_simulazione ( 
	id INTEGER PRIMARY KEY, 
	tipo INTEGER NOT NULL, 
	nome text NOT NULL, 
	descrizione text ); 
CREATE TABLE IF NOT EXISTS listino_simulazione_voce ( 
	id INTEGER PRIMARY KEY, id_listino INTEGER NOT NULL REFERENCES listino_simulazione(id) ON UPDATE CASCADE ON DELETE CASCADE, 
	id_sotto_ambito INTEGER NOT NULL, 
	valore_sotto_ambito text,
	tipo_calcolo text NOT NULL,
	strategia_calcolo text,
	nome text NOT NULL,
	descrizione text ); 
CREATE TABLE IF NOT EXISTS listino_simulazione_voce_fissa ( 
	id_voce INTEGER NOT NULL REFERENCES listino_simulazione_voce(id) ON UPDATE CASCADE ON DELETE CASCADE, 
	valore REAL NOT NULL ); 
CREATE TABLE IF NOT EXISTS listino_simulazione_voce_percentuale (
	id_voce INTEGER NOT NULL REFERENCES listino_simulazione_voce(id) ON UPDATE CASCADE ON DELETE CASCADE, 
	valore REAL NOT NULL,
	valore_minimo REAL,
	valore_massimo REAL ); 
CREATE TABLE IF NOT EXISTS listino_simulazione_voce_proporzionale ( 
	id_voce INTEGER NOT NULL REFERENCES listino_simulazione_voce(id) ON UPDATE CASCADE ON DELETE CASCADE, 
	valore REAL NOT NULL, 
	valore_minimo REAL, 
	valore_massimo REAL ); 
CREATE TABLE IF NOT EXISTS listino_simulazione_voce_scaglioni (
	id_voce INTEGER NOT NULL REFERENCES listino_simulazione_voce(id) ON UPDATE CASCADE ON DELETE CASCADE, 
	valore REAL NOT NULL, 
	inizio REAL NOT NULL, 
	fine REAL NOT NULL, PRIMARY KEY (id_voce, inizio, fine) ); 
CREATE TABLE IF NOT EXISTS listino_simulazione_voce_scaglioni_ripetuti (
	id_voce INTEGER NOT NULL REFERENCES listino_simulazione_voce(id) ON UPDATE CASCADE ON DELETE CASCADE, 
	valore REAL NOT NULL, 
	intervallo REAL NOT NULL, 
	minimo REAL, 
	massimo REAL ); 
CREATE TABLE IF NOT EXISTS resource_to_delete ( name text PRIMARY KEY ); 
CREATE TABLE IF NOT EXISTS utente ( 
	name text PRIMARY KEY, 
	password text NOT NULL, 
	ultimo_login datetime NOT NULL );
CREATE TABLE IF NOT EXISTS proprieta_logica ( key text PRIMARY KEY, value text NOT NULL );
CREATE TABLE IF NOT EXISTS versione_tabella ( name VARCHAR (50) PRIMARY KEY, data_versione datetime NOT NULL );
CREATE TABLE IF NOT EXISTS indirizzo (
	id INTEGER NOT NULL PRIMARY KEY,
	cap VARCHAR (10) NOT NULL,
	consegna_al_piano BOOLEAN NOT NULL,
	consegna_appuntamento BOOLEAN NOT NULL,
	consegna_gdo BOOLEAN NOT NULL,
	consegna_privato BOOLEAN NOT NULL,
	email VARCHAR (100),
	indirizzo VARCHAR (100) NOT NULL,
	localita VARCHAR (45) NOT NULL,
	nazione VARCHAR (3) NOT NULL,
	provincia VARCHAR (2) NOT NULL,
	ragione_sociale VARCHAR (200) NOT NULL,
	data_ultima_modifica datetime NOT NULL,
	telefono VARCHAR (30) );
CREATE INDEX IF NOT EXISTS idx_indirizzo_ragione_sociale ON indirizzo (ragione_sociale);
CREATE TABLE IF NOT EXISTS indirizzo_simulazione (
	id INTEGER PRIMARY KEY,
	cap VARCHAR (10) NOT NULL,
	consegna_al_piano BOOLEAN NOT NULL,
	consegna_appuntamento BOOLEAN NOT NULL,
	consegna_gdo BOOLEAN NOT NULL,
	consegna_privato BOOLEAN NOT NULL,
	email VARCHAR (100),
	indirizzo VARCHAR (100) NOT NULL,
	localita VARCHAR (45) NOT NULL,
	nazione VARCHAR (3) NOT NULL,
	provincia VARCHAR (2) NOT NULL,
	ragione_sociale VARCHAR (200) NOT NULL,
	telefono VARCHAR (30) );
CREATE TABLE IF NOT EXISTS cap (
	cap VARCHAR (5) NOT NULL,
	localita VARCHAR (200) NOT NULL,
	brt_disagiate BOOLEAN NOT NULL,
	brt_isole BOOLEAN NOT NULL,
	brt_ztl BOOLEAN NOT NULL,
	tnt_ore_dieci BOOLEAN NOT NULL,
	tnt_ore_dodici BOOLEAN NOT NULL,
	provincia VARCHAR (2) NOT NULL,
	regione VARCHAR (3) NOT NULL,
	data_ultima_modifica datetime NOT NULL,
	PRIMARY KEY (cap, localita) );
CREATE INDEX IF NOT EXISTS idx_cap_cap ON cap (cap);
CREATE INDEX IF NOT EXISTS idx_cap_localita ON cap (localita);
CREATE TABLE IF NOT EXISTS spedizione (
	id INTEGER NOT NULL PRIMARY KEY,
	assicurazione BOOLEAN NOT NULL,
	codice_cliente VARCHAR (50) NOT NULL,
	colli INTEGER NOT NULL,
	contrassegno BOOLEAN NOT NULL,
	costo REAL,
	data_partenza datetime NOT NULL,
	data_ultima_modifica datetime NOT NULL,
	dati_completi BOOLEAN NOT NULL,
	fatturazione VARCHAR (20) NOT NULL,
	giacenza BOOLEAN NOT NULL,
	id_commessa INTEGER NOT NULL,
	id_corriere INTEGER NOT NULL,
	id_documento INTEGER NOT NULL,
	in_ritardo BOOLEAN NOT NULL,
	indirizzo_destinazione INTEGER NOT NULL,
	indirizzo_partenza INTEGER NOT NULL,
	lettera_di_vettura VARCHAR (45) NOT NULL,
	note text,
	particolarita BOOLEAN NOT NULL,
	peso REAL NOT NULL,
	pezzi INTEGER NOT NULL,
	ricavo REAL,
	riferimento_cliente VARCHAR (45),
	riferimento_mittente VARCHAR (45),
	servizio VARCHAR (3) NOT NULL,
	stato VARCHAR (3) NOT NULL,
	tipo VARCHAR (20) NOT NULL,
	valore_merce_dichiarato REAL,
	volume REAL NOT NULL,
	ragione_sociale_destinatario  VARCHAR (100) );
CREATE INDEX IF NOT EXISTS idx_spedizione_data_partenza ON spedizione (data_partenza);
CREATE INDEX IF NOT EXISTS idx_spedizione_lettera_di_vettura ON spedizione (lettera_di_vettura);
CREATE INDEX IF NOT EXISTS idx_spedizione_riferimento_cliente ON spedizione (riferimento_cliente);
CREATE INDEX IF NOT EXISTS idx_spedizione_ragione_sociale_destinatario ON spedizione (ragione_sociale_destinatario);
CREATE TABLE IF NOT EXISTS spedizione_giacenza (
	id INTEGER NOT NULL PRIMARY KEY,
	costo REAL,
	ricavo REAL,
	data_apertura datetime,
	data_chiusura datetime,
	data_ultima_modifica datetime NOT NULL,
	fatturazione VARCHAR (20) NOT NULL,
	id_commessa INTEGER NOT NULL,
	id_destinatario INTEGER NOT NULL,
	id_mittente INTEGER NOT NULL,
	id_documento INTEGER NOT NULL,
	id_spedizione INTEGER NOT NULL REFERENCES spedizione(id) ON UPDATE CASCADE ON DELETE CASCADE,
	lettera_di_vettura VARCHAR (45) NOT NULL,
	lettera_di_vettura_originale VARCHAR (45) NOT NULL,
	note text );
CREATE INDEX IF NOT EXISTS idx_spedizione_giacenza_data_apertura ON spedizione_giacenza (data_apertura);
CREATE INDEX IF NOT EXISTS idx_spedizione_giacenza_data_chiusura ON spedizione_giacenza (data_chiusura);
CREATE INDEX IF NOT EXISTS idx_spedizione_giacenza_lettera_di_vettura ON spedizione_giacenza (lettera_di_vettura);
CREATE INDEX IF NOT EXISTS idx_spedizione_giacenza_lettera_di_vettura_originale ON spedizione_giacenza (lettera_di_vettura_originale);
CREATE TABLE IF NOT EXISTS spedizione_contrassegno (
	id_spedizione INTEGER NOT NULL REFERENCES spedizione(id) ON UPDATE CASCADE ON DELETE CASCADE,
	annullato BOOLEAN NOT NULL,
	tipo VARCHAR (2) NOT NULL,
	valore REAL NOT NULL,
	valuta VARCHAR (3) NOT NULL,
	data_ultima_modifica datetime NOT NULL );
CREATE TABLE IF NOT EXISTS spedizione_simulazione_documento (
	id INTEGER PRIMARY KEY,
	tipo VARCHAR(5) NOT NULL,
	nome_file VARCHAR(100) NOT NULL,
	data_importazione datetime NOT NULL,
	descrizione text );
CREATE TABLE IF NOT EXISTS spedizione_simulazione (
	id INTEGER PRIMARY KEY,
	assicurazione BOOLEAN NOT NULL,
	codice_cliente VARCHAR (50) NOT NULL,
	colli INTEGER NOT NULL,
	contrassegno BOOLEAN NOT NULL,
	costo REAL,
	data_partenza datetime NOT NULL,
	dati_completi BOOLEAN NOT NULL,
	giacenza BOOLEAN NOT NULL,
	id_commessa INTEGER NOT NULL,
	id_corriere INTEGER NOT NULL,
	id_documento INTEGER NOT NULL,
	in_ritardo BOOLEAN NOT NULL,
	indirizzo_destinazione INTEGER NOT NULL,
	indirizzo_partenza INTEGER NOT NULL,
	lettera_di_vettura VARCHAR (45) NOT NULL,
	note text,
	particolarita BOOLEAN NOT NULL,
	peso REAL NOT NULL,
	pezzi INTEGER NOT NULL,
	ricavo REAL,
	riferimento_cliente VARCHAR (45),
	riferimento_mittente VARCHAR (45),
	servizio VARCHAR (3) NOT NULL,
	stato VARCHAR (3) NOT NULL,
	tipo VARCHAR (20) NOT NULL,
	valore_merce_dichiarato REAL,
	volume REAL NOT NULL,
	ragione_sociale_destinatario  VARCHAR (100) );
CREATE INDEX IF NOT EXISTS idx_spedizione_simulazione_data_partenza ON spedizione_simulazione (data_partenza);
CREATE INDEX IF NOT EXISTS idx_spedizione_simulazione_lettera_di_vettura ON spedizione_simulazione (lettera_di_vettura);
CREATE INDEX IF NOT EXISTS idx_spedizione_simulazione_riferimento_cliente ON spedizione_simulazione (riferimento_cliente);
CREATE INDEX IF NOT EXISTS idx_spedizione_simulazione_ragione_sociale_destinatario ON spedizione_simulazione (ragione_sociale_destinatario);
CREATE TABLE IF NOT EXISTS spedizione_simulazione_giacenza (
	id INTEGER NOT NULL PRIMARY KEY,
	costo REAL,
	ricavo REAL,
	data_apertura datetime,
	data_chiusura datetime,
	fatturazione VARCHAR (20) NOT NULL,
	id_commessa INTEGER NOT NULL,
	id_destinatario INTEGER NOT NULL,
	id_mittente INTEGER NOT NULL,
	id_documento INTEGER NOT NULL,
	id_spedizione INTEGER NOT NULL REFERENCES spedizione(id) ON UPDATE CASCADE ON DELETE CASCADE,
	lettera_di_vettura VARCHAR (45) NOT NULL,
	lettera_di_vettura_originale VARCHAR (45) NOT NULL,
	note text );
CREATE INDEX IF NOT EXISTS idx_spedizione_simulazione_giacenza_data_apertura ON spedizione_simulazione_giacenza (data_apertura);
CREATE INDEX IF NOT EXISTS idx_spedizione_simulazione_giacenza_data_chiusura ON spedizione_simulazione_giacenza (data_chiusura);
CREATE INDEX IF NOT EXISTS idx_spedizione_simulazione_giacenza_lettera_di_vettura ON spedizione_simulazione_giacenza (lettera_di_vettura);
CREATE INDEX IF NOT EXISTS idx_spedizione_simulazione_giacenza_lettera_di_vettura_originale ON spedizione_simulazione_giacenza (lettera_di_vettura_originale);
CREATE TABLE IF NOT EXISTS spedizione_simulazione_contrassegno (
	id_spedizione INTEGER NOT NULL REFERENCES spedizione(id) ON UPDATE CASCADE ON DELETE CASCADE,
	annullato BOOLEAN NOT NULL,
	tipo VARCHAR (2) NOT NULL,
	valore REAL NOT NULL,
	valuta VARCHAR (3) NOT NULL );
CREATE TABLE IF NOT EXISTS prodotto (
	id INTEGER NOT NULL,
	commessa INTEGER NOT NULL,
	cassa VARCHAR(10),
	chiave_cliente VARCHAR(50) NOT NULL,
	codice_modello VARCHAR(50) NOT NULL,
	barcode VARCHAR(50) NOT NULL,
	taglia VARCHAR(15) NOT NULL,
	colore VARCHAR(50),
	descrizione VARCHAR(150),
	descrizione_aggiuntiva VARCHAR(250),
	composizione VARCHAR(50),
	brand VARCHAR(50),
	categoria VARCHAR(20) NOT NULL,
	made_in VARCHAR(3),
	sotto_categoria VARCHAR(30),
	stagione VARCHAR(4),
	valore REAL,
	h INTEGER,
	l INTEGER,
	z INTEGER,
	peso INTEGER,
	sku_fornitore VARCHAR(50),
	barcode_fornitore VARCHAR(50),
	note VARCHAR(250),
	particolarita VARCHAR(50),
	data_ultima_modifica datetime NOT NULL,
	PRIMARY KEY (id, commessa)
);
CREATE INDEX IF NOT EXISTS idx_prodotto_commessa ON prodotto (commessa);
CREATE INDEX IF NOT EXISTS idx_prodotto_data_ultima_modifica ON prodotto (data_ultima_modifica);
CREATE INDEX IF NOT EXISTS idx_prodotto_aggiornamento_commessa ON prodotto (data_ultima_modifica, commessa);
CREATE INDEX IF NOT EXISTS idx_prodotto_chiaveCliente ON prodotto (chiave_cliente);
CREATE INDEX IF NOT EXISTS idx_prodotto_codiceModello ON prodotto (codice_modello);
CREATE INDEX IF NOT EXISTS idx_prodotto_barcode ON prodotto (barcode);
CREATE INDEX IF NOT EXISTS idx_prodotto_categoria ON prodotto (categoria);
