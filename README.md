# 🧪 RestAssured-Framework

A powerful and scalable API test automation framework built with **Java**, **Rest Assured**, **TestNG**, and **Maven**.

---

## 📌 Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Setup Instructions](#setup-instructions)
- [Running Tests](#running-tests)
- [Reports](#reports)
- [Extending the Framework](#extending-the-framework)
- [Contributing](#contributing)
- [License](#license)
- [Author](#author)

---

## 🚀 Features

✅ Modular structure with clean separation of concerns  
✅ Reusable request utilities and endpoints  
✅ Dynamic data generation with Faker  
✅ TestNG integration for suite execution  
✅ Auto-generated HTML reports  
✅ Easy to extend and maintain  

---

## 🧰 Tech Stack

| Tool           | Purpose                           |
|----------------|-----------------------------------|
| Java           | Core programming language         |
| Rest Assured   | API test automation               |
| TestNG         | Test orchestration and assertions |
| Maven          | Dependency management             |
| Faker          | Random test data generation       |
| ExtentReports  | HTML test reports                 |
| Log4j          | Logging and debugging             |

---

## 📁 Project Structure
RestAssured-Framework/
├── pom.xml # Maven dependencies
├── testng.xml # TestNG test suite
├── /src
│ ├── main/java
│ │ ├── endpoints/ # API route definitions
│ │ ├── payload/ # POJO classes (User, Pet, etc.)
│ │ ├── utilities/ # Logger, Faker, Config readers
│ └── test/java
│ └── tests/ # TestNG test classes
└── /target
└── extent-report.html # Execution report


---

## 🛠️ Setup Instructions

1. **Clone the repository**
   ```bash
   git clone https://github.com/rahulsingh731/RestAssured-Framework.git
   cd RestAssured-Framework
Install Maven dependencies

bash
mvn clean install

Ensure the following:

JDK 8 or above is installed

Maven 3.6+ is configured

Internet access for downloading dependencies


➕ Extending the Framework
Add new API routes in /endpoints

Define request models in /payload

Write test logic in /tests

Reuse utilities like Faker and Logger

🤝 Contributing
Want to contribute? Here’s how:

Fork this repo

Create a new branch:
git checkout -b feature-name

Commit your changes:
git commit -m "Added X feature"

Push to the branch:
git push origin feature-name

Open a Pull Request 🚀

