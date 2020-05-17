--SECURITY stuff -- Access Control
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

create table REFRESHTOKENS (
	token_id BIGINT not null AUTO_INCREMENT,
    token varchar_ignorecase(80) unique not null,
    expiry TIMESTAMP not null,
    username varchar_ignorecase(50) not null,
    primary key(token_id)
--    constraint fk_refreshtokens_users foreign key(username) references users(username)
);
--END SECURITY stuff -- Access Control


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
	item_id BIGINT not null,
	name varchar not null,
	item_catagory varchar not null,
	image_path varchar,
	primary key(item_id)
);

create table ABILITY_CARDS(
	ability_card_id BIGINT not null AUTO_INCREMENT,
	name varchar not null,
	level int not null,
	initiative int not null,
	image_path varchar,
	primary key(ability_card_id)
);

create table ABILITY_ACTION_LINES(
	ability_action_id BIGINT not null AUTO_INCREMENT,
	ability_card_id BIGINT not null,
	top boolean not null,
	order_num int not null,
	description varchar not null,
	max_enhancements int not null,
	primary key(ability_action_id),
	constraint FK_AAL_ABILITY_CARDS foreign key (ability_card_id) references ABILITY_CARDS (ability_card_id)
);

create table PERKS(
	perk_id BIGINT not null AUTO_INCREMENT,
	primary key(perk_id)
);

create table ENHANCEMENTS(
	enhancement_id BIGINT not null AUTO_INCREMENT,
	primary key(enhancement_id)
);

create table NOTES(
	note_id BIGINT not null AUTO_INCREMENT,
	note CLOB not null,
	primary key(note_id)
);

create table CHARACTERS_ITEMS(
	character_id BIGINT not null,
	item_id BIGINT not null,
	equip_slot varchar not null,
	primary key (character_id, item_id),
	constraint FK_CI_CHARACTER foreign key (character_id) references CHARACTERS (character_id),
	constraint FK_CI_ITEM foreign key (item_id) references ITEMS (item_id)
);

create table CHARACTERS_ABILITY_CARDS(
	character_id BIGINT not null,
	ability_card_id BIGINT not null,
	primary key (character_id, ability_card_id),
	constraint FK_CA_CHARACTER foreign key (character_id) references CHARACTERS (character_id),
	constraint FK_CA_ABILITY foreign key (ability_card_id) references ABILITY_CARDS (ability_card_id)
);

create table CHARACTERS_PERKS(
	character_id BIGINT not null,
	perk_id BIGINT not null,
	primary key (character_id, perk_id),
	constraint FK_CP_CHARACTER foreign key (character_id) references CHARACTERS (character_id),
	constraint FK_CP_PERK foreign key (perk_id) references PERKS (perk_id)
);

create table CHARACTERS_NOTES(
	character_id BIGINT not null,
	note_id BIGINT not null,
	primary key (character_id, note_id),
	constraint FK_CN_CHARACTER foreign key (character_id) references CHARACTERS (character_id),
	constraint FK_CN_NOTE foreign key (note_id) references NOTES (note_id)
);