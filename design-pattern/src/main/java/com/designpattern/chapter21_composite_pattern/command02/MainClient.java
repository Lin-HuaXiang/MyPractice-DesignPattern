package com.designpattern.chapter21_composite_pattern.command02;

import java.awt.Composite;
import java.util.List;

public class MainClient {

    public static void main(String[] args) {
        Branch ceo = compoisiteCorpTree();
        System.out.println(ceo.getInfo());
        System.out.println(getTreeInfo(ceo));
    }

    public static Branch compoisiteCorpTree() {
        Branch root = new Branch("王大麻子", "总经理", 100000);
        Branch developDep = new Branch("刘大瘸子", "研发部门经理", 10000);
        Branch salesDep = new Branch("马二拐子", "销售部门经理", 20000);
        Branch financeDep = new Branch("赵三驼子", "财务部经理", 30000);
        Branch firstDevGroup = new Branch("杨三大队", "开发一组组长", 5000);
        Branch secondDevGroup = new Branch("吴大棒槌", "开发二组组长", 6000);
        Leaf a = new Leaf("a", "开发人员", 2000);
        Leaf b = new Leaf("b", "开发人员", 2000);
        Leaf c = new Leaf("c", "开发人员", 2000);
        Leaf d = new Leaf("d", "开发人员", 2000);
        Leaf e = new Leaf("e", "开发人员", 2000);
        Leaf f = new Leaf("f", "开发人员", 2000);
        Leaf g = new Leaf("g", "开发人员", 5000);
        Leaf h = new Leaf("h", "销售人员", 4000);
        Leaf i = new Leaf("i", "销售人员", 5000);
        Leaf j = new Leaf("j", "销售人员", 6000);
        Leaf k = new Leaf("k", "CEO秘书", 8000);
        Leaf zhengLaoLiu = new Leaf("郑老六", "研发部副经理", 20000);

        root.addSubordinate(k);
        root.addSubordinate(developDep);
        root.addSubordinate(salesDep);
        root.addSubordinate(financeDep);

        developDep.addSubordinate(zhengLaoLiu);
        developDep.addSubordinate(firstDevGroup);
        developDep.addSubordinate(secondDevGroup);
        firstDevGroup.addSubordinate(a);
        firstDevGroup.addSubordinate(b);
        firstDevGroup.addSubordinate(c);
        secondDevGroup.addSubordinate(d);
        secondDevGroup.addSubordinate(e);
        secondDevGroup.addSubordinate(f);

        salesDep.addSubordinate(h);
        salesDep.addSubordinate(i);

        financeDep.addSubordinate(j);
        return root;
    }

    public static String getTreeInfo(Branch root) {
        List<Corp> subordinateList = root.getSubordinate();
        StringBuilder info = new StringBuilder();
        for (Corp s : subordinateList) {
            if (s instanceof Leaf) {
                info.append(s.getInfo()).append("\n");
            } else {
                info.append(s.getInfo()).append("\n").append(getTreeInfo((Branch) s));
            }
        }
        return info.toString()       ;
    }
}
