package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

import org.hibernate.query.Query;
import po.User;
import po.UserInfo;

public class UserInfoDAO {

        private Configuration cfg;
        private SessionFactory sf=null;
        private Session hsession=null;
        private Transaction ts=null;

        public UserInfo addUserInfo(UserInfo newUserInfo) {
            SessionMgr.getSession(cfg, sf, hsession, ts);
            try{
                UserInfo u = (UserInfo) hsession.save(newUserInfo);
                SessionMgr.releaseConnect(sf, hsession);

                return u;
            }catch(Exception e) {
                e.printStackTrace();
                SessionMgr.releaseConnect(sf, hsession);
                return null;
            }
        }

        public UserInfo modifyUserInfo(UserInfo newUserInfo) {
            SessionMgr.getSession(cfg, sf, hsession, ts);
            try{
                 hsession.update(newUserInfo);
                 SessionMgr.releaseConnect(sf,hsession);
                 return newUserInfo;
            }catch(Exception e) {
                e.printStackTrace();
                SessionMgr.releaseConnect(sf, hsession);
                return null;
            }
        }

        public UserInfo getUserInfo(String userId) {
            SessionMgr.getSession(cfg, sf, hsession, ts);
            try{
                UserInfo u = (UserInfo) hsession.get(UserInfo.class,userId);
                SessionMgr.releaseConnect(sf,hsession);
                return u;
            }catch (Exception e) {
                e.printStackTrace();
                SessionMgr.releaseConnect(sf, hsession);
                return null;
            }
        }

        public List getAllUserWithInfo() {
            SessionMgr.getSession(cfg, sf, hsession, ts);
            try{
                String hql = "from UserInfo";
                Query q= hsession.createQuery(hql);
                List t = q.list();

                SessionMgr.releaseConnect(sf,hsession);
                return t;
            }catch (Exception e) {
                e.printStackTrace();
                SessionMgr.releaseConnect(sf, hsession);
                return null;
            }
        }

}
