// Copyright tang.  All rights reserved.
// https://gitee.com/inrgihc/dbswitch
//
// Use of this source code is governed by a BSD-style license
//
// Author: tang (inrgihc@126.com)
// Date : 2020/1/2
// Location: beijing , china
/////////////////////////////////////////////////////////////
package com.cgn.framework.dbswitch.core.database;

import com.cgn.framework.dbswitch.common.type.ProductTypeEnum;
import com.cgn.framework.dbswitch.core.database.impl.*;

/**
 * 数据库实例构建工厂类
 *
 * @author zyan
 */
public final class DatabaseFactory {

	/**
	 * 根据产品类型创建对应的数据库实例。
	 * <p>
	 * 此方法使用现代的 switch 表达式 (Java 14+)，实现了一种清晰且类型安全的方式。
	 *
	 * @param type 代表数据库产品的枚举类型
	 * @return 一个新的 AbstractDatabase 实现类的实例
	 * @throws UnsupportedOperationException 如果数据库类型不受支持
	 */
	public static AbstractDatabase getDatabaseInstance(ProductTypeEnum type) {
		// switch 表达式可以直接返回一个值，让代码变得极其整洁。
		return switch (type) {
			case MYSQL -> new DatabaseMysqlImpl();
			case MARIADB -> new DatabaseMariaDBImpl();
			case ORACLE -> new DatabaseOracleImpl();
			case SQLSERVER2000 -> new DatabaseSqlserver2000Impl();
			case SQLSERVER -> new DatabaseSqlserverImpl();
			case POSTGRESQL -> new DatabasePostgresImpl();
			case GREENPLUM -> new DatabaseGreenplumImpl();
			case DB2 -> new DatabaseDB2Impl();
			case DM -> new DatabaseDmImpl();
			case SYBASE -> new DatabaseSybaseImpl();
			case KINGBASE -> new DatabaseKingbaseImpl();
			case OSCAR -> new DatabaseOscarImpl();
			case GBASE8A -> new DatabaseGbase8aImpl();
			case HIVE -> new DatabaseHiveImpl();
			case SQLITE3 -> new DatabaseSqliteImpl();
			default -> throw new UnsupportedOperationException(
					String.format("不支持的数据库类型: %s", type.name())
			);
		};
	}

	private DatabaseFactory() {
		// 防止该工具类被实例化
		throw new IllegalStateException("工具类不能被实例化");
	}
}
