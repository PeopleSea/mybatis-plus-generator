package com.boyu.codegenerator.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.BeetlTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.boyu.codegenerator.config.GeneratorConfig;
import com.boyu.codegenerator.vo.DsTableVo;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author eric.y
 */
public class GeneratorCodeUtil {

    /**
     * 數據源設定
     * @param dbType 數據庫類型
     * @param dbUrl 連接網址
     * @param username 帳號
     * @param password 密碼
     * @param driver 驅動
     * @return DataSourceConfig
     */
    private static DataSourceConfig dataSourceConfig(DbType dbType, String dbUrl, String username, String password, String driver) {
        return new DataSourceConfig()
                .setDbType(dbType)
                .setUrl(dbUrl)
                .setUsername(username)
                .setPassword(password)
                .setDriverName(driver)
                ;
    }

    // 設定
    private static GlobalConfig globalConfig() {
        return new GlobalConfig()
                .setAuthor(GeneratorConfig.AUTHOR)
                .setOutputDir(GeneratorConfig.outputDir)
                .setFileOverride(true) // 是否覆蓋已有文件
                //.setOpen(true) // 是否打開輸出目錄
                .setDateType(DateType.TIME_PACK) // 時間採用java 8，（操作工具类：JavaLib => DateTimeUtils）
                .setActiveRecord(true)// 不需要ActiveRecord特性的請改為false
                .setEnableCache(false)// XML 二级緩存
                .setBaseResultMap(false)// XML ResultMap
                .setBaseColumnList(false)// XML columList
                .setKotlin(false) //是否生成 kotlin 程式碼
                // 自定義文件命名，注意 %s 會自動填充表實體屬性！
                .setEntityName(GeneratorConfig.FILE_NAME_MODEL)
                .setMapperName(GeneratorConfig.FILE_NAME_DAO)
                .setXmlName(GeneratorConfig.FILE_NAME_XML)
                .setServiceName(GeneratorConfig.FILE_NAME_SERVICE)
                .setServiceImplName(GeneratorConfig.FILE_NAME_SERVICE_IMPL)
                .setControllerName(GeneratorConfig.FILE_NAME_CONTROLLER)
                .setIdType(IdType.ASSIGN_ID) // 主鍵類型
                .setSwagger2(GeneratorConfig.SWAGGER_SUPPORT) // model swagger2
                ;
//                if (!serviceNameStartWithI)
//                    config.setServiceName("%sService");
    }


    private static StrategyConfig strategyConfig(String [] tablePrefixes, String [] tableNames, String [] fieldPrefixes) {
        return new StrategyConfig()
                .setCapitalMode(true) // 全局大寫命名 ORACLE 注意
                .setSkipView(false) // 是否跳過view
                //.setDbColumnUnderline(true)
                .setTablePrefix(tablePrefixes)// 此處可以修改為表前綴(數組)
                .setFieldPrefix(fieldPrefixes) // 字段前綴
                .setNaming(NamingStrategy.underline_to_camel) // 表名生成策略
                .setInclude(tableNames)//修改替换成你需要的表名，多個表名傳數組
                //.setExclude(new String[]{"test"}) // 排除生成的表
                .setEntityLombokModel(true) // lombok實體
                .setChainModel(true) // 【實體】是否为構建者模型（默認 false）
                .setEntityColumnConstant(false) // 【實體】是否生成字段常量（默認 false）// 可通過常量名獲取數據庫字段名 // 3.x支持lambda表達式
                .setLogicDeleteFieldName(GeneratorConfig.FIELD_LOGIC_DELETE_NAME) // 活及刪除屬性名稱
                .setVersionFieldName(GeneratorConfig.FIELD_VERSION_NAME) // 樂觀鎖字段名
                .setEntityTableFieldAnnotationEnable(true) // 開啟實體字段註解
                ;
    }

    // 封裝設定
    private static PackageConfig packageConfig(String packageName) {
        return new PackageConfig()
                .setParent(packageName)
                .setController(GeneratorConfig.PACKAGE_NAME_CONTROLLER)
                .setEntity(GeneratorConfig.PACKAGE_NAME_MODEL)
                .setMapper(GeneratorConfig.PACKAGE_NAME_DAO)
                .setXml(GeneratorConfig.PACKAGE_NAME_XML)
                .setService(GeneratorConfig.PACKAGE_NAME_SERVICE)
                .setServiceImpl(GeneratorConfig.PACKAGE_NAME_SERVICE_IMPL)
                ;
    }

    /**
     *
     * @param packageConfig
     * @return
     */
    private static InjectionConfig injectionConfig(final PackageConfig packageConfig) {
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> fileOutConfigList = new ArrayList<FileOutConfig>();
        fileOutConfigList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定義輸入文件名稱
                if (StringUtils.isEmpty(packageConfig.getModuleName())) {
                    return GeneratorConfig.projectPath + "/src/main/resources/mapper/" + tableInfo.getXmlName() + StringPool.DOT_XML;
                }else {
                    return GeneratorConfig.projectPath + "/src/main/resources/mapper/" + packageConfig.getModuleName() + "/" + tableInfo.getXmlName() + StringPool.DOT_XML;
                }
            }
        });
        injectionConfig.setFileOutConfigList(fileOutConfigList);
        return injectionConfig;
    }

    /**
     * 獲取模板引擎
     * @return 模板引擎 {@link AbstractTemplateEngine}
     */
    private static AbstractTemplateEngine getTemplateEngine() {
        String templateEngine = GeneratorConfig.TEMPLATE_ENGINE;
        switch (templateEngine) {
            case "velocity":
                return new VelocityTemplateEngine();
            case "freemarker":
                return new FreemarkerTemplateEngine();
            case "beetl":
                return new BeetlTemplateEngine();
        }
        return new VelocityTemplateEngine();
    }

    public static void execute(DsTableVo dsTableVo) {
        GlobalConfig globalConfig = globalConfig();
        DataSourceConfig dataSourceConfig = dsTableVo.getDataSourceConfig();
        StrategyConfig strategyConfig = strategyConfig(dsTableVo.getTablePrefixes(), dsTableVo.getTableNames(), dsTableVo.getFieldPrefixes());
        PackageConfig packageConfig = packageConfig(dsTableVo.getPackageName());
//        InjectionConfig injectionConfig = injectionConfig(packageConfig);
        AbstractTemplateEngine templateEngine = getTemplateEngine();
        new AutoGenerator()
                .setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig)
                .setTemplateEngine(templateEngine)
                //.setCfg(injectionConfig)
                .execute();
    }

}
