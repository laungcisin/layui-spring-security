-- used in tests that use HSQL
DROP TABLE IF EXISTS `oauth_client_details`;
create table oauth_client_details (
  client_id VARCHAR(256) PRIMARY KEY,
  resource_ids VARCHAR(256),
  client_secret VARCHAR(256),
  scope VARCHAR(256),
  authorized_grant_types VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(256)
);

alter table oauth_client_details modify column client_id VARCHAR(256) comment '客户端ID';
alter table oauth_client_details modify column resource_ids VARCHAR(256) comment '资源ID集合,多个资源时用逗号(,)分隔';
alter table oauth_client_details modify column client_secret VARCHAR(256) comment '客户端密匙';
alter table oauth_client_details modify column scope VARCHAR(256) comment '客户端申请的权限范围';
alter table oauth_client_details modify column authorized_grant_types VARCHAR(256) comment '客户端支持的grant_type';
alter table oauth_client_details modify column web_server_redirect_uri VARCHAR(256) comment '重定向URI';
alter table oauth_client_details modify column authorities VARCHAR(256) comment '客户端所拥有的Spring Security的权限值，多个用逗号(,)分隔';
alter table oauth_client_details modify column access_token_validity INTEGER comment '访问令牌有效时间值(单位:秒)';
alter table oauth_client_details modify column refresh_token_validity INTEGER comment '更新令牌有效时间值(单位:秒)';
alter table oauth_client_details modify column additional_information VARCHAR(256) comment '预留字段';
alter table oauth_client_details modify column autoapprove VARCHAR(256) comment '用户是否自动Approval操作';

-- ----------------------------
-- Records of oauth_client_details - test data
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('laungcisin', null, 'imoocsecret', 'all,write,read', 'refresh_token,authorization_code,password', null, null, '7200', '2592000', null, null);


DROP TABLE IF EXISTS `oauth_client_token`;
create table oauth_client_token (
  token_id VARCHAR(256),
  token BLOB,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256)
);

alter table oauth_client_token modify column token_id VARCHAR(256) comment 'MD5加密的access_token值';
alter table oauth_client_token modify column token BLOB comment 'OAuth2AccessToken.java对象序列化后的二进制数据';
alter table oauth_client_token modify column authentication_id VARCHAR(256) comment 'MD5加密过的username,client_id,scope';
alter table oauth_client_token modify column user_name VARCHAR(256) comment '登录的用户名';
alter table oauth_client_token modify column client_id VARCHAR(256) comment '客户端ID';

DROP TABLE IF EXISTS `oauth_access_token`;
create table oauth_access_token (
  token_id VARCHAR(256),
  token BLOB,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication BLOB,
  refresh_token VARCHAR(256)
);

alter table oauth_access_token modify column token_id VARCHAR(256) comment 'MD5加密的access_token的值';
alter table oauth_access_token modify column token BLOB comment 'OAuth2AccessToken.java对象序列化后的二进制数据';
alter table oauth_access_token modify column authentication_id VARCHAR(256) comment 'MD5加密过的username,client_id,scope';
alter table oauth_access_token modify column user_name VARCHAR(256) comment '登录的用户名';
alter table oauth_access_token modify column client_id VARCHAR(256) comment '客户端ID';
alter table oauth_access_token modify column authentication BLOB comment 'OAuth2Authentication.java对象序列化后的二进制数据';
alter table oauth_access_token modify column refresh_token VARCHAR(256) comment 'MD5加密果的refresh_token的值';


DROP TABLE IF EXISTS `oauth_refresh_token`;
create table oauth_refresh_token (
  token_id VARCHAR(256),
  token BLOB,
  authentication BLOB
);

alter table oauth_refresh_token modify column token_id VARCHAR(256) comment 'MD5加密过的refresh_token的值';
alter table oauth_refresh_token modify column token BLOB comment 'OAuth2RefreshToken.java对象序列化后的二进制数据';
alter table oauth_refresh_token modify column authentication VARCHAR(256) comment 'OAuth2Authentication.java对象序列化后的二进制数据';

DROP TABLE IF EXISTS `oauth_code`;
create table oauth_code (
  code VARCHAR(256),
  authentication BLOB
);

alter table oauth_code modify column code VARCHAR(256) comment '授权码(未加密)';
alter table oauth_code modify column authentication BLOB comment 'AuthorizationRequestHolder.java对象序列化后的二进制数据';

DROP TABLE IF EXISTS `oauth_approvals`;
create table oauth_approvals (
  userId VARCHAR(256),
  clientId VARCHAR(256),
  scope VARCHAR(256),
  status VARCHAR(10),
  expiresAt TIMESTAMP,
  lastModifiedAt TIMESTAMP
);

alter table oauth_approvals modify column userid VARCHAR(256) comment '登录的用户名';
alter table oauth_approvals modify column clientid VARCHAR(256) comment '客户端ID';
alter table oauth_approvals modify column scope VARCHAR(256) comment '申请的权限';
alter table oauth_approvals modify column status VARCHAR(256) comment '状态（Approve或Deny）';
alter table oauth_approvals modify column expiresat TIMESTAMP comment '过期时间';
alter table oauth_approvals modify column lastmodifiedat TIMESTAMP comment '最终修改时间';


-- customized oauth_client_details table
DROP TABLE IF EXISTS `ClientDetails`;
create table ClientDetails (
  appId VARCHAR(256) PRIMARY KEY,
  resourceIds VARCHAR(256),
  appSecret VARCHAR(256),
  scope VARCHAR(256),
  grantTypes VARCHAR(256),
  redirectUrl VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation VARCHAR(4096),
  autoApproveScopes VARCHAR(256)
);
