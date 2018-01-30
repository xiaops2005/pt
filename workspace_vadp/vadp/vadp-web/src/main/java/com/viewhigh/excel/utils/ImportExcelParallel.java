package com.viewhigh.excel.utils;

import com.viewhigh.excel.domain.Base;
import com.viewhigh.excel.domain.ExcelFile;
import com.viewhigh.excel.domain.Limit;
import com.viewhigh.vadp.framework.base.service.BaseServiceImpl;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;


public class ImportExcelParallel extends BaseServiceImpl implements Callable<List<? extends Base>> {
    ExcelFile excelFile;
    Limit limit;
    boolean isHeaderThread;
    List<?> t;
    Session s ;

    private final static Logger log = LoggerFactory
            .getLogger(ExcelUtilityParallelReader.class);


    public ImportExcelParallel(List<?> t, Limit limit,boolean isHeaderThread,Session s ) {
        super();
        this.t= t;
        this.limit = limit;
        this.isHeaderThread = isHeaderThread;
        this.s=s;
    }


    @Override
    public List<? extends Base> call() throws Exception {
        return insertDb(this.t,this.isHeaderThread,this.limit.getStartRow(),limit.getEndRow(),this.s);
    }
    /**
     *读取并解析excel指定行数
     * sheet 工作表
     * fileTemplate json横版
     * clazz 要转换的entity类
     * hasHeaderRow 是否包括表头
     * startRow 开始行
     * endRow 结束行
     * validator 校验类
     * */
    public  <T extends Base>  List<T> insertDb(List<?> t, boolean hasHeaderRow, int startRow, int endRow,Session s) {
        List<T> objList = null;
        T obj ;
        try {
            objList = new ArrayList<T>();
            for(int currRow = startRow; currRow <= endRow; currRow++){
                    s.saveOrUpdate(t.get(currRow));
            }
        } catch (Exception e) {
            log.error("插入数据库失败" + e.getMessage());
            e.printStackTrace();
        }
        return objList;
    }
}
