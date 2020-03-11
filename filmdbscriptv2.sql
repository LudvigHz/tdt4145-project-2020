drop schema Film;
create schema Film;
use Film;

CREATE TABLE `Film` (
  `FilmID` int PRIMARY KEY not null AUTO_INCREMENT,
  `tittel` varchar(255) not null,
  `utgivelsesår` int not null,
  `lanseringsdato` date not null,
  `beskrivelse` text
);

CREATE TABLE `Person` (
  `PersonID` int PRIMARY KEY not null auto_increment,
  `navn` varchar(255) not null,
  `fødselsår` int not null,
  `fødselsland` varchar(255) not null
);


CREATE TABLE `FilmSelskap` (
  `FilmSelskapID` int PRIMARY KEY not null auto_increment,
  `navn` varchar(255) not null,
  `URL` varchar(255),
  `addresse` varchar(255) not null,
  `land` varchar(255) not null
);

CREATE TABLE `SkueSpillerIFilm` (
  `FilmID` int not null,
  `PersonID` int not null,
  `rolle` varchar(255) not null,
  foreign key (FilmID)
    references Film (FilmID),
  foreign key (PersonID)
    references Person (PersonID)
);

CREATE TABLE `ManusForfatterIFilm` (
  `FilmID` int not null,
  `PersonID` int not null,
  foreign key (FilmID)
    references Film (FilmID),
  foreign key (PersonID)
    references Person (PersonID)
);

CREATE TABLE `RegissørIFilm` (
  `FilmID` int not null,
  `PersonID` int not null,
  foreign key (FilmID)
    references Film (FilmID),
  foreign key (PersonID)
    references Person (PersonID)
);

CREATE TABLE `Musikk` (
  `MusikkID` int PRIMARY KEY not null AUTO_INCREMENT,
  `komponist` int not null,
  `fremforer` int not null,
  foreign key (Komponist)
    references Person (PersonID),
  foreign key (Fremforer)
    references Person (PersonID)
);

CREATE TABLE `FilmMusikk` (
  `FilmID` int not null,
  `MusikkID` int not null,
  foreign key (FilmID)
    references Film (FilmID),
  foreign key (MusikkID)
    references Musikk (MusikkID)
);

CREATE TABLE `Utgivelse` (
  `FilmID` int PRIMARY KEY not null,
  `FilmSelskapID` int not null,
  foreign key (FilmSelskapID)
    references FilmSelskap (FilmSelskapID)
);

CREATE TABLE `SpilleFilm` (
  `FilmID` int PRIMARY KEY not null,
  `utgittPåVideo` boolean not null,
  `lengde` int not null,
  foreign key (FilmID)
    references Film (FilmID)
);

CREATE TABLE `Serie` (
  `FilmID` int PRIMARY KEY not null,
  foreign key (FilmID)
    references Film (FilmID)
);

CREATE TABLE `Sesong` (
   PRIMARY KEY (FilmID, sesongNr),
  `FilmID` int not null,
  `sesongNr` int not null,
  foreign key (FilmID)
    references Film (FilmID)
);

CREATE TABLE `Episode` (
  `FilmID` int not null,
  `episodeNr` int not null,
  `sesong` int not null,
  `lengde` int not null,
  foreign key (FilmID)
    references Film (FilmID)
);

CREATE TABLE `Kategori` (
  `KategoriID` int PRIMARY KEY not null auto_increment,
  `kategori` varchar(255) not null
);

CREATE TABLE `KategoriIFilm` (
  `FilmID` int not null,
  `KategoriID` int not null,
  foreign key (FilmID)
    references Film (FilmID),
  foreign key (KategoriID)
    references Kategori (KategoriID)
);


CREATE TABLE `Bruker` (
  `BrukerID` int PRIMARY KEY not null auto_increment,
  `brukernavn` varchar(255) not null
);

CREATE TABLE `Kommentar` (
  `KommentarID` int PRIMARY KEY not null auto_increment,
  `tekst` text not null,
  `BrukerID` int not null,
  `FilmID` int not null,
  foreign key (BrukerID)
    references Bruker (BrukerID),
  foreign key (FilmID)
    references Film (FilmID)
);

CREATE TABLE `Anmeldelse` (
  `AnmeldelseID` int PRIMARY KEY not null auto_increment,
  `rating` int not null,
  `tekst` text,
  `FilmID` int not null,
  `BrukerID` int not null,
  foreign key (BrukerID)
    references Bruker (BrukerID),
  foreign key (FilmID)
    references Film (FilmID)
);

select * from Film;