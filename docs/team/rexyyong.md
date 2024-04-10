# Rex Yong's Project Portfolio Page
## Project: LifeTrack
LifeTrack is a desktop app for students to track their health data, 
optimized for use via a Command Line Interface (CLI). 
It tracks calories, hydration and sleep data for the user, 
while also providing daily recommendations for calorie and hydration intake, 
based on the user's build and gender, as well as their body goals and activity levels.

The program was created using Java. Version control was done using Git.

## My contributions to the project

### New features added and enhancements to existing features
1. **Added the ability to add entries for calories intake. [PR #12](https://github.com/AY2324S2-CS2113-F15-2/tp/pull/12)**
   * What it does: Allows users to add records relating to their calories intake, which includes 
   `DESCRIPTION` of food, amount of `CALORIES`, as well as `DATE`.
   * Testing: Added JUnit tests for the feature as well.

2. **Added date format using Class LocalDate for Class Calories, Hydration and Sleep. [PR #83](https://github.com/AY2324S2-CS2113-F15-2/tp/pull/83)**
   * What it does: Allows users to key in dates into their entries. 
   * Justification: By using the Java LocalDate Class, it allows methods to check if dates keyed
   in by users is valid. It also allows easier comparison of dates among entries. 
   * Highlights: This enhancement affects existing commands and commands to be added in future. It required
   the editing of many other classes to change the `DATE` from type String to type LocalDate.
   * Testing: Edited JUnit tests to follow LocalDate format.

3. **Added `entryID` for calories entries in calories list. [PR #153](https://github.com/AY2324S2-CS2113-F15-2/tp/pull/153)**
   * What it does: Ensures that all entries added by users will be tagged to a unique `entryID`.
   * Justification: This allows users to remove calories entries based on the `entryID`, instead of
   removing entries based on the index of entry in the array list.
   * Highlights: This enhancement affects existing commands and commands to be added in future. It required
   the editing of many other classes to ensure that all entries will be given an `entryID`.

4. **Added feature to sort entries by `DATE`. [PR #161](https://github.com/AY2324S2-CS2113-F15-2/tp/pull/161)**
    * What it does: Ensures that dates of entries are sorted in ascending order.
    * Justification: So that user can see the breakdown of calories entries grouped by `DATE`.

5. **Added feature to print calories list grouped by calories in and calories out. [PR #154](https://github.com/AY2324S2-CS2113-F15-2/tp/pull/154)**
    * What it does: Prints the calories list grouped by calories in and calories out data.
    * Justification: So that user can distinguish which entries are for calories in and calories out.

### Contributions to exception handling
* Added robust exception handling for calories Parser, in order to ensure that program does not crash
    when users type the wrong command to add, delete or list calories entries. [PR #43](https://github.com/AY2324S2-CS2113-F15-2/tp/pull/43)
* Added robust exception handling for hydration features, in order to ensure that program does not crash
    when suers type the wrong command to add, delete or list hydration entries. [PR #50](https://github.com/AY2324S2-CS2113-F15-2/tp/pull/50)

### Contributions to documentation
* **Developer guide**
  * Added implementation details for `calories list` feature. [PR #74](https://github.com/AY2324S2-CS2113-F15-2/tp/pull/74)
* **User guide**
  * Added documentation for features `calories in`, `calories out`, `calories list`. 
  Also added quick links as well as command summary. [PR #100](https://github.com/AY2324S2-CS2113-F15-2/tp/pull/100)

### Contributions to project management
* In charge of organising and documenting project meeting minutes and scheduling project meetings. 
  * Click [here](https://docs.google.com/document/d/1hQchbh4mrso-WWNApsfkhvX7QF_kqfvNnIorwwQwjzU/edit) 
  to view the meeting minutes document. 

### Code contributed 
* (Clink RepoSense link and search for rexyyong to see my code contributed:
[RepoSense link](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-02-23&tabOpen=true&tabType=authorship&tabAuthor=rexyyong&tabRepo=AY2324S2-CS2113-F15-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)