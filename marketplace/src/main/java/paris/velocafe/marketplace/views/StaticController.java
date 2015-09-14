package paris.velocafe.marketplace.views;

import static paris.velocafe.marketplace.utils.Constantes.Binaire.*;
import static paris.velocafe.marketplace.utils.Constantes.Categorie.*;
import static paris.velocafe.marketplace.utils.Constantes.Disponibilite.*;
import static paris.velocafe.marketplace.utils.Constantes.Etat.*;
import static paris.velocafe.marketplace.utils.Constantes.TailleCadre.*;
import static paris.velocafe.marketplace.utils.Constantes.TypeUsage.*;
import static paris.velocafe.marketplace.utils.Constantes.UserSize.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import paris.velocafe.marketplace.utils.CommonUtils;
import paris.velocafe.marketplace.utils.Constantes.Accessoires;
import paris.velocafe.marketplace.utils.Constantes.Cadenas;
import paris.velocafe.marketplace.utils.Constantes.Casque;
import paris.velocafe.marketplace.utils.Constantes.Couleur;
import paris.velocafe.marketplace.utils.Constantes.Direction;
import paris.velocafe.marketplace.utils.Constantes.Frein;
import paris.velocafe.marketplace.utils.Constantes.Lumiere;
import paris.velocafe.marketplace.utils.Constantes.Marque;
import paris.velocafe.marketplace.utils.Constantes.Ordinateur;
import paris.velocafe.marketplace.utils.Constantes.Panier;
import paris.velocafe.marketplace.utils.Constantes.PiecesDetachees;
import paris.velocafe.marketplace.utils.Constantes.Pneu;
import paris.velocafe.marketplace.utils.Constantes.Pompe;
import paris.velocafe.marketplace.utils.Constantes.Saccoche;
import paris.velocafe.marketplace.utils.Constantes.Selle;
import paris.velocafe.marketplace.utils.Constantes.Sonnette;
import paris.velocafe.marketplace.utils.Constantes.Transmission;
import paris.velocafe.marketplace.utils.Constantes.Velos;

/**
 * Menus disponibles pour les pages xhtmls
 * 
 * @author maxime
 *
 */
@SuppressWarnings("serial")
@Named
@ApplicationScoped
public class StaticController implements Serializable {

	public Map<String, String> getEtats() {
		return Consts.etats;
	}

	public Map<String, String> getBinaire() {
		return Consts.binaire;
	}

	public Map<String, String> getEtatsTous() {
		return Consts.etatsTous;
	}

	public Map<String, String> getCategories() {
		return Consts.categories;
	}

	public Map<String, String> getMarques() {
		return Consts.marques;
	}

	public Map<String, String> getMarquesTous() {
		return Consts.marquesTous;
	}

	public Map<String, String> getTailleCadres() {
		return Consts.tailleCadres;
	}

	public Map<String, String> getDisponibilites() {
		return Consts.disponibilites;
	}

	public Map<String, String> getTypeUsages() {
		return Consts.typeUsages;
	}

	public Map<String, String> getUserSizes() {
		return Consts.userSizes;
	}

	public Map<String, String> getTailleCadresTous() {
		return Consts.tailleCadresTous;
	}

	public Map<String, String> getDisponibilitesTous() {
		return Consts.disponibilitesTous;
	}

	public Map<String, String> getTypeUsagesTous() {
		return Consts.typeUsagesTous;
	}

	public Map<String, String> getUserSizesTous() {
		return Consts.userSizesTous;
	}

	public static class Consts {
		public static final Map<String, String> binaire = new HashMap<String, String>();
		static {
			binaire.put("O", OUI);
			binaire.put("N", NON);
		}

