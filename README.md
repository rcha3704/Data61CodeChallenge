# Data61CodeChallenge

# Task description

Brian is a coder, and loves all things to do with code. He has come across some data for a computer science social network. The network data contains people, their friends, and their coding skill. Brian is also very busy, and can’t really meet everybody in the network. Given a person in the network that he would like to meet, he would like to find the most skilled coders that can help him get introduced to that person.

Using this social network dataset, the task is to create a function that given two people in the network, find the shortest path with the strongest coders that connects these two people.

# Implementation Overview

Implemented Dijkstra's Alogorithm to find the shortest path between two people in the network through the strongest coders. Used a min-heap based Priority Queue. Used a HashMap in an adjacency list structure for performance gains. The algorithm returns when the destination is found instead of calculating shortest path to all the users in the graph.

# Run/Build Instructions

1. Please download the dataset and place it in the root directory of the project i.e. Data61Challenge.
2. Please ensure that you have added the json-simple-1.1.jar to the project build path. I have included it with the project.
3. Please go to SocialNetworkTest.java and "Run" the tests.

# Note

Please download the dataset before running the tests (SocialNetworkTest.java).
