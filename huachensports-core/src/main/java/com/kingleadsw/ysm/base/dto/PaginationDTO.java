package com.kingleadsw.ysm.base.dto;

import com.google.common.collect.Lists;
import com.kingleadsw.ysm.lang.Instances;
import com.kingleadsw.ysm.utils.Asserts;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@ApiModel(value="pagination", description="分页条件")
public class PaginationDTO<K, E>
        implements Serializable
{
    private static final int MAX_PAGE_SIZE = 100000;
    @ApiModelProperty("查询参数")
    private K condition;
    @ApiModelProperty("总记录数,以客户端为准，如果客户端传>0的值，服务端不再查询总数，直接把该值返回")
    private Integer total;
    @ApiModelProperty(value="每页显示条数，默认 10", example="10")
    private Integer size;

    public String toString()
    {
        return "PaginationDTO(condition=" + getCondition() + ", total=" + getTotal() + ", size=" + getSize() + ", current=" + getCurrent() + ", orderByField=" + getOrderByField() + ", ascs=" + getAscs() + ", descs=" + getDescs() + ", asc=" + isAsc() + ")";
    }

    public int hashCode()
    {
        int PRIME = 59;int result = 1;Object $condition = getCondition();result = result * 59 + ($condition == null ? 43 : $condition.hashCode());Object $total = getTotal();result = result * 59 + ($total == null ? 43 : $total.hashCode());Object $size = getSize();result = result * 59 + ($size == null ? 43 : $size.hashCode());Object $current = getCurrent();result = result * 59 + ($current == null ? 43 : $current.hashCode());Object $orderByField = getOrderByField();result = result * 59 + ($orderByField == null ? 43 : $orderByField.hashCode());Object $ascs = getAscs();result = result * 59 + ($ascs == null ? 43 : $ascs.hashCode());Object $descs = getDescs();result = result * 59 + ($descs == null ? 43 : $descs.hashCode());result = result * 59 + (isAsc() ? 79 : 97);return result;
    }

    protected boolean canEqual(Object other)
    {
        return other instanceof PaginationDTO;
    }

    public boolean equals(Object o)
    {
        if (o == this) {
            return true;
        }
        if (!(o instanceof PaginationDTO)) {
            return false;
        }
        PaginationDTO<?, ?> other = (PaginationDTO)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Object this$condition = getCondition();Object other$condition = other.getCondition();
        if (this$condition == null ? other$condition != null : !this$condition.equals(other$condition)) {
            return false;
        }
        Object this$total = getTotal();Object other$total = other.getTotal();
        if (this$total == null ? other$total != null : !this$total.equals(other$total)) {
            return false;
        }
        Object this$size = getSize();Object other$size = other.getSize();
        if (this$size == null ? other$size != null : !this$size.equals(other$size)) {
            return false;
        }
        Object this$current = getCurrent();Object other$current = other.getCurrent();
        if (this$current == null ? other$current != null : !this$current.equals(other$current)) {
            return false;
        }
        Object this$orderByField = getOrderByField();Object other$orderByField = other.getOrderByField();
        if (this$orderByField == null ? other$orderByField != null : !this$orderByField.equals(other$orderByField)) {
            return false;
        }
        Object this$ascs = getAscs();Object other$ascs = other.getAscs();
        if (this$ascs == null ? other$ascs != null : !this$ascs.equals(other$ascs)) {
            return false;
        }
        Object this$descs = getDescs();Object other$descs = other.getDescs();
        if (this$descs == null ? other$descs != null : !this$descs.equals(other$descs)) {
            return false;
        }
        return isAsc() == other.isAsc();
    }

    public void setAsc(boolean asc)
    {
        this.asc = asc;
    }

    public void setAscs(List<String> ascs)
    {
        this.ascs = ascs;
    }

    public void setOrderByField(E orderByField)
    {
        this.orderByField = orderByField;
    }

    public void setCurrent(Integer current)
    {
        this.current = current;
    }

    public void setSize(Integer size)
    {
        this.size = size;
    }

    public void setTotal(Integer total)
    {
        this.total = total;
    }

    public void setDescs(List<String> descs)
    {
        this.descs = descs;
    }

    public void setCondition(K condition)
    {
        this.condition = condition;
    }

    public K getCondition()
    {
        return this.condition;
    }

    public Integer getTotal()
    {
        return this.total;
    }

    public PaginationDTO()
    {
        this.size = Integer.valueOf(10);
    }

    public Integer getSize()
    {
        return this.size;
    }

    public Integer getCurrent()
    {
        return this.current;
    }

    @ApiModelProperty(value="每页显示条数，默认 10", required=true, allowableValues="range[1, 100000]", example="1")
    @Min(message="当前页不能低于1", value=1L)
    @Max(message="当前页不能超过100000", value=100000L)
    private Integer current = Integer.valueOf(1);
    @ApiModelProperty(value="排序字段,为空则不要传参,或者orderByField=null,否则服务端会报错", example="没有请删除")
    private E orderByField;
    @ApiModelProperty(value="升序排序集合,为空则不要传参,或者orderByField=null,否则服务端会报错", example="没有请删除")
    private List<String> ascs;
    @ApiModelProperty(value="降序排序集合,为空则不要传参,或者orderByField=null,否则服务端会报错", example="没有请删除")
    private List<String> descs;
    @ApiModelProperty(value="是否为升序 ASC[ 默认： true],降序:false", example="false")
    private boolean asc;

    public E getOrderByField()
    {
        return this.orderByField;
    }

    public List<String> getAscs()
    {
        return this.ascs;
    }

    public List<String> getDescs()
    {
        return this.descs;
    }

    public boolean isAsc()
    {
        return this.asc;
    }

    public PaginationDTO<K, E> ascs(String... ascs)
    {
        if (Asserts.isNull(this.ascs)) {

            setAscs(  Lists.newArrayList(ascs));
        } else {
            Collections.addAll(getAscs(), ascs);
        }
        return this;
    }

    public PaginationDTO<K, E> descs(String... descs)
    {
        if (Asserts.isNull(this.descs)) {
            setDescs(Lists.newArrayList(descs));
        } else {
            Collections.addAll(getDescs(), descs);
        }
        return this;
    }

    public K condition(Class<K> v)
    {
        if (getCondition() == null)
        {
            K i = Instances.newInstance(v);
            setCondition(i);
        }
        return getCondition();
    }

    public PaginationDTO<K, E> maxSize()
    {
        this.size = Integer.valueOf(100000);
        return this;
    }

    public PaginationDTO<K, E> maxSize(int maxSize)
    {
        this.size = Integer.valueOf(maxSize);
        return this;
    }
}
