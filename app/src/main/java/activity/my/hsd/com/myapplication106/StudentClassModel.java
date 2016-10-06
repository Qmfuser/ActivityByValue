package activity.my.hsd.com.myapplication106;

import java.io.Serializable;

/**
 * Created by 秦孟飞 on 2016/9/29.
 */

public class StudentClassModel implements Serializable {


    public void setClassname(String classname) {
        this.classname = classname;
    }

    public void setClass_time(String class_time) {
        this.class_time = class_time;
    }

    public String getClassname() {
        return classname;
    }

    public String getClass_time() {
        return class_time;
    }

    private String classname;
    private String class_time;

}
