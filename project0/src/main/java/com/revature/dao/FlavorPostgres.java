package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
				newFlavor.setOunces(rs.getInt("ounces"));
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
		
	}//getAllFlavors
	
	public Flavor getFlavorById(int id) {
		
		String sql = "select * from flavor where id = ?;";
		
		Flavor flavor = new Flavor();
		
		try (Connection c = ConnectionUtil.getConnectionFromEnv()) {
			PreparedStatement ps = c.prepareStatement(sql);

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				
				flavor = new Flavor();
				flavor.setId(rs.getInt("id"));
				flavor.setFlavor(rs.getString("name"));
				flavor.setOunces(rs.getInt("ounces"));
				flavor.setPrice(rs.getFloat("price"));
				/*-
				 *  to handle incompatible types of ref in database and Java Obj
				 *  	- just set a "dummy object" with just the id set for
				*/
				
				Brand brand = new Brand();
				brand.setId(rs.getInt("branch_id"));
				flavor.setBrand(brand);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flavor;
		
	}//end getFlavorById
	
	public List<Flavor> getFlavorByBrandId(int brandId) {
		String sql = "select * from flavor where brand_id = ?;";
		List<Flavor> flavorList = new ArrayList<>();

		try (Connection c = ConnectionUtil.getConnectionFromEnv()) {
			PreparedStatement ps = c.prepareStatement(sql);

			ps.setInt(1, brandId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				
				Flavor newFlavor = new Flavor();
				newFlavor.setId(rs.getInt("id"));
				newFlavor.setFlavor(rs.getString("name"));
				newFlavor.setOunces(rs.getInt("ounces"));
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
		
	}//getFlavorByBrandId
	
	public int createFlavor(Flavor flavor){
		
		String sql = "insert into flavor(name, ounces, price, brand_id) values (?,?,?,?)returning id;";
		int generatedId = -1;

		try (Connection c = ConnectionUtil.getConnectionFromEnv()) {
			PreparedStatement ps = c.prepareStatement(sql);

			ps.setString(1, flavor.getFlavor());
			ps.setInt(2, flavor.getOunces());
			ps.setFloat(3, flavor.getPrice());
			ps.setInt(4, flavor.getBrand().getId());

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				generatedId = rs.getInt("id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return generatedId;
		
	}//end createFlavor
		
	public boolean updateFlavorPrice(Flavor flavor) {
		String sql = "update flavor set price = ? where id = ?;";
		int rowsChanged = -1;

		try (Connection c = ConnectionUtil.getConnectionFromEnv()) {
			PreparedStatement ps = c.prepareStatement(sql);

			ps.setFloat(1, flavor.getPrice());
			ps.setInt(2, flavor.getId());

			rowsChanged = ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (rowsChanged < 1) {
			return false;
		}

		return true;
		
	}//end updateFlavorPrice


	public boolean deleteFlavorById(int id) {
		String sql = "delete from flavor where id = ?;";
		int rowsChanged = -1;

		try (Connection c = ConnectionUtil.getConnectionFromEnv()) {
			PreparedStatement ps = c.prepareStatement(sql);

			ps.setInt(1, id);

			rowsChanged = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (rowsChanged < 1) {
			return false;
		}

		return true;
	}
}//end FlavorPostgres
