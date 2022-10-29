package cn.johnyu.feign;


import cn.johnyu.commons.dto.ProductReduceStockDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * `product-cn.johnyu.service` 服务的 Feign 客户端
 */
@FeignClient(name = "product-service")
public interface ProductServiceFeignClient {

    @PostMapping("/product/reduce-stock")
    void reduceStock(@RequestBody ProductReduceStockDTO productReduceStockDTO);

}