/*
 * Copyright (c) 2017 Teradata
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.teradata.jaqy.resultset;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;

import com.teradata.jaqy.PropertyTable;
import com.teradata.jaqy.schema.FullColumnInfo;
import com.teradata.jaqy.utils.ExceptionUtils;
import com.teradata.jaqy.utils.ResultSetMetaDataUtils;

/**
 * This class keeps a copy of ResultSetMetaData for in-memory ResultSet.
 *
 * @author	Heng Yuan
 */
class InMemoryResultSetMetaData implements ResultSetMetaData
{
	private final FullColumnInfo[] m_columnInfos;

	/**
	 * Make a copy of the ResultSetMetaData
	 * @param	meta
	 *			The ResultSetMetaData to be copied.
	 * @throws	SQLException
	 * 			In case of any SQL exception
	 */
	InMemoryResultSetMetaData (ResultSetMetaData meta) throws SQLException
	{
		m_columnInfos = ResultSetMetaDataUtils.getColumnInfo (meta).columns;
	}

	/**
	 * Make a copy of the ResultSetMetaData
	 * @param	meta
	 *			The ResultSetMetaData to be copied.
	 * @throws	SQLException
	 * 			In case of any SQL exception
	 */
	InMemoryResultSetMetaData (PropertyTable pt)
	{
		String[] titles = pt.getTitles ();
		int columnCount = titles.length;
		FullColumnInfo[] columnInfos = new FullColumnInfo[columnCount];
		m_columnInfos = columnInfos;
		int lengths[] = pt.getLengths ();

		for (int i = 0; i < columnCount; ++i)
		{
			FullColumnInfo columnInfo = new FullColumnInfo ();
			columnInfos[i] = columnInfo;

			columnInfo.autoIncrement = false;
			columnInfo.caseSensitive = false;
			columnInfo.searchable = false;
			columnInfo.currency = false;
			columnInfo.nullable = columnNullable;
			columnInfo.signed = false;
			columnInfo.displaySize = lengths[i];
			columnInfo.label = titles[i];
			columnInfo.name = titles[i];
			columnInfo.schemaName = null;
			columnInfo.precision = 0;
			columnInfo.scale = 0;
			columnInfo.tableName = null;
			columnInfo.catalogName = null;
			columnInfo.type = Types.VARCHAR;
			columnInfo.typeName = "VARCHAR";
			columnInfo.readOnly = true;
			columnInfo.writable = false;
			columnInfo.definitelyWritable = false;
			columnInfo.className = "java.lang.String";
		}
	}

	private void checkColumnIndex (int column) throws SQLException
	{
		if (column < 1 || column > m_columnInfos.length)
			throw ExceptionUtils.getInvalidColumnIndex (column);
	}

	private void notImplemented () throws SQLException
	{
		throw ExceptionUtils.getNotImplemented ();
	}

	@Override
	public <T> T unwrap (Class<T> iface) throws SQLException
	{
		notImplemented ();
		return null;
	}

	@Override
	public boolean isWrapperFor (Class<?> iface) throws SQLException
	{
		notImplemented ();
		return false;
	}

	@Override
	public int getColumnCount () throws SQLException
	{
		return m_columnInfos.length;
	}

	@Override
	public boolean isAutoIncrement (int column) throws SQLException
	{
		checkColumnIndex (column);
		return m_columnInfos[column - 1].autoIncrement;
	}

	@Override
	public boolean isCaseSensitive (int column) throws SQLException
	{
		checkColumnIndex (column);
		return m_columnInfos[column - 1].caseSensitive;
	}

	@Override
	public boolean isSearchable (int column) throws SQLException
	{
		checkColumnIndex (column);
		return m_columnInfos[column - 1].searchable;
	}

	@Override
	public boolean isCurrency (int column) throws SQLException
	{
		checkColumnIndex (column);
		return m_columnInfos[column - 1].currency;
	}

	@Override
	public int isNullable (int column) throws SQLException
	{
		checkColumnIndex (column);
		return m_columnInfos[column - 1].nullable;
	}

	@Override
	public boolean isSigned (int column) throws SQLException
	{
		checkColumnIndex (column);
		return m_columnInfos[column - 1].signed;
	}

	@Override
	public int getColumnDisplaySize (int column) throws SQLException
	{
		checkColumnIndex (column);
		return m_columnInfos[column - 1].displaySize;
	}

	@Override
	public String getColumnLabel (int column) throws SQLException
	{
		checkColumnIndex (column);
		return m_columnInfos[column - 1].label;
	}

	@Override
	public String getColumnName (int column) throws SQLException
	{
		checkColumnIndex (column);
		return m_columnInfos[column - 1].name;
	}

	@Override
	public String getSchemaName (int column) throws SQLException
	{
		checkColumnIndex (column);
		return m_columnInfos[column - 1].schemaName;
	}

	@Override
	public int getPrecision (int column) throws SQLException
	{
		checkColumnIndex (column);
		return m_columnInfos[column - 1].precision;
	}

	@Override
	public int getScale (int column) throws SQLException
	{
		checkColumnIndex (column);
		return m_columnInfos[column - 1].scale;
	}

	@Override
	public String getTableName (int column) throws SQLException
	{
		checkColumnIndex (column);
		return m_columnInfos[column - 1].tableName;
	}

	@Override
	public String getCatalogName (int column) throws SQLException
	{
		checkColumnIndex (column);
		return m_columnInfos[column - 1].catalogName;
	}

	@Override
	public int getColumnType (int column) throws SQLException
	{
		checkColumnIndex (column);
		return m_columnInfos[column - 1].type;
	}

	@Override
	public String getColumnTypeName (int column) throws SQLException
	{
		checkColumnIndex (column);
		return m_columnInfos[column - 1].typeName;
	}

	@Override
	public boolean isReadOnly (int column) throws SQLException
	{
		checkColumnIndex (column);
		return m_columnInfos[column - 1].readOnly;
	}

	@Override
	public boolean isWritable (int column) throws SQLException
	{
		checkColumnIndex (column);
		return m_columnInfos[column - 1].writable;
	}

	@Override
	public boolean isDefinitelyWritable (int column) throws SQLException
	{
		checkColumnIndex (column);
		return m_columnInfos[column - 1].definitelyWritable;
	}

	@Override
	public String getColumnClassName (int column) throws SQLException
	{
		checkColumnIndex (column);
		return m_columnInfos[column - 1].className;
	}
}
