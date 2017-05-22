package po;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Qu on 2017/5/22.
 */
public class Tea {
    private int tid;
    private String tname;
    private Set stu=new HashSet(0);

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public Set getStu() {
        return stu;
    }

    public void setStu(Set stu) {
        this.stu = stu;
    }
}
