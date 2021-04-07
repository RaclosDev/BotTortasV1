import commands.*;
import gui.Gui;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;
import javax.swing.*;
import java.util.ArrayList;

public class Main extends ListenerAdapter {

    public static ArrayList<ListenerAdapter> listeners = new ArrayList<>();
    public static String PREFIX = "!";
    public static ArrayList<Command> COMMANDS = new ArrayList<>();

    public static void main(String args[]) throws LoginException {

        JDA jda = null;
        commandsBuilder();

        if (args.length < 1) {
            final JPanel panel = new JPanel();
            JOptionPane.showMessageDialog(panel, "Te he dicho que es el de abajo puto subnormal", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("You have to provide a token as first argument!");
            System.exit(1);
        }
        Gui gui = new Gui();
        gui.setVisible(true);
        JDABuilder jdaBuilder = JDABuilder.createDefault(args[0]);
        jdaBuilder.enableIntents(GatewayIntent.GUILD_MEMBERS);
        setListeners();
        addAllEventListeners(jdaBuilder);
        jdaBuilder.setActivity(Activity.playing("type !info"));

        jda = jdaBuilder.build();

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
        COMMANDS.add(new ClearCommand());
        COMMANDS.add(new InfoCommand());
        COMMANDS.add(new PersoLolCommand());
        COMMANDS.add(new OnTwitchCommand());
        COMMANDS.add(new PlaylistsCommand());
        COMMANDS.add(new ReminMeLaterCommand());

    }

    public static int botDisconect() {
        System.out.println("chao");

        return 1;
    }
}
