// Copyright tang.  All rights reserved.
// https://gitee.com/inrgihc/dbswitch
//
// Use of this source code is governed by a BSD-style license
//
// Author: tang (inrgihc@126.com)
// Data : 2020/1/2
// Location: beijing , china
/////////////////////////////////////////////////////////////
package com.cgn.framework.dbswitch.core.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.cgn.framework.dbswitch.common.type.ProductTypeEnum;
import com.cgn.framework.dbswitch.core.database.AbstractDatabase;
import com.cgn.framework.dbswitch.core.database.DatabaseFactory;
import com.cgn.framework.dbswitch.core.model.*;
import com.cgn.framework.dbswitch.core.service.IMetaDataByJdbcService;
import com.cgn.framework.dbswitch.core.util.ConnectionUtils;
import com.cgn.framework.dbswitch.core.util.GenerateSqlUtils;
import com.cgn.framework.dbswitch.core.util.SqlUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 使用JDBC连接串的元数据获取服务
 *
 * @author zyan
 */
public class MetaDataByJdbcServiceImpl implements IMetaDataByJdbcService {

    protected ProductTypeEnum dbType;
    protected AbstractDatabase database;

    /**
     * 元数据通过JDBC服务实现
     *
     * @param type 类型
     */
    public MetaDataByJdbcServiceImpl(ProductTypeEnum type) {
        this.dbType = type;
        this.database = DatabaseFactory.getDatabaseInstance(type);
    }

    @Override
    public ProductTypeEnum getDatabaseType() {
        return this.dbType;
    }

    @Override
    public List<String> querySchemaList(String jdbcUrl, String username, String password) {
        try (Connection connection = ConnectionUtils.connect(jdbcUrl, username, password)) {
            return database.querySchemaList(connection);
        } catch (SQLException se) {
            throw new RuntimeException(se);
        }
    }

    @Override
    public List<TableDescription> queryTableList(String jdbcUrl, String username, String password,
                                                 String schemaName) {
        try (Connection connection = ConnectionUtils.connect(jdbcUrl, username, password)) {
            return database.queryTableList(connection, schemaName);
        } catch (SQLException se) {
            throw new RuntimeException(se);
        }
    }

    @Override
    public String getTableDDL(String jdbcUrl, String username, String password, String schemaName,
                              String tableName) {
        try (Connection connection = ConnectionUtils.connect(jdbcUrl, username, password)) {
            return database.getTableDDL(connection, schemaName, tableName);
        } catch (SQLException se) {
            throw new RuntimeException(se);
        }
    }

    @Override
    public String getViewDDL(String jdbcUrl, String username, String password, String schemaName,
                             String tableName) {
        try (Connection connection = ConnectionUtils.connect(jdbcUrl, username, password)) {
            return database.getViewDDL(connection, schemaName, tableName);
        } catch (SQLException se) {
            throw new RuntimeException(se);
        }
    }

    @Override
    public List<ColumnDescription> queryTableColumnMeta(String jdbcUrl, String username,
                                                        String password, String schemaName, String tableName) {
        try (Connection connection = ConnectionUtils.connect(jdbcUrl, username, password)) {
            List<ColumnDescription> columnDescriptions = database.queryTableColumnMeta(connection, schemaName, tableName);
            database.setColumnDefaultValue(connection, schemaName, tableName, columnDescriptions);
            database.setColumnIndexInfo(connection, schemaName, tableName, columnDescriptions);
            return columnDescriptions;
        } catch (SQLException se) {
            throw new RuntimeException(se);
        }
    }

    @Override
    public List<ColumnDescription> queryTableColumnMetaOnly(String jdbcUrl, String username, String password, String schemaName, String tableName) {
        try (Connection connection = ConnectionUtils.connect(jdbcUrl, username, password)) {
            return database.queryTableColumnMeta(connection, schemaName, tableName);
        } catch (SQLException se) {
            throw new RuntimeException(se);
        }
    }

    @Override
    public List<ColumnDescription> querySqlColumnMeta(String jdbcUrl, String username,
                                                      String password, String querySql) {
        try (Connection connection = ConnectionUtils.connect(jdbcUrl, username, password)) {
            return database.querySelectSqlColumnMeta(connection, querySql);
        } catch (SQLException se) {
            throw new RuntimeException(se);
        }
    }

