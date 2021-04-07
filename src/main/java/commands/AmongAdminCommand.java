package commands;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class AmongAdminCommand implements Command {

    String commandName = "admin";

    @Override
    public void execute(MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        Guild guild = event.getGuild();
        VoiceChannel voiceChannel = event.getGuild().getVoiceChannelById("752595002252722289");
        Role amongAdminRole = guild.getRoleById("752620153686196245");
        List<Member> amongAdminMembers = guild.getMembersWithRoles(amongAdminRole);
        List<Member> members = voiceChannel.getMembers();

        if (event.getMember().getRoles().contains(amongAdminRole)) {
            channel.sendMessage(" Ya eras el administrador para la partida").submit();
        } else {
            for (Member member : amongAdminMembers) {
                guild.removeRoleFromMember(member, amongAdminRole).queue();
                if (!member.equals(event.getMember())) {
                    channel.sendMessage(member.getAsMention() + " ha dejado de administrar la partida").submit();
                }
            }
            guild.addRoleToMember(event.getMember(), amongAdminRole).queue();
            channel.sendMessage(event.getMember().getAsMention() + " Es el nuevo administrador para la partida").submit();
        }
    }


    @Override
    public void info(MessageReceivedEvent event) {
        event.getChannel().sendMessage(
                ":white_small_square: !" + commandName + ": Asigna el rol admin en el chat, para que al mutearte mutee a todos").submit();
    }

    @Override
    public String getName() {
        return commandName;
    }
}
