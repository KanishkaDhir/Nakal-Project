/*
Navicat MySQL Data Transfer

Source Server         : bce
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : nakl_project

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2017-10-14 19:47:15
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `new_entry`
-- ----------------------------
DROP TABLE IF EXISTS `new_entry`;
CREATE TABLE `new_entry` (
  `SrNo` bigint(100) NOT NULL,
  `Name` varchar(200) DEFAULT NULL,
  `Fname` varchar(200) DEFAULT NULL,
  `GFname` varchar(200) DEFAULT NULL,
  `Village` varchar(100) DEFAULT NULL,
  `MobileNo` varchar(50) DEFAULT NULL,
  `KhewatNo` varchar(100) DEFAULT NULL,
  `KhasraNo` varchar(100) DEFAULT NULL,
  `Nakl` varchar(50) DEFAULT NULL,
  `InitialFee` varchar(50) DEFAULT NULL,
  `AppliedDate` varchar(100) DEFAULT NULL,
  `TentDeliveryDate` varchar(100) DEFAULT NULL,
  `NoOfPages` varchar(100) DEFAULT NULL,
  `BalanceFee` varchar(100) DEFAULT NULL,
  `TotalFee` varchar(100) DEFAULT NULL,
  `ReadyToDeliver` varchar(100) DEFAULT NULL,
  `Delivered` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`SrNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of new_entry
-- ----------------------------
