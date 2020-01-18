package com.kingleadsw.ysm.dao.customer;

import com.baomidou.mybatisplus.plugins.Page;
import com.kingleadsw.ysm.base.VoidEnum;
import com.kingleadsw.ysm.base.dto.PageDTO;
import com.kingleadsw.ysm.base.dto.PaginationDTO;
import com.kingleadsw.ysm.base.mapper.IBaseMapper;
import com.kingleadsw.ysm.po.customer.CustomerPO;
import com.kingleadsw.ysm.po.user.UserPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ICustomerMapper extends IBaseMapper<CustomerPO> {

    List<CustomerPO> getCustomerList(Page<CustomerPO> poPage, @Param("cm") Map<String, Object> condition);
}
