package com.designpattern.chapter05_LawofDemeter.command_3;

public class InstallSoftware {

    public void installWizard(Wizard wizard) {
        int first = wizard.first();
        if (first > 60) {
            int second = wizard.second();
            if (second > 60) {
                int third = wizard.third();
                if (third > 60) {
                    wizard.first();
                }
            }
        }
    }

}
