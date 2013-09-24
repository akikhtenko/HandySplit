HandySplit
==========

An Eclipse plugin the purpose of which is to enable splitting and rearrangement of editors through keyboard shortcuts. 

The current plugin version ([download] (https://github.com/akikhtenko/HandySplit/raw/master/dist/HandySplit_1.0.3.201309241325.jar)) supports only Eclipse >=4.2 (Juno and Kepler).
In order to make it work drop the binary into your dropins eclipse directory

Available actions
----------

+ **ALT+SHIFT+S, S** Splits an editor with multiple open tabs into two horizontally adjacent sections
+ **ALT+SHIFT+S, M** Moves an active tab into the adjacent section if such exists
+ **ALT+SHIFT+S, C** Clones an active tab into the adjacent section if such exists otherwise creates an adjacent section before cloning
+ **ALT+SHIFT+S, E** Exchanges selected tabs in two adjacent sections
+ **ALT+SHIFT+S, F** Flattens (collapses) the active split editor back into a singular one