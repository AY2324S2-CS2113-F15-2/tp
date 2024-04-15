# Shawn Pong's Project Portfolio Page
## Project: LifeTrack
LifeTrack is a desktop app for students to track their health data,
optimized for use via a Command Line Interface (CLI).
It tracks calories, hydration and sleep data for the user,
while also providing daily recommendations for calorie and hydration intake,
based on the user's build and gender, as well as their body goals and activity levels.

The program was created using Java. Version control was done using Git.

## My contributions to the project

### New features added and enhancements to existing features

1. **Added the ability to add entries for hydration intake.** [PR #71](https://github.com/AY2324S2-CS2113-F15-2/tp/pull/71)
   * What it does: Allows users to add records relating to their hydration intake, which includes `DESCRIPTION` of 
   drink, `VOLUME` of drink, as well as `DATE`. 
   * Testing: Added JUnit tests for the feature as well.

2. **Added functionality for users to view their caloric and hydration goals** [PR #90](https://github.com/AY2324S2-CS2113-F15-2/tp/pull/90)
   * What it does: Allows users to view their progress with a progress bar with `user progress` function.
   * Justification: This lets users easily view their progress with an easy to comprehend progress bar, so users can 
   know how much more calories or hydration to consume.

3. **Added feature for users to view caloric and hydration goals for current day** [PR #180](https://github.com/AY2324S2-CS2113-F15-2/tp/pull/181)
    * What it does: Users can now view their current daily caloric and hydration intake with `user progress` function,
   instead of the cumulative intake.
    * Justification: As this is a lifestyle tracker, the intent is for users to use it on a day-to-day basis, tracking
   their current daily instake against their goals. They are still able to view their previous history of consumption 
   with `calories list` and `hydration list`.

4. **Added feature to sort hydration entries by DATE.** [PR #181](https://github.com/AY2324S2-CS2113-F15-2/tp/pull/181)
    * What it does: Ensures that dates of hydration entries are sorted in ascending order. 
    * Justification: So that user can see the breakdown of hydration entries grouped by DATE.

### Contributions to exception handling
* Added robust exception handling for hydration class and lists, in order to ensure that program does not crash
  when users type the wrong command to add, delete or list hydration entries. [PR #38](https://github.com/AY2324S2-CS2113-F15-2/tp/pull/38)
* Added robust exception handling for ParserHydration, in order to ensure that program does not crash
  when users enter invalid commands [PR #71](https://github.com/AY2324S2-CS2113-F15-2/tp/pull/71)

### Contributions to documentation
* **Developer guide**
    * Added implementation details for `Hydration` class. [PR #93](https://github.com/AY2324S2-CS2113-F15-2/tp/pull/93)
    * Ensure consistent formatting throughout developer guide.
* **User guide**
    * Converted User guide from word document to Markdown format for all existing features.
      * Added documentation for features `hydration in`, `hydration delete`, `hydration list`. [PR #91](https://github.com/AY2324S2-CS2113-F15-2/tp/pull/91)
      * Ensure consistent formatting throughout user guide.

### Code contributed
* [Reposense Link](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=shawnpong&sort=groupTitle&sortWithin=totalCommits%20dsc&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-02-23&tabOpen=false)
