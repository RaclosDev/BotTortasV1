import commands.*;
import gui.Gui;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.ArrayList;
import java.util.HashMap;

@Slf4j
public class Main extends ListenerAdapter {

    public static final ArrayList<ListenerAdapter> listeners = new ArrayList<>();
    public static final HashMap<String, BotCommand> COMMANDS = new HashMap<>();

    public static void main(String[] args) {

        if (args.length < 1) {
            System.out.println("You have to provide a token as first argument!");
            System.exit(1);
        }
        botCommandsBuilder();
        log.info("Comandos creados");
        Gui gui = new Gui();
        gui.setVisible(true);
        log.info("GUI creada");

        JDABuilder jdaBuilder = JDABuilder.createDefault(args[0]);
        jdaBuilder.enableIntents(GatewayIntent.GUILD_MEMBERS);
        jdaBuilder.enableIntents(GatewayIntent.MESSAGE_CONTENT);
        setListeners();
        addAllEventListeners(jdaBuilder);
        log.info("Listeners creados");
        JDA jda = jdaBuilder
                .addEventListeners(new Main())
                .setActivity(Activity.playing("Type /info"))
                .build();
        jda.updateCommands().addCommands(
                Commands.slash("ping", "Calculate ping of the bot"),
                Commands.slash("clear", "Clear x msg").addOption(OptionType.INTEGER, "number", "number of msg to delete"),
                Commands.slash("info", "Shows info")
        ).queue();
        log.info("Slash Commands creados");
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        try {
            switch (event.getName()) {
                case "ping":
                    log.info("Se hallamado a /ping");
                    COMMANDS.get("ping").execute(event);
                    break;
                case "clear":
                    log.info("Se hallamado a /clear");
                    COMMANDS.get("clear").execute(event);
                    break;
                case "info":
                    log.info("Se hallamado a /info");
                    COMMANDS.get("info").execute(event);
                    break;
            }
        } catch (NullPointerException e) {
            log.error("No se ha encontrado el comando: " + event.getName() + " en COMMANDS");
        }
    }

    public static void setListeners() {
        listeners.add(new MyReadyListener());
    }

    public static void addAllEventListeners(JDABuilder jdaBuilder) {
        for (ListenerAdapter listener : listeners) {
            jdaBuilder.addEventListeners(listener);
        }
    }

    public static void botCommandsBuilder() {
        COMMANDS.put("admin", new AmongAdminCommand());
        COMMANDS.put("info", new InfoCommand());
        COMMANDS.put("persolol", new PersoLolCommand());
        COMMANDS.put("twitch", new OnTwitchCommand());
        COMMANDS.put("playlist", new PlaylistsCommand());
        COMMANDS.put("clear", new ClearCommand());
        COMMANDS.put("ping", new PingCommand());
        COMMANDS.forEach((key, value) -> log.info("Created command: " + value.getCommandName()));
    }
}
