CREATE TABLE gpx
(
  id                  SERIAL NOT NULL
  PRIMARY KEY,
  uri           VARCHAR(150),
  version           VARCHAR(50),
  creator           VARCHAR(50),
  metadata_id           INTEGER,
  created_at          TIMESTAMP,
  last_modified_at    TIMESTAMP
);

CREATE TABLE users
(
  id                  SERIAL NOT NULL
  PRIMARY KEY,
  first_name           VARCHAR(50),
  last_name           VARCHAR(50),
  email           VARCHAR(150),
  created_at          TIMESTAMP,
  last_modified_at    TIMESTAMP
);

CREATE TABLE metadata
(
  id                  SERIAL NOT NULL
  PRIMARY KEY,
  name           VARCHAR(50),
  description           VARCHAR(2000),
  author           VARCHAR(50),
  keywords          VARCHAR(50),
  link_id           INTEGER,
  user_id            INTEGER,
  time              TIMESTAMP,
  created_at          TIMESTAMP,
  last_modified_at    TIMESTAMP
);

CREATE TABLE way_point
(
  id                  SERIAL NOT NULL
  PRIMARY KEY,
  name           VARCHAR(50),
  sym           VARCHAR(50),
  latitude           DECIMAL(18,10),
  longitude          DECIMAL(18,10),
  gpx_id             INTEGER,
  created_at          TIMESTAMP,
  last_modified_at    TIMESTAMP
);

CREATE TABLE track
(
  id                  SERIAL NOT NULL
  PRIMARY KEY,
  gpx_id                INTEGER,
  created_at          TIMESTAMP,
  last_modified_at    TIMESTAMP
);

CREATE TABLE track_segment
(
  id                  SERIAL NOT NULL
  PRIMARY KEY,
  track_id              INTEGER,
  created_at          TIMESTAMP,
  last_modified_at    TIMESTAMP
);

CREATE TABLE track_point
(
  id                  SERIAL NOT NULL
  PRIMARY KEY,
  latitude           DECIMAL(18,10),
  longitude          DECIMAL(18,10),
  ele                   DECIMAL(18,10),
  time                  TIMESTAMP,
  track_segment_id      INTEGER,
  created_at          TIMESTAMP,
  last_modified_at    TIMESTAMP
);


CREATE TABLE link
(
  id                  SERIAL NOT NULL
  PRIMARY KEY,
  href                   VARCHAR(500),
  text                  VARCHAR(500),
  created_at          TIMESTAMP,
  last_modified_at    TIMESTAMP
);


alter table gpx
	add constraint gpx_metadata_id_fk
		foreign key (metadata_id) references metadata;

alter table metadata
	add constraint metadata_link_id_fk
		foreign key (link_id) references link;

alter table way_point
	add constraint way_point_gpx_id_fk
		foreign key (gpx_id) references gpx;

alter table track
	add constraint track_gpx_id_fk
		foreign key (gpx_id) references gpx;

alter table track_segment
	add constraint track_segment_track_id_fk
		foreign key (track_id) references track;

alter table track_point
	add constraint track_point_track_segment_id_fks
		foreign key (track_segment_id) references track_segment;

alter table metadata
	add constraint metadata_users_id_fk
		foreign key (user_id) references users;