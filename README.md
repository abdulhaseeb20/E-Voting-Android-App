# E-Voting-Android-App
E-Voting system is an android app that is build on Android Studio using Java as programming language and Firebase for back-end services. The purpose of this application is provide user flexible and secure means for casting a vote while sitting at home. Now, you don't need to caste your vote manually, simply provide details of your identity to caste vote.

**Features**
++ User Dashboard: Create new user account or login into existing account using CNIC and password. The CNIC is then used to caste a vote for that particular identity.
++ Admin Dashboard: Login as an admin and perform several operations, that are,
   1. Add new party
   2. Announce winner party
   3. Check vote count list
   4. Update admin profile, i.e. email and password
++ OTP Verification: This is the main feature of this app. Only users with authentic phone numbers are able to caste a vote upon successfull verification using one time generated password. Users are notified with vote casted message upon successfull verification.

**Technologies used**
Java: Programming Language
Firebase Realtime Database: for all back-end services like storing user CNICs, passwords etc.
Firebase Authentication: for generating one time password 
XML: for front-end layouts

**How to use:**
-> git clone https://github.com/abdulhaseeb20/E-Voting-Android-App.git
-> import complete project into Android Studio
-> build gradle and you are ready to use it
