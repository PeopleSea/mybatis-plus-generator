package com.boyu.codegenerator.vo;

import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import lombok.*;

/**
 * @author eric.y
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DsTableVo {

    private DataSourceConfig dataSourceConfig;
    private String [] tablePrefixes;
    private String [] tableNames;
    private String packageName;
    private String [] fieldPrefixes;
}