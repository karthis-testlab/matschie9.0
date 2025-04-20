Kindly create "secret.properties" file under the "src/test/resources/" mentioned location.

# Service Now API Secret Text
service.now.password=your servicenow instance passowrd

service.now.client.id=your servicenow oauth client id

service.now.client.secret=your servicenow oauth client secret

# JIRA API Secret Text
jira.api.token=your jira api token

# rest-full booker api secret info
booker.password=booker api password

# Allure Report Integration
Step 1: Add depenceies for TestNG and Cucumber in pom.xml
Step 2: Create one properties file as name "allure.properties" under "src/test/resources" folder
Step 3: Install allure npm package globally using command "npm install -g allure-commandline"
Step 4: Confirm the allure report installaisation type below command "allure --version"
Step 5: To generate the html report "allure generate allure-results -o reports/allure-html-report --clean"
Step 6: In-order to view the allure html report we need to type "allure serve"
Step 7: Add below package name in the @CucumberOptions plugin porperty "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"
