package com.revature.dao;

import java.sql.*;
import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.Brand;
import com.revature.util.ConnectionUtil;

public class BrandPostgres implements BrandDao{
	
	private static Logger log = LogManager.getRootLogger();
	@Override
	public int createBrand(Brand brand) {
		// if -1 is returned, no record was created
		int genId = -1;
		// returns the generated id for that user
		String sql = "insert into brand(name) values(?) returning id;";
		
		try(Connection c = ConnectionUtil.getConnectionFromEnv()){
			PreparedStatement ps = c.prepareStatement(sql);
			
			// setting the ? values in the sql prepared statement
			ps.setString(1, brand.getBrand());
			
			// executing the prepared statement which returns the generated id in the result set
			ResultSet rs = ps.executeQuery();
			
			// if successful retrieves the generated id for the new user
			if(rs.next()) {
				genId = rs.getInt("id");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
		return genId;
	}//end createBrand
	
	@Override
	public List<Brand> getAllBrands() {
		String sql = "select * from brand order by id;";
		List<Brand> brandList = new ArrayList<>();
		
		try(Connection c = ConnectionUtil.getConnectionFromEnv()){
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				Brand newBrand = new Brand();
				newBrand.setId(rs.getInt("id"));
				newBrand.setBrand(rs.getString("name"));
				
				brandList.add(newBrand);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
		
		return brandList;
	}//end getBand
	
	@Override
	public Brand getBandById(int id) {

		String sql = "select * from brand where id = ?;";
		Brand brand = null;
		
		try(Connection c = ConnectionUtil.getConnectionFromEnv()){
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setInt(1, id); // this refers to the 1st ? in the sql String
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				brand = new Brand();
				brand.setId(rs.getInt("id"));
				brand.setBrand(rs.getString("name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
		return brand;
		
	}//end getBrandById
	
	@Override
	public boolean updateBrand(Brand brand) {
		String sql = "update brand set name = ? where id = ?;";
		int rowsChanged = -1;
		
		try(Connection c = ConnectionUtil.getConnectionFromEnv()){
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setString(1, brand.getBrand());
			ps.setInt(2, brand.getId());

			rowsChanged = ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
		
		if(rowsChanged < 1) {
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteBrandById(int id) {
		String sql = "delete from brand where id = ?;";
		String sql2 = "delete from flavor where brand_id = ?;";
		
		int rowsChanged = -1;
		try(Connection c = ConnectionUtil.getConnectionFromEnv()){
			//delete all of the flavors of brand id
			PreparedStatement ps2 = c.prepareStatement(sql2);
			ps2.setInt(1, id);
			ps2.executeUpdate();
			
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			rowsChanged = ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}

		if(rowsChanged < 1) {
			return false;
		}
		return true;
		
	}//end deleteBrandById
	
	
}//end BrandPostgres
