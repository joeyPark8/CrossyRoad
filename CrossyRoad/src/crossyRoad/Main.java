package crossyRoad;

import org.bukkit.Bukkit;
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

        getCommand("cross").setExecutor(this::onCommand);
        getCommand("cross").setTabCompleter(this::onTabComplete);
    }

    @Override
    public void onDisable() {
        System.out.println("CrossyRoad is disabled");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if (command.getName().equalsIgnoreCase("cross")) {
            if (args[0].equalsIgnoreCase("group")) {
                if (args[1].equalsIgnoreCase("add")) {
                    if (!groups.contains(args[2])) {
                        groups.add(args[2]);
                        player.sendMessage("그룹 [" + args[2] + "]을 만들었습니다");
                    }
                    else {
                        player.sendMessage(ChatColor.RED + "그룹 [" + args[2] + "]이 이미 있습니다");
                    }
                }
                else if (args[1].equalsIgnoreCase("remove")) {
                    if (groups.contains(args[2])) {
                        groups.remove(args[2]);
                        player.sendMessage("그룹 [" + args[2] + "]을 없앴습니다");
                    }
                    else {
                        player.sendMessage(ChatColor.RED + "그룹 [" + args[2] + "]을 찾을 수 없습니다");
                    }
                }
                else if (args[1].equalsIgnoreCase("join")) {
                    if (groups.contains(args[2])) {
                        if (args[3].equalsIgnoreCase("@all")) {
                            for (Player i : player.getWorld().getPlayers()) {
                                if (!members.get(args[2]).contains(i)) {
                                    members.get(args[2]).add(i);
                                }
                            }
                            player.sendMessage("[" + args[3] + "]을 그룹 [" + args[2] + "]에 추가 했습니다");
                        }
                        else if (args[3].equalsIgnoreCase("@random")) {
                            Random random = new Random();

                            while (true) {
                                Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
                                Bukkit.getServer().getOnlinePlayers().toArray(players);

                                Player target = players[random.nextInt(players.length)];

                                if (members.get(args[2]).contains(target)) {
                                    members.get(args[2]).add(target);
                                    player.sendMessage("[" + target.getName() + "]을 그룹 [" + args[2] + "]에 추가 했습니다");
                                    break;
                                }
                             }
                        }
                        else if (args[3].equalsIgnoreCase("@local")) {

                        }
                    }
                    else {
                        player.sendMessage(ChatColor.RED + "그룹 [" + args[2] + "]을 찾을 수 없습니다");
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
        if (command.getName().equalsIgnoreCase("cross")) {
            if (args.length == 1) {
                List<String> modes = new ArrayList<>();

                modes.add("group");
            }
        }

        return null;
    }
}
