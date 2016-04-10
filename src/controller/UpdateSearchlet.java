package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Search;

/**
 * Servlet implementation class UpdateSearchlet
 */
@WebServlet("/UpdateSearchlet")
public class UpdateSearchlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateSearchlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("before pageNum");
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		request.getSession().setAttribute("hasResults", true);
		//System.out.println("after pageNum: "+pageNum);
		Search sr = (Search) request.getSession().getAttribute("searchResults");
		//System.out.println("after search getSession assignment "+sr.getStart());
		sr.setStart(pageNum);
		//System.out.println("after set start to pageNum");
		request.getSession().setAttribute("searchResults", sr);
		//System.out.println("after setAttribute");
		System.out.println("NumOfPagesNeeded "+sr.getNumOfPagesNeeded());
		System.out.println("Current Index "+sr.getStart());
		System.out.println("Show next button? "+sr.isShowNextIcon());
		request.getRequestDispatcher("OttoSearchResults.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
