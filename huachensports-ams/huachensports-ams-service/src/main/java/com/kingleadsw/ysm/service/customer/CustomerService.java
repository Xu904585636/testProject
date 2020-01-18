package com.kingleadsw.ysm.service.customer;

import com.baomidou.mybatisplus.plugins.Page;
import com.kingleadsw.ysm.api.customer.ICustomerService;
import com.kingleadsw.ysm.base.BaseService;
import com.kingleadsw.ysm.base.VoidEnum;
import com.kingleadsw.ysm.base.dto.PageDTO;
import com.kingleadsw.ysm.base.dto.PaginationDTO;
import com.kingleadsw.ysm.constants.ServerCode;
import com.kingleadsw.ysm.dao.customer.ICustomerMapper;
import com.kingleadsw.ysm.dto.customer.CustomerDTO;
import com.kingleadsw.ysm.dto.user.UserDTO;
import com.kingleadsw.ysm.exception.UnknowException;
import com.kingleadsw.ysm.po.customer.CustomerPO;
import com.kingleadsw.ysm.po.user.UserPO;
import com.kingleadsw.ysm.utils.Asserts;
import com.kingleadsw.ysm.utils.ObjectCopys;
import com.kingleadsw.ysm.wechat.MiniprogramDecryptData;
import com.kingleadsw.ysm.wechat.WxSign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService extends BaseService<CustomerPO> implements ICustomerService {

    @Autowired
    private ICustomerMapper customerMapper;

    @Override
    public CustomerDTO register(CustomerDTO customerDTO) {

        CustomerPO customerPO = this.mapper().selectOne(CustomerPO.builder().openid(customerDTO.getOpenid()).build());
        //如果用户存在
        if(Asserts.isNotNullOrEmpty(customerPO) && Asserts.isNotNullOrEmpty(customerPO.getId())){
            return ObjectCopys.maping(customerPO,CustomerDTO.class);
        }else{
            CustomerPO po = ObjectCopys.maping(customerDTO, CustomerPO.class);
            po.allEx(1L);
            this.mapper().insert(po);
            return ObjectCopys.maping(po, CustomerDTO.class);
        }
    }

    @Override
    public PageDTO<CustomerDTO> getCustomerList(PaginationDTO<CustomerDTO, VoidEnum> paginationDTO) {
        Page<CustomerPO> poPage = this.getPage(paginationDTO);

        List<CustomerPO> list = customerMapper.getCustomerList(poPage, poPage.getCondition());

        return this.getPage(poPage, ObjectCopys.mappingAll(ObjectCopys.mappingAll(list, CustomerDTO.class), CustomerDTO.class));
    }

    @Override
    public String updateCustomerMobileIfNeed(String sessionKey, String encryptedData, String iv) {
        String mobile = ecrypt(sessionKey, encryptedData, iv);
        if (Asserts.isNotNullOrEmpty(mobile)) {
            return mobile;
        } else {
            throw new UnknowException(ServerCode.BadRequest.BAD_REQUEST_INVALID, "您的微信未绑定手机号码");
        }
    }

    /**
     * 解密encryptedData，获取用户手机号码
     *
     */
    private String ecrypt(String sessionKey, String encryptedData, String iv) {
        if (Asserts.isNotNullOrEmpty(encryptedData) && Asserts.isNotNullOrEmpty(iv)) {
            MiniprogramDecryptData userInfo = WxSign.ecryptMiniprogramUserData(sessionKey, encryptedData, iv);
            if (userInfo != null && Asserts.isNotNullOrEmpty(userInfo.getPurePhoneNumber())) {
                return userInfo.getPurePhoneNumber();
            } else {
                throw new UnknowException(ServerCode.BadRequest.BAD_REQUEST_INVALID,"您的微信未绑定手机号码");
            }
        }
        return null;
    }
}
