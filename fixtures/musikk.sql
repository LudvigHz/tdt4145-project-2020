use Film;

insert ignore into Musikk(MusikkID, komponist, fremforer) values (1, 1, 2);
insert ignore into Musikk(MusikkID, komponist, fremforer) values (2, 4, 3);
insert ignore into Musikk(MusikkID, komponist, fremforer) values (3, 10, 11);

insert ignore into FilmMusikk(MusikkID, FilmID) values (1, 1);
insert ignore into FilmMusikk(MusikkID, FilmID) values (2, 3);
insert ignore into FilmMusikk(MusikkID, FilmID) values (3, 2);
