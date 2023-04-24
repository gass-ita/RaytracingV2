package Math;

public class Polygon {
    private final Vector3[] vertices;
    private final Vector3 normal;

    public Polygon(Vector3[] vertices) {
        this.vertices = vertices;
        this.normal = getNormal();
    }


    /* 
    *    My solution uses the Möller–Trumbore intersection algorithm
    *    https://youtu.be/EZXz-uPyCyA 
    */
    public double intersect(Ray ray) {
        Vector3 rayOrigin = ray.origin;
        Vector3 rayDirection = ray.direction;

        //*first check if the ray is parallel to the triangle
        double t = (vertices[0].subtract(rayOrigin)).dot(normal) / rayDirection.dot(normal);
        if (t < 0) {
            return -1;  //here the ray is parallel to the triangle
        }
        //if we pass the first test, then there is a potential intersection
        //as the ray hit the plane of the triangle

        //*let's see where the ray intersects the plane
        Vector3 intersection = ray.pointAt(t);

        //*now we need to check if the intersection point is inside the triangle
        //to do that we need to find the barycentric coordinates of the intersection point
        //and check if they are all positive and their sum is less than or equal to 1

        /* 
        *   barycentric coordinates are the coordinates of a point relative to a triangle
        *   make a drawing using \ / and _ of a triangle and a point inside it
        *              (1, 0, 0)
        *              A
        *             /\
        *            /  \
        *           /    \
        *          /      \
        *         /        \
        *        /          \
        *       /            \
        *      /              \
        *     /                \
        *    /                  \
        *  C/____________________\B (0, 1, 0)
        *  (0, 0, 1)
        */


        Vector3 v1 = vertices[1].subtract(vertices[0]);
        Vector3 v2 = vertices[2].subtract(vertices[0]);
        Vector3 v3 = intersection.subtract(vertices[0]);

        double dot11 = v1.dot(v1);
        double dot12 = v1.dot(v2);
        double dot13 = v1.dot(v3);
        double dot22 = v2.dot(v2);
        double dot23 = v2.dot(v3);

        double invDenom = 1 / (dot11 * dot22 - dot12 * dot12);
        double u = (dot22 * dot13 - dot12 * dot23) * invDenom;
        double v = (dot11 * dot23 - dot12 * dot13) * invDenom;

        //we don't need to calculate w because w = 1 - u - v
        //double w = 1 - u - v;
        //and so if u + v + w = 1 then u + v <= 1

        if (u >= 0 && v >= 0 && u + v <= 1) {
            return t;
        } else {
            return -1;
        }
    }





    

    private Vector3 getNormal() {
        Vector3 v1 = vertices[1].subtract(vertices[0]);
        Vector3 v2 = vertices[2].subtract(vertices[0]);
        return v1.cross(v2).normalize();
    }

}
