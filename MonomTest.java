package myMath;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import junit.framework.Assert;

class MonomTest {

	@Test
	void testMonom() {
		Monom m=new Monom();
		Assert.assertEquals(0, m.get_power());
		Assert.assertEquals(0, m.get_coefficient(), 0);
	}

	@Test
	void testMonomString() {
		Monom m=new Monom ("5x^5");
		Assert.assertEquals(5, m.get_power());
		Assert.assertEquals(5, m.get_coefficient(), 0);
		
		Monom m1=new Monom ("2*x^5");
		Assert.assertEquals(5, m1.get_power());
		Assert.assertEquals(2, m1.get_coefficient(), 0);
		
		Monom m2=new Monom ("x^5");
		Assert.assertEquals(5, m2.get_power());
		Assert.assertEquals(1, m2.get_coefficient(), 0);
		
		Monom m3=new Monom ("2x");
		Assert.assertEquals(1, m3.get_power());
		Assert.assertEquals(2, m3.get_coefficient(), 0);
		
		Monom m5=new Monom ("2*x");
		Assert.assertEquals(1, m5.get_power());
		Assert.assertEquals(2, m5.get_coefficient(), 0);
		
		Monom m6=new Monom ("5");
		Assert.assertEquals(0, m6.get_power());
		Assert.assertEquals(5, m6.get_coefficient(), 0);
	}
	
	

	@Test
	void testMonomDoubleInt() {
		Monom m=new Monom(45.2,7);
		Assert.assertEquals(7, m.get_power());
		Assert.assertEquals(45.2, m.get_coefficient(), 0);
	}
	
	

	@Test
	void testMonomMonom() {
		Monom m=new Monom(1,2);
		Monom m1=new Monom(m);
		Assert.assertEquals(2, m1.get_power());
		Assert.assertEquals(1, m1.get_coefficient(), 0);		
	}

	
	@Test
	void testF() {
		Monom m=new Monom(2,5);
		double f=m.f(2);
		Assert.assertEquals(64, f, 0);
	}

	
	@Test
	void testDerivative() {
		Monom m=new Monom(2,2);
		m.derivative();
		Assert.assertEquals(1, m.get_power());
		Assert.assertEquals(4, m.get_coefficient(), 0);

	}

	
	@Test
	void testMultiplyMonom() {
		Monom m=new Monom(4,1);
		Monom m1=new Monom(2,5);
		m.multiply(m1);
		Assert.assertEquals(6, m.get_power());
		Assert.assertEquals(8, m.get_coefficient(), 0);	

	}

	
	@Test
	void testMultiplyMonomMonom() {
		Monom m=new Monom(4,1);
		Monom m1=new Monom(2,5);
		Monom m2=new Monom();
		m2=m2.multiply(m, m1);
		Assert.assertEquals(6, m2.get_power());
		Assert.assertEquals(8, m2.get_coefficient(), 0);
	}

	
	@Test
	void testAdd() {
		Monom m=new Monom(3,5);
		Monom m1=new Monom(7,5);
		m.add(m1);
		Assert.assertEquals(5, m.get_power());
		Assert.assertEquals(10, m.get_coefficient(), 0);
	}

	
	@Test
	void testSubstract() {
		Monom m=new Monom(3,5);
		Monom m1=new Monom(7,5);
		m.substract(m1);
		Assert.assertEquals(5, m.get_power());
		Assert.assertEquals(-4, m.get_coefficient(), 0);	
	}
	
}
