package paris.velocafe.marketplace.views;

import static paris.velocafe.marketplace.domain.MiniProduit.Props.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.deltaspike.core.api.scope.ViewAccessScoped;

import paris.velocafe.marketplace.domain.Args;
import paris.velocafe.marketplace.domain.MiniProduit;
import paris.velocafe.marketplace.service.ProduitService;
import paris.velocafe.marketplace.utils.CommonUtils;
import paris.velocafe.marketplace.utils.Graphics;
import paris.velocafe.marketplace.utils.XhtmlPages;

@SuppressWarnings("serial")
@Named
@ViewAccessScoped
public class MozaiqueController implements Serializable {

	@Inject
	private ProduitService produitService;
	@Inject
	private FiltreController filtreController;

	private Set<MiniProduit> miniProduits;

	@PostConstruct
	public void postConstruct() {
		miniProduits = new HashSet<MiniProduit>();
	}

	// Assesseurs

	public Set<MiniProduit> getMiniProduits() {
		return miniProduits;
	}

	public int getLeftPosition() {
		return Graphics.filtreWidth;
	}

	// Actions

	public void refresh() {
		miniProduits.clear();
		miniProduits.addAll(produitService.findMiniProduit(filtreController.getForm()));
	}

	public void goToDetail(MiniProduit miniProduit) {
		CommonUtils.toUrlAndParams(XhtmlPages.PRODUIT_XHTML, Arrays.asList(new Args<Long>(ID_PRODUIT, miniProduit.getIdProduit())));
	}
}
