# ludum-dare-36
Ludum Dare 36 jam time! Theme: Ancient Technology

A story about a tiger, some stats, and life getting overly complicated. 

# Running Tiger's Game

Want to see the current progress?

- Install SBT
- Should probably have scala somewhere
- Run `sbt run`
- Look at the grid of wonders!

# Known Issues
- `fatal error C9999: out of memory - internal malloc failed`: this is because I'm in HackyMcKludgeFo mode, and initializing `var`s and `val`s in the draw code, which is stupid. I'll move it elsewhere probably first thing Saturday morning?

# Completed
+ Setup SBT project
+ Added ligGDX 
+ added textures for tiger and locations (cave, prey, mate, plain grass)
+ added grid code to display game layout
+ tiger base stats (satiety, health, energy, intelligence)

# TODO
- tiger base actions (sleep, hunt, mate, observe, move)
- tiger base flavor text
- intelligence benchmarks (carry items, use tools, acquire resources, discover fire, etc)
- tiger intermediate stats (self-esteem, actualization, self-efficacy, depression, anxiety, these are just ideas?)
- tiger intermediate actions (carry, use, craft, skin, chop, plant, capture, feed animal, etc)
- tiger intermediate flavor text
- add textures for advanced tiles (trees, fireplace, farm, animal pen, etc)
- sounds!
- create sense of ennui as the life of the tiger gets more complex, etc (example: basic sleep text for base, but as intermediate/advanced happens, the sleep text changes to something more profound, such as "To sleep, perchance to dream- ay, there's the rub")