package com.xiaowen.indicatorsummary.controller;

import com.xiaowen.indicatorsummary.service.SummaryService;
import com.xiaowen.indicatorsummary.vo.BigSummaryReqVO;
import com.xiaowen.indicatorsummary.vo.BigSummarySectionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SummaryController {
    @Autowired
    private SummaryService summaryService;

    @PostMapping("/summary")
    public List<BigSummarySectionVO> getSummary(BigSummaryReqVO bigSummaryReqVO) {

        // 调用 service 获取汇总数据
        List<BigSummarySectionVO> summaryList = summaryService.getSummary(bigSummaryReqVO); // 传入实际的请求参数

        // 返回结果
        return summaryList;

    }

}
