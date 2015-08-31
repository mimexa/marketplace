package paris.velocafe.marketplace.views;

import java.io.Serializable;
import java.util.Map;

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

}
