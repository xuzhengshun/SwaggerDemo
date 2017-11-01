package generator;




import java.text.MessageFormat;
import java.util.Properties;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.InnerEnum;
import org.mybatis.generator.api.dom.java.JavaElement;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.MergeConstants;
import org.mybatis.generator.internal.db.DatabaseIntrospector;
import org.mybatis.generator.internal.util.StringUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * mybatis代码生成器，类注释配置器
 * @author 18834
 *
 */
public class MybatisGenerarionConfigurer implements CommentGenerator{
	
	
	private static final Logger log = LoggerFactory.getLogger(MybatisGenerarionConfigurer.class);

	 //开始的分隔符，例如mysql为`，sqlserver为[
    private String beginningDelimiter = "";
    //结束的分隔符，例如mysql为`，sqlserver为]
    private String endingDelimiter = "";
    //强制生成注解
    private boolean forceAnnotation;
    //是否添加swagger注解
    private boolean isSwaggerAnnotation=true;

    public MybatisGenerarionConfigurer() {
        super();
    }

    @Override
    public void addJavaFileComment(CompilationUnit compilationUnit) {
        return;
    }

    /**
     * xml中的注释
     *
     * @param xmlElement
     */
    @Override
    public void addComment(XmlElement xmlElement) {
        xmlElement.addElement(new TextElement("<!--"));
        StringBuilder sb = new StringBuilder();
        sb.append("  WARNING - ");
        sb.append(MergeConstants.NEW_ELEMENT_TAG);
        xmlElement.addElement(new TextElement(sb.toString()));
        xmlElement.addElement(new TextElement("-->"));
    }

    @Override
    public void addRootComment(XmlElement rootElement) {
        return;
    }

    @Override
    public void addConfigurationProperties(Properties properties) {
        String beginningDelimiter = properties.getProperty("beginningDelimiter");
        if (StringUtility.stringHasValue(beginningDelimiter)) {
            this.beginningDelimiter = beginningDelimiter;
        }
        String endingDelimiter = properties.getProperty("endingDelimiter");
        if (StringUtility.stringHasValue(endingDelimiter)) {
            this.endingDelimiter = endingDelimiter;
        }
        String forceAnnotation = properties.getProperty("forceAnnotation");
        if (StringUtility.stringHasValue(forceAnnotation)) {
            this.forceAnnotation = forceAnnotation.equalsIgnoreCase("TRUE");
        }
        
        String swaggerAnnotation = properties.getProperty("swaggerAnnotation");
        if (StringUtility.stringHasValue(swaggerAnnotation)) {
            this.isSwaggerAnnotation = swaggerAnnotation.equalsIgnoreCase("TRUE");
        }
    }

    public String getDelimiterName(String name) {
        StringBuilder nameBuilder = new StringBuilder();
        nameBuilder.append(beginningDelimiter);
        nameBuilder.append(name);
        nameBuilder.append(endingDelimiter);
        return nameBuilder.toString();
    }

    /**
     * 删除标记
     *
     * @param javaElement
     * @param markAsDoNotDelete
     */
    protected void addJavadocTag(JavaElement javaElement, boolean markAsDoNotDelete) {
        StringBuilder sb = new StringBuilder();
        sb.append(" * ");
        sb.append(MergeConstants.NEW_ELEMENT_TAG);
        if (markAsDoNotDelete) {
            sb.append(" do_not_delete_during_merge");
        }
        javaElement.addJavaDocLine(sb.toString());
    }

    /**
     * Example使用
     *
     * @param innerClass
     * @param introspectedTable
     */
    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
    	
    	 
    }

    @Override
    public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {
    }

    /**
     * 给字段添加数据库备注
     *
     * @param field
     * @param introspectedTable
     * @param introspectedColumn
     */
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        if (StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
            field.addJavaDocLine("/**");
            StringBuilder sb = new StringBuilder();
            sb.append(" * ");
            sb.append(introspectedColumn.getRemarks());
            field.addJavaDocLine(sb.toString());
            field.addJavaDocLine(" */");
            //添加swagger注解
            if(isSwaggerAnnotation) {
            	 field.addAnnotation("@ApiModelProperty(\""+introspectedColumn.getRemarks()+"\")");
            }
        }else if(isSwaggerAnnotation) {
       	 field.addAnnotation("@ApiModelProperty(\""+introspectedColumn.getRemarks()+"\")");
       }
       
