# Framework

### How to run all tests on local
`./gradlew clean build`

### How to run all tests on grid
`./gradlew clean build -Dtest.on=grid`

### How to run all tests against production environment
`./gradlew clean build -Pprofile=prod`

### How to run all tests on custom url
`./gradlew clean build -Dapp.url=https://spirit.com`

### How to run all tests with specific local browser
`./gradlew clean build -Dtest.local.browser=firefox`

### How to run all tests with specific grid browser
`./gradlew clean build -Dtest.grid.browser=firefox`

### How to run all tests with supported browsers on grid in parallel
`./gradlew clean chrome firefox chromeMobile -Dtest.on=grid --continue`

### How to generate Gradle build details report
Add at the end of any build commands `--scan`.    
Then enter `yes` in command line.
You will get generated link to build report on the web page.

### Run from idea with any settings
Change values in `test` or `prod` `/config.properties` files

### Environment options
* `test`(by default)
* `prod`   
Environment option determines property file which will be used to run tests

#### Settings
* `app.url`
    * base url of tested application
* `test.on`
    * local
         * `test.local.browser`
             * firefox
             * chrome
             * chrome_mobile             
    * grid
         * `test.grid.browser`
             * firefox
             * chrome
             * chrome_mobile
         * `test.grid.url` - node url      


### How to display all gradle tasks
`./gradlew tasks --all`

### How to auto-format all code in the project
`./gradlew spotlessApply`

### Build repo
`https://travis-ci.org/MaximVer/Framework`

### Selenoid ui
`http://34.73.120.9:8080/#/`

### Allure reporting
`./gradlew allureReport - generate Allure report`  
`./gradlew allureServe - generate Allure report and opens it in the default browser`

### Jenkins
`http://34.73.169.36:8080/`

## Instruction for local environment setup
## How to setup local environment for automation.
##### For Web desktop and Web mobile tests:
On macOS X:
1. Install SDKMAN
    * Open a new terminal and enter:
    `curl -s "https://get.sdkman.io" | bash`
    * In terminal enter:
    `source "$HOME/.sdkman/bin/sdkman-init.sh"`
    * Verify installation, in terminal enter: `sdk version`, should be displayed something like: `sdkman 5.0.0+51`
2. Install Java 8
    * Open a new terminal and enter: `sdk install java 8.0.191-oracle`
    * Open a new terminal and enter: `java -version` , should be displayed `java version "1.8.0_191"`
3. Install Homebrew
    * Open terminal and enter: `/usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"`
    * Check Brew installation status by executing command: `brew doctor` expected result: no ERRORS, warnings are OK.
    * Update brew formulae by running: `brew udpate`
4. Install Gradle
    * In terminal enter: `sdk install gradle`
    * Open new terminal and enter: `gradle -version` , should be displayed something like: `Gradle 4.6`
5. Install Git
    * In Terminal enter: `git --version`
    * If you don’t have it installed already, it will prompt you to install it.
        * If Xcode/iOS license message pops up in terminal. run command: `sudo xcodebuild -license`
        * Scroll down and read license agreement. Follow on-screen hints and agree to license terms.            
        * Once accepted, re-run command again: `git --version`
    * Should be displayed something like: `git version 2.15.0` (or later)
6. Precondition to clone Test project to local environment
    * Generate rsa key to access github repo:  
    In terminal enter: `ssh-keygen -t rsa -b 4096 -C "your_email@example.com"`  
    Use the same email that is registered on GitHub.
    * Follow on-screen instructions to set key passphrase.
    * Copy the key to clipboard. In Terminal enter: `pbcopy < ~/.ssh/id_rsa.pub`
    * Go to your GitHub account -> settings -> SSH and GPG keys -> add new SSH key. Paste clipboard content into key body field. 
    * Authorize key with sso (gray pill left from Delete button.)
    * Now your rsa key is ready to use.
7. Clone the project from GitHub.
   * Create new folder (preferably in user directory root) to store automation related files and project.
   * In terminal, switch to that folder: `cd {folderName}`
   * Clone the project. In terminal enter: `git clone git@github.com:Company/project.git`  
   * Expecxted result:   
       * Receiving objects: 100% done.  
       * Resolving Deltas: 100% done.  
   * At this point automation project is cloned to your local folder.
8. Install Intellij Idea 
   * [Download Intellij Ultimate or Community Edition](https://www.jetbrains.com/idea/)
   * To Register Ultimate Edition:
       * Open IntelliJ
       * Click "Help" in the toolbar, then click "Register" in the drop down.
       * Select "License Server", then click "Discover Server".
       * Then Click "Activate". You should see a popup confirming registration.
9. Import project into IntelliJ
   * Start IntelliJ
   * Select Open Project -> select project directory to proceed.
   * In import options enable <Use auto-import> and <Use gradle wrapper (recommended)>.
   * Click OK
10. Add the Lombok IntelliJ plugin
    * In IntelliJ, go to File > Settings > Plugins
    * Click on Browse repositories...
    * Search for Lombok Plugin
    * Click on Install plugin
    * Restart IntelliJ IDEA
11. Add Spotless code auto-formatting plugin for IntelliJ
    * Install Intellij plugin “EclipseCodeFormatter”
    * Open Intellij Preferences -> Other Settings -> Eclipse Code Formatter
    * Select “User the Eclipse code formatter”
    * In “Eclipse Java Formatter config file” browse to “<project_root>/src/eclipse/spotless.eclipseformat.xml”
    * In “Import order” select “From file” and browse to “<project_root>/src/eclipse/spotless.importorder”
    * Click “Apply” and “Ok”
12. Verify local environment configuration.
    * In terminal switch to `project` project folder.
    * Run command: `./gradlew build -Dtest.on=grid`  
    It will run all tests on remote Selenoid Grid.
##### For mobile clients tests:
On macOS X:
* Install NodeJS by `brew install node`
* Install Appium by `npm install -g appium`
* Install Appium Doctor by `npm install appium-doctor -g`
####### Android client
* Install Android SDK
* Install and run one Android Emulator or connect real Android device in debug mode
* Execute `appium-doctor --android` to check that everything is installed correctly
