package myMath;

import java.util.ArrayList;
import java.util.Iterator;
import myMath.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author 308224450,308545151
 *
 */
public class Polynom implements Polynom_able{

	private ArrayList<Monom> poly = new ArrayList<Monom>();


	/**
	 * this function is adding Monom to a Polynom.
	 * @param m1: the monom that we add.
	 */
	@Override
	public void add(Monom m1) {		
		if(m1==null) {
			return;
		}
		if (m1.get_coefficient()==0)       //if m1 is the zero monom.
			return;
		if(poly.size()==0) {
			poly.add(m1);
		}
		else {           //if there is already monoms in the polynom.
			Iterator<Monom> it = poly.iterator();
			Monom ans=new Monom(0,0);
			boolean monomInserted=false;            //checking if the monom inserted.
			while(it.hasNext()) {
				ans=it.next();
				if(ans.get_power()==m1.get_power()) {
					ans.add(m1);
					if(ans.get_coefficient()==0) {       //if after the adding operation the coefficient is 0, remove the monom.
						it.remove();
					}
					monomInserted=true;
					break;
				}
				else {       //if the power of the two monoms aren't the same.
					if(ans.get_power()<m1.get_power()) {
						int index=poly.indexOf(ans);
						poly.add(index, m1);
						monomInserted=true;
						break;
					}
				}
			}
			if(monomInserted==false) {      //if the monom hasn't inserted yet, add it to the end of the polynom.
				poly.add(m1);
			}
		}
	}
	/**
	 * this function is multiplying a polynom by monom.
	 * @param m1: the monom that we multiply by.
	 */
	public void multiply(Monom m1) {
		if(m1==null) {
			return;
		}
		if(poly.size()==0) {
			return;
		}
		else {
			Iterator<Monom> it = poly.iterator();
			while(it.hasNext()) {
				Monom currentPoly=it.next();
				currentPoly.multiply(m1);               //multiply the whole polynom with monom m1.
			}			
		}		
	}
	/**
	 * this function represent y=f(x). 
	 * @param x: the value of x.
	 * @return sum: represents the sum of the polynom at the value of x;
	 */
	@Override
	public double f(double x) {
		Iterator<Monom> it = poly.iterator();
		Monom current;
		double sum=0;
		while(it.hasNext()) {
			current=it.next();
			sum+=(Math.pow(x, current.get_power())*current.get_coefficient());
		}

		return sum;
	}
	/**
	 * adds a polynom_able to this polynom
	 * @param p1: the polynom that we add
	 */
	@Override
	public void add(Polynom_able p1) {
		Iterator<Monom> itP1=p1.iteretor();
		while(itP1.hasNext()) {
			Monom currentP1=itP1.next();
			add(currentP1);			
		}
	}
	/**
	 * this function subtracting Polynom_able from the original polynom
	 * @param p1: the polynom that we subtract
	 */
	@Override
	public void substract(Polynom_able p1) {
		Iterator<Monom> itP1=p1.iteretor();
		while(itP1.hasNext()) {
			Monom currentP1=itP1.next();
			Monom negative=new Monom(-1,0);
			currentP1.multiply(negative);
			add(currentP1);
		}		

	}
	/**
	 * this function is multiplying a polynom_able by the original polynom.
	 * @param p1: the polynom_able that we multiply by.
	 */
	@Override
	public void multiply(Polynom_able p1) {
		Iterator<Monom> itPoly=poly.iterator();
		Iterator<Monom> itP1=p1.iteretor();
		ArrayList<Monom> mult=new ArrayList<Monom>();      //temporary polynom for the results of the multiplication.
		if(poly.size()==0) {
			return;
		}
		while(itP1.hasNext()) {
			Monom currentP1=itP1.next();
			itPoly=poly.iterator();
			while(itPoly.hasNext()) {
				Monom currentPoly=itPoly.next();
				Monom copy=new Monom(currentPoly);
				copy.multiply(currentP1);
				mult.add(copy);
			}
		}
		poly.clear();           //clear the polynom, so it can add the results of the multiplication.
		Iterator<Monom> itMult=mult.iterator() ;
		while(itMult.hasNext()) {
			Monom currentMult=itMult.next();
			add(currentMult);
		}
	}
	/**
	 * this function checks if the original polynom equals to the polynom_able
	 * @param p1: the polynom_able that we compare to.
	 */
	@Override
	public boolean equals(Polynom_able p1) {
		boolean equals=false;
		Iterator<Monom> itP1=p1.iteretor();
		Iterator<Monom> itPoly=poly.iterator();
		if(poly.size()==0) {
			if(!itP1.hasNext()) {         //if the two polynoms are the zero polynom.
				equals=true;
				return equals;
			}
			else {
				return equals;
			}
		}
		else {
			while(itP1.hasNext() && itPoly.hasNext()) {
				Monom currentP1=itP1.next();
				Monom currentPoly=itPoly.next();
				if(currentP1.get_coefficient()==currentPoly.get_coefficient()
						&& currentP1.get_power()==currentPoly.get_power()) {      //if the two monoms are the same.
					equals=true;
				}
				else {
					equals=false;
					return equals;
				}

			}
		}	
		return equals;
	}
	/**
	 * this function checks if the polynom is the zero polynom.
	 */
	@Override
	public boolean isZero() {
		Iterator<Monom> itPoly=poly.iterator();
		boolean isZero=false;
		if(!itPoly.hasNext()) {
			isZero=true;
		}
		else {
			return false;
		}
		return isZero;
	}
	/**
	 * Compute a value x' (x0<=x'<=x1) for with |f(x')| < eps
	 * assuming (f(x0)*f(x1)<=0, returns f(x2) such that:
	 * *	(i) x0<=x2<=x1 && (ii) f(x2)<eps
	 * @param x0 starting point
	 * @param x1 end point
	 * @param eps step (positive) value
	 * @return x2: the value of x such that f(x)=y (to the point of accuracy of epsilon).
	 */
	@Override
	public double root(double x0, double x1, double eps) {
		double start=x0,end=x1;
		double x2=(start+end)/2;            //x2= the average x value of x0 and x1.
		while(Math.abs(f(x2))>eps) {       //while y value of x2 bigger than eps.
			if(f(x2)<0) {              //if y value of x2 is negative.
				if(f(start)<0) {
					start=x2;
				}
				else {
					end=x2;
				}
			}
			else {                 //if y value of x2 is positive.
				if(f(start)<0) {
					end=x2;
				}
				else {
					start=x2;
				}
			}
			x2=(start+end)/2;
		}
		return x2;
	}
	/**
	 * this function is making a copy of this polynom
	 */
	@Override
	public Polynom_able copy() {
		Iterator<Monom> itPoly=poly.iterator();
		Polynom_able ans=new Polynom();
		while(itPoly.hasNext()) {
			Monom currentPoly=itPoly.next();
			Monom copy=new Monom (currentPoly);
			ans.add(copy);
		}
		return ans;
	}

