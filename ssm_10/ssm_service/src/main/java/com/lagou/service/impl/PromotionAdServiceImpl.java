package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.PromotionAdMapper;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PromotionAdServiceImpl implements PromotionAdService {

    @Autowired
    PromotionAdMapper adMapper;

    /**
     * 分页查询广告列表
     *
     * @param adVO
     * @return
     */
    @Override
    public PageInfo findAllAdByPage(PromotionAdVO adVO) {

        PageHelper.startPage(adVO.getCurrentPage(), adVO.getPageSize());
        List<PromotionAd> allAd = adMapper.findAllAdByPage();
        PageInfo<PromotionAd> adPageInfo = new PageInfo<>(allAd);
        return adPageInfo;

    }

    @Override
    public void savePromotionAd(PromotionAd promotionAd) {
        adMapper.savePromotionAd(promotionAd);
    }

    @Override
    public void updatePromotionAd(PromotionAd promotionAd) {
        adMapper.updatePromotionAd(promotionAd);
    }

    @Override
    public PromotionAd findPromotionAdById(int id) {
        PromotionAd promotionAd = adMapper.findPromotionAdById(id);
        return promotionAd;
    }

    @Override
    public void updatePromotionAdStatus(int id, int status) {
        PromotionAd promotionAd = new PromotionAd();
        promotionAd.setUpdateTime(new Date());
        promotionAd.setId(id);
        promotionAd.setStatus(status);
        adMapper.updatePromotionAdStatus(promotionAd);
    }
}


