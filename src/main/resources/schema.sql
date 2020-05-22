-- ----------------------------------------------------
-- Security/Users tables
-- ----------------------------------------------------
create table users(
	username varchar_ignorecase(60) not null primary key,
	password varchar_ignorecase(60) not null,
	enabled boolean not null
);

create table authorities (
	username varchar_ignorecase(60) not null,
	authority varchar_ignorecase(60) not null,
	constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);

-- ----------------------------------------------------
-- Goomhaven Self-Contained Tables (mostly those without foreign keys)
-- ----------------------------------------------------
create table NOTES(
	note_id BIGINT not null AUTO_INCREMENT,
	title varchar,
	description varchar,
	note CLOB,
	primary key(note_id)
);
	
create table WALLET(
	wallet_id BIGINT not null AUTO_INCREMENT,
	money int,
	rock_root int,
	arrow_vine int,
	snow_thristle int,
	corpsecap int,
	axenut int,
	flamefruit int,
	hide int,
	metal int,
	wood int,
	primary key(wallet_id)
);

create table STATUS(
	status_id BIGINT not null AUTO_INCREMENT,
	poisoned boolean,
	wounded boolean,
	immobilized boolean,
	disarmed boolean,
	stunned boolean,
	muddled boolean,
	invisible boolean,
	stregthened boolean,
	regenerating boolean,
	injured boolean,
	brittle boolean,
	bane boolean,
	curse_count int,
	bless_count int,
	primary key(status_id)
);

-- TODO: This is currently a skeleton of the PQ schema.
create table PERSONAL_QUESTS (
	personal_quest_id BIGINT not null AUTO_INCREMENT,
	primary key(personal_quest_id)
);

create table ITEMS(
	item_id BIGINT not null,
	name varchar,
	cost int,
	count int,
	slot varchar,
	description varchar,
	spent boolean,
	consumed boolean,
	source varchar,
	image_path varchar,
	token_slots int,
	minus_one_cards int,
	summon_hp int,
	summon_move int,
	summon_attack int,
	summon_range int,
	primary key(item_id)
);

create table PERKS(
	perk_id BIGINT not null AUTO_INCREMENT,
	description varchar,
	primary key(perk_id)
);

create table ABILITY_CARDS(
	ability_card_id BIGINT not null AUTO_INCREMENT,
	name varchar,
	level int,
	initiative int,
	image_path varchar,
	primary key(ability_card_id)
);

-- TODO: Havn't figured out the requirements on enhancements yet
create table ENHANCEMENTS(
	enhancement_id BIGINT not null AUTO_INCREMENT,
	primary key(enhancement_id)
);

-- ----------------------------------------------------
-- Gloomhaven tables that only require Self-Contained Tables
-- ----------------------------------------------------
create table ABILITY_ACTION_LINES(
	ability_action_id BIGINT not null AUTO_INCREMENT,
	ability_card_id BIGINT not null,
	top boolean,
	sub boolean,
	order_num int,
	description varchar,
	max_enhancements int,
	primary key(ability_action_id),
	constraint FK_AAL_ABILITY_CARDS foreign key (ability_card_id) references ABILITY_CARDS (ability_card_id)
);

create table CLASSES(
	class_id BIGINT not null AUTO_INCREMENT,
	name varchar,
	race_note BIGINT,
	health_expresssion varchar,
	image_path varchar,
	primary key(class_id),
	constraint fk_Cl_NOTE foreign key(race_note) references NOTES (note_id)
);

create table CHARACTERS (
	character_id BIGINT not null AUTO_INCREMENT,
	name varchar not null,
	exp int,
	wallet_id BIGINT,
	check_marks int,
	class_id BIGINT,
	personal_quest_id BIGINT,
	primary key(character_id),
	constraint FK_C_CLASS foreign key (class_id) references CLASSES (class_id),
	constraint FK_C_PERSONAL_QUEST foreign key (personal_quest_id) references PERSONAL_QUESTS (personal_quest_id),
	constraint FK_C_WALLET foreign key (wallet_id) references WALLET (wallet_id)
);

