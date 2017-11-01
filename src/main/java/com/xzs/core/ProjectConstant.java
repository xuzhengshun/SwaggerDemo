package com.xzs.core;

/**
 * mybatis代码生成器，项目常量
 */
public final class ProjectConstant {
	public static final String BASE_PACKAGE = "com.xzs";// 项目基础包名称，根据自己公司的项目修改

	public static final String BUSINESS_NAME = "swaggerDemo";//各业务名称，生成controller和Service

	public static final String MODEL_PACKAGE = BASE_PACKAGE + ".entity";// Model所在包
	public static final String MAPPER_PACKAGE = BASE_PACKAGE + ".dao";// Mapper所在包
	public static final String SERVICE_PACKAGE = BASE_PACKAGE + "." + BUSINESS_NAME + ".service";// Service所在包
	public static final String SERVICE_IMPL_PACKAGE = SERVICE_PACKAGE + ".impl";// ServiceImpl所在包
	public static final String CONTROLLER_PACKAGE = BASE_PACKAGE + "." + BUSINESS_NAME + ".controller";// Controller所在包

	public static final String MAPPER_INTERFACE_REFERENCE = BASE_PACKAGE + ".core.Mapper";// Mapper插件基础接口的完全限定名
}
