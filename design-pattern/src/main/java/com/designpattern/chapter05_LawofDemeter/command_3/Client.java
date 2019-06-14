package com.designpattern.chapter05_LawofDemeter.command_3;

public class Client {

    public static void main(String[] args) {
        InstallSoftware invoker = new InstallSoftware();
        invoker.installWizard(new Wizard());
    }
}
