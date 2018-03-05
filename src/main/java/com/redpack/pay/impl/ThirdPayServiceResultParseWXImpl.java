package com.redpack.pay.impl;

import org.springframework.stereotype.Service;

import com.redpack.pay.service.IThirdPayServiceResultParser;
import com.redpack.pay.service.ParseResult;
import com.redpack.pay.service.PayResult;

@Service("wxPayServiceResultParse")
public class ThirdPayServiceResultParseWXImpl implements IThirdPayServiceResultParser  {

	@Override
	public ParseResult parse(PayResult result) {
		ParseResult parseResult = new ParseResult(result);
		parseResult.setFinish(false);
		if(result.succes.equals(result.getPayReturnVal()) ){
			parseResult.setSuccess(true);
		}else{
			parseResult.setSuccess(false);
		}
		return parseResult;
	}

}
