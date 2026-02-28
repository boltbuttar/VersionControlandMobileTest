# Mobile Test Automation Framework

[![CI](https://github.com/boltbuttar/VersionControlandMobileTest/actions/workflows/ci.yml/badge.svg)](https://github.com/boltbuttar/VersionControlandMobileTest/actions/workflows/ci.yml)
[![Java](https://img.shields.io/badge/Java-11-orange.svg)](https://www.oracle.com/java/)
[![Appium](https://img.shields.io/badge/Appium-9.0.0-purple.svg)](https://appium.io/)
[![TestNG](https://img.shields.io/badge/TestNG-7.8.0-green.svg)](https://testng.org/)

## Project Overview

This is a comprehensive mobile test automation framework built using **Java**, **Appium**, and **TestNG**. The framework follows the **Page Object Model (POM)** design pattern for maintainable and scalable test automation.

### Key Features

- **Page Object Model (POM)** - Separation of test logic from page elements
- **TestNG Framework** - Robust test management and parallel execution
- **Extent Reports** - Beautiful HTML reports with screenshots
- **CI/CD Integration** - GitHub Actions pipeline for automated testing
- **Parallel Execution** - Support for running tests in parallel
- **Screenshot Capture** - Automatic screenshots on test failure

## Project Structure

```
VersionControlandMobileTest/
├── .github/
│   └── workflows/
│       └── ci.yml                    # GitHub Actions CI pipeline
├── src/
│   ├── main/java/com/mobiletest/
│   │   ├── config/
│   │   │   └── AppiumConfig.java     # Appium driver configuration
│   │   ├── pages/
│   │   │   ├── BasePage.java         # Base class for all pages
│   │   │   ├── LoginPage.java        # Login page elements & actions
│   │   │   ├── HomePage.java         # Home page elements & actions
│   │   │   ├── SignupPage.java       # Signup page elements & actions
│   │   │   ├── ProfilePage.java      # Profile page elements & actions
│   │   │   ├── SettingsPage.java     # Settings page elements & actions
│   │   │   ├── CartPage.java         # Cart page elements & actions
│   │   │   ├── ItemDetailPage.java   # Item detail page
│   │   │   └── ...                   # Other page objects
│   │   └── utils/
│   │       ├── WaitUtils.java        # Wait helper methods
│   │       ├── ScreenshotUtils.java  # Screenshot capture utilities
│   │       └── ExtentReportManager.java  # Report generation
│   └── test/
│       ├── java/com/mobiletest/tests/
│       │   ├── BaseTest.java         # Base test class
│       │   ├── TestListener.java     # TestNG listener
│       │   ├── LoginTests.java       # Login test cases
│       │   ├── HomePageTests.java    # Home page test cases
│       │   └── SignupTests.java      # Signup test cases
│       └── resources/
│           ├── config.properties     # Configuration properties
│           └── testng.xml            # TestNG suite configuration
├── pom.xml                           # Maven dependencies
└── README.md                         # This file
```

## Tools and Technologies

| Tool/Technology | Version | Purpose |
|----------------|---------|---------|
| Java | 11 | Programming language |
| Maven | 3.8+ | Build and dependency management |
| Appium | 9.0.0 | Mobile automation framework |
| Selenium | 4.15.0 | WebDriver support |
| TestNG | 7.8.0 | Test framework |
| Extent Reports | 5.1.1 | Test reporting |
| Log4j | 2.22.0 | Logging |

## Prerequisites

Before running the tests, ensure you have the following installed:

1. **Java JDK 11** or higher
   ```bash
   java -version
   ```

2. **Maven 3.8+**
   ```bash
   mvn -version
   ```

3. **Node.js 18+** (for Appium)
   ```bash
   node -version
   ```

4. **Appium Server**
   ```bash
   npm install -g appium
   appium driver install uiautomator2
   ```

5. **Android SDK** with:
   - Android Studio (recommended)
   - Android Emulator or real device
   - Platform Tools (adb)

## Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/boltbuttar/VersionControlandMobileTest.git
cd VersionControlandMobileTest
```

### 2. Install Dependencies

```bash
mvn clean install -DskipTests
```

### 3. Configure the Application

Edit `src/test/resources/config.properties`:

```properties
# Appium Server
appium.server.url=http://127.0.0.1:4723

# Device Configuration
device.name=Android Emulator
platform.name=Android

# Application Configuration
app.package=com.example.app
app.activity=com.example.app.MainActivity

# Or use APK path
# app.path=src/test/resources/app.apk
```

### 4. Start Android Emulator

```bash
# List available emulators
emulator -list-avds

# Start emulator
emulator -avd <emulator_name>
```

### 5. Start Appium Server

```bash
appium
```

## How to Run Tests

### Run All Tests

```bash
mvn clean test
```

### Run Specific Test Class

```bash
mvn test -Dtest=LoginTests
```

### Run with TestNG XML

```bash
mvn test -DsuiteXmlFile=src/test/resources/testng.xml
```

### Run with Parallel Execution

```bash
mvn test -Dparallel=classes -DthreadCount=2
```

## Test Cases

The framework includes **21+ automated test cases** covering:

### Login Module (8 Tests)
| Test ID | Test Case | Priority |
|---------|-----------|----------|
| TC001 | Successful login with valid credentials | High |
| TC002 | Login fails with invalid username | High |
| TC003 | Login fails with invalid password | High |
| TC004 | Login fails with empty credentials | Medium |
| TC005 | Remember Me checkbox functionality | Low |
| TC006 | Navigate to Forgot Password page | Medium |
| TC007 | Navigate to Signup page | Medium |
| TC008 | Login page UI elements verification | Low |

### Home Page Module (9 Tests)
| Test ID | Test Case | Priority |
|---------|-----------|----------|
| TC009 | Home page loads after login | High |
| TC010 | Welcome message displayed | Medium |
| TC011 | Navigate to Profile page | Medium |
| TC012 | Navigate to Settings page | Medium |
| TC013 | Navigate to Cart page | High |
| TC014 | Navigate to Favorites page | Medium |
| TC015 | Item list displayed | High |
| TC016 | Click item opens detail page | High |
| TC017 | Logout functionality | High |

### Signup Module (4 Tests)
| Test ID | Test Case | Priority |
|---------|-----------|----------|
| TC018 | Signup page loads correctly | Medium |
| TC019 | Successful user registration | High |
| TC020 | Signup fails with mismatched passwords | Medium |
| TC021 | Signup requires terms acceptance | Medium |

## CI/CD Pipeline

### GitHub Actions Workflow

The CI pipeline (`.github/workflows/ci.yml`) automatically:

1. **Build Job**
   - Compiles the Java project
   - Validates dependencies
   - Caches Maven packages

2. **Test Job**
   - Sets up Android emulator
   - Installs and starts Appium
   - Executes test suite
   - Uploads test reports and screenshots

3. **Code Quality Job**
   - Runs code style checks
   - Generates dependency reports

### Pipeline Triggers

- **Push** to `main` or `develop` branches
- **Pull Requests** to `main` branch

### Viewing Results

1. Go to **Actions** tab in GitHub
2. Click on the workflow run
3. Download artifacts (test reports, screenshots)

## Git Workflow

### Branching Strategy

```
main (protected)
  └── develop
       ├── feature/login-tests
       ├── feature/home-tests
       └── feature/signup-tests
```

### Rules

1. **No direct commits to `main`**
2. All changes through **Pull Requests**
3. Code review required before merge
4. Feature branches for new functionality
5. Meaningful commit messages

### Commit Message Format

```
<type>: <description>

Types: feat, fix, test, docs, refactor, chore
Example: feat: add login test cases
```

## Test Reports

After test execution, reports are generated in:

- **TestNG Reports**: `target/surefire-reports/`
- **Extent Reports**: `test-output/reports/`
- **Screenshots**: `test-output/screenshots/`

### Sample Report

The Extent Report includes:
- Test execution summary
- Pass/Fail statistics
- Screenshots for failed tests
- System information
- Detailed step logs

## Team Members

| Name | Role | Responsibilities |
|------|------|-----------------|
| Member 1 | Developer | Login & Home tests, CI setup |
| Member 2 | Developer | Signup tests, Documentation |

## Troubleshooting

### Common Issues

1. **Appium server not starting**
   ```bash
   # Check if port is in use
   lsof -i :4723
   # Kill process and restart
   ```

2. **Emulator not detected**
   ```bash
   adb devices
   adb kill-server && adb start-server
   ```

3. **Element not found**
   - Verify locators using Appium Inspector
   - Increase implicit/explicit wait times

## License

This project is created for educational purposes as part of the Version Control and Mobile Test Automation assignment.

## Contact

For questions or issues, please create a GitHub Issue in this repository.
