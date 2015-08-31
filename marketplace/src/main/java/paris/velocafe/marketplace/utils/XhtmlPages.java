package paris.velocafe.marketplace.utils;

/**
 * Enumération des pages xhtml de l'applications
 * 
 * @author maxime
 *
 */
public enum XhtmlPages {
	PRODUIT_XHTML("/public/produit.xhtml"), MARQUETPLACE_XHTML("/public/marquetplace.xhtml"), INDEX_XHTML("/public/index.xhtml"), PRATIQUE_XHTML("/public/pratique.xhtml"), CONTACT_XHTML(
			"/public/contact.xhtml"), LOCATIONS_XHTML("/public/locations.xhtml"), LIVRE_OR_XHTML("/public/livre_or.xhtml"), REPARATIONS_XHTML("/public/reparations.xhtml"), REALISATION_REPARATIONS_XHTML(
			"/public/realisation_reparations.xhtml"), REALISATION_MESURE_XHTML("/public/realisation_mesure.xhtml"), ASSEMBLER_XHTML("/public/assembler.xhtml"), MESURE_XHTML("/public/mesure.xhtml"), PARTENAIRES_XHTML(
			"/public/partenaires.xhtml");

	private String xhtmlPage;

	private XhtmlPages(String xhtmlPage) {
		this.xhtmlPage = xhtmlPage;
	}

	@Override
	public String toString() {
		return xhtmlPage;
	}

}
