package crossyRoad;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class Main extends JavaPlugin {
    List<String> groups = new ArrayList<>();
    Map<String, List<Player>> members = new HashMap<>();

    @Override
    public void onEnable() {
        System.out.println("CrossyRoad is enabled");
    }

    @Override
    public void onDisable() {
        System.out.println("CrossyRoad is disabled");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("cross")) {
            if (args[0].equalsIgnoreCase("group")) {
                if (args[1].equalsIgnoreCase("add")) {
                    if (!groups.contains(args[2])) {
                        groups.add(args[2]);
                    }
                    else {
                        sender.sendMessage(ChatColor.RED + "[" + args[2] + "] 그룹이 이미 있습니다");
                    }
                }
                else if (args[1].equalsIgnoreCase("remove")) {
                    if (groups.contains(args[2])) {
                        groups.remove(args[2]);
                    }
                    else {
                        sender.sendMessage(ChatColor.RED + "[" + args[2] + "] 그룹을 찾을 수 없습니다");
                    }
                }
                else if (args[1].equalsIgnoreCase("join")) {
                    if (groups.contains(args[2])) {
                        //members.get(args[2]).add()
                    }
                    else {
                        sender.sendMessage(ChatColor.RED + "[" + args[2] + "] 그룹을 찾을 수 없습니다");
                    }
                }
                else if (args[1].equalsIgnoreCase("leave")) {

                }
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }
}
