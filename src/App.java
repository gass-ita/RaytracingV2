import Math.*;
import Raytracing.Camera;
import Raytracing.Scene;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;



public class App {
    public static void main(String[] args) {
        int width = 640;
        int height = 480;


        Vector3 cameraPosition = new Vector3(0, 0, -5);
        
        
        // Create a list of polygons
        List<Polygon> polygons = new ArrayList<Polygon>();

        // Create a triangle
        Vector3[] triangleVertices = new Vector3[] {
            new Vector3(-1, -1, 0),
            new Vector3(1, -1, 0),
            new Vector3(0, 1, 0)
        };

        Polygon triangle = new Polygon(triangleVertices);
        polygons.add(triangle);


        Scene scene = new Scene(new Camera(cameraPosition));
        scene.addPolygon(triangle);

        scene.render(width, height);
        
        



    
    
        
        

        
    }
}
