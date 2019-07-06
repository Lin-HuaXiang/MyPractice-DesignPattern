package com.designpattern.chapter13_prototype_pattern.command01;

import java.util.Random;

public class MainClient {

    /**
     * number of send accounts, this value acquire from the database
     */
    private final static int MAX_COUNT = 6;

    public static void main(String[] args) {
        // Mock sending email
        int i = 0;
        // define the prototype, this value acquire from the database
        Mail mail = new Mail(new AdvTemplate());
        mail.setTail("xx bank all right reserved");
        while (i < MAX_COUNT) {
            // Here is how each email is different
            mail.setAppellation(getRandString(5) + " gentle (lady)");
            mail.setReceiver(getRandString(5) + "@" + getRandString(8) + ".com");
            // then send email
            sendMail(mail);
            i++;
        }
    }

    /**
     * send email
     * @param mail
     */
    public static void sendMail(Mail mail) {
        System.out.println("title: " + mail.getSubject() + "\t receiver:" + mail.getReceiver() + "\t ...send success");
    }

    /**
     * gets a random string of the specified length
     * @param maxLength
     * @return
     */
    public static String getRandString(int maxLength) {
        String source = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < maxLength; i++) {
            sb.append(source.charAt(rand.nextInt(source.length())));
        }
        return sb.toString();
    }


}
