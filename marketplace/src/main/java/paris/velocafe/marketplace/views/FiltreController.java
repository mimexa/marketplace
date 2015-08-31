package paris.velocafe.marketplace.views;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.apache.deltaspike.core.api.scope.ViewAccessScoped;

import paris.velocafe.marketplace.forms.FiltreForm;
import paris.velocafe.marketplace.rendered.FiltreRendered;
import paris.velocafe.marketplace.utils.Graphics;

/**
 * Controlleur du filtre de gauche
 * 
 * @author maxime
 *
 */
@SuppressWarnings("serial")
@Named
@ViewAccessScoped
public class FiltreController implements Serializable {

	private FiltreForm form;
	private FiltreRendered rendered;

	@PostConstruct
	public void postConstruct() {
		form = new FiltreForm();
		rendered = new FiltreRendered(form);
	}

	// Assesseurs

	public FiltreForm getForm() {
		return form;
	}

	public FiltreRendered getRendered() {
		return rendered;
	}

	public int getFiltreWidth() {
		return Graphics.filtreWidth;
	}

	public int getTreeWidth() {
		return Graphics.filtreWidth - 20;
	}

	// Actions

	public void reset() {
		form.reset();
		form.refresh();
	}

	public void refresh() {
		form.refresh();
	}

}
