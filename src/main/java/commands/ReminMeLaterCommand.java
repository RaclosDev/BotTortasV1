package commands;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;

public class ReminMeLaterCommand implements Command {

    String commandName = "reminder";

    @Override
    public void execute(MessageReceivedEvent event) {
        MessageChannel channel = event.getGuild().getTextChannelById("585585676141985804") ;
        User user = event.getAuthor();

            // Creates a variable equal to the channel that the user is in.
            VoiceChannel connectedChannel = event.getMember().getVoiceState().getChannel();
            // Checks if they are in a channel -- not being in a channel means that the variable = null.
            if(connectedChannel == null) {
                channel.sendMessage("You are not connected to a voice channel!").queue();
                return;
            }



            // Gets the audio manager.
            AudioManager audioManager = event.getGuild().getAudioManager();
            // Connects to the channel.
            audioManager.openAudioConnection(connectedChannel);
            // Obviously people do not notice someone/something connecting.
            channel.sendMessage("Prueba reminder").queue();



            //https://github.com/sedmelluq/LavaPlayer#usage
        AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerRemoteSources(playerManager);
        AudioPlayer player = playerManager.createPlayer();
        //TrackScheduler trackScheduler = new TrackScheduler(player);
        //player.addListener(trackScheduler);
    }

    @Override
    public void info(MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();

        channel.sendMessage(":white_small_square: !" + commandName + ": Pon un recordatorio para mas adelante").submit();
    }

    @Override
    public String getName() {
        return commandName;
    }
}
