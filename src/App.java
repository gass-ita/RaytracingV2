import Math.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class App {
    public static void main(String[] args) {
        int width = 640;
        int height = 480;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

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

        
        



    
    
        
        

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Calculate the u and v coordinates of the pixel relative to the camera and image size
                double u = (double)x / (double)width;
                double v = (double)y / (double)height;

                // Create a ray from the camera to the pixel
                Ray ray = new Ray(cameraPosition, new Vector3(u - 0.5, v - 0.5, 1).normalize());

                

                double t = Double.POSITIVE_INFINITY;
                for (Polygon p : polygons) {
                    double t2 = p.intersect(ray);
                    if (t2 >= 0 && t2 < t) {
                        t = t2;
                    }
                }

                if (t < Double.POSITIVE_INFINITY) {
                    image.setRGB(x, height - y - 1, 0xffffff);
                } else {
                    image.setRGB(x, height - y - 1, 0x000000);
                }


                
            }
        }

        try {
            ImageIO.write(image, "png", new File("output.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
