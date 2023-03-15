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

@Slf4j
public class Main extends ListenerAdapter {

    public static final ArrayList<ListenerAdapter> listeners = new ArrayList<>();
    public static final ArrayList<Command> COMMANDS = new ArrayList<>();

    public static void main(String[] args) {

        commandsBuilder();

        if (args.length < 1) {
            System.out.println("You have to provide a token as first argument!");
            System.exit(1);
        }
        Gui gui = new Gui();
        gui.setVisible(true);

        JDABuilder jdaBuilder = JDABuilder.createDefault(args[0]);
        jdaBuilder.enableIntents(GatewayIntent.GUILD_MEMBERS);
        jdaBuilder.enableIntents(GatewayIntent.MESSAGE_CONTENT);
        setListeners();

        addAllEventListeners(jdaBuilder);

        JDA jda = jdaBuilder
                .addEventListeners(new Main())
                .setActivity(Activity.playing("Type /info"))
                .build();

        // Sets the global command list to the provided commands (removing all others)
        jda.updateCommands().addCommands(
                Commands.slash("ping", "Calculate ping of the bot"),
                Commands.slash("clear", "Clear x msg").addOption(OptionType.INTEGER, "number", "number of msg to delete"),
                Commands.slash("info", "Shows info")
        ).queue();
    }
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        // make sure we handle the right command
        switch (event.getName()) {
            case "ping":
                long time = System.currentTimeMillis();
                event.reply("Pong!") // reply or acknowledge
                        .flatMap(v ->
                                event.getHook().editOriginalFormat("Pong: %d ms", System.currentTimeMillis() - time) // then edit original
                        ).queue(); // Queue both reply and edit
                break;
            case "clear":
                new ClearCommand().execute(event);
                break;
            case "info":
                new InfoCommand().execute(event);
                break;
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

    public static void commandsBuilder() {
        COMMANDS.add(new AmongAdminCommand());
        COMMANDS.add(new InfoCommand());
        COMMANDS.add(new PersoLolCommand());
        COMMANDS.add(new OnTwitchCommand());
        COMMANDS.add(new PlaylistsCommand());
    }
}
