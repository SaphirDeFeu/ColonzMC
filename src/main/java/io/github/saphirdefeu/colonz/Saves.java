package io.github.saphirdefeu.colonz;

import io.github.saphirdefeu.colonz.assets.Board;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;

public abstract class Saves {

    public static @Nullable Collection<Board> getBoards() {
        Path fullPath = Main.getPluginDataFolder().toPath().resolve("boards.dat").normalize();
        File file = fullPath.toFile();
        if(Files.notExists(fullPath)) {
            try {
                Files.createFile(fullPath);
            } catch (IOException ignored) {}
            return Collections.emptyList();
        }

        try(
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis)
        ) {

            Object obj = ois.readObject();

            if (obj instanceof Collection<?> tempCollection) {

                // Ensure all items in the collection are of type Board
                if (tempCollection.isEmpty() || tempCollection.iterator().next() instanceof Board) {
                    // Cast to Collection<Board> and return
                    return (Collection<Board>) obj;
                }
            }

            // If the object is not of the expected type, return an empty list
            Debug.err("Data format is incorrect or not a Collection<Board>");
            return Collections.emptyList();
        } catch(IOException | ClassNotFoundException e) {
            Debug.err("Failed to load boards data", Debug.getStackTraceAsString(e));
            return null;
        }
    }

    public static void saveBoards(Collection<Board> data) {
        File dataFolder = Main.getPluginDataFolder();

        File file = new File(dataFolder, "boards.dat");
        if(Files.notExists(file.toPath())) return;

        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(data); // Serialize and save the object to the file
            Debug.log("Data saved successfully.");

        } catch (IOException e) {
            e.printStackTrace();
            Debug.err("Failed to save data.");
        }
    }
}
