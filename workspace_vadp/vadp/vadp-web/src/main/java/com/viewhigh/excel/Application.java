package com.viewhigh.excel;


import static com.viewhigh.excel.common.ApplicationConstants.BOOLEAN;
import static com.viewhigh.excel.common.ApplicationConstants.DECIMAL;
import static com.viewhigh.excel.common.ApplicationConstants.DOUBLE;
import static com.viewhigh.excel.common.ApplicationConstants.INTEGER;
import static com.viewhigh.excel.common.ApplicationConstants.LONG;
import static com.viewhigh.excel.common.ApplicationConstants.TIMESTAMP;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlElement;

import com.viewhigh.excel.utils.ClazzUtil;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.viewhigh.excel.common.ApplicationConstants;
import com.viewhigh.excel.domain.ReMateDetail;
import com.viewhigh.excel.domain.entity.Demo;
import com.viewhigh.excel.handler.ColumnTemplate;
import com.viewhigh.excel.handler.FileTemplate;


@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {

    public static String firstCharToLowerCase(String s) {
        return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    /**
     * 生成JSON文件
     *
     * @param cls        要生成JSON文件的类
     * @param primaryKey 数据库中的主键字段
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static void generateJsonFile(Class cls, String primaryKey) throws IllegalArgumentException, IllegalAccessException {
        String classNameAll = cls.getName();
        String className = cls.getSimpleName();

        FileTemplate fileTemplate = new FileTemplate();
        fileTemplate.setTemplateId("1");
        fileTemplate.setTemplateName(classNameAll);
        fileTemplate.setTemplateName("sa");
        fileTemplate.setDbTableName(ClazzUtil.camel2Underline(className));
        fileTemplate.setValidator(firstCharToLowerCase(className) + "Validator");
        fileTemplate.setPrimaryKey(primaryKey);
//		fileTemplate.setDictionaries(dictionaries);
        List<ColumnTemplate> columnTemplates = new ArrayList<ColumnTemplate>();

        Field[] fs = cls.getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            Field f = fs[i];
            // f.setAccessible(true); // 设置些属性是可以访问的
            String propName = f.getName();
            int isInsert = 1;
//			Annotation columnAnnotation = f.getAnnotation(Column.class);
//			if (columnAnnotation == null) {
//				isInsert = 0;
//			}

            Annotation xmlElementAnnotation = f.getAnnotation(XmlElement.class);
            String templateName = "";
            if (xmlElementAnnotation != null) {
                // 强制转化为相应的注解
                XmlElement xmlElement = (XmlElement) xmlElementAnnotation;
                templateName = xmlElement.name();
            }
            ColumnTemplate columnTemplate = new ColumnTemplate();
            columnTemplate.setId(String.valueOf(i + 1));
            columnTemplate.setPos(i);
            columnTemplate.setTemplateName(templateName);
            columnTemplate.setDbColumnName(ClazzUtil.camel2Underline(propName));
            columnTemplate.setBeanColumnName(propName);
            String type = getDbType(f.getGenericType().toString());
            columnTemplate.setType(type);
            columnTemplate.setIsInsert(isInsert);
            columnTemplates.add(columnTemplate);
        }
        fileTemplate.setColumnTemplateList(columnTemplates);
        System.out.println(fileTemplate.toString());
        // 构造方法里，也可以直接传需要序列化的属性名字
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
        filter.getExcludes().add("columnTemplatesMap");
        filter.getExcludes().add("columnTemplatesBeanColumnName");
        String json = JSONObject.toJSONString(fileTemplate, filter);
        System.out.println(json);
        json = formatJson(json);
        createJsonFile(className, json);
    }

    private static String getDbType(String propType) {
        if (propType.equals("class java.math.BigDecimal")) {
            return DECIMAL;
        }
        // 如果类型是Integer或者是Short
        else if (propType.equals("class java.lang.Integer") || propType.equals("class java.lang.Short")) {
            return INTEGER;
        }
        // 如果类型是Double
        else if (propType.equals("class java.lang.Double")) {
            return DOUBLE;
        }
        // 如果类型是Long
        else if (propType.equals("class java.lang.Long")) {
            return LONG;
        }
        // 如果类型是Boolean 是封装类
        else if (propType.equals("class java.lang.Boolean") || propType.equals("boolean")) {
            return BOOLEAN;
        }
        // 如果类型是Date
        else if (propType.equals("class java.util.Date")) {
            return TIMESTAMP;
        } else {
            return ApplicationConstants.VARCHAR;
        }
    }


    public static void main(String[] args) {
        try {
//			Map<String,Map<String,List<String>>> dictionaries = new HashMap();
//			List<String> dictionaries = new ArrayList<String>();
//			dictionaries.add("BdDept");
//			dictionaries.add("BdMaterials");
            generateJsonFile(ReMateDetail.class, "PK_MATE_ORIGINAL");
            System.out.println("json文件生成成功");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
//		SpringApplication.run(Application.class, args);
    }

    public static void createJsonFile(String s, String json) {
        String sep = File.separator;
        String path = System.getProperty("user.dir") + sep + "src" + sep + "main" + sep + "resources" + sep + "properties" + sep + s + "Template.json";
        File file = new File(path);
        try {
            file.createNewFile();
            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            BufferedWriter writer = new BufferedWriter(write);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 格式化
     *
     * @param jsonStr
     * @return
     * @author lizhgb
     * @Date 2015-10-14 下午1:17:35
     * @Modified 2017-04-28 下午8:55:35
     */
    public static String formatJson(String jsonStr) {
        if (null == jsonStr || "".equals(jsonStr)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        char last = '\0';
        char current = '\0';
        int indent = 0;
        boolean isInQuotationMarks = false;
        for (int i = 0; i < jsonStr.length(); i++) {
            last = current;
            current = jsonStr.charAt(i);
            switch (current) {
                case '"':
                    if (last != '\\') {
                        isInQuotationMarks = !isInQuotationMarks;
                    }
                    sb.append(current);
                    break;
                case '{':
                case '[':
                    sb.append(current);
                    if (!isInQuotationMarks) {
                        sb.append('\n');
                        indent++;
                        addIndentBlank(sb, indent);
                    }
                    break;
                case '}':
                case ']':
                    if (!isInQuotationMarks) {
                        sb.append('\n');
                        indent--;
                        addIndentBlank(sb, indent);
                    }
                    sb.append(current);
                    break;
                case ',':
                    sb.append(current);
                    if (last != '\\' && !isInQuotationMarks) {
                        sb.append('\n');
                        addIndentBlank(sb, indent);
                    }
                    break;
                default:
                    sb.append(current);
            }
        }

        return sb.toString();
    }

    /**
     * 添加space
     *
     * @param sb
     * @param indent
     * @author lizhgb
     * @Date 2015-10-14 上午10:38:04
     */
    private static void addIndentBlank(StringBuilder sb, int indent) {
        for (int i = 0; i < indent; i++) {
            sb.append('\t');
        }
    }


    /**
     * 下划线转驼峰法
     *
     * @param line       源字符串
     * @param smallCamel 大小驼峰,是否为小驼峰
     * @return 转换后的字符串
     */
    public static String underline2Camel(String line, boolean smallCamel) {
        if (line == null || "".equals(line)) {
            return "";
        }
        String regular = "([A-Za-z\\d]+)(_)?";
        StringBuffer sb = new StringBuffer();
        Pattern pattern = Pattern.compile(regular);
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            String word = matcher.group();
            sb.append(smallCamel && matcher.start() == 0 ? Character.toLowerCase(word.charAt(0)) : Character.toUpperCase(word.charAt(0)));
            int index = word.lastIndexOf('_');
            if (index > 0) {
                sb.append(word.substring(1, index).toLowerCase());
            } else {
                sb.append(word.substring(1).toLowerCase());
            }
        }
        return sb.toString();
    }

}
