package org.terasology.world.block.marchingCubes;

import org.terasology.math.geom.Vector3f;
import java.util.ArrayList;
import java.util.Map;

public class MarchingCubes {
    public static float isoLevel = 0.5f;

    private static Vector3f lerp(Vector3f vec1, Vector3f vec2, float alpha){
        return new Vector3f(vec1.x + (vec2.x - vec1.x) * alpha, vec1.y + (vec2.y - vec1.y) * alpha, vec1.z + (vec2.z - vec1.z) * alpha);
    }

    public static Vector3f[] marchingCubes(Vector3f position, Map<Vertice, Float> verticeValues) {
        ArrayList<Vector3f> vertices = new ArrayList<>();
        // Actual position along edge weighted according to function values.
        Vector3f[] vertList = new Vector3f[12];

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

        // Voxel intensities
        float value0 = verticeValues.get(Vertice.FRONT_BOTTOM_RIGHT), // p
            value1 = verticeValues.get(Vertice.FRONT_BOTTOM_LEFT),    // px
            value2 = verticeValues.get(Vertice.BACK_BOTTOM_RIGHT),    // py
            value3 = verticeValues.get(Vertice.BACK_BOTTOM_LEFT),     // pxy
            value4 = verticeValues.get(Vertice.FRONT_TOP_RIGHT),      // pz
            value5 = verticeValues.get(Vertice.FRONT_TOP_LEFT),       // pxz
            value6 = verticeValues.get(Vertice.BACK_TOP_RIGHT),       // pyz
            value7 = verticeValues.get(Vertice.BACK_TOP_LEFT);        // pxyz

        // Voxel is active if its intensity is above isolevel
        int cubeindex = 0;
        if (value0 > isoLevel) cubeindex |= 1;
        if (value1 > isoLevel) cubeindex |= 2;
        if (value2 > isoLevel) cubeindex |= 8;
        if (value3 > isoLevel) cubeindex |= 4;
        if (value4 > isoLevel) cubeindex |= 16;
        if (value5 > isoLevel) cubeindex |= 32;
        if (value6 > isoLevel) cubeindex |= 128;
        if (value7 > isoLevel) cubeindex |= 64;

        // Fetch the triggered edges
        int bits = TablesMC.MC_EDGE_TABLE[cubeindex];

        // If no edge is triggered... return
        if (bits == 0) {
            return vertices.toArray(new Vector3f[vertices.size()]);
        };

        // Interpolate the positions based od voxel intensities
        float mu = 0.5f;

        // bottom of the cube
        if ((bits & 1) != 0) {
            mu = (float) ((isoLevel - value0) / (value1 - value0));
            vertList[0] = lerp(position, new Vector3f(position.x + 1, position.y, position.z), mu);
        }
        if ((bits & 2) != 0) {
            mu = (float) ((isoLevel - value1) / (value3 - value1));
            vertList[1] = lerp(new Vector3f(position.x + 1, position.y, position.z), new Vector3f(position.x + 1, position.y + 1, position.z), mu);
        }
        if ((bits & 4) != 0) {
            mu = (float) ((isoLevel - value2) / (value3 - value2));
            vertList[2] = lerp(new Vector3f(position.x, position.y + 1, position.z), new Vector3f(position.x + 1, position.y + 1, position.z), mu);
        }
        if ((bits & 8) != 0) {
            mu = (float) ((isoLevel - value0) / (value2 - value0));
            vertList[3] = lerp(position, new Vector3f(position.x, position.y + 1, position.z), mu);
        }
        // top of the cube
        if ((bits & 16) != 0) {
            mu = (float) ((isoLevel - value4) / (value5 - value4));
            vertList[4] = lerp(new Vector3f(position.x, position.y, position.z + 1), new Vector3f(position.x + 1, position.y, position.z + 1), mu);
        }
        if ((bits & 32) != 0) {
            mu = (float) ((isoLevel - value5) / (value7 - value5));
            vertList[5] = lerp(new Vector3f(position.x + 1, position.y, position.z + 1), new Vector3f(position.x + 1, position.y + 1, position.z + 1), mu);
        }
        if ((bits & 64) != 0) {
            mu = (float) ((isoLevel - value6) / (value7 - value6));
            vertList[6] = lerp(new Vector3f(position.x, position.y + 1, position.z + 1), new Vector3f(position.x + 1, position.y + 1, position.z + 1), mu);
        }
        if ((bits & 128) != 0) {
            mu = (float) ((isoLevel - value4) / (value6 - value4));
            vertList[7] = lerp(new Vector3f(position.x, position.y, position.z + 1), new Vector3f(position.x, position.y + 1, position.z + 1), mu);
        }
        // vertical lines of the cube
        if ((bits & 256) != 0) {
            mu = (float) ((isoLevel - value0) / (value4 - value0));
            vertList[8] = lerp(position, new Vector3f(position.x, position.y, position.z + 1), mu);
        }
        if ((bits & 512) != 0) {
            mu = (float) ((isoLevel - value1) / (value5 - value1));
            vertList[9] = lerp(new Vector3f(position.x + 1, position.y, position.z), new Vector3f(position.x + 1, position.y, position.z + 1), mu);
        }
        if ((bits & 1024) != 0) {
            mu = (float) ((isoLevel - value3) / (value7 - value3));
            vertList[10] = lerp(new Vector3f(position.x + 1, position.y + 1, position.z), new Vector3f(position.x + 1, position.y+ 1, position.z + 1), mu);
        }
        if ((bits & 2048) != 0) {
            mu = (float) ((isoLevel - value2) / (value6 - value2));
            vertList[11] = lerp(new Vector3f(position.x, position.y + 1, position.z), new Vector3f(position.x, position.y + 1, position.z + 1), mu);
        }

        // construct triangles -- get correct vertices from triTable.
        int i = 0;
        // "Re-purpose cubeindex into an offset into triTable."
        cubeindex <<= 4;

        while (TablesMC.MC_TRI_TABLE[cubeindex + i] != -1) {
            int index1 = TablesMC.MC_TRI_TABLE[cubeindex + i];
            int index2 = TablesMC.MC_TRI_TABLE[cubeindex + i + 1];
            int index3 = TablesMC.MC_TRI_TABLE[cubeindex + i + 2];

            // Add triangles vertices normalized with the maximal possible value
            vertices.add(vertList[index3]);
            vertices.add(vertList[index2]);
            vertices.add(vertList[index1]);

            i += 3;
        }

        return vertices.toArray(new Vector3f[vertices.size()]);
    }
}