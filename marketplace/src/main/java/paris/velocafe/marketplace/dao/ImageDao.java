package paris.velocafe.marketplace.dao;

import static paris.velocafe.marketplace.utils.Constantes.Colonnes.Img.*;
import static paris.velocafe.marketplace.utils.Constantes.Tables.*;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import paris.velocafe.marketplace.domain.SqlParams;
import paris.velocafe.marketplace.entity.ImageDb;
import paris.velocafe.marketplace.rowmapper.AbstractRowMapper;
import paris.velocafe.marketplace.rowmapper.ImageDbRowMapper;

@SuppressWarnings("serial")
@Stateless
public class ImageDao implements Serializable {

	private static final AbstractRowMapper<ImageDb> rowMapper = new ImageDbRowMapper();

	@Resource(mappedName = "jdbc/velocafeDB")
	private DataSource ds;

	public ImageDb find(final Long idImage) {
		ImageDb image = null;
		try {
			image = new JdbcTemplate(ds).queryForObject("SELECT * FROM " + IMG + " WHERE " + ID_IMG_IMG + "=" + idImage, rowMapper);
		} catch (Exception e) {
			return null;
		}
		return image;
	}

	public List<ImageDb> findListByIdProduit(final Long idProduit) {
		return new JdbcTemplate(ds).query("SELECT * FROM " + IMG + " WHERE " + ID_PRODUIT_IMG + "=" + idProduit, rowMapper);
	}

	public int insert(ImageDb image) {
		return DaoUtils.insert(new JdbcTemplate(ds), image, rowMapper);
	}

	public ImageDb findSingleResult(final Set<SqlParams<?>> params) {
		return DaoUtils.queryForSingleResult(new JdbcTemplate(ds), params, rowMapper);
	}

	public Integer nextAvaibleOrdre(final Long idProduit) {
		Integer ordre = new JdbcTemplate(ds).queryForObject("SELECT MAX(" + ORDRE_IMG + ") FROM " + IMG + " WHERE " + ID_PRODUIT_IMG + "=" + idProduit, Integer.class);
		return ordre != null ? ordre + 1 : 1;
	}

	public Long nextAvaibleId() {
		Long idImg = new JdbcTemplate(ds).queryForObject("SELECT MAX(" + ID_IMG_IMG + ") FROM " + IMG, Long.class);
		return idImg != null ? idImg + 1 : 1;
	}

	public void delete(final Long idImage) {
		DaoUtils.delete(new JdbcTemplate(ds), idImage, rowMapper);
	}
}
