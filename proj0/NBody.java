import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class NBody {

    public static double readRadius(String filename) {
        double radius;
        try {
            File file = new File(filename);
            Scanner in = new Scanner(file);
            in.nextInt();
            radius = in.nextDouble();
        }
        catch(FileNotFoundException ex){
            return 0;
        }
        return radius;

    }

    public static Planet[] readPlanets(String filename){
        File file = new File(filename);
        In in = new In(file);
//            Scanner in = new Scanner(file);
        int planets = in.readInt();
        double discard2 = in.readDouble();
        Planet[] allPlanets = new Planet[planets];
        for(int i = 0; i < planets; i++) {
            double xPos = in.readDouble();
            double yPos = in.readDouble();
            double xVel = in.readDouble();
            double yVel = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();
            allPlanets[i] = new Planet(xPos, yPos, xVel, yVel, mass, img);
        }
//        catch(IllegalArgumentException ex) {
//            System.out.println("Error1");
//        } catch (FileNotFoundException e) {
//            System.out.println("Error2");
//        }
//        catch (InputMismatchException f){
//            System.out.println("Error 3 " + allPlanets[2].xxPos);
//        }
//      catch (IOException e) {
//            e.printStackTrace();
//        }
        return allPlanets;
    }

    public static void main(String[] args) {

        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double uRadius = readRadius(filename);
        Planet[] allPlanets = readPlanets(filename);
        for (double time = 0; time < T; time += dt) {
            double[] xForces = new double[5];
            double[] yForces = new double[5];

            for (int i = 0; i < allPlanets.length; i++) {
                xForces[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
                yForces[i] = allPlanets[i].calcNetForceExertedByY(allPlanets);
            }
            for (int i = 0; i < allPlanets.length; i++) {
                allPlanets[i].update(dt, xForces[i], yForces[i]);
            }
            //Drawing
            StdDraw.setScale(-uRadius, uRadius);
            StdDraw.enableDoubleBuffering();
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (int i = 0; i < allPlanets.length; i++) {
                allPlanets[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", allPlanets.length);
        StdOut.printf("%.2e\n", uRadius);
        for (int i = 0; i < allPlanets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel,
                    allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);

        }
    }

}
