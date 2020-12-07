package com.boyu.codegenerator.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.boyu.codegenerator.enums.DriverNameEnum;
import com.boyu.codegenerator.util.GeneratorCodeUtil;
import com.boyu.codegenerator.vo.DsTableVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CodeGenerator_old {

    /**
     * <p>
     * 讀取控制台內容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        String help = "請輸入" + tip + "：";
        System.out.println(help);
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("請輸入正確的" + tip + "！");
    }

    public static void main(String[] args) {

        // 代碼生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("eric yang");
        gc.setOpen(false);
        // 設置名字
        gc.setControllerName("%sController");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setMapperName("%sMapper");
        // 設置 resultMap
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        // gc.setFileOverride(true);
        // gc.setSwagger2(true); 實體屬性 Swagger2 註解
        mpg.setGlobalConfig(gc);

        // 數據源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:sqlserver://localhost:1433;DatabaseName=DBName");
        dsc.setDriverName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dsc.setUsername("username");
        dsc.setPassword("password");
        mpg.setDataSource(dsc);

        // 自訂亦配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 包配置
        PackageConfig pc = new PackageConfig();
        //  pc.setModuleName(scanner("模組名稱"));
        pc.setParent("com.boyu.oncall");
        mpg.setPackageInfo(pc);

        // 如果模板引擎是 velocity
        String templatePath = "/templates/mapper.xml.vm";

        // 自定義輸出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定義配置會被優先輸出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定義輸出文件名 ， 如果你 Entity 設置了前後綴、此處注意 xml 的名稱會跟著發生變化！！
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 寫於父類中的公共字段
        // strategy.setSuperEntityColumns("id");
        // strategy.setInclude(scanner("表的名稱，多個表請用英文逗號分割").split(","));
        strategy.setInclude(scanner("表的名稱，多個表請用英文逗號分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new VelocityTemplateEngine());
        mpg.execute();
    }
}
