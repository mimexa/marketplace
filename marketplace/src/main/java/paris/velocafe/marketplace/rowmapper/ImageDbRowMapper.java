package paris.velocafe.marketplace.rowmapper;

import static paris.velocafe.marketplace.entity.Image.Props.*;
import static paris.velocafe.marketplace.utils.Constantes.Colonnes.Img.*;
import static paris.velocafe.marketplace.utils.Constantes.Tables.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import paris.velocafe.marketplace.entity.ImageDb;

public class ImageDbRowMapper extends AbstractRowMapper<ImageDb> {

	@Override
	public ImageDb mapRow(ResultSet rs, int arg1) throws SQLException {
		ImageDb image = new ImageDb();
		image.setData(rs.getBlob(IMG_IMG));
		image.setFileName(rs.getString(FILE_NAME_IMG));
		image.setIdImage(rs.getLong(ID_IMG_IMG));
		image.setIdProduit(rs.getLong(ID_PRODUIT_IMG));
		image.setOrdre(rs.getInt(ORDRE_IMG));
		image.setSize(rs.getLong(SIZE_IMG));
		image.setType(rs.getString(MIME_TYPE_IMG));
		return image;
	}

	@Override
	public String getTableName() {
		return IMG;
	}

	@Override
	public String getColumn(String propertie) {
		return Consts.mapPropertiesValues.get(propertie);
	}

	@Override
	public String getIdPropertyName() {
		return idImage;
	}

	private static class Consts {
		public static final Map<String, String> mapPropertiesValues = new HashMap<String, String>();
		static {
			mapPropertiesValues.put(idImage, ID_IMG_IMG);
			mapPropertiesValues.put(idProduit, ID_PRODUIT_IMG);
			mapPropertiesValues.put(fileName, FILE_NAME_IMG);
			mapPropertiesValues.put(ordre, ORDRE_IMG);
			mapPropertiesValues.put(data, IMG_IMG);
			mapPropertiesValues.put(size, SIZE_IMG);
			mapPropertiesValues.put(type, MIME_TYPE_IMG);
		}
	}

}
