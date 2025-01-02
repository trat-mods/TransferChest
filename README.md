<h2 align="center">Welcome to the Wiki page of Transfer Chest</h2>
<p align="center">
<img src="https://user-images.githubusercontent.com/31132987/103446388-43623800-4c7f-11eb-830c-4734d00f8468.png" height="256">
</p>

## Recipe

![transfer_chest_recipe](https://user-images.githubusercontent.com/31132987/103446568-55dd7100-4c81-11eb-9d4e-0a0601c539e8.png)

## Description

The *Transfer Chest* has a common inventory to all players. This means that, differently from Ender
Chests, each player accesses the same inventory. Each player can interact with the inventory at the
same time, allowing for item "teletransportation" among players, any time and in any
dimension.           
Transfer Chests do not interact with hoppers, thus items cannot be automatically inserted and
extracted.

Moreover, the GUI of the Transfer Chest displays the names of the players that currently have the
inventory open, so every time you want to transfer some items with your friends, you can be sure
that no other players are snooping around.

## Serialization

When a world is joined, the inventory is deserialized and stored. The serialization of the inventory
takes place only when the game session (*local/remote server*) stops. This means that, serializing
only once, the mod is very light weight but, if the server crashes, then the serialization may not
take place.
Data are stored in:

Local game session: <code>%appdata%/.minecraft/mods/transfer_chest/-world
name-/transferChest.data</code>

Remote server: <code>.../mods/transfer_chest/-world name-/transferChest.data</code>
