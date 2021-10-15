package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;

public interface PromotionAdService {

    /**
     * 分页获取所有广告列表
     * @param adVO
     * @return
     */
    public PageInfo findAllAdByPage(PromotionAdVO adVO);

    /**
     * 新增或更新广告位置
     */
    void savePromotionAd(PromotionAd promotionAd);

    void updatePromotionAd(PromotionAd promotionAd);

    /**
     * 回显广告信息
     */
    public PromotionAd findPromotionAdById(int id);

    void updatePromotionAdStatus(int id,int status);
}
