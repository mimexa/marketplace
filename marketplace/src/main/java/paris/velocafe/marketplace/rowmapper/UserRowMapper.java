package paris.velocafe.marketplace.rowmapper;

import static paris.velocafe.marketplace.security.Security.Props.*;
import static paris.velocafe.marketplace.utils.Constantes.Colonnes.User.*;
import static paris.velocafe.marketplace.utils.Constantes.Tables.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import paris.velocafe.marketplace.security.Security;

public class UserRowMapper extends AbstractRowMapper<Security> {

	@Override
	public Security mapRow(ResultSet rs, int arg1) throws SQLException {
		Security security = new Security();
		rs.getString(USERNAME_USER);
		rs.getString(NAME_USER);
		rs.getString(SURNAME_USER);
		rs.getString(PREFIX_USER);
		rs.getString(BIRTHDATE_USER);
		rs.getString(FIXE_USER);
		rs.getString(MOBILE_USER);
		rs.getString(BUREAU_USER);
		rs.getString(ADRESSE1_USER);
		rs.getString(ADRESSE2_USER);
		rs.getString(ADRESSE3_USER);
		rs.getString(COMPLEMENT_USER);
		rs.getString(CP_USER);
		rs.getString(VILLE_USER);
		rs.getString(PAYS_USER);
		return security;
	}

	@Override
	public String getTableName() {
		return USER;
	}

	@Override
	public String getColumn(String propertie) {
		return Consts.mapPropertiesValues.get(propertie);
	}

	@Override
	public String getIdPropertyName() {
		return username;
	}

	private static class Consts {
		public static final Map<String, String> mapPropertiesValues = new HashMap<String, String>();
		static {
			mapPropertiesValues.put(username, USERNAME_USER);
			mapPropertiesValues.put(name, NAME_USER);
			mapPropertiesValues.put(surname, SURNAME_USER);
			mapPropertiesValues.put(prefix, PREFIX_USER);
			mapPropertiesValues.put(birthdate, BIRTHDATE_USER);
			mapPropertiesValues.put(fixe, FIXE_USER);
			mapPropertiesValues.put(mobile, MOBILE_USER);
			mapPropertiesValues.put(bureau, BUREAU_USER);
			mapPropertiesValues.put(adresse1, ADRESSE1_USER);
			mapPropertiesValues.put(adresse2, ADRESSE2_USER);
			mapPropertiesValues.put(adresse3, ADRESSE3_USER);
			mapPropertiesValues.put(complement, COMPLEMENT_USER);
			mapPropertiesValues.put(codePostal, CP_USER);
			mapPropertiesValues.put(ville, VILLE_USER);
			mapPropertiesValues.put(pays, PAYS_USER);
		}
	}

}
