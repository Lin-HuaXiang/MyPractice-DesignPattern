package com.example.designpatterncomposite;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.designpatterncomposite.domain.model.aggregates.TreeRich;
import com.example.designpatterncomposite.domain.model.vo.EngineResult;
import com.example.designpatterncomposite.domain.model.vo.TreeNode;
import com.example.designpatterncomposite.domain.model.vo.TreeNodeLink;
import com.example.designpatterncomposite.domain.model.vo.TreeRoot;
import com.example.designpatterncomposite.domain.service.engine.IEngine;
import com.example.designpatterncomposite.domain.service.engine.impl.TreeEngineHandle;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DesignPatternCompositeApplication.class)
@AutoConfigureMockMvc
class DesignPatternCompositeApplicationTests {


	private TreeRich treeRich;

	@BeforeEach
	public void before() {
		// 节点：1
		TreeNode treeNode01 = new TreeNode();
		treeNode01.setTreeId(10001L);
		treeNode01.setTreeNodeId(1L);
		treeNode01.setNodeType(1);
		treeNode01.setNodeValue(null);
		treeNode01.setRuleKey("userGender");
		treeNode01.setRuleDesc("用户性别[男/女]");
		// 链接：1->11
		TreeNodeLink treeNodeLink11 = new TreeNodeLink();
		treeNodeLink11.setNodeIdFrom(1L);
		treeNodeLink11.setNodeIdTo(11L);
		treeNodeLink11.setRuleLimitType(1);
		treeNodeLink11.setRuleLimitValue("man");
		// 链接：1->12
		TreeNodeLink treeNodeLink12 = new TreeNodeLink();
		treeNodeLink12.setNodeIdFrom(1L);
		treeNodeLink12.setNodeIdTo(12L);
		treeNodeLink12.setRuleLimitType(1);
		treeNodeLink12.setRuleLimitValue("woman");
		// 节点1设置连接 11 12
		List<TreeNodeLink> treeNodeLinkList1 = new ArrayList<>();
		treeNodeLinkList1.add(treeNodeLink11);
		treeNodeLinkList1.add(treeNodeLink12);
		treeNode01.setTreeNodeLinkList(treeNodeLinkList1);
		// 节点11
		TreeNode treeNode11 = new TreeNode();
		treeNode11.setTreeId(10001L);
		treeNode11.setTreeNodeId(11L);
		treeNode11.setNodeType(1);
		treeNode11.setNodeValue(null);
		treeNode11.setRuleKey("userAge");
		treeNode11.setRuleDesc("用户年龄");
		// 链接：11->111
		TreeNodeLink treeNodeLink111 = new TreeNodeLink();
		treeNodeLink111.setNodeIdFrom(11L);
		treeNodeLink111.setNodeIdTo(111L);
		treeNodeLink111.setRuleLimitType(3);
		treeNodeLink111.setRuleLimitValue("25");
		// 链接：11->112
		TreeNodeLink treeNodeLink112 = new TreeNodeLink();
		treeNodeLink112.setNodeIdFrom(11L);
		treeNodeLink112.setNodeIdTo(112L);
		treeNodeLink112.setRuleLimitType(5);
		treeNodeLink112.setRuleLimitValue("25");
		// 节点11 设置链接 111 112
		List<TreeNodeLink> treeNodeLinkList11 = new ArrayList<>();
		treeNodeLinkList11.add(treeNodeLink111);
		treeNodeLinkList11.add(treeNodeLink112);
		treeNode11.setTreeNodeLinkList(treeNodeLinkList11);
		// 节点：12
		TreeNode treeNode12 = new TreeNode();
		treeNode12.setTreeId(10001L);
		treeNode12.setTreeNodeId(12L);
		treeNode12.setNodeType(1);
		treeNode12.setNodeValue(null);
		treeNode12.setRuleKey("userAge");
		treeNode12.setRuleDesc("用户年龄");
		// 链接 12->121
		TreeNodeLink treeNodeLink121 = new TreeNodeLink();
		treeNodeLink121.setNodeIdFrom(12L);
		treeNodeLink121.setNodeIdTo(121L);
		treeNodeLink121.setRuleLimitType(3);
		treeNodeLink121.setRuleLimitValue("25");
		// 链接 12->122
		TreeNodeLink treeNodeLink122 = new TreeNodeLink();
		treeNodeLink122.setNodeIdFrom(12L);
		treeNodeLink122.setNodeIdTo(121L);
		treeNodeLink122.setRuleLimitType(5);
		treeNodeLink122.setRuleLimitValue("25");
		// 节点12 设置链接 121 122
		List<TreeNodeLink> treeNodeLinkList12 = new ArrayList<>();
		treeNodeLinkList12.add(treeNodeLink121);
		treeNodeLinkList12.add(treeNodeLink122);
		treeNode12.setTreeNodeLinkList(treeNodeLinkList12);
		// 节点：111
		TreeNode treeNode111 = new TreeNode();
		treeNode111.setTreeId(10001L);
		treeNode111.setTreeNodeId(111L);
		treeNode111.setNodeType(2);
		treeNode111.setNodeValue("果实A");
		// 节点：112
		TreeNode treeNode112 = new TreeNode();
		treeNode112.setTreeId(10001L);
		treeNode112.setTreeNodeId(112L);
		treeNode112.setNodeType(2);
		treeNode112.setNodeValue("果实B");
		// 节点：121
		TreeNode treeNode121 = new TreeNode();
		treeNode121.setTreeId(10001L);
		treeNode121.setTreeNodeId(121L);
		treeNode121.setNodeType(2);
		treeNode121.setNodeValue("果实C");
		// 节点：122
		TreeNode treeNode122 = new TreeNode();
		treeNode122.setTreeId(10001L);
		treeNode122.setTreeNodeId(122L);
		treeNode122.setNodeType(2);
		treeNode122.setNodeValue("果实D");
		// 树根
		TreeRoot treeRoot = new TreeRoot();
		treeRoot.setTreeId(10001L);
		treeRoot.setTreeRootNodeId(1L);
		treeRoot.setTreeName("规则决策树");
		// 封装所有节点
		Map<Long, TreeNode> treeNodeMap = new HashMap<>();
		treeNodeMap.put(1L, treeNode01);
		treeNodeMap.put(11L, treeNode11);
		treeNodeMap.put(12L, treeNode12);
		treeNodeMap.put(111L, treeNode111);
		treeNodeMap.put(112L, treeNode112);
		treeNodeMap.put(121L, treeNode121);
		treeNodeMap.put(122L, treeNode122);
		treeRich = new TreeRich(treeRoot, treeNodeMap);
	}

	@AfterEach
	public void after() {
		//
	}


	@Test
	public void testTree() {
		log.info("决策树组合结构信息：\r\n {}", treeRich);

		IEngine treeEngineHandle = new TreeEngineHandle();
		Map<String, String> decisionMatter = new HashMap<>();
		decisionMatter.put("gender", "man");
		decisionMatter.put("age", "29");
		// 男性 走  11
		// 女性 走  12
		// 小于25 走 111 121
		// 大于等于25 走 112 122
		EngineResult result = treeEngineHandle.process(10001L, "Oli09pLkdjh", treeRich, decisionMatter);

		assertNotNull(result, "test error");
		log.info("测试结果 {}", result);

	}

}
