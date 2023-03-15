package commands;

import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@Getter
@Setter
public class PersoLolCommand implements BotCommand {

    final String commandName = "persolol";
    final String description = ":white_small_square: !" + commandName + ": Hace equipos random y los mueve a sus salas correspondientes";

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        ArrayList<String> nombresJugadores = new ArrayList<>();
        ArrayList<Integer> randomNumbers = new ArrayList<>();
        ArrayList<String> nombresJugadores2 = new ArrayList<>();
        Random random = new Random();
        List<Member> jugadoresCustom;
        VoiceChannel voiceChannel1 = event.getMember().getVoiceState().getChannel().asVoiceChannel();
        jugadoresCustom = voiceChannel1.getMembers();

        for (Member jugador : jugadoresCustom) {
            nombresJugadores.add(jugador.getEffectiveName());
        }

        if (nombresJugadores.size() < 10) {
            nombresJugadores = fillWithRandoms(nombresJugadores);
        }

        for (int i = 0; i < 5; i++) {
            int number = random.nextInt(9 + 1);
            if (randomNumbers.contains(number)) {
                i--;
            } else {
                randomNumbers.add(number);
            }
        }
        for (Integer number : randomNumbers) {
            nombresJugadores2.add(nombresJugadores.get(number));
        }
        nombresJugadores.removeAll(nombresJugadores2);
        event.getChannel().sendMessage("Team Nattys: " + nombresJugadores).submit();
        event.getChannel().sendMessage("Team Chuzos: " + nombresJugadores2).submit();

        ArrayList<String> finalNombresJugadores = nombresJugadores;
        Thread thread = new Thread(() -> {
            event.getChannel().sendMessage("La partida empieza en: 9").submit();
            for (int i = 10; i > 0; i--) {
                event.getChannel().editMessageById(event.getChannel().getLatestMessageId(), "La partida empieza en: " + (i - 1)).submit();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            VoiceChannel voiceChannel = event.getGuild().getVoiceChannelById("585590707826327578");
            for (String nombre : finalNombresJugadores) {
                for (Member jugador : jugadoresCustom) {
                    if (jugador.getEffectiveName().equals(nombre)) {
                        voiceChannel.getGuild().moveVoiceMember(jugador, voiceChannel).submit();
                    }
                }
            }

            VoiceChannel voiceChannel2 = event.getGuild().getVoiceChannelById("742834147772465162");
            for (String nombre : nombresJugadores2) {
                for (Member jugador : jugadoresCustom) {
                    if (jugador.getEffectiveName().equals(nombre)) {
                        voiceChannel2.getGuild().moveVoiceMember(jugador, voiceChannel2).submit();
                    }
                }
            }
            event.getChannel().sendMessage("Los equipos se han movido").submit();
        });

        thread.start();
    }


    public ArrayList<String> fillWithRandoms(ArrayList<String> jugadoresCustom) {
        int i = 1;
        while (jugadoresCustom.size() < 10) {
            jugadoresCustom.add("Random" + i);
            i++;
        }
        return jugadoresCustom;
    }
}
