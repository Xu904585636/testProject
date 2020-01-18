package com.kingleadsw.ysm.service.collection;

import com.kingleadsw.ysm.api.activity.IActivityService;
import com.kingleadsw.ysm.api.collection.ICollectionService;
import com.kingleadsw.ysm.base.BaseService;
import com.kingleadsw.ysm.dto.collection.CollectionDTO;
import com.kingleadsw.ysm.po.activity.ActivityPO;
import com.kingleadsw.ysm.po.activity.ApplyPO;
import com.kingleadsw.ysm.po.collection.CollectionPO;
import com.kingleadsw.ysm.request.RequestCache;
import com.kingleadsw.ysm.utils.ObjectCopys;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionService extends BaseService<CollectionPO> implements ICollectionService {

    @Override
    public void collectionActivity(CollectionDTO collectionDTO) {
        CollectionPO collectionPO = ObjectCopys.maping(collectionDTO, CollectionPO.class);
        collectionPO.allEx(RequestCache.userId());
        this.mapper().insert(collectionPO);
    }

    /**
     * 是否已经收藏该活动
     * @param collectionDTO
     * @return
     */
    @Override
    public Integer getHasCollection(CollectionDTO collectionDTO) {

//        CollectionPO collectionPO = this.mapper().selectOne(ObjectCopys.maping(collectionDTO,CollectionPO.class));
        List<CollectionPO> collectionPOList = this.mapper().selectList(wrapper(ObjectCopys.maping(collectionDTO, CollectionPO.class)));
        if(collectionPOList != null && collectionPOList.size()>0){
            return collectionPOList.get(0).getId().intValue();
        }else{
            return 0;
        }

    }

    /**
     * 取消收藏活动
     * @param collectionId
     */
    @Override
    public void cancelCollection(Long collectionId) {
        this.mapper().deleteById(collectionId);
    }
}
