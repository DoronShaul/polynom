package myMath;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import junit.framework.Assert;

class PolynomTest {

	@Test
	void testAddMonom() {
		Monom m=new Monom(2,3);
		Polynom p=new Polynom();
		p.add(m);
		String s=p.toString();
		Assert.assertEquals("polynom: 2.0*x^3 ", s);
		Monom m1=new Monom(4.3,5);
		p.add(m1);
		String z=p.toString();
		Assert.assertEquals("polynom: 4.3*x^5 +2.0*x^3 ", z);
	}

	
	@Test
	void testMultiplyMonom() {
		Monom m=new Monom(2,3);
		Polynom p=new Polynom();
		p.add(m);
		Monom m1=new Monom(4,5);
		p.add(m1);
		Monom m2=new Monom(2,1);
		p.multiply(m2);
		String z=p.toString();
		Assert.assertEquals("polynom: 8.0*x^6 +4.0*x^4 ", z);	
	}

	
	@Test
	void testF() {
		Monom m=new Monom(5,2);
		Polynom p=new Polynom();
		p.add(m);
		Monom m1=new Monom(10,1);
		p.add(m1);
		double f=p.f(3);
		Assert.assertEquals(75.0, f, 0);
	}

	
	@Test
	void testAddPolynom_able() {
		Monom m=new Monom(5,2);
		Monom m1=new Monom(10,1);
		Monom m2=new Monom(3,2);
		Monom m3=new Monom(11,1);
		Polynom p=new Polynom();
		Polynom p1=new Polynom();
		p.add(m);		
		p.add(m1);
		p1.add(m2);
		p1.add(m3);
		p.add(p1);
		String z=p.toString();
		Assert.assertEquals("polynom: 8.0*x^2 +21.0*x ", z);	
	}

	
	@Test
	void testSubstract() {
		Monom m=new Monom(5,2);
		Monom m1=new Monom(10,1);
		Monom m2=new Monom(3,2);
		Monom m3=new Monom(11,1);
		Polynom p=new Polynom();
		Polynom p1=new Polynom();
		p.add(m);		
		p.add(m1);
		p1.add(m2);
		p1.add(m3);
		p.substract(p1);
		String z=p.toString();
		Assert.assertEquals("polynom: 2.0*x^2 -1.0*x ", z);
	}

	
	@Test
	void testMultiplyPolynom_able() {
		Monom m=new Monom(5,2);
		Monom m1=new Monom(10,1);
		Monom m2=new Monom(3,2);
		Monom m3=new Monom(11,1);
		Polynom p=new Polynom();
		Polynom p1=new Polynom();
		p.add(m);	 	
		p.add(m1);
		p1.add(m2);
		p1.add(m3);
		p.multiply(p1);
		String z=p.toString();
		Assert.assertEquals("polynom: 15.0*x^4 +85.0*x^3 +110.0*x^2 ", z);		
	}
	
	
	@Test
	void testEqualsPolynom_able() {
		Monom m=new Monom(5,2);
		Monom m1=new Monom(10,1);
		Monom m2=new Monom(3,2);
		Monom m3=new Monom(11,1);
		Polynom p=new Polynom();
		Polynom p1=new Polynom();
		Polynom p2=new Polynom();
		p.add(m);		
		p.add(m1);
		p1.add(m2);
		p1.add(m3);
		p2.add(m2);
		p2.add(m3);
		boolean b=p.equals(p1);
		boolean b2=p1.equals(p2);
		Assert.assertEquals(false, b);
		Assert.assertEquals(true, b2);
	}

	
	@Test
	void testIsZero() {
		Monom m=new Monom();
		Monom m1=new Monom(2,1);
		Polynom p=new Polynom();
		Polynom p1=new Polynom();
		p.add(m);
		p1.add(m1);
		boolean b=p.isZero();
		boolean b1=p1.isZero();
		Assert.assertEquals(true, b);
		Assert.assertEquals(false, b1);
	}

	
	@Test
	void testRoot() {
		Monom m=new Monom(1,2);
		Monom m1=new Monom(-2,0);
		Polynom p1=new Polynom();
		p1.add(m);		
		p1.add(m1);
		double ans=p1.root(-2, 0, 0.1);
		Assert.assertEquals(-1.4375, ans, 0);
		
		double ans2=p1.root(0.5, 3, 0.1);
		Assert.assertEquals(1.4375, ans2, 0);
	
	}

	@Test
	void testCopy() {
		Monom m=new Monom();
		Monom m1=new Monom(2,1);
		Polynom p=new Polynom();
		p.add(m);
		p.add(m1);
		Polynom_able p1=new Polynom();
		p1=p.copy();
		String z=p1.toString();
		Assert.assertEquals("polynom: 2.0*x ", z);
		
	}

	@Test
	void testDerivative() {
		Monom m=new Monom(4,3);
		Monom m1=new Monom(2,1);
		Polynom p=new Polynom();
		p.add(m);
		p.add(m1);
		p.derivative();
		String z=p.derivative().toString();
		Assert.assertEquals("polynom: 12.0*x^2 +2.0 ", z);
		
	}

	@Test
	void testArea() {
		Monom t=new Monom(1,2);
		Monom t1=new Monom(-2,0);
		Polynom po=new Polynom();
		po.add(t);
		po.add(t1);
		double area=po.area(-3, 3, 0.5);
		Assert.assertEquals(9.7, area, 0.1);
	}
	

}
