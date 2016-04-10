package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AttemptRegisterServlet
 */
@WebServlet("/AttemptRegister")
public class AttemptRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AttemptRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String fn = request.getParameter("firstName");
		String ln = request.getParameter("lastName");
		String pw = request.getParameter("password");
		String rpw = request.getParameter("rePassword");
		String bMon = request.getParameter("birthMonth");
		String bDay = request.getParameter("dayOfMonth");
		String bYear = request.getParameter("birthYear");
		String eAddr = request.getParameter("email");
		String addrBill = request.getParameter("addressBill");
		String addrShip = request.getParameter("addressShip");
		String cardNum = request.getParameter("card1")+request.getParameter("card2")+
				request.getParameter("card3")+request.getParameter("card4");
		
		System.out.println("User's before encryption "+cardNum);
		//cardNum = Bitshifter.encrypt(cardNum);
		System.out.println("User's after encryption "+cardNum);
		ValidateRegister vr = new ValidateRegister(fn,ln,pw,rpw,bMon,bDay,bYear,eAddr,addrBill,addrShip,cardNum);
		if(vr.everythingIsValid()){
			response.sendRedirect("CheckEmail.jsp");
		}
		else{	//if the user tries to log in, but fails for some reason, they go to register
			response.sendRedirect("OttoFailRegister.jsp");
		}
		
		
		//request.getSession().setAttribute("battleshipBean", game);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doGet(request, response);
	}

}
