package Raytracing;

import Math.Vector3;

public class Camera {
    private Vector3 position;

    /* 
     * for now the camera will be static, so we don't need a direction
     */

    /* Vector3 direction; */

    public Camera(Vector3 position/* , Vector3 direction */) {
        this.position = position;
        /* this.direction = direction; */
    }

    public Vector3 getPosition() {
        return position;
    }

    /* public Vector3 getDirection() {
        return direction;
    } */

    public void setPosition(Vector3 position) {
        this.position = position;
    }
}
