
package myMath;
/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @authors  308224450,308545151
 *
 */
public class Monom implements function{


	/**
	 * this function is a construction of monom. initialize it (0,0).
	 */
	public Monom() {
		this.set_coefficient(0);
		this.set_power(0);
	}

	/**
	 * this function is a construction of monom. OK strings:{"x^2","x", "34", "5x^5", "4x^0", "0x^4"}
	 * not OK strings:{"x^-2","xx", "34^3", "5x^", "^0", "0x^^4", "5x7"}
	 * @param s: the input string.
	 */
	
	
	public Monom(String s) {
		s.toLowerCase();            //changes X --> x.
		double coe=0;
		int pow=0;
		int xLocation=s.indexOf("x");
		boolean pow_OK=true;        //indicates positive natural power.
		int k=s.indexOf("*");
		if(k==0) {                  //if the first char is "*".
			try {
				throw new Exception("illegeal expression");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(k==-1) {                             //if there is no "*".
			if(xLocation==-1) {                 //if there is no "x".
				coe=Double.parseDouble(s);
				this.set_coefficient(coe);
				this.set_power(0);
				return;
			}
			else {                              //if there is "x".
				if(xLocation!=0) {
					String co=s.substring(0, xLocation);
					coe=Double.parseDouble(co);
					this.set_coefficient(coe);
				}
				else {
					this.set_coefficient(1);
				}
			}
		}
		else if(xLocation==k+1) {             //if "x" is right after "*".
			String co=s.substring(0, k);
			coe=Double.parseDouble(co);
			this.set_coefficient(coe);
		}
		
		if(xLocation==s.length()-1) {             //if "x" is the last char.    
			this.set_power(1);
			return;
		}
		else {
			int h=s.indexOf("^");            	
			if(h==-1) {                      //if there is no "^".
				try {
					throw new Exception("illegeal expression");
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
			else if(xLocation+1!=h){         //if the "^" sign is not right after "x".
				try {
					throw new Exception("illegeal expression");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else {
				pow=Integer.parseInt(s.substring(h+1, s.length()));
				this.set_power(pow);
			}
		}
	}

	/**
	 * this function is a construction.
	 * @param a: the coefficient
	 * @param b: the power of x.
	 */
	public Monom(double a, int b){
		
		if(b>=0) {
			this.set_power(b);
		}
		else {
			try {
				throw new Exception("Error: the power has to be a non-negative integer: "+b);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.set_coefficient(a);
	}

	/**
	 * this function is a copy construction.
	 * @param ot: the original monom.
	 */
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}

	/**
	 * this function represent y=f(x). 
	 * @param ans: is the valuation of a*x^b.
	 */
	@Override
	public double f(double x) {
		double ans=(Math.pow(x, this.get_power()))*this.get_coefficient();
		return ans;
	}
	/**
	 * this function is the derivative of the monom: (a*b)*x^(b-1).
	 * @param m: the original monom.
	 * @return ans: the monom after the derivative.
	 */
	public Monom derivative() {
		if(this.get_power()==0) {
			this.set_coefficient(0);
			this.set_power(0);
			return this;
		}
		else {
			this.set_coefficient(this.get_coefficient()*this.get_power());
			this.set_power(this.get_power()-1);
			return this;
		}

	}

	/**
	 * this function multiply two monoms. let's say the first one is: a*x^b and the other one:
	 * c*x^d, then the first monom will get the value of: (a*c)*x^(b+d).
	 * @param m: the first monom.
	 * @param n: the second monom.
	 * @return ans: the result of the multiplication between the monoms.
	 */
	public Monom multiply(Monom n) {
		this.set_coefficient(this.get_coefficient()*n.get_coefficient());
		this.set_power(this.get_power()+n.get_power());
		return this;
	}
	/**
	 * this function multiply two monoms. let's say the first one is: a*x^b and the other one:
	 * c*x^d, then the new monom will be: (a*c)*x^(b+d).
	 * @param m: the first monom.
	 * @param n: the second monom.
	 * @return res: the result of the multiplication between the monoms.
	 */
	public Monom multiply(Monom m, Monom n) {
		Monom res=new Monom(0,0);
		res.set_coefficient(m.get_coefficient()*n.get_coefficient());
		res.set_power(m.get_power()+n.get_power());
		return res;
	}
	/**
	 * this function adds the monom n to the original monom only if they have the same power value. 
	 * @param n: the monom that we add to the original one.
	 * @return the sum of these 2 monoms. 
	 */
	public Monom add(Monom n) {
		if(this.get_power()==n.get_power()) {
			this.set_coefficient(this.get_coefficient()+n.get_coefficient());
			this.set_power(this.get_power());
			if(this.get_coefficient()==0) {
				Monom zero=new Monom (0,0);
				return zero;
			}
			return this;
		}
		else {
			return null;
		}
	}
	/**
	 * this function substracts the monom m from the original monom only if they have the same power value. 
	 * @param m: the monom that we substracts from the original monom.
	 * @return the difference of these 2 monoms. 
	 */
	public Monom substract(Monom m) {
		if(this.get_power()==m.get_power()) {
			this.set_coefficient(this.get_coefficient()-m.get_coefficient());
			return this;
		}
		else {
			return null;
		}

	}
	/**
	 * returns a String such that it matches the value of the monom.
	 */
	@Override
	public String toString() {
		String monom="";
		if(get_coefficient()==0) {
			return ""+0;
		}
		else if(get_coefficient()!=1) {
			monom+=""+get_coefficient();
		}
		if(get_power()==0) {
			return monom;
		}
		if (get_power()==1) {
			return monom+"*x";
		}
		if(get_coefficient()==1)
			return monom+"x"+"^"+""+get_power();
		return monom+"*x"+"^"+""+get_power();

	}

	
	/**
	 * 
	 * @return the power of the monom
	 */
	public int get_power() {
		return this._power;
	}
	/**
	 * 
	 * @return the coefficient of the monom
	 */
	public double get_coefficient() {
		return this._coefficient;
	}
	
							//////////private functions////////////
	
	private void set_coefficient(double a){
		this._coefficient = a;
	}
	private void set_power(int p) {
		this._power = p;
	}

	private double _coefficient; // 
	private int _power; 
}
