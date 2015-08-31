package paris.velocafe.marketplace.views;

import java.io.IOException;
import java.io.OutputStream;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import paris.velocafe.marketplace.entity.Image;
import paris.velocafe.marketplace.service.ImageService;

@SuppressWarnings("serial")
@WebServlet("/mozaique")
public class MozaiqueServlet extends HttpServlet {

	@Inject
	private ImageService imageService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		try {
			Long idImage = Long.parseLong(req.getParameter("idImage"));
			Image image = imageService.findById(idImage);
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
			e.printStackTrace();
			super.doGet(req, response);
		}
	}

}
