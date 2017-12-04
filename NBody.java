public class NBody {

	public static double readRadius(String filename){
		In in = new In(filename);

		int number = in.readInt();
		double radius = in.readDouble();

		return radius;
	}

	public static Planet[] readPlanets(String filename){
		In in = new In(filename);
		int number = in.readInt();
		double radius = in.readDouble();
		Planet[] planets = new Planet[number];

		for (int i=0; i<number; i++){
			double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double mass = in.readDouble();
			String img = in.readString();

			planets[i] = new Planet(xP, yP, xV, yV, mass, img);
    
		}
		return planets;
	}

	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];

		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);

		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, "images/starfield.jpg");

		for (Planet item : planets){
			item.draw();
		}

		double time = 0;

		while (time < T){
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];

			for (int i=0; i < planets.length; i++){
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);	
			}

			for (int i=0; i < planets.length; i++){
				planets[i].update(dt, xForces[i], yForces[i]);
			}

			StdDraw.picture(0, 0, "images/starfield.jpg");

			for (Planet item : planets){
				item.draw();
			}

			StdDraw.show(10);

			time = time + dt;

		}


        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
        	StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
   		    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel, planets[i].yyVel, planets[i].mass, planets[i].imgFileName);	
        }
		
	}

}
