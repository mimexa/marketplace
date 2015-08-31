package paris.velocafe.marketplace.dao;

import static paris.velocafe.marketplace.domain.Operator.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.jboss.logging.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import paris.velocafe.marketplace.domain.Operator;
import paris.velocafe.marketplace.domain.Pair;
import paris.velocafe.marketplace.domain.SqlParams;
import paris.velocafe.marketplace.rowmapper.AbstractRowMapper;
import paris.velocafe.marketplace.utils.CommonUtils;

public abstract class DaoUtils {

	private static final Logger LOG = Logger.getLogger(DaoUtils.class);

	public static <X> int update(final JdbcTemplate jdbcTemplate, final X x, final AbstractRowMapper<X> rowMapper) {
		StringBuilder stringBuilder = new StringBuilder();
		List<Object> values = new LinkedList<Object>();
		String tableName = rowMapper.getTableName();
		stringBuilder.append("UPDATE ").append(tableName).append(" SET ");
		addValues(x, stringBuilder, values, rowMapper);
		stringBuilder.append(" WHERE ").append(tableName).append(".").append(rowMapper.getColumn(rowMapper.getIdPropertyName()));
		stringBuilder.append("=").append(getValueTypePair(x, rowMapper.getIdPropertyName()).getValue()).append(";");
		log(stringBuilder, values);
		return jdbcTemplate.update(stringBuilder.toString(), values.toArray());
	}

	public static <X> int insert(final JdbcTemplate jdbcTemplate, final X x, final AbstractRowMapper<X> rowMapper) {
		StringBuilder stringBuilder = new StringBuilder();
		List<Object> values = new LinkedList<Object>();
		String tableName = rowMapper.getTableName();
		stringBuilder.append("INSERT INTO ").append(tableName);
		insertValues(x, stringBuilder, values, rowMapper);
		log(stringBuilder, values);
		return jdbcTemplate.update(stringBuilder.toString(), values.toArray());
	}

	public static <X> List<X> query(final JdbcTemplate jdbcTemplate, final Set<SqlParams<?>> args, final AbstractRowMapper<X> rowMapper) {
		StringBuilder stringBuilder = new StringBuilder();
		List<Object> values = new LinkedList<Object>();
		stringBuilder.append("SELECT * FROM " + rowMapper.getTableName() + " WHERE 1=1");
		if (CommonUtils.isNotNullOrEmpty(args)) {
			for (SqlParams<?> sqlParam : args) {
				appendSqlParam(stringBuilder, values, sqlParam, rowMapper);
			}
		}
		stringBuilder.append(";");
		log(stringBuilder, values);
		return jdbcTemplate.query(stringBuilder.toString(), values.toArray(), rowMapper);
	}

	public static void execute(final JdbcTemplate jdbcTemplate, final String sql) {
		log(new StringBuilder(sql));
		jdbcTemplate.execute(sql);
	}

	public static <X> List<X> queryAll(final JdbcTemplate jdbcTemplate, final AbstractRowMapper<X> rowMapper) {
		return query(jdbcTemplate, null, rowMapper);
	}

	public static <X> X queryForSingleResult(final JdbcTemplate jdbcTemplate, final Set<SqlParams<?>> args, final AbstractRowMapper<X> rowMapper) {
		X x = null;
		List<X> xs = query(jdbcTemplate, args, rowMapper);
		if (xs != null && xs.size() == 1) {
			x = xs.get(0);
		}
		return x;
	}

	private static void appendMultipleValues(final StringBuilder stringBuilder, final List<Object> values, final Set<?> values2) {
		if (CommonUtils.isNotNullOrEmpty(values2)) {
			String separator = "";
			for (Object value : values2) {
				if (CommonUtils.isNotNullOrEmpty(value)) {
					if (value instanceof Date) {
						stringBuilder.append(separator).append("DATE_FORMAT(?, '%Y-%d-%m')");
					} else {
						stringBuilder.append(separator).append("?");
					}
					values.add(value);
					separator = ",";
				}
			}
		}
	}

	private static void appendSingleValue(final StringBuilder stringBuilder, final List<Object> values, final Set<?> values2) {
		appendMultipleValues(stringBuilder, values, values2);
	}

	private static void appendSqlParam(final StringBuilder stringBuilder, final List<Object> values, final SqlParams<?> sqlParams, final AbstractRowMapper<?> rowMapper) {
		Operator operator = sqlParams != null ? sqlParams.getOperator() : null;
		Set<?> values2 = sqlParams != null ? sqlParams.getValues() : null;
		String column = getColumnName(sqlParams, rowMapper);
		if (CommonUtils.isNotNullOrEmpty(values2) && CommonUtils.isNotNullOrEmpty(column)) {
			stringBuilder.append(" AND ").append(column);
			if (EQUALS.equals(operator)) {
				stringBuilder.append(" IN(");
				appendMultipleValues(stringBuilder, values, values2);
				stringBuilder.append(")");
			} else if (NOT_EQUALS.equals(operator)) {
				stringBuilder.append(" NOT IN(");
				appendMultipleValues(stringBuilder, values, values2);
				stringBuilder.append(")");
			} else if (SUPERIOR.equals(operator)) {
				stringBuilder.append(">");
				appendSingleValue(stringBuilder, values, values2);
			} else if (SUPERIOR_OR_EQUALS.equals(operator)) {
				stringBuilder.append(">=");
				appendSingleValue(stringBuilder, values, values2);
			} else if (INFEROR.equals(operator)) {
				stringBuilder.append("<");
				appendSingleValue(stringBuilder, values, values2);
			} else if (INFERIOR_OR_EQUALS.equals(operator)) {
				stringBuilder.append("<=");
				appendSingleValue(stringBuilder, values, values2);
			}
		}
	}

