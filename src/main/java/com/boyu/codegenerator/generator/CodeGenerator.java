package com.boyu.codegenerator.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.boyu.codegenerator.enums.DriverNameEnum;
import com.boyu.codegenerator.util.GeneratorCodeUtil;
import com.boyu.codegenerator.vo.DsTableVo;

public class CodeGenerator {

    public static void main(String[] args) {

        DataSourceConfig dsc = new DataSourceConfig();
        DbType dbType = DbType.SQL_SERVER;
        dsc.setDbType(dbType);
        dsc.setDriverName(DriverNameEnum.SQL_SERVER.getValue());
        String hostIP = "10.101.6.182\\LETOUQA";
        String port="1433";
        String dbName="MainDB";
        String userName="rd1";
        String password="rd1";
        dsc.setUrl("jdbc:sqlserver://" + hostIP + ":" + port + ";DatabaseName=" + dbName);
        dsc.setUsername(userName);
        dsc.setPassword(password);

        DsTableVo dsTableVo = new DsTableVo();

        dsTableVo.setDataSourceConfig(dsc);

        // 表前缀，生成的實體類，不含前缀
        String [] tablePrefixes = {};
        // 表名，為空，生成所有的表
        String [] tableNames = {};
        // 字段前缀
        String [] fieldPrefixes = {};
        // 基礎封裝名或路徑
        String packageName = "com.boyu.oncall";
        dsTableVo.setTablePrefixes(tablePrefixes);
        dsTableVo.setTableNames(tableNames);
        dsTableVo.setPackageName(packageName);
        dsTableVo.setFieldPrefixes(fieldPrefixes);

        GeneratorCodeUtil.execute(dsTableVo);
    }
}
