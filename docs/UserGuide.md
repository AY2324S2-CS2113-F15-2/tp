# User Guide

## Introduction

LifeTrack is a desktop app for students to track their health data, optimized for use via a Command Line Interface (CLI). It tracks calories, hydration and sleep data for the user, while also providing daily recommendations for calorie and hydration intake, based on the user's build and gender, as well as their body goals and activity levels.

## Quick links
- [Quick Start](#quick-start)
- [General](#general)
  - [help](#viewing-help-help)
  - [bye](#exiting-the-program-bye)
- [Calories Tracker](#calories-tracker)
  - [Input calorie Intake](#input-calorie-intake-calories-in)
  - [Input calorie loss](#input-calorie-loss-calories-out)
  - [Listing calorie items](#listing-calorie-items-calories-list)
  - [Deleting a calorie item](#deleting-a-calorie-item-calories-delete)
  - [Searching for a calorie item](#searching-for-a-calorie-item-calories-find)
- [Hydration Tracker](#hydration-tracker)
  - [Input hydration intake](#input-hydration-intake-hydration-in)
  - [Listing hydration items](#listing-hydration-items-hydration-list)
  - [Deleting a hydration item](#deleting-a-hydration-item-hydration-delete)
  - [Searching for a hydration item from hydration list](#searching-for-a-hydration-item-hydration-find)
- [Sleep Tracker](#sleep-tracker)
  - [Input sleeping hours](#input-sleeping-hours-sleep-add)
  - [Listing sleep records](#listing-sleep-records-sleep-list)
  - [Deleting a sleep record](#deleting-a-sleep-record-sleep-delete)
- [User Profile](#user-profile)
  - [Set Up User Profile](#set-up-user-profile-user-setup)
  - [Check User Details](#check-your-users-details-user-details)
  - [Update User Details](#update-your-users-details-user-update)
  - [Check User's daily calories and hydration consumption and your sleep statistics](#check-your-daily-calories-and-hydration-consumption-and-your-sleep-statistics-user-progress)
- [FAQ](#faq)
- [Command Summary](#command-summary)

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `LifeTrack` from [here](https://github.com/AY2324S2-CS2113-F15-2/tp/releases). You may move the JAR file to anywhere in your computer if you wish.
3. Open a terminal window and change directories to where the JAR file is located.
4. Run the command `java -jar LifeTrack.jar` and the application will start running.

[//]: # (## Features )

## General

### Viewing help: `help`
Shows a help message listing the commands available in the application.

**Format:**
`help`

#### Expected output
         -----------------------------------------------------------------------------
	 LifeTrack Command List:
	 - help: Displays a list of available commands and their descriptions.
         -----------------------------------------------------------------------------
	 - calories in <food> c/<calories> d/<date, format:YYYY-MM-DD> m/[carbohydrates, proteins, fats]:
	 Adds a calorie gaining entry into the calories tracker.
	 - calories out <activity> c/<calories> d/<date, format:YYYY-MM-DD>:
	 Adds a calorie burning entry into the calories tracker.
	 - calories list: Displays all entries currently stored in the calorie list.
	 - calories delete <calorie ID>: Deletes the entry at the specified ID from the calorie list.
	 - calories find <keyword>: finds and lists all calorie entries containing the keyword in their description
         -----------------------------------------------------------------------------
	 - hydration in <beverage> v/<volume> d/<date, format:YYYY-MM-DD>:
	 Adds a hydration entry into the hydration tracker.
	 - hydration list: Displays all entries currently stored in the hydration list.
	 - hydration delete <hydration ID>: Deletes the hydration entry at the specified ID from the hydration list.
	 - hydration find <keyword>: finds and lists all hydration entries containing the keyword in their description
         -----------------------------------------------------------------------------
	 - sleep add <duration> d/<date, format:YYYY-MM-DD>: Adds a sleep entry into the sleep tracker.
	 - sleep list: Displays all entries currently stored in the sleep list.
	 - sleep delete <sleep ID>: Deletes the entry at the specified index from the sleep list.
         -----------------------------------------------------------------------------
	 - user setup <name> h/<height> w/<weight> a/<age> s/<sex> e/<exercise_level> g/<body_goal>:
	 Create a new user, or edit an existing one.
	 - user details: prints the details of the user.
	 - user update name/height/weight/age/sex/exercise levels/goal <UPDATED VALUE>: updates the corresponding field of the user.
	 - user progress: Display calories and hydration progress towards the daily requirement.
         -----------------------------------------------------------------------------

### Exiting the program: `bye`

Exits the program.

**Format:** 
`bye`

#### Expected output

         -----------------------------------------------------------------------------
         Bye! See you again soon ^^

## Calories Tracker

### Input calorie intake: `calories in`
Adds a calorie gaining activity into the calories tracker.
Macronutrients such as Carbohydrates, Proteins and Fats can be included if needed.
The `caloriesID` of each entry increments based on the previous `caloriesID`. For example, if highest
`caloriesID` is now 10, and user deletes the highest entry, the `caloriesID` of the next addition 
would be 11. 

**Format:** 
`calories in DESCRIPTION c/CALORIES d/DATE [m/CARBOHYDRATES,PROTEIN,FATS]`

* The `DESCRIPTION` refers to the food that the person consumed.
* The `CALORIES` must be a positive **integer** 1, 2, 3, …, measured in kcal. The limit for `CALORIES` for each entry 
 is 5000 (inclusive).
* The `DATE` provided should be of the form YYYY-MM-DD, such as 2024-03-04.
* Macronutrients field including `CARBOHYDRATES`, `PROTEINS` and `FATS` is optional. The macronutrients must be a positive **integer** 1, 2, 3, measured in grams.
The limit for each macronutrient per entry is 800g (inclusive).

**Examples:** 
* `calories in chicken rice c/678 d/2022-02-24`
* `calories in cai png c/543 d/2024-04-13 m/200, 150, 100`
* `calories in drink liho milk tea c/200 d/2024-04-14 m/50, 20, 10`

#### Expected output for `calories in drink liho milk tea c/200 d/2024-04-14 m/50, 20, 10`
	 The following entry has been added to your caloric list!
	 	 caloriesID: 22, Date: 2024-04-14, Description: drink liho milk tea, Calories: 200 (C: 50, P: 20, F: 10)
         -----------------------------------------------------------------------------

### Input calorie loss: `calories out`
Adds a calorie burning activity into the calories tracker.

**Format:** 
`calories out DESCRIPTION c/CALORIES d/DATE`

* The `DESCRIPTION` refers to any activity that resulted in loss of calories.
* The `CALORIES` must be a positive **integer** 1, 2, 3, …, measured in kcal. The limit for `CALORIES` for each entry
  is 5000 (inclusive).
* The `DATE` provided should be of the form YYYY-MM-DD such as 2024-04-03.

**Examples:**

* `calories out Run around NUS c/678 d/2022-02-24` 
* `calories out go gym c/300 d/2024-04-03`

#### Expected output for `calories out go gym c/300 d/2024-04-03`
         -----------------------------------------------------------------------------
	 The following entry has been added to your caloric list!
	 	 caloriesID: 21, Date: 2024-04-03, Description: go gym, Calories: 300
         -----------------------------------------------------------------------------
### Listing calorie items: `calories list`
Shows a list of all activities in the calories tacker. Calories inflow and outflow are displayed separately.
All entries are sorted by date, in ascending order, from earlier dates to present dates.

**Format:**
`calories list`

#### Expected output
                  -----------------------------------------------------------------------------
	 Your Caloric List:

	 Your Caloric Inflow List:
	 1. 	 caloriesID: 16, Date: 2024-01-01, Description: mcd ice cream, Calories: 100
	 2. 	 caloriesID: 15, Date: 2024-03-03, Description: chicken rice, Calories: 1000
	 3. 	 caloriesID: 3, Date: 2024-04-09, Description: wingstop, Calories: 1000 (C: 100, P: 100, F: 100)
	 4. 	 caloriesID: 11, Date: 2024-04-11, Description: breakfast, Calories: 100

	 Your Caloric Outflow List:
	 1. 	 caloriesID: 20, Date: 2024-01-01, Description: walk to comm hall, Calories: 20
	 2. 	 caloriesID: 17, Date: 2024-02-02, Description: badminton, Calories: 250
	 3. 	 caloriesID: 19, Date: 2024-02-02, Description: swim, Calories: 400
	 4. 	 caloriesID: 18, Date: 2024-04-15, Description: run, Calories: 300
         -----------------------------------------------------------------------------


### Deleting a calorie item: `calories delete`
Deletes the specified calories ID entry from the calories tracker according to the `CALORIESID`.

**Format:**
`calories delete CALORIESID`
* The `CALORIESID` must be a positive **integer** 1, 2, 3 and so on.

**Examples:**

* `calories list` followed by `calories delete 2` deletes the entry with `CALORIESID` 2 in the calories tracker.

#### Expected output for `calories delete 3` based on calories list shown in example above.
	 The following calorie record has been successfully deleted!
	 	 caloriesID: 3, Date: 2024-04-09, Description: wingstop, Calories: 1000 (C: 100, P: 100, F: 100)
         -----------------------------------------------------------------------------

### Searching for a calorie item: `calories find`
Finds and retrieves all calories entries from the caloric list containing the keyword to search for.

**Format:**
`calories find KEYWORD`

**Examples:**

* `calories find cream` retrieves all the calories entries with `cream` in their description.
#### Expected output of `calories find cream` based on calories list shown in example above.
	 Caloric List based on your search:

	 Your Caloric Inflow List:
	 1. 	 caloriesID: 16, Date: 2024-01-01, Description: mcd ice cream, Calories: 100

	 Your Caloric Outflow List:
         -----------------------------------------------------------------------------

## Hydration Tracker

### Input hydration intake: `hydration in`
Adds a hydration record into the hydration tracker.

**Format:**
`hydration in DESCRIPTION v/VOLUME d/DATE`

* The `DESCRIPTION` refers to the food that the person consumed.
* The `VOLUME` must be a positive integer 1, 2, 3, …, measured in milliliters. The limit for `volume` for each entry is 
10000(inclusive).
* The `DATE` provided should be of the form YYYY-MM-DD, such as 2024-03-04.

**Examples:**
* `hydration in Milo v/1000 d/2022-03-25`
* `hydration in Tea v/200 d/2022-02-05`

### Listing hydration items: `hydration list`
Show the list of all hydration records in the hydration tracker.

**Format:**
`hydration list`
#### Expected output
         -----------------------------------------------------------------------------
         Your Hydration List:
         1. 	 hydrationID: 1, Date: 2024-04-10, Description: milo, Volume: 100
         2. 	 hydrationID: 2, Date: 2024-04-10, Description: coke, Volume: 1000
         -----------------------------------------------------------------------------


### Deleting a hydration item: `hydration delete`
Deletes the specified hydration entry according to the `HYDRATIONID`.

**Format:**
`hydration delete HYDRATIONID`
* The `HYDRATIONID` must be a positive **integer** 1, 2, 3 and so on.

**Examples:**
* `hydration list` followed by `hydration delete 2` deletes the entry with `HYDRATIONID` 2 in the hydration tracker.

### Searching for a hydration item: `hydration find`
Finds and retrieves all hydration entries from the hydration list containing the keyword to search for.

**Format:**
`hydration find KEYWORD`

**Examples:**

* `hydration find water` retrieves all the hydration entries with `water` in their description.

## Sleep Tracker

### Input sleeping hours: `sleep add`
Adds a sleep record into the sleep tracker.

**Format:**
`sleep add DURATION d/DATE`
* The duration provided must be a positive real number.
* The duration should not exceed 24 hours.
* The date provided should be of the form YYYY-MM-DD.


**Examples:**
* `sleep add 7.5 d/2024-03-11`


### Listing sleep records: `sleep list`
Show the list of all sleep records in the sleep tracker.

**Format:**
`sleep list`

### Deleting a sleep record: `sleep delete`
Deletes the specified sleep entry according to the `SLEEPID`.

**Format:**
`sleep delete SLEEPID`
* Delete the sleep record with specified `SLEEPID`.
* The `SLEEPID` refers to the id number shown in the displayed sleeping records list.
* The `SLEEPID` must be a positive integer 1, 2, 3, …​

**Examples:**
* `sleep list` followed by `sleep delete 2` deletes the sleep record with `SLEEPID` 2 from the sleep tracker.

## User Profile

### Set up user profile: `user setup`
Creates/edits an existing user profile.

**Format:**
`user setup NAME h/HEIGHT w/WEIGHT a/AGE s/GENDER e/EXERCISE LEVELS g/BODY GOAL`
* The height provided must be an integer between 90 and 225 cms.
* The weight provided must be an integer between 30 and 200 kgs.
* The age provided must be an integer between 13 and 30 years old.
* The gender provided must be either `male`/`m` or `female`/`f`. It is not case-sensitive.
* The exercise levels provided must be an integer between 1 and 5.
* The body goal provided must be an integer between 1 and 5.

**Notes about the command format:**

| Exercise Level Input | Corresponding Exercise Levels |
|:--------------------:|:-----------------------------:|
|          1           |           Sedentary           |
|          2           |        Lightly Active         |
|          3           |       Moderately Active       |
|          4           |          Very Active          |
|          5           |       Extremely Active        |

| Body Goal Input |  Corresponding Goal  |
|:---------------:|:--------------------:|
|        1        |  Quick Weight Loss   |
|        2        | Moderate Weight Loss |
|        3        |   Maintain Weight    |
|        4        | Moderate Weight Gain |
|        5        |  Quick Weight Gain   |


**Examples:**
* `user setup Tom h/180 w/80 a/25 s/male e/3 g/2`
* `user setup Jane h/163 w/54 a/23 s/female e/2 g/3`

### Check your user's details: `user details`
Displays the details of the user who is using _LifeTrack_.

**Format:**
`user details`

**Notes about the command:**
If you have not set your user up beforehand, this command will prompt you to do so instead.

#### Expected output

         -----------------------------------------------------------------------------
         User details:
         Name: John
         Height: 170
         Weight: 80
         Age: 23
         Sex: male
         Exercise Levels: 2 out of 5 (Lightly Active)
         Goal: 4 out of 5 (Moderate Weight Gain)
         -----------------------------------------------------------------------------
### Update your user's details: `user update`
Updates the details of the user depending on their input.

**Format:**
`user update <FIELD_TO_UPDATE> <NEWVALUE>`

**List of possible fields to update:**
1. name
2. height
3. weight
4. age
5. sex
6. exercise levels
7. goal

#### Examples:
- `user update weight 70`
- `user update height 170`
- `user update exercise levels 2`

### Check your daily calories and hydration consumption and your sleep statistics: `user progress`
Displays progress bars to show the percentage of calories and hydration you have consumed as well as sleep you have gotten over the past 3 days.

**Format:**
`user progress`

**Notes about the command:**
If you have not set your user up beforehand, this command will prompt you to do so instead.

#### Expected output

         -----------------------------------------------------------------------------
         Calories:
         ----------
         You have consumed 200 calories out of your goal of 2650 calories today.
         [===                                               ]  7%
    
         You have consumed 300 calories out of your goal of 2650 calories yesterday.
         [=====                                             ]  11%
    
         You have consumed 400 calories out of your goal of 2650 calories on the day before yesterday.
         [======                                            ]  15%
    
         Hydration:
         ----------
         You have consumed 300ml out of your goal of 2000ml today.
         [=======                                           ]  15%
    
         You have consumed 600ml out of your goal of 2000ml yesterday.
         [==============                                    ]  30%
    
         You have consumed 1200ml out of your goal of 2000ml on the day before yesterday.
         [============================                      ]  60%
    
         Sleep:
         ----------
         You have slept for 4.2 hrs out of your goal of 7 hrs today.
         [==============================                    ]  60%
    
         You have slept for 4.7 hrs out of your goal of 7 hrs yesterday.
         [=================================                 ]  67%
    
         You have slept for 8.0 hrs out of your goal of 7 hrs on the day before yesterday.
         [==================================================]  114%

         -----------------------------------------------------------------------------


## FAQ

**Q**: How do I transfer my data to another computer?

**A**: In the same directory as where the JAR file is located, the application will automatically create a `/data` directory which stores all the data files required for the application. Simply copy the entire directory and its contents to your new computer and ensure that it is in the same directory as your JAR file, then run the application as per normal.

**Q**: How do I manually edit the data files in the correct format?

**A**: For the calories data file, the format is as such: `ENTRY_ID;DATE;DESCRIPTION;ENTRY_TYPE;CALORIES;CARBOHYDRATES;PROTEINS;FATS`. Take note that `ENTRY_TYPE` must be `C_IN` for the application to accept additional macronutrient fields after the `CALORIES` field. For the hydration data file, the format is as such: `ENTRY_ID;DATE;DESCRIPTION;VOLUME`. For the sleep data file, the format is as such: `ENTRY_ID;DATE;DURATION`. The delimiter used in the data files must be a semicolon (;).

**Q**: Why must I input integers for my calories when it is a continuous variable?

**A**: Although calories is technically a continuous variable, we chose to only take in integer inputs in our application as the difference is just not that significant, i.e. users can just round up values that have decimal values of 0.5 and above, and round down any values below that. An average human will have calorie intake in the thousands daily, thus such a small inaccuracy is insignificant in comparison. An `int` is also much easier to work with than `float`, which is why we chose to only use the former.

**Q**: Why is the limit for `CALORIES` 5000?

**A**: Our team decided that a rational amount of calories per entry/meal would be 5000 calories. We decided on 5000 calories
because it is not too big, nor is it too small an amount. Thus, it would account for extreme cases of high calorie intake.

## Coming soon

### Undo/Redo feature

An undo/redo feature will be a nice quality of life boost for the app, as it provides users with the freedom to navigate their actions with confidence. Mistakes are inevitable, whether it's unintentional deletions, accidental changes, or simply exploring different options. This feature will help to enhance user experience by instilling a sense of control and reducing anxiety about irreversible actions.

### Automatic calculation of calories burnt

Calories burnt from any exercise can be calculated from online calculators depending on the user's body proportions. If such calculators were implemented within the application itself, the calories burnt can be made even more accurate, and also improve user experience as they do not have to manually calculate and key in the values now.

### Implement daily requirements for macronutrients intake

Daily requirements for macronutrients intake can also be implemented to enhance the user's diet further. These requirements also change depending on the user's exercise levels and body goals.

### Provide recommendations to meet daily requirements

The `user progress` command displays the user's current progress towards the daily caloric and hydration requirements. This can be enhanced if the application was able to provide recommendations on how to meet these requirements. For example, healthy meals with carefully calculated macronutrients can be recommended to users to meet their daily caloric and macronutrient requirements.

## Command Summary

| Action                             | Format, Examples                                                                 |
|------------------------------------|----------------------------------------------------------------------------------|
| Help                               | `help`                                                                           |
| Exit program                       | `bye`                                                                            |
| Add calories intake                | `calories in DESCRIPTION c/CALORIES d/DATE [m/CARBOHYDRATES,PROTEIN,FATS]`       |
| Add calories outflow               | `calories out DESCRIPTION c/CALORIES d/DATE`                                     |
| List calories                      | `calories list`                                                                  |
| Delete calories entry              | `calories delete CALORIESID`                                                     |
| Search for calorie entry/entries   | `calories find KEYWORD`                                                          |
| Add hydration intake               | `hydration in DESCRIPTION v/VOLUME d/DATE`                                       |
| List hydration                     | `hydration list`                                                                 |
| Delete hydration entry             | `hydration delete HYDRATIONID`                                                   |
| Search for hydration entry/entries | `hydration find KEYWORD`                                                         |
| Add sleep                          | `sleep add DURATION d/DATE`                                                      |
| List sleep                         | `sleep list`                                                                     |
| Delete sleep entry                 | `sleep delete SLEEPID`                                                           |
| Set Up User Profile                | `user setup NAME h/HEIGHT w/WEIGHT a/AGE s/GENDER e/EXERCISE LEVELS g/BODY GOAL` |
| Check User Profile                 | `user details`                                                                   |
| Update User Details                | `user update <FIELD_TO_UPDATE> <NEWVALUE>`                                       |
| Check User Progress                | `user progress`                                                                  |

