package cn.johnyu.commons.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 账户减少余额 DTO
 */
@Data
@Accessors(chain = true)
public class AccountReduceBalanceDTO {

    /**
     * 用户编号
     */
    private Long userId;

    /**
     * 扣减金额
     */
    private Integer price;



}