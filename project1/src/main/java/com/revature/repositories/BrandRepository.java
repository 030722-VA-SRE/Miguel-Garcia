package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.Brand;

/*indicates that the decorated class is a repository.
 * a repository is a mechanism for encapsulating storage, retrieval, and search behavior
 * which emulates a collection of objects 
 */
@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer>{
	//JpaRepository<T,ID>
	//T: Domain type that repository manages
	//ID: type of the id of the entity that repository manages
	//For Brand the id type is an Integer 
}//end interface
