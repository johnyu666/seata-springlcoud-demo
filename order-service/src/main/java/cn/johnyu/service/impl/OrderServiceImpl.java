package cn.johnyu.service.impl;

import cn.johnyu.commons.dto.AccountReduceBalanceDTO;
import cn.johnyu.commons.dto.ProductReduceStockDTO;
import cn.johnyu.dao.OrderDao;
import cn.johnyu.entity.OrderDO;
import cn.johnyu.feign.AccountServiceFeignClient;
import cn.johnyu.feign.ProductServiceFeignClient;

import cn.johnyu.service.OrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private DataSource dataSource;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private AccountServiceFeignClient accountService;
    @Autowired
    private ProductServiceFeignClient productService;

    @Override
    @GlobalTransactional // <1>
    public Integer createOrder(Long userId, Long productId, Integer price) {

        Integer amount = 1; // 购买数量，暂时设置为 1。

        log.info("[createOrder] 当前 XID: {}", RootContext.getXID());

        // <2> 扣减库存
        productService.reduceStock(new ProductReduceStockDTO().setProductId(productId).setAmount(amount));

        // <3> 扣减余额
        accountService.reduceBalance(new AccountReduceBalanceDTO().setUserId(userId).setPrice(price));

        // <4> 保存订单
        OrderDO order = new OrderDO().setUserId(userId).setProductId(productId).setPayAmount(amount * price);
        orderDao.saveOrder(order);
        log.info("[createOrder] 保存订单: {}", order.getId());

        // 返回订单编号
        return order.getId();
    }

}
