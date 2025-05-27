package com.xiaowen.indicatorsummary.service;

import com.xiaowen.indicatorsummary.vo.BigSummaryReqVO;
import com.xiaowen.indicatorsummary.vo.BigSummarySectionVO;

import java.util.List;

public interface SummaryService {

    List<BigSummarySectionVO> getSummary(BigSummaryReqVO bigSummaryReqVO);

}
