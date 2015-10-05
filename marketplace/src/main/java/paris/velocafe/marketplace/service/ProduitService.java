package paris.velocafe.marketplace.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import paris.velocafe.marketplace.dao.ProduitDaoImpl;
import paris.velocafe.marketplace.domain.MiniProduit;
import paris.velocafe.marketplace.domain.Produit;
import paris.velocafe.marketplace.domain.SecurityController;
import paris.velocafe.marketplace.entity.Image;
import paris.velocafe.marketplace.forms.FiltreForm;
import paris.velocafe.marketplace.utils.DataUtils;
import paris.velocafe.marketplace.utils.ObjectConverter;

@Stateless
public class ProduitService {

	@Inject
	private ProduitDaoImpl produitDao;
	@Inject
	private ImageService imageService;
	@Inject
	private SecurityController securityController;

	public MiniProduit findMiniProduitById(final Long id) {
		return ObjectConverter.toMiniProduit(produitDao.find(id));
	}

	public List<MiniProduit> findAllMiniProduit() {
		List<MiniProduit> miniProduits = ObjectConverter.toMiniProduits(produitDao.findAll());
		return miniProduits;
	}

	public List<MiniProduit> findMiniProduit(final FiltreForm filtreForm) {
		List<MiniProduit> miniProduits = ObjectConverter.toMiniProduits(produitDao.findList(DataUtils.filtreToSqlparams(filtreForm, securityController)));
		if (miniProduits != null) {
			for (MiniProduit miniProduit : miniProduits) {
				Image image = imageService.findMainImage(miniProduit.getIdProduit());
				miniProduit.setImageId(image != null ? image.getIdImage() : 0);
			}
		}
		return miniProduits;
	}

	public Produit findProduitById(final Long id) {
		return ObjectConverter.toProduit(produitDao.find(id));
	}

	public int update(final Produit produit) {
		return produitDao.update(ObjectConverter.toProduitDb(produit));
	}

	public Long getNewAvaibleId() {
		return produitDao.getNewAvaibleId();
	}

	public void insert(final Produit produit) {
		produit.setIdProduit(getNewAvaibleId());
		produitDao.insert(ObjectConverter.toProduitDb(produit));
	}
}
