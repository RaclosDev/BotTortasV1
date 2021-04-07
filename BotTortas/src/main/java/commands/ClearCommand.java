package commands;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class ClearCommand implements Command {

    String commandName = "clear";

    @Override
    public void execute(MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        if (args.length == 2) {
            if (Integer.parseInt(args[1]) < 1 || Integer.parseInt(args[1]) > 100) {
                channel.sendMessage("Solo puedes borrar entre 1 y 100 mensajes").submit();
            } else {
                List<Message> messages = channel.getHistory().retrievePast(Integer.parseInt(args[1])).complete();
                channel.purgeMessages(messages);
            }

        } else {
            channel.sendMessage("El formato del comando debe ser: !tclear <numMensajes>").submit();
        }
    }

    @Override
    public void info(MessageReceivedEvent event) {
        event.getChannel().sendMessage(":white_small_square: !" + commandName + ": Borra x mensajes, el formato es: !clear <numMensajes>").submit();
    }

    @Override
    public String getName() {
        return commandName;
    }
}
