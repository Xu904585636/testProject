package com.kingleadsw.ysm.base;

import java.util.List;
import java.util.Map;

import com.kingleadsw.ysm.base.dto.PageDTO;
import com.kingleadsw.ysm.base.dto.PaginationDTO;
import com.kingleadsw.ysm.base.dto.ResultDTO;
import com.kingleadsw.ysm.base.mapper.IBaseMapper;
import com.kingleadsw.ysm.base.po.BasePO;
import com.kingleadsw.ysm.request.RequestCache;
import com.kingleadsw.ysm.utils.Asserts;
import com.kingleadsw.ysm.utils.ObjectCopys;
import com.kingleadsw.ysm.utils.Statuss;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;


@Log4j2
@Data
public abstract class BaseService<T extends BasePO> implements ApplicationContextAware
{

    protected boolean canEqual(Object object)
    {
        return  object instanceof BaseService;
    }



    public void setMapper(IBaseMapper<T> mapper)
    {
        this.mapper = mapper;
    }



    private static final int MAX_THREAD = 5;
    private static final int RANDOM = 10;

    @Autowired
    private IBaseMapper<T> mapper;

    private ApplicationContext applicationContext;

    public IBaseMapper<T> getMapper()
    {
        return this.mapper;
    }

    public int maxThread()
    {
        return 5;
    }

    public void setApplicationContext(ApplicationContext applicationContext)
    {
        this.applicationContext = applicationContext;
    }

    public <K, E> Page<T> getPage(PaginationDTO<K, E> pagination)
    {
        Page<T> pagePO = new Page(pagination.getCurrent().intValue(), pagination.getSize().intValue());
        K condition = pagination.getCondition();
        if (Asserts.notNull(pagination.getTotal()))
        {
            pagePO.setTotal(pagination.getTotal().intValue());
            pagePO.setSearchCount(false);
        }

        boolean asc = pagination.isAsc();
        if (condition != null) {
            pagePO.setCondition(ObjectCopys.toMap(condition));
        }
        pagePO.setAscs(pagination.getAscs());
        pagePO.setDescs(pagination.getDescs());
        if (!asc) {
            pagePO.setAsc(asc);
        }
        return pagePO;
    }

    public <K> PageDTO<K> getPage(Page<T> page)
    {
        return new PageDTO(Integer.valueOf(page.getCurrent()), Integer.valueOf(page.getSize()), Integer.valueOf(page.getTotal()), Integer.valueOf(page.getPages()));
    }

    public <K> PageDTO<K> getPage(Page<T> page, List<K> records)
    {
        PageDTO<K> p = getPage(page);
        p.setRecords(records);
        return p;
    }

    public EntityWrapper<T> wrapper()
    {
        return new EntityWrapper();
    }

    public EntityWrapper<T> wrapper(T v)
    {
        return new EntityWrapper(v);
    }


    @Transactional
    public void delete(Long id)
    {
        this.mapper.deleteById(id);
    }




    public Long userId()
    {
        Long uid = RequestCache.userId();
        if (uid == null) {
            uid = Long.valueOf(1L);
        }
        return uid;
    }

    @Transactional
    public Integer deleteByMap(Map<String, Object> columnMap)
    {
        return this.mapper.deleteByMap(columnMap);
    }

    @Transactional
    public Integer deleteByEntity(T t)
    {
        Wrapper<T> wrapper = new EntityWrapper(t);
        return this.mapper.delete(wrapper);
    }


    public ApplicationContext getApplicationContext()
    {
        return this.applicationContext;
    }

    protected IBaseMapper<T> mapper()
    {
        return getMapper();
    }

    @Deprecated
    protected <E> ResultDTO<E> success()
    {
        return success(200001);
    }

    @Deprecated
    protected <E> ResultDTO<E> success(int code)
    {
        return success(code, null);
    }

    @Deprecated
    protected <E> ResultDTO<E> success(E data)
    {
        return success(200001, data);
    }

    @Deprecated
    protected <E> ResultDTO<E> success(int code, E data)
    {
        ResultDTO.ResultDTOBuilder<E> r = ResultDTO.builder();
        return r.code(Integer.valueOf(code)).msg(Statuss.getMsg(Integer.valueOf(code))).data(data).build();
    }
}