-- ----------------------------------------------------
-- The Rest of the Gloomhaven Tables, sometimes order matters here
-- ----------------------------------------------------
create table ACTIVE_CHARACTERS (
	active_character_id BIGINT not null AUTO_INCREMENT,
	character_id BIGINT not null,
	wallet_id BIGINT,
	status_id BIGINT,
	active boolean,
	primary key(active_character_id),
	constraint FK_AC_CHARACTER foreign key (character_id) references CHARACTERS (character_id),
	constraint FK_AC_WALLET foreign key (wallet_id) references WALLET (wallet_id)
);

-- This is really just a join table with an extra boolean to equip items. Basically removes this
-- consern from the DB entirely. The DB is about Data, not game logic.
-- Thought about making constraints on equiping items to the right slots and such, but it makes sense
-- to push that consern down the line when you consider small items. Who knows, maybe a future race 
-- (say in frosthaven) will have four arms or two legs. Not really my consern here.
create table CHARACTERS_ITEMS(
	character_id BIGINT not null,
	item_id BIGINT not null,
	equipped boolean,
	primary key (character_id, item_id),
	constraint FK_CI_CHARACTER foreign key (character_id) references CHARACTERS (character_id),
	constraint FK_CI_ITEM foreign key (item_id) references ITEMS (item_id)
);

-- ----------------------------------------------------
-- Gloomhaven Join Tables
-- ----------------------------------------------------
create table CHARACTERS_ABILITY_CARDS(
	character_id BIGINT not null,
	ability_card_id BIGINT not null,
	primary key (character_id, ability_card_id),
	constraint FK_CA_CHARACTER foreign key (character_id) references CHARACTERS (character_id),
	constraint FK_CA_ABILITY foreign key (ability_card_id) references ABILITY_CARDS (ability_card_id)
);

--     Tables that join to PERKS

create table CHARACTERS_PERKS(
	character_id BIGINT not null,
	perk_id BIGINT not null,
	primary key (character_id, perk_id),
	constraint FK_CP_CHARACTER foreign key (character_id) references CHARACTERS (character_id),
	constraint FK_CP_PERK foreign key (perk_id) references PERKS (perk_id)
);

create table CLASSES_PERKS(
	class_id BIGINT not null,
	perk_id BIGINT not null,
	primary key (class_id, perk_id),
	constraint FK_ClP_CLASS foreign key (class_id) references CLASSES (class_id),
	constraint FK_ClP_PERK foreign key (perk_id) references PERKS (perk_id)
);

--     Tables that join to NOTES

create table CHARACTERS_NOTES(
	character_id BIGINT not null,
	note_id BIGINT not null,
	primary key (character_id, note_id),
	constraint FK_CN_CHARACTER foreign key (character_id) references CHARACTERS (character_id),
	constraint FK_CN_NOTE foreign key (note_id) references NOTES (note_id)
);

create table ITEMS_NOTES(
	item_id BIGINT not null,
	note_id BIGINT not null,
	primary key (item_id, note_id),
	constraint FK_IN_ITEM foreign key (item_id) references ITEMS (item_id),
	constraint FK_IN_NOTE foreign key (note_id) references NOTES (note_id)
);

create table CLASSES_NOTES(
	class_id BIGINT not null,
	note_id BIGINT not null,
	primary key (class_id, note_id),
	constraint FK_ClN_CLASS foreign key (class_id) references CLASSES (class_id),
	constraint FK_ClN_NOTE foreign key (note_id) references NOTES (note_id)
);

create table ABILITY_CARDS_NOTES (
	ability_card_id BIGINT not null,
	note_id BIGINT not null,
	primary key (ability_card_id, note_id),
	constraint FK_ACN_CLASS foreign key (ability_card_id) references ABILITY_CARDS (ability_card_id),
	constraint FK_ACN_NOTE foreign key (note_id) references NOTES (note_id)
);
