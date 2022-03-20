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

public class FlavorPostgres implements FlavorDao{
	
	public List<Flavor> getAllFlavors() {
		//String sql = "select * from flavor;";
		String sql = "select f.id, f.name, f.ounces, f.price, f.brand_id, b.name as brand_name from flavor f join brand b on f.brand_id = b.id;";
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
				brand.setId(rs.getInt("brand_id"));
				brand.setBrand(rs.getString("brand_name"));
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
		
		//String sql = "select * from flavor where id = ?;";
		String sql = "select f.id, f.name, f.ounces, f.price, f.brand_id, b.name as brand_name from flavor f join brand b on f.brand_id = b.id where f.id = ?;";
		
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
				brand.setId(rs.getInt("brand_id"));
				brand.setBrand(rs.getString("brand_name"));
				flavor.setBrand(brand);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flavor;
		
	}//end getFlavorById
	
	public List<Flavor> getFlavorByBrandId(int brandId) {
		//String sql = "select * from flavor where brand_id = ?;";
		String sql = "select f.id, f.name, f.ounces, f.price, f.brand_id, b.name as brand_name from flavor f join brand b on f.brand_id = b.id where b.id = ?;";
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
				brand.setId(rs.getInt("brand_id"));
				brand.setBrand(rs.getString("brand_name"));
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
	}//end deleteFlavorById
	
