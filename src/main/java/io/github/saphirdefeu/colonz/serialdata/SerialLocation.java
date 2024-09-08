package io.github.saphirdefeu.colonz.serialdata;

import org.bukkit.Location;
import org.bukkit.World;

import java.io.Serial;
import java.io.Serializable;

public class SerialLocation implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int x;
    private int y;
    private int z;

    public SerialLocation(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public Location toLocation(World world) {
        return new Location(world, this.x, this.y, this.z);
    }
}