	private static String getColumnName(final SqlParams<?> sqlParams, final AbstractRowMapper<?> rowMapper) {
		String column = sqlParams != null ? rowMapper.getColumn(sqlParams.getParam()) : null;
		return column != null ? rowMapper.getTableName() + "." + column : null;
	}

	private static <E> Integer getType(final E e) {
		Integer type = null;
		if (e instanceof Long) {
			type = Types.FLOAT;
		} else if (e instanceof Double) {
			type = Types.DOUBLE;
		} else {
			type = Types.VARCHAR;
		}
		return type;
	}

	private static <X> void addValues(final X x, final StringBuilder stringBuilder, final List<Object> values, final AbstractRowMapper<X> rowMapper) {
		String separator = "";
		String idPropertyName = rowMapper.getIdPropertyName();
		for (Field field : x.getClass().getDeclaredFields()) {
			String property = field.getName();
			String columnName = rowMapper.getColumn(property);
			if (CommonUtils.isNotNullOrEmpty(columnName) && !idPropertyName.equals(property)) {
				Pair<Object, Integer> pair = getValueTypePair(x, property);
				Object value = pair.getValue();
				Integer type = pair.getLabel();
				if (CommonUtils.isNotNullOrEmpty(value) && type != null) {
					stringBuilder.append(separator).append(rowMapper.getTableName()).append(".").append(columnName).append("=");
					if (value instanceof Date || value instanceof java.util.Date) {
						stringBuilder.append("DATE_FORMAT(?, '%Y-%d-%m')");
					} else {
						stringBuilder.append("?");
					}
					values.add(value);
					separator = ", ";
				}
			}
		}
	}

	private static <X> Pair<Object, Integer> getValueTypePair(final X x, final String propertyName) {
		Method method;
		try {
			method = x.getClass().getMethod("get" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1));
			method.setAccessible(true);
			Object value = method.invoke(x);
			return new Pair<Object, Integer>(value, getType(value));
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			return null;
		} catch (SecurityException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
	}

	private static <X> void insertValues(final X x, final StringBuilder stringBuilder, final List<Object> values, final AbstractRowMapper<X> rowMapper) {
		StringBuilder stringValues = new StringBuilder(" VALUES (");
		stringBuilder.append("(");
		String separator = "";
		String idPropertyName = rowMapper.getIdPropertyName();
		for (Field field : x.getClass().getDeclaredFields()) {
			String property = field.getName();
			String columnName = rowMapper.getColumn(property);
			if (CommonUtils.isNotNullOrEmpty(columnName) && !idPropertyName.equals(property)) {
				Pair<Object, Integer> pair = getValueTypePair(x, property);
				Object value = pair.getValue();
				Integer type = pair.getLabel();
				if (CommonUtils.isNotNullOrEmpty(value) && type != null) {
					stringBuilder.append(separator).append(rowMapper.getTableName()).append(".").append(columnName);
					if (type.equals(Types.DATE)) {
						stringValues.append(separator).append("DATE_FORMAT(?, '%Y-%d-%m')");
					} else {
						stringValues.append(separator).append("?");
					}
					values.add(value);
					separator = ", ";
				}
			}
		}
		stringValues.append(");");
		stringBuilder.append(")").append(stringValues.toString());
	}

	public static <T> Long getNewAvaibleId(final JdbcTemplate jdbcTemplate, final AbstractRowMapper<T> rowMapper) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("SELECT MAX(").append(rowMapper.getColumn(rowMapper.getIdPropertyName()));
		stringBuilder.append(") FROM ").append(rowMapper.getTableName()).append(";");
		log(stringBuilder);
		Long maxId = jdbcTemplate.queryForObject(stringBuilder.toString(), Long.class);
		return maxId != null ? maxId + 1 : null;
	}

	private static void log(final StringBuilder stringBuilder, final List<Object> values) {
		if (CommonUtils.isNotNullOrEmpty(stringBuilder)) {
			LOG.infof("Exécution d'une requête :" + stringBuilder.toString());
			if (CommonUtils.isNotNullOrEmpty(values)) {
				LOG.info("Valeurs :" + CommonUtils.toString(values));
			}
		}
	}

	private static void log(final StringBuilder stringBuilder) {
		log(stringBuilder, null);
	}

	public static <X> void delete(final JdbcTemplate jdbcTemplate, final Long id, final AbstractRowMapper<X> rowmapper) {
		StringBuilder stringBuilder = new StringBuilder("DELETE FROM ");
		stringBuilder.append(rowmapper.getTableName()).append(" WHERE ");
		stringBuilder.append(rowmapper.getColumn(rowmapper.getIdPropertyName()));
		stringBuilder.append("=").append(id);
		log(stringBuilder);
		jdbcTemplate.execute(stringBuilder.toString());
	}

}
