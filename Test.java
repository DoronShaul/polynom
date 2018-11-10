package myMath;

public class Test {

	public static void main(String[] args) {
		//constructors tests:
		
		Monom m=new Monom();
		Monom m1=new Monom(1,2);
		Monom m2=new Monom(m1);
		Monom s=new Monom("5x^7");
		System.out.println("monom m: "+m);
		System.out.println("monom m1: "+m1);
		System.out.println("monom m2: "+m2);
		System.out.println("monom s: "+s);
		
		//monom add, multiply, subtract, derivative etc. tests:
		
		m2.add(m1);
		System.out.println("m2+m1: "+m2);
		Monom m3=new Monom(3,5);
		m3.multiply(m2);
		System.out.println("m3*m2: "+m3);
		Monom m4=new Monom(15,7);
		m4.substract(m3);
		System.out.println("m4-m3: "+m4);
		System.out.println("m4 derivative: "+m4.derivative());
		System.out.println("f(5) on monom s: "+s.f(5));
		
		//polynom tests:
		
		Monom p=new Monom(2,6);
		Monom p1=new Monom(1,9);
		Monom p2=new Monom(3,4);
		Monom p3=new Monom(7,8);
		Monom p4=new Monom(6,5);
		Monom p5=new Monom(4,12);
		Polynom poly=new Polynom();
		poly.add(p);
		poly.add(p1);
		System.out.println("polynom+p+p1: "+poly);
		poly.multiply(p2);
		System.out.println("poly*p2: "+poly);
		Polynom poly2=new Polynom();
		poly2.add(p3);
		poly2.add(p4);
		poly2.add(p5);
		poly.add(poly2);
		System.out.println("poly+poly2: "+poly);
		poly.multiply(poly2);
		System.out.println("poly*poly2: "+poly);
		System.out.println("poly2 derivative: "+poly2.derivative());
		System.out.println("is poly equals to poly 2? "+poly.equals(poly2));
		Polynom_able poly3=poly2.copy();
		System.out.println("is poly3 equals to poly 2? "+poly3.equals(poly2));
		System.out.println("is poly is the zero polynom? "+poly.isZero());
		
		System.out.println("f(2) on poly2: "+poly2.f(2));
		System.out.println("poly root: "+poly.root(-4, 10, 0.5));
		System.out.println("poly3: "+poly3);
		Monom t=new Monom(1,2);
		Monom t1=new Monom(-2,0);
		Monom t2=new Monom(6,5);
		Polynom po=new Polynom();
		po.add(t);
		po.add(t1);
		System.out.println("po area: "+po.area(-3, 3, 0.5));
		System.out.println("poly3 area: "+poly3.area(-3, 3, 0.5));
		

	}

}
