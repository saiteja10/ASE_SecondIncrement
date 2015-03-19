alter table item add column description varchar(255)
create table restaurant (id bigint not null auto_increment, comments varchar(255), createdTime datetime, lastUpdateTime datetime, lastUpdatedBy varchar(255), version integer, name varchar(255), primary key (id)) ENGINE=InnoDB
create table restaurant_subcategory (id bigint not null auto_increment, comments varchar(255), createdTime datetime, lastUpdateTime datetime, lastUpdatedBy varchar(255), version integer, restaurantId bigint not null, subCategoryId bigint not null, primary key (id)) ENGINE=InnoDB
alter table restaurant_subcategory add index FK3FE8F13CF1F31477 (restaurantId), add constraint FK3FE8F13CF1F31477 foreign key (restaurantId) references restaurant (id)
alter table restaurant_subcategory add index FK3FE8F13CA9224855 (subCategoryId), add constraint FK3FE8F13CA9224855 foreign key (subCategoryId) references item_subcategory (id)
