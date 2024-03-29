package commands;

import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

import java.util.List;

@Getter
@Setter
public class ClearCommand implements BotCommand{

    private final String commandName = "BORRA";
    private final String description = ":white_small_square: !" + commandName + ": Borra x mensajes, el formato es: !clear <numMensajes>";

    public void execute(SlashCommandInteractionEvent event) {
        MessageChannel channel = event.getChannel();
        int messagesToDelete = event.getOption("number", OptionMapping::getAsInt);

        if (messagesToDelete < 1 || messagesToDelete > 100) {
            event.reply("Solo puedes borrar entre 1 y 100 mensajes").queue();
        } else {
            List<Message> messages = channel.getHistory().retrievePast(messagesToDelete).complete();
            channel.purgeMessages(messages);
            event.reply("Se han eliminado los ultimos " + messagesToDelete + " mensajes.").queue();
        }
    }
}
