package com.kingleadsw.ysm.api.activity;

import com.kingleadsw.ysm.dto.activity.ActivityDTO;
import com.kingleadsw.ysm.dto.activity.ScoreDTO;

public interface IScoreService {

    /**
     * 获取成绩信息
     * @param activityId
     * @return
     */
    ScoreDTO getScoreInfo(Long activityId);


    /**
     * 修改活动成绩信息
     * @param scoreDTO
     */
    void updateScore(ScoreDTO scoreDTO);

    /**
     * 新增活动成绩信息
     * @param scoreDTO
     * @return
     */
    void insertScore(ScoreDTO scoreDTO);
}
