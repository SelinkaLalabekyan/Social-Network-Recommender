https://docs.google.com/document/d/16X5Xgm3pj_vIk31I2iXi7CjovtsY45AfY4RN8E5Y14Y/edit?usp=sharing

1. Problem Description and Requirements Analysis

1.1 Problem Description

In modern digital environments, social networking platforms such as Facebook and LinkedIn play a crucial role in connecting individuals. One of the most widely used features of these platforms is the “People You May Know” recommendation system, which suggests potential new connections based on existing relationships.
The core challenge behind such systems lies in identifying meaningful connections between users who are not directly linked. This problem can be effectively modeled using graph theory, where users are represented as nodes and friendships are represented as edges in an undirected graph.
The objective of this project is to design and implement a friend recommendation system that suggests potential connections for a given user based on mutual friends. The underlying assumption is that users who share a higher number of common connections are more likely to know each other in real life or have similar interests.
To achieve this, the social network is represented as an adjacency list-based graph. The system explores the network structure using the Breadth-First Search (BFS) algorithm to identify users within two levels of connection (friends-of-friends). Among these candidates, only users who are not already directly connected to the target user are considered.
Each candidate is then evaluated based on the number of mutual friends shared with the target user. The candidates are ranked in descending order of this score, and the top five recommendations are returned. Additionally, the system analyzes the overall structure of the network by detecting connected components, which represent isolated groups of users.
This project demonstrates how classical graph algorithms can be applied to solve real-world problems in social networks, providing both efficient and interpretable recommendation results.


1.2 Functional Requirements
The system must satisfy the following functional requirements:
• The system must load a social network dataset from a CSV file, where each line represents a connection between two users.
• The system must represent the social network as an undirected graph using an adjacency list structure.
• The system must allow the selection of a target user for whom recommendations will be generated.
• The system must verify whether the selected user exists in the dataset and handle invalid input appropriately.
• The system must perform a Breadth-First Search (BFS) traversal starting from the target user, limited to a maximum depth of two levels.
• The system must identify candidate users who are reachable within two hops but are not directly connected to the target user.
• The system must compute the number of mutual friends between the target user and each candidate.
• The system must rank candidates based on the number of mutual friends in descending order.
• The system must return and display the top five recommended users.
• The system must detect and display all connected components within the graph.
• The system must provide an alternative similarity measure (Jaccard similarity) for comparison purposes.
• The system must display results in a clear and structured format through a command-line interface.



1.3 Non-Functional Requirements
In addition to functional requirements, the system must satisfy several non-functional requirements to ensure efficiency, scalability, and usability.
First, the system must be efficient in terms of time complexity. The use of adjacency lists and the Breadth-First Search algorithm ensures that the graph traversal operates in linear time relative to the number of vertices and edges, i.e., O(V + E).
Second, the system must be scalable and capable of handling larger datasets without significant performance degradation. The chosen data structures minimize memory usage and allow efficient access to user connections.
Third, the system must be reliable and robust. It should handle edge cases such as users with no connections, users not present in the dataset, and fully connected graphs without crashing or producing incorrect results.
Finally, the system must be user-friendly. The command-line interface should provide clear instructions and display outputs in a readable tabular format, allowing users to easily interpret the recommendation results.










1.4 Recommendation Rules
The recommendation process in this system is governed by a set of logical rules that define how candidate users are selected and ranked.
Only users who are within two levels of connection from the target user are considered as potential candidates. This ensures that recommendations are based on meaningful proximity within the social network.
Users who are already directly connected to the target user are excluded from the recommendation list, as the goal is to suggest new connections rather than existing ones.
The target user is also excluded from the candidate set to prevent self-recommendation.
Each candidate user is assigned a score based on the number of mutual friends shared with the target user. Only candidates with at least one mutual friend are considered valid recommendations.
Candidates are then ranked in descending order based on their mutual friend count. In cases where multiple candidates have the same score, their relative order may depend on implementation details.
Finally, only the top five candidates are returned as recommendations, ensuring that the output remains concise and relevant.







2. Conceptual Design Explanation

   
2.1 Social Network as a Graph
In this project, the social network is modeled using concepts from graph theory. A graph is a mathematical structure consisting of a set of nodes (also called vertices) and edges that connect pairs of nodes.
In the context of this system, each node represents a user, and each edge represents a friendship between two users. Since friendships are mutual, the graph is considered undirected, meaning that if user A is connected to user B, then user B is also connected to user A.
The graph is implemented using an adjacency list representation. In this structure, each user is associated with a list (or set) of their direct friends. This approach is particularly efficient for sparse graphs, such as social networks, where the number of connections is significantly smaller than the maximum possible number of connections.
The adjacency list structure allows for efficient storage and fast lookup of user connections, which is essential for performing graph traversal and computing recommendations.



                
2.2 System Architecture Overview
The system is designed in a modular way, where each component is responsible for a specific functionality. This modular architecture improves code readability, maintainability, and scalability.
The core components of the system include:
• The Graph module, which manages the data structure representing the social network and provides methods for adding users and connections.
• The CSV Loader module, which reads the dataset file and constructs the graph by adding connections between users.
• The BFS module, which performs breadth-first search to identify users within two levels of connection.
• The Recommender module, which applies the recommendation logic by filtering candidates and ranking them based on mutual friends.
• The Components module, which identifies connected components within the graph.
• The Main module, which serves as the entry point of the application and coordinates the execution of all components through a command-line interface.
This layered design ensures a clear separation of concerns, allowing each part of the system to be developed and tested independently.











