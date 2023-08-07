-- 创建数据库
drop database if exists mycnblog;
create database mycnblog DEFAULT CHARACTER SET utf8mb4;

-- 使用数据数据
use mycnblog;

-- 创建表[用户表]
drop table if exists  userinfo;
create table userinfo(
    id int primary key auto_increment,
    username varchar(100) not null,
    password varchar(32) not null,
    photo varchar(500) default '',
    createtime timestamp default current_timestamp,
    updatetime timestamp default current_timestamp,
    `state` int default 1
) default charset 'utf8mb4';

-- 创建文章表
drop table if exists  articleinfo;
create table articleinfo(
    id int primary key auto_increment,
    title varchar(100) not null,
    content text not null,
    createtime timestamp default current_timestamp,
    updatetime timestamp default current_timestamp,
    uid int not null,
    rcount int not null default 1,
    `state` int default 1
)default charset 'utf8mb4';

-- 创建视频表
drop table if exists videoinfo;
create table videoinfo(
  	vid int primary key,
  	`title` varchar(250),
  	`url` varchar(1000),
		createtime timestamp default current_timestamp,
		updatetime timestamp default current_timestamp,
  	uid int
)default charset 'utf8mb4';

-- 添加一个用户信息
INSERT INTO `mycnblog`.`userinfo` (`id`, `username`, `password`, `photo`, `createtime`, `updatetime`, `state`) VALUES 
(1, 'admin', 'admin', '', '2021-12-06 17:10:48', '2021-12-06 17:10:48', 1);

-- 文章添加测试数据
insert into articleinfo(title,content,uid)
    values('Java','Java正文',1);
    
-- 添加视频
insert into videoinfo(vid,title,url,uid) values(1,'java title','http://www.baidu.com',1);