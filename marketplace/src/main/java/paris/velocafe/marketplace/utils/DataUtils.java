package paris.velocafe.marketplace.utils;

import static paris.velocafe.marketplace.domain.Operator.*;
import static paris.velocafe.marketplace.entity.ProduitDb.Props.*;

import java.util.HashSet;
import java.util.Set;

import org.primefaces.model.TreeNode;

import paris.velocafe.marketplace.domain.Pair;
import paris.velocafe.marketplace.domain.SecurityController;
import paris.velocafe.marketplace.domain.SqlParams;
import paris.velocafe.marketplace.entity.ProduitDb.Props;
import paris.velocafe.marketplace.forms.FiltreForm;

public class DataUtils {

	public static Set<SqlParams<?>> filtreToSqlparams(final FiltreForm filtreForm, final SecurityController securityController) {
		Set<SqlParams<?>> args = new HashSet<SqlParams<?>>();
		Set<String> couleur = filtreForm.getCouleur();
		if (CommonUtils.isNotNullOrEmpty(couleur)) {
			args.add(new SqlParams<String>("couleur", EQUALS, couleur));
		}
		Set<String> etat = filtreForm.getEtat();
		if (CommonUtils.isNotNullOrEmpty(etat)) {
			args.add(new SqlParams<String>(Props.etat, EQUALS, etat));
		}
		Set<String> marque = filtreForm.getMarque();
		if (CommonUtils.isNotNullOrEmpty(marque)) {
			args.add(new SqlParams<String>(Props.marque, EQUALS, marque));
		}
		int prixMaximum = filtreForm.getPrixMaximum();
		if (prixMaximum > 0) {
			args.add(new SqlParams<Double>(montantHt, INFERIOR_OR_EQUALS, getMontantHT(prixMaximum)));
		}
		int prixMinimum = filtreForm.getPrixMinimum();
		if (prixMinimum > 0) {
			args.add(new SqlParams<Double>(montantHt, SUPERIOR_OR_EQUALS, getMontantHT(prixMinimum)));
		}
		Set<String> categorie = toSelection(filtreForm.getCategoriesTree());
		if (CommonUtils.isNotNullOrEmpty(categorie)) {
			args.add(new SqlParams<String>(Props.categorie, EQUALS, categorie));
		}
		Set<String> tailleCadre = filtreForm.getTailleCadre();
		if (CommonUtils.isNotNullOrEmpty(tailleCadre)) {
			args.add(new SqlParams<String>(Props.tailleCadre, EQUALS, tailleCadre));
		}
		Set<String> disponibilite = filtreForm.getDisponibilite();
		if (CommonUtils.isNotNullOrEmpty(disponibilite)) {
			args.add(new SqlParams<String>(Props.disponibilite, EQUALS, disponibilite));
		}
		Set<String> typeUsage = filtreForm.getTypeUsage();
		if (CommonUtils.isNotNullOrEmpty(typeUsage)) {
			args.add(new SqlParams<String>(Props.typeUsage, EQUALS, typeUsage));
		}
		Set<String> userSize = filtreForm.getUserSize();
		if (CommonUtils.isNotNullOrEmpty(userSize)) {
			args.add(new SqlParams<String>(Props.userSize, EQUALS, userSize));
		}
		if (!securityController.isAdminView()) {
			args.add(new SqlParams<String>(Props.afficherProduit, EQUALS, Consts.OUI));
		}
		return args;
	}

	@SuppressWarnings("unchecked")
	private static Set<String> toSelection(final TreeNode treeNodes) {
		Set<String> selection = new HashSet<String>();
		for (TreeNode treeNode : treeNodes.getChildren()) {
			if (treeNode.isSelected()) {
				selection.add(((Pair<String, String>) treeNode.getData()).getValue());
			}
		}
		return selection;
	}

	private static double getMontantHT(final int montantTTC) {
		return montantTTC * (1 - Consts.tva);
	}

	private interface Consts {
		double tva = 0.196;
		String OUI = "O";
	}

}
