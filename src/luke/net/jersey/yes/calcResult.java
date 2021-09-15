package luke.net.jersey.yes;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement()
public class calcResult {
	
	private String op;
	private double x;
	private double y;
	private double res;
	
	public void setOp(String str) {
		this.op = str;
	}
	public String getOp() {
		return this.op;
	}
	public void setX(double d) {
		this.x = d;
	}
	public double getX() {
		return this.x;
	}
	public void setY(double d) {
		this.y = d;
	}
	public double getY() {
		return this.y;
	}
	public void setRes(double d) {
		this.res = d;
	}
	public double getRes() {
		return this.res;
	}

}
