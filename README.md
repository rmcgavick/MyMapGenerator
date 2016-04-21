# MyMapGenerator
Experiment for playing with procedurally generated 2d maps. I downloaded a free tileset from deviantart and edited them a little bit with Gimp to get the right look and feel. There are 6 different tiles - deep water, shallow water, beach, forest, hills, and mountains.

The current version was set up to make sure the graphic display is working right. I need to make the tiles smaller - right now they are 60X60 pixels which makes for a map with a small number of tiles. The actual map is generated with a simple random number generator, so it's not an actual "map," but really a random collection of those 6 tiles.

I have the display working, with psuedo-random tile placement. The 6 tiles range in height from 0 to 5, where deep water is a 0 and mountains are 5. The tiles are currently placed psuedo-randomly, and the result is not a real "map," but it does have the framework I need to implement a noise function to generate a real 2D height map. I'm starting by researching the Simplex noise function for height map generation.
