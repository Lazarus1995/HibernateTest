package dao;

import connect.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Test;
import po.Hus;
import po.Wife;

import java.util.List;

/**
 * Created by Qu on 2017/5/20.
 */
public class oneToOne {
    @Test
    public void insertFaimly(){
        Session session= HibernateSessionFactory.getSession();
        Transaction transaction=null;
        try{
            transaction= session.beginTransaction();
            Hus hus=new Hus();
            hus.setHname("Black");
            hus.setId(1);
            Wife wife=new Wife();
            wife.setWname("Belle");
            wife.setId(1);
            hus.setWife(wife);
            wife.setHus(hus);

            session.save(hus);
            transaction.commit();
        }catch(Exception e){

        }finally {
            HibernateSessionFactory.closeSession();
        }
    }

    @Test
    public void updateFaimly(){
        Session session= HibernateSessionFactory.getSession();
        Transaction transaction=null;
        try{
            transaction= session.beginTransaction();

            Hus hus=new Hus();
            hus=session.get(Hus.class,9);
            hus.setHname("Max");
            hus.getWife().setWname("Mary");

            session.update(hus);
            transaction.commit();
        }catch(Exception e){

        }finally {
            HibernateSessionFactory.closeSession();
        }
    }

    @Test
    public void quaryAll(){
        Session session=HibernateSessionFactory.getSession();
        Transaction transaction=null;
        try{
            String hql="from Hus as h order by id desc";
            Query query=session.createQuery(hql);
            List list=query.list();
            for(int i=0;i<list.size();i++){
                Hus hus=(Hus)list.get(i);
                System.out.println("id is: "+hus.getHname()+"; wife is: "+hus.getWife().getWname());
            }
        }catch(Exception e){
            transaction.rollback();;
            e.printStackTrace();
        }finally {
            HibernateSessionFactory.closeSession();
        }
    }
}
