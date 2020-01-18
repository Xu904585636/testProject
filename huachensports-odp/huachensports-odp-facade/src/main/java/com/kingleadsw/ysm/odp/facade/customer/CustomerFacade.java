package com.kingleadsw.ysm.odp.facade.customer;

import com.kingleadsw.ysm.api.customer.ICustomerService;
import com.kingleadsw.ysm.api.user.IUserService;
import com.kingleadsw.ysm.base.VoidEnum;
import com.kingleadsw.ysm.base.dto.PageDTO;
import com.kingleadsw.ysm.base.dto.PaginationDTO;
import com.kingleadsw.ysm.constants.ServerCode;
import com.kingleadsw.ysm.dto.customer.CustomerDTO;
import com.kingleadsw.ysm.dto.user.UserDTO;
import com.kingleadsw.ysm.exception.UnknowException;
import com.kingleadsw.ysm.need.token.TokenService;
import com.kingleadsw.ysm.odp.vo.user.LoginReqVO;
import com.kingleadsw.ysm.odp.vo.user.LoginVO;
import com.kingleadsw.ysm.request.RequestCache;
import com.kingleadsw.ysm.utils.ObjectCopys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerFacade {

	@Autowired
	private ICustomerService customerService;

	


	/**
	 * 获取用户列表
	 * @param paginationDTO
	 * @return
	 */
	public PageDTO<CustomerDTO> getCustomerList(PaginationDTO<CustomerDTO, VoidEnum> paginationDTO) {
		return customerService.getCustomerList(paginationDTO);
	}
}
