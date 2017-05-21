package po;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Qu on 2017/5/21.
 */
public class Parent {
    private int pid;
    private String pname;
    private Set sons=new HashSet();

    public Set getSons() {
        return sons;
    }

    public void setSons(Set sons) {
        this.sons = sons;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }
}
