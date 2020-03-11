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

insert ignore into Kategori(KategoriID, kategori) values(1, "Thriller");
insert ignore into Kategori(KategoriID, kategori) values(2, "Action");
insert ignore into Kategori(KategoriID, kategori) values(3, "Drama");
insert ignore into Kategori(KategoriID, kategori) values(4, "Comedy");

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
