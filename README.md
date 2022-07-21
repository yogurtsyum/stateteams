# StateTeams
Plugin for assigning teams and handling whitelisting based on files with UUIDs.

## Artificial Whitelist
- If the player is added to the `whitelist.json`, they will always be able to join.
- If the `/whitelist` is on, it will be prioritized over the artificial whitelist.
- If someone does not have a team and is not otherwise whitelisted, they will not be able to join.

## Team Files
Upon installing the plugin, a `teams` folder will be made in the data folder. This takes .txt files with lists of UUIDs.

Example: `Blue.txt`
```
fa8fab26-4f18-41ab-b507-4fd65bf1908f
```

When the user with that UUID joins, this user would:
1. Be assigned to the `Blue` scoreboard team
2. Be added to the `Blue` LuckPerms group
