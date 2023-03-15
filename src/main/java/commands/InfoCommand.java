package commands;


import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.Command;

import java.util.ArrayList;
import java.util.stream.Collectors;
@Getter
@Setter
public class InfoCommand implements BotCommand {

    final String commandName = "info";
    final String description = ":white_small_square: !" + commandName + ": Con este comando puedes ver que hace el bot, aunque si lo estas leyendo es porque ya lo has usado xd";

    public void execute(SlashCommandInteractionEvent event) {
        ArrayList<Command> commands = event.getJDA().retrieveCommands().complete().stream().collect(Collectors.toCollection(ArrayList::new));
        commands.forEach(command->command.getDescription());
    }
}
