package com.lagou.dao;


import com.lagou.domain.PromotionAd;

import java.util.List;

public interface PromotionAdMapper {

    /**
     * 分页查询所有的广告列表
     * @return
     */
    public List<PromotionAd> findAllAdByPage();

    void savePromotionAd(PromotionAd promotionAd);

    void updatePromotionAd(PromotionAd promotionAd);

    public PromotionAd findPromotionAdById(int id);

    /**
     * 广告状态上下线
     */
    void updatePromotionAdStatus(PromotionAd promotionAd);
}
