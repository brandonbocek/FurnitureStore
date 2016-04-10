package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Customer;

/**
 * Servlet implementation class FillCartServlet
 */
@WebServlet("/FillCartServlet")
public class FillCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FillCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Customer customer = (Customer)request.getSession().getAttribute("theCustomer");
		if(customer==null){
			System.out.println("Customer is null");
			customer = (Customer)request.getSession().getAttribute("customer");
		}
		PopulateCartController pop = new PopulateCartController();
		System.out.println("Shouldn't be null or 0: "+pop.getFilledCart(customer.getEmail()).size());
		customer.fillCustomerCart(pop.getFilledCart(customer.getEmail()));
		
		request.getSession().setAttribute("theCustomer", customer);
//		System.out.println(customer.getAnOrder(0).getImageName());
//		System.out.println(customer.getMaxInCart());
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