	/**
	 * this function is derivative this polynom.
	 */
	@Override
	public Polynom_able derivative() {
		Iterator<Monom> itPoly=poly.iterator();
		Polynom_able ans=new Polynom();
		while(itPoly.hasNext()) {
			Monom currentPoly=itPoly.next();
			Monom copy=new Monom (currentPoly);
			copy.derivative();
			ans.add(copy);
		}
		return ans;
	}


	/**
	 * Compute Riemann's Integral over this Polynom starting from x0, till x1 using eps size steps,
	 * see: https://en.wikipedia.org/wiki/Riemann_integral
	 * @return the approximated area above the x-axis below this Polynom and between the [x0,x1] range.
	 * @param x0: the point where the area start to be calculate.
	 * @param x1: the point where the area stop being calculate.
	 * @param eps: size of the steps.
	 * @param tempEnd: when one of the points x0,x1 is positive and the other one's negative, tempEnd finds a point close
	 * to x-axis. that way i can calculate the positive area between [x0,x1].
	 */
	@Override
	public double area(double x0, double x1, double eps) {
		double start=x0;
		double area=0;
		double tempEnd=0;
		double end=x0+eps;
		boolean check=true;
		if(x0>x1) {
			return 0;
		}
		while(end<=x1) {

			if(f(start)>0 && f(end)>0) {             //if both y values of start and end are positive.
				area+=eps*(f((start+end)/2));
			}
			else if(f(start)>0 && f(end)<0) {       //if y value of start is positive and y value of end is negative.
				tempEnd=root(start,end,0.01);
				area+=(tempEnd-start)*(f((start+tempEnd)/2));

			}
			else if(f(start)<0 && f(end)>0) {       //if y value of start is negative and y value of end is positive.
				tempEnd=root(start,end,0.01);
				area+=(end-tempEnd)*(f((end+tempEnd)/2));
			}
			start=end;
			end+=eps;
			if(end>x1 && check && x1!=end-eps) {     //making sure that there is no more area to calculate.
				end=x1;
				check=false;
			}

		}

		return area;
	}
	
	/**
	 * @return an Iterator (of Monoms) over this Polynom
	 */
	@Override
	public Iterator<Monom> iteretor() {
		Iterator<Monom> it = poly.iterator();
		return it;

	}
	/**
	 * returns a String such that it matches the value of the polynom.
	 */
	public String toString() {
		Iterator<Monom> it=poly.iterator();
		boolean first=true;
		String polynom=new String("polynom: ");
		while(it.hasNext()) {
			Monom currentMonom=it.next();
			if(currentMonom.get_coefficient()>0) {
				if(first) {           //if this is the first char.
					polynom=polynom+""+currentMonom+" ";
					first=false;
				}
				else {
					polynom=polynom+"+"+currentMonom+" ";
				}
			}
			else
				polynom=polynom+""+currentMonom+" ";

		}
		return polynom;
	}


}
