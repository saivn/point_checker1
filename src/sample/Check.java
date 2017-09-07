package sample;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Annotation;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Polygon;

/**
 * Created by Olena on 19.05.2016.
 */
public class Check {
    private int n = 8;  //количество вершин многоугольника


    Polygon polygonEat = new Polygon();


    private Point2D Poly[] = new Point2D[]{
  /*      new Point2D(200, 170),
        new Point2D(320, 120),
        new Point2D(440, 170),
        new Point2D(440, 250),
        new Point2D(320, 300),
        new Point2D(200, 250),
*/

            new Point2D(50.0, 50.0),
            new Point2D(50.0, 80.0),
            new Point2D(180.0, 80.0),
            new Point2D(180.0, 185.0),
            new Point2D(50.0, 185.0),
            new Point2D(50.0, 200.0),
            new Point2D(200.0, 200.0),
            new Point2D(200.0, 50.0)


    };

    //возвращает true, если точка лежит слева от прямой
    private boolean vector_mult(Point2D A, Point2D B, double click_X, double click_Y) {
        if (((B.getX() - A.getX()) * (click_Y - A.getY()) - (B.getY() - A.getY()) * (click_X - A.getX())) >= 0)
            return true;
        else return false;
    }

    public void DrPoly(GraphicsContext g_c) {      //отрисовка многоугольника
        for (int i = 0; i < n - 1; i++) {
            g_c.strokeLine(Poly[i].getX(), Poly[i].getY(), Poly[i + 1].getX(), Poly[i + 1].getY());
        }
        g_c.strokeLine(Poly[n - 1].getX(), Poly[n - 1].getY(), Poly[0].getX(), Poly[0].getY());
    }

    //принадлежгость выпуклому многоугольнику
    public boolean check_attachment(double click_X, double click_Y) {  //возвращает true, если точка лежит слева от каждой прямой
        for (int i = 0; i < n - 1; i++) {
            if (!vector_mult(Poly[i], Poly[i + 1], click_X, click_Y)) return false;
        }
        if (vector_mult(Poly[n - 1], Poly[0], click_X, click_Y)) return true;
        else return false;
    }

    //принадлежность не выпуклому многоугольнику
    public boolean check_attach(double click_X, double click_Y) {
        int count = 0;        //количество пересечений
        for (int i = 0; i < n; i++) {
            int j = (i + 1) % n;  //следующая вершина
            if (Poly[i].getY() == Poly[j].getY())
                continue;
            if (Poly[i].getY() > click_Y && Poly[j].getY() > click_Y)
                continue;
            if (Poly[i].getY() < click_Y && Poly[j].getY() < click_Y)
                continue;
            if (Math.max(Poly[i].getY(), Poly[j].getY()) == click_Y)
                count++;
            else {
                if (Math.min(Poly[i].getY(), Poly[j].getY()) == click_Y)
                    continue;
                else {
                    double t = (click_Y - Poly[i].getY()) / (Poly[j].getY() - Poly[i].getY());
                    if (Poly[i].getX() + t * (Poly[j].getX() - Poly[i].getX()) >= click_X)
                        count++;
                }
            }
        }
        //return count & 1;

        count = count & 1;
        if (count > 0) return true;
        else return false;
    }

    /*
        bool is_inside(POINT *a, POINT b, int n)
    //a - массив вершин многоугольника, их n штук
    //b - точка для проверки вхождения
        {  int count=0;        //количество пересечений
            for(int i=0; i<n; i++)
            {
                int j=(i+1)%n;  //следующая вершина
                if(a.y==a[j].y)
                    continue;
                if(a.y>b.y && a[j].y>b.y)
                    continue;
                if(a.y<b.y && a[j].y<b.y)
                    continue;
                if(max(a.y,a[j].y)==b.y)
                    count++;
                else {
                    if(min(a.y,a[j].y)==b.y)
                        continue;
                    else {
                        float t=(b.y-a.y)/(a[j].y-a.y);
                        if(a.x+t*(a[j].x-a.x)>=b.x)
                            count++;
                    }
                }
            }
            return count&1;
        }
        */
    public boolean check_attach1(Polygon poly, double click_X, double click_Y) {

        Point2D polly[] = new Point2D[n];
        for (int i = 0, j = 0; i < n * 2; i += 2) {
            polly[j++] = new Point2D(poly.getPoints().get(i), poly.getPoints().get(i + 1));
        }

        int count = 0;        //количество пересечений
        int sizePoly = polly.length;
        for (int i = 0; i < sizePoly; i++) {
            int j = (i + 1) % sizePoly;  //следующая вершина
            if (polly[i].getY() == polly[j].getY()) continue;
            if (polly[i].getY() > click_Y && polly[j].getY() > click_Y) continue;
            if (polly[i].getY() < click_Y && polly[j].getY() < click_Y) continue;

            if (Math.max(polly[i].getY(), polly[j].getY()) == click_Y)
                count++;
            else {
                if (Math.min(polly[i].getY(), polly[j].getY()) == click_Y)
                    continue;
                else {
                    double t = (click_Y - polly[i].getY()) / (polly[j].getY() - polly[i].getY());
                    if (polly[i].getX() + t * (polly[j].getX() - polly[i].getX()) >= click_X)
                        count++;
                }
            }
        }

        count = count & 1;
        return (count > 0) ? true : false;
    }


}