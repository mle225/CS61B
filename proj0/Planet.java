public class Planet {

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet (double xP, double yP, double xV, double yV, double m, String img)
    {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet P){
        xxPos = P.xxPos; yyPos = P.yyPos; xxVel = P.xxVel; yyVel = P.yyVel;
        mass = P.mass; imgFileName = P.imgFileName;
    }

    public double calcDistance(Planet P){
        double dx = P.xxPos - xxPos;
        double dy = P.yyPos - yyPos;
        double distance = Math.sqrt((dx * dx) + (dy * dy));
        return distance;
    }

    public double calcForceExertedBy(Planet P){
        final double G = 6.67 * Math.pow(10, -11);
        double r = calcDistance(P);
        double force = (G * mass * P.mass) / Math.pow(r,2);

        return force;
    }

    public double calcNetForceExertedByX (Planet[] allPlanets){
        double sum = 0;
        for (int i = 0; i < allPlanets.length; i++){
            if(this.equals(allPlanets[i]))
                continue;
            double dx = allPlanets[i].xxPos - this.xxPos;
            double fx = (calcForceExertedBy(allPlanets[i]) * dx)
            / calcDistance(allPlanets[i]);
            sum += fx;
        }

        return sum;
    }

    public double calcForceExertedByX (Planet p){

            double dx = p.xxPos - this.xxPos;
            return (calcForceExertedBy(p) * dx)
                    / calcDistance(p);

    }

    public double calcNetForceExertedByY (Planet[] allPlanets){
        double sum = 0;
        for (int i = 0; i < allPlanets.length; i++){
            if(this.equals(allPlanets[i]))
                continue;
            double dy = allPlanets[i].yyPos - this.yyPos;
            double fy = (calcForceExertedBy(allPlanets[i]) * dy)
                    / calcDistance(allPlanets[i]);
            sum += fy;
        }

        return sum;
    }

    public double calcForceExertedByY (Planet p){

        double dy = p.yyPos - this.yyPos;
        return (calcForceExertedBy(p) * dy)
                / calcDistance(p);
    }

    public void update(double dt, double Fx, double Fy){
        double ax = Fx / mass;
        double ay = Fy / mass;
        this.xxVel += ax * dt;
        this.yyVel += ay * dt;
        this.xxPos += xxVel * dt;
        this.yyPos += yyVel * dt;
    }

    public void draw(){
        String filename = "images/" + imgFileName;
        StdDraw.picture(xxPos, yyPos, filename);
    }

}

