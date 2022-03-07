DROP TABLE IF EXISTS coindesk;
CREATE TABLE coindesk  (
	currency_code char(3) NOT NULL PRIMARY KEY  ,
	currency_name VARCHAR(60)NOT NULL,
	rate_float DECIMAL(10, 4)NOT NULL,
	updated_time_utc VARCHAR(60)NOT NULL ,
	updated_time_gmt VARCHAR(60)NOT NULL,
	updated_time_iso VARCHAR(60)NOT NULL
);
