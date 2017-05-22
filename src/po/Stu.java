package po;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Qu on 2017/5/22.
 */
public class Stu {
    private int sid;
    private String sname;
    private Set tea=new HashSet(0);

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Set getTea() {
        return tea;
    }

    public void setTea(Set tea) {
        this.tea = tea;
    }
}
