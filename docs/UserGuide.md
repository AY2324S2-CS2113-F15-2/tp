# User Guide

## Introduction

LifeTrack is a desktop app for students to track their health data, optimized for use via a Command Line Interface (CLI). It tracks calories, hydration and sleep data for the user, while also providing daily recommendations for calorie and hydration intake, based on the user's build and gender, as well as their body goals and activity levels.

## Quick links
- [Quick Start](#quick-start)
- [General](#general)
  - [help](#viewing-help-help)
  - [bye](#exiting-the-program-bye)
- [Calories Tracker](#calories-tracker)
  - [Input calories Intake](#input-calorie-intake-calories-in)
  - [Input calorie loss](#input-calorie-loss-calories-out)
  - [Listing calorie items](#listing-calorie-items-calories-list)
  - [Deleting a calorie item](#deleting-a-calorie-item-calories-delete)
- [Hydration Tracker](#hydration-tracker)
  - [Input hydration intake](#input-hydration-intake-hydration-in)
  - [Listing hydration items](#listing-hydration-items-hydration-list)
  - [Deleting a hydration item](#deleting-a-hydration-item-hydration-delete)
- [Sleep Tracker](#sleep-tracker)
  - [Input sleeping hours](#input-sleeping-hours-sleep-add)
  - [Listing sleep records](#listing-sleep-records-sleep-list)
  - [Deleting a sleep record](#deleting-a-sleep-record-sleep-delete)
- [User Profile](#user-profile)
  - [Set Up User Profile](#set-up-user-profile-user-setup)
  - [Check User's daily calories and hydration consumption](#check-your-daily-calories-and-hydration-consumption-user-progress)
- [FAQ](#faq)
- [Command Summary](#command-summary)

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `LifeTrack` from [here](http://link.to/duke).

[//]: # (## Features )

## General

### Viewing help: `help`
Shows a help message listing the commands available in the application.

**Format:** 
`help`

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

**Format:** 
`calories in DESCRIPTION c/CALORIES d/DATE [m/CARBOHYDRATES,PROTEIN,FATS]`

* The `DESCRIPTION` refers to the food that the person consumed.
* The `CALORIES` must be a positive integer 1, 2, 3, …, measured in kcal. 
* The `DATE` provided should be of the form YYYY-MM-DD, such as 2024-03-04.
* Macronutrients field including `CARBOHYDRATES`, `PROTEINS` and `FATS` is optional. The macronutrients must be a positive integer 1, 2, 3, measured in grams.

**Examples:** 
* `calories in chicken rice c/678 d/2022-02-24`
* `calories in hamburger c/983 d/2024-04-03`
* `calories in cai png c/543 d/2024-04-13 m/200, 150, 100`
* `calories in drink liho milk tea c/200 d/2024-04-25 m/50, 20, 10`


### Input calorie loss: `calories out`
Adds a calorie burning activity into the calories tracker.

**Format:** 
`calories out DESCRIPTION c/CALORIES d/DATE`

* The `DESCRIPTION` refers to any activity that resulted in loss of calories.
* The `CALORIES` must be a positive integer 1, 2, 3, …, measured in kcal. 
* The `DATE` provided should be of the form YYYY-MM-DD such as 2024-04-03.

**Examples:**

* `calories out Run around NUS c/678 d/2022-02-24` 
* `calories out Walk to i3 building c/67 d/2022-03-25`
* `calories out go gym c/300 d/2024-04-03`

### Listing calorie items: `calories list`
Shows a list of all activities in the calories tracker. Includes both calories in and out.

**Format:**
`calories list`

#### Expected output
         -----------------------------------------------------------------------------
	 Your Caloric List:
	 1. 	 Date: 2024-06-15, Description: chicken, Calories: 1000
	 2. 	 Date: 2024-06-15, Description: chicken, Calories: 1000
	 3. 	 Date: 2024-05-15, Description: chicken, Calories: 1000
	 4. 	 Date: 2023-03-01, Description: taco, Calories: 1
	 5. 	 Date: 2024-04-03, Description: burger, Calories: 100
	 6. 	 Date: 2024-04-03, Description: cai png, Calories: 1000
	 7. 	 Date: 2024-04-03, Description: cai png, Calories: 500
	 8. 	 Date: 2024-04-13, Description: liho milk tea, Calories: 200
         -----------------------------------------------------------------------------

### Deleting a calorie item: `calories delete`
Deletes the specified activity from the calories tracker.

**Format:**
`calories delete INDEX`

**Examples:**

* `calories list` followed by `calories delete 2` deletes the 2nd activity in the calories tracker.

## Hydration Tracker

### Input hydration intake: `hydration in`
Adds a hydration record into the hydration tracker.

**Format:**
`hydration in DESCRIPTION v/VOLUME d/DATE`

* The volume must be a positive integer 1, 2, 3, …, measured in milliliters.
* The time indicated should follow the 24-hour system.
* The date provided should be of the form YYYY-MM-DD.

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
	 1. 	 Date: 2022-01-01, Description: teh peng, Volume: 100
	 2. 	 Date: 2022-01-01, Description: milo, Volume: 100
	 3. 	 Date: 2022-01-02, Description: water, Volume: 100
	 4. 	 Date: 2022-01-02, Description: milo, Volume: 200
	 5. 	 Date: 2022-01-01, Description: milo, Volume: 100
         -----------------------------------------------------------------------------

### Deleting a hydration item: `hydration delete`
Deletes the hydration record according to the index.

**Format:**
`hydration delete INDEX`

Delete the drinking water record at the specific index. The index refers to the index number shown in the displayed Hydration list. The index must be a positive integer 1, 2, 3, …​

**Examples:**
* `hydration list` followed by `hydration delete 2` deletes the 2nd hydration record from the hydration tracker.

## Sleep Tracker

### Input sleeping hours: `sleep add`
Adds a sleep record into the sleep tracker.

**Format:**
`sleep add DURATION d/DATE`
* The duration provided must be a positive real number.
* The time indicated should follow the 24-hour system.
* The date provided should be of the form YYYY-MM-DD.


**Examples:**
* `sleep add 7.5 d/2024-03-11`


### Listing sleep records: `sleep list`
Show the list of all sleep records in the sleep tracker.

**Format:**
`sleep list`

### Deleting a sleep record: `sleep delete`
Deletes the sleep record according to the index.

**Format:**
`sleep delete INDEX`
* Delete the sleep record at the specific index.
* The index refers to the index number shown in the displayed sleeping records list.
* The index must be a positive integer 1, 2, 3, …​

**Examples:**
* `list` followed by `sleep delete 2` deletes the 2nd sleep record from the sleep tracker.

## User Profile

### Set up user profile: `user setup`
Creates/edits an existing user profile.

**Format:**
`user setup NAME h/HEIGHT w/WEIGHT a/AGE s/GENDER e/EXERCISE LEVELS g/BODY GOAL`
* The height provided must be an integer between 90 and 225 cms.
* The weight provided must be an integer between 30 and 200 kgs.
* The age provided must be an integer between 13 and 110 years old.
* The gender provided must be either `male` or `female`.
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

### Check your daily calories and hydration consumption: `user progress`
Displays a progress bar to show the percentage of calories and hydration you have consumed.

**Format:**
`user progress`

**Notes about the command:**
If you have not set your user up beforehand, this command will prompt you to do so instead.

#### Expected output

        -----------------------------------------------------------------------------
	 Calories:
	 You have consumed 350 calories out of your goal of 2140 calories so far.
	 [========                                          ]  16%

	 Hydration:
	 You have consumed 200ml out of your goal of 2000ml so far.
	 [=====                                             ]  10%
         -----------------------------------------------------------------------------


## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Coming soon

### Calorie lists to show calorie intakes and outflow separately

When the user inputs `calorie list` into the terminal, they should be able to see the calorie lists for their calorie intakes and outflow separately.

This upcoming feature will be implemented by allowing the `CalorieList` class to have two `ArrayList` members that store the calorie inflows and outflows separately.

### Calorie and hydration progress to be calculated based on date

The current implementation of the calorie and hydration progress calculates the calories/hydration consumed based on the total number of entries in the list, irregardless of the date.

We want to be able to provide accurate representations of users meeting their daily calorie and hydration needs, so this feature will be updated very soon to accommodate that.

### Reduce reliance on network features

As of current, calorie requirements for users is calculated from an external API. However, to reduce the reliance on having a strong network connection for our application to work well, we are implementing a failsafe to calculate calorie requirements even if the network connection is not good.

### Edit user details

The user should be able to quickly edit their details without having to run the `user setup` command again, as they may only need to change a few details for their account.

## Command Summary

| Action                 | Format, Examples                                                                 |
|------------------------|----------------------------------------------------------------------------------|
| Help                   | `help`                                                                           |
| Add calories intake    | `calories in DESCRIPTION c/CALORIES d/DATE [m/CARBOHYDRATES,PROTEIN,FATS]`       |
| Add calories outflow   | `calories out DESCRIPTION c/CALORIES d/DATE`                                     |
| List calories          | `calories list`                                                                  |
| Delete calories entry  | `calories delete INDEX`                                                          |
| Add hydration intake   | `hydration in DESCRIPTION v/VOLUME d/DATE`                                       |
| List hydration         | `hydration list`                                                                 |
| Delete hydration entry | `hydration delete INDEX`                                                         |
| Add sleep              | `sleep add DURATION d/DATE`                                                      |
| List sleep             | `sleep list`                                                                     |
| Delete sleep entry     | `sleep delete INDEX`                                                             |
| Set Up User Profile    | `user setup NAME h/HEIGHT w/WEIGHT a/AGE s/GENDER e/EXERCISE LEVELS g/BODY GOAL` |
| Check User Progress    | `user progress`                                                                  |

