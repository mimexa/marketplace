package paris.velocafe.marketplace.domain;

import static paris.velocafe.marketplace.security.Role.*;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import paris.velocafe.marketplace.security.Role;
import paris.velocafe.marketplace.security.Security;
import paris.velocafe.marketplace.utils.CommonUtils;

@SuppressWarnings("serial")
@SessionScoped
@Named
public class SecurityController implements Serializable {

	private Role roleView;

	@Inject
	private Security security;

	@PostConstruct
	public void postConstruct() {
		roleView = client;
	}

	public String getFullName() {
		return CommonUtils.toFullName(security.getSurname(), security.getName());
	}

	public String getPrefixe() {
		return security.getPrefix();
	}

	public boolean isAdminView() {
		return admin.equals(roleView) && isAdmin();
	}

	public boolean isClientView() {
		return client.equals(roleView);
	}

	public boolean isAdmin() {
		return security.getRoles().contains(admin);
	}

	public boolean isClient() {
		return security.getRoles().contains(client);
	}

	public Role getRoleView() {
		return roleView;
	}

	public void setRoleView(Role roleView) {
		this.roleView = roleView;
	}

}
