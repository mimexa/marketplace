package paris.velocafe.marketplace.forms;

import java.util.Date;

public class ProduitForm {

	private Long idProduit;
	private String titre;
	private String marque;
	private String referenceFabricant;
	private double montantHT;
	private double montantTVA;
	private Long idTechnique;
	private String categorie;
	private String sousCategorie;
	private String etat;
	private Date dateEntree;
	private Date dateFabrication;
	private String referenceTechnique;
	private String disponibilite;
	private String typeUsage; // TYP_USAGE VARCHAR(3)
	private String diametreRoue; // DIAM_ROUE VARCHAR(5)
	private String tailleCadre; // TAILL_CADRE VARCHAR(4)
	private String dimensionCadre; // DIM_CADRE INT
	private String userSize; // ADD COLUMN `USER_SIZE` INT NULL COMMENT '' AFTER `DISPO`,
	private String style; // ADD COLUMN `STYLE` VARCHAR(10) NULL COMMENT '' AFTER `USER_SIZE`,
	private String option1; // ADD COLUMN `OPTION_1` VARCHAR(10) NULL COMMENT '' AFTER `STYLE`,
	private String option2; // ADD COLUMN `OPTION_2` VARCHAR(10) NULL COMMENT '' AFTER `OPTION_1`,
	private String option3; // ADD COLUMN `OPTION_3` VARCHAR(10) NULL COMMENT '' AFTER `OPTION_2`;
	private String sizecategory; // ADD COLUMN `SIZE_CAT` VARCHAR(10) NULL COMMENT '' AFTER `OPTION_3`,
	private String weightCategory; // ADD COLUMN `WEIGHT_CAT` VARCHAR(10) NULL COMMENT '' AFTER `SIZE_CAT`,
	private String packageCategory; // ADD COLUMN `PACK_CAT` VARCHAR(10) NULL COMMENT '' AFTER `WEIGHT_CAT`,
	private String stockOption; // ADD COLUMN `STOCK_OPTION` VARCHAR(10) NULL COMMENT '' AFTER `PACK_CAT`,
	private String color; // ADD COLUMN `COLOR` VARCHAR(10) NULL COMMENT '' AFTER `STOCK_OPTION`,
	private String suspensionSeat; // ADD COLUMN `SUSP_SEAT` VARCHAR(1) NULL COMMENT '' AFTER `COLOR`,
	private String easyEntry; // ADD COLUMN `EASY_ENTRY` VARCHAR(1) NULL COMMENT '' AFTER `SUSP_SEAT`;

	public String getDisponibilite() {
		return disponibilite;
	}

	public void setDisponibilite(String disponibilite) {
		this.disponibilite = disponibilite;
	}

	public String getUserSize() {
		return userSize;
	}

