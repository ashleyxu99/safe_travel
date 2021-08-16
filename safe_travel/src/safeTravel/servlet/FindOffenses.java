package safeTravel.servlet;

import safeTravel.dao.*;
import safeTravel.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/findoffenses")
public class FindOffenses extends HttpServlet {

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
        
        List<Offenses> offenses = new ArrayList<>();
        
        String offenseId = req.getParameter("offenseid");
        if (offenseId == null || offenseId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid offense id.");
        } else {
        	try {
	    		Offenses offense = offensesDao.getOffenseById(offenseId);
	    		offenses.add(offense);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + offenseId);
        	messages.put("previousOffenseId", offenseId);
        }
        req.setAttribute("offense", offenses);
        
        req.getRequestDispatcher("/FindOffenses.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Offenses> offenses = new ArrayList<>();
        
        String offenseId = req.getParameter("offenseid");
        if (offenseId == null || offenseId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid offense id.");
        } else {
        	try {
	    		Offenses offense = offensesDao.getOffenseById(offenseId);
	    		offenses.add(offense);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + offenseId);
        }
        req.setAttribute("offenses", offenses);
        
        req.getRequestDispatcher("/FindOffenses.jsp").forward(req, resp);
    }
}
