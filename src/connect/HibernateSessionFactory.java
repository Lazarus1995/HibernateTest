package connect;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {
    // ָ��Ҫ��ȡ�����ļ�·��
    private static String CONFIG_FILE_LOCATION = "/hibernate.cfg.xml";
    // ʵ����ThreadLocal��
    private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
    // ʵ����Configuration�࣬���ò�����Hibernate������SessionFactory
    private static Configuration configuration = new Configuration();
    // ����SessionFactory�ӿڣ���ʼ��Hibernate
    private static SessionFactory sessionFactory;
    // ����configFile��������ֵ5
    private static String configFile = CONFIG_FILE_LOCATION;

    static {
        try {
            // ��ȡĬ�ϵ������ļ�hibernate.cfg.xml
            configuration.configure(configFile);
            // ʵ����SessionFactory
            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private HibernateSessionFactory() {
    }// �����޲ε�HibernateSessionFactory���캯��

    // ��ȡSession
    public static Session getSession() throws HibernateException {
        Session session = (Session) threadLocal.get();
        // �ж��Ƿ��Ѿ�����Session����
        if (session == null || !session.isOpen()) {
            // ���SessionFactory����Ϊnull���򴴽�SessionFactory
            if (sessionFactory == null) {
                rebuildSessionFactory();//����rebuildSessionFactory��������SessionFactory
            }
            // �ж�SessionFactory�����Ƿ�Ϊnull��������ǣ����Session
            session = (sessionFactory != null) ? sessionFactory.openSession() : null;
            threadLocal.set(session);
        }

        return session;
    }

    // ����SessionFactory
    public static void rebuildSessionFactory() {
        try {
            configuration.configure(configFile);
            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // �ر�Session
    public static void closeSession() throws HibernateException {
        Session session = (Session) threadLocal.get();
        threadLocal.set(null);
        if (session != null) {
            session.close();
        }
    }

    // SessionFactory�����get()����
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    // configFile���Ե�setXXX()����
    public static void setConfigFile(String configFile) {
        HibernateSessionFactory.configFile = configFile;
        sessionFactory = null;
    }

    // configFile���Ե�getXXX()����
    public static Configuration getConfiguration() {
        return configuration;
    }

    public static void main(String[] args) {
        Session session = HibernateSessionFactory.getSession();
        session.beginTransaction();
        System.out.println("Success");
    }
}