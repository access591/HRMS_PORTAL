
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