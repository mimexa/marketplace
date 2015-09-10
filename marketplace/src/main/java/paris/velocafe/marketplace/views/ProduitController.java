package paris.velocafe.marketplace.views;

import static paris.velocafe.marketplace.utils.Constantes.Categorie.*;
import static paris.velocafe.marketplace.utils.Constantes.PiecesDetachees.*;
import static paris.velocafe.marketplace.utils.Constantes.Accessoires.*;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.deltaspike.core.api.scope.ViewAccessScoped;

import paris.velocafe.marketplace.domain.Produit;
import paris.velocafe.marketplace.domain.Produit.Props;
import paris.velocafe.marketplace.forms.ProduitForm;
import paris.velocafe.marketplace.service.ProduitService;
import paris.velocafe.marketplace.utils.CommonUtils;
import paris.velocafe.marketplace.utils.Graphics;
import paris.velocafe.marketplace.utils.ObjectConverter;

@Named
@ViewAccessScoped
@SuppressWarnings("serial")
public class ProduitController implements Serializable {

	private ProduitForm form;

	@Inject
	private ProduitService produitService;
	@Inject
	private ImageController imageController;

	@PostConstruct
	public void init() {
		Map<String, String> attributes = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		Long idProduit = attributes != null ? CommonUtils.parseLong(attributes.get(Props.idProduit)) : null;
		Produit produit = CommonUtils.isNotNullOrEmpty(idProduit) ? produitService.findProduitById(idProduit) : null;
		form = produit != null ? ObjectConverter.toProduitForm(produit) : new ProduitForm();
	}

	public ProduitForm getForm() {
		return form;
	}

	public void enregistrer() {
		if (CommonUtils.isNotNullOrEmpty(form.getIdProduit()) && CommonUtils.isNotNullOrEmpty(produitService.findProduitById(form.getIdProduit()))) {
			produitService.update(ObjectConverter.toProduit(form));
		} else {
			Produit produit = ObjectConverter.toProduit(form);
			produitService.insert(produit);
			form.setIdProduit(produit.getIdProduit());
		}
		imageController.enregistrer(form.getIdProduit());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Succès de la demande", "Les données du produit ont été mises à jour"));
	}

	public String reset() {
		ProduitForm.reset(form);
		imageController.getImgs().clear();
		return null;
	}

	public int getFicheWidth() {
		return Graphics.ficheWidth + 30;
	}

	public boolean getIsVelo() {
		return form == null ? false : VELO.equals(form.getCategorie());
	}

	public Map<String, String> getSousCategories() {
		Map<String, String> sousCategories = null;
		switch (form.getCategorie()) {
		case VELO:
			sousCategories = StaticController.Consts.velos;
			break;
		case ACCESSOIRE:
			sousCategories = StaticController.Consts.accessoires;
			break;
		case PIECE_DETACHEE:
			sousCategories = StaticController.Consts.piecesDetachees;
			break;
		}
		return sousCategories;
	}

	public Map<String, String> getSousSousCategories() {
		Map<String, String> sousSousCategories;
		String sousSousCategorie = form.getCategorie();
		if (sousSousCategorie == null) {
			sousSousCategories = null;
		} else if (sousSousCategorie.equals(LUMIERES.toString())) {
			sousSousCategories = StaticController.Consts.lumieres;
		} else if (sousSousCategorie.equals(PNEUS.toString())) {
			sousSousCategories = StaticController.Consts.pneus;
		} else if (sousSousCategorie.equals(SELLES.toString())) {
			sousSousCategories = StaticController.Consts.selles;
		} else if (sousSousCategorie.equals(FREINS.toString())) {
			sousSousCategories = StaticController.Consts.freins;
		} else if (sousSousCategorie.equals(DIRECTIONS.toString())) {
			sousSousCategories = StaticController.Consts.directions;
		} else if (sousSousCategorie.equals(TRANSMISS.toString())) {
			sousSousCategories = StaticController.Consts.transmissions;
		} else if (sousSousCategorie.equals(CASQUES.toString())) {
			sousSousCategories = StaticController.Consts.casques;
		} else if (sousSousCategorie.equals(SONNETTES.toString())) {
			sousSousCategories = StaticController.Consts.sonnettes;
		} else if (sousSousCategorie.equals(CADENAS.toString())) {
			sousSousCategories = StaticController.Consts.cadenas;
		} else if (sousSousCategorie.equals(SACCOCHES.toString())) {
			sousSousCategories = StaticController.Consts.saccoches;
		} else if (sousSousCategorie.equals(ORDIS.toString())) {
			sousSousCategories = StaticController.Consts.ordinateur;
		} else if (sousSousCategorie.equals(POMPS.toString())) {
			sousSousCategories = StaticController.Consts.pompes;
		} else {
			sousSousCategories = null;
		}
		return sousSousCategories;
	}
}
