

package com.redpack.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redpack.account.dao.IBizUserAccountDao;
import com.redpack.account.model.BizUserAccountDo;
import com.redpack.member.IMemberService;
import com.redpack.member.dao.IMemberDao;
import com.redpack.member.model.KuangjiUserAccountDo;


@Service("memberService")
public class MemberServiceImpl implements IMemberService {

	private final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private IBizUserAccountDao bizUserAccountDao;
	
	@Autowired
    private IMemberDao  memberDao;
	
	@Override
	public KuangjiUserAccountDo getByUserid(long userId) {
		// TODO Auto-generated method stub
		return memberDao.getByUserId(userId);
	}

	@Override
	public int updateAccountById(KuangjiUserAccountDo memberDo) {
		// TODO Auto-generated method stub
		return memberDao.updateAccountById(memberDo);
	}

	public List<Map<String, Object>> getOrderList(long userId){
		return memberDao.getOrderList(userId);
	}

	@Override
	public int getUserReff(long userId) {
		// TODO Auto-generated method stub
		return memberDao.getUserReff(userId);
	}
	
	@Override
	public Map<String, Object> getUserAccount(long userId) {
		// TODO Auto-generated method stub
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("userId", userId);
		List<BizUserAccountDo> accountList = bizUserAccountDao.selectUserAccount(paramMap);
		
		Map<String, Object> userMap = new HashMap<String,Object>();
		for (BizUserAccountDo bizUserAccountDo : accountList) {
			String accountType = bizUserAccountDo.getAccountType();
			userMap.put(accountType, bizUserAccountDo.getAmount());
		}
		return userMap;
	}
	

}
