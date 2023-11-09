package CAMs_App.data;
import java.util.ArrayList;

import CAMs_App.entity.Camp;
import CAMs_App.entity.Student;
import CAMs_App.entity.User;

public class Database {

    private static ArrayList<User> database = new ArrayList<>();
    private static ArrayList<Camp> campDataBase = new ArrayList<>();

    public ArrayList<Camp> getCampDataBase() {
        return campDataBase;
    }

    public ArrayList<User> getDatabase() {
        return database;
    }

    public static void setCampDataBase(ArrayList<Camp> campDataBase) {
        Database.campDataBase = campDataBase;
    }

    public static void setDatabase(ArrayList<User> database) {
        Database.database = database;
    }


    // to incorporate service 
    public static int findUserPos(User user, ArrayList<User> database){
        int j;
        for (j=0;j<database.size();j++) {
            if (database.get(j).getUserID() == user.getUserID())
                return j;
        }
        return -1;
    }

    public static void addUserToDatabase(User user, ArrayList<User> database){
        int j = findUserPos(user, database);
        if (j!=-1) {
            database.set(j,user);
            System.out.println(user.getUserID() + " successfully added.");
            return;
        }

        database.add(user);
        System.out.println(user.getUserID() + " successfully added.");
        return;
    }

    // Camp

    public static int findCampPos(Camp camp, ArrayList<Camp> campDataBase){
        int j;
        for (j=0;j<campDataBase.size();j++) {
            if (campDataBase.get(j).getCampName() == camp.getCampName())
                return j;
        }
        return -1;
    }

    public static boolean findCamp(Camp camp, ArrayList<Camp> campDataBase){
        int j;
        for (j=0;j<campDataBase.size();j++) {
            if (campDataBase.get(j).getCampName() == camp.getCampName())
                return true;
        }
        return false;
    }

    public static void addCampToDatabase(Camp camp, ArrayList<Camp> campDataBase){
        int j = findCampPos(camp, campDataBase);
        if (j!=-1) {
            campDataBase.set(j,camp);
            System.out.println(camp.getCampName() + " successfully added.");
            return;
        }

        campDataBase.add(camp);
        System.out.println(camp.getCampName() + " successfully added.");
        return;
    }

    public static boolean removeCampFromDatabase(Camp camp, ArrayList<Camp> campDataBase){
        int j = findCampPos(camp, campDataBase);
        if (j!=-1) {
            campDataBase.remove(j);
            System.out.println("Successfully removed.");
            return true;
        }
        return false;
    }
}
