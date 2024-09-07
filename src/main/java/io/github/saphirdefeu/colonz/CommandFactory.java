package io.github.saphirdefeu.colonz;

import io.github.saphirdefeu.colonz.commands.Help;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;

public abstract class CommandFactory {

    public static void registerCommands() {
        Main.getColonzPlugin()
                .getLifecycleManager()
                .registerEventHandler(LifecycleEvents.COMMANDS, event ->
        {
            final Commands commands = event.registrar();
            commands.register(Help.name, Help.description, Help.alias, Help.inst());

        });
    }

}
