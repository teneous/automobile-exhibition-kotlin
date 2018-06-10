接口流程说明书

IPayForOrderService 用户下单
1.用户点击下单的时候调用，会在表中生成一个未付款的订单记录，并且对应订单商品表也会有对应记录。

IPlaceOrderService 用户付款
1.用户针对于某一个订单进行付款，此时会生成对应真实交易记录，并且将订单变更为已付款状态

getAllCustomerHistoryOrder 查看用户所有订单（尝试使用Redis试试）
1.默认查看当前用户有效订单


查看某一笔订单的详细

查看所有用户订单详细


