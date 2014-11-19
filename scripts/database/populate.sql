#populate TENANT
insert into `content_bowl`.`TENANT` (ID_TENANT, DS_MNEMONIC, DS_DESCRIPTION, IS_ACTIVE)
	values (0, 'default', 'Default tenant, should be used to handle common data and values', 'Y');

insert into `content_bowl`.`TENANT` (ID_TENANT, DS_MNEMONIC, DS_DESCRIPTION, IS_ACTIVE)
	values (1, 'test', 'Tenant used for testing purposes', 'Y');

insert into `content_bowl`.`TENANT` (ID_TENANT, DS_MNEMONIC, DS_DESCRIPTION, IS_ACTIVE)
	values (2, 'dviveiros', 'Daniel Viveiros tenant. Handles danielviveiros.com data', 'Y');

commit;

#populate API_CLIENT
insert into `content_bowl`.`API_CLIENT`
	(ID_API_CLIENT, ID_TENANT, DS_API_KEY, DS_DESCRIPTION, IS_ACTIVE, DT_EXPIRATION)
	values (0, 0, '06dbf2d26350d49ff66084a258fe2fcc', 'Master API key for administration', 'Y', null);

insert into `content_bowl`.`API_CLIENT`
	(ID_API_CLIENT, ID_TENANT, DS_API_KEY, DS_DESCRIPTION, IS_ACTIVE, DT_EXPIRATION)
	values (1, 1, 'ef0f4d05aaf9de6fdc562d9a35bb134a', 'Default test API key', 'Y', null);

insert into `content_bowl`.`API_CLIENT`
	(ID_API_CLIENT, ID_TENANT, DS_API_KEY, DS_DESCRIPTION, IS_ACTIVE, DT_EXPIRATION)
	values (1, 2, '457d9b95c4bcf57125432476a7e0d488', 'Default dviveiros API key', 'Y', null);

commit;

#populate configuration category
insert into `content_bowl`.`CONFIGURATION_CATEGORY` (ID_CONF_CATEGORY, DS_MNEMONIC, DS_DESCRIPTION)
	values (0, 'general', 'General configuration. Used for aggregating basic configuration attributes');

