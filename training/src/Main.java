//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;
public class Main {
    public static double quadArea(double a, double b) {
        return a*b;
    }
    public static double triangleArea(double b, double h) {
        return b*h/2;
    }
    public static double semiellipseArea(double a, double h) {
        return a*h*Math.PI/2;
    }
    public static double circleArea(double w, double h) {
        return w*h*Math.PI;
    }
    public static double tryParseDouble(String txt, int def) {
        try {
            return Math.abs(Double.parseDouble(txt));
        } catch (NumberFormatException e) {
            System.out.println("Input treated as " + def);
            return def;
        }
    }

    public static double area = 0;
    public static Scanner reader = new Scanner(System.in);
    public static void addSurfaces() {
        System.out.println("Enter surfaces to be painted. You can remove areas from these surfaces (e.g. windows, doors) at a later time.");
        boolean inProgress = true;
        while(inProgress) {
            boolean dupe = false;
            double shapeArea = 0;
            System.out.println("You can add a quadrilateral (Q), triangle (T), or semicircle/semiellipse (S)");
            String surfaceType = reader.next();
            System.out.println("What is the height of the surface (in m)?");
            double height = tryParseDouble(reader.next(), 0);
            System.out.println("What is the width of the surface (in m)?");
            double width = tryParseDouble(reader.next(), 0);
            if ("q".equalsIgnoreCase(surfaceType) || "quadrilateral".equalsIgnoreCase(surfaceType)) {
                shapeArea = quadArea(width, height);
            } else if ("t".equalsIgnoreCase(surfaceType) || "triangle".equalsIgnoreCase(surfaceType)) {
                shapeArea = triangleArea(width,height);
            } else if ("s".equalsIgnoreCase(surfaceType) || "semicircle".equalsIgnoreCase(surfaceType) || "semiellipse".equalsIgnoreCase(surfaceType)) {
                shapeArea = semiellipseArea(width, height);
            } else {
                System.out.println("Couldn't recognise your input.");
            }
            do {
                area += shapeArea;
                System.out.println("Total area to paint: " + String.format("%.2f", area));
                System.out.println("Enter D to duplicate previous surface, P to progress to next stage, or any other input to add new surface.");
                String choice = reader.next();
                if ("p".equalsIgnoreCase(choice)) {
                    inProgress = false;
                } else dupe = "d".equalsIgnoreCase(choice);
            } while(dupe && inProgress);
        }
    }
    public static void removeSurfaces() {
        boolean inProgress = false;
        System.out.println("Would you like to remove any areas (windows, doors etc.) from the area to be painted? (Y/N) ");
        String input = reader.next();
        if ("y".equalsIgnoreCase(input)) {
            inProgress = true;
        }
        while (inProgress) {
            System.out.println("You can remove a quadrilateral (Q), triangle (T), circle/ellipse (C), or semicircle/semiellipse (S)");
            String surfaceType = reader.next();
            System.out.println("What is the height of the surface (in m)?");
            double height = tryParseDouble(reader.next(), 0);
            System.out.println("What is the width of the surface (in m)?");
            double width = tryParseDouble(reader.next(), 0);
            if ("q".equalsIgnoreCase(surfaceType) || "quadrilateral".equalsIgnoreCase(surfaceType)) {
                area -= quadArea(width, height);
            } else if ("t".equalsIgnoreCase(surfaceType) || "triangle".equalsIgnoreCase(surfaceType)) {
                area -= triangleArea(width, height);
            } else if ("s".equalsIgnoreCase(surfaceType) || "semicircle".equalsIgnoreCase(surfaceType) || "semiellipse".equalsIgnoreCase(surfaceType)) {
                area -= semiellipseArea(width, height);
            } else if ("c".equalsIgnoreCase(surfaceType) || "circle".equalsIgnoreCase(surfaceType) || "ellipse".equalsIgnoreCase(surfaceType)) {
                area -= circleArea(width, height);
            } else {
                System.out.println("Couldn't recognise your input.");
            }
            if (area < 0) {
                area = 0;
            }
            System.out.println("Total area to paint: " + String.format("%.2f", area));
            System.out.println("Remove more? (Y/N) ");
            if ("n".equalsIgnoreCase(reader.next())) {
                inProgress = false;
            }
        }
    }
    public static void calculateCost() {
        //100ml per sq m
        double litres = area / 10;
        System.out.println("You require " + litres + "l of paint.");
        System.out.println("This is most efficiently split into:");
        int tenLitres = 0;
        int fiveLitres = 0;
        int twoPtFiveLitres = 0;
        if (litres >=10) {
            tenLitres = (int)Math.floor(litres/10);
            litres -= 10*tenLitres;
            System.out.println(tenLitres + " 10l can(s),");
        }
        if (litres >= 5) {
            fiveLitres = (int)Math.floor(litres/5);
            litres -= 5*fiveLitres;
            System.out.println("a 5l can,");
        }
        if (litres >= 2.5) {
            twoPtFiveLitres = (int)Math.floor(litres/2.5);
            litres -= 2.5*twoPtFiveLitres;
            System.out.println("a 2.5l can,");
        }
        int oneLitres = (int)Math.ceil(litres);
        System.out.println(oneLitres + " 1l cans.");

        double cost = 0;
        System.out.println("Enter the values of paint, or . to use defaults");
        System.out.print("A 10l can costs £");
        cost += (tryParseDouble(reader.next(), 26))*tenLitres;
        System.out.print("A 5l can costs £");
        cost += (tryParseDouble(reader.next(), 14))*fiveLitres;
        System.out.print("A 2.5l can costs £");
        cost += (tryParseDouble(reader.next(), 7))*twoPtFiveLitres;
        System.out.print("A 1l can costs £");
        cost += (tryParseDouble(reader.next(), 3))*oneLitres;

        System.out.println("Total cost: £" + String.format("%.2f", cost));
        //add a comment
    }

    public static void main(String[] args) {
        addSurfaces();
        removeSurfaces();
        calculateCost();


    }
}