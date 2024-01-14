package com.example.project2connect4finished;

public class CollisionDetermine {
    public static boolean checkCircleCollide(double x1, double y1, double r1, double x2, double y2, double r2){
        return Math.abs((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)) < (r1 + r2) * (r1 + r2);
    }
    private static Point closestPointOnLine(double lx1, double ly1,
                             double lx2, double ly2, double x0, double y0){
        double A1 = ly2 - ly1;
        double B1 = lx1 - lx2;
        double C1 = (ly2 - ly1)*lx1 + (lx1 - lx2)*ly1;
        double C2 = -B1*x0 + A1*y0;
        double det = A1*A1 - -B1*B1;
        double cx = 0;
        double cy = 0;
        if(det != 0){
            cx = (float)((A1*C1 - B1*C2)/det);
            cy = (float)((A1*C2 - -B1*C1)/det);
        }
        else{
            cx = x0;
            cy = y0;
        }
        return new Point(cx, cy);
    }
    private static Point dd(double circle1X, double circle1Y, double circle1VX, double circle1VY, double circle2X, double circle2Y) {
        return closestPointOnLine(circle1X, circle1Y, circle1X + circle1VX, circle1Y + circle1VY, circle2X, circle2Y);
    }
    private static double closestDistSq(double circle2X, double DX, double circle2Y, double DY) {
        return Math.pow(circle2X - DX, 2) + Math.pow(circle2Y - DY, 2);
    }
    public static boolean collisionDetect(double x1, double y1, double r1, double x2, double y2, double r2, double VX1, double VY1, double VX2, double VY2) {
        Point d = dd(x1, y1, VX1, VY1, x2, y2);
        double closestdistsq = closestDistSq(x2, d.getX(), y2, d.getY());
        if (checkCircleCollide(x1, y1, r1, x2, y2, r2) && closestdistsq <= Math.pow(r1 + r2, 2)) {
            return true;
        }
        else {
            return false;
        }
    }
}
