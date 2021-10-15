package com.lagou.dao;

import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionSpace;

import java.util.List;

public interface PromotionSpaceMapper {
    /**
     * 获取所有的广告位
     * @return
     */
    public List<PromotionSpace> findAllPromotionSpace();

    /**
     * 保存广告位信息
     * @param promotionSpace
     */
    public void savePromotionSpace(PromotionSpace promotionSpace);

    /**
     * 修改广告位信息
     * @param promotionSpace
     */
    public void updatePromotionSpace(PromotionSpace promotionSpace);


    public PromotionSpace findPromotionSpaceById(int id);


}