2.3 Data Representation and Input Format



The input data for the system is provided in the form of a CSV file, where each line represents a connection between two users in the network. Each entry follows a simple format:
UserA, UserB
This indicates that UserA and UserB are friends, and therefore an undirected edge is created between them in the graph.
During the loading process, the system reads each line of the file and adds the corresponding connection to the graph. If a user does not already exist in the graph, they are automatically added.
This approach allows the system to efficiently build the network structure from real or synthetic datasets. It also ensures flexibility, as new data can be easily integrated by modifying or extending the input file.

Sample Input Dataset
matrix_guardian,luna_master
data_king,shadow_engineer
cloud_leader,shadow_guardian
data_weaver,ember_wizard
neo_weaver,nova_hunter
server_guru,gamer_smith
server_leader,vision_leader
test_ninja,matrix_king
core_hunter,quant_hunter
ember_weaver,dev_pilot
ai_researcher,core_wizard
stream_guru,byte_hunter
byte_architect,code_guru
quant_leader,logic_architect
pixel_artist,logic_thinker
core_explorer,ux_leader


2.4 Design Decisions and Justification

Several design decisions were made during the development of this system to ensure efficiency and clarity.
First, an adjacency list was chosen over other graph representations such as adjacency matrices. This decision was motivated by the fact that social networks are typically sparse graphs, where the number of edges is much smaller than the maximum possible number of edges. As a result, adjacency lists provide better space efficiency.
Second, the Breadth-First Search (BFS) algorithm was selected for graph traversal. BFS is particularly suitable for this application because it explores the graph level by level, making it ideal for identifying users within a fixed number of hops from the target user.
Third, a modular architecture was adopted to separate concerns and improve maintainability. Each component of the system is responsible for a specific task, which simplifies debugging and future extensions.
Finally, a command-line interface was chosen for simplicity and ease of use. While graphical interfaces can provide a richer user experience, a CLI is sufficient for demonstrating the core functionality of the system.








3. Algorithms and Implementation

   
3.1 Graph Representation

The foundation of the system is the graph data structure used to represent the social network. The graph is implemented using an adjacency list, where each user is associated with a set of their direct connections.
More specifically, the graph is stored as a mapping between user identifiers and sets of neighboring users. This structure ensures that each user can efficiently access their list of friends, and it prevents duplicate connections by relying on set-based storage.
When a new connection between two users is added, both users are inserted into the graph if they do not already exist. Then, each user is added to the other’s adjacency list, ensuring that the graph remains undirected.
This representation offers several advantages. It provides efficient space usage, especially for sparse graphs, and allows constant-time average complexity for checking whether a connection exists between two users. Additionally, it enables fast traversal of neighboring nodes, which is essential for implementing graph algorithms such as Breadth-First Search.
















3.2 Breadth-First Search (BFS)

To identify potential candidates for recommendation, the system employs the Breadth-First Search (BFS) algorithm. BFS is a graph traversal technique that explores nodes level by level, starting from a given source node.
In this implementation, BFS is used to explore the network up to a maximum depth of two levels. The first level corresponds to the direct friends of the target user, while the second level represents friends-of-friends. These second-level nodes form the primary pool of candidates for recommendation.
The algorithm uses a queue to process nodes in a first-in, first-out (FIFO) manner and a set to keep track of visited nodes in order to avoid revisiting the same user multiple times. At each iteration, the algorithm processes all nodes at the current level before moving to the next level, ensuring a clear separation between distances in the graph.
A level counter is maintained to limit the traversal depth to two. Once this limit is reached, the algorithm stops exploring further nodes. The result of this process is a set of users that are reachable within two hops from the target user.
This approach is efficient and well-suited for recommendation systems, as it focuses only on nearby nodes in the graph, which are more likely to be relevant.

                                


