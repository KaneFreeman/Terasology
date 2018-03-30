package org.terasology.world.block.marchingCubes;

import org.terasology.math.geom.Vector3f;

public enum Vertice {
    // Indices pointing to cube vertices
    //              pyz  ___________________  pxyz
    //                  /|                 /|
    //                 / |                / |
    //                /  |               /  |
    //          pz   /___|______________/pxz|
    //              |    |              |   |
    //              |    |              |   |
    //              | py |______________|___| pxy
    //              |   /               |   /
    //              |  /                |  /
    //              | /                 | /
    //              |/__________________|/
    //             p                     px

    FRONT_BOTTOM_RIGHT(new Vector3f(0.5f, -0.5f, -0.5f)),  // p
    FRONT_BOTTOM_LEFT(new Vector3f(-0.5f, -0.5f, -0.5f)),  // px
    BACK_BOTTOM_RIGHT(new Vector3f(0.5f, -0.5f, 0.5f)),    // py
    BACK_BOTTOM_LEFT(new Vector3f(-0.5f, -0.5f, 0.5f)),    // pxy
    FRONT_TOP_RIGHT(new Vector3f(0.5f, 0.5f, -0.5f)),      // pz
    FRONT_TOP_LEFT(new Vector3f(-0.5f, 0.5f, -0.5f)),      // pxz
    BACK_TOP_RIGHT(new Vector3f(0.5f, 0.5f, 0.5f)),        // pyz
    BACK_TOP_LEFT(new Vector3f(-0.5f, 0.5f, 0.5f));        // pxyz

    private Vector3f position;

    Vertice(Vector3f position) {
        this.position = position;
    }

    public Vector3f getVector3i() {
        return position;
    }
}