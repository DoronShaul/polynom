package myMath;

import java.util.Comparator;

public class Monom_Comperator implements Comparator<Monom> {

	
/**
 * this function compare between two monom. first it calculate the difference of the powers between the monoms.
 * if the powers are equals than it calculate the difference of the coefficients. if the function return
 * a positive number then the first monom (m) is bigger, if it's a negative number the second monom (n)
 * is bigger. if the function return 0 then the monoms are equals. 
 */
	public int compare(Monom m,Monom n) {
		int ans=m.get_power()-n.get_power();
		if(ans==0) {
			return (int)(m.get_coefficient()-n.get_coefficient());
		}
		else {
			return ans;
		}
	}

}
