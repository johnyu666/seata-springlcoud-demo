package cn.johnyu.commons.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 商品减少库存 DTO
 */
@Data
@Accessors(chain = true)
public class ProductReduceStockDTO {

    /**
     * 商品编号
     */
    private Long productId;
    /**
     * 数量
     */
    private Integer amount;

}