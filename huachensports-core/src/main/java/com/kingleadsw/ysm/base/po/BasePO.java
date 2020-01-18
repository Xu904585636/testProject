package com.kingleadsw.ysm.base.po;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

@Data
public  class BasePO extends  Entity{

    @TableId(
            value = "id",
            type = IdType.AUTO
    )
    private  Long  id;


}
