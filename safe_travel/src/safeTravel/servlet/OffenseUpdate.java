package safeTravel.servlet;

import safeTravel.dao.*;
import safeTravel.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/offenseupdate")
public class OffenseUpdate extends HttpServlet {
	
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

        String offenseId = req.getParameter("offenseid");
        if (offenseId == null || offenseId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid offense ID.");
        } else {
        	try {
        		Offenses offense = offensesDao.getOffenseById(offenseId);
        		if(offense == null) {
        			messages.put("success", "Offense ID does not exist.");
        		}
        		req.setAttribute("offense", offense);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/OffenseUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        String offenseId = req.getParameter("offenseid");
        if (offenseId == null || offenseId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid offense ID.");
        } else {
        	try {
        		Offenses offense = offensesDao.getOffenseById(offenseId);
        		if(offense == null) {
        			messages.put("success", "Offense ID does not exist. No update to perform.");
        		} else {
    	    		Timestamp newReportDateTime = new Timestamp(System.currentTimeMillis());
    	    		offense = offensesDao.updateReportDateTime(offense, newReportDateTime);
    	        	messages.put("success", "Successfully updated " + offenseId);
        		}
        		req.setAttribute("offense", offense);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/OffenseUpdate.jsp").forward(req, resp);
    }
}
