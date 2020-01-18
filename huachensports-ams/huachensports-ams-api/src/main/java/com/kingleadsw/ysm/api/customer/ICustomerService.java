package com.kingleadsw.ysm.api.customer;

import com.kingleadsw.ysm.base.VoidEnum;
import com.kingleadsw.ysm.base.dto.PageDTO;
import com.kingleadsw.ysm.base.dto.PaginationDTO;
import com.kingleadsw.ysm.dto.customer.CustomerDTO;

public interface ICustomerService {

    CustomerDTO register(CustomerDTO customerDTO);

    PageDTO<CustomerDTO> getCustomerList(PaginationDTO<CustomerDTO, VoidEnum> paginationDTO);

    /**
     * 解密手机号
     * @param sessionKey
     * @param encryptedData
     * @param iv
     * @return
     */
    String updateCustomerMobileIfNeed(String sessionKey, String encryptedData, String iv);
}
