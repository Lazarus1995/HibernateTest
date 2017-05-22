package dao;

import connect.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Test;
import po.Parent;
import po.Sons;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
            session.update(p);
            transaction.commit();
        }catch(Exception e){
            e.printStackTrace();
            transaction.rollback();;
        }finally{
            HibernateSessionFactory.closeSession();
        }
    }

    @Test
    public void selectParent(){
        Session session = HibernateSessionFactory.getSession();
        try{
           Query query=session.createQuery("from Parent as p ");
            List<Parent> list= query.list();
            for(Parent p:list){
                System.out.println("father name is: "+p.getPname());
                Set<Sons> s=p.getSons();
                Iterator<Sons> it=s.iterator();
                while(it.hasNext()){
                    System.out.println("His sons'name are: "+it.next().getSnmae());
                }
            }
        }catch(Exception e){
            e.printStackTrace();

        }finally{
            HibernateSessionFactory.closeSession();
        }
    }



}
