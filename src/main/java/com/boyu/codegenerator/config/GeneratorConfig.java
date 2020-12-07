package com.boyu.codegenerator.config;

public class GeneratorConfig {
    /** 封裝名：controller */
    public static final String PACKAGE_NAME_CONTROLLER = "controller";

    /** 封裝名：service */
    public static final String PACKAGE_NAME_SERVICE = "repository";

    /** 封裝名：service.impl */
    public static final String PACKAGE_NAME_SERVICE_IMPL = "repository.impl";

    /** 封裝名：model */
    public static final String PACKAGE_NAME_MODEL = "entity";

    /** 封裝名：dao */
    public static final String PACKAGE_NAME_DAO = "mapper";

    /** 封裝名：xml */
    public static final String PACKAGE_NAME_XML = "xml";

    /** 文件名後缀：Model */
    public static final String FILE_NAME_MODEL = "%sEntity";

    /** 文件名後缀：Dao */
    public static final String FILE_NAME_DAO = "I%sMapper";

    /** 文件名後缀：Mapper */
    public static final String FILE_NAME_XML = "%sMapper";

    /** MP開頭，Service结尾 */
    public static final String FILE_NAME_SERVICE = "MP%sRepository";

    /** 文件名後缀：ServiceImpl */
    public static final String FILE_NAME_SERVICE_IMPL = "%sRepositoryImpl";

    /** 文件名後缀：Controller */
    public static final String FILE_NAME_CONTROLLER = "%sController";

    /** 逻辑删除字段 */
    public static final String FIELD_LOGIC_DELETE_NAME = "delete_status";


    /** 樂觀鎖字段名 */
    public static final String FIELD_VERSION_NAME = "version";

    /** 作者 */
    public static final String AUTHOR = "Eric Yang";

    /** 生成文件的输出目录 */
    public static String projectPath = System.getProperty("user.dir");

    /** 输出目录
     *  如果在Window環境，未設置磁碟區，會由專案所在的磁碟區最上層開始展開outputDir+ package path
     *  如果在Linus環境，則會展開outputDir+ package path
     * */
//    public static final String outputDir = projectPath + "/src/main/java";
    public static final String outputDir = "/Users/eric/Temp/code-generator";

    /** 模板引擎。velocity / freemarker / beetl */
    public static final String TEMPLATE_ENGINE = "velocity";

    /** 是否支持Swagger，默認不支持 */
    public static final Boolean SWAGGER_SUPPORT = false;
}
