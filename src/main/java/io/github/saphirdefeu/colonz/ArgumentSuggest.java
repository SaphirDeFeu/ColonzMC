package io.github.saphirdefeu.colonz;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.stream.Collectors;

public abstract class ArgumentSuggest {
    public static @NotNull Collection<String> getCorrespondingStrings(@NotNull Collection<String> options, @NotNull String current) {
        return options.stream()
                .filter(option -> option.startsWith(current))
                .collect(Collectors.toList());
    }
}
