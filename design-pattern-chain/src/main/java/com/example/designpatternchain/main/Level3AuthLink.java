package com.example.designpatternchain.main;

import java.text.ParseException;
import java.util.Date;

public class Level3AuthLink extends AuthLink {

    private Date beginDate = f.parse("2020-06-11 00:00:00");
    private Date endDate = f.parse("2020-06-20 23:59:59");

    public Level3AuthLink(String levelUserId, String levelUserName) throws ParseException {
        super(levelUserId, levelUserName);
    }

    @Override
    public AuthInfo doAuth(String uId, String orderId, Date authDate) {
        Date date = AuthService.queryAuthInfo(levelUserId, orderId);
        // 表示还没有负责人，需要等待负责人就位
        if (null == date) {
            return new AuthInfo("0001", "单号：", orderId, " 状态：待三级审批负责⼈ ", levelUserName);
        }
        // 没有下一级，那就直接审批
        AuthLink next = super.next();
        if (null == next) {
            return new AuthInfo("0000", "单号：", orderId, " 状态：三级审批负责人完成", " 时间：", f.format(date), " 审批⼈人：", levelUserName);
        }
        // 如果是有审批日期之外的，表示当前级别可以审批，直接审批
        if (authDate.before(beginDate) || authDate.after(endDate)) {
            return new AuthInfo("0000", "单号：", orderId, " 状态：三级审批负责⼈完成", " 时间：", f.format(date), " 审批⼈人：", levelUserName);
        }
        return next.doAuth(uId, orderId, authDate);

    }

    

    
}
