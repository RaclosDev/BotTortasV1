package commands;

import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class InfoCommand implements Command {

    final String commandName = "info";

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        new AmongAdminCommand().info(event);
        new ClearCommand().info(event);
        new InfoCommand().info(event);
        new PersoLolCommand().info(event);
        new PlaylistsCommand().info(event);
    }

    @Override
    public void info(SlashCommandInteractionEvent event) {
        MessageChannel channel = event.getChannel();

        channel.sendMessage(":white_small_square: !" + commandName + ": Con este comando puedes ver que hace el bot, aunque si lo estas leyendo es porque ya lo has usado xd").submit();
    }

    @Override
    public String getName() {
        return commandName;
    }
}
