package org.terasology.world.block.marchingCubes;

import org.terasology.math.geom.Vector3f;

public enum VerticeNeighbor {
    FRONT_TOP_LEFT(new Vector3f(-0.5f, 0.5f, -0.5f)),
    FRONT_TOP_RIGHT(new Vector3f(0.5f, 0.5f, -0.5f)),
    BACK_TOP_LEFT(new Vector3f(-0.5f, 0.5f, 0.5f)),
    BACK_TOP_RIGHT(new Vector3f(0.5f, 0.5f, 0.5f)),
    FRONT_BOTTOM_LEFT(new Vector3f(-0.5f, -0.5f, -0.5f)),
    FRONT_BOTTOM_RIGHT(new Vector3f(0.5f, -0.5f, -0.5f)),
    BACK_BOTTOM_LEFT(new Vector3f(-0.5f, -0.5f, 0.5f)),
    BACK_BOTTOM_RIGHT(new Vector3f(0.5f, -0.5f, 0.5f));

    private Vector3f position;

    VerticeNeighbor(Vector3f position) {
        this.position = position;
    }

    public Vector3f getVector3i() {
        return position;
    }
}