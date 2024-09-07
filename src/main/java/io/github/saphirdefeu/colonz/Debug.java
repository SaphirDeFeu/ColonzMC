package io.github.saphirdefeu.colonz;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.jetbrains.annotations.NotNull;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;

public abstract class Debug {

    public static void log(@NotNull String ...msg) {
        String message = String.join(" ", msg);
        Main.getColonzPlugin()
                .getLogger()
                .log(Level.INFO, message);
    }

    public static void warn(@NotNull String ...msg) {
        String message = String.join(" ", msg);
        Main.getColonzPlugin()
                .getLogger()
                .log(Level.WARNING, message);
    }

    public static void err(@NotNull String ...msg) {
        String message = String.join(" ", msg);
        Main.getColonzPlugin()
                .getLogger()
                .log(Level.SEVERE, message);
    }

    public static String getStackTraceAsString(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }

}
