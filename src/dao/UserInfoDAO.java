package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import org.hibernate.query.Query;
import po.UserInfo;

public class UserInfoDAO {
        //每个DAO类中只用Session 和 Transaction 不需要 Configuration SessionFactory
        private Session hsession=null;
        private Transaction ts=null;

        private void getSession()
        {
            //从SessionMgr获取Session和Transaction
            Object[] connectionList=SessionMgr.getSession();
            hsession=(Session)connectionList[0];
            ts=(Transaction)connectionList[1];
        }

        private void releaseSession()
        {
            SessionMgr.releaseConnect(hsession,ts);
        }

        private void releaseSession(Session hsession)
        {
            SessionMgr.releaseConnect(hsession);
        }

    /**
     * 测试通过
     * 增加新详细用户信息
     * @param newUserInfo 要保存的新详细信息
     * @return 新详细信息
     */
    public UserInfo addUserInfo(UserInfo newUserInfo) throws Exception{
           getSession();
            try{
                hsession.save(newUserInfo);
                releaseSession();

                return newUserInfo;
            }catch(Exception e) {
                releaseSession(hsession);
                return null;
            }
        }

    /**
     * 测试通过
     * @param newUserInfo 要保存的新用户信息
     * @return 保存的新用户信息
     */
    public UserInfo modifyUserInfo(UserInfo newUserInfo) throws Exception{
            getSession();
            try{
                 hsession.update(newUserInfo);
                releaseSession();
                 return newUserInfo;
            }catch(Exception e) {
                releaseSession(hsession);
                return null;
            }
        }

    /**
     * 测试通过
     * 获取某个用户的信息
     * @param userId 用户的id
     * @return 用户信息类
     */
    public UserInfo getUserInfo(String userId) throws Exception{
            getSession();
            try{
                UserInfo u = (UserInfo) hsession.get(UserInfo.class,userId);
                releaseSession();
                return u;
            }catch (Exception e) {
                releaseSession(hsession);
                return null;
            }
        }

    /**
     * 测试通过
     * @return 所有用户详细信息列表
     */
    public List<UserInfo> getAllUserWithInfo() throws Exception{
            getSession();
            try{
                String hql = "from UserInfo";
                Query q= hsession.createQuery(hql);
                List<UserInfo> t = q.list();

                releaseSession();
                return t;
            }catch (Exception e) {
                releaseSession(hsession);
                return null;
            }
        }

}
