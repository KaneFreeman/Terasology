package org.terasology.world.block.marchingCubes;

import org.terasology.math.geom.Vector3i;

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

    FRONT_BOTTOM_RIGHT(new Vector3f(0.5, -0.5, -0.5)),  // p
    FRONT_BOTTOM_LEFT(new Vector3i(-0.5, -0.5, -0.5)),  // px
    BACK_BOTTOM_RIGHT(new Vector3i(0.5, -0.5, 0.5)),    // py
    BACK_BOTTOM_LEFT(new Vector3i(-0.5, -0.5, 0.5)),    // pxy
    FRONT_TOP_RIGHT(new Vector3i(0.5, 0.5, -0.5)),      // pz
    FRONT_TOP_LEFT(new Vector3i(-0.5, 0.5, -0.5)),      // pxz
    BACK_TOP_RIGHT(new Vector3i(0.5, 0.5, 0.5)),        // pyz
    BACK_TOP_LEFT(new Vector3i(-0.5, 0.5, 0.5));        // pxyz

    private Vector3i vector3iDir;

    Vertice(Vector3i position) {
        this.position = position;
    }

    public Vector3i getVector3i() {
        return vector3iDir;
    }
}