	public List<Flavor> getFlavorByName(String name){
		
		String sql = "select f.id, f.name, f.ounces, f.price, f.brand_id, b.name as brand_name from flavor f join brand b on f.brand_id = b.id where f.name like ?;";
		
		List<Flavor> flavorList = new ArrayList<>();

		try (Connection c = ConnectionUtil.getConnectionFromEnv()) {
			PreparedStatement ps = c.prepareStatement(sql);

			ps.setString(1, "%" + name + "%");

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
				brand.setId(rs.getInt("brand_id"));
				brand.setBrand(rs.getString("brand_name"));
				newFlavor.setBrand(brand);

				flavorList.add(newFlavor);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flavorList;
		
		
	}//getFlavorByName
	
	public List<Flavor> getFlavorByOunces(int ounces) {
		String sql = "select f.id, f.name, f.ounces, f.price, f.brand_id, b.name as brand_name from flavor f join brand b on f.brand_id = b.id where f.ounces <= ?;";
		
		List<Flavor> flavorList = new ArrayList<>();

		try (Connection c = ConnectionUtil.getConnectionFromEnv()) {
			PreparedStatement ps = c.prepareStatement(sql);

			ps.setInt(1, ounces);

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
				brand.setId(rs.getInt("brand_id"));
				brand.setBrand(rs.getString("brand_name"));
				newFlavor.setBrand(brand);

				flavorList.add(newFlavor);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flavorList;
	}//end getFlavorByOunces
	
	public List<Flavor> getFlavorByPrice(float price){
		String sql = "select f.id, f.name, f.ounces, f.price, f.brand_id, b.name as brand_name from flavor f join brand b on f.brand_id = b.id where f.price <= ?;";
		
		List<Flavor> flavorList = new ArrayList<>();

		try (Connection c = ConnectionUtil.getConnectionFromEnv()) {
			PreparedStatement ps = c.prepareStatement(sql);

			ps.setFloat(1, price);

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
				brand.setId(rs.getInt("brand_id"));
				brand.setBrand(rs.getString("brand_name"));
				newFlavor.setBrand(brand);

				flavorList.add(newFlavor);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flavorList;
	}//end getFlavorByPrice
	
	public List<Flavor> getFlavorByNameAndOunces(String name, int ounces){
		String sql = "select f.id, f.name, f.ounces, f.price, f.brand_id, b.name as brand_name from flavor f join brand b on f.brand_id = b.id where f.name like ? and f.ounces <= ?;";
		
		List<Flavor> flavorList = new ArrayList<>();

		try (Connection c = ConnectionUtil.getConnectionFromEnv()) {
			PreparedStatement ps = c.prepareStatement(sql);

			ps.setString(1, "%" + name + "%");
			ps.setInt(2, ounces);
			
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
				brand.setId(rs.getInt("brand_id"));
				brand.setBrand(rs.getString("brand_name"));
				newFlavor.setBrand(brand);

				flavorList.add(newFlavor);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flavorList;
	}//end getFlavorByNameAndOunces
	
	public List<Flavor> getFlavorByNameAndPrice(String name, float price){
		String sql = "select f.id, f.name, f.ounces, f.price, f.brand_id, b.name as brand_name from flavor f join brand b on f.brand_id = b.id where f.name like ? and f.price <= ?;";
		
		List<Flavor> flavorList = new ArrayList<>();

		try (Connection c = ConnectionUtil.getConnectionFromEnv()) {
			PreparedStatement ps = c.prepareStatement(sql);

			ps.setString(1, "%" + name + "%");
			ps.setFloat(2, price);
			
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
				brand.setId(rs.getInt("brand_id"));
				brand.setBrand(rs.getString("brand_name"));
				newFlavor.setBrand(brand);

				flavorList.add(newFlavor);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flavorList;
	}//end getFlavorByNameAndPrice
	
	public List<Flavor> getFlavorByOuncesAndPrice(int ounces, float price){
		String sql = "select f.id, f.name, f.ounces, f.price, f.brand_id, b.name as brand_name from flavor f join brand b on f.brand_id = b.id where f.ounces <= ? and f.price <= ?;";
		
		List<Flavor> flavorList = new ArrayList<>();

		try (Connection c = ConnectionUtil.getConnectionFromEnv()) {
			PreparedStatement ps = c.prepareStatement(sql);

			ps.setInt(1, ounces);
			ps.setFloat(2, price);
			
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
				brand.setId(rs.getInt("brand_id"));
				brand.setBrand(rs.getString("brand_name"));
				newFlavor.setBrand(brand);

				flavorList.add(newFlavor);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flavorList;
	}//end getFlavorByOuncesAndPrice
	
	public List<Flavor> getFlavorByNameOuncesAndPrice(String name, int ounces, float price){
		String sql = "select f.id, f.name, f.ounces, f.price, f.brand_id, b.name as brand_name from flavor f join brand b on f.brand_id = b.id where f.name like ? and f.ounces <= ? and f.price <= ?;";
		
		List<Flavor> flavorList = new ArrayList<>();

		try (Connection c = ConnectionUtil.getConnectionFromEnv()) {
			PreparedStatement ps = c.prepareStatement(sql);

			ps.setString(1, "%" + name + "%");
			ps.setInt(2, ounces);
			ps.setFloat(3, price);
			
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
				brand.setId(rs.getInt("brand_id"));
				brand.setBrand(rs.getString("brand_name"));
				newFlavor.setBrand(brand);

				flavorList.add(newFlavor);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flavorList;
	}//end getFlavorByNameOuncesAndPrice
	
	public List<Flavor> getFlavorByNameWithBrandId(String name, int id){
		String sql = "select f.id, f.name, f.ounces, f.price, f.brand_id, b.name as brand_name from flavor f join brand b on f.brand_id = b.id where f.name like ? and f.brand_id = ?;";
		
		List<Flavor> flavorList = new ArrayList<>();

		try (Connection c = ConnectionUtil.getConnectionFromEnv()) {
			PreparedStatement ps = c.prepareStatement(sql);

			ps.setString(1, "%" + name + "%");
			ps.setInt(2, id);

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
				brand.setId(rs.getInt("brand_id"));
				brand.setBrand(rs.getString("brand_name"));
				newFlavor.setBrand(brand);

				flavorList.add(newFlavor);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flavorList;
	}//end 
	
	public List<Flavor> getFlavorByOuncesWithBrandId(int ounces, int id){
		String sql = "select f.id, f.name, f.ounces, f.price, f.brand_id, b.name as brand_name from flavor f join brand b on f.brand_id = b.id where f.ounces <= ? and f.brand_id = ?;";
		
		List<Flavor> flavorList = new ArrayList<>();

		try (Connection c = ConnectionUtil.getConnectionFromEnv()) {
			PreparedStatement ps = c.prepareStatement(sql);

			ps.setInt(1, ounces);
			ps.setInt(2, id);

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
				brand.setId(rs.getInt("brand_id"));
				brand.setBrand(rs.getString("brand_name"));
				newFlavor.setBrand(brand);

				flavorList.add(newFlavor);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flavorList;
	}//end
	
	public List<Flavor> getFlavorByPriceWithBrandId(float price, int id){
		String sql = "select f.id, f.name, f.ounces, f.price, f.brand_id, b.name as brand_name from flavor f join brand b on f.brand_id = b.id where f.price <= ? and where f.brand_id = ?;";
		
		List<Flavor> flavorList = new ArrayList<>();

		try (Connection c = ConnectionUtil.getConnectionFromEnv()) {
			PreparedStatement ps = c.prepareStatement(sql);

			ps.setFloat(1, price);
			ps.setInt(2, id);

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
				brand.setId(rs.getInt("brand_id"));
				brand.setBrand(rs.getString("brand_name"));
				newFlavor.setBrand(brand);

				flavorList.add(newFlavor);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flavorList;
	}//end getFlavorByPrice

	public List<Flavor> getFlavorByNameAndOuncesWithBrandId(String name, int ounces, int id){
		String sql = "select f.id, f.name, f.ounces, f.price, f.brand_id, b.name as brand_name from flavor f join brand b on f.brand_id = b.id where f.name like ? and f.ounces <= ? and f.brand_id = ?;";
		
		List<Flavor> flavorList = new ArrayList<>();

		try (Connection c = ConnectionUtil.getConnectionFromEnv()) {
			PreparedStatement ps = c.prepareStatement(sql);

			ps.setString(1, "%" + name + "%");
			ps.setInt(2, ounces);
			ps.setInt(3, id);
			
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
				brand.setId(rs.getInt("brand_id"));
				brand.setBrand(rs.getString("brand_name"));
				newFlavor.setBrand(brand);

				flavorList.add(newFlavor);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flavorList;
	}//end getFlavorByNameAndOunces
	
	public List<Flavor> getFlavorByNameAndPriceWithBrandId(String name, float price, int id){
		String sql = "select f.id, f.name, f.ounces, f.price, f.brand_id, b.name as brand_name from flavor f join brand b on f.brand_id = b.id where f.name like ? and f.price <= ? and f.brand_id = ?;";
		
		List<Flavor> flavorList = new ArrayList<>();

		try (Connection c = ConnectionUtil.getConnectionFromEnv()) {
			PreparedStatement ps = c.prepareStatement(sql);

			ps.setString(1, "%" + name + "%");
			ps.setFloat(2, price);
			ps.setInt(3, id);
			
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
				brand.setId(rs.getInt("brand_id"));
				brand.setBrand(rs.getString("brand_name"));
				newFlavor.setBrand(brand);

				flavorList.add(newFlavor);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flavorList;
	}//end getFlavorByNameAndPrice
	
	public List<Flavor> getFlavorByOuncesAndPriceWithBrandId(int ounces, float price, int id){
		String sql = "select f.id, f.name, f.ounces, f.price, f.brand_id, b.name as brand_name from flavor f join brand b on f.brand_id = b.id where f.ounces <= ? and f.price <= ? and f.brand_id = ?;";
		
		List<Flavor> flavorList = new ArrayList<>();

		try (Connection c = ConnectionUtil.getConnectionFromEnv()) {
			PreparedStatement ps = c.prepareStatement(sql);

			ps.setInt(1, ounces);
			ps.setFloat(2, price);
			ps.setInt(3, id);
			
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
				brand.setId(rs.getInt("brand_id"));
				brand.setBrand(rs.getString("brand_name"));
				newFlavor.setBrand(brand);

				flavorList.add(newFlavor);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flavorList;
	}//end getFlavorByOuncesAndPrice

	public List<Flavor> getFlavorByNameOuncesAndPriceWithBrandId(String name, int ounces, float price, int id){
		String sql = "select f.id, f.name, f.ounces, f.price, f.brand_id, b.name as brand_name from flavor f join brand b on f.brand_id = b.id where f.name like ? and f.ounces <= ? and f.price <= ? and f.brand_id = ?;";
		
		List<Flavor> flavorList = new ArrayList<>();

		try (Connection c = ConnectionUtil.getConnectionFromEnv()) {
			PreparedStatement ps = c.prepareStatement(sql);

			ps.setString(1, "%" + name + "%");
			ps.setInt(2, ounces);
			ps.setFloat(3, price);
			ps.setInt(4, id);
			
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
				brand.setId(rs.getInt("brand_id"));
				brand.setBrand(rs.getString("brand_name"));
				newFlavor.setBrand(brand);

				flavorList.add(newFlavor);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flavorList;
	}//end getFlavorByNameOuncesAndPrice
	
}//end FlavorPostgres
