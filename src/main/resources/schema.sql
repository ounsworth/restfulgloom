
create table users(
	username varchar_ignorecase(50) not null primary key,
	password varchar_ignorecase(60) not null,
	enabled boolean not null
);

create table authorities (
	username varchar_ignorecase(50) not null,
	authority varchar_ignorecase(50) not null,
	constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);

create table CLASSES(
	class_id BIGINT not null AUTO_INCREMENT,
	primary key(class_id)
);

create table CHARACTERS (
	character_id BIGINT not null AUTO_INCREMENT,
	health int not null,
	exp int not null,
	gold int not null,
	check_marks int not null,
	name varchar not null,
	class_id BIGINT not null,
	primary key(character_id),
	constraint FK_C_CLASS foreign key (class_id) references CLASSES (class_id)
);

create table ITEMS(
	item_id BIGINT not null AUTO_INCREMENT,
	primary key(item_id)
);

create table ABILITIES(
	ability_id BIGINT not null AUTO_INCREMENT,
	primary key(ability_id)
);

create table PERKS(
	perk_id BIGINT not null AUTO_INCREMENT,
	primary key(perk_id)
);

create table NOTES(
	note_id BIGINT not null AUTO_INCREMENT,
	note CLOB not null,
	primary key(note_id)
);

create table CHARACTER_ITEM(
	character_id BIGINT not null,
	item_id BIGINT not null,
	primary key (character_id, item_id),
	constraint FK_CI_CHARACTER foreign key (character_id) references CHARACTERS (character_id),
	constraint FK_CI_ITEM foreign key (item_id) references ITEMS (item_id)
);

create table CHARACTER_ABILITY(
	character_id BIGINT not null,
	ability_id BIGINT not null,
	primary key (character_id, ability_id),
	constraint FK_CA_CHARACTER foreign key (character_id) references CHARACTERS (character_id),
	constraint FK_CA_ABILITY foreign key (ability_id) references ABILITIES (ability_id)
);

create table CHARACTER_PERK(
	character_id BIGINT not null,
	perk_id BIGINT not null,
	primary key (character_id, perk_id),
	constraint FK_CP_CHARACTER foreign key (character_id) references CHARACTERS (character_id),
	constraint FK_CP_PERK foreign key (perk_id) references PERKS (perk_id)
);

create table CHARACTER_NOTE(
	character_id BIGINT not null,
	note_id BIGINT not null,
	primary key (character_id, note_id),
	constraint FK_CN_CHARACTER foreign key (character_id) references CHARACTERS (character_id),
	constraint FK_CN_NOTE foreign key (note_id) references NOTES (note_id)
);