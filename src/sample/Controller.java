package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Controller {
    @FXML
    Pane pane;
    @FXML
    Button butt_1;
    @FXML
    Canvas canvas;
    @FXML
    Label label;
    Check Polygon = new Check();
    GraphicsContext draw;
    double x = 0, y = 0;
    @FXML
    public void initialize (){
        draw = canvas.getGraphicsContext2D();
        Polygon.DrPoly(draw);

        pane.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Polygon.polygonEat.getPoints().addAll(new Double[]{
                        50.0, 50.0,
                        50.0, 80.0,
                        180.0, 80.0,
                        180.0, 185.0,
                        50.0, 185.0,
                        50.0, 200.0,
                        200.0, 200.0,
                        200.0, 50.0

            });





                x = event.getX();
                y = event.getY();
                draw.setLineWidth(3);
              //  if(Polygon.check_attachment(x, y)){
               // if(Polygon.check_attach(x, y)){
                if(Polygon.check_attach1(Polygon.polygonEat, x, y)){
                    draw.setStroke(Color.BLUE);
                    label.setText("Принадлежит");
                } else {
                    draw.setStroke(Color.RED);
                    label.setText("Не принадлежит");
                }
                draw.strokeLine(x, y, x, y);
            }
        });

        butt_1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                label.setText("");
                draw.clearRect(0, 0, 640, 480);
                draw.setLineWidth(1);
                draw.setStroke(Color.BLACK);
                Polygon.DrPoly(draw);
            }
        });

    }
}
