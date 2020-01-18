package com.kingleadsw.ysm.api.collection;

import com.kingleadsw.ysm.dto.collection.CollectionDTO;

public interface ICollectionService {
    /**
     * 收藏该活动
     * @param collectionDTO
     */
    void collectionActivity(CollectionDTO collectionDTO);

    /**
     * 查询是否已经收藏该活动
     * @return
     */
    Integer getHasCollection(CollectionDTO collectionDTO);

    /**
     * 取消收藏活动
     * @param collectionId
     */
    void cancelCollection(Long collectionId);
}