    @Override
    public int queryTableDataCount(String jdbcUrl, String username, String password, String tableName) {
        try (Connection connection = ConnectionUtils.connect(jdbcUrl, username, password)) {
            return database.queryTableDataCount(connection, tableName);
        } catch (SQLException se) {
            throw new RuntimeException(se);
        }
    }

    @Override
    public boolean dropTable(String jdbcUrl, String username, String password, String tableName) {
        try (Connection connection = ConnectionUtils.connect(jdbcUrl, username, password)) {
            return database.dropTable(connection, tableName);
        } catch (SQLException se) {
            throw new RuntimeException(se);
        }
    }


    @Override
    public List<String> queryTablePrimaryKeys(String jdbcUrl, String username, String password,
                                              String schemaName, String tableName) {
        try (Connection connection = ConnectionUtils.connect(jdbcUrl, username, password)) {
            return database.queryTablePrimaryKeys(connection, schemaName, tableName);
        } catch (SQLException se) {
            throw new RuntimeException(se);
        }
    }

    @Override
    public SchemaTableMeta queryTableMeta(String jdbcUrl, String username, String password,
                                          String schemaName, String tableName) {
        SchemaTableMeta tableMeta = new SchemaTableMeta();
        try (Connection connection = ConnectionUtils.connect(jdbcUrl, username, password)) {
            TableDescription tableDesc = database.queryTableMeta(connection, schemaName, tableName);
            if (null == tableDesc) {
                throw new IllegalArgumentException("Table Or View Not Exist");
            }

            List<ColumnDescription> columns = database
                    .queryTableColumnMeta(connection, schemaName, tableName);

            List<String> pks;
            String createSql;
            if (tableDesc.isViewTable()) {
                pks = Collections.emptyList();
                createSql = database.getViewDDL(connection, schemaName, tableName);
            } else {
                pks = database.queryTablePrimaryKeys(connection, schemaName, tableName);
                createSql = database.getTableDDL(connection, schemaName, tableName);
            }

            tableMeta.setSchemaName(schemaName);
            tableMeta.setTableName(tableName);
            tableMeta.setTableType(tableDesc.getTableType());
            tableMeta.setRemarks(tableDesc.getRemarks());
            tableMeta.setColumns(columns);
            tableMeta.setPrimaryKeys(pks);
            tableMeta.setCreateSql(createSql);

            return tableMeta;
        } catch (SQLException se) {
            throw new RuntimeException(se);
        }
    }

    @Override
    public SchemaTableData queryTableData(String jdbcUrl, String username, String password,
                                          String schemaName, String tableName, int rowCount) {
        try (Connection connection = ConnectionUtils.connect(jdbcUrl, username, password)) {
            return database.queryTableData(connection, schemaName, tableName, rowCount);
        } catch (SQLException se) {
            throw new RuntimeException(se);
        }
    }

    @Override
    public SchemaTableData queryTableDataBySql(String jdbcUrl, String username, String password,
                                               String sql, int rowCount) {
        try (Connection connection = ConnectionUtils.connect(jdbcUrl, username, password)) {
            return database.queryTableDataBySql(connection, sql, rowCount);
        } catch (SQLException se) {
            throw new RuntimeException(se);
        }
    }

    @Override
    public JdbcSelectResult queryDataBySql(String jdbcUrl, String dbType, String username, String password, String sql, Integer openTrans, int rowCount) {
        try (Connection connection = ConnectionUtils.connect(jdbcUrl, username, password)) {
            return database.queryDataBySql(connection, dbType, sql, openTrans, rowCount);
        } catch (SQLException se) {
            throw new RuntimeException(se);
        }
    }

    @Override
    public JdbcSelectResult queryDataByApiSql(String jdbcUrl, String username, String password, String sql, Integer openTrans, String sqlSeparator, Map<String, Object> sqlParam, int rowCount) {
        try (Connection connection = ConnectionUtils.connect(jdbcUrl, username, password)) {
            return database.queryDataByApiSql(connection, sql, openTrans, sqlSeparator, sqlParam, rowCount);
        } catch (SQLException se) {
            throw new RuntimeException(se);
        }
    }