	public void setUserSize(String userSize) {
		this.userSize = userSize;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getOption1() {
		return option1;
	}

	public String getTypeUsage() {
		return typeUsage;
	}

	public void setTypeUsage(String typeUsage) {
		this.typeUsage = typeUsage;
	}

	public String getDiametreRoue() {
		return diametreRoue;
	}

	public void setDiametreRoue(String diametreRoue) {
		this.diametreRoue = diametreRoue;
	}

	public String getTailleCadre() {
		return tailleCadre;
	}

	public void setTailleCadre(String tailleCadre) {
		this.tailleCadre = tailleCadre;
	}

	public String getDimensionCadre() {
		return dimensionCadre;
	}

	public void setDimensionCadre(String dimensionCadre) {
		this.dimensionCadre = dimensionCadre;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public String getSizecategory() {
		return sizecategory;
	}

	public void setSizecategory(String sizecategory) {
		this.sizecategory = sizecategory;
	}

	public String getWeightCategory() {
		return weightCategory;
	}

	public void setWeightCategory(String weightCategory) {
		this.weightCategory = weightCategory;
	}

	public String getPackageCategory() {
		return packageCategory;
	}

	public void setPackageCategory(String packageCategory) {
		this.packageCategory = packageCategory;
	}

	public String getStockOption() {
		return stockOption;
	}

	public void setStockOption(String stockOption) {
		this.stockOption = stockOption;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSuspensionSeat() {
		return suspensionSeat;
	}

	public void setSuspensionSeat(String suspensionSeat) {
		this.suspensionSeat = suspensionSeat;
	}

	public String getEasyEntry() {
		return easyEntry;
	}

	public void setEasyEntry(String easyEntry) {
		this.easyEntry = easyEntry;
	}

	public ProduitForm() {
	}

	public ProduitForm(Long idProduit) {
		this.idProduit = idProduit;
	}

	public Long getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(Long idProduit) {
		this.idProduit = idProduit;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getReferenceFabricant() {
		return referenceFabricant;
	}

	public void setReferenceFabricant(String referenceFabricant) {
		this.referenceFabricant = referenceFabricant;
	}

	public double getMontantHT() {
		return montantHT;
	}

	public void setMontantHT(double montantHT) {
		this.montantHT = montantHT;
	}

	public double getMontantTVA() {
		return montantTVA;
	}

	public void setMontantTVA(double montantTVA) {
		this.montantTVA = montantTVA;
	}

	public Long getIdTechnique() {
		return idTechnique;
	}

	public void setIdTechnique(Long idTechnique) {
		this.idTechnique = idTechnique;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getSousCategorie() {
		return sousCategorie;
	}

	public void setSousCategorie(String sousCategorie) {
		this.sousCategorie = sousCategorie;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public Date getDateEntree() {
		return dateEntree;
	}

	public void setDateEntree(Date dateEntree) {
		this.dateEntree = dateEntree;
	}

	public Date getDateFabrication() {
		return dateFabrication;
	}

	public void setDateFabrication(Date dateFabrication) {
		this.dateFabrication = dateFabrication;
	}

	public String getReferenceTechnique() {
		return referenceTechnique;
	}

	public void setReferenceTechnique(String referenceTechnique) {
		this.referenceTechnique = referenceTechnique;
	}

	public static void reset(final ProduitForm form) {
		form.titre = null;
		form.marque = null;
		form.referenceFabricant = null;
		form.montantHT = 0;
		form.montantTVA = 0;
		form.idTechnique = null;
		form.categorie = null;
		form.sousCategorie = null;
		form.etat = null;
		form.dateEntree = null;
		form.dateFabrication = null;
		form.referenceTechnique = null;
		form.disponibilite = null;
		form.typeUsage = null; // TYP_USAGE VARCHAR(3)
		form.diametreRoue = null; // DIAM_ROUE VARCHAR(5)
		form.tailleCadre = null; // TAILL_CADRE VARCHAR(4)
		form.dimensionCadre = null; // DIM_CADRE INT
		form.userSize = null; // ADD COLUMN `USER_SIZE` INT NULL COMMENT '' AFTER `DISPO`,
		form.style = null; // ADD COLUMN `STYLE` VARCHAR(10) NULL COMMENT '' AFTER `USER_SIZE`,
		form.option1 = null; // ADD COLUMN `OPTION_1` VARCHAR(10) NULL COMMENT '' AFTER `STYLE`,
		form.option2 = null; // ADD COLUMN `OPTION_2` VARCHAR(10) NULL COMMENT '' AFTER `OPTION_1`,
		form.option3 = null; // ADD COLUMN `OPTION_3` VARCHAR(10) NULL COMMENT '' AFTER `OPTION_2`=null;
		form.sizecategory = null; // ADD COLUMN `SIZE_CAT` VARCHAR(10) NULL COMMENT '' AFTER `OPTION_3`,
		form.weightCategory = null; // ADD COLUMN `WEIGHT_CAT` VARCHAR(10) NULL COMMENT '' AFTER `SIZE_CAT`,
		form.packageCategory = null; // ADD COLUMN `PACK_CAT` VARCHAR(10) NULL COMMENT '' AFTER `WEIGHT_CAT`,
		form.stockOption = null; // ADD COLUMN `STOCK_OPTION` VARCHAR(10) NULL COMMENT '' AFTER `PACK_CAT`,
		form.color = null; // ADD COLUMN `COLOR` VARCHAR(10) NULL COMMENT '' AFTER `STOCK_OPTION`,
		form.suspensionSeat = null; // ADD COLUMN `SUSP_SEAT` VARCHAR(1) NULL COMMENT '' AFTER `COLOR`,
		form.easyEntry = null; // ADD COLUMN `EASY_ENTRY` VARCHAR(1) NULL COMMENT '' AFTER `SUSP_SEAT`=null;

	}

}
