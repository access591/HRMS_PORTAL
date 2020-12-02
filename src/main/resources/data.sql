
CREATE TABLE `myuser` (
  `User_code` varchar(10) DEFAULT NULL,
  `User_Name` varchar(200) DEFAULT NULL,
  `Emp_code` varchar(15) DEFAULT NULL,
  `User_pass` varchar(50) DEFAULT NULL,
  `User_active_yn` char(1) DEFAULT NULL,
  `Ins_by` varchar(10) DEFAULT NULL,
  `ins_date` date DEFAULT NULL,
  `upd_by` varchar(10) DEFAULT NULL,
  `upd_date` date DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO `hrms`.`myuser`
(`User_code`,
`User_Name`,
`Emp_code`,
`User_pass`,
`User_active_yn`,
`Ins_by`,
`ins_date`,
`upd_by`,
`upd_date`,
`id`)
VALUES
(<{User_code: }>,
<{User_Name: }>,
<{Emp_code: }>,
<{User_pass: }>,
<{User_active_yn: }>,
<{Ins_by: }>,
<{ins_date: }>,
<{upd_by: }>,
<{upd_date: }>,
<{id: }>);



CREATE TABLE M_MODULE
(
  MODULE_CODE   VARCHAR(10) NOT NULL,
  MODULE_NAME   VARCHAR(100)  NOT NULL,
  ACTIVE_YN     CHAR(1)  DEFAULT 'Y' NOT NULL,
  INS_BY    VARCHAR(10)   NOT NULL,
  INS_DATE  DATE   NOT NULL,
  UPDATE_BY     VARCHAR(10),
  UPDATE_DATE   DATE,
  SEQ_NO    INT
);




CREATE TABLE M_SUB_MODULE
(
  SUB_MODULE_CODE  VARCHAR(10),
  SUB_MODULE_NAME  VARCHAR(100),
  MODULE_CODE      VARCHAR(10),
  INS_BY       VARCHAR(10),
  INS_DATE      DATE,
  UPDATE_BY        VARCHAR(10),
  UPDATE_DATE      DATE,
  ACTIVE_YN        CHAR(1) DEFAULT 'Y',
  SEQ_NO           INT
);



CREATE TABLE  M_PROGRAM
(
  PRG_CODE         VARCHAR(10)  NOT NULL,
  PRG_NAME         VARCHAR(100) NOT NULL,
  MODULE_CODE      VARCHAR(10)  NOT NULL,
  PRG_TYPE         CHAR(1)      NOT NULL,
  PRG_HREF_NAME    VARCHAR(50)  NOT NULL,
  ACTIVE_YN        CHAR(1)      DEFAULT 'Y' NOT NULL,
  INS_BY       VARCHAR(10)  NOT NULL,
  INS_DATE     TIMESTAMP DEFAULT NOW(),
  UPDATE_BY        VARCHAR(10),
  UPDATE_DATE      DATE,
  SUB_MODULE_CODE  VARCHAR(10),
  SEQ_NO           INT
);



CREATE TABLE M_URIGHTS
(
  MODULE_CODE       VARCHAR(10),
  SUB_MODULE_CODE   VARCHAR(10),
  PRG_ID            VARCHAR(8),
  USER_CODE         VARCHAR(10),
  INS_BY        VARCHAR(10),
  INS_DATE     TIMESTAMP DEFAULT NOW(),
  UPDATE_BY         VARCHAR(10),
  UPDATE_DATE       TIMESTAMP DEFAULT NOW(),
  PRG_ACTIVE_YN     CHAR(1)  DEFAULT 'Y'
)