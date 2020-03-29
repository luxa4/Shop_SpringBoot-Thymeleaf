package ru.belyaev.shop.util;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class RoutingUtil {

	public static void forwardToPage(String htmlPage, String fragment, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("CURRENT_PAGE", "/pages/page/" + htmlPage);
		req.setAttribute("PAGE", "/pages/fragment/" + fragment);
		req.getRequestDispatcher("/pages/pages-template.html").forward(req, resp);
	}

	public static void redirect(String url, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.sendRedirect(url);
	}
}
