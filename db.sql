CREATE DATABASE IF NOT EXISTS fastsales DEFAULT CHARACTER SET utf8;
USE fastsales;

DROP TABLE IF EXISTS sysuser;
CREATE TABLE sysuser(
  user_id  BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_password VARCHAR(25) NOT NULL,
  user_name VARCHAR(32) NOT NULL COMMENT '用户名',
  user_mobile VARCHAR(11) DEFAULT '' COMMENT '手机号码',
  user_tele VARCHAR(20) DEFAULT '' COMMENT '用户座机号码',
  user_role INT(1) DEFAULT '1' COMMENT '0为管理员，1为普通职员，其它再定',
  user_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS supplier;
CREATE TABLE supplier(
  supplier_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  supplier_name VARCHAR(255) NOT NULL COMMENT '供应商名称',
  supplier_mobile VARCHAR(11) DEFAULT '' COMMENT '供应商手机号码',
  supplier_tele VARCHAR(20) DEFAULT '' COMMENT '供应商电话',
  supplier_wechat VARCHAR(255) DEFAULT '' COMMENT '供应商微信号码',
  supplier_agent_name VARCHAR(25) DEFAULT '' COMMENT '供应商联系人名称',
  supplier_comment VARCHAR(25) DEFAULT '' COMMENT '供应商备注',
  supplier_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS customer;
CREATE TABLE customer(
  customer_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  customer_name VARCHAR(255) NOT NULL COMMENT '客户名称',
  customer_mobile VARCHAR(11) DEFAULT '' COMMENT '客户手机号码',
  customer_tele VARCHAR(20) DEFAULT '' COMMENT '客户电话',
  customer_wechat VARCHAR(255) DEFAULT '' COMMENT '客户微信',
  customer_credit INTEGER DEFAULT 0 COMMENT '客户积分',
  customer_comment VARCHAR(255) DEFAULT '' COMMENT '客户备注',
  customer_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS category;
CREATE TABLE category(
  category_id INTEGER PRIMARY KEY AUTO_INCREMENT ,
  category_name VARCHAR(255) NOT NULL UNIQUE COMMENT '类别名称',
  category_comment VARCHAR(255) DEFAULT '' COMMENT '类别备注',
  category_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS image;
CREATE TABLE image(
  image_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  image_user_id INTEGER NOT NULL COMMENT '图片用户名称',
  image_name TEXT NOT NULL COMMENT '图片名称',
  image_prefix TEXT NOT NULL COMMENT '图片存储的域名',
  image_directory TEXT NOT NULL COMMENT '图片存储的路径',
  image_suffix VARCHAR(10) NOT NULL COMMENT '图片的后缀'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS product_image;
CREATE TABLE product_image(
  product_id BIGINT,
  image_url VARCHAR(255) ,
  PRIMARY KEY (product_id, image_url)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS tag;
CREATE TABLE tag(
  tag_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  tag_info VARCHAR(10) UNIQUE  NOT NULL,
  tag_comment VARCHAR(255) DEFAULT ''
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS product;
CREATE TABLE product(
  product_id BIGINT PRIMARY KEY COMMENT '系统赋予的ID',
  product_short_id VARCHAR(255) NOT NULL COMMENT '产品编号即货号',
  product_serialized_id VARCHAR(255) UNIQUE NOT NULL COMMENT '产品序列号即条形码',
  product_category_id INTEGER NOT NULL COMMENT '产品类型ID',
  product_name VARCHAR(255) NOT NULL UNIQUE COMMENT '产品名称',
  product_cover_image_url VARCHAR(255) DEFAULT '',
  product_tags VARCHAR(255) DEFAULT '' COMMENT '产品标签',
  product_comment VARCHAR(255) DEFAULT '' COMMENT '产品备注',
  product_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS product_spec;
CREATE TABLE product_spec(
  product_spec_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  product_id BIGINT NOT NULL COMMENT '产品ID',
  product_spec_quantity BIGINT DEFAULT 0,
  product_spec_detail VARCHAR(255) DEFAULT '' COMMENT '记录产品的其它属性'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS purchase_order;
CREATE TABLE IF NOT EXISTS purchase_order(
  purchase_order_id BIGINT PRIMARY KEY,
  supplier_id BIGINT NOT NULL COMMENT '供应商ID',
  purchase_order_real_id VARCHAR(255) DEFAULT "" COMMENT '厂家的直销ID',
  purchase_order_sum DOUBLE DEFAULT 0.0 COMMENT '进货单总金额',
  purchase_order_comment VARCHAR(255) DEFAULT '' COMMENT '进货单备注'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS purchase_order_item;
CREATE TABLE IF NOT EXISTS purchase_order_item(
  item_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  purchase_order_id BIGINT NOT NULL ,
  product_spec_id BIGINT NOT NULL COMMENT  '商品详情ID',
  item_quantity INTEGER DEFAULT 0 COMMENT '数量',
  item_quantity_per_unit INTEGER DEFAULT 1 COMMENT '平均每件中的数量',
  item_init_price DOUBLE DEFAULT 0.0 COMMENT '初始进价',
  UNIQUE (purchase_order_id, product_spec_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS payment;
CREATE TABLE IF NOT EXISTS payment(
  payment_id BIGINT PRIMARY KEY DEFAULT -1 COMMENT '付款码',
  sales_order_id BIGINT NOT NULL,
  payment_sum DOUBLE DEFAULT 0.0 COMMENT '支付总金额',
  payment_type INT DEFAULT 0 COMMENT '支付类型',
  payment_comment VARCHAR(255) DEFAULT '' COMMENT '备注',
  payment_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS sales_item_spec;
CREATE TABLE IF NOT EXISTS sales_item_spec(
  item_spec_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  product_spec_id BIGINT NOT NULL ,
  sales_item_id BIGINT NOT NULL COMMENT '对应所在的销售单品ID',
  item_spec_quantity INT DEFAULT 1 COMMENT '商品数量',
  UNIQUE (product_spec_id, sales_item_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS sales_item;
CREATE TABLE IF NOT EXISTS sales_item(
  sales_item_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  sales_item_serialized_id VARCHAR(255) NOT NULL UNIQUE ,
  sales_item_name VARCHAR(255) NOT NULL ,
  sales_item_default_price DOUBLE DEFAULT 0.0 COMMENT '默认售价',
  sales_item_create_at TIMESTAMP DEFAULT current_timestamp
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS sales_order_item;
CREATE TABLE IF NOT EXISTS sales_order_item(
  order_item_id BIGINT PRIMARY KEY AUTO_INCREMENT,
  sales_item_id BIGINT NOT NULL COMMENT '销售单中的商品单品ID',
  order_id BIGINT NOT NULL COMMENT '对应销售单ID',
  order_item_price DOUBLE DEFAULT 0,
  order_item_quantity INT DEFAULT 0 COMMENT '销售单单品数量',
  UNIQUE (sales_item_id, order_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS sales_order;
CREATE TABLE IF NOT EXISTS sales_order(
  sales_order_id BIGINT PRIMARY KEY,
  sales_order_source INT DEFAULT 0,
  salesman_id BIGINT NOT NULL ,
  customer_id BIGINT DEFAULT NULL ,
  sales_order_created_at TIMESTAMP DEFAULT current_timestamp,
  sales_order_issent TINYINT(1) DEFAULT 0 COMMENT '是否发货',
  sales_order_isfinished TINYINT(1) DEFAULT 0 COMMENT '该订单是否完成',
  sales_order_iscancelled TINYINT(1) DEFAULT 0 COMMENT '该订单是否取消'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
