package com.revature.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.revature.models.Brand;
import com.revature.models.Flavor;
import com.revature.util.ConnectionUtil;

public class FlavorPostgres {
	
	public List<Flavor> getAllFlavors() {
		String sql = "select * from flavors;";
		List<Flavor> flavorList = new ArrayList<>();

		try (Connection c = ConnectionUtil.getConnectionFromEnv()) {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				Flavor newFlavor = new Flavor();
				newFlavor.setId(rs.getInt("id"));
				newFlavor.setFlavor(rs.getString("name"));
				newFlavor.setOunces(rs.getFloat("ounces"));
				newFlavor.setPrice(rs.getFloat("price"));
				/*-
				 *  to handle incompatible types of ref in database and Java Obj
				 *  	- just set a "dummy object" with just the id set for
				*/
				
				Brand brand = new Brand();
				brand.setId(rs.getInt("branch_id"));
				newFlavor.setBrand(brand);
				
				flavorList.add(newFlavor);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flavorList;
	}

}//end FlavorPostgres
