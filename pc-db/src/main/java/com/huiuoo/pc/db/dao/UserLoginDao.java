package com.huiuoo.pc.db.dao;

import com.huiuoo.pc.db.dataobject.UserLoginDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/5/7
 */
public interface UserLoginDao extends JpaRepository<UserLoginDO,Long> {

    UserLoginDO findByLoginTypeAndIdentity(Integer loginType, String identity);
}
