🚀 Automation Project – SauceDemo
📌 Overview
This repository contains two automation frameworks for the SauceDemo
 website:
1.TestNG-based framework (Page Object Model + TestNG)
2.Cucumber BDD framework (Cucumber + TestNG)
Both projects automate the key e-commerce workflows: Login, Sorting, Cart, and Checkout.

✅ Features Covered
🔐 Login
Valid login
Invalid login
Locked out user
Wrong password
🔄 Sorting
Sort A–Z
Sort Z–A
Sort Low → High
Sort High → Low
🛒 Cart
Add item to cart
Remove item from cart
📦 Checkout
Valid checkout
Invalid checkout (missing Data)

🏗 Project Structure
automation_project/
 ├── testng-project/            # TestNG + POM framework
 │    ├── src/test/java/
 │    │    ├── pages/           # Page Object classes
 │    │    ├── testcases/       # TestNG test classes
 │    │    └── utils/           # DriverFactory, helpers
 │    ├── pom.xml
 │    └── testng.xml
 │
 ├── cucumber-project/          # Cucumber BDD framework
 │    ├── src/test/java/
 │    │    ├── feature/         # Gherkin feature files
 │    │    ├── stepdefs/        # Step definitions (Selenium code)
 │    │    ├── runners/         # Cucumber + TestNG runners
 │    │    └── utils/           # DriverFactory, helpers
 │    ├── pom.xml
 │    └── testng-cucumber.xml
 │
 └── README.md

⚙️ Setup Instructions
Clone the repo:git clone https://github.com/<your-username>/automation_project.git
Open in Eclipse IDE (File → Import → Maven → Existing Maven Projects).
Install dependencies via Maven (pom.xml will auto-download).
Ensure ChromeDriver is installed and path is set in DriverFactory.

▶️ Running Tests in Eclipse
🔹 TestNG Project
Expand testng-project.
Right-click testng.xml.
Select Run As > TestNG Suite.
Runs all TestNG tests.
Reports available in test-output/.
🔹 Cucumber Project
Expand cucumber-project.
Right-click runners/TestRunner.java.
Select Run As > TestNG Test.
Runs all .feature files.
Reports available in target/cucumber-reports.html.
👉 To run a single feature (e.g., Login.feature):
Right-click on the .feature file → Run As > Cucumber Feature.

📊 Reports
TestNG Report → test-output/index.html
Cucumber Report → target/cucumber-reports.html

👨‍💻 Author
Your Name – Lakshmi Priyanka Bayyapuneni
