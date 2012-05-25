import java.io.File;
import java.util.logging.Logger;


public class RankupPlugin extends Plugin{
	  String name = "RankupPlugin";
	  String version = "1.0";
	  String author = "spenk";
	  static Logger log = Logger.getLogger("Minecraft");
	  
	  
public void initialize(){
	RankupPluginListener listener = new RankupPluginListener();
log.info(this.name +" version "+ this.version + " by " +this.author+ " is initialized!");
etc.getLoader().addListener(PluginLoader.Hook.COMMAND, listener, this, PluginListener.Priority.MEDIUM);
File f = new File("plugins/config");
f.mkdir();
listener.reloadprops();
}
public void enable(){
	log.info(this.name + " version " + this.version + " by " + this.author + " is enabled!");
}

public void disable(){
	log.info(this.name + " version " + this.version + " is disabled!");
}
}
