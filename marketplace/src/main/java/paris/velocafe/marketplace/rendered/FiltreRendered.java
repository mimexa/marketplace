package paris.velocafe.marketplace.rendered;

import paris.velocafe.marketplace.forms.FiltreForm;
import static paris.velocafe.marketplace.utils.Constantes.Etat.*;

public class FiltreRendered {

	private final FiltreForm form;

	public FiltreRendered(FiltreForm form) {
		this.form = form;
	}

	public boolean isAnneesRendered() {
		return form.getEtat().contains(OCCASION);
	}

}
