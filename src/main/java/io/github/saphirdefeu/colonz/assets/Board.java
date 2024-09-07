package io.github.saphirdefeu.colonz.assets;

import org.bukkit.Material;

import java.io.Serial;
import java.io.Serializable;

public class Board implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final Material limitationBlock;

    public Board(Material limitationBlock) {
        this.limitationBlock = limitationBlock;
    }

    public Material getLimitationBlock() {
        return limitationBlock;
    }
}