3.3 Recommendation Algorithm
The recommendation process is built on top of the results obtained from the BFS traversal. Once the set of users within two hops is identified, the system applies a filtering and ranking procedure to generate meaningful recommendations.
First, the system retrieves the list of direct friends of the target user. Then, from the set of nodes returned by the BFS algorithm, it filters out all users who are already directly connected to the target user, as well as the user themselves.
The remaining users are considered as candidate recommendations. For each candidate, the system computes a score based on the number of mutual friends shared with the target user. This score reflects the strength of the potential connection.
Only candidates with at least one mutual friend are retained. These candidates are then sorted in descending order based on their scores. Finally, the system selects the top five users from this sorted list and returns them as the final recommendations.
This method ensures that recommendations are both relevant and interpretable, as they are directly based on shared social connections.







3.4 Mutual Friend Calculation


The number of mutual friends between two users is a key metric used to evaluate the strength of a potential recommendation. In this system, mutual friends are defined as users who are directly connected to both the target user and the candidate user.
To compute this value, the system retrieves the set of friends for each user and performs a comparison to identify common elements. This is effectively equivalent to computing the intersection of two sets.
The implementation iterates through one set and checks whether each element exists in the other set. For each match, a counter is incremented. The final count represents the total number of mutual friends.
This approach is simple and efficient, particularly due to the use of set data structures, which allow for fast membership checks.













3.5 Jaccard Similarity 
In addition to the mutual friend count, the system implements the Jaccard similarity coefficient as an alternative metric for evaluating the similarity between two users.
The Jaccard similarity is defined as the ratio of the number of common elements to the total number of unique elements in two sets. In this context, it measures the similarity between the friend lists of two users.
This metric provides a normalized score between 0 and 1, where a higher value indicates a greater degree of similarity. Unlike the mutual friend count, which only considers the number of shared connections, the Jaccard similarity also accounts for the total number of connections, providing a more balanced evaluation.
The inclusion of this metric allows for comparison between different recommendation strategies and enhances the analytical capabilities of the system.

                       



3.6 Connected Components Detection
To analyze the overall structure of the social network, the system identifies connected components within the graph. A connected component is a group of users where each user is reachable from any other user within the same group.
The system uses a BFS-based approach to identify these components. It iterates through all users in the graph and, for each unvisited user, performs a BFS traversal to collect all users belonging to the same component.
Each discovered group is stored as a separate list, and the process continues until all users have been visited. The result is a collection of connected components that represent isolated clusters within the network.
This analysis provides insight into the structure of the network, such as identifying isolated users or communities.
                 
                               

                                

4. Dataset Description


   
4.1 Dataset Overview
The dataset used in this project represents a social network in the form of user-to-user connections. It is stored as a CSV file, where each line defines a friendship relationship between two users.
Each entry follows a simple format consisting of two user identifiers separated by a comma. This format allows for easy parsing and efficient construction of the graph structure during the loading phase.
The dataset includes more than 50 users and at least 150 connections, satisfying the project requirements. The data may be either synthetic or derived from realistic naming conventions, but it maintains the structural properties of a real-world social network.
This dataset serves as the foundation for building the graph and performing all subsequent computations, including traversal, recommendation, and component detection.









4.2 Data Format
The input data is organized in a plain text CSV format, where each line represents a bidirectional friendship between two users. The format is as follows:
UserA, UserB
This indicates that UserA is connected to UserB, and vice versa. During the loading process, the system ensures that this relationship is stored symmetrically in the graph.


4.3 Data Loading Process

The dataset is loaded into the system using a dedicated CSV loader module. This module reads the input file line by line and splits each line into two user identifiers based on the comma delimiter.
For each valid pair, the system invokes the graph construction logic to add a connection between the two users. If a user does not already exist in the graph, it is automatically created.
This process continues until the entire file is read, resulting in a fully constructed adjacency list representation of the social network. The loading procedure is efficient and scales linearly with the number of connections in the dataset.






5. System Workflow


5.1 Workflow Description

The overall workflow of the system follows a structured sequence of steps that transform raw input data into meaningful friend recommendations.
First, the system loads the dataset from a CSV file and constructs the graph representation of the social network. Once the graph is built, the user selects a target user for whom recommendations will be generated.
The system then verifies whether the selected user exists in the graph. If the user is not found, an error is handled gracefully, and the process terminates.
If the user exists, the system retrieves their direct friends and performs a Breadth-First Search (BFS) traversal up to a depth of two levels. This step identifies all users who are within two hops of the target user.
Next, the system filters out users who are already directly connected to the target user, as well as the user themselves. The remaining users are treated as candidates for recommendation.
For each candidate, the system computes the number of mutual friends shared with the target user. These candidates are then ranked in descending order based on this score.
The system selects the top five candidates and displays them as recommendations. Finally, the system performs an analysis of the graph by detecting connected components and displaying them to the user.










5.2 Workflow Summary  

