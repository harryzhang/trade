//package com.redpacket.test.nodes;
//
//import com.redpack.account.model.UserDo;
//import com.redpack.service.chain.ChainNode;
//import com.redpack.service.chain.ChainParam;
//import com.redpack.service.chain.Constant;
//import com.redpacket.test.BaseTestCase;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
///**
// * Created by eiven on 2016/10/23.
// */
//public class JingTaiJiangNodeTest extends BaseTestCase {
//
//    @Autowired
//    ChainNode jingTaiJiangNode;
//
//    @Test
//    public void execute() {
//        UserDo userDo = new UserDo();
//        userDo.setId(1824L);
//
//        ChainParam param = new ChainParam();
//        param.setUserDo(userDo);
//        param.setAttr(Constant.GROUP_NAME, "GB2016110209296");
//        param.setAttr(Constant.GROUP_TYPE,"B");
//        jingTaiJiangNode.execute(param);
//    }
//}