    @Override
    public List<SqlExplainResult> explain(String sql, String dbType) {
        List<SqlExplainResult> sqlExplainResults = new ArrayList<>();
        String current = null;
        try {
            List<SQLStatement> stmtList = SQLUtils.parseStatements(sql, dbType.toLowerCase());
            for (SQLStatement item : stmtList) {
                current = item.toString();
                String type = item.getClass().getSimpleName();
                sqlExplainResults.add(SqlExplainResult.success(type, current, null));
            }

        } catch (Exception e) {
            sqlExplainResults.add(SqlExplainResult.fail(current, e.getMessage()));
        }
        return sqlExplainResults;
    }

    @Override
    public void testQuerySQL(String jdbcUrl, String username, String password, String sql) {
        try (Connection connection = ConnectionUtils.connect(jdbcUrl, username, password)) {
            database.testQuerySQL(connection, sql);
        } catch (SQLException se) {
            throw new RuntimeException(se);
        }
    }

    @Override
    public void executeSql(String jdbcUrl, String username, String password, String sql) {
        try (Connection connection = ConnectionUtils.connect(jdbcUrl, username, password)) {
            database.executeSql(connection, sql);
        } catch (SQLException se) {
            throw new RuntimeException(se);
        }
    }

    @Override
    public boolean tableExist(String jdbcUrl, String username, String password, String tableName) {
        try (Connection connection = ConnectionUtils.connect(jdbcUrl, username, password)) {
            database.executeSql(connection, String.format("SELECT 1 FROM %s WHERE 1=0", tableName));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getDDLCreateTableSQL(ProductTypeEnum type, List<ColumnDescription> fieldNames,
                                       List<String> primaryKeys, String schemaName, String tableName, boolean autoIncr) {
        return GenerateSqlUtils.getDDLCreateTableSQL(
                type, fieldNames, primaryKeys, schemaName, tableName, autoIncr);
    }



    @Override
    public String getSqlSelect(List<ColumnDescription> columns, String schemaName, String tableName, String tableRemarks) {
        StringBuilder sb = new StringBuilder("SELECT\n");
        for (int i = 0; i < columns.size(); i++) {
            sb.append("    ");
            if (i > 0) {
                sb.append(",");
            }
            String columnComment = columns.get(i).getRemarks();
            if (StrUtil.isNotBlank(columnComment)) {
                if (columnComment.contains("\'") | columnComment.contains("\"")) {
                    columnComment = columnComment.replaceAll("[\"']", "");
                }
                sb.append(columns.get(i).getFieldName()).append("  --  ").append(columnComment).append(" \n");
            } else {
                sb.append(columns.get(i).getFieldName()).append(" \n");

            }
        }
        if (StrUtil.isNotBlank(tableRemarks)) {
            sb.append(" FROM ").append(schemaName).append(".").append(tableName).append(";").append(" -- ").append(tableRemarks).append("\n");
        } else {
            sb.append(" FROM ").append(schemaName).append(".").append(tableName).append(";\n");
        }
        return sb.toString();
    }

    @Override
    public long countBySql(String jdbcUrl, String userName, String password, String countSql) {
        try (Connection connection = ConnectionUtils.connect(jdbcUrl, userName, password)) {
            return database.countBySql(connection, countSql);
        } catch (SQLException se) {
            throw new RuntimeException(se);
        }
    }

    @Override
    public Integer executeBatchInsertSql(String jdbcUrl, String username, String password, String sql) {
        try (Connection connection = ConnectionUtils.connect(jdbcUrl, username, password)) {
            return database.executeBatchInsertSql(connection, sql);
        } catch (SQLException se) {
            throw new RuntimeException(se);
        }
    }


    private String getFlinkTableWith(String flinkConfig, String schemaName, String tableName) {
        String tableWithSql = "";
        if (StrUtil.isNotBlank(flinkConfig)) {
            tableWithSql = SqlUtil.replaceAllParam(flinkConfig, "schemaName", schemaName);
            tableWithSql = SqlUtil.replaceAllParam(tableWithSql, "tableName", tableName);
        }
        return tableWithSql;
    }

    @Override
    public String getCountMoreThanOneSql(String schemaName, String tableName, List<String> columns) {
        return database.getCountMoreThanOneSql(schemaName, tableName, columns);
    }

    @Override
    public String getCountOneSql(String schemaName, String tableName, List<String> columns) {
        return database.getCountOneSql(schemaName, tableName, columns);
    }



}