//        //添加注解 jpa
//        if (field.isTransient()) {
//            //@Column
//            field.addAnnotation("@Transient");
//        }
//        for (IntrospectedColumn column : introspectedTable.getPrimaryKeyColumns()) {
//            if (introspectedColumn == column) {
//                field.addAnnotation("@Id");
//                break;
//            }
//        }
//        String column = introspectedColumn.getActualColumnName();
//        if (StringUtility.stringContainsSpace(column) || introspectedTable.getTableConfiguration().isAllColumnDelimitingEnabled()) {
//            column = introspectedColumn.getContext().getBeginningDelimiter()
//                    + column
//                    + introspectedColumn.getContext().getEndingDelimiter();
//        }
//        if (!column.equals(introspectedColumn.getJavaProperty())) {
//            //@Column
//            field.addAnnotation("@Column(name = \"" + getDelimiterName(column) + "\")");
//        } else if (StringUtility.stringHasValue(beginningDelimiter) || StringUtility.stringHasValue(endingDelimiter)) {
//            field.addAnnotation("@Column(name = \"" + getDelimiterName(column) + "\")");
//        } else if(forceAnnotation){
//            field.addAnnotation("@Column(name = \"" + getDelimiterName(column) + "\")");
//        }
//        if (introspectedColumn.isIdentity()) {
//            if (introspectedTable.getTableConfiguration().getGeneratedKey().getRuntimeSqlStatement().equals("JDBC")) {
//                field.addAnnotation("@GeneratedValue(generator = \"JDBC\")");
//            } else {
//                field.addAnnotation("@GeneratedValue(strategy = GenerationType.IDENTITY)");
//            }
//        } else if (introspectedColumn.isSequenceColumn()) {
//            //在 Oracle 中，如果需要是 SEQ_TABLENAME，那么可以配置为 select SEQ_{1} from dual
//            String tableName = introspectedTable.getFullyQualifiedTableNameAtRuntime();
//            String sql = MessageFormat.format(introspectedTable.getTableConfiguration().getGeneratedKey().getRuntimeSqlStatement(), tableName, tableName.toUpperCase());
//            field.addAnnotation("@GeneratedValue(strategy = GenerationType.IDENTITY, generator = \"" + sql + "\")");
//        }
    }

    /**
     * Example使用
     *
     * @param field
     * @param introspectedTable
     */
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
    }

    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    	//重新写方法从数据库中获取表备注的信息
    
       // String remarks = introspectedTable.getFullyQualifiedTable().getRemark();
        //获取实体类名称
        String entityName = introspectedTable.getFullyQualifiedTable().getDomainObjectName();
        StringBuilder sb = new StringBuilder();
    	if(isSwaggerAnnotation) {
    		topLevelClass.addAnnotation("@ApiModel(value=\""+"xzs"+"\")");
    		topLevelClass.addImportedType("io.swagger.annotations.*");
    		
       }
//    	topLevelClass.addImportedType("javax.persistence.*;");
    }

    /**
     * @param method
     * @param introspectedTable
     */
    @Override
    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
    }

    /**
     * getter方法注释
     *
     * @param method
     * @param introspectedTable
     * @param introspectedColumn
     */
    @Override
    public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
//        StringBuilder sb = new StringBuilder();
//        method.addJavaDocLine("/**");
//        if (StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
//            sb.append(" * 获取");
//            sb.append(introspectedColumn.getRemarks());
//            method.addJavaDocLine(sb.toString());
//            method.addJavaDocLine(" *");
//        }
//        sb.setLength(0);
//        sb.append(" * @return ");
//        sb.append(introspectedColumn.getActualColumnName());
//        if (StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
//            sb.append(" - ");
//            sb.append(introspectedColumn.getRemarks());
//        }
//        method.addJavaDocLine(sb.toString());
//        method.addJavaDocLine(" */");
    }

    /**
     * setter方法注释
     *
     * @param method
     * @param introspectedTable
     * @param introspectedColumn
     */
    @Override
    public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
//        StringBuilder sb = new StringBuilder();
//        method.addJavaDocLine("/**");
//        if (StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
//            sb.append(" * 设置");
//            sb.append(introspectedColumn.getRemarks());
//            method.addJavaDocLine(sb.toString());
//            method.addJavaDocLine(" *");
//        }
//        Parameter parm = method.getParameters().get(0);
//        sb.setLength(0);
//        sb.append(" * @param ");
//        sb.append(parm.getName());
//        if (StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
//            sb.append(" ");
//            sb.append(introspectedColumn.getRemarks());
//        }
//        method.addJavaDocLine(sb.toString());
//        method.addJavaDocLine(" */");
    }

    /**
     * Example使用
     *
     * @param innerClass
     * @param introspectedTable
     * @param markAsDoNotDelete
     */
    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
    }
}
