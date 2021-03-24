use hmily_fx2;
CREATE TABLE account (
	user_id BIGINT ,
	usd DECIMAL(11,2) NULL,
	cny DECIMAL NULL,
	primary key (`user_id`)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;


CREATE TABLE freeze_account (
	user_id BIGINT ,
	usd DECIMAL(11,2) NULL,
	cny DECIMAL NULL,
	primary key (`user_id`)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

-- A账号
INSERT into account value(1,100,100);
-- B账号
INSERT into account value(2,100,100);
-- A冻结账号
INSERT into freeze_account value(1,0,0);
-- B冻结账号
INSERT into freeze_account value(2,0,0);