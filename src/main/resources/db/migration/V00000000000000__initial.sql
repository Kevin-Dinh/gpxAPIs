CREATE TABLE gpx
(
  id                  SERIAL NOT NULL
  PRIMARY KEY,
  name           VARCHAR(50),
  description           VARCHAR(50),
  author           VARCHAR(50),
  link           VARCHAR(50),
  time           TIMESTAMP,
  way_point_id           INTEGER,
  track_id              INTEGER,
  created_at          TIMESTAMP,
  last_modified_at    TIMESTAMP
);

CREATE TABLE way_point
(
  id                  SERIAL NOT NULL
  PRIMARY KEY,
  name           VARCHAR(50),
  latitude           DECIMAL(18,10),
  longitude          DECIMAL(18,10),
  created_at          TIMESTAMP,
  last_modified_at    TIMESTAMP
);

CREATE TABLE track
(
  id                  SERIAL NOT NULL
  PRIMARY KEY,
  name           VARCHAR(50),
  element           FLOAT,
  time                TIMESTAMP,
  latitude           DECIMAL(18,10),
  longitude          DECIMAL(18,10),
  created_at          TIMESTAMP,
  last_modified_at    TIMESTAMP
);

alter table gpx
	add constraint gpx_track_id_fk
		foreign key (track_id) references track;

alter table gpx
	add constraint gpx_way_point_id_fk
		foreign key (way_point_id) references way_point;
