package paris.velocafe.marketplace.security;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class Security implements Serializable {

	private String username;
	private Set<Role> roles;
	private String name;
	private String surname;
	private String prefix;
	private Date birthdate;
	private String fixe;
	private String mobile;
	private String bureau;
	private String adresse1;
	private String adresse2;
	private String adresse3;
	private String complement;
	private String codePostal;
	private String ville;
	private String pays;

	public Security() {
		roles = new HashSet<Role>();
	}

	public void setSecurity(final Security security) {
		if (security != null) {
			username = security.getUsername();
			roles = security.getRoles();
			name = security.getName();
			surname = security.getSurname();
			prefix = security.getPrefix();
			birthdate = security.getBirthdate();
			fixe = security.getFixe();
			mobile = security.getMobile();
			bureau = security.getBureau();
			adresse1 = security.getAdresse1();
			adresse2 = security.getAdresse2();
			adresse3 = security.getAdresse3();
			complement = security.getComplement();
			codePostal = security.getCodePostal();
			ville = security.getVille();
			pays = security.getPays();
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getFixe() {
		return fixe;
	}

	public void setFixe(String fixe) {
		this.fixe = fixe;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getBureau() {
		return bureau;
	}

	public void setBureau(String bureau) {
		this.bureau = bureau;
	}

	public String getAdresse1() {
		return adresse1;
	}

	public void setAdresse1(String adresse1) {
		this.adresse1 = adresse1;
	}

	public String getAdresse2() {
		return adresse2;
	}

	public void setAdresse2(String adresse2) {
		this.adresse2 = adresse2;
	}

	public String getAdresse3() {
		return adresse3;
	}

	public void setAdresse3(String adresse3) {
		this.adresse3 = adresse3;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public interface Props {
		String username = "username";
		String roles = "roles";
		String name = "name";
		String surname = "surname";
		String prefix = "prefix";
		String birthdate = "birthdate";
		String fixe = "fixe";
		String mobile = "mobile";
		String bureau = "bureau";
		String adresse1 = "adresse1";
		String adresse2 = "adresse2";
		String adresse3 = "adresse3";
		String complement = "complement";
		String codePostal = "codePostal";
		String ville = "ville";
		String pays = "pays";
	}

}
