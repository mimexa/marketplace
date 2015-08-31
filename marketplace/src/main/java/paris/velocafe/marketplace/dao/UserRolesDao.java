package paris.velocafe.marketplace.dao;

import static paris.velocafe.marketplace.utils.Constantes.Colonnes.UserRoles.*;
import static paris.velocafe.marketplace.utils.Constantes.Tables.*;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import paris.velocafe.marketplace.security.Role;
import paris.velocafe.marketplace.utils.CommonUtils;
import paris.velocafe.marketplace.utils.ObjectConverter;

@SuppressWarnings("serial")
@Stateless
public class UserRolesDao implements Serializable {

	@Resource(mappedName = "jdbc/velocafeDB")
	private DataSource ds;

	public List<Role> findRoles(final String userName) {
		List<String> roles = new JdbcTemplate(ds).queryForList("SELECT " + USERROLES_USERROLES + " FROM " + USERROLES + " WHERE " + USERNAME_USERROLES + " = '" + userName + "';", String.class);
		return ObjectConverter.toRoles(roles);
	}

	public void setRoles(final String userName, final Set<Role> roles) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
		if (CommonUtils.isNotNullOrEmpty(userName) && CommonUtils.isNotNullOrEmpty(roles)) {
			DaoUtils.execute(jdbcTemplate, "DELETE FROM " + USERROLES + " WHERE " + USERNAME_USERROLES + "=" + userName);
			for (Role role : roles) {
				DaoUtils.execute(jdbcTemplate, "INSERT INTO " + USERROLES + " VALUES ('" + userName + "', '" + role.toString() + "')");
			}
		}
	}

}
