package com.viewhigh.excel.utils;

import com.viewhigh.excel.domain.Base;
import com.viewhigh.excel.domain.ExcelFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Component
public class ExcelUtilityParallelProcessor {

    //创建 可用处理器的Java虚拟机的数量*16 的可重用固定线程数的线程池 executorService
    ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 16);

    private final static Logger log = LoggerFactory
            .getLogger(ExcelUtilityParallelProcessor.class);

    public <T extends Base> List<T> readExcelInParallel(ExcelFile excelFile) throws Exception {

        List<T> list = new ArrayList<T>();
        List<T> t = new ArrayList<T>();
        Long startDate = new Date().getTime();
        log.info("start time ~~~~~~~~~~~~~~~" + startDate);

        try {
            List<Future<List<? extends Base>>> results = new ArrayList<Future<List<? extends Base>>>(excelFile.getNoOfthreadsToProcess());

            for (int i : excelFile.getSheetThreads().keySet()) {
                //執行並存儲任務結果，线程池执行ExcelUtilityParallelReader的call方法，并使用results.add方法存储
                results.add(executorService.submit(new ExcelUtilityParallelReader(excelFile, excelFile.getSheetThreads().get(i), i == 1)));
            }

            log.info("list assembling ~~~~~~~~~~~~~~~");

            for (Future<List<? extends Base>> result : results) {
                list.addAll((Collection<? extends T>) result.get());
            }

        } catch (Exception e) {
            throw e;
        }
        Long endDate = new Date().getTime();
        log.info("end time ~~~~~~~~~~~~~~~" + endDate);
        log.info("cost time ~~~~~~~~~~~~~~~" + (endDate - startDate) + " ms");
        return list;
    }

    @Override
    protected void finalize() throws Throwable {
        // TODO Auto-generated method stub
        super.finalize();
        try {
            executorService.shutdown();
        } catch (Exception e) {
            // TODO: handle exception
            log.info(e.getMessage());
            e.printStackTrace();
        }
    }

}
