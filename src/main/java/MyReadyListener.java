import commands.*;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.DisconnectEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceSelfMuteEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateOnlineStatusEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.util.List;

public class MyReadyListener extends ListenerAdapter {
    @Override
    public void onReady(ReadyEvent event) {
        System.out.println("I am ready to go!");
    }

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        super.onMessageReceived(event);
        MessageChannel channel = event.getChannel();
        MessageChannel djChannel = event.getGuild().getTextChannelById("585605197590560789");
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (channel.equals(djChannel)) {
            autoPlayDj.execute(event);
        }

        for (Command command : Main.COMMANDS) {
            if ((Main.PREFIX + command.getName()).equals(args[0])) {
                command.execute(event);
            }
        }
    }


    @Override
    public void onGuildVoiceSelfMute(@Nonnull GuildVoiceSelfMuteEvent event) {
        super.onGuildVoiceSelfMute(event);

        Role adminRole = event.getGuild().getRoleById("752620153686196245");
        Member member = event.getMember();

        if (member.getRoles().contains(adminRole)) {    //Si tiene rango adminAmong
            VoiceChannel voiceChannel = event.getGuild().getVoiceChannelById("752595002252722289");
            List<Member> members = voiceChannel.getMembers();

            if (members.contains(member)) {                 //Si el user está en el canal among
                if (member.getVoiceState().isMuted()) {     //Si esta muteado
                    if (member.getVoiceState().isGuildMuted()) {
                        for (Member member2 : members) {
                            member2.mute(false).queue();    //Desmutea a todos
                        }
                    } else {
                        for (Member member2 : members) {
                            member2.mute(true).queue();     //Mutea a todos
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onDisconnect(@Nonnull DisconnectEvent event) {
        super.onDisconnect(event);
        System.out.println("Chao");
    }

    @Override
    public void onGuildMemberJoin(@Nonnull GuildMemberJoinEvent event) {
        super.onGuildMemberJoin(event);

        String userId = event.getMember().getUser().getId();
        Role cuerpoEscombroRole = event.getGuild().getRoleById("592433999058567170");

        try {
            event.getGuild().addRoleToMember(event.getMember(), cuerpoEscombroRole).queue();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
