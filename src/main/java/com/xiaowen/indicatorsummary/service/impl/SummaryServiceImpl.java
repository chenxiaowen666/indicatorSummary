package com.xiaowen.indicatorsummary.service.impl;

import com.xiaowen.indicatorsummary.service.SummaryService;
import com.xiaowen.indicatorsummary.vo.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SummaryServiceImpl implements SummaryService {



    @Override
    public List<BigSummarySectionVO> getSummary(BigSummaryReqVO reqVO) {
        // TODO: 这里应从数据库或其他服务获取多次调用结果，拼成 sparseList
        // 示例：每个指标单独返回一个 List<BigSummarySectionVO>
        List<BigSummarySectionVO> sparseList = new ArrayList<>();

        // 模拟：你应替换为真实的数据查询逻辑
        // sparseList.addAll(queryIndicator1(reqVO));
        // sparseList.addAll(queryIndicator2(reqVO));
        // ...

        // 合并所有稀疏指标数据
        return mergeSummary(sparseList);
    }

    /**
     * 合并多个稀疏的指标结构为完整的 List<BigSummarySectionFinalVO>
     * 每个模块/团队/科室的 indicator1-11 / teamSummary1-11 / sectionSummary1-11 尽可能填满
     */
    public List<BigSummarySectionVO> mergeSummary(List<BigSummarySectionVO> sparseList) {
        // sectionCode -> SectionVO 映射
        Map<String, BigSummarySectionVO> sectionMap = new LinkedHashMap<>();

        for (BigSummarySectionVO section : sparseList) {
            String sectionKey = section.getSectionCode();
            BigSummarySectionVO mergedSection = sectionMap.computeIfAbsent(sectionKey, k -> {
                BigSummarySectionVO newSection = new BigSummarySectionVO();
                newSection.setSectionCode(section.getSectionCode());
                newSection.setSectionName(section.getSectionName());
                newSection.setTeams(new ArrayList<>());
                return newSection;
            });

            // 合并 sectionSummary1~11
            mergeSectionSummaries(mergedSection, section);

            // 合并团队
            if (section.getTeams() != null) {
                for (BigSummaryTeamVO team : section.getTeams()) {
                    String teamKey = team.getSectionTeamId();
                    List<BigSummaryTeamVO> mergedTeams = mergedSection.getTeams();

                    // 找或新建团队
                    BigSummaryTeamVO mergedTeam = mergedTeams.stream()
                            .filter(t -> teamKey.equals(t.getSectionTeamId()))
                            .findFirst()
                            .orElseGet(() -> {
                                BigSummaryTeamVO newTeam = new BigSummaryTeamVO();
                                newTeam.setSectionTeamId(team.getSectionTeamId());
                                newTeam.setTeamName(team.getTeamName());
                                newTeam.setModules(new ArrayList<>());
                                mergedTeams.add(newTeam);
                                return newTeam;
                            });

                    // 合并 teamSummary1~11
                    mergeTeamSummaries(mergedTeam, team);

                    // 合并模块
                    if (team.getModules() != null) {
                        for (BigSummaryModuleVO module : team.getModules()) {
                            String moduleKey = module.getModuleCode();
                            List<BigSummaryModuleVO> mergedModules = mergedTeam.getModules();

                            BigSummaryModuleVO mergedModule = mergedModules.stream()
                                    .filter(m -> moduleKey.equals(m.getModuleCode()))
                                    .findFirst()
                                    .orElseGet(() -> {
                                        BigSummaryModuleVO newModule = new BigSummaryModuleVO();
                                        newModule.setModuleCode(module.getModuleCode());
                                        newModule.setModuleName(module.getModuleName());
                                        mergedModules.add(newModule);
                                        return newModule;
                                    });

                            // 合并指标 indicator1~11
                            mergeIndicators(mergedModule, module);
                        }
                    }
                }
            }
        }

        return new ArrayList<>(sectionMap.values());
    }

    // 合并模块指标
    private void mergeIndicators(BigSummaryModuleVO target, BigSummaryModuleVO source) {
        if (target.getIndicator1() == null) { target.setIndicator1(source.getIndicator1()); }
        if (target.getIndicator2() == null) { target.setIndicator2(source.getIndicator2()); }
        if (target.getIndicator3() == null) { target.setIndicator3(source.getIndicator3()); }
        if (target.getIndicator4() == null) { target.setIndicator4(source.getIndicator4()); }
        if (target.getIndicator5() == null) { target.setIndicator5(source.getIndicator5()); }
        if (target.getIndicator6() == null) { target.setIndicator6(source.getIndicator6()); }
        if (target.getIndicator7() == null) { target.setIndicator7(source.getIndicator7()); }
        if (target.getIndicator8() == null) { target.setIndicator8(source.getIndicator8()); }
        if (target.getIndicator9() == null) { target.setIndicator9(source.getIndicator9()); }
        if (target.getIndicator10() == null) { target.setIndicator10(source.getIndicator10()); }
        if (target.getIndicator11() == null) { target.setIndicator11(source.getIndicator11()); }
    }

    // 合并团队汇总指标
    private void mergeTeamSummaries(BigSummaryTeamVO target, BigSummaryTeamVO source) {
        if (target.getTeamSummary1() == null) { target.setTeamSummary1(source.getTeamSummary1()); }
        if (target.getTeamSummary2() == null) { target.setTeamSummary2(source.getTeamSummary2()); }
        if (target.getTeamSummary3() == null) { target.setTeamSummary3(source.getTeamSummary3()); }
        if (target.getTeamSummary4() == null) { target.setTeamSummary4(source.getTeamSummary4()); }
        if (target.getTeamSummary5() == null) { target.setTeamSummary5(source.getTeamSummary5()); }
        if (target.getTeamSummary6() == null) { target.setTeamSummary6(source.getTeamSummary6()); }
        if (target.getTeamSummary7() == null) { target.setTeamSummary7(source.getTeamSummary7()); }
        if (target.getTeamSummary8() == null) { target.setTeamSummary8(source.getTeamSummary8()); }
        if (target.getTeamSummary9() == null) { target.setTeamSummary9(source.getTeamSummary9()); }
        if (target.getTeamSummary10() == null) { target.setTeamSummary10(source.getTeamSummary10()); }
        if (target.getTeamSummary11() == null) { target.setTeamSummary11(source.getTeamSummary11()); }
    }

    // 合并科室汇总指标
    private void mergeSectionSummaries(BigSummarySectionVO target, BigSummarySectionVO source) {
        if (target.getSectionSummary1() == null) { target.setSectionSummary1(source.getSectionSummary1()); }
        if (target.getSectionSummary2() == null) { target.setSectionSummary2(source.getSectionSummary2()); }
        if (target.getSectionSummary3() == null) { target.setSectionSummary3(source.getSectionSummary3()); }
        if (target.getSectionSummary4() == null) { target.setSectionSummary4(source.getSectionSummary4()); }
        if (target.getSectionSummary5() == null) { target.setSectionSummary5(source.getSectionSummary5()); }
        if (target.getSectionSummary6() == null) { target.setSectionSummary6(source.getSectionSummary6()); }
        if (target.getSectionSummary7() == null) { target.setSectionSummary7(source.getSectionSummary7()); }
        if (target.getSectionSummary8() == null) { target.setSectionSummary8(source.getSectionSummary8()); }
        if (target.getSectionSummary9() == null) { target.setSectionSummary9(source.getSectionSummary9()); }
        if (target.getSectionSummary10() == null) { target.setSectionSummary10(source.getSectionSummary10()); }
        if (target.getSectionSummary11() == null) { target.setSectionSummary11(source.getSectionSummary11()); }
    }

}
