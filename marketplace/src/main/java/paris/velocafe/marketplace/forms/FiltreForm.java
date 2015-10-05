package paris.velocafe.marketplace.forms;

import static paris.velocafe.marketplace.utils.Constantes.Categorie.*;
import static paris.velocafe.marketplace.utils.Constantes.Couleur.*;
import static paris.velocafe.marketplace.utils.Constantes.SousCategorie.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.faces.context.FacesContext;

import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.TreeNode;

import paris.velocafe.marketplace.domain.Args;
import paris.velocafe.marketplace.domain.Pair;
import paris.velocafe.marketplace.utils.CommonUtils;
import paris.velocafe.marketplace.views.StaticController;

/**
 * 
 * Formulaire du filtre de gauche
 * 
 * @author maxime
 *
 */
public class FiltreForm {

	private Set<String> marque;
	private Set<String> etat;
	private int prixMinimum;
	private int prixMaximum;
	private int anneeDebut;
	private int anneeFin;
	private TreeNode categoriesTree;
	private TreeNode[] selection;
	private Set<String> couleur;
	private Set<String> tailleCadre;
	private Set<String> disponibilite;
	private Set<String> typeUsage;
	private Set<String> userSize;

	// Constructeurs

	public FiltreForm() {
		marque = new HashSet<String>();
		etat = new HashSet<String>();
		prixMinimum = -1;
		prixMaximum = -1;
		categoriesTree = null;
		selection = null;
		tailleCadre = new HashSet<String>();
		disponibilite = new HashSet<String>();
		typeUsage = new HashSet<String>();
		userSize = new HashSet<String>();
	}

	// Assesseurs

	public int getAnneeDebut() {
		return anneeDebut;
	}

	public int getPrixMinimum() {
		return prixMinimum;
	}

	public Set<String> getEtat() {
		return etat;
	}

	public void setEtat(Set<String> etat) {
		this.etat = etat;
	}

	public void setPrixMinimum(int prixMinimum) {
		this.prixMinimum = prixMinimum;
	}

	public int getPrixMaximum() {
		return prixMaximum;
	}

	public void setPrixMaximum(int prixMaximum) {
		this.prixMaximum = prixMaximum;
	}

	public void setAnneeDebut(int anneeDebut) {
		this.anneeDebut = anneeDebut;
	}

	public int getAnneeFin() {
		return anneeFin;
	}

	public void setAnneeFin(int anneeFin) {
		this.anneeFin = anneeFin;
	}

	public Set<String> getMarque() {
		return marque;
	}

	public void setMarque(Set<String> marque) {
		this.marque = marque;
	}

	public Set<String> getTailleCadre() {
		return tailleCadre;
	}

	public void setTailleCadre(Set<String> tailleCadre) {
		this.tailleCadre = tailleCadre;
	}

	public Set<String> getDisponibilite() {
		return disponibilite;
	}

	public void setDisponibilite(Set<String> disponibilite) {
		this.disponibilite = disponibilite;
	}

	public Set<String> getTypeUsage() {
		return typeUsage;
	}

	public void setTypeUsage(Set<String> typeUsage) {
		this.typeUsage = typeUsage;
	}

	public Set<String> getUserSize() {
		return userSize;
	}

	public void setUserSize(Set<String> userSize) {
		this.userSize = userSize;
	}

	public void setCategoriesTree(TreeNode categoriesTree) {
		this.categoriesTree = categoriesTree;
	}

	public Map<Integer, Integer> getAnnees() {
		return Consts.annees;
	}

	public Map<String, String> getCouleurs() {
		return Consts.couleurs;
	}

	public TreeNode[] getSelection() {
		return selection;
	}

	public TreeNode getCategoriesTree() {
		return categoriesTree;
	}

	public void setSelection(TreeNode[] selection) {
		this.selection = selection;
	}

	public Set<String> getCouleur() {
		return couleur;
	}

	public void setCouleur(Set<String> couleur) {
		this.couleur = couleur;
	}

	// Methodes utiles

	public void reset() {
		marque.clear();
		etat.clear();
		prixMinimum = -1;
		prixMaximum = -1;
		categoriesTree = null;
		selection = null;
		tailleCadre.clear();
		disponibilite.clear();
		typeUsage.clear();
		userSize.clear();
	}

	public void refresh() {
		initialiseEmptyProperties();
		Map<String, String> attributes = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		setPropertiesWithAttributes(CommonUtils.toMap(attributes));
	}

	public void initialiseEmptyProperties() {
		if (CommonUtils.isNullOrEmpty(marque)) {
			marque.addAll(StaticController.Consts.marques.keySet());
		}
		if (anneeDebut < 0) {
			anneeDebut = Consts.anneeMinimum;
		}
		if (anneeFin < 0) {
			anneeFin = Consts.anneeMaximum;
		}
		if (prixMinimum < 0) {
			prixMinimum = Consts.prixMinimum;
		}
		if (prixMaximum < 0) {
			prixMaximum = Consts.prixMaximum;
		}
		if (CommonUtils.isNullOrEmpty(etat)) {
			etat.addAll(StaticController.Consts.etats.keySet());
		}
		if (CommonUtils.isNullOrEmpty(categoriesTree)) {
			categoriesTree = newTreeNode();
		}
		if (CommonUtils.isNullOrEmpty(tailleCadre)) {
			tailleCadre.addAll(StaticController.Consts.tailleCadres.keySet());
		}
		if (CommonUtils.isNullOrEmpty(disponibilite)) {
			disponibilite.addAll(StaticController.Consts.disponibilites.keySet());
		}
		if (CommonUtils.isNullOrEmpty(userSize)) {
			userSize.addAll(StaticController.Consts.userSizes.keySet());
		}
		if (CommonUtils.isNullOrEmpty(typeUsage)) {
			typeUsage.addAll(StaticController.Consts.typeUsages.keySet());
		}
	}

