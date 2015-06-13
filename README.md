# MyMapGenerator
Experiment for playing with procedurally generated 2d maps. I downloaded a free tileset from deviantart and edited them a little bit with Gimp to get the right look and feel. There are 6 different tiles - deep water, shallow water, beach, forest, hills, and mountains.

The current version was set up to make sure the GUI is working right. I need to make the tiles smaller - right now they are 60X60 pixels which makes for a map with a small number of tiles. The actual map is generated with a simple random number generator, so it's not an actual "map," but really a random collection of those 6 tiles.

The next step for me after I adjust the tiles is to start playing with an algorithm that will produce a somewhat realistic looking map, where deep water tiles are only next to shallow water tiles, and shallow water is only next to deep water and beach tiles, etc. 
