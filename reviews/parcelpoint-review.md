No.	Check	Code location (file:line)	Finding	Severity (Must fix, Should fix or Note)	Suggested fix
1	Check 1	Main.java:6	Resource leak: Scanner object in is never closed.	Should fix	Wrap the Scanner in a try-with-resources block or close it before exiting.
2	Check 1	ParcelService.java:6	Unused import java.util.Date.	Note	Remove the unused import statement.
3	Check 1	ParcelService.java:12	Unused private field maxParcels.	Note	Remove the unused field maxParcels.
4	Check 1	ParcelService.java:41	String comparison using == instead of .equals().	Must fix	Replace parcel.getCode() == code with parcel.getCode().equals(code).
5	Check 1	ParcelService.java:74	Resource leak: FileWriter is opened but never closed, causing buffer data not to write.	Must fix	Use a try-with-resources statement to automatically close FileWriter.
6	Check 1	ParcelService.java:78	Empty catch block ignoring IOException.	Should fix	Log or handle the exception properly inside the catch block.
7	Check 2	Main.java:8	Method main has procedural cohesion as it sequentially handles input reading, command parsing, and menu execution.	Should fix	Extract command handling logic into separate methods or a dedicated controller class.
8	Check 2	Main.java:20	Strong coupling: main reaches into ParcelService's internal collection to filter and count lockers.	Should fix	Delegate free locker counting to a new method inside ParcelService.
9	Check 2	Parcel.java:6	Field status is public, breaking encapsulation.	Must fix	Change status to private and provide getter/setter methods.
10	Check 2	Parcel.java:4	Connascence of convention: integer size codes (1, 2, 3) are used directly across classes without standard definition.	Should fix	Replace integer size codes with a dedicated Size enum.
11	Check 2	ParcelService.java:8	Weak class cohesion: ParcelService handles locker management, fee calculations, console output, SMS simulation, and file persistence.	Should fix	Extract persistence and notification logic into separate dedicated classes.
12	Check 2	ParcelService.java:21	Operations return internal collection directly (getLockers()), allowing callers to modify internal state.	Should fix	Return an unmodifiable view using Collections.unmodifiableList(lockers).
13	Check 2	ParcelService.java:28	Content coupling: storeParcel directly modifies the public field status of Parcel.	Must fix	Update status using a setter method on the Parcel class.
14	Check 2	ParcelService.java:41	Strong coupling / Law of Demeter: pickUp reaches through Locker to access Parcel properties.	Should fix	Add a helper method on Locker to check for parcel code matching.
15	Check 2	ParcelService.java:53	Stamp coupling: fee accepts a whole Parcel object when it only requires its size.	Note	Pass only int size (or the Size enum) instead of the entire Parcel object.
16	Check 2	ParcelService.java:64	Control coupling: printParcels uses a boolean flag (forStaff) to alter execution flow.	Should fix	Split into two distinct methods for customer display and staff display.
17	Check 2	ParcelService.java:64	Method printParcels has logical cohesion because its behavior changes based on the forStaff flag.	Should fix	Separate staff printing and customer printing into individual methods with functional cohesion.