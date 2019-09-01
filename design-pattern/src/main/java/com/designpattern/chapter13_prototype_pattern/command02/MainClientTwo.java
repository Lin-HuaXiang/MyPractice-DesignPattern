package com.designpattern.chapter13_prototype_pattern.command02;

import com.designpattern.chapter13_prototype_pattern.command01.AdvTemplate;
import com.designpattern.chapter13_prototype_pattern.command01.Mail;
import com.designpattern.chapter13_prototype_pattern.command01.MainClient;

/**
 * @author linhuaxiang
 * @date   2019/7/7
 */
public class MainClientTwo {

    /**
     *  number of send accounts, this value acquire from the database
     */
    public static int MAX_COUNT = 6;

    public static void main(String[] args) {
        // mock send email
        int i = 0;
        // define prototype, acquire from database
        MailTwo mailTwo = new MailTwo(new AdvTemplate());
        while (i < MAX_COUNT) {
            // here is how each email is different
            MailTwo cloneMail = mailTwo.clone();
            cloneMail.setAppellation(MainClient.getRandString(6) + "gentle (lady)");
            cloneMail.setReceiver(MainClient.getRandString(5) + "@" + MainClient.getRandString(8) + ".com");
            // then send email
            sendMail(cloneMail);
            i++;
        }
    }

    /**
     * send email
     * @param mail
     */
    public static void sendMail(MailTwo mail) {
        System.out.println("title: " + mail.getSubject() + "\t receiver:" + mail.getReceiver() + "\t ...send success");
    }
}
