package com.boyu.codegenerator.enums;

import lombok.Getter;

@Getter
public enum DriverNameEnum {
    // default port = 3306
    MYSQL("mysql", "MySql数据库", "com.mysql.cj.jdbc.Driver"),
    MARIADB("mariadb", "MariaDB数据库", "org.mariadb.jdbc.Driver"),
    ORACLE("oracle", "Oracle11g及以下数据库(高版本推荐使用ORACLE_NEW)", "oracle.jdbc.driver.OracleDriver"),
    ORACLE_12C("oracle12c", "Oracle12c+数据库", "oracle.jdbc.driver.OracleDriver"),
    DB2("db2", "DB2数据库", ""),
    H2("h2", "H2数据库", ""),
    HSQL("hsql", "HSQL数据库", ""),
    SQLITE("sqlite", "SQLite数据库", ""),
    POSTGRE_SQL("postgresql", "Postgre数据库", "org.postgresql.Driver"),
    SQL_SERVER2005("sqlserver2005", "SQLServer2005数据库", ""),
    // default port = 1433
    SQL_SERVER("sqlserver", "SQLServer数据库", "com.microsoft.sqlserver.jdbc.SQLServerDriver"),
    DM("dm", "达梦数据库", ""),
    XU_GU("xugu", "虚谷数据库", ""),
    KINGBASE_ES("kingbasees", "人大金仓数据库", ""),
    PHOENIX("phoenix", "Phoenix HBase数据库", ""),
    GAUSS("zenith", "Gauss 数据库", ""),
    CLICK_HOUSE("clickhouse", "clickhouse 数据库", ""),
    GBASE("gbase", "南大通用数据库", ""),
    OSCAR("oscar", "神通数据库", ""),
    SYBASE("sybase", "Sybase ASE 数据库", ""),
    OCEAN_BASE("oceanbase", "OceanBase 数据库", ""),
    FIREBIRD("Firebird", "Firebird 数据库", ""),
    OTHER("other", "其他数据库", "");

    private String code;
    private String desc;
    private String value;

    DriverNameEnum(String code, String desc, String value){
        this.code = code;
        this.desc = desc;
        this.value = value;
    }
}
