package com.viewhigh.service;

import com.viewhigh.entity.ApproveRecord;
import com.viewhigh.entity.ReMateDetail;
import com.viewhigh.excel.domain.entity.BdMaterials;

import java.util.List;

public interface IApproveRecordService {
    List<ApproveRecord> findAll();
}
