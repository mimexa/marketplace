package paris.velocafe.marketplace.views;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.deltaspike.core.api.scope.ViewAccessScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import paris.velocafe.marketplace.entity.Image;
import paris.velocafe.marketplace.service.ImageService;
import paris.velocafe.marketplace.utils.CommonUtils;
import paris.velocafe.marketplace.utils.Graphics;
import paris.velocafe.marketplace.utils.XhtmlPages;

@Named
@ViewAccessScoped
@SuppressWarnings("serial")
public class ImageController implements Serializable {

	@Inject
	private ImageService imageService;

	private List<Image> imgs;
	private Long idProduit;

	public void upload(FileUploadEvent event) {
		Image image = new Image();
		UploadedFile file = event.getFile();
		image.setIdImage(imageService.nextAvaibleId() + Math.round((Math.random() * 1000)));
		image.setData(file.getContents());
		image.setFileName(file.getFileName());
		image.setOrdre(1);
		image.setSize(file.getSize());
		image.setType(file.getContentType());
		imgs.add(image);
		CommonUtils.toUrl(XhtmlPages.PRODUIT_XHTML);
	}

	public Long getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(long idProduit) {
		this.idProduit = (CommonUtils.isNullOrEmpty(idProduit) || idProduit <= 0) ? null : idProduit;
		setImages();
	}

	public void setImages() {
		if (CommonUtils.isNullOrEmpty(imgs)) {
			imgs = (idProduit != null && idProduit > 0) ? imageService.findByIdProduit(idProduit) : new LinkedList<Image>();
		}
		setSessionAttribute("images", imgs);
	}

	public List<Image> getImgs() {
		return imgs;
	}

	public int getFicheWidth() {
		// faire un image util qui redimensionne les images
		return Graphics.ficheWidth;
	}

	public void enregistrer(final Long idProduit) {
		List<Image> images = imageService.findByIdProduit(idProduit);// Suppression des images non utilisées
		if (CommonUtils.isNotNullOrEmpty(images)) {
			for (Image image : images) {
				boolean deleteImage = true;
				for (Image img : imgs) {
					if (image.getIdImage().equals(img.getIdImage())) {
						deleteImage = false;
						break;
					}
				}
				if (deleteImage) {
					imageService.delete(image.getIdImage());
				}
			}
		}
		if (CommonUtils.isNotNullOrEmpty(imgs)) { // Ajout des nouvelles images
			for (Image image : imgs) {
				if (CommonUtils.isNullOrEmpty(imageService.findById(image.getIdImage()))) {
					image.setIdProduit(idProduit);
					imageService.insert(image);
				}
			}
		}
	}

	private static void setSessionAttribute(final String name, final Object value) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(name, value);
	}

	public String erase(Long idImage) {
		if (CommonUtils.isNotNullOrEmpty(imgs)) {
			Image image = null;
			for (Image img : imgs) {
				if (img.getIdImage().equals(idImage)) {
					image = img;
					break;
				}
			}
			if (image != null) {
				imgs.remove(image);
			}
		}
		return null;
	}
}
