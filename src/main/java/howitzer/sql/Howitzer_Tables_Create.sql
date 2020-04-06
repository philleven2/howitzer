DROP DATABASE IF EXISTS howitzer;

CREATE DATABASE IF NOT EXISTS howitzer CHARACTER SET utf8;

use howitzer;

SET FOREIGN_KEY_CHECKS = 0;

/* Users */
DROP TABLE IF EXISTS Users;

CREATE TABLE Users (
  id 			int				NOT NULL 	AUTO_INCREMENT,
  userId		varchar(50)		NOT NULL,
  shots			int				NOT NULL	COMMENT 'Number of shots.',
  hits			int				NOT NULL	COMMENT 'Number of hits.',
  misses		int				NOT NULL	COMMENT 'Number of misses.',
  avgHits		decimal(8,2)	NOT NULL	COMMENT 'Average hits.',
  userRank		int				NOT NULL	COMMENT 'Rank.',
  timeStamp 	TIMESTAMP 		DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO Users VALUES(1, "User1", 0, 0, 0, 0, 0, CURRENT_TIMESTAMP);
INSERT INTO Users VALUES(2, "User2", 0, 0, 0, 0, 0, CURRENT_TIMESTAMP);
INSERT INTO Users VALUES(3, "User3", 0, 0, 0, 0, 0, CURRENT_TIMESTAMP);
INSERT INTO Users VALUES(4, "User4", 0, 0, 0, 0, 0, CURRENT_TIMESTAMP);
INSERT INTO Users VALUES(5, "User5", 0, 0, 0, 0, 0, CURRENT_TIMESTAMP);
INSERT INTO Users VALUES(6, "User6", 0, 0, 0, 0, 0, CURRENT_TIMESTAMP);
INSERT INTO Users VALUES(7, "User7", 0, 0, 0, 0, 0, CURRENT_TIMESTAMP);
INSERT INTO Users VALUES(8, "User8", 0, 0, 0, 0, 0, CURRENT_TIMESTAMP);
INSERT INTO Users VALUES(9, "User9", 0, 0, 0, 0, 0, CURRENT_TIMESTAMP);
INSERT INTO Users VALUES(10, "User10", 0, 0, 0, 0, 0, CURRENT_TIMESTAMP);
INSERT INTO Users VALUES(11, "User11", 0, 0, 0, 0, 0, CURRENT_TIMESTAMP);
INSERT INTO Users VALUES(12, "User12", 0, 0, 0, 0, 0, CURRENT_TIMESTAMP);
INSERT INTO Users VALUES(13, "User13", 0, 0, 0, 0, 0, CURRENT_TIMESTAMP);
INSERT INTO Users VALUES(14, "User14", 0, 0, 0, 0, 0, CURRENT_TIMESTAMP);
INSERT INTO Users VALUES(15, "User15", 0, 0, 0, 0, 0, CURRENT_TIMESTAMP);

/* HowitzerHistory */
DROP TABLE IF EXISTS HowitzerHistory;

CREATE TABLE HowitzerHistory (
  id 				int				NOT NULL 	AUTO_INCREMENT,
  userId			varchar(50)		NOT NULL	COMMENT 'User id.',
  distanceToTarget	double(10,4)	NOT NULL	COMMENT 'Distance to target (meters).',
  angle				double(10,4)	NOT NULL	COMMENT 'Muzzle angle of elevation (radians).',
  velocity			double(10,4)	NOT NULL	COMMENT 'Initial velocity (meters/second).',
  targetSize		double(10,4)	NOT NULL	COMMENT	'Target diameter (meters).',
  result			varchar(4)		NOT NULL	COMMENT 'Hit or miss.',
  distanceTraveled	double(10,4)	NOT NULL	COMMENT 'Distance traveled (meters).',
  distanceMissedBy	double(10,4)	NOT NULL	COMMENT 'Distance missed by (meters).',
  timeTraveled		double(10,4)    NOT NULL	COMMENT 'Time traveled (seconds).',
  timeStamp 		TIMESTAMP 		DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create index idx_howitzerhistory_userId on HowitzerHistory (userId);

/* Properties */
DROP TABLE IF EXISTS Properties;

CREATE TABLE Properties (
  id 					int				NOT NULL 	AUTO_INCREMENT,
  user					varchar(50)		NOT NULL,
  password				varbinary(255)	NOT NULL,
  lastModified			TIMESTAMP 		NOT NULL	DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO Properties VALUES(1, "phil", 
AES_ENCRYPT("fire", "Cynthia1"), CURRENT_TIMESTAMP);

/* Logs */
DROP TABLE IF EXISTS Logs;

CREATE TABLE Logs (
	dateCreated 	datetime       	NOT NULL,
   	logger  		varchar(100)    NOT NULL,
   	level   		varchar(10)    	NOT NULL,
   	message 		text  			NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;