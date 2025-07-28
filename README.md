# Wintertodt

A wintertodt script written for the [Inubot client](https://inubot.com/), 
mostly for educational purposes.

* Demonstrates usage of the core query API
* Utilises PaintBinding to automatically generate a paint
* A good example of script design and architecture 
* Shows you how to use TaskScript
* Shows you basic Guice usage for dependency injection
* Makes use of the event listeners provided in the bot, as well as implementing a custom 
  PyromancerEvent
* Strong modelling around the game tick system, using ticks to measure animations, delays and more
* Utilises the builder pattern for configuration of the script 
* Very efficient script in general, but this comes with having strong game knowledge
* Utilises client-sided runescripts to read data from the game as opposed to reading from interfaces

## Notes

This script was made very early on during Inubots lifetime so there are quite a lot of things 
that can now be done better easily

- Use of children tasks
- Better use of loadouts (only quickly hacked them in to fix a bug)
- Rejuvenation potions now exist, the script does not use these
- Priorities are now deprecated, it's better to just add the tasks in the desired order as it's 
  easier to follow

## Features

* Will steal your password (probably)
* Chops bruma roots
* Lights the brazier
* Feeds the brazier
* Supports food
* Fletching
* Walks to the brazier as the game starts
* Hops to wintertodt world
* Banks for more food, withdraws requisite items if needed
* Supports all gangs (west, east, northwest, northeast)

## Planned

* There is a small window when leaving wintertodt where you can't interact with anything for a
  tick or 2. Need to figure out a way to determine if the game is in this state.
* Dodge snowfall
* Fletch 1 log while walking between the roots and brazier (I believe this is 0-time)
* Take required items from crates if not in bank, inventory or equipment
* Solo mode
* Quickstart
* Adding idle tasks (between spawns - bankstanding skills, fletching darts/bolts etc)
* Dragon axe special attack
* PoH and rejuv pool after each kill
* Restock food?

## Usage

There is currently no UI, and it is not released on the store or included in the bot.
Due to this, you'll be required to compile it yourself.
If you have some java knowledge you can also edit the variables in the
[Config](https://github.com/d-o-g/inubot-wintertodt/blob/master/src/main/java/org/rspeer/scripts/wintertodt/domain/config/ConfigDefaults.java)
class to your liking.

## License

[License](LICENSE)