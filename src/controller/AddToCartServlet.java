package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Customer;
import model.Product;
import model.Search;

/**
 * Servlet implementation class AddToCartServlet
 */
@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//when the customer clicks "add to cart"
		String productNameToAdd = request.getParameter("productToAdd");
		String cameFrom = request.getParameter("cameFrom");
		Search search = (Search)request.getSession().getAttribute("searchResults");
		
		Customer customer = (Customer)request.getSession().getAttribute("theCustomer");
		FindChosenProductController fcpc;
		
		//if I'm coming from the home page, my search will be null
		if(search!=null && cameFrom.equals("search")){
			fcpc = new FindChosenProductController(productNameToAdd, search.getSearchArray(), customer);
		}else{
			fcpc = new FindChosenProductController(customer, productNameToAdd);
		}
		customer.incrementCart();
		request.getSession().setAttribute("theCustomer", customer);
		if(cameFrom.equals("home")){
			request.getRequestDispatcher("OttoHome.jsp").forward(request, response);
		}else if(cameFrom.equals("search")){
			request.getRequestDispatcher("OttoSearchResults.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
