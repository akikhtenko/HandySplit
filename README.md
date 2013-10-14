HandySplit
==========
![HandySplit Logo](handysplit.jpg)


An Eclipse plugin that enables split and rearrangement of open editors with keyboard shortcuts. 
The current plugin version supports only Eclipse >=4.2 (Juno and higher).

Installation
----------
Use the [update site](http://sourceforge.net/projects/handysplit/files/update/) or drop the [binary] (https://github.com/akikhtenko/HandySplit/raw/master/dist/HandySplit_1.0.5.201310131424.jar) into your Eclipse dropins directory

Available actions
----------

+ `ALT+SHIFT+S, S` Splits an editor with multiple open tabs into two *horizontally* adjacent sections
+ `ALT+CTRL+S, S`  Splits an editor with multiple open tabs into two *vertically* adjacent sections
+ `ALT+SHIFT+S, M` Moves an active tab into the adjacent section if such exists
+ `ALT+SHIFT+S, C` Clones an active tab into the adjacent section if such exists otherwise creates a new *horizontally* adjacent section before cloning
+ `ALT+CTRL+S, C`  Same as above but splits *vertically* if split is needed
+ `ALT+SHIFT+S, E` Exchanges selected tabs in two adjacent sections
+ `ALT+SHIFT+S, F` Flattens (collapses) the active split editor's sections back into one and removes all duplicate tabs (produced by the Clone action) on the same level
+ `ALT+SHIFT+S, T` Toggles the focus between the two sections (active tabs) of the split editor

All actions can be seen in built in Help, created with [Enide](http://enide.github.io/) & [GFM Viewer](https://github.com/satyagraha/gfm_viewer)

[HandySplit plugin](https://github.com/akikhtenko/HandySplit) created by Alexander Kikhtenko