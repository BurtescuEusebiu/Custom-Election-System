# Election System

This project models an election system that allows the management of voters, candidates, voting zones, regions, and the analysis of election results.  
It simulates the voting process, vote collection, and result analysis at different levels (zone, region, and national).  
The system also tracks fraudulent activities during the electoral process.

---

## Features

### Voters
- Add and track voters in different zones.
- Each voter can cast a vote for a candidate.

### Candidates
- Register candidates.
- Track the number of votes each candidate receives.

### Zones
- Organize voting zones (e.g., towns or districts).
- Each zone has its own list of voters and vote count.

### Regions
- Group multiple zones into a region.
- Aggregate voting data at the regional level.

### Election Analysis
- Analyze results at zone, region, or national level.
- Display the total number of votes and their percentage distribution.

### Fraud Detection
- Track and list fraudulent activities within the election process.

---

# Class Overview

## Candidate

Represents a candidate in the election.

### Attributes
- `CNP`: A unique identifier for the candidate.
- `age`: The candidate's age.
- `name`: The candidate's name.

### Methods
- Getters and setters for each attribute.

---

## Voter

Represents an eligible voter in the election.

### Attributes
- `CNP`: A unique identifier for the voter.
- `age`: The voter's age.
- `graceless`: A boolean value indicating whether the voter is "clumsy".
- `voted`: A boolean value indicating whether the voter has already voted.
- `name`: The voter's name.

### Methods
- `vote()`: Marks the voter as having voted.

---

## Zone

Represents a constituency (e.g., a town or district) where voters cast their votes.

### Attributes
- `name`: The name of the zone.
- `totalVotes`: The total number of votes cast in this zone.
- `region`: The region to which the zone belongs.
- `voters`: A map of voters identified by their CNP.
- `votes`: A map of candidates and the number of votes they received in this zone.

### Methods
- `addVoter()`: Adds a voter to the zone.
- `addVote()`: Records a vote for a candidate from a voter.
- `freeVoters()`: Clears the list of voters in the zone.
- `freeVotes()`: Clears the vote count for all candidates in the zone.
- `checkVoter()`: Checks whether a voter exists in the zone.
- `listVoters()`: Lists all voters in the zone.
- `returnVotes()`: Returns the list of votes sorted by candidate.

---

## Region

Represents a region (a group of zones).

### Attributes
- `nrVotes`: The total number of votes in the region.
- `votes`: A map of candidates and the number of votes received in the region.

### Methods
- Getters and setters for each attribute.

---

## Election

Represents the election itself, managing candidates, zones, and the electoral process.

### Attributes
- `id`: A unique identifier for the election.
- `name`: The name of the election.
- `status`: The current state of the election (e.g., not started, ongoing, or finished).
- `candidates`: A map of candidates identified by their CNP.
- `zones`: A map of zones identified by their names.
- `frauds`: A stack used to track fraudulent activities during the election.

### Methods
- Methods to add, delete, and verify candidates, voters, and zones.
- Methods to list candidates and votes.
- Methods to manage the election status (start, end).
- Fraud detection methods to add and list fraud cases.

---

## Analysis

Provides analysis tools to evaluate election results.

### Methods
- `analyzeZone()`: Analyzes the results for a specific zone and displays the number of votes and percentages.
- `analyzeNation()`: Analyzes results for the entire nation by aggregating data from all regions.

---

# Main

The program reads input from the keyboard and uses a `switch` structure to check all possible cases,  
including the default case, which handles situations where the entered command is invalid or unknown.

To improve readability, command implementations were placed in separate functions.

---

# Notes

It would have been possible to implement the `Region` class so that it contains all constituencies (zones).  
However, since the region is minimally used, this modification is not necessary.

### Explanation

Although a more complex structure could have been created, where each region contains all corresponding zones,  
this would not add significant benefits in the current context of the application.

At present, regions are simply logical groupings of zones, and election analysis is primarily performed at the zone level rather than treating regions as fully independent entities.

Therefore, this simpler approach is sufficient and avoids major structural changes, keeping the code manageable and efficient.

---

# Use of Artificial Intelligence (AI)

The use of Artificial Intelligence (AI) was extremely helpful in several aspects of developing this project.  
However, it is important to mention that AI contributed only to a small extent to the implementation — it is estimated that less than 30% of the code was generated or assisted by AI, while over 70% was developed independently.

AI significantly helped in quickly generating getter and setter methods for each class attribute.  
These methods are essential for object manipulation and were generated automatically, saving time and reducing the risk of errors.

Additionally, through code autocompletion, AI suggested or completed standard code fragments such as constructors or access method definitions.

AI was also helpful in learning new classes or techniques, such as sorting a `HashMap`.  
Instead of manually implementing sorting logic, it became easier to understand how to transform a `HashMap` into a `TreeMap` to obtain ordered keys or values.

This approach is efficient for managing structured data, and AI helped clarify how to use these tools effectively.

In conclusion, AI was a helpful tool, but the vast majority of the work was done manually by understanding the requirements and applying programming logic.

---

# Bonus

In this application, the case where a command is invalid or unknown has been handled.

It would also have been possible to verify cases where command arguments were incorrectly written.

Another relevant edge case involves CNP validation, where additional checks could be implemented.  
For example, the first digits — representing personal data (such as date of birth or residence) — could be used to confirm specific criteria, such as verifying a minimum voting age of 18 years.

Regarding command refactoring and application responses, several improvements could be implemented:

- Add the possibility to list all available commands along with their associated arguments.
- Introduce support for managing voters from abroad, including a detailed analysis of diaspora votes.
- When detecting possible fraud, instead of automatically treating it as such, display an error requesting additional confirmation to determine whether it is truly fraud or simply an input mistake.
- The "graceless" attribute could become irrelevant, as it involves information that is difficult to obtain and therefore impractical.
