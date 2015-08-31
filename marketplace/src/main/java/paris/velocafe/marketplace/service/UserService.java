package paris.velocafe.marketplace.service;

import java.util.HashSet;

import javax.ejb.Stateless;
import javax.inject.Inject;

import paris.velocafe.marketplace.dao.UserDao;
import paris.velocafe.marketplace.dao.UserRolesDao;
import paris.velocafe.marketplace.security.Role;
import paris.velocafe.marketplace.security.Security;

@Stateless
public class UserService {

	@Inject
	private UserDao userDao;
	@Inject
	private UserRolesDao userRolesDao;

	public Security find(final String userName) {
		Security security = userDao.find(userName);
		if (security != null) {
			security.setRoles(new HashSet<Role>(userRolesDao.findRoles(userName)));
		}
		return security;
	}

	public int insert(final Security security) {
		int i = 0;
		if (security != null) {
			i = userDao.insert(security);
			userRolesDao.setRoles(security.getUsername(), security.getRoles());
		}
		return i;
	}

	public int updateWithRoles(final Security security) {
		return updateRoles(security, true);
	}

	public int updateWithoutRoles(final Security security) {
		return updateRoles(security, false);
	}

	private int updateRoles(final Security security, final boolean updateRoles) {
		int i = userDao.update(security);
		if (updateRoles) {
			userRolesDao.setRoles(security.getUsername(), security.getRoles());
		}
		return i;
	}

}
