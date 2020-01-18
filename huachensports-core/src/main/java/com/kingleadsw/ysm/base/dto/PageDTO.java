package com.kingleadsw.ysm.base.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Collections;
import java.util.List;

@ApiModel(value="page", description="分页记录")
public class PageDTO<T> extends PaginationDTO
{
    public String toString()
    {
        return "PageDTO(records=" + getRecords() + ", pages=" + getPages() + ")";
    }

    protected boolean canEqual(Object other)
    {
        return other instanceof PageDTO;
    }

    public int hashCode()
    {
        int PRIME = 59;int result = 1;Object $records = getRecords();result = result * 59 + ($records == null ? 43 : $records.hashCode());result = result * 59 + getPages();return result;
    }

    public void setRecords(List<T> records)
    {
        this.records = records;
    }

    public void setPages(Integer pages)
    {
        this.pages = pages;
    }

    public boolean equals(Object o)
    {
        if (o == this) {
            return true;
        }
        if (!(o instanceof PageDTO)) {
            return false;
        }
        PageDTO<?> other = (PageDTO)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Object this$records = getRecords();Object other$records = other.getRecords();
        if (this$records == null ? other$records != null : !this$records.equals(other$records)) {
            return false;
        }
        return getPages() == other.getPages();
    }

    public static class PageDTOBuilder<T>
    {
        @ApiModelProperty("查询结果数据列表")
        private List<T> records;
        private Integer pages;

        public String toString()
        {
            return "PageDTO.PageDTOBuilder(records=" + this.records + ", pages=" + this.pages + ")";
        }

        public PageDTO<T> build()
        {
            return new PageDTO(this.records, this.pages);
        }

        public PageDTOBuilder<T> pages(Integer pages)
        {
            this.pages = pages;return this;
        }

        public PageDTOBuilder<T> records(List<T> records)
        {
            this.records = records;return this;
        }
    }

    public static <T> PageDTOBuilder<T> builder()
    {
        return new PageDTOBuilder();
    }

    public PageDTO(List<T> records, Integer pages)
    {
        this.records = records;this.pages = pages;
    }

    public List<T> getRecords()
    {
        return this.records;
    }

    @ApiModelProperty("查询结果数据列表")
    private List<T> records = Collections.emptyList();
    @ApiModelProperty("总页数")
    private Integer pages;

    public PageDTO(Integer current, Integer size, Integer total, Integer pages)
    {
        this.pages = pages;
        setTotal(total);
        setSize(size);
        setCurrent(current);
    }

    public int getPages()
    {
        int size = getSize().intValue();
        int total = getTotal().intValue();
        if (size == 0) {
            return 0;
        }
        this.pages = Integer.valueOf(total / size);
        Integer localInteger1;
        if (total % size != 0)
        {
            PageDTO<T> localPageDTO = this;localInteger1 = localPageDTO.pages;Integer localInteger2 = localPageDTO.pages = Integer.valueOf(localPageDTO.pages.intValue() + 1);
        }
        return this.pages.intValue();
    }

    public PageDTO() {}
}
