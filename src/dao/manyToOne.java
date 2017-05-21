package dao;

import connect.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import po.Parent;
import po.Sons;

/**
 * Created by Qu on 2017/5/21.
 */
public class manyToOne {
    @Test
    public void insertParent(){
        Session session = HibernateSessionFactory.getSession();
        Transaction transaction =null;
        try{
            transaction=session.beginTransaction();

            Parent p=new Parent();
            p.setPname("C");

            Sons s=new Sons();
            s.setSnmae("C++");
            s.setParent(p);
            p.getSons().add(s);

            Sons s1=new Sons();
            s1.setSnmae("Java");
            s1.setParent(p);
            p.getSons().add(s1);

            session.save(p);
            transaction.commit();
        }catch(Exception e){
            e.printStackTrace();
            transaction.rollback();;
        }finally{
            HibernateSessionFactory.closeSession();
        }
    }
    @Test
    public void delParent(){
        Session session = HibernateSessionFactory.getSession();
        Transaction transaction =null;
        try{
            transaction=session.beginTransaction();
//            Sons s=session.get(Sons.class,4);
//            session.delete(s);
            Parent p=session.get(Parent.class,2);
            session.delete(p);
            transaction.commit();
        }catch(Exception e){
            e.printStackTrace();
            transaction.rollback();;
        }finally{
            HibernateSessionFactory.closeSession();
        }
    }
    @Test
    public void updateParent(){
        Session session = HibernateSessionFactory.getSession();
        Transaction transaction =null;
        try{
            transaction=session.beginTransaction();
            Parent p=session.get(Parent.class,2);
            p.setPname("C#");
            ((Sons)(p.getSons().toArray()[0])).setSnmae("MS");
            ((Sons)(p.getSons().toArray()[1])).setSnmae("AP");
            transaction.commit();
        }catch(Exception e){
            e.printStackTrace();
            transaction.rollback();;
        }finally{
            HibernateSessionFactory.closeSession();
        }
    }

}
