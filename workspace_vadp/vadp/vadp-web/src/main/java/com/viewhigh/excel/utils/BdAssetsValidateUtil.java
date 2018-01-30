package com.viewhigh.excel.utils;

import com.viewhigh.entity.BdAssets;
import com.viewhigh.excel.domain.entity.ReAssetsOriginal;
import com.viewhigh.vadp.framework.util.UUIDGenerater;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author zhaoxizi
 * @Date 18/1/9下午5:32
 */
@Component
public class BdAssetsValidateUtil {


  public String   validate(List<BdAssets> list, ReAssetsOriginal rao){

      for (BdAssets ba:
              list) {
          if(StringUtils.equals(ba.getAssetsCode(), rao.getAssetsCode())){
              return ba.getPkAssets();
          }

      }
      BdAssets ba=new BdAssets();
      String id = UUIDGenerater.generateId();
      ba.setAssetsCode(rao.getAssetsCode());
      ba.setAssetsName(rao.getAssetsName());
      ba.setDurableYears(rao.getDurableYears());
      ba.setOriginalValue(rao.getOriginalValue());
      ba.setStartDate(rao.getStartDate());
      ba.setAssetsType(rao.getAssetsType());

      ba.setPkAssets(id);
      list.add(ba);
      return id;
  }

}
