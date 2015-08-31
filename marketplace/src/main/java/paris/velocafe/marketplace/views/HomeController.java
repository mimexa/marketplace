package paris.velocafe.marketplace.views;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * Controlleur de la page d'accueil
 * 
 * @author maxime
 *
 */
@SuppressWarnings("serial")
@Named
@SessionScoped
public class HomeController implements Serializable {

	public List<String> getImgs() {
		return Consts.imgs;
	}

	private static class Consts {
		public static final List<String> imgs = new LinkedList<String>();
		static {
			imgs.add("/resources/img/home1.jpg");
			imgs.add("/resources/img/home2.jpg");
			imgs.add("/resources/img/home3.jpg");
		}

	}
}
