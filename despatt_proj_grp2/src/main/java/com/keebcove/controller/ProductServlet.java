package com.keebcove.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.keebcove.utility.AbstractFactory;
import com.keebcove.utility.ChocFactory;
import com.keebcove.utility.KbPartFactory;
import com.keebcove.model.ChocParts;
import com.keebcove.model.KbPart;
import com.keebcove.model.Product;
/**
 * Servlet implementation class ProductServlet
 */
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String productParam = request.getParameter("product");
//        KbPart product = KbPartFactory.getName(productParam);
//
//        request.setAttribute("product", product);
//        request.getRequestDispatcher("product.jsp").forward(request, response);
		
		String productParam = request.getParameter("product");

        if (productParam == null || productParam.trim().isEmpty()) {
            response.sendRedirect("index.jsp");
            return;
        }

        AbstractFactory factory;
        Product product;

        switch (productParam.toUpperCase()) {
            case "AKKO FAIRY SILENT":
            case "AKKO TOP 40":
            case "AKKO KEYCAPS":
                factory = new KbPartFactory();
                break;

            case "AMBIENT TWILIGHT CHOC SWITCHES":
            case "CHOC KEYCAPS":
            case "CHOC CORNE BAREBONES":
                factory = new ChocFactory();
                break;

            default:
                response.sendRedirect("index.jsp"); 
                return;
        }

        product = factory.getProduct(productParam);

        request.setAttribute("product", product);
        request.getRequestDispatcher("product.jsp").forward(request, response);
	}
 
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
