package paris.velocafe.marketplace.utils;

public interface Constantes {

	interface Etat {
		String REVISE = "R";
		String A_REVISER = "A";
		String POUR_PIECES = "P";
		String OCCASION = "O";
		String NEUF = "N";
	}

	interface Categorie {
		String VELO = "VELO";
		String ACCESSOIRE = "ACCESSOIRE";
		String VETEMENT = "VETEMENT";
		String PIECE_DETACHEE = "PIECE_DETACHEE";
	}

	interface SousCategorie {
		String CASQUE = "CASQUE";
		String CADENAS = "CADENAS";
		String VTT = "VTT";
		String ELECTRIQUE = "ELECTRIQUE";
		String RETRO = "RETRO";
		String URBAN = "URBAN";
		String VOYAGE = "VOYAGE";
		String VILLE = "VILLE";
		String PLIABLE = "PLIABLE";
		String VTC = "VTC";
		String SHORT = "SHORT";
		String TSHIRT = "TSHIRT";
		String CHAUSSURES = "CHAUSSURES";
		String GANTS = "GANTS";
		String SONNETTE = "SONNETTE";
	}

	interface Couleur {
		String BLEU = "BLEU";
		String BLANC = "BLANC";
		String ROUGE = "ROUGE";
		String VERT = "VERT";
		String JAUNE = "JAUNE";
	}

	interface TailleCadre {
		String XXS = "XXS";
		String XS = "XS";
		String S = "S";
		String M = "M";
		String L = "L";
		String XL = "XL";
	}

	interface UserSize {
		String US150_160 = "US150_160";
		String US160_170 = "US160_170";
		String US170_180 = "US170_180";
		String US180_190 = "US180_190";
		String US190 = "US190";
	}

	interface TypeUsage {
		String HOM = "HOM";
		String FEM = "FEM";
		String MIX = "MIX";
	}

	interface Tables {
		String LABELS = "LABELS";
		String PRODUITS = "PRODUITS";
		String TECHNIQUE = "TECHNIQUE";
		String IMG = "IMG";
		String USERROLES = "USERROLES";
		String USER = "USER";
	}

	interface Disponibilite {
		String STOCK = "STOCK";
		String RAPID = "RAPID";
		String COMAN = "COMAN";
		String INDIS = "INDIS";
	}

	interface Colonnes {
		interface Produits {
			String ID_PRODUIT_PRODUITS = "ID_PRODUIT";
			String MONTANT_HT_PRODUITS = "MONTANT_HT";
			String MONTANT_TVA_PRODUITS = "MONTANT_TVA";
			String DATE_CRE_PRODUITS = "DATE_CRE";
			String MARQUE_PRODUITS = "MARQUE";
			String TITRE_PRODUITS = "TITRE";
			String REF_PRODUIT_PRODUITS = "REF_PRODUIT";
			String CATEGORIE_PRODUIT = "CATEGORIE";
			String SOUS_CATEGORIE_PRODUIT = "SOUS_CATEGORIE";
			String ETAT_PRODUIT = "ETAT";
			String REF_TECHNIQUE_PRODUIT = "REF_TECHNIQUE";
			String DATE_FAB_PRODUIT = "DATE_FAB";
			String TYP_USAGE_PRODUIT = "TYP_USAGE";
			String DIAM_ROUE_PRODUIT = "DIAM_ROUE";
			String TAILL_CADRE_PRODUIT = "TAILL_CADRE";
			String DIM_CADRE_PRODUIT = "DIM_CADRE";
			String DISPO_PRODUIT = "DISPO";
			String USER_SIZE_PRODUIT = "USER_SIZE";
			String STYLE_PRODUIT = "STYLE";
			String OPTION_1_PRODUIT = "OPTION_1";
			String OPTION_2_PRODUIT = "OPTION_2";
			String OPTION_3_PRODUIT = "OPTION_3";
			String SIZE_CAT_PRODUIT = "SIZE_CAT";
			String WEIGHT_CAT_PRODUIT = "WEIGHT_CAT";
			String PACK_CAT_PRODUIT = "PACK_CAT";
			String STOCK_OPTION_PRODUIT = "STOCK_OPTION";
			String COLOR_PRODUIT = "COLOR";
			String SUSP_SEAT_PRODUIT = "SUSP_SEAT";
			String EASY_ENTRY_PRODUIT = "EASY_ENTRY";

		}

		interface Img {
			String ID_IMG_IMG = "ID_IMG";
			String ID_PRODUIT_IMG = "ID_PRODUIT";
			String IMG_IMG = "IMG";
			String MIME_TYPE_IMG = "MIME_TYPE";
			String FILE_NAME_IMG = "FILE_NAME";
			String ORDRE_IMG = "ORDRE";
			String DATE_CRE_IMG = "DATE_CRE";
			String USER_CRE_IMG = "USER_CRE";
			String SIZE_IMG = "SIZE";

		}

		interface User {
			String USERNAME_USER = "USERNAME";
			String PASSWORD_USER = "PASSWORD";
			String NAME_USER = "NAME";
			String SURNAME_USER = "SURNAME";
			String PREFIX_USER = "PREFIX";
			String BIRTHDATE_USER = "BIRTHDATE";
			String FIXE_USER = "FIXE";
			String MOBILE_USER = "MOBILE";
			String BUREAU_USER = "BUREAU";
			String ADRESSE1_USER = "ADRESSE1";
			String ADRESSE2_USER = "ADRESSE2";
			String ADRESSE3_USER = "ADRESSE3";
			String COMPLEMENT_USER = "COMPLEMENT";
			String CP_USER = "CP";
			String VILLE_USER = "VILLE";
			String PAYS_USER = "PAYS";
		}

		interface UserRoles {
			String USERNAME_USERROLES = "USERNAME";
			String USERROLES_USERROLES = "USERROLES";
		}
	}

	public enum Marque {
		Batavus, Gazelle, Giant, Sparta, Koga, Autre;
	}

	interface Lang {
		String FR = "FR";
		String EN = "EN";
		String HD = "NL";
	}

	interface Roles {
		String CLIENT = "CLIENT";
		String ADMIN = "ADMIN";
	}
}
