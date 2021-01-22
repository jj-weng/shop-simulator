# shop-simulator
A simple discrete event simulator that queues customers and serve them.

Servers are divided into human servers and self-servers. Human servers are allowed to rest but not the self-servers.
Mutiple queues are created for each of the servers.
Customers are divided into normal customers which will join the first available queue and greedy customers, who will always join the shortest queue.
Includes a random generator to randomise arrival and service time, and also the probability of whether a customer is greedy.

Java files are packaged under the branch cs2030/simulator.
