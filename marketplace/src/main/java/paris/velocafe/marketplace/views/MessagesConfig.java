package paris.velocafe.marketplace.views;

import static javax.faces.application.FacesMessage.*;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

@Singleton
@Startup
public class MessagesConfig {

	private volatile static MessagesConfig instance;
	private Messages.Resolver messageResolver;

	public static MessagesConfig getInstance() {
		if (instance == null) {
			synchronized (MessagesConfig.class) {
				if (instance == null) {
					instance = new MessagesConfig();
				}
			}
		}
		return instance;
	}

	public static String getMessage(String message, Object... params) {
		return MessagesConfig.getInstance().getMessageResolver().getMessage(message, params);
	}

	public MessagesConfig() {
		messageResolver = new Messages.Resolver() {

			public String getMessage(String message, Object... params) {
				FacesContext facesContext = FacesContext.getCurrentInstance();
				String messageBundle = facesContext.getApplication().getMessageBundle();
				ResourceBundle resourceBundle = null;
				if (messageBundle != null) {
					resourceBundle = ResourceBundle.getBundle(messageBundle, Faces.getLocale(), Thread.currentThread().getContextClassLoader());
				}
				if (resourceBundle != null && resourceBundle.containsKey(message)) {
					message = resourceBundle.getString(message);
				}
				if (params != null && params.length > 0) {
					try {
						message = MessageFormat.format(message, params);
					} catch (IllegalArgumentException err) {
						throw new IllegalArgumentException("Error formatting '" + message + "' with MessageFormat : " + err.toString(), err);
					}
				}
				return message;
			}
		};
	}

	@PostConstruct
	public void initialize() {
		Messages.setResolver(messageResolver);
	}

	@Produces
	@ApplicationScoped
	public Messages.Resolver getMessageResolver() {
		return messageResolver;
	}

	/**
	 * Renvoie un FacesMessage après l'avoir ajouté dans le contexte.<br/>
	 * La reference du message est relative au fichier messages.properties<br/>
	 */
	public static FacesMessage newFacesMessage(FacesContext facesContext, UIComponent component, String messageReference) {
		FacesMessage facesMessage = new FacesMessage();
		String message = getMessage(messageReference);
		facesMessage.setSeverity(SEVERITY_ERROR);
		facesMessage.setSummary(message);
		facesMessage.setDetail(message);
		facesContext.addMessage(component.getClientId(), facesMessage);
		return facesMessage;
	}

}
