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
//public class SplitGroupNodeTest extends BaseTestCase {
//
//    @Autowired
//    ChainNode splitGroupNode;
//
//    @Test
//    public void execute() {
//        ChainParam param = new ChainParam();
//        UserDo user = new UserDo();
//        user.setId(1744L);
//        param.setUserDo(user);
//
//        param.setAttr(Constant.GROUP_NAME, "GA2016040200001");
//        splitGroupNode.execute(param);
//    }
//}
