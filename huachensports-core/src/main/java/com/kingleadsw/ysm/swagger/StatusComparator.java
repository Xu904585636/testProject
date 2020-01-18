package com.kingleadsw.ysm.swagger;

import com.google.common.primitives.Ints;
import com.kingleadsw.ysm.constants.Status;


import java.util.Comparator;
/**
 * @Auther: zhoujie
 * @Description:
 */
public class StatusComparator
        implements Comparator<Status>
{
    public int compare(Status o1, Status o2)
    {
        return Ints.compare(o2.getCode().intValue(), o1.getCode().intValue());
    }
}
