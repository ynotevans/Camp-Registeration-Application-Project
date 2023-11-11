package CAMs_App.service;
import CAMs_App.data.Database;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import CAMs_App.entity.Camp;
import CAMs_App.entity.Student;
import CAMs_App.entity.CampCompMem;
import CAMs_App.entity.Suggestions;
import CAMs_App.enums.Faculty;
import CAMs_App.entity.Enquiries;

// class provides methods for reading and writing data from / to CSV files 

public class HelperService {
    private static List<String> userCsvHeaders = new ArrayList<String>();
    private static List<String> studentCsvHeaders = new ArrayList<String>();
    private static List<String> staffCsvHeaders = new ArrayList<String>();
    private static List<String> campCompMemHeaders = new ArrayList<String>();
    
    public HelperService(){};

    public List<String[]> readCsvFile(String filePath, List<String> headers){
        List<String[]> dataList = new ArrayList<String[]>();
        headers.clear();

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String[] headerRow = br.readLine().split(",");
            for (String header : headerRow){
                headers.add(header);
            }

            String line = "";
            while ((line = br.readLine()) != null){
                String[] values = line.split(",");
                dataList.add(values);
            }
        } catch (IOException e){
            System.out.println("Cannot import data!");
        }

        return dataList;
    }

    public boolean writeCsvFile(String filePath, List<String> headers, List<String> lines){
        try (FileWriter writer = new FileWriter(filePath)){
            String headerLine = String.join("," , headers);
            writer.write(headerLine + "\n");

            for (String line:lines){
                writer.write(line + "\n");
                
            }
        } catch (IOException e){
            System.out.println("Cannot export data!");
            return false;
        }
        return true;
    }

    private Map<String, String> parseUserRow(String[] userRow){
        String userID = userRow[0];
        String password = userRow[1];
        String faculty = userRow[2];
        String status = userRow[3];
        
        Map<String, String> userInfoMap = new HashMap<String, String>();
        userInfoMap.put("userID", userID);
        userInfoMap.put("password", password);
        userInfoMap.put("faculty", faculty);
        userInfoMap.put("status", status);

        return userInfoMap;
    }

    public Map<String, Student> importStudentData(String usersFilePath, String studentsFilePath){
        Map<String, Student> studentsMap = new HashMap<String, Student>();

        List<String[]> usersRows = this.readCsvFile(usersFilePath,userCsvHeaders);
        List<String[]> studentsRows = this.readCsvFile(studentsFilePath,studentCsvHeaders);
        
        for (String[] userRow : usersRows){
            Map <String, String> userInfoMap = parseUserRow(userRow);
            
            String role = userInfoMap.get("role");
            if (!role.equals("student"));
                continue;
            
            // String userID = userInfoMap.get("userID");
            String password = userInfoMap.get("password");
            String faculty = userInfoMap.get("faculty");
            String status = userInfoMap.get("status");
                
            boolean isCommittee = false;
            for (String[] studentRow : studentsRows){
                if (!studentRow[0].equals(userID))
                    continue;

                isCommittee = Boolean.parseBoolean(studentRow[1]);
            }            
            
        
        }
        return studentsMap;
    }



}
