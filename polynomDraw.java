package myMath;

import java.awt.Font;
import java.text.DecimalFormat;

public class polynomDraw {

    /**
     * @author 308545151, 308224450.
     * this functions were made by 308545151, as an addition to the existing class StdDraw. this functions allow us to
     * draw a poylnom and find the extreme points of it. 
     */
    
    /**
     * this function is drawing extreme points.
     * @param p=current polynom.
     * @param x1=end value.
     * @param eps=the steps size.
     * @param x0=first value.
     */
	private static void drawExtremePoints(Polynom_able p, double x1, double eps, double x0) {
    	DecimalFormat df = new DecimalFormat();
    	df.setMaximumFractionDigits(3);                           //making the function showing only 3 digits after the period.
		while(x0<=x1) {
    		if(Math.abs(p.derivative().f(x0))<=eps) {             // if f'(x) <=eps, if it's close enough to extreme point -->suspected extreme point.
    			if(p.derivative().f(x0+0.5)<0 && (p.derivative().f(x0-0.5)>0) || p.derivative().f(x0+0.5)>0 && (p.derivative().f(x0-0.5)<0)){   // confirms that the suspected point is indeed extreme point.
        			Point point=new Point(Double.parseDouble(df.format(x0)),Double.parseDouble(df.format(p.f(x0))));
    				Font font1 = new Font("Arial", Font.BOLD, 12);
        			StdDraw.setFont(font1);
        	        StdDraw.setPenColor(StdDraw.BLACK);
        	    	StdDraw.text(point.getX(), point.getY()-0.2, "Extreme point");
        	    	StdDraw.text(point.getX(), point.getY()-0.5, "("+point.getX()+" ,"+point.getY()+")");

    			}

    		}
    		
    		x0+=0.009;                   //the closest value to eps, because in eps steps the first extreme point doesn't count.
    	}
	}
	
	/**
	 * this function draws the polynom on the spreadsheet.
	 * 
	 * @param p= input polynom.
	 * @param x1= end value.
	 * @param eps= epsilon, represents size of each step.
	 * @param x0= start value.
	 */
	
	private static void drawPolynom(Polynom_able p, double x1, double eps, double x0) {
		while(x0<=x1) {
    		StdDraw.setPenRadius(0.01);
            StdDraw.setPenColor(StdDraw.BLUE);
        	StdDraw.line(x0, p.f(x0), x0+eps, p.f(x0+eps));
        	x0+=eps;
    	}
	}
	
	/**
	 * this function calculates the area from above the polynom and under axis-X 
	 * 
	 * @param p=  input polynom.
	 * @param x1= end value
	 * @param eps= epsilon, represents size of each step.
	 * @param x0= start value.
	 * @return absolute value of the area
	 */
	
	private static double calculateArea(Polynom_able p, double x1, double eps, double x0) {
		double area=0; 
		double midPoint=0;  
		double root;
		if(x0>x1) {        //if x0 is greater than x1, swap them.
			double temp=x1;
			x1=x0;
			x0=temp;
		}
		double start=x0;
		double end=start+eps;
		while(start<=x1) {    
			if(p.f(start)<=0 && p.f(end)<=0) {    //if the function values of x=start and x=end are both negative
				midPoint=(p.f((start+end)/2));
				area+=midPoint*eps;
			}
			else if(p.f(start)<=0 && p.f(end)>=0) {  //if the function value at x=start is negative and x=end is positive.
				root=p.root(start, end, eps);
				midPoint=(p.f((start+root)/2));
				area+=midPoint*(end-root);
			}
			else if(p.f(start)>=0 && p.f(end)<=0) {  //if the function value at x=start is positive and x=end is negative.
				root=p.root(start, end, eps);         
				midPoint=(p.f((end+root)/2));
				area+=midPoint*(end-root);
			}
			start=end;        
			end+=eps;
		}
		Font font1 = new Font("Arial", Font.BOLD, 27);
		StdDraw.setFont(font1);
        StdDraw.setPenColor(StdDraw.BOOK_RED);
    	DecimalFormat df = new DecimalFormat();
    	df.setMaximumFractionDigits(3);
    	StdDraw.text(4.8, -8, "[Area: "+df.format(area)+"]");
		return Math.abs(area);  //returns the absolute value of the area.
	}

   	/**
   	 * this function is drawing the axis-y.
   	 */
	private static void drawAxisY(Font font, int endScaleMarkY) {
		StdDraw.setPenRadius(0.001);
        StdDraw.setPenColor(StdDraw.BLACK);
    	StdDraw.line(0, 10, 0, -10);                      //drawing y-axis line.
    	StdDraw.setFont(font);
        StdDraw.setPenColor(StdDraw.BLACK);
    	StdDraw.text(0, 8.5, "Y");
		for(int i=-8;i<=endScaleMarkY;i++) {
       		if(i!=0) {
            	StdDraw.setFont(font);
                StdDraw.setPenColor(StdDraw.BLACK);
            	StdDraw.text(0, i, "-");
            	StdDraw.text(-0.2, i, ""+i);		
       		}
       	}
	}

   	/**
   	 * this function is drawing the axis-x.
   	 */
	private static void drawAxisX(Font font, int endScaleMarkX) {
		StdDraw.setPenRadius(0.001);
        StdDraw.setPenColor(StdDraw.BLACK);
    	StdDraw.line(-10, 0, 10, 0);                      //drawing x-axis line.
    	StdDraw.setFont(font);
        StdDraw.setPenColor(StdDraw.BLACK);
    	StdDraw.text(5.8, 0, "X");
		for(int i=-1;i<=endScaleMarkX;i++) {
            if(i!=0) {
            	StdDraw.setFont(font);
                StdDraw.setPenColor(StdDraw.BLACK);
            	StdDraw.text(i, 0, "|");
            	StdDraw.text(i, 0.4, ""+i);
            }
       	}
	}



	public static void main(String[] args) {
    	
    	StdDraw.setCanvasSize(800,800);                           //setting the size of the screen to 800*800.
    	StdDraw.setXscale(-2, 6);                                 //the range of x's in the graph.
    	StdDraw.setYscale(-9, 9);                                 //the range of y's in the graph.
    	Font font = new Font("Arial", Font.BOLD, 20);
    	Monom m=new Monom(0.2,4);
    	Monom m1=new Monom(-1.5,3);
    	Monom m2=new Monom(3,2);
    	Monom m3=new Monom(-1,1);
    	Monom m4=new Monom(-5,0);
    	Polynom_able p=new Polynom();
    	p.add(m);
    	p.add(m1);
    	p.add(m2);
    	p.add(m3);
    	p.add(m4);
    	
    	double x0=-2;
    	double x1=6;
    	double eps=0.01;
       	int endScaleMarkX=5;
       	int endScaleMarkY=8;
       	

       	drawAxisX(font, endScaleMarkX);        //drawing the axis-x from -2 to 6.
       	
       	drawAxisY(font, endScaleMarkY);        //drawing the axis-y from -8 to 8.

    	drawPolynom(p, x1, eps, x0);  	       //drawing the polynom: 0.2x^4-1.5x^3+3.0x^2-x-5.
    	
    	drawExtremePoints(p, x1, eps, x0);
    	DecimalFormat df = new DecimalFormat();
    	df.format(calculateArea(p, x0, eps, x1));     //calculate the area of the function below axis-x.
		
		
		
	}

}
