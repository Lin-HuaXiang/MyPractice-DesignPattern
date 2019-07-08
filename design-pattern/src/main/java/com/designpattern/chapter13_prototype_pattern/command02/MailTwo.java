package com.designpattern.chapter13_prototype_pattern.command02;

import com.designpattern.chapter13_prototype_pattern.command01.AdvTemplate;

/**
 * @author linhuaxiang
 * @date   2019/7/7
 */
public class MailTwo implements Cloneable {

    /**
     * addressee
     */
    private String receiver;

    /**
     * email name
     */
    private String subject;

    /**
     * appellation
     */
    private String appellation;

    /**
     * email context
     */
    private String context;

    /**
     * email tail, they usually add "xxx all right reserved" and so on
     */
    private String tail;

    // constructor

    public MailTwo(AdvTemplate advTemplate) {
        this.context = advTemplate.getAdvContext();
        this.subject = advTemplate.getAdvSubject();
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAppellation() {
        return appellation;
    }

    public void setAppellation(String appellation) {
        this.appellation = appellation;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getTail() {
        return tail;
    }

    public void setTail(String tail) {
        this.tail = tail;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "receiver='" + receiver + '\'' +
                ", subject='" + subject + '\'' +
                ", appellation='" + appellation + '\'' +
                ", context='" + context + '\'' +
                ", tail='" + tail + '\'' +
                '}';
    }

    @Override
    protected MailTwo clone() {
        MailTwo mailTwo = null;
        try {
            mailTwo = (MailTwo) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return mailTwo;
    }
}
