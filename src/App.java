import Math.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class App {
    public static void main(String[] args) {
        int width = 640;
        int height = 480;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Vector3 cameraPosition = new Vector3(0, 0, -5);
        Vector3 polyCenter = new Vector3(0, 0, 0);
        Polygon poly = new Polygon(new Vector3[] {
            //triangle
            new Vector3(-1, -1, 0).add(polyCenter),
            new Vector3(1, -1, 0).add(polyCenter),
            new Vector3(0, 1, 0).add(polyCenter)
            
        });

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double u = (double)x / (double)width;
                double v = (double)y / (double)height;

                Ray ray = new Ray(cameraPosition, new Vector3(u - 0.5, v - 0.5, 1).normalize());

                double t = poly.intersect(ray);
                if (t >= 0) {
                    // Ray intersects the triangle, color the pixel white
                    image.setRGB(x, height - y - 1, 0xffffff);
                } else {
                    // Ray does not intersect the triangle, color the pixel black
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
