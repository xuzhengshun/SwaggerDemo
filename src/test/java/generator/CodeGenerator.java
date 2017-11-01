package generator;

import com.google.common.base.CaseFormat;

import freemarker.template.TemplateExceptionHandler;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.generator.internal.db.DatabaseIntrospector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;


import static com.xzs.core.ProjectConstant.*;
import static org.mockito.Mockito.when;


/**
 * 代码生成器，根据数据表名称生成对应的Model、Mapper、Service、Controller简化开发。
 */
public class CodeGenerator {

	private static final Logger log = LoggerFactory.getLogger(CodeGenerator.class);


	
	
	// JDBC配置，请修改为你项目的实际配置

	//
	 private static final String JDBC_URL = "jdbc:mysql://localhost:3306/xzsblog";
	 private static final String JDBC_USERNAME = "root";
	 private static final String JDBC_PASSWORD = "root";
	 private static final String JDBC_DIVER_CLASS_NAME = "com.mysql.jdbc.Driver";



	 private static final String PROJECT_PATH = System.getProperty("user.dir");// 项目在硬盘上的基础路径
		private static final String TEMPLATE_FILE_PATH = PROJECT_PATH + "/src/test/resources/generator/template";// 模板位置

		private static final String JAVA_PATH = "/src/main/java"; // java文件路径
		private static final String RESOURCES_PATH = "/src/main/resources";// 资源文件路径

		private static final String PACKAGE_PATH_SERVICE = packageConvertPath(SERVICE_PACKAGE);// 生成的Service存放路径
		private static final String PACKAGE_PATH_SERVICE_IMPL = packageConvertPath(SERVICE_IMPL_PACKAGE);// 生成的Service实现存放路径
		private static final String PACKAGE_PATH_CONTROLLER = packageConvertPath(CONTROLLER_PACKAGE);// 生成的Controller存放路径

		private static final String AUTHOR = "xzs";// @author
		private static final String DATE = new SimpleDateFormat("yyyy/MM/dd").format(new Date());// @date

	public static void main(String[] args) {
		// genCode("City", "District", "Stock", "WorkStation", "Province");
		//genCode("Account", "Account", "用户");
		Map<String, String> tables = new HashMap<>();
		// map 表明 :备注（swagger）
//		 tables.put("City", "城市");
//		 tables.put("District", "区域");
//		 tables.put("Stock", "工位库存");
//		 tables.put("WorkStation", "维修厂工位");
//		 tables.put("Province", "省份");

		 tables.put("admin", "管理员表");
		 tables.put("blog", "博客表");
		 tables.put("classify", "分类表");
		

		 genCode(tables);
//		 tables.put("SeriesDisplacement", "车系排量关系");
		

//		 genCode(tables);

	}

	public static void genCode(Map<String, String> tables) {
		Set<Entry<String, String>> entries = tables.entrySet();
		Iterator<Entry<String, String>> iterator = entries.iterator();
		while (iterator.hasNext()) {
			Entry<String, String> entry = iterator.next();
			genCode(entry.getKey(), null, entry.getValue());
		}

		// for (String tableName : tableNames) {
		// genCode(tableName, null,null);
		// }
	}

	/**
	 * 通过数据表名称生成代码，Model 名称通过解析数据表名称获得，下划线转大驼峰的形式。 如输入表名称 "t_user_detail" 将生成
	 * TUserDetail、TUserDetailMapper、TUserDetailService ...
	 * 
	 * @param tableNames
	 *            数据表名称...
	 */
	public static void genCode(String... tableNames) {
		for (String tableName : tableNames) {
			genCode(tableName, null, null);
		}
	}

	/**
	 * 通过数据表名称，和自定义的 Model 名称生成代码 如输入表名称 "t_user_detail" 和自定义的 Model 名称 "User" 将生成
	 * User、UserMapper、UserService ...
	 * 
	 * @param tableName
	 *            数据表名称
	 * @param modelName
	 *            自定义的 Model 名称
	 */
	public static void genCode(String tableName, String modelName, String remark) {
		genModelAndMapper(tableName, modelName);
		genService(tableName, modelName);
		genController(tableName, modelName, remark);
	}

