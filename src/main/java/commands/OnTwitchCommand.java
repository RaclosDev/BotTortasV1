package commands;

import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
@Getter
@Setter
public class OnTwitchCommand implements BotCommand {

    final String commandName = "twitch";
    final String description = ":white_small_square: !" + commandName + ": Con este comando puedes ver que hace el bot";

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        MessageChannel channel = event.getGuild().getTextChannelById("585585676141985804");
        User user = event.getUser();
        channel.sendMessage(user.getAsMention() + "está streameando en: https://www.twitch.tv/raclos  @everyone").submit();

    }

}
