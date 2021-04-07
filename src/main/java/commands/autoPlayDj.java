package commands;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class autoPlayDj {

    public static void execute(MessageReceivedEvent event) {
        MessageChannel channel = event.getGuild().getTextChannelById("585605197590560789");
        User user = event.getAuthor();
        String mensaje = event.getMessage().getContentRaw();
        if (!isCommand(mensaje)) {
            channel.sendMessage("-play " + mensaje).submit();
        }
    }

    public static boolean isCommand(String m) {
        if (Character.isLetter(m.charAt(0))) {
            return false;
        }
        return true;
    }
}