	public static void genModelAndMapper(String tableName, String modelName) {
		Context context = new Context(ModelType.FLAT);
		context.setId("Potato");
		context.setTargetRuntime("MyBatis3Simple");
		context.addProperty(PropertyRegistry.CONTEXT_BEGINNING_DELIMITER, "[");
		context.addProperty(PropertyRegistry.CONTEXT_ENDING_DELIMITER, "]");
		context.addProperty("javaFileEncoding", "UTF-8");

		JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
		jdbcConnectionConfiguration.setConnectionURL(JDBC_URL);
		jdbcConnectionConfiguration.setUserId(JDBC_USERNAME);
		jdbcConnectionConfiguration.setPassword(JDBC_PASSWORD);
		jdbcConnectionConfiguration.setDriverClass(JDBC_DIVER_CLASS_NAME);

		JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
		javaModelGeneratorConfiguration.setTargetProject(PROJECT_PATH + JAVA_PATH);
		javaModelGeneratorConfiguration.setTargetPackage(MODEL_PACKAGE);

		SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
		sqlMapGeneratorConfiguration.setTargetProject(PROJECT_PATH + RESOURCES_PATH);
		sqlMapGeneratorConfiguration.setTargetPackage("mybatis/mapper");

		JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
		javaClientGeneratorConfiguration.setTargetProject(PROJECT_PATH + JAVA_PATH);
		javaClientGeneratorConfiguration.setTargetPackage(MAPPER_PACKAGE);
		javaClientGeneratorConfiguration.setConfigurationType("XMLMAPPER");

		TableConfiguration tableConfiguration = new TableConfiguration(context);
		tableConfiguration.setTableName(tableName);
		tableConfiguration.setDomainObjectName(modelName);
		 tableConfiguration.setGeneratedKey(new GeneratedKey("id", "MySql", true,
		 null));
//		tableConfiguration.setGeneratedKey(new GeneratedKey("id", "SqlServer", true, null));
		tableConfiguration.addProperty("useActualColumnNames", "true");

		/**
		 * 屏蔽注释信息 <property name="suppressAllComments" value="true"/>
		 * <property name="suppressDate" value="true"/>
		 */
		CommentGeneratorConfiguration commentGeneratorConfiguration = new CommentGeneratorConfiguration();
		commentGeneratorConfiguration.setConfigurationType("generator.MybatisGenerarionConfigurer");
		// commentGeneratorConfiguration.addProperty("suppressAllComments", "true");
		commentGeneratorConfiguration.addProperty("suppressDate", "true");
		commentGeneratorConfiguration.addProperty("addRemarkComments", "true");

		PluginConfiguration pluginConfiguration = new PluginConfiguration();

		Configuration config = new Configuration();
		config.addContext(context);
		// context.addPluginConfiguration(pluginConfiguration);
		context.setCommentGeneratorConfiguration(commentGeneratorConfiguration);
		context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);
		context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);
		context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);
		context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);
		context.addTableConfiguration(tableConfiguration);

		List<String> warnings;
		MyBatisGenerator generator;
		try {

			config.validate();
			boolean overwrite = true;
			DefaultShellCallback callback = new DefaultShellCallback(overwrite);
			warnings = new ArrayList<String>();
			generator = new MyBatisGenerator(config, callback, warnings);
			generator.generate(null);
		} catch (Exception e) {
			throw new RuntimeException("生成Model和Mapper失败", e);
		}

		if (generator.getGeneratedJavaFiles().isEmpty() || generator.getGeneratedXmlFiles().isEmpty()) {
			throw new RuntimeException("生成Model和Mapper失败：" + warnings);
		}
		if (StringUtils.isEmpty(modelName))
			modelName = tableNameConvertUpperCamel(tableName);
		System.out.println(modelName + ".java 生成成功");
		System.out.println(modelName + "Mapper.java 生成成功");
		System.out.println(modelName + "Mapper.xml 生成成功");
	}

	public static void genService(String tableName, String modelName) {
		try {
			freemarker.template.Configuration cfg = getConfiguration();

			Map<String, Object> data = new HashMap<>();
			data.put("date", DATE);
			data.put("author", AUTHOR);
			String modelNameUpperCamel = StringUtils.isEmpty(modelName) ? tableNameConvertUpperCamel(tableName)
					: modelName;
			data.put("modelNameUpperCamel", modelNameUpperCamel);
			data.put("modelNameLowerCamel", tableNameConvertLowerCamel(tableName));
			data.put("basePackage", BASE_PACKAGE);
			data.put("businessName", BUSINESS_NAME);

			File file = new File(
					PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_SERVICE + modelNameUpperCamel + "Service.java");
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			cfg.getTemplate("service.ftl").process(data, new FileWriter(file));
			System.out.println(modelNameUpperCamel + "Service.java 生成成功");

			File file1 = new File(
					PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_SERVICE_IMPL + modelNameUpperCamel + "ServiceImpl.java");
			if (!file1.getParentFile().exists()) {
				file1.getParentFile().mkdirs();
			}
			cfg.getTemplate("service-impl.ftl").process(data, new FileWriter(file1));
			System.out.println(modelNameUpperCamel + "ServiceImpl.java 生成成功");
		} catch (Exception e) {
			throw new RuntimeException("生成Service失败", e);
		}
	}

	public static void genController(String tableName, String modelName, String tableRemark) {
		try {
			freemarker.template.Configuration cfg = getConfiguration();

			Map<String, Object> data = new HashMap<>();
			data.put("date", DATE);
			data.put("author", AUTHOR);
			String modelNameUpperCamel = StringUtils.isEmpty(modelName) ? tableNameConvertUpperCamel(tableName)
					: modelName;
			data.put("baseRequestMapping", modelNameConvertMappingPath(modelNameUpperCamel));
			data.put("modelNameUpperCamel", modelNameUpperCamel);
			data.put("modelNameLowerCamel", CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, modelNameUpperCamel));
			data.put("basePackage", BASE_PACKAGE);
			// data.put("swaagerTags", SWAGGERTAGS);
			data.put("swaagerRemark", tableRemark == null ? tableName : tableRemark);
			data.put("businessName", BUSINESS_NAME);
			File file = new File(
					PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_CONTROLLER + modelNameUpperCamel + "Controller.java");
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			cfg.getTemplate("controller-restful.ftl").process(data, new FileWriter(file));
			// cfg.getTemplate("controller.ftl").process(data, new FileWriter(file));

			System.out.println(modelNameUpperCamel + "Controller.java 生成成功");
		} catch (Exception e) {
			throw new RuntimeException("生成Controller失败", e);
		}

	}

	private static freemarker.template.Configuration getConfiguration() throws IOException {
		freemarker.template.Configuration cfg = new freemarker.template.Configuration(
				freemarker.template.Configuration.VERSION_2_3_23);
		cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_FILE_PATH));
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
		return cfg;
	}

	private static String tableNameConvertLowerCamel(String tableName) {
		return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableName.toLowerCase());
	}

	private static String tableNameConvertUpperCamel(String tableName) {
		// 表明不规范时使用
		return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.toLowerCase());
		// return tableName;
	}

	private static String tableNameConvertMappingPath(String tableName) {

		tableName = tableName.toLowerCase();// 兼容使用大写的表名
		return "/" + (tableName.contains("_") ? tableName.replaceAll("_", "/") : tableName);
	}

	private static String modelNameConvertMappingPath(String modelName) {
		log.info(modelName);
		String tableName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, modelName);
		return tableNameConvertMappingPath(tableName);
	}

	private static String packageConvertPath(String packageName) {
		return String.format("/%s/", packageName.contains(".") ? packageName.replaceAll("\\.", "/") : packageName);
	}

}
