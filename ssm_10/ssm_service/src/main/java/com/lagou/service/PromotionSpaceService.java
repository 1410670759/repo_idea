package com.lagou.service;


import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionSpace;

import java.util.List;

public interface PromotionSpaceService {
    /**
     * 查询所有广告位
     * @return
     */
    public List<PromotionSpace> findAllPromotionSpace();

    /**\
     * 保存广告位
     * @param promotionSpace
     */
    public void savePromotionSpace(PromotionSpace promotionSpace);

    /**
     * 更新广告位信息
     * @param promotionSpace
     */
    public void updatePromotionSpace(PromotionSpace promotionSpace);

    /*
       根据Id查询广告位信息
  */
    public PromotionSpace findPromotionSpaceById(int id);



}
