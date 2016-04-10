package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Customer;
import model.Product;

/**
 * Servlet implementation class RemoveItemServlet
 */
@WebServlet("/RemoveItemServlet")
public class RemoveItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveItemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idToRemove = request.getParameter("productToRemove");
		Customer customer = (Customer)request.getSession().getAttribute("theCustomer");
		if(customer==null){
			System.out.println("Customer is null");
			customer = (Customer)request.getSession().getAttribute("customer");
		}
		//remove from database
		RemoveProductController rpc = new RemoveProductController();
		rpc.deleteAnItem(idToRemove);
		
		//remove from arraylist
		int id = Integer.parseInt(idToRemove);
		customer.removeAnOrder(id);
		request.getSession().setAttribute("theCustomer", customer);

		request.getRequestDispatcher("ShopCart.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
