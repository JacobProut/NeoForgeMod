package com.jacobpmods.util.glm;

import net.minecraft.core.BlockPos;

public class Vector3 {
    public double x;
    public double y;
    public double z;

    // Constructor
    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // Method to floor the vector's coordinates to obtain block positions
    public BlockPos toBlockPos() {
        return new BlockPos((int) Math.floor(x), (int) Math.floor(y), (int) Math.floor(z));
    }

    // You can add more vector operations if needed (e.g., add, subtract, etc.)
}