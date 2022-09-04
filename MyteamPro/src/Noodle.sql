drop table monster cascade constraints;
drop table user_info cascade constraints;
drop table character cascade constraints;

create table character
(
str number(20) not null,
dex number(20) not null,
int number(20) not null,
luk number(20) not null,
id varchar2(30),
pw varchar2(30) not null,
nick varchar2(30),
lv number(20) not null,
hp number(20),
mp number(20),
exp number(20),
score number(30),
constraint cha_nickidpw_pk primary key(id, nick),
constraint cha_nickidpw_fk foreign key(id, nick) references user_info(id, nick)
);

create table user_info(
id varchar2(30),
pw varchar2(30) not null,
name varchar2(30) not null,
nick varchar2(30),
donate varchar2(20) not null,
constraint userinfo_idpwnick_pk primary key(id, nick),
constraint userinfo_donate_ck check(donate in('Y', 'N'))
);

create table monster
(
m_name varchar2(30),
m_lv number(20) not null,
m_exp number(20) not null,
m_score number(20) not null,
constraint monster_name_pk primary key(m_name)
);

select * from monster;
select * from character;
select * from user_info;

insert into monster values('4',2,5);

insert into user_info values('admin', 'admin', '관리자', '관리자',  'Y');

update character set score = 6 where id = '2';

update character set exp = 0, lv = 1, str = 9, int = 6, dex = 8, luk = 6, score = 0, hp = 5, mp = 200 where id = '2';

update character set hp = 5, exp = 0, lv = 1 where id = 'sinhy13';

select * from character order by score desc

insert into monster values('속찬라면', 1, 10, 100);
insert into monster values('콩국수', 1, 11, 200);
insert into monster values('봉골라면', 1, 12, 300);
insert into monster values('메밀라면', 1, 13, 400);
insert into monster values('스파게티', 1, 14, 500);
insert into monster values('라면볶이', 1, 15, 600);
insert into monster values('쌀라면', 1, 16, 700);
insert into monster values('독도와 함께라면', 1, 17, 800);
insert into monster values('목이라면', 1, 18, 900);
insert into monster values('손큰라면', 1, 19, 1000);
insert into monster values('남자라면', 2, 20, 1100);
insert into monster values('우리밀라면', 2, 21, 1200);
insert into monster values('황태라면', 2, 22, 1300);
insert into monster values('국민라면', 2, 23, 1400);
insert into monster values('컵누들', 2, 24, 1500);
insert into monster values('속풀라면', 2, 25, 1600);
insert into monster values('사리면', 2, 26, 1700);
insert into monster values('카레라면', 2, 27, 1800);
insert into monster values('간짬뽕', 2, 28, 1900);
insert into monster values('짜짜로니', 2, 29, 2000);
insert into monster values('나가사끼짬뽕', 3, 30, 2100);
insert into monster values('쇠고기면', 3, 31, 2200);
insert into monster values('손칼국수', 3, 32, 2300);
insert into monster values('수타면', 3, 33, 2400);
insert into monster values('파워라면', 3, 34, 2500);
insert into monster values('진짬뽕', 3, 35, 2600);
insert into monster values('튀김우동', 3, 36, 2700);
insert into monster values('참깨라면', 3, 37, 2800);
insert into monster values('열라면', 3, 38, 2900);
insert into monster values('새우탕', 3, 39, 3000);
insert into monster values('오모리김치찌개',4,40,3100);
insert into monster values('오징어짬뽕',4,41,3200);
insert into monster values('삼양라면',4,42,3300);
insert into monster values('비빔면',4,43,3400);
insert into monster values('안성탕면',4,44,3500);
insert into monster values('스낵면',4,45,3600);
insert into monster values('너구리',4,46,3700);
insert into monster values('무파마',4,47,3800);
insert into monster values('꼬꼬면',4,48,3900);
insert into monster values('틈새라면',4,49,4000);
insert into monster values('사리곰탕',5,50,4100);
insert into monster values('진라면',5,51,4200);
insert into monster values('공화춘',5,52,4300);
insert into monster values('불닭볶음면',5,53,4400);
insert into monster values('육개장',5,54,4500);
insert into monster values('짜파게티',5,55,4600);
insert into monster values('마왕라면',5,56,4700);
insert into monster values('후추라면',5,57,4800);
insert into monster values('감자면',5,58,4900);
insert into monster values('부대찌개라면',5,59,5000);