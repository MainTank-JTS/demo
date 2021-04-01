package com.jts.demo.busi;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jts.demo.busi.dao.TblUserDao;
import com.jts.demo.busi.entity.TblUser;
import com.jts.demo.interceptor.ImportantInterceptor;
import com.jts.demo.mq.disruptor.MessageBo;
import com.jts.demo.proxy.IUserOp;
import com.jts.demo.proxy.User;
import com.jts.demo.proxy.enhancer.UserEnhaner;
import com.jts.demo.proxy.jdk.UserJdk;
import com.lmax.disruptor.RingBuffer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author jts
 */
@Slf4j
@Service
public class BusiService {

    private final RingBuffer<MessageBo> messageBoRingBuffer;

    private UserJdk userJdk;

    private UserEnhaner userEnhaner;

    private TblUserDao tblUserDao;

    public BusiService(RingBuffer<MessageBo> messageBoRingBuffer) {
        this.messageBoRingBuffer = messageBoRingBuffer;
    }

    @Autowired
    public void setUserJdk(UserJdk userJdk) {
        this.userJdk = userJdk;
    }

    @Autowired
    public void setUserEnhaner(UserEnhaner userEnhaner) {
        this.userEnhaner = userEnhaner;
    }

    @Autowired
    public void setTblUserDao(TblUserDao tblUserDao) {
        this.tblUserDao = tblUserDao;
    }

    @ImportantInterceptor("getStr")
    public String busiIndex(String param) {
        log.info("invocked BusiService.busiIndex[{}]", param);
        int id = 1;
        if (Objects.nonNull(param)) {
            try {
                id = Integer.parseInt(param);
            } catch (NumberFormatException e) {
                log.warn("NumberFormatException[{}]", param, e);
            }
        }
        Page<TblUser> tblUserPage = new Page<>(2, 3);
        Wrapper<TblUser> wrapper = Wrappers.<TblUser>lambdaQuery()
                .isNotNull(TblUser::getId);
        IPage<TblUser> userPage = tblUserDao.selectPage(tblUserPage, wrapper);
        log.info("Page tblUserList [{}]", userPage.getRecords());
        TblUser tblUerBySql = tblUserDao.getById(id);
        log.info("Sql id [{}],tblUser [{}]", id, tblUerBySql);
        invockUserJdk().getInfo();
        invockUserEnhancer().getInfo();
        StringJoiner res = new StringJoiner("_");
        res.add("busiIndex").add(param).add(tblUerBySql.toString());
        pushDisruptor(param);
        log.info("res BusiService.busiIndex[{}]", res);
        return res.toString();
    }

    private void pushDisruptor(String param) {
        long seq = messageBoRingBuffer.next();
        try {
            MessageBo messageBo = messageBoRingBuffer.get(seq);
            messageBo.setMsg(param);
        } finally {
            messageBoRingBuffer.publish(seq);
            log.info("Seq[{}] push to disruptor", seq);
        }
    }

    private IUserOp invockUserJdk() {
        userJdk.bind(new User());
        return userJdk.newUser();
    }

    private IUserOp invockUserEnhancer() {
        return userEnhaner.newUser();
    }
}
