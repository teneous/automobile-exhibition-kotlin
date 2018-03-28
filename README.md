# ecommerce-kotlin
这个是为了学习kotlin所写的一个简单交易例子
### 情景
**顾客来店购买商品,放进购物车，下单。生成订单页面进行结账，并且查看历史订单的功能**

### 流程
1.首先选择在许多"分类"的商品中选择好商品,商品可以有折扣，参考( <a name="discount_rule">折扣规则</a> )

#### 关于退货或换货
###### 退款
+ 购买商品的3天内可以要求退货,且以实际支付价格退还,如果有用过优惠卷等,则卷不予返还
+ 退款以原支付类型渠道退还，并在1~3个工作日内到款
+ 满减的金额是平摊到各个商品的
###### 换货
+ 顾客可以用自己剩下商品余额+差价来购买新的商品(不适用于已经过期的商品),新商品价格必须大于老商品
+ 普通换货
### <p id="discount_rule">折扣类型</p>
+ 第一种:特价商品(折扣价：百分比)
+ 第二种:满减(不能包含特价商品)
+ 第三种:必须适用于无折扣商品,当同类型商品购买大于2个(包含),第三个半价
+ 第四种:适用于全场任意商品的抵用卷(金额较少)

优先级大小:
+ 满减与半价活动不可同时使用
+ 可以在第一种后在额外使用全场抵用卷
+ 可以在第二种后在额外使用全场抵用卷
+ 可以在第三种后在额外使用全场抵用卷
+ 最低可以减少至0

**金额全部以上下取整**


### DB设计书

**商品主表**
###### product
+ id            BIGINT(11) AUTO_INCREMENT PRIMARY KEY,
+  product_name VARCHAR(20) COMMENT '商品名' NULL,
+  product_type SMALLINT(6) COMMENT '商品类型' NULL,
+  status       SMALLINT(6) COMMENT '商品状态' NULL

**订单表**
###### order
+  id             BIGINT(11) AUTO_INCREMENT PRIMARY KEY,
+  customer_id    BIGINT(11)  COMMENT '顾客Id' NULL
+  sequence_no    VARCHAR(12) COMMENT '订单流水号' NULL,
+  order_time     DATETIME    COMMENT '订单时间' NULL,
+  status         SMALLINT(6) COMMENT '订单状态' NULL,
+  total_money    INT         COMMENT '订单总价格' NULL,
+  total_discount INT         COMMENT '订单总折扣' NULL,

**订单支付金钟金额表**
###### order_payment
+  id             BIGINT(11) AUTO_INCREMENT PRIMARY KEY,
+  sequence_no    VARCHAR(11) COMMENT '订单流水号' NULL,
+  payment_type   SMALLINT(6) COMMENT '支付类型' NULL,
+  payment_detail BIGINT(11)  COMMENT '支付类型详细' NULL,
+  payment_money  int         COMMENT '支付金额' NULL
+  purchase_time  DATE        COMMENT '支付时间'  NULL

**交易表**
###### sale
 + id             BIGINT(11) AUTO_INCREMENT PRIMARY KEY,
 + sequence_no    INT        COMMENT '交易流水号' NOT NULL,
 + sale_times     DATETIME   COMMENT '交易时间' NULL,
 + product        BIGINT(11) COMMENT '商品id' NOT NULL,
 + number         INT        COMMENT '交易数量' NULL,
 + discount       INT        COMMENT '商品折扣价' NULL,
 + extra_discount  SMALLINT(6) COMMENT '额外折扣价，一般是全场减卷' NULL
 + special_opr    SMALLINT   COMMENT '换货/退货'  NULL


**金种表**
###### payement
+  id           BIGINT(11) AUTO_INCREMENT PRIMARY KEY,
+  payment_type SMALLINT(6) COMMENT '金钟类型' NOT NULL,
+  payment_name VARCHAR(30) COMMENT '金钟名字' NOT NULL,
+  index        SMALLINT(6) COMMENT 'index排序' NULL,
+  status       SMALLINT(6) COMMENT '有效性' NOT NULL,
+  start_time   DATETIME    COMMENT '开始时间' NULL

**金种明细表**
###### payement_detail
+  id              BIGINT(11) AUTO_INCREMENT PRIMARY KEY,
+  payment_type    SMALLINT(6) COMMENT '和主表类型相同' NULL,
+  detail_name     VARCHAR(30) COMMENT '明细名字' NULL,
+  index           SMALLINT(6) COMMENT '排序' NULL,
+  status          SMALLINT(6) COMMENT '有效性' NULL,
+  own_conpany     VARCHAR(30) COMMENT '所属公司' NULL,
+  authorized_time DATETIME    COMMENT '授权时间' NULL

**顾客表**
###### customer
+  id            BIGINT      NULL,
+  identity_num  VARCHAR(12) COMMENT '卡号' NULL,
+  nick_name     VARCHAR(40) COMMENT '名字' NULL,
+  register_time DATETIME    COMMENT '注册时间' NULL,
+  status        SMALLINT(6) COMMENT '有效性' NULL,
+  point         INT         COMMENT '积分' NULL,
  CONSTRAINT customer_identity_num_uindex
  UNIQUE (identity_num),
  CONSTRAINT customer_card_num_uindex


**顾客商品所有表**
###### customer_product
+  id            BIGINT(11) AUTO_INCREMENT PRIMARY KEY,
+  customer_id   BIGINT      COMMENT '顾客id' NULL,
+  product_id    BIGINT(11)  COMMENT '商品id' NULL,
+  validate_time DATETIME    COMMENT '商品有效期' NULL,
+  purchase_time INT         COMMENT '商品购买时间' NULL,
+  total_num     INT         COMMENT '总个数' NULL,
+  remain_num    INT         COMMENT '剩余次数' NULL,
+  status        SMALLINT(6) COMMENT '有效性' NULL,
+  single_price  INT         COMMENT '单价' NULL
