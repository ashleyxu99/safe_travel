package safeTravel.servlet;

import safeTravel.dao.*;
import safeTravel.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/offensedelete")
public class OffenseDelete extends HttpServlet {
	
	protected OffensesDao offensesDao;
	
	@Override
	public void init() throws ServletException {
		offensesDao = OffensesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        messages.put("title", "Delete Offense");        
        req.getRequestDispatcher("/OffenseDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        String offenseId = req.getParameter("offenseid");
        if (offenseId == null || offenseId.trim().isEmpty()) {
            messages.put("title", "Invalid Offense ID");
            messages.put("disableSubmit", "true");
        } else {
        	Offenses offense = new Offenses(offenseId);
	        try {
	        	offense = offensesDao.delete(offense);
		        if (offense == null) {
		            messages.put("title", "Successfully deleted " + offenseId);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + offenseId);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/OffenseDelete.jsp").forward(req, resp);
    }
}