		public static final String tousLabel = "-- Tous --";
		public static final String tousValue = "TOUS";
		public static final Map<String, String> etats = new HashMap<String, String>();
		static {
			etats.put(NEUF, NEUF);
			etats.put(OCCASION, OCCASION);
			etats.put(REVISE, REVISE);
			etats.put(POUR_PIECES, POUR_PIECES);
			etats.put(A_REVISER, A_REVISER);
		}
		public static final Map<String, String> etatsTous = new HashMap<String, String>();
		static {
			etatsTous.put(tousValue, tousLabel);
			etatsTous.putAll(etats);
		}
		public static final Map<String, String> categories = new HashMap<String, String>();
		static {
			categories.put(VELO, VELO);
			categories.put(ACCESSOIRE, ACCESSOIRE);
			categories.put(VETEMENT, VETEMENT);
			categories.put(PIECE_DETACHEE, PIECE_DETACHEE);
		}
		public static final Map<String, String> tailleCadres = new HashMap<String, String>();
		static {
			tailleCadres.put(XXS, XXS);
			tailleCadres.put(XS, XS);
			tailleCadres.put(S, S);
			tailleCadres.put(M, M);
			tailleCadres.put(L, L);
			tailleCadres.put(XL, XL);
		}
		public static final Map<String, String> tailleCadresTous = new HashMap<String, String>();
		static {
			tailleCadresTous.put(tousValue, tousLabel);
			tailleCadresTous.putAll(tailleCadres);
		}
		public static final Map<String, String> userSizes = new HashMap<String, String>();
		static {
			userSizes.put(US150_160, US150_160);
			userSizes.put(US160_170, US160_170);
			userSizes.put(US170_180, US170_180);
			userSizes.put(US180_190, US180_190);
			userSizes.put(US190, US190);
		}
		public static final Map<String, String> userSizesTous = new HashMap<String, String>();
		static {
			userSizesTous.put(tousValue, tousLabel);
			userSizesTous.putAll(userSizes);
		}
		public static final Map<String, String> marques = CommonUtils.collectionToMap(Marque.values());
		public static final Map<String, String> marquesTous = new HashMap<String, String>();
		static {
			marquesTous.put(tousValue, tousLabel);
			marquesTous.putAll(marques);
		}
		public static final Map<String, String> disponibilites = new HashMap<String, String>();
		static {
			disponibilites.put(STOCK, STOCK);
			disponibilites.put(RAPID, RAPID);
			disponibilites.put(COMAN, COMAN);
			disponibilites.put(INDIS, INDIS);
		}
		public static final Map<String, String> disponibilitesTous = new HashMap<String, String>();
		static {
			disponibilitesTous.put(tousValue, tousLabel);
			disponibilitesTous.putAll(disponibilites);
		}
		public static final Map<String, String> typeUsages = new HashMap<String, String>();
		static {
			typeUsages.put(HOM, HOM);
			typeUsages.put(FEM, FEM);
			typeUsages.put(MIX, MIX);
		}
		public static final Map<String, String> typeUsagesTous = new HashMap<String, String>();
		static {
			typeUsagesTous.put(tousValue, tousLabel);
			typeUsagesTous.putAll(typeUsages);
		}
		public static final Map<String, String> velos = CommonUtils.collectionToMap(Velos.values());
		public static final Map<String, String> piecesDetachees = CommonUtils.collectionToMap(PiecesDetachees.values());
		public static final Map<String, String> accessoires = CommonUtils.collectionToMap(Accessoires.values());
		public static final Map<String, String> lumieres = CommonUtils.collectionToMap(Lumiere.values());
		public static final Map<String, String> pneus = CommonUtils.collectionToMap(Pneu.values());
		public static final Map<String, String> selles = CommonUtils.collectionToMap(Selle.values());
		public static final Map<String, String> freins = CommonUtils.collectionToMap(Frein.values());
		public static final Map<String, String> directions = CommonUtils.collectionToMap(Direction.values());
		public static final Map<String, String> transmissions = CommonUtils.collectionToMap(Transmission.values());
		public static final Map<String, String> sonnettes = CommonUtils.collectionToMap(Sonnette.values());
		public static final Map<String, String> casques = CommonUtils.collectionToMap(Casque.values());
		public static final Map<String, String> cadenas = CommonUtils.collectionToMap(Cadenas.values());
		public static final Map<String, String> saccoches = CommonUtils.collectionToMap(Saccoche.values());
		public static final Map<String, String> ordinateur = CommonUtils.collectionToMap(Ordinateur.values());
		public static final Map<String, String> pompes = CommonUtils.collectionToMap(Pompe.values());
		public static final Map<String, String> panier = CommonUtils.collectionToMap(Panier.values());
		public static final Map<String, String> couleurs = CommonUtils.collectionToMap(Couleur.values());

	}

}
