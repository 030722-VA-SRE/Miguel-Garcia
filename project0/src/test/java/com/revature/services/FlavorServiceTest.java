package com.revature.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockitoSession;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.revature.dao.FlavorDao;


class FlavorServiceTest {

	private static FlavorDao fMock;
	private static FlavorService fService;
	
	@BeforeAll
	public static void setup(){
		fMock = Mockito.mock(FlavorDao.class);
		fService = new FlavorService(fMock);
	}//end 
	
	

}
