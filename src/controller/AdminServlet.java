package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class AdminServlet
 */

@WebServlet("/AdminServlet")
@MultipartConfig
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
       // response.setContentType("text/html;charset=UTF-8");
AdminController ac = new AdminController();
		
		if(request.getParameter("submitProduct")!=null){			//if the user tried to submit a product
			String productName = request.getParameter("adminProd");
			String productDesc = request.getParameter("adminDesc");
			String productImage="";
			double productPrice = Double.parseDouble(request.getParameter("adminPrice"));
			Part filePart = request.getPart("photo");
			if (filePart != null) {
				productImage = filePart.getSubmittedFileName();
				// obtains input stream of the upload file
			}
//start paste
			// Create path components to save the file
		    final String path = "/Users/brandonbocek/Documents/workspace/FurnitureStore/WebContent/ottoImages";

		    OutputStream out = null;
		    InputStream filecontent = null;
		    final PrintWriter writer = response.getWriter();

		    try {
		        out = new FileOutputStream(new File(path + File.separator+productImage));
		        filecontent = filePart.getInputStream();

		        int read = 0;
		        final byte[] bytes = new byte[1024];

		        while ((read = filecontent.read(bytes)) != -1) {
		            out.write(bytes, 0, read);
		        }
		       
		    } catch (FileNotFoundException fne) {
		        writer.println("You either did not specify a file to upload or are "
		                + "trying to upload a file to a protected or nonexistent "
		                + "location.");
		        writer.println("<br/> ERROR: " + fne.getMessage());
		    } finally {
		        if (out != null) {
		            out.close();
		        }
		        if (filecontent != null) {
		            filecontent.close();
		        }
		        
		    }
		
			//end paste
			String productKeyWords = request.getParameter("adminKeyWords");
			if(!productName.equals(null) && !productName.equals("")){
				ac.addInventory(productName, productDesc, productPrice, productImage, productKeyWords);
					
			}
		}else if(request.getParameter("submitCard")!=null){			//if the user tried to submit a credit card
			
			String cardNumber = request.getParameter("part1")+request.getParameter("part2")+
					request.getParameter("part3")+request.getParameter("part4");
			if(!cardNumber.equals(null) && !cardNumber.equals("")){
				System.out.println("In the card's 2nd if");
				if(ac.addCreditCard(cardNumber)){
					
				}
			}
		}
		request.getSession().setAttribute("cardAddStatus", ac.getCardAddStatus());
		request.getSession().setAttribute("productAddStatus", ac.getProductAddStatus());
			
		
		
		response.sendRedirect("AdminAdd.jsp");
		
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPostt(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
