# Lab Report, Code Quality and Design Quality

Repository URL: https://github.com/kjmcha/itcs382-lab.git

## Tools

| Item | Version or name |
| --- | --- |
| JDK | 25.0.5 |
| Maven | 3.10.0 |
| IDE | |
| SonarQube for IDE | |
| AI tool and exact model | |

## Task 1. SonarQube on the starter code

Commit of the starter, before any change: aac634c

Table 1. SonarQube issues in the starter code

| No. | Rule | File:line | Message |
| --- | --- | --- | --- |
| 1 | java:S106 | Main.java:9 | Replace this use of System.out by a logger. |
| 2 | java:S106 | Main.java:15 | Replace this use of System.out by a logger. |
| 3 | java:S106 | Main.java:17 | Replace this use of System.out by a logger. |
| 4 | java:S106 | Main.java:22 | Replace this use of System.out by a logger. |
| 5 | java:S2143 | ParcelService.java:1 | Use the "java.time" API for date and time. |
| 6 | java:S1128 | ParcelService.java:6 | Remove this unused import 'java.util.Date'. |
| 7 | java:S1068 | ParcelService.java:12 | Remove this unused "maxParcels" private field. |
| 8 | java:S106 | ParcelService.java:30 | Replace this use of System.out by a logger. |
| 9 | java:S4973 | ParcelService.java:42 | Strings and Boxed types should be compared using "equals()". |
| 10 | java:S106 | ParcelService.java:67 | Replace this use of System.out by a logger. |
| 11 | java:S106 | ParcelService.java:69 | Replace this use of System.out by a logger. |
| 12 | java:S2095 | ParcelService.java:76 | Use try-with-resources or close this "FileWriter" in a "finally" clause. |
| 13 | java:S108 | ParcelService.java:80 | Remove this block of code, fill it in, or add a comment explaining why it is empty. |

## Task 2. AI review of the starter code

| Item | Value |
| --- | --- |
| Date | 25 Oct 2026 |
| AI tool and exact model | Gemini 2.5 Pro |
| Commit reviewed | aac634c |
| How the AI read the files (attached the files, opened the folder, or pasted the files) | pasted the files |
| Output file in the reviews folder | reviews/parcelpoint-review.md |

Table 2. All findings on the starter code

| No. | File:line | Finding | Found by (SonarQube, AI or both) | SonarQube rule or course term | Correct? (yes or no, and why) |
| --- | --- | --- | --- | --- | --- |
|| 1 | Main.java:9,15,17,22 | Replace System.out by a logger | SonarQube | java:S106 | Yes, using a logger allows proper log level management in production. |
| 2 | ParcelService.java:1 | Use java.time API instead of Legacy Date | SonarQube | java:S2143 | Yes, java.time (Instant/LocalDateTime) is thread-safe and modern. |
| 3 | ParcelService.java:6 | Unused import java.util.Date | both | java:S1128 / Unused import | Yes, Date is imported but never referenced in the code. |
| 4 | ParcelService.java:12 | Unused private field maxParcels | both | java:S1068 / Unused field | Yes, maxParcels is declared but never read or modified. |
| 5 | ParcelService.java:41-42 | String comparison using == instead of equals() | both | java:S4973 / Probable bug | Yes, == compares reference identity, causing pickup matching to fail. |
| 6 | ParcelService.java:76 | FileWriter resource leak (never closed) | both | java:S2095 / Resource leak | Yes, unclosed FileWriter causes buffered data to not write to disk. |
| 7 | ParcelService.java:80 | Empty catch block ignoring IOException | both | java:S108 / Empty block | Yes, swallowing exceptions silently hides file write errors. |
| 8 | Parcel.java:6 | Field status is public | AI | Encapsulation violation | Yes, public fields allow external classes to mutate internal state directly. |
| 9 | Parcel.java:4 | Integer size codes (1, 2, 3) used directly | AI | Connascence of convention | Yes, using raw integers requires all classes to agree on arbitrary magic numbers. |
| 10 | ParcelService.java:8 | Handles locker, fee, SMS, and file IO | AI | Weak class cohesion | Yes, ParcelService violates SRP by mixing business logic with persistence and notification. |
| 11 | ParcelService.java:21 | Operation getLockers() returns internal mutable list | AI | Exposure of internal collection | Yes, callers can alter the internal lockers list without going through ParcelService methods. |
| 12 | ParcelService.java:28 | storeParcel directly modifies parcel.status | AI | Content coupling | Yes, storeParcel directly mutates another class's field instead of calling a method. |
| 13 | ParcelService.java:53 | fee method accepts entire Parcel object when only size is needed | AI | Stamp coupling | Yes, passing the whole Parcel exposes unnecessary data to the fee calculation. |
| 14 | ParcelService.java:64 | printParcels uses boolean flag (forStaff) to alter execution flow | AI | Control coupling / Logical cohesion | Yes, flag argument forces one method to perform two distinct behaviors based on control parameter. |
| 15 | Main.java:8 | main method sequentially handles I/O, parsing, and execution | AI | Procedural cohesion | Yes, main performs a sequence of unrelated operational tasks in a fixed order. |

