
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
		boolean pow_OK=true;        //indicates positive natural power.
		int j=s.indexOf("^");
		if(j==-1) {                 //if there is no "^" then the power value is one.
			this.set_power(1);
		}
		else {       //if there is "^" in the string.
			int pow=Integer.parseInt(s.substring(j+1, s.length()));        //calculate the value after the "^".
			if(pow<0) {		     
				System.err.println();
				try {
					throw new Exception("Error: the power has to be a non-negative integer: "+pow);
				} catch (Exception e) {
					e.printStackTrace();
				}
				pow_OK=false; 
			}
			else {
				this.set_power(pow);
			}
		}
		if(pow_OK)             //if the power is a non-negative integer.
		{
			int i=s.indexOf("x");
			if(i==-1) {            //if the x is not found in the string.
				this.set_coefficient(Double.parseDouble(s.substring(0, s.length())));
				this.set_power(0);
			}
			else {         //if there is "x" in the string.
				if(i==0) {        //if "x" is in the beginning of the string.
					this.set_coefficient(1);
				}
				else {       //if the "x" is in the string, but not the first char.
					double co=Double.parseDouble(s.substring(0, i));
					if(co==0) {       //if the coefficient value is 0.
						this.set_coefficient(0);
						this.set_power(0);
					}
					else { 
						this.set_coefficient(co);
					}
				}
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
