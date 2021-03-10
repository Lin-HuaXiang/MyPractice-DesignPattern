package com.example.designpatterniterator.group;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.example.designpatterniterator.lang.Collection;
import com.example.designpatterniterator.lang.Iterator;

public class GroupStructure implements Collection<Employee, Link> {

    // 组织id，也是一个组织链的头部id
    private String groupId;
    // 组织名称
    private String groupName;
    // 雇员列表
    private Map<String, Employee> employeeMap = new ConcurrentHashMap<>();
    // 组织架构关系 id -> list，一个人的下属
    private Map<String, List<Link>> linkMap = new ConcurrentHashMap<>();
    // 反向关系链，每个雇员的上司
    private Map<String, String> invertedMap = new ConcurrentHashMap<>();
    
    public GroupStructure(String groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }

    @Override
    public boolean add(Employee employee) {
        return null != employeeMap.put(employee.getUId(), employee);
    }

    @Override
    public boolean remove(Employee employee) {
        return null != employeeMap.remove(employee.getUId());
    }

    @Override
    public boolean addLink(String key, Link link) {
        invertedMap.put(link.getToId(), link.getFromId());
        if (linkMap.containsKey(key)) {
            return linkMap.get(key).add(link);
        } else {
            List<Link> links = new LinkedList<>();
            links.add(link);
            linkMap.put(key, links);
            return true;
        }
    }

    @Override
    public boolean removeLink(String key) {
        return null != linkMap.remove(key);
    }

    @Override
    public Iterator<Employee> iterator() {
        return new Iterator<Employee>(){

            Map<String, Integer> keyMap =  new HashMap<>();
            
            int totalIdx = 0;
            // 雇员Id，From
            private String fromId = groupId;
            // 雇员id，To
            private String toId = groupId;

            @Override
            public boolean hasNext() {
                return totalIdx < employeeMap.size();
            }

            @Override
            public Employee next() {
                
                List<Link> links = linkMap.get(toId);
                int cursorIdx = getCursorIdx(toId);

                // 同级节点扫描，links表示没有子节点
                if  (null == links) {
                    cursorIdx = getCursorIdx(fromId);
                    links = linkMap.get(fromId);
                }

                // 上级节点扫描，表示当前同级节点已经全部遍历完成，需要找上级同级节点
                while (cursorIdx > links.size() - 1) {
                    fromId = invertedMap.get(fromId);
                    cursorIdx = getCursorIdx(fromId);
                    links = linkMap.get(fromId);
                }

                // 获取节点
                Link link = links.get(cursorIdx);
                toId = link.getToId();
                fromId = link.getFromId();
                totalIdx++;
                
                return employeeMap.get(link.getToId());
            }

            // 给每个层级定义宽度遍历进度
            public int getCursorIdx(String key) {
                int idx = 0;
                if (keyMap.containsKey(key)) {
                    idx = keyMap.get(key);
                    keyMap.put(key, ++idx);
                } else {
                    keyMap.put(key, idx);
                }
                return idx;
            }
            

        };
    }


    

}
