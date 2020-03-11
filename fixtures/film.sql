use Film;

insert ignore into Film(FilmID, tittel, `utgivelsesår`, lanseringsdato, beskrivelse) values (1, 'The godfather', '1972', '1972-03-25', 'The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.');
insert ignore into Film(FilmID, tittel, `utgivelsesår`, lanseringsdato, beskrivelse) values (2, 'Pulp fiction', '1994', '1994-10-14', 'The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption. ');
insert ignore into Film(FilmID, tittel, `utgivelsesår`, lanseringsdato, beskrivelse) values (3, 'Fight Club', '1999', '1999-10-15', 'An insomniac office worker and a devil-may-care soapmaker form an underground fight club that evolves into something much, much more.');
insert ignore into Film(FilmID, tittel, `utgivelsesår`, lanseringsdato, beskrivelse) values (4, 'Game of Thrones', '2011', '2011-11-11', 'In the fictional land of westeros nine great houses battle for control');


insert ignore into Utgivelse(FilmID, FilmSelskapID) values(1, 1);
insert ignore into Utgivelse(FilmID, FilmSelskapID) values(2, 3);
insert ignore into Utgivelse(FilmID, FilmSelskapID) values(3, 3);
insert ignore into Utgivelse(FilmID, FilmSelskapID) values(4, 1);


insert ignore into SpilleFilm(FilmID, `utgittPåVIDeo`, lengde) values(1, true, 120);
insert ignore into SpilleFilm(FilmID, `utgittPåVIDeo`, lengde) values(2, true, 110);
insert ignore into SpilleFilm(FilmID, `utgittPåVIDeo`, lengde) values(3, true, 100);

insert ignore into KategoriIFilm(FilmID, KategoriID) values(1, 1);
insert ignore into KategoriIFilm(FilmID, KategoriID) values(2, 1);
insert ignore into KategoriIFilm(FilmID, KategoriID) values(3, 3);
insert ignore into KategoriIFilm(FilmID, KategoriID) values(3, 2);
insert ignore into KategoriIFilm(FilmID, KategoriID) values(2, 2);

insert ignore into Serie(FilmID) values(4);

insert ignore into Sesong values(4,1, true);
insert ignore into Sesong values(4,2, true);
insert ignore into Sesong values(4,3, true);
insert ignore into Sesong values(4,4, false);

insert ignore into SkueSpillerIFilm values(2, 7, "Jules Winnfield");
insert ignore into SkueSpillerIFilm values(2, 8, "Vincent Vega");

insert ignore into RegissørIFilm values(2, 9);
insert ignore into ManusForfatterIFilm values(2, 9);
insert ignore into ManusForfatterIFilm values(2, 12);

insert ignore into Film values (5, "Winter is coming", '2011', '2011-04-17', "Eddard Stark is torn between his family and an old friend when asked to serve at the side of King Robert Baratheon; Viserys plans to wed his sister to a nomadic warlord in exchange for an army.");
insert ignore into Film values (6, "The Kingsroad", '2011', '2011-04-24', "While Bran recovers from his fall, Ned takes only his daughters to King's Landing. Jon Snow goes with his uncle Benjen to the Wall. Tyrion joins them.");

insert ignore into Episode values (5, 1, 1, 60, 4);
insert ignore into Episode values (6, 2, 1, 60, 4);

insert ignore into SkueSpillerIFilm values(4, 3, "Toromund Giantsman");
insert ignore into SkueSpillerIFilm values(4, 5, "Jaime Lannister");
insert ignore into SkueSpillerIFilm values(4, 6, "Tywin Lannister");
