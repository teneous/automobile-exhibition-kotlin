# ecommerce-kotlin
这个是为了学习kotlin所写的一个简单交易例子
### 情景
**顾客来店购买商品,放进购物车，下单。然后订单页面进行结账，并且打印小票的功能**

### 流程
1.首先选择在许多"分类"的商品中选择好商品,商品可以有折扣，参考( <a name="discount_rule">折扣规则</a> )

#### 关于退货或换货
###### 退款
+ 购买商品的3天内可以要求退货,且以实际支付价格退还,如果有用过优惠卷等,则卷不予返还
+ 退款以原支付类型渠道退还，并在1~3个工作日内到款
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


**订单表**



**交易表**
###### sale
 + id             BIGINT(11) AUTO_INCREMENT PRIMARY KEY,
 + customerid     BIGINT(11) NULL,
 + sequence_no    INT        NOT NULL,
 + sale_times     DATETIME   NULL,
 + realpay_money  INT        NULL,
 + totalmoney     INT        NULL,
 + discount       INT        NULL,
 + discount_type  SMALLINT(6) NULL,
 + re_sequence_no INT        NULL



**金种表**



**顾客表**



**折扣主表**