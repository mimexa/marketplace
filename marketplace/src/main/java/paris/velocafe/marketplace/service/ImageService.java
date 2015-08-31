package paris.velocafe.marketplace.service;

import static paris.velocafe.marketplace.domain.Operator.*;
import static paris.velocafe.marketplace.entity.Image.Props.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;

import paris.velocafe.marketplace.dao.ImageDao;
import paris.velocafe.marketplace.domain.SqlParams;
import paris.velocafe.marketplace.entity.Image;
import paris.velocafe.marketplace.entity.Image.Props;
import paris.velocafe.marketplace.utils.ObjectConverter;

@Stateless
public class ImageService {

	@Inject
	private ImageDao imageDao;

	public Image findById(final Long idImage) {
		return ObjectConverter.toImage(imageDao.find(idImage));
	}

	public List<Image> findByIdProduit(final Long idProduit) {
		return ObjectConverter.toImages(imageDao.findListByIdProduit(idProduit));
	}

	public int insert(final Image image) {
		image.setOrdre(imageDao.nextAvaibleOrdre(image.getIdProduit()));
		image.setIdImage(imageDao.nextAvaibleId());
		return imageDao.insert(ObjectConverter.toImageDb(image));
	}

	public Image findMainImage(final Long idProduit) {
		Set<SqlParams<?>> params = new HashSet<SqlParams<?>>();
		params.add(new SqlParams<Long>(Props.idProduit, EQUALS, idProduit));
		params.add(new SqlParams<Integer>(ordre, EQUALS, 1));
		return ObjectConverter.toImage(imageDao.findSingleResult(params));
	}

	public Long nextAvaibleId() {
		return imageDao.nextAvaibleId();
	}

	public void delete(final Long idImage) {
		if (idImage != null) {
			imageDao.delete(idImage);
		}
	}

}
