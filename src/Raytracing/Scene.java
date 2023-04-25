package Raytracing;

import java.util.ArrayList;

import javax.imageio.ImageIO;

import Math.Polygon;
import Math.Ray;
import Math.Vector3;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Scene {

    private ArrayList<Polygon> polygons = new ArrayList<Polygon>();
    //private ArrayList<Light> lights = new ArrayList<Light>();
    private Camera camera;

    public Scene(Camera camera) {
        this.camera = camera;
    }

    public void addPolygon(Polygon polygon) {
        polygons.add(polygon);
    }

    public ArrayList<Polygon> getPolygons() {
        return polygons;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public void render(int width, int height){
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Calculate the u and v coordinates of the pixel relative to the camera and image size
                double u = (double)x / (double)width;
                double v = (double)y / (double)height;

                // Create a ray from the camera to the pixel
                Ray ray = new Ray(camera.getPosition(), new Vector3(u - 0.5, v - 0.5, 1).normalize());

                

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
