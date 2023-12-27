package commands;


import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.Command;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Getter
@Setter
public class InfoCommand implements BotCommand {

    final String commandName = "info";
    final String description = commandName + ": Con este comando puedes ver que hace el bot";

    public void execute(SlashCommandInteractionEvent event) {
        List<Command> commands = event.getJDA().retrieveCommands().complete();

        // Crear un StringBuilder para almacenar los nombres y descripciones de los comandos
        StringBuilder commandInfo = new StringBuilder();

        commands.forEach(command -> {
            commandInfo.append("**").append(command.getName()).append("** - ").append(command.getDescription()).append("\n");
        });

        // Construir el mensaje embed con los nombres y descripciones de los comandos
        EmbedBuilder embed = new EmbedBuilder()
                .setTitle("Lista de comandos")
                .setDescription(commandInfo.toString())
                .setColor(Color.BLUE); // Puedes cambiar el color si lo deseas

        // Enviar el mensaje embed como respuesta al evento
        event.replyEmbeds(embed.build()).queue();
    }
}
