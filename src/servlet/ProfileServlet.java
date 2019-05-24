package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.*;
import bean.*;

/**
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
        
        if(!authentication.isLoggedIn()){
            response.senRedirect(request.getContextPath() + "/login");
            //Must change redirect page to proper login page name
            return;
        }

        request.setAttribute("user", authentication.getUser());
        //Must change "user" to appropriate attribute name

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/profile.jsp");
        //Must change profile view page name
        dispatcher.forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        //doGet(request, response);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthdate = dateFormat.parse((String)request.getParameter("birthdate"));
        
        String strSex = (String)request.getParameter("sex");
        Sex sex;
        if(strSex.equals("male")){
            sex = Sex.male;
        }
        else if(strSex.equals("female")){
            sex = Sex.female;
        }
        else sex = Sex.other;

        String email = (String)request.getParameter("email");
        String telephone = (String)request.getParameter("telephone");

        User user = authentication.getUser();

        user.setBirthdate(birthdate);
        user.setSex(sex);
        user.setEmail(email);
        user.setTelephone(telephone);

        User.set(user);

        response.sendRedirect(request.getContextPath() + "/profile.jsp");
        //Must change profile view page name
	}

}