The workflow can be summarized as a pipeline where data flows through multiple stages, including loading, validation, traversal, filtering, scoring, and ranking. Each stage contributes to refining the set of potential recommendations and ensuring that the final output is both relevant and meaningful.
This structured approach ensures that the system remains efficient, modular, and easy to understand, while also producing high-quality recommendation results.


6. Testing and Edge Cases

   
6.1 Edge Case Handling
To ensure robustness and reliability, the system has been designed to handle several important edge cases that may arise during execution.
First, the system checks whether the selected user exists in the graph. If the user is not found, the recommendation process is terminated gracefully, and no results are returned.
Second, the system handles cases where a user has no friends. In such situations, the BFS traversal does not discover any additional nodes, and therefore no recommendations are generated.
Third, the system accounts for fully connected graphs, where every user is already connected to every other user. In this case, there are no valid candidates for recommendation, and the system returns an empty result set.
Additionally, the system avoids self-recommendation by explicitly excluding the target user from the candidate list. It also ensures that duplicate candidates are not processed multiple times by using set-based data structures.
These measures ensure that the system behaves predictably and correctly under a wide range of input conditions.



6.2 Testing Strategy

The system was tested using both small and large datasets to validate correctness and performance.
A small test network was used to manually verify the correctness of the BFS traversal, mutual friend calculations, and recommendation results. This allowed for step-by-step validation of the algorithm's behavior.
A larger dataset, containing more than 50 users and 150 connections, was used to evaluate the system under realistic conditions. The results confirmed that the system efficiently generates meaningful recommendations and correctly identifies connected components.
Additional tests were performed to validate edge cases, including scenarios where the user does not exist, has no connections, or is part of a fully connected network.
Overall, the testing process confirmed the correctness, stability, and efficiency of the system.

7. Screenshots and Output
7.1 Recommendation Output

The system provides its output through a command-line interface, displaying recommendations in a structured tabular format.
For a given input user, the system outputs a list of recommended users along with their mutual friend counts and Jaccard similarity scores. This format allows users to easily compare candidates and understand the reasoning behind each recommendation.






           


7.2 Connected Components Output
In addition to recommendations, the system displays the connected components of the graph. Each component is presented as a group of users, indicating clusters within the social network.
This output provides insight into the structure of the network and helps identify isolated groups or communities.



8. Complexity Analysis

   
8.1 BFS Complexity
The Breadth-First Search (BFS) algorithm operates with a time complexity of O(V + E), where V represents the number of users (vertices) and E represents the number of connections (edges).
This efficiency is achieved because each node and each edge is processed at most once during the traversal. The space complexity is O(V), as the algorithm maintains a queue and a visited set.

8.2 Mutual Friend Calculation


The computation of mutual friends between two users involves comparing their respective sets of neighbors. This operation has a time complexity proportional to the size of the smaller set, typically denoted as O(d), where d is the degree of a user.
Thanks to the use of hash-based sets, membership checks are performed in constant time on average, making this operation efficient in practice.

8.3 Recommendation Algorithm Complexity


The overall complexity of the recommendation process includes BFS traversal, candidate filtering, mutual friend computation, and sorting.
The BFS traversal takes O(V + E) time. The mutual friend calculations depend on the number of candidates and their degrees. Finally, sorting the candidates requires O(n log n), where n is the number of candidates.
In practice, since the number of candidates is limited to users within two hops, the algorithm remains efficient even for moderately large networks.

9. Results and Discussion

    
9.1 Results

The system successfully generates friend recommendations based on mutual connections within the social network. The results demonstrate that users with a higher number of shared friends are prioritized, leading to meaningful and intuitive suggestions.
The inclusion of Jaccard similarity provides an additional perspective, allowing for comparison between absolute and normalized similarity measures.

9.2 Discussion	


The results indicate that the mutual friend approach is effective for generating relevant recommendations in small to medium-sized networks. It is simple, interpretable, and computationally efficient.
However, the method has limitations. It does not consider additional factors such as user interests, activity levels, or interaction frequency. Future improvements could incorporate more advanced techniques, such as weighted graphs or machine learning-based recommendation models.
Despite these limitations, the system provides a solid foundation for understanding graph-based recommendation systems.





10. Conclusion

    
This project demonstrates the application of graph theory and algorithm design in solving a real-world problem: friend recommendation in social networks.
By modeling the network as an undirected graph and applying Breadth-First Search, the system efficiently identifies relevant candidates within a limited neighborhood. The use of mutual friend counts provides a simple yet effective ranking mechanism, while the inclusion of Jaccard similarity enhances the analytical capabilities of the system.
The modular design of the system ensures clarity and maintainability, and the implementation successfully handles various edge cases. Additionally, the analysis of connected components offers valuable insight into the structure of the network.
Overall, the project highlights the power of fundamental algorithms in building practical and scalable solutions, and it serves as a strong example of how theoretical concepts can be applied in real-world applications.


