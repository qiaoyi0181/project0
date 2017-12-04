public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Planet(double xP, double yP, double xV,
              double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p){
		double dx = p.xxPos - this.xxPos;
		double dy = p.yyPos - this.yyPos;
		return Math.sqrt(dx * dx + dy * dy);
	}

	public double calcForceExertedBy(Planet p){
		double G = 6.67e-11;
		return (G*p.mass*this.mass / (calcDistance(p)*calcDistance(p)));
	}

	public double calcForceExertedByX(Planet p){
		double dx = p.xxPos - this.xxPos;
		return (calcForceExertedBy(p)*dx/(calcDistance(p)));
	}

	public double calcForceExertedByY(Planet p){
		double dy = p.yyPos - this.yyPos;
		return (calcForceExertedBy(p)*dy/(calcDistance(p)));
	}

	public double calcNetForceExertedByX(Planet[] planets){
		double xForce = 0;
		for (Planet planet : planets){
			if ( !this.equals(planet)){
				xForce = xForce + calcForceExertedByX(planet);
			}
		}
		return xForce;
	}

	public double calcNetForceExertedByY(Planet[] planets){
		double yForce = 0;
		for (Planet planet : planets){
			if ( !this.equals(planet)){
				yForce = yForce + calcForceExertedByY(planet);
			}
		}
		return yForce;
	}

	public void update(double dt, double fX, double fY){
		double aX = fX/this.mass;
		double aY = fY/this.mass;
		xxVel = this.xxVel + dt*aX;
		yyVel = this.yyVel + dt*aY;
		xxPos = this.xxPos + dt*xxVel;
		yyPos = this.yyPos + dt*yyVel;
	}

	public void draw(){
		StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
	}

}
