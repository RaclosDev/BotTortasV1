package commands;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class OnTwitchCommand implements Command {

    final String commandName = "twitch";

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        MessageChannel channel = event.getGuild().getTextChannelById("585585676141985804");
        User user = event.getUser();
        channel.sendMessage(user.getAsMention() + "está streameando en: https://www.twitch.tv/raclos  @everyone").submit();

    }

    @Override
    public void info(SlashCommandInteractionEvent event) {
        MessageChannel channel = event.getChannel();

        channel.sendMessage(":white_small_square: !" + commandName + ": Con este comando puedes ver que hace el bot").submit();
    }

    @Override
    public String getName() {
        return commandName;
    }
}
