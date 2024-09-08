package io.github.saphirdefeu.colonz.commands;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

public class Help implements BasicCommand {

    public static final String name = "colonzhelp";
    public static final Collection<String> alias = List.of("");
    public static final String description = "Displays help for ColonzMC";

    private static Help instance;

    private Help() {}

    public static @NotNull Help inst() {
        if (instance == null) {
            instance = new Help();
        }
        return instance;
    }

    @Override
    public void execute(@NotNull CommandSourceStack stack, @NotNull String[] args) {
        String message = "<rainbow>[ColonzMC]</rainbow><br>" +
                "<gold>Automates creation and playing of Colonz, a game made by SaphirDeFeu & sans_psaudo</gold><br>" +
                "<yellow>/colonzboard <addlimit|create|list> [<args>]</yellow>";

        stack.getSender().sendRichMessage(message);
    }

    @Override
    public @NotNull Collection<String> suggest(@NotNull CommandSourceStack stack, @NotNull String[] args) {
        return BasicCommand.super.suggest(stack, args);
    }

    @Override
    public boolean canUse(@NotNull CommandSender sender) {
        return true;
    }
}
