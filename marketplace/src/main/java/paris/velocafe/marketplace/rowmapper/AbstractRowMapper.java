package paris.velocafe.marketplace.rowmapper;

import org.springframework.jdbc.core.RowMapper;

public abstract class AbstractRowMapper<T> implements RowMapper<T> {

	public abstract String getTableName();

	public abstract String getColumn(String propertie);

	public abstract String getIdPropertyName();

}
