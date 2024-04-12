# Paturi Karthik's Project Portfolio Page
## Project: LifeTrack
LifeTrack is a desktop app for students to track their health data,
optimized for use via a Command Line Interface (CLI).
It tracks calories, hydration and sleep data for the user,
while also providing daily recommendations for calorie and hydration intake,
based on the user's build and gender, as well as their body goals and activity levels.

The program was created using Java. Version control was done using Git.

## My contributions to the project

### New features added and enhancements to existing features
1. **Added a `User` Class to store the details of the user of the app and calculate their daily calorie goal. [PR #73](https://github.com/AY2324S2-CS2113-F15-2/tp/pull/73)**
    * What it does: Allows users to set up their details such as their `NAME`, `HEIGHT`, `WEIGHT`, `AGE`, `SEX`, `EXERCISE LEVELS` and `GOALS`.
      Using, these information, the user's calorie goal is also calculated and stored in `caloriesRequired`. Using the `user progress` command, users can track the percentage of calories and hydration they have consumed for the day. [PR #X](xxxx)
    * Testing: Add JUnit tests for the feature (future plans).

2. **Added `user update` and `user details` functions for users to quickly glance at and update their details. [PR #171](https://github.com/AY2324S2-CS2113-F15-2/tp/pull/171)**
    * What it does: `user update` allows users to update their details one field at a time, while `user details` allows user to view all their details at once.
    * Testing: Add JUnit tests for the feature (future plans).

3. **Added `UI` class for overall handling of user commands. [PR #52](https://github.com/AY2324S2-CS2113-F15-2/tp/pull/52), [PR #58](https://github.com/AY2324S2-CS2113-F15-2/tp/pull/58)**
    * What it does: Redirects commands from user to the relevant methods.
    * Justification: Having a separate class for `UI` ensures that the code is well factored out and that changes to the UI do not need to be made in multiple files. .

4. **Added `UserUI` class to handle all System messages regarding the `User` class. [PR #108](https://github.com/AY2324S2-CS2113-F15-2/tp/pull/108)**
    * What it does: Ensures that all messages from the System are factored out and consolidated into a single class for code clarity.

5. **Added a `search` feature to sieve through `calorieList` and `hydrationList` to find entries based on a given keyword.(future plans)**
    * What it does: Prints the calories list/ hydration list based on the keyword that was searched.
    * Justification: So that users have an easier time finding certain entries from the lists, instead of looking through the full list.

### Contributions to exception handling
* Added robust exception handling for `ParserUser`, in order to ensure that program does not crash
  when users give incomplete commands, invalid commands, missing fields, impractical entries or entries which are out of the range of LifeTrack. [PR #89](https://github.com/AY2324S2-CS2113-F15-2/tp/pull/89)
* Contributed to the robust exception handling for calorie features, in order to ensure that program does not crash
  when users type the wrong command to add calorie entries. [PR #33](https://github.com/AY2324S2-CS2113-F15-2/tp/pull/33)

### Contributions to documentation
* **Developer guide**
    * Added implementation details for `User` class and `user progress` feature. [PR #109](https://github.com/AY2324S2-CS2113-F15-2/tp/pull/109)
* **User guide**
    * Added documentation for features `user setup`, `user progress` and `help`. [PR #109](https://github.com/AY2324S2-CS2113-F15-2/tp/pull/109)

### Contributions to project management
* In charge of organising and factoring out code written to ensure high levels of OOP, as well as one of 2 designation PR reviewers to maintain the Github repository.

### Code contributed
* [RepoSense link](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=paturikarthik&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-02-23&tabOpen=true&tabType=authorship&tabAuthor=paturikarthik&tabRepo=AY2324S2-CS2113-F15-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)