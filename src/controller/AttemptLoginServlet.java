package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Customer;


/**
 * Servlet implementation class AttemptLoginServlet
 */
@WebServlet("/AttemptLoginServlet")
public class AttemptLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AttemptLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In log in servlet");
		String email = request.getParameter("Email");
		String password = request.getParameter("Password");
		if(email.equals("admin") && password.equals("admin")){
			response.sendRedirect("AdminAdd.jsp");
		}else{
			ValidateLogin vl = new ValidateLogin();
			if(vl.validateUserLogin(email, password)){
				PopulateCartController pcc = new PopulateCartController();
				Customer customer = vl.getCustomerLoggedIn();
				customer.fillCustomerCart(pcc.getFilledCart(customer.getEmail()));
				request.getSession().setAttribute("fullName", vl.getCustomerFullName());
				request.getSession().setAttribute("theCustomer", customer);
				
				request.getRequestDispatcher("OttoHome.jsp").forward(request, response);
			}
			else{	//if the user tries to log in, but fails for some reason, they go to register
				response.sendRedirect("OttoRegister.jsp");
			}
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
