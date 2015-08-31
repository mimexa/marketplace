package paris.velocafe.marketplace.views;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.omnifaces.cdi.ViewScoped;

import paris.velocafe.marketplace.security.Security;
import paris.velocafe.marketplace.service.UserService;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class LoginController implements Serializable {

	@Inject
	private Security security;
	@Inject
	private UserService userService;

	private String login;
	private String password;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String connect() {
		try {
			security.setSecurity(new Security());
			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			if (request.getUserPrincipal() != null) {
				request.logout();
			}
			request.login(login, password);
			security.setSecurity(userService.find(login));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String addUser() {
		return null;

	}

	public String getName() {
		try {
			return FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
