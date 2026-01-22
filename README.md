# 📄 **README — Alphabet Snowball Minigame (Spigot Plugin)**

## 🎮 **Overview**

Alphabet Snowball is a Spigot minigame where players throw snowballs at floating letters to score points. Letters respawn, scores are tracked, and the game ends when players run out of snowballs.

The plugin supports multiple players and is designed for minigame servers.

---

## ✨ **Features**

✔ Multiplayer support  
✔ Letters spawn in front of players  
✔ Large letter display (ArmorStand)  
✔ Firework effects  
✔ Scoreboard with player points  
✔ Letter respawn after hit  
✔ Game ends when snowballs run out  
✔ Announces winner  

---

## 🖥 **Requirements**

- Java 8+  
- Spigot/Paper **1.19.x**  
- IntelliJ IDEA (recommended)  
- Maven (build system)

---

## 🧱 **Installation (Dev Setup)**

1. Clone/download project
2. Open in IntelliJ IDEA
3. Build using Maven:

```
mvn clean package
```

4. The compiled plugin .jar will be inside:

```
target/
```

Example:

```
target/AlphabetGame-1.0.jar
```

5. Place the jar file into:

```
Spigot Minecraft Server/plugins/
```

---

## 🕹 **Running the Server**

Folder example:

```
Spigot Minecraft Server/
 ├─ spigot.jar
 ├─ run.bat
 ├─ plugins/
 ├─ world/
 ├─ world_nether/
 ├─ world_the_end/
 ...
```

To start the server:

```
run.bat
```

or:

```
java -jar spigot.jar
```

---

## 🕹 **How To Play**

1. Join Minecraft (1.19.x)
2. Connect to:

```
localhost
```

3. Run:

```
/startgame
```

Players receive snowballs, letters appear, and scoring begins.

Scoreboard shows:

```
Alphabet Game
Player1  4
Player2  6
```

Game ends when players are out of snowballs and a winner is announced.

---

## 🗂 **Commands**

| Command      | Function                          |
|--------------|----------------------------------|
| `/startgame` | Starts the alphabet minigame     |

---

## 🌍 **Optional — Flat World Setup for Better Testing**

1. Stop server
2. Edit `server.properties`:

```
level-type=flat
```

3. Delete old worlds:

```
world/
world_nether/
world_the_end/
```

4. Start server again → new flat world loads

---

## 🧩 **Plugin File Structure**

```
src/main/java/org/example/
│ Main.java
│ GameManager.java
│ Letter.java
│ SnowballListener.java
pom.xml
plugin.yml
```

---

## 📦 **plugin.yml**

```yaml
name: AlphabetGame
main: org.example.Main
version: 1.0
api-version: "1.19"
commands:
  startgame:
    description: Starts the alphabet snowball minigame
```

---

## 🛠 **Future Improvements**

✔ Config file for settings  
✔ Spectator mode  
✔ Word spelling mode  
✔ Team vs Team  
✔ Larger 3D letters  
✔ Countdown start  
✔ Arena regions  
✔ Lobby  
✔ Mongo/SQL stats  

---

## 📜 **License**

Free for personal projects & servers.
