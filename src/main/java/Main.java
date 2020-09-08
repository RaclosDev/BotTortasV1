import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class Main {

    public static String GUILD = null;

    public static void main (String args[]){
        AplicacionGrafica ventana = new AplicacionGrafica();
        JDABuilder jdaBuilder = JDABuilder.createDefault("NzUyNTk2MDQ5Njc5MzUxODM3.X1Z7sA.SD2lBcSPG-cqEzUui7WSQigMKAE");
        JDA jda = null;
        MuteAll muteAll = new MuteAll();
        jdaBuilder.addEventListeners(muteAll);
        jdaBuilder.setActivity(Activity.playing("Moderando Among Us"));

        try {
            jda = jdaBuilder.build();
        } catch (LoginException e) {
            e.printStackTrace();
        }

    }


}
