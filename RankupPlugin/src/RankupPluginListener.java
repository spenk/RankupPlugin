import java.util.logging.Logger;


public class RankupPluginListener extends PluginListener{
	Logger log = Logger.getLogger("Minecraft");
	PropertiesFile f = new PropertiesFile("plugins/config/RankupPluginprops.properties");
	PropertiesFile f1 = new PropertiesFile("plugins/config/RankupPluginranks.properties");
	String ghnr;
	String nem;
	String ru;
public void reloadprops(){
	PropertiesFile f = new PropertiesFile("plugins/config/RankupPluginprops.properties");
	ghnr = f.getString("Group_Has_No_Rankup","&cThis group doesnt have an rankup!").replace("&", "§");
	nem = f.getString("Not_Enough_Money","&cYou dont have enough money to preform this action!").replace("&", "§");
	ru = f.getString("Ranked_Up","&2Ranked Up!").replace("&", "§");
	PropertiesFile f1 = new PropertiesFile("plugins/config/RankupPluginranks.properties");
	f1.save();
}
public boolean onCommand(Player player,String[] split){
	if (split[0].equalsIgnoreCase("/rankup")&&player.canUseCommand("/rankup")){
		if (split.length == 1){
		String group = (player.getGroups().length > 0 ? player.getGroups()[0] : etc.getDataSource().getDefaultGroup().Name);
		if (f1.containsKey(group)){
		 String s = f1.getProperty(group);
		 String[] sp = s.split(":");
		 Group g = etc.getDataSource().getGroup(sp[0]);
		 if (g == null){log.info("[Rankup] - There is an invalid group in the properties at "+group); player.sendMessage(ghnr); return true;}
		 Double money = (Double)etc.getLoader().callCustomHook("dCBalance", new Object[] { "Account-Balance", player.getName() });
		 Double price = Double.parseDouble(sp[1]);
		 if (money < price){player.sendMessage(nem);return true;}
		 player.setGroups(new String[] { g.Name });
		 etc.getDataSource().modifyPlayer(player);
		 etc.getLoader().callCustomHook("dCBalance", new Object[] { "Account-Withdraw", player.getName(), Double.valueOf(price)});
		 player.sendMessage(ru);
		 etc.getServer().messageAll("§4"+player.getName()+" §2Is RankedUp to §4"+g.Name);
		 return true;
		}else{
			player.sendMessage(ghnr);
			return true;
		}
	}
		if (split.length == 2){
			if (split[1].equals("price")){
				String group = (player.getGroups().length > 0 ? player.getGroups()[0] : etc.getDataSource().getDefaultGroup().Name);
				if (f1.containsKey(group)){
				 String s = f1.getProperty(group);
				 String[] sp = s.split(":");
				 Group g = etc.getDataSource().getGroup(sp[0]);
				 if (g == null){log.info("[Rankup] - There is an invalid group in the properties at "+group); player.sendMessage(ghnr); return true;}
				player.sendMessage("§2The price to rankup to group §4"+sp[0]+"§2 is §4"+sp[1]);
				return true;
			}else{
				player.sendMessage(ghnr);
				return true;
			}
		}
}
}
	return false;
}
}
