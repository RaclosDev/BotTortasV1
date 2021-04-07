package commands;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class PlaylistsCommand implements Command {

    String commandName = "playlists";

    @Override
    public void execute(MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        User user = event.getAuthor();
        channel.sendMessage(
                "1: \uD83D\uDDA4⚡GO HARD⚡\uD83D\uDDA4 https://open.spotify.com/playlist/5r4nCKd1RIfLoFezRrWMXy?si=h_Cn1LaQTs-nNMmexXNrXg\n" +
                "2: \uD83D\uDDA4\uD83D\uDE08Dark perreo\uD83D\uDE08\uD83D\uDDA4 https://open.spotify.com/playlist/5P1wrtFsGeQu7KexQnhTGY?si=18i-C3hNQryTJ2UxYRmUdw\n" +
                "3: \uD83D\uDD25\uD83D\uDE4F\uD83C\uDFFC FRESH\uD83D\uDE4F\uD83C\uDFFC\uD83D\uDD25  https://open.spotify.com/playlist/0SOKCxTb8rGLSiwCRd7PS7?si=irl1on6dTUGPXoO7NTiZWw\n" +
                "4: \uD83D\uDE22\uD83D\uDC8ASAD\uD83D\uDC8A\uD83D\uDE22 https://open.spotify.com/playlist/2GODBb90rHawRn5VWNRiuX?si=lYAaYK67SOuRMqntTEMNow\n" +
                "5: \uD83C\uDF0CRave in the Cavern\uD83C\uDF0C https://open.spotify.com/playlist/1JTObb3PRljeFCYFYQxQHf?si=kaXGv_kiS7WD3GNpGGFA2Q\n"
                //"6: \uD83D\uDD25\uD83C\uDF51Reggaeton \uD83C\uDF51\uD83D\uDD25 https://open.spotify.com/playlist/0IqYOLzkgQ1QP0dDirozCx?si=H6AyNvSMSpKOkG2YAOFl1Q\n" +
                //"7: \uD83D\uDD7AOld Party Mix\uD83D\uDD7A https://open.spotify.com/playlist/4LOiWUg5EcbrBiM6k0ymJp?si=JEgj9iKkR6-9JacTRV_k4w\n" +
                //"8: Tipicas https://open.spotify.com/playlist/6PSFjunz9OfFp8l95O4Bd7?si=jTfAa0dsStKK7hauxkrQbw\n" +
                //"9: Himons del Rock/ Rock anthems https://open.spotify.com/playlist/3iwob9Jg3ohdhthz7mmyI7?si=GPzmaFrjSsqIYio92nwSlQ\n"
        ).submit();
        //channel.sendMessage("Que playlist te apetece oir? (numero)\n").submit();

    }

    @Override
    public void info(MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();

        channel.sendMessage(":white_small_square: !" + commandName + ": Despliega una lista de las playlists").submit();
    }

    @Override
    public String getName() {
        return commandName;
    }
}