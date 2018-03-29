package org.terasology.world.block.marchingCubes;

import org.terasology.math.geom.Vector3i;

public enum VerticeNeighbor {
    FRONT_TOP_LEFT(new Vector3i(-0.5, 0.5, -0.5)),
    FRONT_TOP_RIGHT(new Vector3i(0.5, 0.5, -0.5)),
    BACK_TOP_LEFT(new Vector3i(-0.5, 0.5, 0.5)),
    BACK_TOP_RIGHT(new Vector3i(0.5, 0.5, 0.5)),
    FRONT_BOTTOM_LEFT(new Vector3i(-0.5, -0.5, -0.5)),
    FRONT_BOTTOM_RIGHT(new Vector3i(0.5, -0.5, -0.5)),
    BACK_BOTTOM_LEFT(new Vector3i(-0.5, -0.5, 0.5)),
    BACK_BOTTOM_RIGHT(new Vector3i(0.5, -0.5, 0.5));

    private Vector3i position;

    VerticeNeighbor(Vector3i position) {
        this.position = position;
    }

    public Vector3i getVector3i() {
        return vector3iDir;
    }
}