	@SuppressWarnings("unchecked")
	public void setPropertiesWithAttributes(final Set<Args<String>> args) {
		if (CommonUtils.isNotNullOrEmpty(args)) {
			for (Args<String> arg : args) {
				String param = arg.getParam();
				Set<String> values = arg.getValues();
				if (CommonUtils.isNotNullOrEmpty(param) && CommonUtils.isNotNullOrEmpty(values)) {
					if (Props.ETAT.equals(param)) {
						etat.clear();
						etat.addAll(values);
					} else if (Props.CATEGORIE.equals(param) && !isPropertyFound(Props.SOUS_CATEGORIE, args)) {
						for (String value : values) {
							for (TreeNode node : categoriesTree.getChildren()) {
								if (value.equals(((Pair<String, String>) node.getData()).getValue())) {
									node.setSelected(true);
								} else {
									node.setSelected(false);
								}
							}
						}
					} else if (Props.SOUS_CATEGORIE.equals(param)) {
						for (String value : values) {
							for (TreeNode node : categoriesTree.getChildren()) {
								node.setSelected(false);
								for (TreeNode subnode : node.getChildren()) {
									if (value.equals(((Pair<String, String>) subnode.getData()).getValue())) {
										subnode.setSelected(true);
									} else {
										subnode.setSelected(false);
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private static <E> boolean isPropertyFound(final String propertyName, Set<Args<E>> args) {
		boolean isPropertyFound = false;
		for (Args arg : args) {
			if (propertyName.equals(arg.getParam())) {
				isPropertyFound = true;
				break;
			}
		}
		return isPropertyFound;
	}

	private static <E> Args getProperty(final String propertyName, Set<Args<E>> args) {
		Args arg = null;
		for (Args ag : args) {
			if (propertyName.equals(ag.getParam())) {
				arg = ag;
				break;
			}
		}
		return arg;
	}

	public static TreeNode newTreeNode() {
		TreeNode categoriesTree = new CheckboxTreeNode();
		TreeNode velos = new CheckboxTreeNode(new Pair<String, String>(VELO, VELO), categoriesTree);
		TreeNode accessoires = new CheckboxTreeNode(new Pair<String, String>(ACCESSOIRE, ACCESSOIRE), categoriesTree);
		TreeNode vetements = new CheckboxTreeNode(new Pair<String, String>(VETEMENT, VETEMENT), categoriesTree);
		new CheckboxTreeNode(new Pair<String, String>(PIECE_DETACHEE, PIECE_DETACHEE), categoriesTree);
		new CheckboxTreeNode(new Pair<String, String>(CASQUE, CASQUE), accessoires);
		new CheckboxTreeNode(new Pair<String, String>(CADENAS, CADENAS), accessoires);
		new CheckboxTreeNode(new Pair<String, String>(VTT, VTT), velos);
		new CheckboxTreeNode(new Pair<String, String>(ELECTRIQUE, ELECTRIQUE), velos);
		new CheckboxTreeNode(new Pair<String, String>(RETRO, RETRO), velos);
		new CheckboxTreeNode(new Pair<String, String>(URBAN, URBAN), velos);
		new CheckboxTreeNode(new Pair<String, String>(VOYAGE, VOYAGE), velos);
		new CheckboxTreeNode(new Pair<String, String>(VILLE, VILLE), velos);
		new CheckboxTreeNode(new Pair<String, String>(PLIABLE, PLIABLE), velos);
		new CheckboxTreeNode(new Pair<String, String>(VTC, VTC), velos);
		new CheckboxTreeNode(new Pair<String, String>(SHORT, SHORT), vetements);
		new CheckboxTreeNode(new Pair<String, String>(TSHIRT, TSHIRT), vetements);
		new CheckboxTreeNode(new Pair<String, String>(CHAUSSURES, CHAUSSURES), vetements);
		new CheckboxTreeNode(new Pair<String, String>(GANTS, GANTS), vetements);
		new CheckboxTreeNode(new Pair<String, String>(SONNETTE, SONNETTE), accessoires);
		return categoriesTree;
	}

	public interface Props {
		String MARQUE = "marque";
		String ETAT = "etat";
		String PRIX_MAXIMUM = "prixMinimum";
		String PRIX_MINIMUM = "prixMaximum";
		String ANNEE_DEBUT = "anneeDebut";
		String ANNEE_FIN = "anneeFin";
		String CATEGORIE = "categorie";
		String SOUS_CATEGORIE = "sousCategorie";
		String tailleCadre = "tailleCadre";
		String disponibilite = "disponibilite";
		String typeUsage = "typeUsage";
		String userSize = "userSize";
	}

	/**
	 * Constantes locales utiles
	 *
	 */
	private static class Consts {
		static final int anneeMinimum = 1970;
		static final int anneeMaximum = CommonUtils.getYear();
		static final int prixMinimum = 0;
		static final int prixMaximum = 3000;
		/**
		 * Années de fabrication
		 */
		static final Map<Integer, Integer> annees = new TreeMap<Integer, Integer>();
		static {
			for (int year = anneeMinimum; year <= anneeMaximum; year++) {
				annees.put(year, year);
			}
		}
		static final Map<String, String> couleurs = new HashMap<String, String>();
		static {
			couleurs.put(BLEU.toString(), BLEU.toString());
			couleurs.put(BLANC.toString(), BLANC.toString());
			couleurs.put(ROUGE.toString(), ROUGE.toString());
			couleurs.put(VERT.toString(), VERT.toString());
			couleurs.put(JAUNE.toString(), JAUNE.toString());
		}
	}

}
