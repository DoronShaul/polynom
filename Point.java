package myMath;

public class Point {
	/**
	 * constructor of Point.
	 */
	public Point() {
		this.setX(0);
		this.setY(0);
	}

	/**
	 * constructor of Point.
	 * @param x=x value.
	 * @param y=y value.
	 */
	public Point(double x, double y) {
		this.setX(x);
		this.setY(y);
	}
	
	/**
	 * copy constructor.
	 */
	public Point(Point p) {
		this.setX(p.getX());
		this.setY(p.getY());
	}
	
	public String toString() {
		String s="x: "+this.getX()+"   y: "+this.getY();
		return s;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	
	///////private methods//////////

	private double x;
	private double y;

	private void setX(double x) {
		this.x = x;
	}

	private void setY(double y) {
		this.y = y;
	}
}
