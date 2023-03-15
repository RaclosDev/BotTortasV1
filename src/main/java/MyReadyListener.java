import lombok.NonNull;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceSelfMuteEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MyReadyListener extends ListenerAdapter {
    @Override
    public void onReady(@NotNull ReadyEvent event) {
        System.out.println("I am ready to go!");
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

    }


    @Override
    public void onGuildVoiceSelfMute(@NotNull GuildVoiceSelfMuteEvent event) {
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
    public void onGuildMemberJoin(@NonNull GuildMemberJoinEvent event) {
        super.onGuildMemberJoin(event);

        String userId = event.getMember().getUser().getId();
        Role cuerpoEscombroRole = event.getGuild().getRoleById("592433999058567170");

        try {
            event.getGuild().addRoleToMember(event.getMember(), cuerpoEscombroRole).queue();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        MessageChannel channel = event.getGuild().getTextChannelById("585585676141985804");

        channel.sendMessage("Pssssssss  " + event.getMember().getAsMention() + ", me he estado fijando como entrenas, " +
                "y si tomas estas pastillitas seguro que te pones como el suasenaguer ese, la primera es gratis que me dices. ").queue();
        channel.sendMessage(event.getUser().getEffectiveAvatarUrl()).queue();

    }
}
