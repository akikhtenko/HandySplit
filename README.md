HandySplit
==========

An Eclipse plugin that enables split and rearrangement of open editors with keyboard shortcuts. 

The current plugin version supports only Eclipse >=4.2 (Juno and higher).

Installation
----------
Use the [update site](http://sourceforge.net/projects/handysplit/files/update/) or drop the [binary] (https://github.com/akikhtenko/HandySplit/raw/master/dist/HandySplit_1.0.3.201309241325.jar) into your Eclipse dropins directory

Available actions
----------

+ `ALT+SHIFT+S, S` Splits an editor with multiple open tabs into two horizontally adjacent sections
+ `ALT+SHIFT+S, M` Moves an active tab into the adjacent section if such exists
+ `ALT+SHIFT+S, C` Clones an active tab into the adjacent section if such exists otherwise creates an adjacent section before cloning
+ `ALT+SHIFT+S, E` Exchanges selected tabs in two adjacent sections
+ `ALT+SHIFT+S, F` Flattens (collapses) the active split editor back into a singular one
