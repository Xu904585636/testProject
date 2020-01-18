package com.kingleadsw.ysm.mtp.facade.customer;

import com.kingleadsw.ysm.api.customer.ICustomerService;
import com.kingleadsw.ysm.dto.customer.CustomerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerFacade {
    @Autowired
    private ICustomerService customerService;

    /**
     * 微信小程序端注册用户信息
     * @param customerDTO
     * @return
     */
    public CustomerDTO register(CustomerDTO customerDTO){
        customerDTO = customerService.register(customerDTO);
        return customerDTO;
    }

    /**
     * 解密手机号
     * @param sessionKey
     * @param encryptedData
     * @param iv
     * @return
     */
    public String updateCustomerMobileIfNeed(String sessionKey, String encryptedData, String iv) {
        return customerService.updateCustomerMobileIfNeed(sessionKey,encryptedData,iv);
    }
}
