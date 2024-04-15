# Mao Yanyu's Project Portfolio Page
## Project: LifeTrack
LifeTrack is a desktop app for students to track their health data,
optimized for use via a Command Line Interface (CLI).
It tracks calories, hydration and sleep data for the user,
while also providing daily recommendations for calorie and hydration intake,
based on the user's build and gender, as well as their body goals and activity levels.

The program was created using Java. Version control was done using Git.

## My contributions to the project

### New features added and enhancements to existing features

1. **Added the ability to delete entries for calories intake. [PR #8](https://github.com/AY2324S2-CS2113-F15-2/tp/pull/8)**
    * What it does: Allows users to delete records relating to their calories intake by index
    * Testing: Added JUnit tests for the feature as well.

2. **Added logger for debugging.** [PR #45](https://github.com/AY2324S2-CS2113-F15-2/tp/pull/45)
    * What it does: Allow coder to better handling the bugs.

3. **Added the ability to add entries for sleep.** [PR #60](https://github.com/AY2324S2-CS2113-F15-2/tp/pull/60)
    * What it does: Allows users to add records relating to their sleep, which includes `Duration` and `Date`
    * Testing: Added JUnit tests for the feature as well.

4. **Added the ability to delete entries for sleep added.** [PR #60](https://github.com/AY2324S2-CS2113-F15-2/tp/pull/60)
    * What it does: Allows users to delete sleep records by id.
    * Testing: Added JUnit tests for the feature as well.

5.**Added the ability to list entries in the sleep list.** [PR #60](https://github.com/AY2324S2-CS2113-F15-2/tp/pull/60)
    * What it does: Allows users to see sleep records by date in the sleep list.
    * Testing: Added JUnit tests for the feature as well.

6.**Added the sleep id for sleep.** [PR #157](https://github.com/AY2324S2-CS2113-F15-2/tp/pull/157)
    * What it does: Give sleep records unique sleep id and can save it into file
    * Testing: Added JUnit tests for the feature as well.


### Contributions to exception handling
* Added robust exception handling for sleep class and lists, in order to ensure that program does not crash
  when users type the wrong command to add, delete or list sleep entries. [PR #16](https://github.com/AY2324S2-CS2113-F15-2/tp/pull/16)
* Added robust exception handling for ParserSleep, in order to ensure that program does not crash
  when users enter invalid commands [PR #60](https://github.com/AY2324S2-CS2113-F15-2/tp/pull/60)

### Contributions to documentation
* **Developer guide**
    * Added implementation details for `Sleep` class. [PR #201](https://github.com/AY2324S2-CS2113-F15-2/tp/pull/201)
    * Ensure consistent formatting throughout developer guide.
    * Generated sequence diagram and class diagram for above feature
* **User guide**
    * Converted User guide from word document to Markdown format for all existing features.
        * Added documentation for features `calories delete`,`sleep add`, `sleep delete`, `sleep list`. [PR #91](https://github.com/AY2324S2-CS2113-F15-2/tp/pull/91)
        * Ensure consistent formatting throughout user guide.

### Contributions to project management


### Code contributed
* [Reposense Link](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=functional-code&since=2024-02-23&tabOpen=true&tabType=zoom&zA=a-wild-chocolate&zR=AY2324S2-CS2113-F15-2%2Ftp%5Bmaster%5D&zACS=106.912839737582&zS=2024-02-23&zFS=&zU=2024-04-15&zMG=false&zFTF=commit&zFGS=groupByRepos&zFR=false)
