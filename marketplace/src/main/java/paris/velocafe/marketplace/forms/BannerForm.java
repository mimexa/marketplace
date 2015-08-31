package paris.velocafe.marketplace.forms;

import java.util.Locale;

import javax.faces.context.FacesContext;

public class BannerForm {

	private String lang;
	private Locale locale;

	public BannerForm() {
		locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		lang = locale.getLanguage().toUpperCase();
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

}
