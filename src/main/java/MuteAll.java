import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class MuteAll extends ListenerAdapter {

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        super.onMessageReceived(event);

        VoiceChannel voiceChannel = event.getGuild().getVoiceChannelById("752595002252722289");
        List<Member> members = null;

        if (event.getMessage().getContentRaw().equals("!play")){
            members = voiceChannel.getMembers();

            members.forEach(member -> {
                if (!member.getVoiceState().isGuildMuted()) {
                    member.mute(true).queue();
                } else {
                    member.mute(false).queue();
                }
            });
            event.getMessage().delete().queue();
            }



        }
    }




