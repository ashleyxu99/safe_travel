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

@WebServlet("/offensecreate")
public class OffenseCreate extends HttpServlet {

	protected OffenseGroupsDao offenseGroupDao;
	protected OffensesDao offensesDao;
	protected OffenseTypesDao offenseTypeDao;
	protected PoliceSectorsDao policesectorsDao;
	protected NeighborhoodsDao neighborhoodsDao;
	protected HundredBlockAddressDao hundredBlockAddressDao;
	protected LocationsDao locationsDao;
	
	@Override
	public void init() throws ServletException {
		offenseGroupDao = OffenseGroupsDao.getInstance();
		offensesDao = OffensesDao.getInstance();
		offenseTypeDao = OffenseTypesDao.getInstance();
		policesectorsDao = PoliceSectorsDao.getInstance();
		neighborhoodsDao = NeighborhoodsDao.getInstance();
		hundredBlockAddressDao = HundredBlockAddressDao.getInstance();
		locationsDao = LocationsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        req.getRequestDispatcher("/OffenseCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        String offenseId = req.getParameter("offenseid");
        if (offenseId == null || offenseId.trim().isEmpty()) {
            messages.put("success", "Invalid Offense Id");
        } else {
        	// Create the Offense.
        	String offenseCode = req.getParameter("offensecode");
        	String offenseString = req.getParameter("offense");
        	String offenseParentGroup = req.getParameter("offenseparentgroup");
        	String crimeAgainstCategory = req.getParameter("crimeagainstcategory");
        	String beat = req.getParameter("beat");
        	String precinct = req.getParameter("precinct");
        	int neighborhoodId = Integer.parseInt(req.getParameter("neighborhoodid"));
        	String MCPP = req.getParameter("mcpp");
        	String hundredBlockAddressString = req.getParameter("hundredblockaddress");
        	String latitude = req.getParameter("latitude");
        	String longitude = req.getParameter("longitude");
	        try {
	        	OffenseGroups offenseGroup = new OffenseGroups(offenseParentGroup, OffenseGroups.CrimeAgainstCategory.valueOf(crimeAgainstCategory));
	        	offenseGroup = offenseGroupDao.create(offenseGroup);
	        	OffenseTypes offenseType = new OffenseTypes(offenseCode, offenseString, offenseParentGroup);
	    		offenseType = offenseTypeDao.create(offenseType);
	    		PoliceSectors policeSector = new PoliceSectors(beat,  PoliceSectors.Precinct.valueOf(precinct));
	    		policeSector = policesectorsDao.create(policeSector);
	    		Neighborhoods neighborhood = new Neighborhoods(neighborhoodId, MCPP, policeSector);
	    		neighborhood = neighborhoodsDao.create(neighborhood);
	    		HundredBlockAddress hundredBlockAddress = new HundredBlockAddress(hundredBlockAddressString);
	    		hundredBlockAddress = hundredBlockAddressDao.create(hundredBlockAddress);
	    		Locations location = new Locations(latitude, longitude);
	    		location = locationsDao.create(location);
	    		Timestamp reportDateTime = new Timestamp(System.currentTimeMillis());
	    		Offenses offense = new Offenses(offenseId, reportDateTime, offenseType, 
	    				neighborhood, hundredBlockAddress, location);
	    		offense = offensesDao.create(offense);
	        	messages.put("success", "Successfully created " + offenseId);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/OffenseCreate.jsp").forward(req, resp);
    }
}
