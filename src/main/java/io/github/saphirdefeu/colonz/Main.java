package io.github.saphirdefeu.colonz;

import io.github.saphirdefeu.colonz.assets.Board;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;
import java.util.Collections;

public final class Main extends JavaPlugin {

    private static File PLUGIN_DATA_FOLDER;
    private static JavaPlugin COLONZ_PLUGIN;

    private static Collection<Board> boards;

    @Override
    public void onEnable() {
        COLONZ_PLUGIN = this;
        PLUGIN_DATA_FOLDER = this.getDataFolder();
        if(setupFiles() == 1) this.getServer().getPluginManager().disablePlugin(this);

        CommandFactory.registerCommands();
        boards = Saves.getBoards();
        if(boards == null) Debug.err("Could not retrieve board data - use ColonzMC at your own risk.");
        if(boards == null) boards = Collections.emptyList();

        Debug.log("ColonzMC enabled");
    }

    @Override
    public void onDisable() {
        Debug.log("Disabling ColonzMC");
        Debug.log("Saving data");
        Saves.saveBoards(boards);
    }

    private int setupFiles() {
        if(PLUGIN_DATA_FOLDER == null) return 0;
        if(Files.exists(PLUGIN_DATA_FOLDER.toPath())) return 0;
        try {
            Files.createDirectories(PLUGIN_DATA_FOLDER.toPath());
            return 0;
        } catch (IOException e) {
            String err = Debug.getStackTraceAsString(e);
            Debug.err("Could not setup ColonzMC data folder. Aborting. Details follow:\n", err);
            return 1;
        }
    }

    public static File getPluginDataFolder() {
        return PLUGIN_DATA_FOLDER;
    }

    public static JavaPlugin getColonzPlugin() {
        return COLONZ_PLUGIN;
    }

    public static Collection<Board> getBoards() {
        return boards;
    }

    public static void setBoards(Collection<Board> boards) {
        Main.boards = boards;
    }
}
