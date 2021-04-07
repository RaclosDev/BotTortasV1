package commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface Command {
    
    public void execute(MessageReceivedEvent event);
    public void info(MessageReceivedEvent event);
    public String getName();
}
