package paris.velocafe.marketplace.dao;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import paris.velocafe.marketplace.domain.Operator;
import paris.velocafe.marketplace.domain.SqlParams;
import paris.velocafe.marketplace.rowmapper.AbstractRowMapper;
import paris.velocafe.marketplace.rowmapper.UserRowMapper;
import paris.velocafe.marketplace.security.Security;
import paris.velocafe.marketplace.security.Security.Props;

@SuppressWarnings("serial")
@Stateless
public class UserDao implements Serializable {

	private static final AbstractRowMapper<Security> rowMapper = new UserRowMapper();

	@Resource(mappedName = "jdbc/velocafeDB")
	private DataSource ds;

	public Security find(final String userName) {
		Security security = null;
		try {
			Set<SqlParams<?>> params = new HashSet<SqlParams<?>>();
			params.add(new SqlParams<String>(Props.username, Operator.EQUALS, userName));
			security = DaoUtils.queryForSingleResult(new JdbcTemplate(ds), params, rowMapper);
		} catch (Exception e) {
			return null;
		}
		return security;
	}

	public int insert(final Security security) {
		return DaoUtils.insert(new JdbcTemplate(ds), security, rowMapper);
	}

	public int update(final Security security) {
		Integer ordre = DaoUtils.update(new JdbcTemplate(ds), security, rowMapper);
		return ordre != null ? ordre + 1 : 1;
	}
}
