package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Customer;
import model.Search;

/**
 * Servlet implementation class SeeProductServlet
 */
@WebServlet("/SeeProductServlet")
public class SeeProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SeeProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productNameToView = request.getParameter("productToView");
		String cameFrom = request.getParameter("cameFrom");
		Search search = (Search)request.getSession().getAttribute("searchResults");
		//Customer customer = (Customer)request.getSession().getAttribute("theCustomer");
		FindChosenProductController fcpc;
		
		if(cameFrom!=null){
			fcpc = new FindChosenProductController(productNameToView, search.getSearchArray());
			request.getSession().setAttribute("shownProduct",fcpc.getChosenProduct());
		}else{
			fcpc = new FindChosenProductController(productNameToView);
			request.getSession().setAttribute("shownProduct",fcpc.getProductToViewFromDataBase());
		}
		//request.getSession().setAttribute("theCustomer", customer);
		
		request.getRequestDispatcher("SeeProduct.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
