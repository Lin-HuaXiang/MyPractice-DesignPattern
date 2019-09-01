package com.designpattern.chapter05_lawof_demeter.command_3;

public class Client {

    public static void main(String[] args) {
        InstallSoftware invoker = new InstallSoftware();
        invoker.installWizard(new Wizard());
    }
}
