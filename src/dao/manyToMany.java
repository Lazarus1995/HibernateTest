package dao;

import connect.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import po.Stu;
import po.Tea;

/**
 * Created by Qu on 2017/5/22.
 */
public class manyToMany {

    @Test
    public void insertClass(){
        Session session= HibernateSessionFactory.getSession();
        Transaction transaction=session.beginTransaction();
        try{
            Tea tea=new Tea();
            tea.setTid(3);
            tea.setTname("FAN");

            Tea tea1=new Tea();
            tea1.setTid(4);
            tea1.setTname("ZHOU");

            Stu stu=new Stu();
            stu.setSid(5);
            stu.setSname("fan");

            Stu stu1=new Stu();
            stu1.setSid(6);
            stu1.setSname("zhou");

            tea.getStu().add(stu);
            stu.getTea().add(tea);
            tea.getStu().add(stu1);
            stu1.getTea().add(tea);

            tea1.getStu().add(stu);
            stu.getTea().add(tea1);
            tea1.getStu().add(stu1);
            stu1.getTea().add(tea1);

            session.save(tea);
            session.save(tea1);

            transaction.commit();
        }catch(Exception e){
            e.printStackTrace();
            transaction.rollback();
        }finally {
            HibernateSessionFactory.closeSession();
        }
    }

}
