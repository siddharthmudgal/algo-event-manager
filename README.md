# algo-event-manager

### About
Algorithmic challenge related to hosting an event and generating invites.

### How to run
Import this as a maven project in your favourite IDE and run the tests/java/com.df.EventManagerTest

### Artifacts

1. src/main/java/com.df.EventManager
    - Class that implements generating invites
2. src/test/java/com.df.EventManagerTest
    - test class that implements test cases for EventManager
    
### Roadmap
1. Command line input
2. Object relationships

### Assumptions
1. No group / person has higher priority than any other

### Time complexity
Best case -> O(n) where n is the number of invitees
Worst case -> O(p*g) where p is the number of people in each group and g is the number of groups

### Over and above
1. Unit tests
2. Github CI/CD pipeline
3. Git managed repo
4. Git workflow