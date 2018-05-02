package it.therickys93.wikibot;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class UtilsTest {

	@Test
	public void testParseAuthorizedUsersEnvNoValue(){
		assertNull(Utils.parseAuthorizedEnv(null));
		assertNull(Utils.parseAuthorizedEnv(""));
	}
	
	@Test
	public void testParseAuthorizedUsersOneValue(){
		assertEquals(oneUser(12345678), Utils.parseAuthorizedEnv("12345678"));
		assertEquals(oneUser(87654321), Utils.parseAuthorizedEnv("87654321"));
	}
	
	@Test
	public void testParseAuthorizedUsersMultipleValues(){
		assertEquals(multipleUsers(), Utils.parseAuthorizedEnv("12345678,87654321,13578642"));
	}
	
	@Test
	public void testImproveCodeCoverage(){
		Utils util = new Utils();
		assertNotNull(util.toString());
	}
	
	private List<Long> oneUser(long value){
		List<Long> user = new ArrayList<Long>();
		user.add(new Long(value));
		return user;
	}
	
	private List<Long> multipleUsers(){
		List<Long> users = new ArrayList<Long>();
		users.add(new Long(12345678));
		users.add(new Long(87654321));
		users.add(new Long(13578642));
		return users;
	}
	
}
