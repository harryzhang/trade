package com.redpack.pay.impl;

import org.springframework.stereotype.Service;

import com.redpack.pay.service.IThirdPayServiceResultParser;
import com.redpack.pay.service.ParseResult;
import com.redpack.pay.service.PayResult;

@Service("saomaPayServiceResultParse")
public class ThirdPayServiceResultParseSaomaImpl implements IThirdPayServiceResultParser  {

	@Override
	public ParseResult parse(PayResult result) {
		ParseResult parseResult = new ParseResult(result);
		return parseResult;
	}

}
