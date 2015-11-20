package duhblea.me.hourtrax;

import android.content.Context;
import android.content.SharedPreferences;

public class Biweek {

    private static float mon1, tue1, wed1, thur1, fri1, sat1, sun1,
            mon2, tue2, wed2, thur2, fri2, sat2, sun2;
    private static float ptb;

    public static final String MONDAY = "mon";
    public static final String TUESDAY = "tue";
    public static final String WEDNESDAY = "wed";
    public static final String THURSDAY = "thur";
    public static final String FRIDAY = "fri";
    public static final String SATURDAY = "sat";
    public static final String SUNDAY = "sun";


    private static SharedPreferences sharedPrefs;
    private static SharedPreferences.Editor editor;


    public Biweek(Context aContext) {
        sharedPrefs = aContext.getSharedPreferences("prefsval", Context.MODE_PRIVATE);


    }


    public float getHours(int week, String day) {
        float returnhour;
        switch (week) {
            case 1:
                //switch for week 1
                switch (day) {
                    case MONDAY:
                        returnhour = mon1;
                        break;
                    case TUESDAY:
                        returnhour = tue1;
                        break;
                    case WEDNESDAY:
                        returnhour = wed1;
                        break;
                    case THURSDAY:
                        returnhour = thur1;
                        break;
                    case FRIDAY:
                        returnhour = fri1;
                        break;
                    case SATURDAY:
                        returnhour = sat1;
                        break;
                    case SUNDAY:
                        returnhour = sun1;
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid day designator!" + day + " doesn't exist.");
                }
                break;
            case 2:
                //switch for week 2
                switch (day) {
                    case MONDAY:
                        returnhour = mon2;
                        break;
                    case TUESDAY:
                        returnhour = tue2;
                        break;
                    case WEDNESDAY:
                        returnhour = wed2;
                        break;
                    case THURSDAY:
                        returnhour = thur2;
                        break;
                    case FRIDAY:
                        returnhour = fri2;
                        break;
                    case SATURDAY:
                        returnhour = sat2;
                        break;
                    case SUNDAY:
                        returnhour = sun2;
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid day designator!" + day + " doesn't exist.");
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid week designator!" + week + " doesn't exist.");
        }

        return returnhour;
    }

    public void setHourValue(int week, String day, float hoursWorked) {

        switch (week) {
            case 1:
                //switch for week 1
                switch (day) {
                    case MONDAY:
                        mon1 = hoursWorked;
                        break;
                    case TUESDAY:
                        tue1 = hoursWorked;
                        break;
                    case WEDNESDAY:
                        wed1 = hoursWorked;
                        break;
                    case THURSDAY:
                        thur1 = hoursWorked;
                        break;
                    case FRIDAY:
                        fri1 = hoursWorked;
                        break;
                    case SATURDAY:
                        sat1 = hoursWorked;
                        break;
                    case SUNDAY:
                        sun1 = hoursWorked;
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid day designator!" + day + " doesn't exist.");
                }
                break;
            case 2:
                //switch for week 1
                switch (day) {
                    case MONDAY:
                        mon2 = hoursWorked;
                        break;
                    case TUESDAY:
                        tue2 = hoursWorked;
                        break;
                    case WEDNESDAY:
                        wed2 = hoursWorked;
                        break;
                    case THURSDAY:
                        thur2 = hoursWorked;
                        break;
                    case FRIDAY:
                        fri2 = hoursWorked;
                        break;
                    case SATURDAY:
                        sat2 = hoursWorked;
                        break;
                    case SUNDAY:
                        sun2 = hoursWorked;
                    default:
                        throw new IllegalArgumentException("There was an error setting the hours");
                }
                break;
            default:
                throw new IllegalArgumentException("here was an error setting the hours");
        }
    }

    public float getPTB() {
        float ptbtest = mon1 + tue1 + wed1 + thur1 + fri1;
        if (ptbtest > 40) {
            ptb = 40 - ptbtest;
        }
        else {
            ptb = 0;
        }

        return ptb;
    }

    public float calculateTotal() {
        return calculateWeek1() + calculateWeek2();
    }

    public float calculateWeek1() {
        return mon1 + tue1 + wed1 + thur1 + fri1 + sat1 + sun1;
    }

    public float calculateWeek2() {
        return mon2 + tue2 + wed2 + thur2 + fri2 + sat2 + sun2;
    }

    public float calculatePTB() {
        float ptbweek1 = calculateWeek1();
        if (ptbweek1 > 40) {
            return ptbweek1 - 40;
        }
        else {
            return 0;
        }
    }

    public void saveWeek1() {
        editor = sharedPrefs.edit();
        editor.putFloat("mon1", mon1);
        editor.putFloat("tue1", tue1);
        editor.putFloat("wed1", wed1);
        editor.putFloat("thur1", thur1);
        editor.putFloat("fri1", fri1);
        editor.putFloat("sat1", sat1);
        editor.putFloat("sun1", sun1);

        editor.commit();
    }

    public void saveWeek2() {
        editor = sharedPrefs.edit();
        editor.putFloat("mon2", mon2);
        editor.putFloat("tue2", tue2);
        editor.putFloat("wed2", wed2);
        editor.putFloat("thur2", thur2);
        editor.putFloat("fri2", fri2);
        editor.putFloat("sat2", sat2);
        editor.putFloat("sun2", sun2);

        editor.commit();
    }

    public void populateWeek1() {
        mon1 = sharedPrefs.getFloat("mon1", 0);
        tue1 = sharedPrefs.getFloat("tue1", 0);
        wed1 = sharedPrefs.getFloat("wed1", 0);
        thur1 = sharedPrefs.getFloat("thur1", 0);
        fri1 = sharedPrefs.getFloat("fri1", 0);
        sat1 = sharedPrefs.getFloat("sat1", 0);
        sun1 = sharedPrefs.getFloat("sun1", 0);
    }

    public void populateWeek2() {
        mon2 = sharedPrefs.getFloat("mon2", 0);
        tue2 = sharedPrefs.getFloat("tue", 0);
        wed2 = sharedPrefs.getFloat("wed2", 0);
        thur2 = sharedPrefs.getFloat("thur2", 0);
        fri2 = sharedPrefs.getFloat("fri2", 0);
        sat2 = sharedPrefs.getFloat("sat2", 0);
        sun2 = sharedPrefs.getFloat("sun2", 0);
    }

    @Override
    public String toString() {
        return "First week: " + mon1 + " " + tue1 + " " + wed1 + " " + thur1 + " " + fri1 + " " + sat1
                + sun1 + "\n" + "Second week: " + mon2 + " " + tue2 + " " + wed2 + " " + thur2
                + " " + fri2 + " " + sat2 + " " + sun2;
    }


}