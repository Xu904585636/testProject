package com.kingleadsw.ysm.service.activity;

import com.kingleadsw.ysm.api.activity.IActivityService;
import com.kingleadsw.ysm.api.activity.IScoreService;
import com.kingleadsw.ysm.base.BaseService;
import com.kingleadsw.ysm.dao.activity.IScoreMapper;
import com.kingleadsw.ysm.dto.activity.ScoreDTO;
import com.kingleadsw.ysm.po.activity.ActivityPO;
import com.kingleadsw.ysm.po.activity.ScorePO;
import com.kingleadsw.ysm.request.RequestCache;
import com.kingleadsw.ysm.utils.ObjectCopys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreService extends BaseService<ScorePO> implements IScoreService {

    @Autowired
    private IScoreMapper scoreMapper;

    @Override
    public ScoreDTO getScoreInfo(Long activityId) {
        List<ScorePO> list = scoreMapper.selectList(wrapper(ScorePO.builder().activityId(activityId).build()).orderBy("createTime", false));

        if (list != null && list.size() > 0) {
            String scoreImg = "";
            for (ScorePO scorePO : list) {
                scoreImg = scoreImg + scorePO.getScoreUrl() + ",";
            }
            ScorePO scorePO = list.get(0);
            scorePO.setScoreUrl(scoreImg);
            return ObjectCopys.maping(scorePO, ScoreDTO.class);
        } else {
            return new ScoreDTO();
        }
    }

    @Override
    public void updateScore(ScoreDTO scoreDTO) {
        ScorePO scorePO = ObjectCopys.maping(scoreDTO, ScorePO.class);
        scorePO.allEx(RequestCache.userId());
        scoreMapper.update(scorePO,wrapper().eq("id",scoreDTO.getId()));
    }

    @Override
    public void insertScore(ScoreDTO scoreDTO) {
        ScorePO scorePO = ObjectCopys.maping(scoreDTO, ScorePO.class);
        scorePO.allEx(RequestCache.userId());
        scoreMapper.insert(scorePO);
    }
}
