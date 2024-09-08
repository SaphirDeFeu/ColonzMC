package io.github.saphirdefeu.colonz.assets;

import io.github.saphirdefeu.colonz.serialdata.SerialLocation;
import org.jetbrains.annotations.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Board implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String name;

    private ArrayList<SerialLocation> limits;

    public Board(@NotNull String name) {
        this.name = name;
        this.limits = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<SerialLocation> getLimits() {
        return limits;
    }

    public void addLimit(int x, int z) {
        SerialLocation loc = new SerialLocation(x, 0, z);
        limits.add(loc);
    }
}
