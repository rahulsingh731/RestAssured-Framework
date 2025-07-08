package utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class DataProviders {

    @DataProvider(name = "userdata")
    public static Object[][] provideUserData() throws IOException {
        // Read Excel data using ExcelUtility
        List<Map<String, String>> data = utilityReader.readExcelData("/testdata/user_test_data.xlsx", "users");

        // Convert List<Map<String, String>> to Object[][] for TestNG
        Object[][] testData = new Object[data.size()][8]; // 8 parameters: id, username, firstName, lastName, email, password, phone, userStatus
        for (int i = 0; i < data.size(); i++) {
            Map<String, String> row = data.get(i);
            testData[i][0] = row.getOrDefault("id", ""); // Lowercase to match Excel headers
            testData[i][1] = row.getOrDefault("username", "");
            testData[i][2] = row.getOrDefault("firstName", "");
            testData[i][3] = row.getOrDefault("lastName", "");
            testData[i][4] = row.getOrDefault("email", "");
            testData[i][5] = row.getOrDefault("password", "");
            testData[i][6] = row.getOrDefault("phone", "");
            testData[i][7] = row.getOrDefault("userStatus", "");
        }
        return testData;
    }
}