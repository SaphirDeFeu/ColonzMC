package io.github.saphirdefeu.colonz.commands;

import io.github.saphirdefeu.colonz.ArgumentSuggest;
import io.github.saphirdefeu.colonz.Debug;
import io.github.saphirdefeu.colonz.Main;
import io.github.saphirdefeu.colonz.assets.Board;
import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BoardCommand implements BasicCommand {
    public static final String name = "colonzboard";
    public static final Collection<String> alias = Collections.emptyList();
    public static final String description = "Board management command";

    private static BoardCommand instance;

    private BoardCommand() {}

    public static @NotNull BoardCommand inst() {
        if (instance == null) {
            instance = new BoardCommand();
        }
        return instance;
    }

    @Override
    public void execute(@NotNull CommandSourceStack stack, @NotNull String[] args) {
        if(args.length == 0) return;

        switch(args[0]) {
            case "create": {
                if(args.length < 2) return;
                String name = args[1];
                Board board = new Board(name);
                int size = Main.getBoards().size();
                LinkedList<Board> allBoards = new LinkedList<>(Main.getBoards());
                boolean hasChanged = allBoards.add(board);
                if(hasChanged)
                    stack.getSender().sendRichMessage(String.format("<gold>Board %s (id %d) successfully created</gold>", name, size));
                else
                    stack.getSender().sendRichMessage(String.format("<red>Board %s could not be created</red>", name));
                Main.setBoards(allBoards);
                break;
            }
            case "addlimit": {
                if(!(args.length >= 6)) return;
                int id;
                int startX;
                int startZ;
                int endX;
                int endZ;
                try {
                    id = Integer.parseInt(args[1]);
                    startX = Integer.parseInt(args[2]);
                    startZ = Integer.parseInt(args[3]);
                    endX = Integer.parseInt(args[4]);
                    endZ = Integer.parseInt(args[5]);
                } catch (NumberFormatException e) {
                    Debug.err(Debug.getStackTraceAsString(e));
                    return;
                }

                boolean alignedX = startX == endX;
                boolean alignedZ = startZ == endZ;
                if(!alignedX && !alignedZ) {
                    stack.getSender().sendRichMessage("<red>Not in a line!</red>");
                    return;
                }
                if(alignedX && alignedZ) {
                    stack.getSender().sendRichMessage("<red>Is a single block!</red>");
                    return;
                }

                LinkedList<Board> boards = new LinkedList<>(Main.getBoards());
                if(boards.size() < id) return;
                Board board = boards.get(id);

                if (alignedX) {
                    int minZ = Math.min(startZ, endZ);
                    int maxZ = Math.max(startZ, endZ);

                    for (int z = minZ; z <= maxZ; z++) {
                        board.addLimit(startX, z);
                        stack.getSender().sendRichMessage(String.format("<green>Found %d %d</green>", startX, z));
                    }
                } else if (alignedZ) {
                    int minX = Math.min(startX, endX);
                    int maxX = Math.max(startX, endX);

                    for (int x = minX; x <= maxX; x++) {
                        board.addLimit(x, startZ);
                        stack.getSender().sendRichMessage(String.format("<green>Found %d %d</green>", x, startZ));
                    }
                }

                break;
            }
            case "list": {
                int page = 0;
                int MAX = 10;
                if(args.length > 1) {
                    try {
                        page = Integer.parseInt(args[1]) - 1;
                        if(page < 0) page = 0;
                    } catch (NumberFormatException e) {
                        Debug.err(Debug.getStackTraceAsString(e));
                    }
                }

                LinkedList<Board> boards = new LinkedList<>(Main.getBoards());
                stack.getSender().sendRichMessage("<rainbow>[ColonzMC]</rainbow> <gold>List of all registered boards:</gold>");
                int size = boards.size();
                int MAX_INDEX = Math.min(size, (page + 1) * MAX);
                for(int i = page * MAX; i < MAX_INDEX; i++) {
                    Board board = boards.get(i);
                    stack.getSender().sendRichMessage(String.format("<yellow>Board %d: %s</yellow>", i, board.getName()));
                }

                stack.getSender().sendRichMessage(String.format("<gold>Page %d</gold>", page + 1));

                break;
            }
        }
    }

    @Override
    public @NotNull Collection<String> suggest(@NotNull CommandSourceStack stack, @NotNull String[] args) {
        Collection<String> subcommandsFirst = List.of("addlimit", "create", "list");

        if(args.length == 0) {
            return subcommandsFirst;
        }

        if(args.length == 1 && !subcommandsFirst.contains(args[0])) {
            return ArgumentSuggest.getCorrespondingStrings(subcommandsFirst, args[0]);
        }

        return BasicCommand.super.suggest(stack, args);
    }

    @Override
    public boolean canUse(@NotNull CommandSender sender) {
        return true;
    }
}
