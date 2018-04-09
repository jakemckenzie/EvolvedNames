# EvolvedNames
Here includes the specifications for the assignment and personal notes I used in understanding the assignment.

Imagine a virtual world in which all that exists are strings of characters from the set 

{ A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z, _, -, ’ } 

(The ‘_’ represents the space character.) 

Strings in this world can reproduce new strings and die if they are not fit enough. You will evolve strings in this world until they spell your name. To do this you will create a Genome class which will contain a list of characters from the above set representing a string in your world, and you will create a Population class which will contain a list of Genomes representing all the strings in your world.

From the initial value of A we must ~evolve~ a string into "CHRISTOPHER PAUL MARRIOT" preferrably in fewer than 1000 generations and in less than 3 seconds. A generation is defined as times witch which a day() function is called. day() is called during each breeding cycle.