2.1 Name two problems that only the AI review found.
1. Public field `status` in `Parcel.java` which violates encapsulation and leads to Content Coupling.
2. Control coupling / Logical cohesion in `ParcelService.printParcels(boolean forStaff)` caused by using a boolean flag to switch method logic.

2.2 Name one problem that only SonarQube found, or write none.
Rule `java:S2143` in `ParcelService.java:1` (Use the "java.time" API for date and time instead of legacy `java.util.Date`).

2.3 Name one AI finding that was wrong or too vague, and explain why. If every finding was right, explain how you checked them.
Every AI finding was correct. I checked each finding by inspecting the corresponding code lines: the public field `status` in `Parcel.java` indeed violates encapsulation; `==` on String in line 41 caused the runtime pickup test to fail (`Fee: 0.0`); unclosed `FileWriter` caused `parcels.csv` to remain empty after execution; and the boolean flag in `printParcels` explicitly switches execution flow between staff and public modes.

## Task 3. Fix and check

Table 3. What you did with each SonarQube issue in Table 1

| No. in Table 1 | Fixed or kept | Commit, or the reason for keeping it | 
| --- | --- | --- |
| 1 | kept | System.out is standard for CLI console applications in this lab. |
| 2 | kept | System.out is standard for CLI console applications in this lab. |
| 3 | kept | System.out is standard for CLI console applications in this lab. |
| 4 | kept | System.out is standard for CLI console applications in this lab. |
| 5 | fixed | d6989dc |
| 6 | fixed | d6989dc |
| 7 | fixed | d6989dc |
| 8 | kept | System.out is standard for CLI console applications in this lab. |
| 9 | fixed | d6989dc |
| 10 | kept | System.out is standard for CLI console applications in this lab. |
| 11 | kept | System.out is standard for CLI console applications in this lab. |
| 12 | fixed | d6989dc |
| 13 | fixed | d6989dc |

Number of SonarQube issues that remain after your fixes: 7

The two design problems that you fixed

| Design problem (course term) | What you changed | Commit |
| --- | --- | --- |
| Encapsulation Violation / Content Coupling | Changed public field `status` in `Parcel.java` to `private` and added getter/setter methods (`getStatus`, `setStatus`). Updated `ParcelService` to use these accessors. | d6989dc |
| Resource Leak / Exception Handling | Rewrote `saveToFile()` in `ParcelService.java` using try-with-resources to ensure `FileWriter` is automatically closed and errors are logged. | d6989dc |

Output of the program after your fixes, for the commands `store P1 6501 2`, `pickup P1 5` and `staff`, and the content of parcels.csv:

```text
Commands: store <code> <studentId> <size 1-3>, pickup <code> <days>, list, staff, quit
SMS to 6501: parcel P1 is in locker 2
Locker 2
Fee: 30.0
P1 6501 PICKED_UP
Free lockers: 3

Content of parcels.csv:
P1,PICKED_UP
```

## Task 4. AI-generated poor and good designs

| Item | bikeshare-poor | bikeshare-good |
| --- | --- | --- |
| Date | | |
| AI tool and exact model | | |
| Prompt (B or C as given, or your changed prompt in full) | | |
| Builds with mvn package (yes or no) | | |
| Number of classes, interfaces, enums and records | | |
| SonarQube issues, total | | |
| SonarQube issues, by rule | | |
| AI review findings in Check 1 | | |
| AI review findings in Check 2 | | |
| Review output file in the reviews folder | | |

4.1 Did SonarQube report any cohesion or coupling problem in bikeshare-poor? Quote the rule and the message if it did.

4.2 Did the AI review tell the two versions apart? Give one finding for bikeshare-poor that does not appear for bikeshare-good.

4.3 Which version would you rather extend with a new member type, such as a visitor who pays 4 baht per minute, and why?

## Task 5. Reflection

5.1 What does SonarQube check, and what does it not check? Answer with examples from this lab.

5.2 When should you not trust the AI review? Give an example from this lab.

5.3 What will you do differently in Project Phase 2 after this lab?

## AI usage declaration

| AI tool and exact model | Purpose | Files or parts that it produced or changed | How you checked its output |
| --- | --- | --- | --- |
| | | | |
