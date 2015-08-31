package paris.velocafe.marketplace.views;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import paris.velocafe.marketplace.entity.Image;
import paris.velocafe.marketplace.utils.CommonUtils;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/images")
public class ImageServlet extends HttpServlet {

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		String idImage = req.getParameter("idImage");
		try {
			Image image = null;
			List<Image> images = (List<Image>) req.getSession().getAttribute("images");
			if (CommonUtils.isNotNullOrEmpty(images)) {
				for (Image img : images) {
					if (img.getIdImage().equals(Long.parseLong(idImage))) {
						image = img;
					}
				}
			}
			if (image != null) {
				response.setContentType(image.getType());
				response.setHeader("Content-disposition", "attachment; filename=" + image.getFileName());
				OutputStream out = response.getOutputStream();
				out.write(image.getData());
				out.flush();
			} else {
				super.doGet(req, response);
			}
		} catch (NumberFormatException e) {
			super.doGet(req, response);
		}
	}
}
