### Game Play ###
  * Single Player Game Modes
    * Survival
      * This will be the main game mode
      * Survive as long as you possibly can ( what more can we say )
    * Destroy Enemy Bases
      * Destroy enemy base
      * Every level gets harder and there are more enemy units to kill
    * Time Attack
      * Kill as many enemy soldiers within a certain time
  * Multiplayer Game Modes
    * Co-operative
      * Work together to survive as long as possible
    * One-on-one: With "Backup":
      * Each player has "friendly" enemies who don't attack him, only the other player(s)
    * One-on-one: Lone Ranger
      * Enemies go for whoever they like; each player tries to survive as long as possible
      * Players can be killed be enemies and by each other
    * Capture flag
      * first player to capture the flag wins
    * King Of The Hill
      * Stand at a certain spot on the map and collect point
      * First person to reach target points wins
    * Destroy Enemy Base
      * Destroy enemy base to win
      * Player Deploys AI to assist him with defence
    * Death Match
      * Kill opponent certain number of times to win
    * King Of Combo's
      * Biggest combo wins
      * Reach a certain combo limit before other player
      * Have biggest combo within certain time limit
  * Countdown timer  - displays number of kills to next upgrade
  * In-game guide: new units, pickups, etc. are highlighted the first time they appear
    * Can be disabled by user
  * 3D Terrain
    * players and enemies move faster downhill and slower uphill
      * implemented :)
    * grenades etc. roll downhill
    * bullets travel faster downhill, etc.
      * cancelled - then bullet speed would fluctuate with terrain! stick to grenades rolling downhill, constant bullet travel time

### Weapons ###
  * Weapon Skill Upgrade
    * A skill level that will increase the damage that the player deals to enemies
  * XP Upgrade
    * XP will automatically upgrade the player's weapon
  * XP Purchase
    * Purchase certain weapons or grenades with gained XP
  * Combine Weapons
    * Put two weapons together to make a super weapon
  * Create Own Weapons
    * To be implemented later as game advances

### Classes of Enemy ###
  * Various levels of standard enemies
    * Just move towards you and attack, but with differing stats (movement speed, attack damage, strength/armour, etc)
      * implemented :)
  * Bombers (explode when shot, or when near player, causing damage to all nearby units)
    * Player can then shoot bombers in order to destroy other enemies ;)
  * Tsotsies
    * These players rob you of your XP, Health and Weapons

### Classes of Pickup ###
  * Land-mines
    * Possibly only available later in the game as this will probably change the gameplay quite drastically
  * Increased movespeed
  * Increased firing rate
  * Increased clip size
  * Increased weapon base damage (global vs localised - ie. does it affect all weapons?)
  * Regeneration
    * Regenerate at 5%, 10% and 25%, health every 10 seconds
  * Armour
    * A protective shield reducing 10%, 25%, 50% of damage dealt to player
  * Health
    * Increase the player's health 25%, 50%, 100%
  * Weapons
    * Get weapons that will assist in your conquest

### Aliens Bio ###
  * Alien Upgrades
  * Smarter Aliens
    * Shoot At player
    * Aliens can use certain pickups
    * AI possibility of guarding pickups
    * AI flanking player
    * AI trapping player

### Alien Types ###
  * Classifications
    * Assuming linear classifications for the moment; then speed\*damage\*aspeed indicates difficulty. Need to add classification for attack speed later
    * Speed classifications: amble (1), walk (2), trot (3), race (4)
    * Damage classifications: annoy (1), hit (2), hurt (3), maul (4)
    * Attack speed ("aspeed"): slow (1), moderate (2), fast (3)
    * Classification formula: (Average(speed, effective attack speed) x attack damage) + optional gut-feel bonus
|**Type**|Speed |**ASpeed**|Damage|**Other notes**                  |**Classification**|
|:-------|:-----|:---------|:-----|:--------------------------------|:-----------------|
|Critter  |walk  |fast    |annoy |                               |1a3x1 =2        |
|Zombie   |amble |moderate|hit   |                               |1a2x2 =3        |
|Leech    |amble |moderate|hit   |                               |1a2x2 =3        |
|Superfly |race  |moderate|annoy |weaving movements; hard to hit|4a(2-1)x1,+1 =3.5|
|Barbarian|amble |slow    |maul  |                               |1a1x4 =4        |
|Scuttler |walk  |moderate|hurt  |walks sideways, turn to attack |2a(2-1)x3 =4.5  |
|Banshee  |walk  |slow    |annoy |boosts nearby enemies' damage  |2a1x1, +5 = 6.5 |
|Fletcher |trot  |moderate|hurt  |retreat a bit after each attack|3a(2-1)x3 =6    |
|Lemming Leader|walk|moderate|hurt|spawns with followers         |2a2x3=6         |
|Lemming Follower|walk|moderate|hit|follows Lemming Leader       |=leader = 6     |

### Player Bio ###
  * Limited Ammo
  * Gain levels through experience (i.e. killing enemies)
    * Stats increase with experience

### Pickups ###
  * Player Attributes
    * Super Speed – Speed increased by 25%
    * Ultra Speed – Speed increased by 50%
    * Armour – Damage to player reduced
    * Jump - allow player to move in air; take less damage but cannot shoot while airborn. Allows you to escape, but you stop killing the enemies ;)
  * Attack improvements
    * Faster Shooting (i.e. more shots per second)
    * Faster bullet speed
    * Stronger Bullets
    * Larger clip sizes (more bullets before reload)

  * Abilities
    * Regeneration – Restore health 5 HP every 10 seconds
    * Super Regeneration – Restore health 10 HP every 10 seconds
    * Ultra Regeneration – Restore health 20 HP every 10 seconds
    * Stealth
    * Counter Attack - chance to dodge/fight back when attacked
    * Vampirism – Get bit of HP from shooting enemies
    * Bloody Mess – Damage multiplier ( more players u kill without getting shot at = more normal XP `*` killed enemies )
  * Other
    * Health Pickups – Increase heath ( 25% or 50% )
    * Super Health – Health increased to 200%

### Linking Player and Weapon ###
  * Idea on how to draw player and weapon ( including accurate rotate )
    * Simply render weapon image after rendering player, using same matrix transformations as for player

### Performance Improvements ###
testOverlapsWith function (and all others that calculate distance): return straight away if either horizontal or vertical difference is too great, avoiding unnecessary calculations of exact distances