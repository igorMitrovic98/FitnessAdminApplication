package controll;

import models.beans.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.beans.AdminBean;
import models.daos.UserDAO;
import models.dtos.AdminDTO;
import models.dtos.AttributeDTO;
import models.dtos.CategoryDTO;

import java.io.IOException;

/**
 * Servlet implementation class AdminController
 */

@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String action = request.getParameter("action");
		String address = "WEB-INF/pages/SignIn.jsp";
		String name = request.getParameter("name");
		String attribute = request.getParameter("a");
		String atrName = request.getParameter("atrName");
		HttpSession session = request.getSession();
		session.setAttribute("notify", "");
		AdminBean admin = new AdminBean();
		if("login".equals(action)) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			AdminDTO adminDTO = admin.getByUsernameAndPassword(username, password);
			if(adminDTO != null) {
				adminDTO.setLoggedIn(true);
				session.setAttribute("admin", adminDTO);
				address = "WEB-INF/pages/Menu.jsp";
			}else {
				session.setAttribute("notify", "Username or password not valid.");
			}
		}else if("categories".equals(action) || "home".equals(action) || "users".equals(action)) {
			address = "WEB-INF/pages/Menu.jsp";
		}else if("deleteCategory".equals(action) && name != null && !"true".equals(attribute)){
			CategoryBean categoryBean = new CategoryBean();
			categoryBean.deleteCategory(new CategoryDTO(name));
			address = "WEB-INF/pages/Menu.jsp?action=categories";
		}else if(name != null && "true".equals(attribute)) {
			address = "WEB-INF/pages/Attributes.jsp?name="+name+"";
		}else if("deleteAttribute".equals(action) && atrName != null) {
			AttributeBean attributeBean = new AttributeBean();
			attributeBean.deleteAttribute(new AttributeDTO(atrName,name));
			address="WEB-INF/pages/Attributes.jsp?name="+name+"";
		}else if("deleteUser".equals(action)) {
			String username = request.getParameter("username");
			UserDAO.deleteUser(username);
			address="WEB-INF/pages/Menu.jsp?action=users";
		}else if("deleteSupport".equals(action)) {
			String username = request.getParameter("username");
			UserDAO.deleteSupport(username);
			address="WEB-INF/pages/Menu.jsp?action=users";
		}else if("signout".equals(action)) {
			address="WEB-INF/pages/Sign-out.jsp";
		}else if("stats".equals(action)) {
			address="WEB-INF/pages/Stats.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
